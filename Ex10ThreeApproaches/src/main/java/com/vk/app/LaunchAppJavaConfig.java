package com.vk.app;

import com.vk.order.OrderService;
import com.vk.config.JavaConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class LaunchAppJavaConfig {
    public static void main(String[] args) {
        // APPROACH 3: Java Config (Zero XML)
        // AnnotationConfigApplicationContext instead of ClassPathXmlApplicationContext
        // Pass the @Configuration class instead of an XML file
        ApplicationContext ctx =
            new AnnotationConfigApplicationContext(JavaConfig.class);

        OrderService service = ctx.getBean(OrderService.class);
        service.placeOrder("Spring Boot Course");

        System.out.println("--- Approach 3: Java Config (Zero XML) ---");
    }
}