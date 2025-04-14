package com.employee.management.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;


    @NotBlank(message = "First name is required")
    @Size(max = 18 , message = "first name cannot exceed 18 characters")
    private String firstname ;

    @NotBlank(message = "Last name is required")
    @Size(max = 18 , message = "Last name cannot exceed 18 characters")
    private String lastname  ;

    @Email(message = "Email must be valid")
    @Column(unique = true)
    private String email ;

    @Pattern(regexp = "^[0-9]{10,15}$" , message = "phone number must be 10-15 digits")
    private String phone;

    @Enumerated(EnumType.STRING)
    private Gender gender ;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth ;

    @PastOrPresent(message = "Joining date cannot be in the future")
    private LocalDate dateOfJoining;

    @PositiveOrZero(message = "salary cannot be negative")
    private double salary ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department ;

    @ManyToMany
    @JoinTable(
            name = "Employee_projects",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private Set<Project> projects = new HashSet<>() ;

    public Employee() {
    }

    public Employee(Long id, String firstname, String lastname, String email, String phone, Gender gender, LocalDate dateOfBirth, LocalDate dateOfJoining, double salary, Department department, Set<Project> projects) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.dateOfJoining = dateOfJoining;
        this.salary = salary;
        this.department = department;
        this.projects = projects;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", gender=" + gender +
                ", dateOfBirth=" + dateOfBirth +
                ", dateOfJoining=" + dateOfJoining +
                ", salary=" + salary +
                ", department=" + department +
                ", projects=" + projects +
                '}';
    }

    public void addProject(Project project) {
        this.projects.add(project);
        project.getEmployees().add(this);
    }

    // Helper method to remove project
    public void removeProject(Project project) {
        this.projects.remove(project);
        project.getEmployees().remove(this);
    }
}
