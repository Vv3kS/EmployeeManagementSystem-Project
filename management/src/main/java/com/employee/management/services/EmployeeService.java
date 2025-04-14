package com.employee.management.services;

import com.employee.management.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto addEmployee(EmployeeDto edto);
    EmployeeDto put(Long id , EmployeeDto edto);
    void deleteEmployee(Long id) ;
    EmployeeDto getEmployeeById(Long id) ;
    List<EmployeeDto> getAllEmployee() ;

}
