package com.vk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.vk.model.Employee;
import com.vk.service.EmployeeService;

@SpringBootApplication
public class Ex015SpringDataJpaApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =  SpringApplication.run(Ex015SpringDataJpaApplication.class, args);
		EmployeeService service = context.getBean(EmployeeService.class);
		
	

		// INSERT
		System.out.println("--- Adding Employees ---");
		service.addEmployee(new Employee("Varun", "Hyderabad"));
		service.addEmployee(new Employee("Kiran", "Banglore"));
		service.addEmployee(new Employee("Manoj", "Hyderabad"));
		service.addEmployee(new Employee("Harsha", "Delhi"));

		// FIND BY CITY
		System.out.println("\n--- Employees in Hyderabad ---");
		service.findByCity("Hyderabad").forEach(System.out::println);

		// FIND BY NAME
		System.out.println("\n--- Employees named Kiran ---");
		service.findByName("Kiran").forEach(System.out::println);

		// FIND BY CITY AND NAME
		System.out.println("\n--- Employee named Varun in Hyderabad ---");
		service.findByCityAndName("Varun", "Hyderabad").forEach(System.out::println);

		// FIND BY CITY OR NAME
		System.out.println("\n--- Employees in Delhi OR named Manoj ---");
		service.findByCityOrName("Manoj", "Delhi").forEach(System.out::println);

		// EXISTS BY NAME
		System.out.println("\n--- Does Varun exist? ---");
		System.out.println(service.existsByName("Varun"));

		System.out.println("\n--- Does Raj exist? ---");
		System.out.println(service.existsByName("Raj"));

		// COUNT BY CITY
		System.out.println("\n--- Count of employees in Hyderabad ---");
		System.out.println(service.countByCity("Hyderabad"));

		// DELETE BY CITY
		System.out.println("\n--- Deleting all employees in Delhi ---");
		service.deleteByCity("Delhi");

		// FINAL LIST
		System.out.println("\n--- All Employees After Delete ---");
		service.getAllEmployees().forEach(System.out::println);
				
				
				
		
	}
}
