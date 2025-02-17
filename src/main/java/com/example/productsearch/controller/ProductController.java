package com.example.productsearch.controller;

import com.example.productsearch.entity.Product;
import com.example.productsearch.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing products.
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    /**
     * Constructor for ProductController.
     *
     * @param productService the product service
     */
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * GET /search : search for products based on search term, minimum price, and maximum price.
     *
     * @param searchTerm the search term
     * @param minPrice the minimum price
     * @param maxPrice the maximum price
     * @return the list of products matching the search criteria
     */
    @GetMapping("/search")
    public List<Product> searchProducts(
            @RequestParam(required = false) String searchTerm,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {
        logger.info("Received search request with searchTerm: {}, minPrice: {}, maxPrice: {}", searchTerm, minPrice, maxPrice);
        List<Product> products = productService.getProductsBySearchTerm(searchTerm, minPrice, maxPrice);
        logger.info("Returning products: {}", products);
        return products;
    }
}