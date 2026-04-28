package com.vk.service;

import com.vk.model.Transaction;
import com.vk.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository repo;

    @Override
    public void addTransaction(Transaction transaction) {
        repo.save(transaction);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return repo.findAll();
    }

    @Override
    public List<Transaction> getTransactionsByAccount(Long accountId) {
        return repo.findByAccountId(accountId);
    }
}