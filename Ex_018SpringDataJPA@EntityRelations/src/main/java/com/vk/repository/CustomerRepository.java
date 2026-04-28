package com.vk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vk.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	

}
