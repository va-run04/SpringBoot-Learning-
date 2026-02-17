
package com.vk.app;

import com.vk.service.ReportService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.vk")
public class LaunchApp {
    public static void main(String[] args) {
        System.out.println("= STARTING SPRING CONTAINER =");
        System.out.println();

        AnnotationConfigApplicationContext ctx =
            new AnnotationConfigApplicationContext(LaunchApp.class);

        System.out.println();
        System.out.println("= BEAN IS READY — CALLING BUSINESS METHOD =");
        System.out.println();

        ReportService report = ctx.getBean(ReportService.class);
        report.generateReport();

        System.out.println();
        System.out.println("= SHUTTING DOWN CONTAINER =");
        System.out.println();

        // MUST call close() for @PreDestroy to fire in console apps!
        // In web apps, the server handles shutdown automatically.
        ctx.close();

        System.out.println();
        System.out.println("=== CONTAINER CLOSED ===");
    }
}