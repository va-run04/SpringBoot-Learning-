package com.vk.app;

import com.vk.order.OrderServiceField;
import com.vk.order.OrderServiceConstructor;
import com.vk.order.OrderServiceSetter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LaunchApp {
    public static void main(String[] args) {
        ApplicationContext ctx =
            new ClassPathXmlApplicationContext("applicationconfig.xml");

        System.out.println("=== FIELD INJECTION ===");
        // Gets @Primary bean (EmailNotification)
        OrderServiceField field = ctx.getBean(OrderServiceField.class);
        field.placeOrder("Spring Boot Course");

        System.out.println();

        System.out.println("=== CONSTRUCTOR INJECTION ===");
        // @Qualifier("smsNotification") overrides @Primary → gets SMS
        OrderServiceConstructor constructor = ctx.getBean(OrderServiceConstructor.class);
        constructor.placeOrder("Java Course");

        System.out.println();

        System.out.println("=== SETTER INJECTION ===");
        // @Qualifier("whatsAppNotification") overrides @Primary → gets WhatsApp
        OrderServiceSetter setter = ctx.getBean(OrderServiceSetter.class);
        setter.placeOrder("React Course");

        // PRECEDENCE:
        // 1st: @Qualifier   → always wins (picks exact bean by name)
        // 2nd: @Primary     → wins when no @Qualifier is present
        // 3rd: byType       → fails if multiple beans match
    }
}