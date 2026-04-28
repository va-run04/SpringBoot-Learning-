package com.vk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vk.model.Account;
import com.vk.repository.AccountRepository;

import jakarta.transaction.Transactional;

@Service
public class AccountServiceImple implements AccountService{
	
	
	@Autowired
	private AccountRepository repo;

	@Override
	public void createAccount(Account account) {
		repo.save(account);
	}

	@Override
	public Account getAccount(Long id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Account not found exception: "+id));
	}

	@Override
	public List<Account> getAllAccounts() {
		return repo.findAll();
	}

	@Override
	@Transactional
	public void transfer(Long fromId, Long toId, double amount) {
		
		Account from = getAccount(fromId);
		Account to = getAccount(toId);
		
		//Check sufficient Balance
		if(from.getBalance() < amount){
			throw new RuntimeException("balance is less than amount: "+amount);
		}
		
		from.setBalance(from.getBalance() - amount);
		repo.save(from);
		
		to.setBalance(from.getBalance() + amount);
		repo.save(to);
		
		System.out.println("Transfer successful");
        System.out.println("From: " + from);
        System.out.println("To:   " + to);
	}

}
