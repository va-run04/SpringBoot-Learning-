package com.vk.notification;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service   // bean name = "emailNotification"
@Primary   // default choice when multiple beans match
public class EmailNotification implements NotificationService {

    @Override
    public void send(String message) {
        System.out.println("[EMAIL] Sending: " + message);
    }
}
