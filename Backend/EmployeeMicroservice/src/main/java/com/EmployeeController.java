package com;

import com.data.EmployeeDTO;
import com.data.EmployeeResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class EmployeeController {
    @Autowired
    private EmployeeDataService employeeDataService;

    //GETs
    @RequestMapping(value = "/employeeBackend", method = RequestMethod.GET)
    public HttpEntity<List<EmployeeResource>> getAllEmployees(){
        return new HttpEntity(employeeDataService.getEmployeeResources());
    }

    @RequestMapping(value = "/employeeBackend/id={employeeId}", method = RequestMethod.GET)
    public EmployeeResource getEmployeeById(@PathVariable int employeeId){
        return employeeDataService.getEmployeeResourceById(employeeId);
    }

    @RequestMapping(value = "/employeeBackend/firstname={employeeFirstname}", method = RequestMethod.GET)
    public HttpEntity<List<EmployeeResource>> getEmployeeByFirstname(@PathVariable String employeeFirstname){
        return new HttpEntity(employeeDataService.getEmployeeResourcesByFirstname(employeeFirstname));
    }

    @RequestMapping(value = "/employeeBackend/lastname={employeeLastname}", method = RequestMethod.GET)
    public HttpEntity<List<EmployeeResource>> getEmployeeByLastname(@PathVariable String employeeLastname){
        return new HttpEntity(employeeDataService.getEmployeeResourcesByLastname(employeeLastname));
    }

    //POST
    @RequestMapping(value = "/employeeBackend", method = RequestMethod.POST)
    public EmployeeResource addEmployee(@RequestBody EmployeeDTO employeeDTO){
        return employeeDataService.addService(employeeDTO); // TODO
    }

    //PUT
    @RequestMapping(value = "/employeeBackend/id={employeeId}", method = RequestMethod.PUT)
    public EmployeeResource updateEmployee(@PathVariable int employeeId, @RequestBody EmployeeDTO employeeDTO){
        return employeeDataService.updateService(employeeId, employeeDTO); // TODO
    }

    //DELETE
    @RequestMapping(value = "/employeeBackend/id={employeeId}", method = RequestMethod.DELETE)
    public EmployeeResource deleteEmployee(@PathVariable int employeeId){
        return employeeDataService.deleteService(employeeId); // TODO
    }
}
