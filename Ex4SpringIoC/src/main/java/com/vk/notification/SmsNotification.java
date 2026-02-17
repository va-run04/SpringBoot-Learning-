package com.vk.notification;

public class SmsNotification implements NotificationService{
	
	public SmsNotification() {
		System.out.println("Spring created SmsNotification");
	}

	@Override
	public void send(String message) {
		System.out.println("[SMS] Sending: "+message);
		
	}
}
