package com.vk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vk.model.BankAccount;


public interface BanckAccountRepositoty extends JpaRepository<BankAccount, Long>{

}
