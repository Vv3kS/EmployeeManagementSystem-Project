package com.employee.management.repositories;

import com.employee.management.dto.EmployeeDto;
import com.employee.management.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT new com.employee.management.dto.EmployeeDto(" +
            "e.id, e.firstname, e.lastname, e.email, e.phone, e.gender, " +
            "e.dateOfBirth, e.dateOfJoining, e.salary, e.department.id, null)" +
            " FROM Employee e " +
            "WHERE LOWER(e.firstname) LIKE %:query% OR " +
            "LOWER(e.lastname) LIKE %:query% OR " +
            "LOWER(e.email) LIKE %:query% OR " +
            "LOWER(e.phone) LIKE %:query% OR " +   // Added phone search
            "LOWER(e.gender) LIKE %:query% OR " +  // Added gender search
            "STR(e.id) = :query OR " +
            "STR(e.salary) = :query OR " +
            "STR(e.dateOfBirth) = :query OR " +
            "STR(e.dateOfJoining) = :query OR " +
            "STR(e.department.id) LIKE %:query%")
    List<EmployeeDto> searchEmployees(@Param("query") String query);
}
