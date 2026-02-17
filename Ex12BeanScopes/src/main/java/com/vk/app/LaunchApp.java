package com.vk.app;

import com.vk.service.NotificationService;
import com.vk.service.OrderRequest;
import com.vk.service.HeavyReportService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.vk")
public class LaunchApp {
    public static void main(String[] args) {

        System.out.println("=== STARTING CONTAINER ===");
        System.out.println();

        AnnotationConfigApplicationContext ctx =
            new AnnotationConfigApplicationContext(LaunchApp.class);

        System.out.println();
        // Notice: NotificationService constructor printed at startup (eager)
        // HeavyReportService constructor did NOT print yet (lazy)

        // ==========================================
        // SINGLETON TEST
        // ==========================================
        System.out.println("=== SINGLETON TEST ===");
        NotificationService n1 = ctx.getBean(NotificationService.class);
        NotificationService n2 = ctx.getBean(NotificationService.class);

        System.out.println("n1 == n2? " + (n1 == n2));           // true — same object
        System.out.println("n1 hash: " + n1.hashCode());
        System.out.println("n2 hash: " + n2.hashCode());         // same hash
        System.out.println("Singleton: ONE instance, shared everywhere");

        System.out.println();

      
        // PROTOTYPE TEST
       
        System.out.println("=== PROTOTYPE TEST ===");
        OrderRequest o1 = ctx.getBean(OrderRequest.class);
        o1.setOrderId("ORD-001");

        OrderRequest o2 = ctx.getBean(OrderRequest.class);
        o2.setOrderId("ORD-002");

        System.out.println("o1 == o2? " + (o1 == o2));           // false — different objects
        System.out.println("o1 hash: " + o1.hashCode());
        System.out.println("o2 hash: " + o2.hashCode());         // different hash
        System.out.println("o1 orderId: " + o1.getOrderId());    // ORD-001
        System.out.println("o2 orderId: " + o2.getOrderId());    // ORD-002
        System.out.println("Prototype: NEW instance every time");

        System.out.println();

        // @LAZY TEST
       
        System.out.println("=== @LAZY TEST ===");
        System.out.println("Requesting HeavyReportService for the first time...");
        HeavyReportService heavy = ctx.getBean(HeavyReportService.class);
        // Constructor prints NOW — not at container startup
        heavy.generateReport();

        System.out.println();

        ctx.close();
    }
}