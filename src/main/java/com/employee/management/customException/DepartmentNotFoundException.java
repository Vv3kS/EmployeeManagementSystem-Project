package com.employee.management.customException;

public class DepartmentNotFoundException extends RuntimeException{
    public DepartmentNotFoundException(String resouce , String field , Object value){
        super(String.format("%s not found with %s : '%s'" , resouce , field , value));
    }
}
