package com.vk.order;

import com.vk.notification.NotificationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceConstructor {

    // Can be final — immutable! Only possible with constructor injection
    private final NotificationService notification;

    // CONSTRUCTOR INJECTION — Spring passes the dependency through constructor
    // @Autowired is OPTIONAL when there's only one constructor (Spring 4.3+)
    // @Qualifier overrides @Primary — picks "smsNotification" specifically
    public OrderServiceConstructor(@Qualifier("smsNotification") NotificationService notification) {
        this.notification = notification;
    }

    public void placeOrder(String item) {
        System.out.println("[Constructor Injection + @Qualifier]");
        System.out.println("Order placed for: " + item);
        notification.send("Your order for " + item + " has been confirmed!");
    }
}