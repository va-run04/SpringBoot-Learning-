
package com.vk.notification;

import org.springframework.stereotype.Service;

@Service   // bean name = "smsNotification"
public class SmsNotification implements NotificationService {

    @Override
    public void send(String message) {
        System.out.println("[SMS] Sending: " + message);
    }
}