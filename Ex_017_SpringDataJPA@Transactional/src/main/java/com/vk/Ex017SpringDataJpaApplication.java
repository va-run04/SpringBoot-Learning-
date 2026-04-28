package com.vk;

import com.vk.model.Account;
import com.vk.service.AccountService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Ex017SpringDataJpaApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
            SpringApplication.run(Ex017SpringDataJpaApplication.class, args);

        AccountService service = context.getBean(AccountService.class);

        // create accounts
        System.out.println("--- Creating Accounts ---");
        service.createAccount(new Account("Varun", 10000.00));
        service.createAccount(new Account("Kiran", 2000.00));

        // initial balances
        System.out.println("\n--- Initial Balances ---");
        service.getAllAccounts().forEach(System.out::println);

        // successful transfer
        System.out.println("\n--- Transferring 3000 from Varun to Kiran ---");
        service.transfer(1L, 2L, 3000.00);

        // balances after successful transfer
        System.out.println("\n--- Balances After Transfer ---");
        service.getAllAccounts().forEach(System.out::println);

        // failed transfer — insufficient balance
        System.out.println("\n--- Attempting to transfer 50000 (will fail) ---");
        try {
            service.transfer(1L, 2L, 50000.00);
        } catch (RuntimeException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        // balances should be unchanged after failed transfer
        System.out.println("\n--- Balances After Failed Transfer ---");
        service.getAllAccounts().forEach(System.out::println);
    }
}