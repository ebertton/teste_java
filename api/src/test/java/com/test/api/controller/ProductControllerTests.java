package com.test.api.controller;

import com.test.api.dto.ProductDTO;
import com.test.api.model.Product;
import com.test.api.services.ProductService;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Array;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductControllerTests {

    private ProductController productController;

    @Mock
    private ProductService productService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        productController = new ProductController(productService);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));
    }

    @Test
    void testList() {
        List<ProductDTO> expectedProductDTOs = Arrays.asList(
                new ProductDTO(1L, "Product Name 1", "Description 1", 123.45, 5),
                new ProductDTO(2L, "Product Name 2", "Description 2", 678.90, 10)
        );

        when(productService.getAll()).thenReturn(expectedProductDTOs.stream()
                .map(productDTO -> new Product(productDTO.getId(), productDTO.getName(), productDTO.getDescription(), productDTO.getPrice(), productDTO.getQuantity()))
                .collect(Collectors.toList()));

        ResponseEntity<List<ProductDTO>> result = productController.list();

        assertEquals(HttpStatus.OK, result.getStatusCode());

        assertThat(result.getBody())
                .usingElementComparator(Comparator.comparing(ProductDTO::getId)
                        .thenComparing(ProductDTO::getName)
                        .thenComparing(ProductDTO::getDescription)
                        .thenComparing(ProductDTO::getPrice)
                        .thenComparing(ProductDTO::getQuantity))
                .isEqualTo(expectedProductDTOs);
    }

    @Test
    void testInsert() throws URISyntaxException {
        ProductDTO dto = new ProductDTO(1L, "Product Name", "Description", 123.45, 5);

        when(productService.save(any(ProductDTO.class)))
                .thenReturn(dto);

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        request.setRequestURI("/products/");

        ResponseEntity<ProductDTO> responseEntity = productController.insert(dto);

        assertEquals(201, responseEntity.getStatusCodeValue());
        assertEquals("http://localhost/products/1", responseEntity.getHeaders().getLocation().toString());
        verify(productService, times(1)).save(any(ProductDTO.class));
    }

    @Test
    void testUpdate() {
        ProductDTO dto = new ProductDTO(1L, "Product Name", "Description", 123.45, 5);
        // Assuming your update method will return an updated product.
        when(productService.update(any(Long.class), any(ProductDTO.class))).thenReturn(dto);

        ResponseEntity<ProductDTO> result = productController.update(1L, dto);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(dto);
    }


    @Test
    void testShow() {
        Product product = new Product(1L, "Product Name", "Description", 123.45, 5);
        when(productService.getById(any(Long.class))).thenReturn(product);
        ResponseEntity<ProductDTO> result = productController.show(1L);
        ProductDTO resultProductDTO = result.getBody();

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resultProductDTO.getId()).isEqualTo(product.getId());
        assertThat(resultProductDTO.getName()).isEqualTo(product.getName());
        assertThat(resultProductDTO.getDescription()).isEqualTo(product.getDescription());
        assertThat(resultProductDTO.getPrice()).isEqualTo(product.getPrice());
        assertThat(resultProductDTO.getQuantity()).isEqualTo(product.getQuantity());
    }

    @Test
    void testDelete() {
        doNothing().when(productService).delete(any(Long.class));
        ResponseEntity result = productController.delete(1L);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void testInsert_invalidProduct_shouldThrowConstraintViolation() {
        ProductDTO dto = new ProductDTO(1L, "Product Name", "Product Description", 99.99, 10);
        when(productService.save(any(ProductDTO.class))).thenThrow(ConstraintViolationException.class);
        try {
            ResponseEntity<ProductDTO> result = productController.insert(dto);
            assert false; // test should fail if it reaches here
        } catch (ConstraintViolationException e) {
            assert true; // test passes as exception is thrown
        }
    }
}