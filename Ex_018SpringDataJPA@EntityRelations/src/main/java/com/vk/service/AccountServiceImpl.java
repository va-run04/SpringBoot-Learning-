package com.vk.service;

import com.vk.model.Account;
import com.vk.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository repo;

    @Override
    public void addAccount(Account account) {
        repo.save(account);
    }

    @Override
    public Account getAccount(Long id) {
        return repo.findById(id)
                   .orElseThrow(() -> new RuntimeException("Account not found: " + id));
    }

    @Override
    public List<Account> getAllAccounts() {
        return repo.findAll();
    }

    @Override
    public List<Account> getAccountsByCustomer(Long customerId) {
        return repo.findByCustomerId(customerId);
    }

    @Override
    public void deleteAccount(Long id) {
        repo.deleteById(id);
    }
}