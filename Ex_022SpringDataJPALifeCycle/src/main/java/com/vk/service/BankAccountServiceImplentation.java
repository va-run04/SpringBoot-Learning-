package com.vk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vk.model.BankAccount;
import com.vk.repository.BanckAccountRepositoty;

@Service
public class BankAccountServiceImplentation implements BankAccountService{
	
	@Autowired
	private BanckAccountRepositoty repositoty;

	@Override
	public BankAccount createAccount(BankAccount account) {
		// TODO Auto-generated method stub
		return repositoty.save(account);
	}

	@Override
	public BankAccount deposit(Long id, Double amount) {
		BankAccount account = repositoty.findById(id).orElseThrow(() -> new RuntimeException("Account not found: "+id));
		account.setBalance(account.getBalance()+amount);
		return repositoty.save(account);    // triggers @PreUpdate and @PostUpdate
		
	}

	@Override
	public BankAccount closeAccount(Long id) {
		BankAccount account = repositoty.findById(id).orElseThrow(() -> new RuntimeException("Account not found: "+id));
		account.setStatus("Closed");
		return account;      // triggers @PreUpdate and @PostUpdate
	}

	@Override
	public void deleteAccount(Long id) {
		BankAccount account = repositoty.findById(id).orElseThrow(() -> new RuntimeException("Account not found: "+id));
		repositoty.delete(account);  // triggers @PreRemove and @PostRemove
	}

	@Override
	public List<BankAccount> getAllAccounts() {
		
		return repositoty.findAll();  // triggers @PostLoad for each account
	}
	
}
