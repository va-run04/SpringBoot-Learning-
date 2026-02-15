package main;

import com.tight.OrderService;

public class Main {
	public static void main(String[] args) {
		OrderService service = new OrderService();
		service.placeOrder("Spring Boot Course");
	}
}
