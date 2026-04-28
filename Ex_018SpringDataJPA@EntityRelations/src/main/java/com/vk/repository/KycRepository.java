package com.vk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vk.model.Kyc;

public interface KycRepository extends JpaRepository<Kyc, Long>{
	
     Kyc findByCustomerId(Long CustomerId);
     
}
