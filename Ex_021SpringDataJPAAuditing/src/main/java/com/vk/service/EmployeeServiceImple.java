package com.vk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vk.model.Employee;
import com.vk.repository.EmployeeeRepository;

@Service
public class EmployeeServiceImple implements EmployeeService{
	
	
	private EmployeeeRepository repository;
	
	@Autowired
	EmployeeServiceImple(EmployeeeRepository repository) {
		this.repository = repository;
	}

	@Override
	public Employee addEmployee(Employee employee) {
		return repository.save(employee);
	}

	@Override
	public Employee updateEmployee(Long id, String newCity) {
		Employee employee = repository.findById(id).orElseThrow(() -> new RuntimeException("Employee Not Found: "+id));
		employee.setCity(newCity);
		return repository.save(employee);
		
	}

	@Override
	public List<Employee> getAllEmployees() {
		return repository.findAll();
	}

}
