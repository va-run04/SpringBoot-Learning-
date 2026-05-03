package com.vk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vk.model.Employee;


public interface EmployeeeRepository extends JpaRepository<Employee, Long>{
	
}
