package com.employee.management.dto;

import com.employee.management.entities.ProjectStatus;

import java.util.Set;

public class ProjectDto {
    private Long id;
    private String name;
    private String description;
    private Long departmentId;
    private Set<Long> employeeIds;
    private ProjectStatus status;

    public ProjectDto(){}

    public ProjectDto(ProjectStatus status, Set<Long> employeeIds, Long departmentId, String description, String name, Long id) {
        this.status = status;
        this.employeeIds = employeeIds;
        this.departmentId = departmentId;
        this.description = description;
        this.name = name;
        this.id = id;
    }

    public Set<Long> getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(Set<Long> employeeIds) {
        this.employeeIds = employeeIds;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ProjectDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", departmentId=" + departmentId +
                ", employeeIds=" + employeeIds +
                ", status=" + status +
                '}';
    }
}