package com;

import com.data.*;
import com.exceptions.BadRequestException;
import com.exceptions.ResourceNotFoundException;
import com.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EmployeeDataService {

    @Autowired
    private EmployeeRepository employeeRepository;

    private final int EMPLOYEE_FIRSTNAME_MIN_LENGTH = 1;
    private final int EMPLOYEE_LASTNAME_MIN_LENGTH = 3;

    private EmployeeResource convertEmployeeToEmployeeResource(Employee employee) {
        EmployeeResource er = new EmployeeResource();

        er.setId(employee.getId());
        er.setFirstname(employee.getFirstname());
        er.setLastname(employee.getLastname());

        return er;
    }

    private Employee convertEmployeeEntityToEmployee(EmployeeEntity employeeEntity){
        Employee e = new Employee();

        e.setId(employeeEntity.getId());
        e.setFirstname(employeeEntity.getFirstname());
        e.setLastname(employeeEntity.getLastname());

        return e;
    }

    public List<EmployeeResource> getEmployeeResources(){
        List<EmployeeResource> result = new ArrayList<>();

        for(EmployeeEntity employeeEntity : employeeRepository.findAll()){
            EmployeeResource employeeResource = convertEmployeeToEmployeeResource(convertEmployeeEntityToEmployee(employeeEntity));
            result.add(employeeResource);
        }

        return result;
    }

    public EmployeeResource getEmployeeResourceById(int id) {
        Optional<EmployeeEntity> search = employeeRepository.findById(id);
        if(search.isPresent()){
            return convertEmployeeToEmployeeResource(convertEmployeeEntityToEmployee(search.get()));
        }else{
            throw new ResourceNotFoundException("The employee with the ID " + id + " does not exist!");
        }
    }

    public List<EmployeeResource> getEmployeeResourcesByFirstname(String employeeFirstname) {
        List<EmployeeResource> result = new ArrayList<>();

        for(EmployeeEntity employeeEntity : employeeRepository.findByFirstname(employeeFirstname)){
            EmployeeResource employeeResource = convertEmployeeToEmployeeResource(convertEmployeeEntityToEmployee(employeeEntity));
            result.add(employeeResource);
        }

        return result;
    }

    public List<EmployeeResource> getEmployeeResourcesByLastname(String employeeLastname) {
        List<EmployeeResource> result = new ArrayList<>();

        for(EmployeeEntity employeeEntity : employeeRepository.findByLastname(employeeLastname)){
            EmployeeResource employeeResource = convertEmployeeToEmployeeResource(convertEmployeeEntityToEmployee(employeeEntity));
            result.add(employeeResource);
        }

        return result;
    }

    //POST
    public EmployeeResource addService(EmployeeDTO employeeDTO) {
        checkEmployeeDTO(employeeDTO);

        EmployeeEntity e = new EmployeeEntity();

        e.setId(-1);
        e.setFirstname(employeeDTO.getFirstname());
        e.setLastname(employeeDTO.getLastname());

        int id = employeeRepository.save(e).getId();
        e.setId(id);

        return convertEmployeeToEmployeeResource(convertEmployeeEntityToEmployee(e));
    }

    //PUT
    public EmployeeResource updateService(int employeeId, EmployeeDTO employeeDTO) {
        Optional<EmployeeEntity> ee = employeeRepository.findById(employeeId);

        if(ee.isPresent()){

            // Check if firstname should be updated
            if(!isNullOrEmpty(employeeDTO.getFirstname())) {

                // Check if firstname is long enough to get updated
                if (employeeDTO.getFirstname().length() >= EMPLOYEE_FIRSTNAME_MIN_LENGTH) {
                    ee.get().setFirstname(employeeDTO.getFirstname());
                } else {
                    throw new BadRequestException(
                            "The firstname of the employee must be equal or longer than " +
                                    EMPLOYEE_FIRSTNAME_MIN_LENGTH + " letters!");
                }
            }

            // Check if lastname should be updated
            if(!isNullOrEmpty(employeeDTO.getLastname())){

                // Check if lastname is long enough to get updated
                if(employeeDTO.getLastname().length() >= EMPLOYEE_LASTNAME_MIN_LENGTH){
                    ee.get().setLastname(employeeDTO.getLastname());
                }else{
                    throw new BadRequestException(
                            "The lastname of the employee must be equal or longer than " +
                                    EMPLOYEE_LASTNAME_MIN_LENGTH + " letters!");
                }
            }
            employeeRepository.save(ee.get());

            return convertEmployeeToEmployeeResource(convertEmployeeEntityToEmployee(ee.get()));
        }else{
            throw new ResourceNotFoundException("The employee with the ID " + employeeId + " could not be found!");
        }
    }

    // DELETE
    public EmployeeResource deleteService(int employeeId) {
        Optional<EmployeeEntity> ee = employeeRepository.findById(employeeId);
        if(ee.isPresent()){
            employeeRepository.deleteById(employeeId);
            return convertEmployeeToEmployeeResource(convertEmployeeEntityToEmployee(ee.get()));
        }else{
            throw new ResourceNotFoundException("The employee with the ID " + employeeId + " does not exist!");
        }
    }

    // Exception Handling
    private void checkEmployeeDTO(EmployeeDTO employeeDto) {
        if(isNullOrEmpty(employeeDto.getFirstname())){
            throw new BadRequestException(
                    "The firstname of the employee must be set!");
        }
        if(isNullOrEmpty(employeeDto.getLastname())){
            throw new BadRequestException(
                    "The lastname of the employee must be set!");
        }
        if(employeeDto.getFirstname().length() < EMPLOYEE_FIRSTNAME_MIN_LENGTH){
            throw new BadRequestException(
                    "The firstname of the employee must be equal or longer than " +
                            EMPLOYEE_FIRSTNAME_MIN_LENGTH + " letters!");
        }
        if(employeeDto.getLastname().length() < EMPLOYEE_LASTNAME_MIN_LENGTH){
            throw new BadRequestException(
                    "The lastname of the employee must be equal or longer than " +
                            EMPLOYEE_LASTNAME_MIN_LENGTH + " letters!");
        }
    }

    private boolean isNullOrEmpty(String string) {
        return string == null || string.equals("");
    }
}
