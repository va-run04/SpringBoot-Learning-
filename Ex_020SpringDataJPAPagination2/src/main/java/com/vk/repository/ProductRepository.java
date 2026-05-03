package com.vk.repository;

import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vk.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
    //Find By Category
	Page<Product> findByCategory(String category, Pageable pageable);
	
	//Find By Name Keyword
	Page<Product> findByNameContaining(String keyword, Pageable pageable);
	

	
	Page<Product> findByCategoryAndNameContaining(String category, String keyword, Pageable pageable);
	
 }
