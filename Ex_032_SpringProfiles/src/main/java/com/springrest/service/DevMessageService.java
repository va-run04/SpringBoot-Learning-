package com.springrest.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class DevMessageService implements IMessageService{
	
	
	public DevMessageService() {
		System.out.println("Dev message service bean created");
	}

	@Override
	public String getMessage() {
		
		return "Hello from dev environment";
	}

}
