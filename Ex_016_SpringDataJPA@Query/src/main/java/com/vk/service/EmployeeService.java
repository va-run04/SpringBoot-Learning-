package com.vk.service;

import java.util.List;

import com.vk.model.Employee;

public interface EmployeeService {
	
	
	// Saving Employee Data(we pass all the employee data here using employee model)
	void addEmployee(Employee employee);
	
	
	//get employee by ID
	Employee getEmployee(Long id);
	
	//gets all the employees
	List<Employee> getAllEmployees();
	
	//update employee(We pass all the employee data here using the Employee model)
	void updateEmployee(Employee employee);
	
	//Remove Employee
	void removeEmployee(Long id);
	
	
	//find by single field
		List<Employee> findByCity(String city);
		List<Employee> findByName(String name);
		
		// find by multiple fields
		List<Employee> findByCityAndName(String name, String city);
		List<Employee> findByCityOrName(String name, String city);
		

		//Check existence
		boolean existsByName(String name);
		
		//count
		long countByCity(String city);
		
		//delete
		void deleteByCity(String city);
		
		
		// @Query methods
		List<Employee> getEmployeesByCity(String city);
		List<String> getAllEmployeeNames();
		List<Employee> searchByName(String keyword);
		List<Employee> getEmployeesByCityNative(String city);
		int updateEmployeeCity(Long id, String city);
		int deleteEmployeesByCity(String city);
}
