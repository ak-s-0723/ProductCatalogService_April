package org.example.productcatalogservice_april.repositories;

import jakarta.transaction.Transactional;
import org.example.productcatalogservice_april.models.Category;
import org.example.productcatalogservice_april.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CategoryRepoTest {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ProductRepo productRepo;


    @Test
    @Transactional
   //@Rollback(value=false)
    void testLoading() {
        Category c = categoryRepo.findById(2L).get();
        System.out.println(c.getName());
        List<Product> p = c.getProducts();
        for(Product product : p) {
            System.out.println(product.getName());
        }
    }


    @Test
    @Transactional
    void testFetchModesAndTypes() {
        Category c = categoryRepo.findById(2L).get();
        System.out.println(c.getName());
        List<Product> p = c.getProducts();
        for(Product product : p) {
            System.out.println(product.getName());
        }
    }

    //1 for cat + 10 for products - N+1
    //batch size is better than subselect
    @Test
    @Transactional
    void testGetAllCats() {
        List<Category> cats = categoryRepo.findAll();
        for(Category cat : cats) {
            List<Product> products = cat.getProducts();
            if(!products.isEmpty()) {
                System.out.println(products.get(0).getName());
            }
        }
    }

    @Test
    @Transactional
    void testQueries() {
        //List<Product> products = productRepo.findProductByPriceBetween(1000D,100012D);
        List<Product> products = productRepo.findAllByOrderByIdDesc();
        System.out.println("debug");

    }





}