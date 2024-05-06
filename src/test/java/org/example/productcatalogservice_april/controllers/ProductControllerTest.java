package org.example.productcatalogservice_april.controllers;

import org.example.productcatalogservice_april.models.Product;
import org.example.productcatalogservice_april.services.IProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
    //@Autowired
    private IProductService productService;

    //Test_Method_Params_Result
    @Test
    @DisplayName("product fetched successfully  - happy path")
    public void Test_GetProduct_WithValidId_ReturnsProductSuccessfully() {

        //Arrange
        Product product = new Product();
        product.setName("Iphone");
        product.setPrice(1000D);
        when(productService.getProduct(any(Long.class))).thenReturn(product);

        //Act
        ResponseEntity<Product> productResponseEntity =
                productController.getProduct(1L);

        //Assert
        assertNotNull(productResponseEntity.getBody());
        assertEquals(1000D,productResponseEntity.getBody().getPrice());
        assertEquals("Iphone",productResponseEntity.getBody().getName());
    }

    @Test
    @DisplayName("run time exception from mocked dependency  - sad path")
    public void Test_GetProduct_ExternalDependencyThrowsException() {
       //Arrange
       when(productService.getProduct(any(Long.class)))
               .thenThrow(new RuntimeException("Something went wrong"));

       //Act and Assert
        assertThrows(RuntimeException.class,()->productController.getProduct(1L));
    }

    @Test
    @DisplayName("illegal argument exception when 0 product id was passed  - sad path")
    public void Test_GetProduct_WithInvalidId_ThrowsIllegalArgumentException() {
        //Act and Assert
        assertThrows(IllegalArgumentException.class,
                ()-> productController.getProduct(0L));

        verify(productService,times(0)).getProduct(0L);
    }
}