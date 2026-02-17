package com.example.demo.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @PostConstruct
    public void init() {
        System.out.println("NotificationService is ready!");
    }

    public void send(String message) {
        System.out.println("[EMAIL] Sending: " + message);
    }
}
