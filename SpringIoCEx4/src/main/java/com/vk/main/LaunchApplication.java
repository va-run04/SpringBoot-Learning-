package com.vk.main;

import com.vk.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LaunchApplication {
    public static void main(String[] args) {

        // THE KEY SHIFT: Spring creates and wires everything
        ApplicationContext container = new ClassPathXmlApplicationContext("applicationconfig.xml");

        // You just ASK for the bean — no 'new' keyword!
        OrderService service = container.getBean(OrderService.class);
        service.placeOrder("Spring Boot Course");

        // COMPARE WITH LooseCouplingEx3:
        //
        // Before (you did everything manually in main()):
        //   OrderService service = new OrderService(new EmailNotification());
        //
        // Now (Spring does it for you):
        //   OrderService service = container.getBean(OrderService.class);
        //
        // You never wrote 'new OrderService()' or 'new EmailNotification()'
        // Spring read the XML, created both objects, and wired them together.
        //
        // Want SMS instead of Email?
        // → Change ref="email" to ref="sms" in XML. That's it.
        // → No Java code changes. Not one line.
    }
}