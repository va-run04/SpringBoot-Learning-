package com.vk.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vk.model.Customer;
import com.vk.model.Offer;
import com.vk.repository.CustomerRepository;


@Service
public class CustomerServiceImple implements CustomerService{

	
	@Autowired
	private CustomerRepository repo;
	@Override
	public void addCustomer(Customer customer) {
		repo.save(customer);
		
	}

	@Override
	public Customer getCustomer(Long id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Customer not found: "+id));
	}

	@Override
	public List<Customer> getAllCustomer() {
		return repo.findAll();
	}

	@Override
	public void deleteCustomer(Long Id) {
		repo.deleteById(Id);
		
	}
	
	@Override
	@Transactional
	public List<Offer> getCustomerOffers(Long customerId) {
	    Customer customer = getCustomer(customerId);
	    // wrap in new ArrayList to force initialization while session is still open
	    return new ArrayList<>(customer.getOffers());
	}

	@Override
	@Transactional
	public void assignOffer(Long customerId, Offer offer) {
	    Customer customer = getCustomer(customerId);
	    customer.getOffers().add(offer);
	    repo.save(customer);
	}
}
