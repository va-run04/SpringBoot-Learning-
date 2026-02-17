package com.vk.app;

import com.vk.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LaunchAppPureXml {
    public static void main(String[] args) {
        // APPROACH 1: Pure XML
        // Everything defined in XML — verbose, no compile-time safety
        ApplicationContext ctx =
            new ClassPathXmlApplicationContext("approach1-pure-xml.xml");

        OrderService service = ctx.getBean(OrderService.class);
        service.placeOrder("Spring Boot Course");

        System.out.println("--- Approach 1: Pure XML ---");
    }
}