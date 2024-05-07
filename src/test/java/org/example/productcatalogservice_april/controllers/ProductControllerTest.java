package org.example.productcatalogservice_april.controllers;

import org.example.productcatalogservice_april.dtos.ProductDto;
import org.example.productcatalogservice_april.models.Product;
import org.example.productcatalogservice_april.services.IProductService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
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

    private static Product product;

    private static ProductDto productDto1,productDto2;

    @Captor
    private ArgumentCaptor<Long> idCaptor;

    @BeforeAll
    public static void setup() {
        product  = new Product();
        product.setName("macbook");
        product.setId(1L);


        productDto1 = new ProductDto();
        productDto2 = new ProductDto();
        productDto1.setName("macbook air");
        productDto2.setName("macbook pro");
    }

    //IGNORE THIS , TESTING SOMETHING OUT------
    @Test
    public void TestCreate_Get_UpdateProduct() {
        product.setName(productDto1.getName());
        when(productService.createProduct(any(Product.class))).thenReturn(product);

        when(productService.getProduct(any(Long.class))).thenReturn(product);

        when(productService.replaceProduct(any(Long.class),any(Product.class))).thenReturn(product);

        Product result = productController.createProduct(productDto1);
        ResponseEntity<Product> result2 = productController.getProduct(result.getId());

        product.setName(productDto2.getName());

        Product result3 = productController.replaceProduct(result2.getBody().getId(),productDto2);

        assertEquals(result2.getBody().getName(),"macbook air");
        assertEquals(result3.getName(),"macbook pro");
    }


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

    @Test
    public void Test_IfDownstreamInvokedWithSameParameters_HappyPath() {
        //Arrange
        Long id = 2L;

        //Act
        productController.getProduct(id);

        //Assert
        verify(productService).getProduct(idCaptor.capture());
        assertEquals(id,idCaptor.getValue());
    }
}