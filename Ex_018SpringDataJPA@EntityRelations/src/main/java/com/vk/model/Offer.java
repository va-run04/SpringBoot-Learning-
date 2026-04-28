package com.vk.model;

import java.util.ArrayList;
import java.util.List;
import java.util.ListResourceBundle;
import java.util.function.LongFunction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "offer")
public class Offer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	private Double discount;
	
	@ManyToMany(mappedBy = "offers")   // ← this line is missing
	private List<Customer> customers = new ArrayList<>();
	
	public Offer() {
		
	}
	
	public Offer(String title, double discount) {
		this.title = title;
		this.discount = discount;
	}
	
	public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public double getDiscount() { return discount; }
    public void setDiscount(double discount) { this.discount = discount; }

    public List<Customer> getCustomers() { return customers; }
    public void setCustomers(List<Customer> customers) { this.customers = customers; }

    @Override
    public String toString() {
        return "Offer[id=" + id + ", title=" + title + ", discount=" + discount + "]";
        // don't include customers — circular reference
    }
}
