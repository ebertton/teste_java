package com.test.api.dao;

import com.test.api.model.Product;

import java.util.List;

public interface ProductDAO {
    void save(Product product);
    Product findById(Long id);
    List<Product> findAll();
    Product update(Product product);
    void delete(Long id);
    boolean existsById(Long id);
}
