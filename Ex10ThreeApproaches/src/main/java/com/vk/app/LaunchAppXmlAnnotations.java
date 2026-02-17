package com.vk.app;

import com.vk.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LaunchAppXmlAnnotations {
    public static void main(String[] args) {
        // APPROACH 2: XML + Annotations
        // Annotations on classes, XML only for component-scan
        ApplicationContext ctx =
            new ClassPathXmlApplicationContext("approach2-xml-annotations.xml");

        OrderService service = ctx.getBean(OrderService.class);
        service.placeOrder("Spring Boot Course");

        System.out.println("--- Approach 2: XML + Annotations ---");
    }
}