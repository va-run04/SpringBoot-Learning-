package com.tight;

public class OrderService {

	// TIGHT COUPLING: OrderService directly creates EmailNotification
	
	EmailNotification notification = new EmailNotification();
	
	public void placeOrder(String item) {
		System.out.println("Order placed for: "+item);
		notification.send("Your order for "+ item + "has been confirmed!");
		
		 // PROBLEMS:
        // 1. What if Client wants SMS notifications instead of email?
        //    → You have to OPEN OrderService.java and REWRITE it.
        //
        // 3. what if you Want to add WhatsApp or Push notifications?
        //    → You have to modify OrderService EVERY TIME.
        //
        // 4.If EmailNotification's send() method signature changes then 
        //    → OrderService breaks immediately.
        //
        // OrderService should NOT decide HOW to notify.
        // It should just say "notify the customer" and let
        // someone else decide whether that's email, SMS, or WhatsApp.
	}
}
