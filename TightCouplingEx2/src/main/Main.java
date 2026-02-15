package main;

import com.tight.OrderService;

public class Main {
    public static void main(String[] args) {
        OrderService service = new OrderService();
        service.placeOrder("Spring Boot Course");

        // It works — but OrderService is permanently locked to
        // EmailNotification through 'extends'.
        //
        // This is WHY you'll hear:
        // "Favor composition over inheritance"
        //
        // Composition = class HAS-A dependency (passed from outside)
        // Inheritance = class IS-A dependency (locked through extends)
       
        
    }
}
