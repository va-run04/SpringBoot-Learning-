package com.vk.order;

import com.vk.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component  // Spring registers this as a bean named "orderService"
public class OrderService {

    // @Autowired tells Spring: find a bean of type NotificationService
    // and inject it here automatically
    @Autowired
    private NotificationService notification;

    public void placeOrder(String item) {
        System.out.println("Order placed for: " + item);
        notification.send("Your order for " + item + " has been confirmed!");
    }
}
