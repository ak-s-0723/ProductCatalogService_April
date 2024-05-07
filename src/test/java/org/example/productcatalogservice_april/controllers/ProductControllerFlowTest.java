package org.example.productcatalogservice_april.controllers;

import org.example.productcatalogservice_april.dtos.ProductDto;
import org.example.productcatalogservice_april.models.Product;
import org.example.productcatalogservice_april.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductControllerFlowTest {

    @Autowired
    private ProductController productController;

    @Test
    public void Test_Create_Get_ReplaceProduct() {
        //Arrange
        ProductDto productDto = new ProductDto();
        productDto.setName("Iphone");
        productDto.setPrice(10000D);
        productDto.setId(1L);

        //Act
        Product product = productController.createProduct(productDto);
        ResponseEntity<Product> productResponseEntity = productController.getProduct(product.getId());
        productDto.setName("macbook");
        productDto.setPrice(20000D);
        Product replacedProduct = productController.replaceProduct(product.getId(),productDto);
        ResponseEntity<Product> productResponseEntity2 = productController.getProduct(replacedProduct.getId());

        //Assert
        assertEquals(productResponseEntity.getBody().getName(),"Iphone");
        assertEquals(productResponseEntity.getBody().getPrice(),10000D);
        assertEquals(productResponseEntity2.getBody().getName(),"macbook");
        assertEquals(productResponseEntity2.getBody().getPrice(),20000D);
    }


}
