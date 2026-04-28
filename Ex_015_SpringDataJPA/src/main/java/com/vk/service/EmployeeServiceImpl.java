package com.vk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vk.model.Employee;
import com.vk.repository.EmployeeRepository;


@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	
	@Autowired //Field injection using Autowired
	private EmployeeRepository repo;

	@Override
	public void addEmployee(Employee employee) {

		repo.save(employee);
		
	}

	@Override
	public Employee getEmployee(Long id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException());
		
	}

	@Override
	public List<Employee> getAllEmployees() {
		return repo.findAll();
	
	}

	@Override
	public void updateEmployee(Employee employee) {
		repo.save(employee);
	}

	@Override
	public void removeEmployee(Long id) {
		repo.deleteById(id);
	}

	@Override
	public List<Employee> findByCity(String city) {
		return repo.findByCity(city);
	}

	@Override
	public List<Employee> findByName(String name) {
		
		return repo.findByName(name);
	}

	@Override
	public List<Employee> findByCityAndName(String name, String city) {
		return repo.findByCityAndName(name, city);
	}

	@Override
	public List<Employee> findByCityOrName(String name, String city) {
		return  repo.findByCityOrName(name, city);
	}

	@Override
	public boolean existsByName(String name) {
		return repo.existsByName(name);
	}

	@Override
	public long countByCity(String city) {
		return repo.countByCity(city);
	}

	@Override
	public void deleteByCity(String city) {
		repo.deleteByCity(city);
	}

}
