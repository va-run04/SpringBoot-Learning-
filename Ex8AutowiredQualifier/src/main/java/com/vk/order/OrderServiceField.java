package com.vk.order;

import com.vk.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceField {

    // FIELD INJECTION — @Autowired directly on the field
    // Shortest code, but NOT recommended
    // Why? Can't make field final, hides dependencies, hard to test
    @Autowired
    private NotificationService notification;
    // Gets @Primary bean (EmailNotification)

    public void placeOrder(String item) {
        System.out.println("[Field Injection]");
        System.out.println("Order placed for: " + item);
        notification.send("Your order for " + item + " has been confirmed!");
    }
}
