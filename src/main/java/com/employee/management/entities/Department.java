package com.employee.management.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @NotBlank(message = "department name must required")
    @Size( max = 15,message = "Department name cannot have more than 15 characters" )
    private String name ;

    @OneToMany(mappedBy = "department" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Employee> employees = new ArrayList<>();

    @OneToMany(mappedBy = "department" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Project> projects = new ArrayList<>() ;


    public Department(){}

    public Department(Long id, String name, List<Employee> employees, List<Project> projects) {
        this.id = id;
        this.name = name;
        this.employees = employees;
        this.projects = projects;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employees=" + employees +
                ", projects=" + projects +
                '}';
    }
}
