package org.example.productcatalogservice_april.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.example.productcatalogservice_april.dtos.ProductDto;
import org.example.productcatalogservice_april.models.Product;
import org.example.productcatalogservice_april.services.IProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerMVCTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void Test_GetAllProducts_ReturnsListSuccessfully() throws Exception {
        List<Product> productList = new ArrayList<>();
        Product product = new Product();
        product.setName("Iphone12");
        productList.add(product);

        when(productService.getAllProducts()).thenReturn(productList);

//        mockMvc.perform(get("/products"))
//               .andExpect(status().isOk())
//               .andExpect(content().string("[]"));

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productList)))
                .andExpect(jsonPath("$[0].name").value("Iphone12"));
    }

    @Test
    public void Test_CreateProduct_RunsSuccessfully() throws Exception {
        //Arrange
        ProductDto productToCreate = new ProductDto();
        productToCreate.setName("Iphone");
        productToCreate.setDescription("Fastest Iphone ever");

        Product expected = new Product();
        expected.setName("Iphone");
        //expected.setDescription("Fastest IPhone ever");

        when(productService.createProduct(any(Product.class))).thenReturn(expected);

        //Act and Assert
        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productToCreate)))
                .andExpect(content().string(objectMapper.writeValueAsString(expected)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Iphone"))
                .andExpect(jsonPath("$.length()").value(1));
    }
}
