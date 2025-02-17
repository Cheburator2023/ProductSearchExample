package com.example.productsearch.service;

import com.example.productsearch.entity.Product;
import com.example.productsearch.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing products.
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * Constructor for ProductService.
     *
     * @param productRepository the product repository
     */
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Gets products based on search term, minimum price, and maximum price.
     *
     * @param searchTerm the search term
     * @param minPrice the minimum price
     * @param maxPrice the maximum price
     * @return the list of products matching the search criteria
     */
    public List<Product> getProductsBySearchTerm(String searchTerm, Double minPrice, Double maxPrice) {
        return productRepository.findProductsBySearchTerm(searchTerm, minPrice, maxPrice);
    }
}