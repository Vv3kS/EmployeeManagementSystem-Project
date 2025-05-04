package com.employee.management.controllers;

import com.employee.management.dto.EmployeeDto;
import com.employee.management.entities.Employee;
import com.employee.management.services.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/employee")
@Tag(name = "Employee Management", description = "APIs for managing employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Operation(summary = "Get all employees", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/getall")
    public ResponseEntity<List<EmployeeDto>> getAllEmployee(){
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }

    @Operation(summary = "Get employee by ID", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @Operation(summary = "Update employee", security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto dto){
        return ResponseEntity.ok(employeeService.put(id, dto));
    }

    @Operation(summary = "Add new employee", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/add")
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto dto) {
        return ResponseEntity.ok(employeeService.addEmployee(dto));
    }

    @Operation(summary = "Add multiple employees", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/add-bulk")
    public ResponseEntity<List<EmployeeDto>> addEmployee(@RequestBody List<EmployeeDto> dto){
        List<EmployeeDto> savedemployee = dto.stream().map(employeeService::addEmployee).toList();
        return ResponseEntity.ok(savedemployee);
    }

    @Operation(summary = "Search employees", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/search")
    public ResponseEntity<List<EmployeeDto>> searchEmployees(@RequestParam String query){
        return ResponseEntity.ok(employeeService.searchEmp(query));
    }

    @Operation(summary = "Delete employee", security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build().ok("Employee deleted Successfully with id "+id);
    }
}