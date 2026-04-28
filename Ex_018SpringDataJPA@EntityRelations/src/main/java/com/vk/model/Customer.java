package com.vk.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	
	private String email;
	
	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Kyc kyc;
	
	// add getter and setter
	public Kyc getKyc() { return kyc; }
	public void setKyc(Kyc kyc) { this.kyc = kyc; }
	
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Account> accounts = new ArrayList<>();
	
	// add this inside Customer class
	// CORRECT — only cascade save/update, not delete
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinTable(
	    name = "customer_offer",                              // join table name
	    joinColumns = @JoinColumn(name = "customer_id"),      // FK to customer
	    inverseJoinColumns = @JoinColumn(name = "offer_id")   // FK to offer
	)
	private List<Offer> offers = new ArrayList<>();

	// add getter and setter
	public List<Offer> getOffers() { return offers; }
	public void setOffers(List<Offer> offers) { this.offers = offers; }
	
	
	public Customer() {}


	public Customer(String name, String email) {
		
		this.name = name;
		this.email = email;
	
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public List<Account> getAccounts() {
		return accounts;
	}


	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}


	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", email=" + email + "]";
	}
	
	
	
	
}
