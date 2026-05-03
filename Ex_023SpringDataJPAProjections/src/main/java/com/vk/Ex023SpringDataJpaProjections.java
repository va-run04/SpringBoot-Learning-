package com.vk;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.vk.model.Employee;
import com.vk.service.EmployeeService;

@SpringBootApplication
public class Ex023SpringDataJpaProjections {
 

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
            SpringApplication.run(Ex023SpringDataJpaProjections.class, args);

        EmployeeService service = context.getBean(EmployeeService.class);

        // add employees
        System.out.println("--- Adding Employees ---");
        service.addEmployee(new Employee("Varun",  "Hyderabad", "Engineering", 75000.0));
        service.addEmployee(new Employee("Kiran",  "Banglore",  "Engineering", 80000.0));
        service.addEmployee(new Employee("Sneha",  "Hyderabad", "HR",          55000.0));
        service.addEmployee(new Employee("Harsha", "Delhi",     "Finance",     65000.0));
        service.addEmployee(new Employee("Priya",  "Chennai",   "HR",          50000.0));

        // full entity — all columns
        System.out.println("\n--- All Employees (all columns) ---");
        service.getAllEmployees().forEach(System.out::println);

        // projection — only name and city
        System.out.println("\n--- Name and City only ---");
        service.getNameAndCity().forEach(p ->
            System.out.println("Name: " + p.getName() + " | City: " + p.getCity())
        );

        // projection — only name and salary
        System.out.println("\n--- Name and Salary only ---");
        service.getNameAndSalary().forEach(p ->
            System.out.println("Name: " + p.getName() + " | Salary: " + p.getSalary())
        );

        // projection — only name and department
        System.out.println("\n--- Name and Department only ---");
        service.getNameAndDepartment().forEach(p ->
            System.out.println("Name: " + p.getName() + " | Department: " + p.getDepartment())
        );

        // projection with filter — Engineering employees name and salary
        System.out.println("\n--- Engineering Department (name + salary) ---");
        service.getByDepartment("Engineering").forEach(p ->
            System.out.println("Name: " + p.getName() + " | Salary: " + p.getSalary())
        );
    }
}