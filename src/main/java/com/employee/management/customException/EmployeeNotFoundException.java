package com.employee.management.customException;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s: '%s'", resourceName, fieldName, fieldValue));
    }
}
