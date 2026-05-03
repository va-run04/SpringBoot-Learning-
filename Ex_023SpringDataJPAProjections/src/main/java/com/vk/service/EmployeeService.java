package com.vk.service;

import com.vk.model.Employee;
import com.vk.projection.NameCityProjection;
import com.vk.projection.NameDepartmentProjection;
import com.vk.projection.NameSalaryProjection;
import java.util.List;

public interface EmployeeService {
    void addEmployee(Employee employee);
    List<Employee> getAllEmployees();
    List<NameCityProjection> getNameAndCity();
    List<NameSalaryProjection> getNameAndSalary();
    List<NameDepartmentProjection> getNameAndDepartment();
    List<NameSalaryProjection> getByDepartment(String department);
}