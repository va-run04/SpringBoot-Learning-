package com.vk.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vk.model.Account;


@Service
public interface AccountService{
	
	//create account
	void createAccount(Account account);
	
	//get account by id
	Account getAccount(Long id);
	
	//get all accounts
	List<Account> getAllAccounts();
	
	void transfer(Long fromId, Long toId, double amount);
	
}
