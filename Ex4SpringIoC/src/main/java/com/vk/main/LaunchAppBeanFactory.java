package com.vk.main;

import com.vk.order.OrderService;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public class LaunchAppBeanFactory {
    public static void main(String[] args) {

        // BeanFactory — the basic container (lazy loading)
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions("applicationconfig.xml");

        // Beans are created NOW (when you ask), not at startup
        OrderService service = (OrderService) factory.getBean("orderService");
        service.placeOrder("Spring Boot Course");

        // BeanFactory vs ApplicationContext:
        // BeanFactory → lazy (creates beans only when you ask)
        // ApplicationContext → eager (creates ALL beans at startup)
        // ApplicationContext is used 99% of the time
    }
}