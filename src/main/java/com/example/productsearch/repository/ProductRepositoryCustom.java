package com.example.productsearch.repository;

import com.example.productsearch.entity.Product;

import java.util.List;

public interface ProductRepositoryCustom {
    List<Product> findProductsBySearchTerm(String searchTerm, Double minPrice, Double maxPrice);
}