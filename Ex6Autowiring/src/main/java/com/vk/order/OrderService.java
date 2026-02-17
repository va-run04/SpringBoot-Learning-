package com.vk.order;

import com.vk.notification.NotificationService;

public class OrderService {
	
	private NotificationService notification;
	
	//No-arg constructor
	public OrderService() {
		
	}
	
	// Constructor (used by autowire="constructor")
	public OrderService(NotificationService notification) {
		this.notification = notification;
	}
	
	// Setter (used by autowire="byName" and autowire="byType")
    // IMPORTANT: method name is setNotification
    // so the property name is "notification"
    public void setNotification(NotificationService notification) {
        this.notification = notification;
    }

    public void placeOrder(String item) {
        System.out.println("Order placed for: " + item);
        if (notification != null) {
            notification.send("Your order for " + item + " has been confirmed!");
        } else {
            System.out.println("WARNING: No notification service injected!");
        }
    }
	
}
