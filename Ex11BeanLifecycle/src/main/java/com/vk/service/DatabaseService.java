
package com.vk.service;

import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

    public DatabaseService() {
        System.out.println("  [DatabaseService] Constructor called");
    }

    public String getData() {
        return "Data from database";
    }
}