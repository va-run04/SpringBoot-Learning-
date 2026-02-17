package com.tight;

// TIGHT COUPLING via INHERITANCE: OrderService IS-A EmailNotification
//OrderService is permanently locked to EmailNotification through 'extends'
public class OrderService extends EmailNotification {

 public void placeOrder(String item) {
     System.out.println("Order placed for: " + item);

     // send() comes from the parent class EmailNotification
     send("Your order for " + item + " has been confirmed!");
 }

 // PROBLEMS:
 //
 // 1. OrderService IS-A EmailNotification — that makes no sense!
 //    An order service is NOT a type of notification.
 //    It USES notification, it doesn't BECOME one.
 //
 // 2. What if Client wants SMS instead of email?
 //    → You can't just swap. OrderService is BOUND to EmailNotification
 //      through extends. You'd have to change the parent class itself
 //      or rewrite OrderService to extend SmsNotification.
 //
 // 3. Java allows only ONE parent class (single inheritance).
 //    → If OrderService already extends EmailNotification,
 //      it can NEVER extend anything else.
 //
 // 4. If EmailNotification adds a new method or changes send(),
 //    → OrderService is affected immediately. It inherits EVERYTHING
 //      from the parent — even things it doesn't need.
 //
 // 5. Want OrderService to send BOTH email and SMS?
 //    → Impossible with inheritance. You can't extend two classes.
}
