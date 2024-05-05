package org.example.productcatalogservice_april.controllers;

import org.example.productcatalogservice_april.models.Product;
import org.example.productcatalogservice_april.services.IProductService;
import org.junit.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private IProductService productService;


    @Test
    @DisplayName("get product successfully")
    void Test_GetProductById_ReturnsProductSuccessfully() {
        //Arrange
        Product product = new Product();
        product.setName("Iphone");
        product.setPrice(1000D);
        //when(productService.getProduct(any(Long.class))).thenReturn(new Product());
        when(productService.getProduct(any(Long.class))).thenReturn(product);

        //Act
        ResponseEntity<Product> productResponseEntity = productController.getProduct(1L);

        //Assert
        assertEquals("Iphone",productResponseEntity.getBody().getName());
        verify(productService,times(1)).getProduct(1L);
    }

    @Test
    @DisplayName("dependency exception while running")
    void Test_GetProductById_InternalDependencyException() {
     //Arrange
        when(productService.getProduct(any(Long.class))).thenThrow(new RuntimeException("Something went very wrong"));

     //Act and Assert
        assertThrows(RuntimeException.class,()-> productController.getProduct(1L));
    }

    @Test
    @DisplayName("id 0 leads to Illegal argument exception")
    void Test_GetProductById_InvalidId_ThrowsIllegalArgumentException() {
        //Act and Assert

        //assertThrows(IllegalArgumentException.class,()->productController.getProduct(0L));
        Exception ex = assertThrows(IllegalArgumentException.class,()->productController.getProduct(0L));

        assertEquals("id is invalid",ex.getMessage());
        verify(productService,times(0)).getProduct(0L);
    }
}