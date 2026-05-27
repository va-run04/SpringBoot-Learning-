package com.springrest.service;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionService {
	
	public SessionService() {
		System.out.println("Session service bean is created");
	}
	
	public String getMessage() {
		return "Session — instance: " + this.hashCode();
	}
}
