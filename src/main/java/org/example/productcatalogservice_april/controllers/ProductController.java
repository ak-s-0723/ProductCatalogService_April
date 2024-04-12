package org.example.productcatalogservice_april.controllers;

import org.example.productcatalogservice_april.dtos.ProductDto;
import org.example.productcatalogservice_april.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public List<Product> getAllProducts() {
        return null;
    }

    @GetMapping("{id}")
    public Product getProduct(@PathVariable("id") Long productId) {
       Product product = new Product();
       product.setId(productId);
       product.setName("Iphone");
       product.setPrice(100000D);
       return product;
    }


    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return productDto;
    }
}

