package com.loose;

public class WhatsAppNotification implements NotificationService{

	@Override
	public void send(String message) {
		System.out.println("[WHATSAPP] Sending: "+message);
	}
	
}
