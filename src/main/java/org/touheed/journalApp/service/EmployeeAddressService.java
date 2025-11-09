package org.touheed.journalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.touheed.journalApp.entity.Address;
import org.touheed.journalApp.entity.Employee;
import org.touheed.journalApp.repo.AddressRepo;
import org.touheed.journalApp.repo.EmployeeRepo;

import java.util.Optional;

@Service
public class EmployeeAddressService {

    @Autowired
    EmployeeRepo employeeRepo;
    @Autowired
    AddressRepo addressRepo;

    public Employee saveEmployee(Employee employee){
        return employeeRepo.save(employee);
    }

    public Address saveAddress(Address address){
        return addressRepo.save(address);
    }

    public Optional<Address> getAddress(long id){
        return addressRepo.findById(id);
    }

    public Optional<Employee> getEmployee(long id){
        return employeeRepo.findById(id);
    }


}
