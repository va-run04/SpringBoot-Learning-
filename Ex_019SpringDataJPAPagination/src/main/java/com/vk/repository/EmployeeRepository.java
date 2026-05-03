package com.vk.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vk.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	Page<Employee> findByCity(String city, Pageable pageable);
	
}