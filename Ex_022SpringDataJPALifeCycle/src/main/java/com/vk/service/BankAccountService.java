package com.vk.service;

import com.vk.model.BankAccount;
import java.util.List;

public interface BankAccountService {
    BankAccount createAccount(BankAccount account);
    BankAccount deposit(Long id, Double amount);
    BankAccount closeAccount(Long id);
    void deleteAccount(Long id);
    List<BankAccount> getAllAccounts();
}