package com.test.api.services;

import com.test.api.dao.ProductDAO;
import com.test.api.dto.ProductDTO;
import com.test.api.model.Product;
import com.test.api.services.exceptions.DatabaseException;
import com.test.api.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private ProductDAO productDAO;

    @Autowired
    public ProductService(ProductDAO productDAO)
    {
        this.productDAO = productDAO;
    }

    @Transactional
    public ProductDTO save(ProductDTO dto) {
        Product entity = new Product();
        copyDtoToEntity(dto, entity);
        productDAO.save(entity);
        return new ProductDTO(entity);
    }

    public Product getById(Long id) {
        return productDAO.findById(id);
    }

    public List<Product> getAll() {
        return productDAO.findAll();
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        Product entity = this.productDAO.findById(id);
        if (entity == null) {
            throw new ResourceNotFoundException("Product not found for id: " + id);
        }
        copyDtoToEntity(dto, entity);
        entity = this.productDAO.update(entity);
        return new ProductDTO(entity);
    }

    public void delete(Long id) {
        if (!this.productDAO.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            this.productDAO.delete(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setQuantity(dto.getQuantity());

    }

}
