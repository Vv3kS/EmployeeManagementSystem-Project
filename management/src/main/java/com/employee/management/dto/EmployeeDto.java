package com.employee.management.dto;

import com.employee.management.entities.*;
import java.time.LocalDate;
import java.util.Set;

public class EmployeeDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private Gender gender;
    private LocalDate dateOfBirth;
    private LocalDate dateOfJoining;
    private double salary;
    private Long departmentId;
    private Set<Long> projectIds;

    public EmployeeDto(){}
    public EmployeeDto(Long id, String firstname, String lastname, String email, String phone, Gender gender, LocalDate dateOfBirth, LocalDate dateOfJoining, double salary, Long departmentId, Set<Long> projectIds) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.dateOfJoining = dateOfJoining;
        this.salary = salary;
        this.departmentId = departmentId;
        this.projectIds = projectIds;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Set<Long> getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(Set<Long> projectIds) {
        this.projectIds = projectIds;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", gender=" + gender +
                ", dateOfBirth=" + dateOfBirth +
                ", dateOfJoining=" + dateOfJoining +
                ", salary=" + salary +
                ", departmentId=" + departmentId +
                ", projectIds=" + projectIds +
                '}';
    }
}
