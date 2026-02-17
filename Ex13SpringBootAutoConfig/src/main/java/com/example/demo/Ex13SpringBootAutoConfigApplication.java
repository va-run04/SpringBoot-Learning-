package com.example.demo;

import com.example.demo.service.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
// This ONE annotation replaces THREE:
// @Configuration       → this class is a config class
// @ComponentScan       → scan this package and sub-packages
// @EnableAutoConfiguration → auto-configure beans based on classpath
public class Ex13SpringBootAutoConfigApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
            SpringApplication.run(Ex13SpringBootAutoConfigApplication.class, args);
        
     // Use our bean
        OrderService service = ctx.getBean(OrderService.class);
        service.placeOrder("Spring Boot Course");

        System.out.println();

        // How many beans did Spring Boot create?
        int count = ctx.getBeanDefinitionCount();
        System.out.println();
        System.out.println("==============================");
        System.out.println("Total beans created: " + count);
        System.out.println("==============================");
        // You'll see 50+ beans — and you only wrote a few classes!

        // Print every bean name
        System.out.println();
        System.out.println("=== ALL BEAN NAMES ===");
        String[] names = ctx.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println("  " + name);
        }
    }
}
