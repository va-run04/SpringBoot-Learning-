package com.vk.service;


import org.springframework.data.domain.Page;

import com.vk.model.Product;

public interface ProductService {
	
	void addProduct(Product product);
	Page <Product> getAllProducts(int page, int size);
	Page <Product> getAllProductsSortedByPrice(int page, int size);
	Page <Product> getAllProductsSortedByPriceDec(int page, int size);
	Page <Product> getByCategory(String category, int page, int size);
	Page <Product> searchByName(String keyword, int page, int size);
    Page <Product> getByCategoryAndName(String category, String keyword, int page, int size);
    
    
}
