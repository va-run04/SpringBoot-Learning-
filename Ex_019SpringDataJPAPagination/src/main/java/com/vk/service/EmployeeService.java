package com.vk.service;


import org.springframework.data.domain.Page;

import com.vk.model.Employee;

public interface EmployeeService {
	
	void addEmployee(Employee employee);
	Page<Employee> getAllEmployeesPaged(int page, int size);
	Page<Employee> getAllEmployeesSorted(int page, int size, String sortBy);
	Page<Employee> getEmployeesByCity(String city, int page, int size);
	
	
	
}
