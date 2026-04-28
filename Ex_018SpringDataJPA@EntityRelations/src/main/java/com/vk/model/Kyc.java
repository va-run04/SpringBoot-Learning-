package com.vk.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "kyc")
public class Kyc {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String documentNumber;
	
	private boolean verified;
	
	@OneToOne
	@JoinColumn(name = "customer_id", unique = true)
	private Customer customer;
	
	public Kyc() {}
	
	public Kyc(String documentNumber, boolean verified, Customer customer) {
		this.documentNumber = documentNumber;
		this.verified  = verified;
		this.customer = customer;
	}
	
	public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }

    public boolean isVerified() { return verified; }
    public void setVerified(boolean verified) { this.verified = verified; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    @Override
    public String toString() {
        return "Kyc[id=" + id + ", documentNumber=" + documentNumber +
               ", verified=" + verified + "]";
        // don't include customer — circular reference
    }
	
	
}
