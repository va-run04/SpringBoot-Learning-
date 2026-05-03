package com.vk.repository;

import com.vk.model.Employee;
import com.vk.projection.NameCityProjection;
import com.vk.projection.NameDepartmentProjection;
import com.vk.projection.NameSalaryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // fetch only name and city
    List<NameCityProjection> findAllNameCityBy();

    // fetch only name and salary
    List<NameSalaryProjection> findAllNameSalaryBy();

    // fetch only name and department
    List<NameDepartmentProjection> findAllNameDepartmentBy();

    // projection with filter
    List<NameSalaryProjection> findByDepartment(String department);
}