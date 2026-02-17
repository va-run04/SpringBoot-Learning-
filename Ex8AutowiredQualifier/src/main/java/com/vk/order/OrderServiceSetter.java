package com.vk.order;

import com.vk.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceSetter {

    private NotificationService notification;

    // SETTER INJECTION — Spring calls this method after creating the object
    // Use for optional dependencies or when you need to change at runtime
    @Autowired
    @Qualifier("whatsAppNotification")  // picks WhatsApp specifically
    public void setNotification(NotificationService notification) {
        this.notification = notification;
    }

    public void placeOrder(String item) {
        System.out.println("[Setter Injection + @Qualifier]");
        System.out.println("Order placed for: " + item);
        notification.send("Your order for " + item + " has been confirmed!");
    }
}