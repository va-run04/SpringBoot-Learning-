package com.vk.order;

import com.vk.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderService {

    private NotificationService notification;

    // No-arg constructor (needed for Approach 1: pure XML setter injection)
    public OrderService() {}

    // Constructor injection (used by Approach 2, 3, 4)
    @Autowired
    public OrderService(NotificationService notification) {
        this.notification = notification;
    }

    // Setter (used by Approach 1: pure XML)
    public void setNotification(NotificationService notification) {
        this.notification = notification;
    }

    public void placeOrder(String item) {
        System.out.println("Order placed for: " + item);
        notification.send("Your order for " + item + " has been confirmed!");
    }
}