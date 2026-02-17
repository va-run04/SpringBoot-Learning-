package com.vk.notification;

public class EmailNotification implements NotificationService{
	
	
	public EmailNotification() {
		System.out.println("Spring created EmailNotification");
	}

	@Override
	public void send(String message) {
		System.out.println("[EMAIL] Sending: "+message);
		
	}

}
