package com.vk.greeting;

import java.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service  // you own this class → use @Component/@Service
public class GreetingService {

    // LocalTime bean will be injected from the @Bean method in Config
    @Autowired
    private LocalTime time;

    public String greet() {
        int hour = time.getHour();
        if (hour < 12) return "Good Morning!";
        if (hour < 17) return "Good Afternoon!";
        return "Good Evening!";
    }
}