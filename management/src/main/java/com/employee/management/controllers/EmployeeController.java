package com.employee.management.controllers;

import com.employee.management.dto.EmployeeDto;
import com.employee.management.entities.Employee;
import com.employee.management.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService ;

   @GetMapping("/getall")
    public ResponseEntity<List<EmployeeDto>> getAllEmployee(){
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

     @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id , @RequestBody EmployeeDto dto){
        return ResponseEntity.ok(employeeService.put(id ,dto));
    }

    @PostMapping("/add")
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto dto){
        return ResponseEntity.ok(employeeService.addEmployee(dto));
    }

    @DeleteMapping("delete/{id}")
    public  ResponseEntity<String> deleteEmployee(@PathVariable Long id ){
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build().ok("Employee deleted Successfully with id "+id) ;
    }

}
