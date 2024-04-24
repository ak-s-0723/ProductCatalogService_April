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

}