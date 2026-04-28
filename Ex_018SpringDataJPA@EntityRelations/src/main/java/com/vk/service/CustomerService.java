package com.vk.service;

import java.util.List;


import com.vk.model.Customer;
import com.vk.model.Offer;

public interface CustomerService {
	
	void addCustomer(Customer customer);
	Customer getCustomer(Long id);
	List<Customer> getAllCustomer();
	void deleteCustomer(Long Id);
	void assignOffer(Long customerId, Offer offer);
	List<Offer> getCustomerOffers(Long customerId);
	

}
