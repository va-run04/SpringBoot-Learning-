package com.springrest.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class ProdMessageService implements IMessageService{
	
	public ProdMessageService() {
		System.out.println("Prod message service bean is created");
	}

	@Override
	public String getMessage() {
		return "Hello from Prod Message Service";
	}
	
	
}
