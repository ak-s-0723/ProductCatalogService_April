package org.example.productcatalogservice_april.controllers;

import org.example.productcatalogservice_april.dtos.ProductDto;
import org.example.productcatalogservice_april.models.Product;
import org.example.productcatalogservice_april.services.IProductService;
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
        return null;
    }

    @GetMapping("{id}")
    public Product getProduct(@PathVariable("id") Long productId) {
      return productService.getProduct(productId);
    }


    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return productDto;
    }
}

