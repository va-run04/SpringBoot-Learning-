package com.vk.repository;

import java.util.List;
import java.util.ListResourceBundle;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vk.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	
	List<Account> findByCustomerId(Long id);
}
