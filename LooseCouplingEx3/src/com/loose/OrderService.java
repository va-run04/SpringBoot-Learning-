package com.loose;

public class OrderService {
	

    // LOOSE COUPLING: depends on the INTERFACE, not a specific class
    // OrderService has NO idea whether it's email, SMS, or WhatsApp
	private NotificationService notification;
	
	
	// CONSTRUCTOR INJECTION — dependency passed from outside at creation time
	public OrderService(NotificationService notification) {
		this.notification = notification;
	}
	
	 // SETTER INJECTION — can swap the notification type after creation
	public void setNotification(NotificationService notification) {
		this.notification = notification;
	}
	
	public void placeOrder(String item) {
		System.out.println("Order placed for: "+item);
		notification.send("Your order for "+item + "has been confirmed!");
	
	}
	
	    // HERE:
	    // - No 'new' keyword anywhere in this class
	    // - No mention of Email, SMS, or WhatsApp anywhere
	    // - OrderService only knows about NotificationService interface
	    // - Someone ELSE decides what notification type to use
}
