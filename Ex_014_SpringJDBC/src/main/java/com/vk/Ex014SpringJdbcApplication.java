package com.vk;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import java.util.List;                    
import com.vk.model.Employee;            
import com.vk.Dao.IEmployeeDao;

@SpringBootApplication
public class Ex014SpringJdbcApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context =
            SpringApplication.run(Ex014SpringJdbcApplication.class, args);

        IEmployeeDao dao = context.getBean("dao", IEmployeeDao.class);

        List<Employee> employees = dao.getEmployeeInfo();
        employees.forEach(System.out::println);
    }
}