package com.vk.notification;

import org.springframework.stereotype.Service;

@Service   // bean name = "whatsAppNotification"
public class WhatsAppNotification implements NotificationService {

    @Override
    public void send(String message) {
        System.out.println("[WHATSAPP] Sending: " + message);
    }
}