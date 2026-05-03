package com.vk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vk.model.Product;
import com.vk.repository.ProductRepository;


@Service
public class ProductServiceImple implements ProductService{
	

	private final ProductRepository repository;
	
	@Autowired
	public ProductServiceImple(ProductRepository repository) {
		// TODO Auto-generated constructor stub
		this.repository = repository;
	}

	@Override
	public void addProduct(Product product) {
		repository.save(product);
	}

	@Override
	public Page<Product> getAllProducts(int page, int size) {
		return repository.findAll(PageRequest.of(page, size));
	}

	@Override
	public Page<Product> getAllProductsSortedByPrice(int page, int size) {
		return repository.findAll(PageRequest.of(page, size, Sort.by("price")));
	}

	@Override
	public Page<Product> getAllProductsSortedByPriceDec(int page, int size) {
		return repository.findAll(PageRequest.of(page, size, Sort.by("price").descending()));
	}

	@Override
	public Page<Product> getByCategory(String category, int page, int size) {
		return repository.findByCategory(category, PageRequest.of(page, size));
	}

	@Override
	public Page<Product> searchByName(String keyword, int page, int size) {
		
		return repository.findByNameContaining(keyword, PageRequest.of(page, size));
	}

	@Override
	public Page<Product> getByCategoryAndName(String category, String keyword, int page, int size) {
		return repository.findByCategoryAndNameContaining(category, keyword, PageRequest.of(page, size));
	}

}
