package com.vk.service;

import com.vk.model.Transaction;
import java.util.List;

public interface TransactionService {
    void addTransaction(Transaction transaction);
    List<Transaction> getAllTransactions();
    List<Transaction> getTransactionsByAccount(Long accountId);
}