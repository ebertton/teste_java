package com.test.api.dao;

import com.test.api.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {

    private final EntityManager entityManager;

    public ProductDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Product product) {
        entityManager.persist(product);
    }

    @Override
    @Transactional
    public Product findById(Long id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public List<Product> findAll() {
        return entityManager.createQuery("from Product", Product.class).getResultList();
    }

    @Override
    @Transactional
    public Product update(Product product) {
        return entityManager.merge(product);
    }

    @Override
    public void delete(Long id) {
        Product entity = entityManager.find(Product.class, id);
        entityManager.remove(entity);
    }

    @Override
    public boolean existsById(Long id) {
        var query = entityManager.createQuery("select 1 from Product p where p.id = :id");
        query.setParameter("id", id);
        return !query.getResultList().isEmpty();
    }
}
