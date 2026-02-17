
package com.vk.app;

import com.vk.employee.Employee;
import com.vk.department.Department;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LaunchApp {
    public static void main(String[] args) {
        ApplicationContext ctx =
            new ClassPathXmlApplicationContext("applicationconfig.xml");

        // 1. Constructor injection with primitives (by index)
        Employee emp1 = (Employee) ctx.getBean("emp1");
        System.out.println("Constructor (index):    " + emp1);

        // 2. Setter injection with primitives + bean reference
        Employee emp2 = (Employee) ctx.getBean("emp2");
        System.out.println("Setter + bean ref:      " + emp2);

        // 3. Constructor injection (by name)
        Employee emp3 = (Employee) ctx.getBean("emp3");
        System.out.println("Constructor (name):     " + emp3);

        // 4. P-namespace (setter shortcut)
        Employee emp4 = (Employee) ctx.getBean("emp4");
        System.out.println("P-namespace:            " + emp4);

        // 5. C-namespace (constructor shortcut)
        Department dept4 = (Department) ctx.getBean("dept4");
        System.out.println("C-namespace:            " + dept4);

        // SUMMARY:
        // value="Rohan"  -> Spring passes the String "Rohan" (auto-converts to int/double too)
        // ref="dept1"    -> Spring looks up the bean with id="dept1" and passes that object
        //
        // <property>           = setter injection (verbose)
        // <constructor-arg>    = constructor injection (verbose)
        // p:name="value"       = setter injection (shortcut)
        // c:name="value"       = constructor injection (shortcut)
    }
}