package com.vk.order;

import com.vk.notification.NotificationService;

public class OrderService {
	
	private NotificationService notification;
	
	// NO-Arg constructor needed for setter injection
	public OrderService() {
		System.out.println("Spring created order service");
	}
	
	//Constructor for constructor injection
	public OrderService(NotificationService notification) {
		System.out.println("Spring created OrderService with constructor injection");
		this.notification = notification;
	}
	
	// Setter for setter injection
    public void setNotification(NotificationService notification) {
        System.out.println("Spring called setNotification() — injecting dependency");
        this.notification = notification;
    }

    public void placeOrder(String item) {
        System.out.println("Order placed for: " + item);
        notification.send("Your order for " + item + " has been confirmed!");
    }
}
