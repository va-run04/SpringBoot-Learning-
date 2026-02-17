package main;

import com.loose.EmailNotification;
import com.loose.OrderService;
import com.loose.SmsNotification;
import com.loose.WhatsAppNotification;

public class Main {
	public static void main(String[] args) {
		
		// == CONSTRUCTOR INJECTION ==
		//Pass EmailNotification at creation time
		
		OrderService service = new OrderService(new EmailNotification());
		service.placeOrder("Spring Boot Course");
		
		System.out.println();
		
		 // === SETTER INJECTION ===
        // Client wants SMS now? Just swap — OrderService code doesn't change!
		service.setNotification(new SmsNotification());
	    service.placeOrder("Java Course");

	    System.out.println();

	    // Client wants WhatsApp? Swap again — still no change to OrderService!
	    service.setNotification(new WhatsAppNotification());
	    service.placeOrder("React Course");

	    // COMPARE WITH EXAMPLES TightCouplingEx1 and TightCouplingEx2
	    
	    // Tight coupling:
	    //   - OrderService created EmailNotification INSIDE itself
	    //   - Want SMS? → Open OrderService.java, rewrite it
	    //   - Want WhatsApp? → Open OrderService.java again, rewrite it again
	    
	    // Loose coupling:
	    //   - OrderService depends on NotificationService INTERFACE
	    //   - Want SMS? → Just pass new SmsNotification() from here. Done.
	    //   - Want WhatsApp? → Just pass new WhatsAppNotification(). Done.
	    //   - OrderService.java was NEVER opened, NEVER changed.
	   
	    // THE REMAINING PROBLEM:
	    // Someone still has to write 'new EmailNotification()' somewhere.
	    // Right now, main() is doing this job manually.
	    // In a real app with 500 classes, you can't wire everything by hand.
	    // → Spring IoC Container will do this for you 
	}
}
