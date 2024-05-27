package org.example.productcatalogservice_april.controllers;

import org.example.productcatalogservice_april.dtos.ProductDto;
import org.example.productcatalogservice_april.models.Category;
import org.example.productcatalogservice_april.models.Product;
import org.example.productcatalogservice_april.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    //@Qualifier is alternative of @Primary, whatever name you mention in this @Qualifier, it
    //will get picked up
    @Autowired
    private IProductService productService;

//    public ProductController(IProductService productService) {
//        this.productService = productService;
//    }

    @GetMapping
    public List<Product> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return products;
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long productId) {
        MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        headers.add("called by","smart frontend");
        try {
            if(productId < 1) {
                headers.add("called by","pagal frontend");
                throw new IllegalArgumentException("id is invalid");
            }
            Product product = productService.getProduct(productId);
            return new ResponseEntity<>(product,headers,HttpStatus.OK);
        } catch(Exception ex) {
            //return new ResponseEntity<>(headers,HttpStatus.BAD_REQUEST);
            throw ex;
        }


//            Product product = productService.getProduct(productId);
//            return new ResponseEntity<>(product,HttpStatus.OK);

    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product createProduct(@RequestBody ProductDto productDto) {
        return productService.createProduct(getProduct(productDto));
    }

    @PutMapping("{id}")
    public Product replaceProduct(@PathVariable("id") Long id,@RequestBody ProductDto productDto) {
        return productService.replaceProduct(id,getProduct(productDto));
    }

    private Product getProduct(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        product.setDescription(productDto.getDescription());
        Category category = new Category();
        if(productDto.getCategoryDto() != null) {
            category.setId(productDto.getCategoryDto().getId());
            category.setName(productDto.getCategoryDto().getName());
        }
        product.setCategory(category);
        return product;
    }
}

