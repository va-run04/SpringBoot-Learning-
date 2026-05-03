package com.vk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import com.vk.service.EmployeeService;

@SpringBootApplication
@EnableJpaAuditing
public class Ex021SpringDataJpaAuditing {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
            SpringApplication.run(Ex021SpringDataJpaAuditing.class, args);

        EmployeeService service = context.getBean(EmployeeService.class);

        
    }
}