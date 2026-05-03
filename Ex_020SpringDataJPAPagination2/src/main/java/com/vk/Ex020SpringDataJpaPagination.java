package com.vk;
import com.vk.repository.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.Page;
import com.vk.model.Product;
import com.vk.service.ProductService;

@SpringBootApplication
public class Ex020SpringDataJpaPagination {

    private final ProductRepository productRepository;

	Ex020SpringDataJpaPagination(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public static void main(String[] args) {
        ConfigurableApplicationContext context =
            SpringApplication.run(Ex020SpringDataJpaPagination.class, args);
        
        ProductService service = context.getBean(ProductService.class);
        
        // add products
        System.out.println("--- Adding Products ---");
        service.addProduct(new Product("iPhone 15",       "Electronics", 80000));
        service.addProduct(new Product("Samsung TV",      "Electronics", 55000));
        service.addProduct(new Product("Nike Shoes",      "Footwear",    8000));
        service.addProduct(new Product("Adidas Shoes",    "Footwear",    6000));
        service.addProduct(new Product("Levi's Jeans",    "Clothing",    3000));
        service.addProduct(new Product("HP Laptop",       "Electronics", 65000));
        service.addProduct(new Product("Puma Shoes",      "Footwear",    5000));
        service.addProduct(new Product("Allen Solly",     "Clothing",    2000));
        service.addProduct(new Product("Sony Headphones", "Electronics", 15000));
        service.addProduct(new Product("Reebok Shoes",    "Footwear",    7000));
        service.addProduct(new Product("Van Heusen",      "Clothing",    2500));
        service.addProduct(new Product("MacBook Pro",     "Electronics", 120000));
        
        
        //All Products page 0
        System.out.println("\n -- All Products Page 0(4 per page)");
        Page<Product> page0 = service.getAllProducts(0, 4);
        page0.getContent().forEach(System.out::println);
        System.out.println("Total products: " + page0.getTotalElements());
        System.out.println("Total pages:    " + page0.getTotalPages());
        
        // All products page 1
        System.out.println("\n All prodcuts page 1 --");
        service.getAllProducts(1, 4).getContent().forEach(System.out::println);
        
        //Sorted by PRICE
        System.out.println("\n Sorted by price accending");
        service.getAllProductsSortedByPrice(0, 5).getContent().forEach(System.out::println);
        
     // sorted by price descending
        System.out.println("\n--- Sorted by Price Descending ---");
        service.getAllProductsSortedByPriceDec(0, 5).getContent().forEach(System.out::println);
        
     // filter by category
        System.out.println("\n--- Electronics (3 per page) ---");
        Page<Product> electronics = service.getByCategory("Electronics", 0, 3);
        electronics.getContent().forEach(System.out::println);
        System.out.println("Total Electronics: " + electronics.getTotalElements());

        // search by keyword
        System.out.println("\n--- Search 'Shoes' ---");
        Page<Product> shoes = service.searchByName("Shoes", 0, 4);
        shoes.getContent().forEach(System.out::println);
        System.out.println("Total Shoes found: " + shoes.getTotalElements());

        // category + keyword
        System.out.println("\n--- Footwear containing 'Shoes' ---");
        Page<Product> footwearShoes = service.getByCategoryAndName("Footwear", "Shoes", 0, 3);
        footwearShoes.getContent().forEach(System.out::println);
        System.out.println("Total: " + footwearShoes.getTotalElements());

        // loop through all pages
        System.out.println("\n--- Loop Through All Electronics Pages ---");
        int page = 0;
        Page<Product> result;
        do {
            result = service.getByCategory("Electronics", page, 2);
            System.out.println("Page " + page + ":");
            result.getContent().forEach(System.out::println);
            page++;
        } while (!result.isLast());
        
    }
}