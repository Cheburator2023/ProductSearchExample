package com.example.productsearch.service;

import com.example.productsearch.entity.Product;
import com.example.productsearch.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProductsBySearchTerm(String searchTerm, Double minPrice, Double maxPrice) {
        return productRepository.findProductsBySearchTerm(searchTerm, minPrice, maxPrice);
    }
}