package com.vk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vk.model.Employee;

import jakarta.transaction.Transactional;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	//Here we are creating custom Queries
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
	
	//delete (It requires @Transactional because here data is being modified and of something 
	// goes wrong and error occurs then it rolls back to original state
	// Transactions are not applied to read operation or fetch operations
	@Transactional
	void deleteByCity(String city);
	
	
	

	
}
