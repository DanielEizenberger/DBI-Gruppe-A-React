package com;

import com.data.ServiceDTO;
import com.data.ServiceResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class ServiceController {
    @Autowired
    private ServiceDataService serviceDataService;

    //GETs
    @RequestMapping(value = "/serviceBackend", method = RequestMethod.GET)
    public HttpEntity<List<ServiceResource>> getAllServices(){
        return new HttpEntity(serviceDataService.getServiceResources());
    }

    @RequestMapping(value = "/serviceBackend/id={serviceId}", method = RequestMethod.GET)
    public ServiceResource getServiceById(@PathVariable int serviceId){
        return serviceDataService.getServiceResourceById(serviceId);
    }

    @RequestMapping(value = "/serviceBackend/name={serviceName}", method = RequestMethod.GET)
    public HttpEntity<List<ServiceResource>> getServiceByName(@PathVariable String serviceName){
        return new HttpEntity(serviceDataService.getServiceResourcesByName(serviceName));
    }

    @RequestMapping(value = "/serviceBackend/employeeID={employeeID}", method = RequestMethod.GET)
    public HttpEntity<List<ServiceResource>> getServiceByEmployeeID(@PathVariable int employeeID){
        return new HttpEntity(serviceDataService.getServiceResourcesByEmployeeID(employeeID));
    }

    @RequestMapping(value = "/serviceBackend/date={serviceDate}", method = RequestMethod.GET)
    public HttpEntity<List<ServiceResource>> getServiceByDate(@PathVariable String serviceDate){
        return new HttpEntity(serviceDataService.getServiceResourcesByDate(serviceDate));
    }

    //POST
    @RequestMapping(value = "/serviceBackend", method = RequestMethod.POST)
    public ServiceResource addService(@RequestBody ServiceDTO serviceDTO){
        return serviceDataService.addService(serviceDTO);
    }

    //PUT
    @RequestMapping(value = "/serviceBackend/id={serviceId}", method = RequestMethod.PUT)
    public ServiceResource updateService(@PathVariable int serviceId, @RequestBody ServiceDTO serviceDTO){
        return serviceDataService.updateService(serviceId, serviceDTO);
    }

    //DELETE
    @RequestMapping(value = "/serviceBackend/id={serviceId}", method = RequestMethod.DELETE)
    public ServiceResource deleteService(@PathVariable int serviceId){
        return serviceDataService.deleteService(serviceId);
    }
}
