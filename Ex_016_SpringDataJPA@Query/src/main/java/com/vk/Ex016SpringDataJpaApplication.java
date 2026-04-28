package com.vk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.vk.model.Employee;
import com.vk.service.EmployeeService;

@SpringBootApplication
public class Ex016SpringDataJpaApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =  SpringApplication.run(Ex016SpringDataJpaApplication.class, args);
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
		
		
		
		// JPQL — find by city
		System.out.println("\n--- JPQL: Employees in Hyderabad ---");
		service.getEmployeesByCity("Hyderabad").forEach(System.out::println);

		// JPQL — get only names
		System.out.println("\n--- JPQL: All Employee Names ---");
		service.getAllEmployeeNames().forEach(System.out::println);

		// JPQL — search by keyword
		System.out.println("\n--- JPQL: Search name containing 'ar' ---");
		service.searchByName("ar").forEach(System.out::println);

		// Native SQL
		System.out.println("\n--- Native SQL: Employees in Banglore ---");
		service.getEmployeesByCityNative("Banglore").forEach(System.out::println);

		// JPQL UPDATE
		System.out.println("\n--- JPQL: Update city of employee 1 ---");
		int updated = service.updateEmployeeCity(1L, "Pune");
		System.out.println("Rows updated: " + updated);

		// verify update
		System.out.println(service.getEmployee(1L));

		// JPQL DELETE
		System.out.println("\n--- JPQL: Delete all employees in Pune ---");
		int deleted = service.deleteEmployeesByCity("Pune");
		System.out.println("Rows deleted: " + deleted);

		// final list
		System.out.println("\n--- All Employees After JPQL Delete ---");
		service.getAllEmployees().forEach(System.out::println);
				
				
				
		
	}
}
