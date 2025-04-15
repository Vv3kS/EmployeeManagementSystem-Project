package com.employee.management.services;

import com.employee.management.customException.DepartmentNotFoundException;
import com.employee.management.customException.ProjectNotFoundException;
import com.employee.management.customException.EmployeeNotFoundException;
import com.employee.management.dto.EmployeeDto;
import com.employee.management.entities.Department;
import com.employee.management.entities.Employee;
import com.employee.management.entities.Project;
import com.employee.management.repositories.DepartmentRepository;
import com.employee.management.repositories.EmployeeRepository;
import com.employee.management.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto addEmployee(EmployeeDto edto) {
        return mapToDto(employeeRepository.save(mapToEntity(edto)));
    }

    private EmployeeDto mapToDto(Employee emp) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(emp.getId());
        dto.setFirstname(emp.getFirstname());
        dto.setLastname(emp.getLastname());
        dto.setEmail(emp.getEmail());
        dto.setPhone(emp.getPhone());
        dto.setGender(emp.getGender());
        dto.setDateOfBirth(emp.getDateOfBirth());
        dto.setDateOfJoining(emp.getDateOfJoining());
        dto.setSalary(emp.getSalary());

        if (emp.getDepartment() != null) {
            dto.setDepartmentId(emp.getDepartment().getId());
        }

        if (emp.getProjects() != null && !emp.getProjects().isEmpty()) {
            Set<Long> projectIds = emp.getProjects().stream()
                    .map(Project::getId)
                    .collect(Collectors.toSet());
            dto.setProjectIds(projectIds);
        }

        return dto;
    }

    private Employee mapToEntity(EmployeeDto edto) {
        Employee employee = new Employee();
        employee.setId(edto.getId());
        employee.setFirstname(edto.getFirstname());
        employee.setLastname(edto.getLastname());
        employee.setEmail(edto.getEmail());
        employee.setPhone(edto.getPhone());
        employee.setGender(edto.getGender());
        employee.setDateOfBirth(edto.getDateOfBirth());
        employee.setDateOfJoining(edto.getDateOfJoining());
        employee.setSalary(edto.getSalary());

        if (edto.getDepartmentId() != null) {
            Department department = departmentRepository.findById(edto.getDepartmentId())
                    .orElseThrow(() -> new DepartmentNotFoundException("Department", "id", edto.getDepartmentId()));
            employee.setDepartment(department);
        }

        if (edto.getProjectIds() != null && !edto.getProjectIds().isEmpty()) {
            Set<Project> projects = edto.getProjectIds().stream()
                    .map(id -> projectRepository.findById(id)
                            .orElseThrow(() -> new ProjectNotFoundException("Project", "id", id)))
                    .collect(Collectors.toSet());
            employee.setProjects(projects);
        }

        return employee;
    }

    @Override
    public EmployeeDto put(Long id, EmployeeDto edto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee", "id", id));

        employee.setFirstname(edto.getFirstname());
        employee.setLastname(edto.getLastname());
        employee.setEmail(edto.getEmail());
        employee.setPhone(edto.getPhone());
        employee.setGender(edto.getGender());
        employee.setDateOfBirth(edto.getDateOfBirth());
        employee.setDateOfJoining(edto.getDateOfJoining());
        employee.setSalary(edto.getSalary());

        if (edto.getDepartmentId() != null) {
            Department dept = departmentRepository.findById(edto.getDepartmentId())
                    .orElseThrow(() -> new DepartmentNotFoundException("Department", "id", edto.getDepartmentId()));
            employee.setDepartment(dept);
        }

        if (edto.getProjectIds() != null && !edto.getProjectIds().isEmpty()) {
            Set<Project> projects = edto.getProjectIds().stream()
                    .map(pid -> projectRepository.findById(pid)
                            .orElseThrow(() -> new ProjectNotFoundException("Project", "id", pid)))
                    .collect(Collectors.toSet());
            employee.setProjects(projects);
        }

        return mapToDto(employeeRepository.save(employee));
    }

    @Override
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException("Employee", "id", id);
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee", "id", id));
        return mapToDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        return employeeRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}
