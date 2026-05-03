package com.vk.service;

import com.vk.model.Employee;
import com.vk.projection.NameCityProjection;
import com.vk.projection.NameDepartmentProjection;
import com.vk.projection.NameSalaryProjection;
import com.vk.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repo;

    @Override
    public void addEmployee(Employee employee) {
        repo.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

    @Override
    public List<NameCityProjection> getNameAndCity() {
        return repo.findAllNameCityBy();
    }

    @Override
    public List<NameSalaryProjection> getNameAndSalary() {
        return repo.findAllNameSalaryBy();
    }

    @Override
    public List<NameDepartmentProjection> getNameAndDepartment() {
        return repo.findAllNameDepartmentBy();
    }

    @Override
    public List<NameSalaryProjection> getByDepartment(String department) {
        return repo.findByDepartment(department);
    }
}