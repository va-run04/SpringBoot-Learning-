package com.vk;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.Page;
import com.vk.model.Employee;
import com.vk.service.EmployeeService;

@SpringBootApplication
public class Ex019SpringDataJpaPagination {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
            SpringApplication.run(Ex019SpringDataJpaPagination.class, args);

        EmployeeService service = context.getBean(EmployeeService.class);

        // add 10 employees
        System.out.println("--- Adding 10 Employees ---");
        service.addEmployee(new Employee("Varun", "Hyderabad"));
        service.addEmployee(new Employee("Kiran", "Banglore"));
        service.addEmployee(new Employee("Manoj", "Hyderabad"));
        service.addEmployee(new Employee("Harsha", "Delhi"));
        service.addEmployee(new Employee("Rohit", "Mumbai"));
        service.addEmployee(new Employee("Sneha", "Hyderabad"));
        service.addEmployee(new Employee("Priya", "Chennai"));
        service.addEmployee(new Employee("Arun", "Banglore"));
        service.addEmployee(new Employee("Rahul", "Delhi"));
        service.addEmployee(new Employee("Divya", "Mumbai"));

        // page 0 — first 3
        System.out.println("\n--- Page 0 (3 per page) ---");
        Page<Employee> page0 = service.getAllEmployeesPaged(0, 3);
        page0.getContent().forEach(System.out::println);
        System.out.println("Total elements: " + page0.getTotalElements());
        System.out.println("Total pages:    " + page0.getTotalPages());
        System.out.println("Current page:   " + page0.getNumber());
        System.out.println("Is first:       " + page0.isFirst());
        System.out.println("Is last:        " + page0.isLast());
        System.out.println("Has next:       " + page0.hasNext());

        // page 1
        System.out.println("\n--- Page 1 ---");
        service.getAllEmployeesPaged(1, 3).getContent().forEach(System.out::println);

        // page 2
        System.out.println("\n--- Page 2 ---");
        service.getAllEmployeesPaged(2, 3).getContent().forEach(System.out::println);

        // page 3 — last page
        System.out.println("\n--- Page 3 ---");
        Page<Employee> page3 = service.getAllEmployeesPaged(3, 3);
        page3.getContent().forEach(System.out::println);
        System.out.println("Is last: " + page3.isLast());

        // sorted by name
        System.out.println("\n--- Page 0 Sorted by Name ---");
        service.getAllEmployeesSorted(0, 5, "name").getContent().forEach(System.out::println);

        // filter by city with pagination
        System.out.println("\n--- Hyderabad Employees ---");
        Page<Employee> hyderabad = service.getEmployeesByCity("Hyderabad", 0, 2);
        hyderabad.getContent().forEach(System.out::println);
        System.out.println("Total in Hyderabad: " + hyderabad.getTotalElements());
    }
}