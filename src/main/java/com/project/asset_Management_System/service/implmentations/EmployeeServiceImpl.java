package com.project.asset_Management_System.service.implmentations;

import com.project.asset_Management_System.model.AssetType;
import com.project.asset_Management_System.model.Employee;
import com.project.asset_Management_System.repository.EmployeeRepository;
import com.project.asset_Management_System.service.interfaces.EmployeeService;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public ResponseEntity<Employee> getEmployeeById(int id) {
        employeeRepository.findById(id).get();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> createEmployees(List<Employee> employees) {
        try {
            employeeRepository.saveAll(employees);
            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully created.");

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while creating employee.");
        }
    }

    @Override
    public ResponseEntity<String> updateEmployee(Employee employee, int id) {
        try {
            Optional<Employee> existingEmployee = employeeRepository.findById(id);
            if (existingEmployee.isPresent()) {
                Employee originalEmployee = existingEmployee.get();
                originalEmployee.setEmployeeCode(employee.getEmployeeCode());
                originalEmployee.setName(employee.getName());
                originalEmployee.setPosition(employee.getPosition());
                originalEmployee.setDepartment(employee.getDepartment());
                originalEmployee.setPhoneNumber(employee.getPhoneNumber());
                employeeRepository.save(originalEmployee);
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Updated successfully.");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while updating employee");
        }
    }

    @Override
    public ResponseEntity<String> deleteEmployee(int id) {
        try{
            Optional<Employee> existingEmployee = employeeRepository.findById(id);
            if (existingEmployee.isPresent()) {
                Employee originalEmployee = existingEmployee.get();
                originalEmployee.setDeleted(Boolean.TRUE);
                employeeRepository.save(originalEmployee);
            }
            return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully.");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while deleting asset type");
        }
    }

}