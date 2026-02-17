package com.vk.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    private DatabaseService databaseService;

    // STEP 1: Static block — runs when JVM loads the class (before Spring)
    static {
        System.out.println("1. Static block — class loaded by JVM");
    }

    // STEP 2: Instance initializer block — runs before constructor
    {
        System.out.println("2. Instance block — runs before constructor");
    }

    // STEP 3: Constructor — Spring creates the bean
    public ReportService() {
        System.out.println("3. Constructor — Spring is creating ReportService");
    }

    // STEP 4: Dependency Injection — Spring injects dependencies
    @Autowired
    public void setDatabaseService(DatabaseService databaseService) {
        this.databaseService = databaseService;
        System.out.println("4. Setter called — Spring injected DatabaseService");
    }

    // STEP 5: @PostConstruct — runs AFTER all dependencies are injected
    @PostConstruct
    public void init() {
        System.out.println("5. @PostConstruct — all dependencies ready, running init logic");
        System.out.println("   Loading config, validating state, opening connections...");
    }

    // STEP 6: Business method — the actual work
    public void generateReport() {
        System.out.println("6.  Business method — generating report");
        System.out.println("   Data: " + databaseService.getData());
    }

    // STEP 7: @PreDestroy — runs BEFORE the bean is destroyed
    @PreDestroy
    public void cleanup() {
        System.out.println("7. @PreDestroy — cleaning up before shutdown");
        System.out.println("   Closing connections, flushing caches, releasing resources...");
    }
}