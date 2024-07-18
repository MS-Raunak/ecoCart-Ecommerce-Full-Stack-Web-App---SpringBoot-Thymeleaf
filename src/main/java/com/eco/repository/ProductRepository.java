package com.eco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eco.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
