package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private NotificationService notification;

    public void placeOrder(String item) {
        System.out.println("Order placed for: " + item);
        notification.send("Your order for " + item + " has been confirmed!");
    }
}