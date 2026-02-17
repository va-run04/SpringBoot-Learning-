package com.vk.config;

import java.time.LocalTime;
import com.vk.security.Password;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  // tells Spring: this class contains bean definitions
public class AppConfig {

    @Bean  // method name = bean name ("currentTime")
    public LocalTime currentTime() {
        // You can't put @Component on java.time.LocalTime
        // You don't own that class — it's part of the Java library
        // So you create it here with @Bean instead
        return LocalTime.now();
    }

    @Bean  // method name = bean name ("passwordEncoder")
    public Password passwordEncoder() {
        // Custom construction logic — passing "SHA" to constructor
        // Can't do this with @Component
        return new Password("SHA-256");
    }
}