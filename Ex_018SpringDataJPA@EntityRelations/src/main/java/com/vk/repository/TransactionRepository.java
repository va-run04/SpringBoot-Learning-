package com.vk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vk.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	
	List<Transaction> findByAccountId(Long accountId);
	

}
