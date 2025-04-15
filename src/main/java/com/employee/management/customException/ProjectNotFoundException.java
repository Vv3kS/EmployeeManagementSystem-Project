package com.employee.management.customException;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException (String resouce , String field , Object value){
        super(String.format("%s not found with %s : '%s'" , resouce , field , value));

    }
}
