package com.eco.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.eco.model.Product;
import com.eco.repository.ProductRepository;
import com.eco.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductRepository productRepository;
	
	@Override
	public Product saveProduct(Product product) {
		Product saveProduct = productRepository.save(product);
		
		return saveProduct;
	}

}
