package com.vk.notification;

import org.springframework.stereotype.Service;

@Service
public class EmailNotification implements NotificationService {

    @Override
    public void send(String message) {
        System.out.println("[EMAIL] Sending: " + message);
    }
}