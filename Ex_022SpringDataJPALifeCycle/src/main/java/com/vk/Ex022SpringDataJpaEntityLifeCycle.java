package com.vk;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.vk.model.BankAccount;
import com.vk.service.BankAccountService;

@SpringBootApplication
public class Ex022SpringDataJpaEntityLifeCycle {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
            SpringApplication.run(Ex022SpringDataJpaEntityLifeCycle.class, args);

        BankAccountService service = context.getBean(BankAccountService.class);

        // CREATE — triggers @PrePersist, @PostPersist
        System.out.println("---- CREATE ACCOUNTS ---");
        BankAccount a1 = service.createAccount(
            new BankAccount("Varun", "SAVINGS", 10000.0));
        BankAccount a2 = service.createAccount(
            new BankAccount("Kiran", "CURRENT", 5000.0));

        System.out.println("\n" + a1);
        System.out.println(a2);

        // LOAD — triggers @PostLoad
        System.out.println("--- LOAD ALL ACCOUNTS ---");
        service.getAllAccounts().forEach(System.out::println);

        // UPDATE — deposit triggers @PreUpdate, @PostUpdate
        System.out.println("--- DEPOSIT 5000 TO VARUN ---");
        BankAccount deposited = service.deposit(a1.getId(), 5000.0);
        System.out.println(deposited);

        // UPDATE — close account triggers @PreUpdate, @PostUpdate
        System.out.println("--- CLOSE KIRAN'S ACCOUNT ---");
        BankAccount closed = service.closeAccount(a2.getId());
        System.out.println(closed);

        // DELETE — triggers @PreRemove, @PostRemove
        System.out.println("--- DELETE KIRAN'S ACCOUNT ---");
        service.deleteAccount(a2.getId());

        // verify
        System.out.println("--- ALL ACCOUNTS AFTER DELETE ---");
        service.getAllAccounts().forEach(System.out::println);
    }
}