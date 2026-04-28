package com.vk.service;

import com.vk.model.Account;
import java.util.List;

public interface AccountService {
    void addAccount(Account account);
    Account getAccount(Long id);
    List<Account> getAllAccounts();
    List<Account> getAccountsByCustomer(Long customerId);
    void deleteAccount(Long id);
}