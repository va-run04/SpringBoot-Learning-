package com.vk.notification;

public class SmsNotification implements NotificationService{

	@Override
	public void send(String message) {
		System.out.println("[SMS] Sending: "+message);
	}

}
