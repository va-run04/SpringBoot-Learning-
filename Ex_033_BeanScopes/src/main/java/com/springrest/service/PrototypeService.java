package com.springrest.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class PrototypeService {
	
	public PrototypeService() {
		System.out.println("Prototype service bean is created");
	}
	
	public String getMessage() {
        return "Prototype — instance: " + this.hashCode();
    }

}
