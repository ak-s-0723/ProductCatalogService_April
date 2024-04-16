package org.example.productcatalogservice_april.controllers;

import org.example.productcatalogservice_april.dtos.ProductDto;
import org.example.productcatalogservice_april.models.Product;
import org.example.productcatalogservice_april.services.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long productId) {
            Product product = productService.getProduct(productId);
            return new ResponseEntity<>(product,HttpStatus.OK);
    }


    @PostMapping
    public Product createProduct(@RequestBody ProductDto productDto) {
        return productService.createProduct(productDto);
    }

    @PutMapping("{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return this.productService.replaceProduct(id,productDto);
    }
}

