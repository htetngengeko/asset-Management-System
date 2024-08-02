package com.project.asset_Management_System.service.interfaces;

import com.project.asset_Management_System.model.Asset;
import com.project.asset_Management_System.model.Employee;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    ResponseEntity<Employee> getEmployeeById(int id);

    ResponseEntity<String> createEmployees(List<Employee> employees) ;

    ResponseEntity<String> updateEmployee(Employee employee, int id);

    ResponseEntity<String> deleteEmployee(int id);

}
