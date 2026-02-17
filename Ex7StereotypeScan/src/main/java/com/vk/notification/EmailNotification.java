package com.vk.notification;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service // Spring registers this as a bean named "emailNotification"
@Primary
public class EmailNotification implements NotificationService{

	@Override
	public void send(String message) {
		System.out.println("[EMAIL] sending: "+message);
		
	}
	
}
