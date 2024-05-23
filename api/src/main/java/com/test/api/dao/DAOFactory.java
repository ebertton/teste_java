package com.test.api.dao;

import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DAOFactory {
    private final EntityManager entityManager;
    public DAOFactory(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Bean
    public ProductDAO productDAO() {

        return new ProductDAOImpl(this.entityManager);
    }
}
