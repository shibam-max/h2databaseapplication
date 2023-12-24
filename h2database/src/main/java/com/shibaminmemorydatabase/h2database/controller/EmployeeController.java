package com.shibaminmemorydatabase.h2database.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shibaminmemorydatabase.h2database.dao.EmployeeRepository;
import com.shibaminmemorydatabase.h2database.model.Employee;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/saveEmployee")
    public String saveEmployee(@RequestBody Employee employee) {

        employeeRepository.save(employee);
        return "Employee saved...";

    }

    @GetMapping("/getAllEmployees")
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @GetMapping("/getAllEmployees/{dept}")
    public List<Employee> getEmployeesByDept(@PathVariable String dept) {

        return employeeRepository.findByDept(dept);

    }

    @PutMapping("/updatedEmployee/{id}")
    public String updateEmployee(@PathVariable int id, @RequestBody Employee updatedEmployee) {
        Optional<Employee> existingEmployee = employeeRepository.findById(id);

        if (existingEmployee.isPresent()) {
            Employee presentEmployee = existingEmployee.get();
            presentEmployee.setName(updatedEmployee.getName());
            presentEmployee.setDept(updatedEmployee.getDept());
            presentEmployee.setSalary(updatedEmployee.getSalary());
            employeeRepository.save(presentEmployee);
            return "Employee updated";
        } else {
            return "Employee not found";
        }

    }

    @DeleteMapping("/delete/{id}")
    public String deleteMapping(@PathVariable int id) {

        Optional<Employee> existingEmployee = employeeRepository.findById(id);

        if (existingEmployee.isPresent()) {
            employeeRepository.delete(existingEmployee.get());

            return "Employee deleted";
        } else {
            return "employee not found";
        }
    }

}
