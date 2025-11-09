package org.touheed.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.touheed.journalApp.entity.Address;
import org.touheed.journalApp.entity.Employee;
import org.touheed.journalApp.service.EmployeeAddressService;

@RestController
@RequestMapping("/employee")
public class EmployeeAddressController {

    @Autowired
    EmployeeAddressService employeeAddressService;

    @PostMapping("/addEmployee")
    public Employee saveEmployee(@RequestBody Employee employee){
        return employeeAddressService.saveEmployee(employee);
    }

    @PostMapping("/addAddress")
    public Address saveAddress(@RequestBody Address address){
        return employeeAddressService.saveAddress(address);
    }

    @GetMapping("/getEmployee/{id}")
    public Employee getEmployee(@PathVariable long id){
        return employeeAddressService.getEmployee(id).get();
    }

    @GetMapping("/getAddress/{id}")
    public Address getAddress(@PathVariable long id){
        return employeeAddressService.getAddress(id).get();
    }

}
