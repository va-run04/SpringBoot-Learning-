package com.springrest.service;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "request" , proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestService {
	
	public RequestService() {
		System.out.println("Request Service bean is created");
	}
	
	public String getMessage() {
		return "Request — instance: " + this.hashCode();
	}

}
