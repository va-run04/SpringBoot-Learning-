package com.springrest.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")  // Default scope no need to it
public class SingletonService {
	
	public SingletonService() {
		System.out.println("SingletonService bean is created");
	}
	
	public String getMessage() {
		return "Singleton - instance: "+this.hashCode();
	}
}
