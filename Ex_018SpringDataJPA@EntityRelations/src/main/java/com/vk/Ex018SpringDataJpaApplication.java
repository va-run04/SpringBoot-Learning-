package com.vk;

import com.vk.model.Account;
import com.vk.model.Customer;
import com.vk.model.Kyc;
import com.vk.model.Offer;
import com.vk.model.Transaction;
import com.vk.service.AccountService;
import com.vk.service.CustomerService;
import com.vk.service.KycService;
import com.vk.service.OfferService;
import com.vk.service.TransactionService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Ex018SpringDataJpaApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
            SpringApplication.run(Ex018SpringDataJpaApplication.class, args);

        CustomerService Cservice = context.getBean(CustomerService.class);
        AccountService Aservice = context.getBean(AccountService.class);
        TransactionService Tservice = context.getBean(TransactionService.class);
        KycService Kservice = context.getBean(KycService.class);
        OfferService Oservice = context.getBean(OfferService.class);

        // Create Customers
        System.out.println("--- Creating Customers ---");
        Cservice.addCustomer(new Customer("Varun", "varunkumar.nanneboina@gmail.com"));
        Cservice.addCustomer(new Customer("Kiran", "kirankumarnanneboina@gmail.com"));

        // printing all customers
        System.out.println("\n--- All Customers ---");
        Cservice.getAllCustomer().forEach(System.out::println);

        // fetch customers
        Customer varun = Cservice.getCustomer(1L);
        Customer kiran = Cservice.getCustomer(2L);

        // Create KYC for each customer
        System.out.println("\n--- Creating KYC ---");
        Kservice.addKyc(new Kyc("AADHAR-1234-5678", true, varun));
        Kservice.addKyc(new Kyc("PAN-ABCDE-1234", false, kiran));

        // print KYC for specific customer
        System.out.println("\n--- Varun's KYC ---");
        System.out.println(Kservice.getKycByCustomer(1L));

        System.out.println("\n--- Kiran's KYC ---");
        System.out.println(Kservice.getKycByCustomer(2L));

        // Create accounts related to customers
        System.out.println("\n--- Creating Accounts ---");
        Aservice.addAccount(new Account(10000.00, "SAVINGS", varun));
        Aservice.addAccount(new Account(5000.00, "CURRENT", kiran));

        // print all accounts
        System.out.println("\n--- All Accounts ---");
        Aservice.getAllAccounts().forEach(System.out::println);

        // print accounts for specific customer
        System.out.println("\n--- Varun's Accounts ---");
        Aservice.getAccountsByCustomer(1L).forEach(System.out::println);

        System.out.println("\n--- Kiran's Accounts ---");
        Aservice.getAccountsByCustomer(2L).forEach(System.out::println);

        // fetch accounts for transactions
        Account varunAccount = Aservice.getAccount(1L);
        Account kiranAccount = Aservice.getAccount(2L);

        // Create transactions
        System.out.println("\n--- Creating Transactions ---");
        Tservice.addTransaction(new Transaction(2000.00, "DEBIT", varunAccount));
        Tservice.addTransaction(new Transaction(5000.00, "CREDIT", varunAccount));
        Tservice.addTransaction(new Transaction(1000.00, "DEBIT", kiranAccount));
        Tservice.addTransaction(new Transaction(3000.00, "CREDIT", kiranAccount));

        // print all transactions
        System.out.println("\n--- All Transactions ---");
        Tservice.getAllTransactions().forEach(System.out::println);

        // print transactions for specific account
        System.out.println("\n--- Varun's Account Transactions ---");
        Tservice.getTransactionsByAccount(1L).forEach(System.out::println);

        System.out.println("\n--- Kiran's Account Transactions ---");
        Tservice.getTransactionsByAccount(2L).forEach(System.out::println);

        // Create offers
        System.out.println("\n--- Creating Offers ---");
        Oservice.addOffer(new Offer("Cashback 10%", 10.0));
        Oservice.addOffer(new Offer("Zero Fee Transfer", 0.0));
        Oservice.addOffer(new Offer("Savings Bonus", 5.0));

        // print all offers
        System.out.println("\n--- All Offers ---");
        Oservice.getAllOffers().forEach(System.out::println);

        // fetch offers
        Offer cashback = Oservice.getOffer(1L);
        Offer zeroFee = Oservice.getOffer(2L);
        Offer savingsBonus = Oservice.getOffer(3L);

        // assign offers to customers
        System.out.println("\n--- Assigning Offers to Customers ---");
        Cservice.assignOffer(1L, cashback);     // Varun gets cashback
        Cservice.assignOffer(1L, zeroFee);      // Varun gets zero fee
        Cservice.assignOffer(2L, zeroFee);      // Kiran gets zero fee too
        Cservice.assignOffer(2L, savingsBonus); // Kiran gets savings bonus

        System.out.println("\n--- Varun's Offers ---");
        Cservice.getCustomerOffers(1L).forEach(System.out::println);

        System.out.println("\n--- Kiran's Offers ---");
        Cservice.getCustomerOffers(2L).forEach(System.out::println);

        // delete Kiran — cascade deletes KYC, accounts, transactions and offers
        System.out.println("\n--- Deleting Kiran (cascade deletes KYC + Account + Transactions + Offers) ---");
        Cservice.deleteCustomer(2L);

        // verify KYC deleted
        System.out.println("\n--- Kiran's KYC After Delete ---");
        System.out.println(Kservice.getKycByCustomer(2L));

        System.out.println("\n--- All Accounts After Delete ---");
        Aservice.getAllAccounts().forEach(System.out::println);

        System.out.println("\n--- All Transactions After Delete ---");
        Tservice.getAllTransactions().forEach(System.out::println);

        System.out.println("\n--- All Offers After Delete ---");
        Oservice.getAllOffers().forEach(System.out::println);
    }
}