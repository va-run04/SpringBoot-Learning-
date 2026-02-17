package com.vk.app;

import com.vk.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LaunchApp {
    public static void main(String[] args) {
        ApplicationContext ctx =
            new ClassPathXmlApplicationContext("applicationconfig.xml");

        OrderService service = ctx.getBean(OrderService.class);
        service.placeOrder("Spring Boot Course");

        // No <bean> tags in XML!
        // Spring found @Component and @Service annotations on classes
        // Spring saw @Autowired on the field
        // Spring saw @Primary on EmailNotification
        // Spring wired everything automatically
    }
}