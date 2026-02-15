package com.loose;

public class EmailNotification implements NotificationService{

	@Override
	public void send(String message) {
		System.out.println("[EMAIL] Sending: "+message);
	}
	
}
