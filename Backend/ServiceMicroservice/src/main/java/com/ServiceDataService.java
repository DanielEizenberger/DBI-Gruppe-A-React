package com;

import com.data.*;
import com.exceptions.BadRequestException;
import com.exceptions.ResourceNotFoundException;
import com.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ServiceDataService {

    @Autowired
    private ServiceRepository serviceRepository;

    private final int SERVICE_NAME_LENGTH = 4;
    private final int SERVICE_ADDRESS_LENGTH = 5;
    private final String DATABASE_DATE_FORMAT = "yyyy-MM-dd";
    private final String URI_DATE_FORMAT = "dd-MM-yyyy";
    private final String LOCATIONIQ_API_KEY = "pk.525a3a60ea9bf42ae9bac92569a7a81e";

    private ServiceResource convertServiceToServiceResource(Service service) {
        ServiceResource sr = new ServiceResource();
        SimpleDateFormat sdf = new SimpleDateFormat(DATABASE_DATE_FORMAT);

        sr.setId(service.getId());
        sr.setEmployeeId(service.getEmployeeId());
        sr.setName(service.getName());
        sr.setDate(sdf.format(service.getDate()));
        sr.setLongitude(String.valueOf(service.getLongitude()));
        sr.setLatitude(String.valueOf(service.getLatitude()));
        sr.setAddress(service.getAddress());

        return sr;
    }

    private Service convertServiceEntityToService(ServiceEntity service){
        Service s = new Service();

        s.setId(service.getId());
        s.setName(service.getName());
        s.setEmployeeId(service.getEmployeeId());
        s.setDate(service.getDate());
        s.setLongitude(service.getLongitude());
        s.setLatitude(service.getLatitude());
        s.setAddress(service.getAddress());

        return s;
    }

    public List<ServiceResource> getServiceResources(){
        List<ServiceResource> result = new ArrayList<>();

        for(ServiceEntity service : serviceRepository.findAll()){
            ServiceResource serviceResource = convertServiceToServiceResource(convertServiceEntityToService(service));
            result.add(serviceResource);
        }

        return result;
    }

    public ServiceResource getServiceResourceById(int id) {
        Optional<ServiceEntity> search = serviceRepository.findById(id);
        if(search.isPresent()){
            return convertServiceToServiceResource(convertServiceEntityToService(search.get()));
        }else{
            throw new ResourceNotFoundException("The service with the ID " + id + " does not exist!");
        }
    }

    public List<ServiceResource> getServiceResourcesByName(String serviceName) {
        List<ServiceResource> result = new ArrayList<>();

        for(ServiceEntity service : serviceRepository.findByName(serviceName)){
            ServiceResource serviceResource = convertServiceToServiceResource(convertServiceEntityToService(service));
            result.add(serviceResource);
        }

        return result;
    }

    public List<ServiceResource> getServiceResourcesByEmployeeID(int employeeID) {
        List<ServiceResource> result = new ArrayList<>();

        for(ServiceEntity service : serviceRepository.findByEmployeeId(employeeID)){
            ServiceResource serviceResource = convertServiceToServiceResource(convertServiceEntityToService(service));
            result.add(serviceResource);
        }

        return result;
    }

    public List<ServiceResource> getServiceResourcesByDate(String serviceDate) {
        Date date = checkDateFormat(serviceDate, URI_DATE_FORMAT);
        List<ServiceResource> result = new ArrayList<>();

        for(ServiceEntity service : serviceRepository.findByDate(date)){
            ServiceResource serviceResource = convertServiceToServiceResource(convertServiceEntityToService(service));
            result.add(serviceResource);
        }

        return result;
    }

    //POST
    public ServiceResource addService(ServiceDTO serviceDTO) {
        checkServiceDto(serviceDTO);

        ServiceEntity s = new ServiceEntity();
        SimpleDateFormat sdf = new SimpleDateFormat(URI_DATE_FORMAT);

        s.setId(-1);
        s.setName(serviceDTO.getName());
        s.setEmployeeId(serviceDTO.getEmployeeId());
        try {
            s.setDate(sdf.parse(serviceDTO.getDate()));
        } catch (ParseException e) {
            throw new ResourceNotFoundException("The date must be in this format: " + URI_DATE_FORMAT);
        }
        Location location = getCoordinates(serviceDTO.getAddress());
        s.setLongitude(location.getLon());
        s.setLatitude(location.getLat());

        s.setAddress(serviceDTO.getAddress());

        int id = serviceRepository.save(s).getId();
        s.setId(id);

        return convertServiceToServiceResource(convertServiceEntityToService(s));
    }

    //PUT
    public ServiceResource updateService(int serviceId, ServiceDTO serviceDTO) {
        Optional<ServiceEntity> se = serviceRepository.findById(serviceId);

        if(se.isPresent()){
            SimpleDateFormat sdf = new SimpleDateFormat(URI_DATE_FORMAT);

            if(!serviceDTO.getName().equals("")) se.get().setName(serviceDTO.getName());
            if(serviceDTO.getEmployeeId() != 0) se.get().setEmployeeId(serviceDTO.getEmployeeId());
            try {
                if(serviceDTO.getDate() != null) se.get().setDate(sdf.parse(serviceDTO.getDate()));
            } catch (ParseException e) {
                throw new ResourceNotFoundException("The date must be in this format: " + URI_DATE_FORMAT);
            }
            if(!serviceDTO.getAddress().equals("")) {
                Location location = getCoordinates(serviceDTO.getAddress());
                se.get().setLongitude(location.getLon());
                se.get().setLatitude(location.getLat());

                se.get().setAddress(serviceDTO.getAddress());
            }
            serviceRepository.save(se.get());

            return convertServiceToServiceResource(convertServiceEntityToService(se.get()));
        }else{
            throw new ResourceNotFoundException("The service with the id " + serviceId + " could not be found!");
        }
    }

    // DELETE
    public ServiceResource deleteService(int serviceId) {
        Optional<ServiceEntity> se = serviceRepository.findById(serviceId);
        if(se.isPresent()){
            serviceRepository.deleteById(serviceId);
            return convertServiceToServiceResource(convertServiceEntityToService(se.get()));
        }else{
            throw new ResourceNotFoundException("The service with the ID " + serviceId + " does not exist!");
        }
    }

    // LOCATION IQ
    private Location getCoordinates(String address){
        final String uri = "https://eu1.locationiq.com/v1/search.php?key="
        + LOCATIONIQ_API_KEY + "&q=" + address + "&format=json&limit=1";
        Optional<Location> result = Arrays.stream(new RestTemplate().getForObject(uri, Location[].class)).findFirst();

        if(result.isPresent()) return result.get();
        else return new Location(0.0, 0.0);
    }

    // Exception Handling
    private Date checkDateFormat(String date, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date result = null;
        try {
            result = sdf.parse(date);
        } catch (ParseException e) {
            throw new BadRequestException("The provided date is in the wrong format! " +
                    "Please try " + format + ".");
        }
        return result;
    }

    private void checkServiceDto(ServiceDTO serviceDto) {
        if(serviceDto.getName().length() <= SERVICE_NAME_LENGTH){
            throw new BadRequestException(
                    "The name of the service must be longer than " +
                            SERVICE_NAME_LENGTH + " letters!");
        }
        if(isNullOrEmpty(serviceDto.getName())){
            throw new BadRequestException(
                    "The name of the service must be set!");
        }
        if(isNullOrEmpty(serviceDto.getDate())){
            throw new BadRequestException(
                    "The date of the service must be set!");
        }

        SimpleDateFormat sdf = new SimpleDateFormat(URI_DATE_FORMAT);
        try {
            if(sdf.parse(serviceDto.getDate()) == null){
                throw new ResourceNotFoundException(
                        "The date must be in this format: " +
                                URI_DATE_FORMAT);
            }
        } catch (ParseException e) {
            throw new ResourceNotFoundException(
                    "The date must be in this format: " +
                            URI_DATE_FORMAT);
        }

        if(isNullOrEmpty(serviceDto.getAddress())){
            throw new BadRequestException(
                    "The address of the service must be set");
        }
        if(serviceDto.getAddress().length() <= SERVICE_ADDRESS_LENGTH){
            throw new BadRequestException(
                    "The address of the service must be longer than " +
                            SERVICE_ADDRESS_LENGTH + " characters!");
        }
    }

    private boolean isNullOrEmpty(String string) {
        return string == null || string.equals("");
    }
}
