package org.example.productcatalogservice_april.controllers;

import org.example.productcatalogservice_april.dtos.ProductDto;
import org.example.productcatalogservice_april.models.Product;
import org.example.productcatalogservice_april.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductControllerFlowTest {

    @Autowired
    private ProductController productController;


    @Test
    public void Test_Create_Replace_GetProduct_WithStub_RunsSuccessfully() {
        //Arrange
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName("Iphone 14");
        productDto.setDescription("Fanciest Iphone ever");


        //Act
        Product product = productController.createProduct(productDto);
        ResponseEntity<Product> productResponseEntity = productController
                             .getProduct(product.getId());

        productDto.setName("Iphone 15");
        Product replacedProduct = productController
                .replaceProduct(product.getId(),productDto);

        ResponseEntity<Product> productResponseEntity2 = productController
                .getProduct(replacedProduct.getId());


        //Assert
        assertEquals("Iphone 14",productResponseEntity.getBody().getName());
        assertEquals("Iphone 15",productResponseEntity2.getBody().getName());
    }

}
