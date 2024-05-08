package org.example.productcatalogservice_april.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.productcatalogservice_april.dtos.ProductDto;
import org.example.productcatalogservice_april.models.Product;
import org.example.productcatalogservice_april.services.IProductService;
import org.junit.jupiter.api.Test;
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
public class ProductControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void Test_GetAllProducts_RunsSuccessfully() throws Exception {
        List<Product> productList = new ArrayList<>();
        Product product = new Product();
        product.setName("Iphone15");
        productList.add(product);
        Product product2 = new Product();
        product2.setName("Iphone13");
        productList.add(product2);

        when(productService.getAllProducts()).thenReturn(productList);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(objectMapper.writeValueAsString(productList)))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Iphone15"))
                .andExpect(jsonPath("$[1].name").value("Iphone13"));
                        //.string("[]"));
                //object -> json -> string
    }

    @Test
    public void Test_CreateProduct_ProductCreatedSuccessfully() throws Exception {
        Product expectedProduct = new Product();
        expectedProduct.setName("MacBook");
        expectedProduct.setId(2L);

        ProductDto productToCreate = new ProductDto();
        productToCreate.setName("MacBook");
        productToCreate.setId(2L);

        when(productService.createProduct(any(Product.class)))
                .thenReturn(expectedProduct);

        mockMvc.perform(post("/products").contentType(MediaType.APPLICATION_JSON).content(
                objectMapper.writeValueAsString(productToCreate)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(expectedProduct)))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.name").value("MacBook"));

    }
}

//{
//    "name" : "MacBook",
//        "id" : 2
//}
