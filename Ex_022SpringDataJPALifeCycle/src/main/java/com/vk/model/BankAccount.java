package com.vk.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bank_account")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountHolder;
    private String accountType;
    private Double balance;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BankAccount() {}

    public BankAccount(String accountHolder, String accountType, Double balance) {
        this.accountHolder = accountHolder;
        this.accountType = accountType;
        this.balance = balance;
    }

    // Lifecycle Callbacks 

    @PrePersist
    public void beforeCreate() {
        this.status = "ACTIVE";                  // set default status
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        System.out.println("@PrePersist  → Creating account for: " + accountHolder);
    }

    @PostPersist
    public void afterCreate() {
        System.out.println("@PostPersist → Account created with id: " + id +
                           " | Status: " + status);
    }

    @PreUpdate
    public void beforeUpdate() {
        this.updatedAt = LocalDateTime.now();
        System.out.println("@PreUpdate   → Updating account id: " + id +
                           " | Holder: " + accountHolder);
    }

    @PostUpdate
    public void afterUpdate() {
        System.out.println("@PostUpdate  → Account updated | New balance: " + balance);
    }

    @PreRemove
    public void beforeDelete() {
        System.out.println("@PreRemove   → Deleting account id: " + id +
                           " | Holder: " + accountHolder);
    }

    @PostRemove
    public void afterDelete() {
        System.out.println("@PostRemove  → Account deleted successfully");
    }

    @PostLoad
    public void afterLoad() {
        System.out.println("@PostLoad    → Account loaded: " + accountHolder +
                           " | Balance: " + balance);
    }

    // Getters and Setters 

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAccountHolder() { return accountHolder; }
    public void setAccountHolder(String accountHolder) { this.accountHolder = accountHolder; }

    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }

    public Double getBalance() { return balance; }
    public void setBalance(Double balance) { this.balance = balance; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    
    //toString method
    @Override
    public String toString() {
        return "BankAccount[id=" + id +
               ", holder=" + accountHolder +
               ", type=" + accountType +
               ", balance=" + balance +
               ", status=" + status +
               ", createdAt=" + createdAt +
               ", updatedAt=" + updatedAt + "]";
    }
}