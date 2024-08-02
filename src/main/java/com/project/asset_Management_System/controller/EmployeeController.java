package com.project.asset_Management_System.controller;

import com.project.asset_Management_System.model.AssetType;
import com.project.asset_Management_System.model.Employee;
import com.project.asset_Management_System.service.implmentations.AssetTypeServiceImpl;
import com.project.asset_Management_System.service.implmentations.EmployeeServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeServiceImpl;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeServiceImpl.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
        return employeeServiceImpl.getEmployeeById(id);
    }

    @PostMapping("/employees")
    public ResponseEntity<String> createEmployee(@Valid @RequestBody List<Employee> employees) {
        return employeeServiceImpl.createEmployees(employees);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<String> updateEmployee(@Valid @RequestBody Employee employee, @PathVariable int id) {
        return employeeServiceImpl.updateEmployee(employee, id);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
        return employeeServiceImpl.deleteEmployee(id);
    }
}
