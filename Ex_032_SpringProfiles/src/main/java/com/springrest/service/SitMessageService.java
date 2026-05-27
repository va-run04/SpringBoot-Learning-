package com.springrest.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("sit")
public class SitMessageService implements IMessageService{
	
	
	public SitMessageService() {
		System.out.println("Sit message service bean created");
	}

	@Override
	public String getMessage() {
		
		return "Hello from Sit Environment";
	}

}
