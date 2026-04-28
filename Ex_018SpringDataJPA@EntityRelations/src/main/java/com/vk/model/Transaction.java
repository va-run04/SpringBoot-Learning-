package com.vk.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double amount;
	
	private String type;  // Debit or Credit
	
	private LocalDateTime timestamp;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="account_id")  //Foreign key in transaction table
	private Account account;
	
	public Transaction() {}
	
	public Transaction(Double amount, String type,  Account account) {
		this.amount = amount;
        this.type = type;
        this.timestamp = LocalDateTime.now();  // auto set current time
        this.account = account;
	}
	
	public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public Account getAccount() { return account; }
    public void setAccount(Account account) { this.account = account; }

    @Override
    public String toString() {
        return "Transaction[id=" + id + ", amount=" + amount +
               ", type=" + type + ", timestamp=" + timestamp + "]";
        // don't include account — causes circular reference
	
	
    }
	
}
