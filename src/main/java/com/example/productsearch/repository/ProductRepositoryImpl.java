package com.example.productsearch.repository;

import com.example.productsearch.entity.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Custom implementation of the ProductRepository.
 */
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Finds products based on search term, minimum price, and maximum price.
     *
     * @param searchTerm the search term
     * @param minPrice the minimum price
     * @param maxPrice the maximum price
     * @return the list of products matching the search criteria
     */
    @Override
    public List<Product> findProductsBySearchTerm(String searchTerm, Double minPrice, Double maxPrice) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> product = cq.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();

        // Add predicates based on search term
        if (searchTerm != null && !searchTerm.isEmpty()) {
            String searchPattern = "%" + searchTerm.toLowerCase() + "%";
            Predicate namePredicate = cb.like(cb.lower(product.get("name")), searchPattern);
            Predicate categoryPredicate = cb.like(cb.lower(product.get("category")), searchPattern);
            Predicate brandPredicate = cb.like(cb.lower(product.get("brand")), searchPattern);
            predicates.add(cb.or(namePredicate, categoryPredicate, brandPredicate));
        }

        // Add predicate based on minimum price
        if (minPrice != null) {
            predicates.add(cb.greaterThanOrEqualTo(product.get("price"), minPrice));
        }

        // Add predicate based on maximum price
        if (maxPrice != null) {
            predicates.add(cb.lessThanOrEqualTo(product.get("price"), maxPrice));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Product> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
}