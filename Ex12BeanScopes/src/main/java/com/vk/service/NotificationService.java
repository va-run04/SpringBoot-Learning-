package com.vk.service;

import org.springframework.stereotype.Service;

@Service
// @Scope("singleton")  this is the DEFAULT, you don't need to write it
public class NotificationService {

    public NotificationService() {
        System.out.println("[Singleton] NotificationService constructor called");
    }

    public void send(String message) {
        System.out.println("[Singleton] Sending: " + message);
    }
}