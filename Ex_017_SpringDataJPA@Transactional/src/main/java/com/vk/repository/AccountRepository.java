package com.vk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vk.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}
