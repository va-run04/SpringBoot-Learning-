package com.vk.app;

import com.vk.greeting.GreetingService;
import com.vk.security.Password;
import java.time.LocalTime;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LaunchApp {
    public static void main(String[] args) {
        ApplicationContext ctx =
            new ClassPathXmlApplicationContext("applicationconfig.xml");

        // @Service class — Spring discovered via component scanning
        GreetingService gs = ctx.getBean(GreetingService.class);
        System.out.println(gs.greet());

        // @Bean — created by the method in AppConfig
        Password pwd = ctx.getBean(Password.class);
        System.out.println("Password algorithm: " + pwd.getAlgorithm());

        // @Bean — created by the method in AppConfig
        LocalTime time = ctx.getBean(LocalTime.class);
        System.out.println("Current time: " + time);

        // @Component/@Service = your classes (Spring discovers via scanning)
        // @Bean = third-party classes or custom creation logic
        //         (you write a method that creates and returns the object)
    }
}