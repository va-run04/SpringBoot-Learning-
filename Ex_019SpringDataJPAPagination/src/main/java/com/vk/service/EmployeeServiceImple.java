package com.vk.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.vk.model.Employee;
import com.vk.repository.EmployeeRepository;

@Service
public class EmployeeServiceImple implements EmployeeService{
	
	@Autowired
	private EmployeeRepository repository;
	
	@Autowired
	public EmployeeServiceImple(EmployeeRepository repository) {
		this.repository = repository;
	}

	@Override
	public void addEmployee(Employee employee) {
		repository.save(employee);
		
	}

	@Override
	public Page<Employee> getAllEmployeesPaged(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return repository.findAll(pageable);
	}

	@Override
	public Page<Employee> getAllEmployeesSorted(int page, int size, String sortBy) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return repository.findAll(pageable);
	}

	@Override
	public Page<Employee> getEmployeesByCity(String city, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return repository.findByCity(city, pageable);
	}
	

}
