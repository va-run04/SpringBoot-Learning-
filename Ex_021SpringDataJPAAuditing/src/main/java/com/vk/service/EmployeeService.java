package com.vk.service;


import java.util.List;

import com.vk.model.Employee;

public interface EmployeeService {
	
	Employee addEmployee(Employee employee);
	
	Employee updateEmployee(Long id, String newCity);
	
	List<Employee> getAllEmployees();
	
	
}
