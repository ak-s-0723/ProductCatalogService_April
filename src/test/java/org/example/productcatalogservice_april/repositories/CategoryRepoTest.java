package org.example.productcatalogservice_april.repositories;

import jakarta.transaction.Transactional;
import org.example.productcatalogservice_april.models.Category;
import org.example.productcatalogservice_april.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

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


//@Fetch(FetchMode.mode)
//    LAZY             SELECT         Asked For Products - Queries =  2 select queries
//                                    Not Asked For Products - Queries = 1 select query
//
//
//    EAGER            SELECT         Asked For Products - Queries =   2 select queries
//                                    Not Asked For Products - Queries = 2 select queries
//
//
//
//    LAZY             JOIN          Asked For Products - Queries  = 1 join query
//                                   Not Asked For Products - Queries = 1 join query
//
//
//
//    EAGER            JOIN         Asked For Products - Queries = 1 join query
//                                  Not Asked For Products - Queries = 1 join query
//
//
//
//    LAZY             SUBSELECT      Asked For Products - Queries  = 2 select queries
//                                    Not Asked For Products - Queries = 1 select query
//
//
//
//    EAGER            SUBSELECT      Asked For Products - Queries =  2 select queries
//                                    Not Asked For Products - Queries   = 2 select queries

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




    //No difference will come even if we use FetchType.Eager.
   //We will demonstrate using FetchType.Lazy and FetchType.Select
   //Solution of this N+1 Problem is to use FetchType.Subselect
    // With Subselect I will just see one query and one sub-query

    //Other Solution is @BatchSize(size=n) with FetchType.Select

    //Using fetchType.LAZY and fetchMode.Select
    //In this case, using select we will see 5 queries in total (1+4),
    //So to avoid this, one solution is using @batchsize or using mode.subselect
    //in subselect, we will only see 2 queries instead of 5
    //in case of batchsize=2>4, we will see 4/2=2 + 1 = 3 queries
    //in case of batchsize=6>4, we will see 2 queries only
    //run this testcase with select , subselect , select+batchsize to show difference

    //https://hackernoon.com/3-ways-to-deal-with-hibernate-n1-problem
    //https://dheerajgopinath.medium.com/the-issue-with-fetchmode-subselect-and-onetomany-mappings-in-hibernate-and-jpa-f79724068897
    //https://itecnote.com/tecnote/hibernate-subselect-vs-batch-fetching/
    //https://www.baeldung.com/hibernate-fetchmode
    @Test
    @Transactional
    void testNPlus1Problem() {
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
        //@Rollback(value = false)
    void testcaseForShowingHowJPAGeneratesQueries() {
        //-------------USE OF OPTIONAL OR NOT--------------------
        //Optional<Product> product = productRepo.findProductById(1L);
        Optional<Product> product = productRepo.findProductById(2L);
        if(product.isPresent()) {
            System.out.println(product.get().getName());
        }

        //----------BETWEEN CLAUSE-----------
        //List<Product> products = productRepo.findProductByPriceBetween(250,2500);

        //List<Product> products = productRepo.findByPrice(1234D);

        //------------BOOLEAN VARIABLES--------------
        //List<Product> products = productRepo.findAllByIsSpecialFalse();
        //List<Product> products = productRepo.findAllByIsSpecial(false);

        //----------------ORDERBY CLAUSE--------------
        //List<Product> products = productRepo.findAllOrderByIdDesc(); -- will error out
        //List<Product> products = productRepo.findAllByOrderByIdDesc();
        //List<Product> products = productRepo.findAllByOrderByPriceDesc();
        //List<Product> products = productRepo.findByIdIsNotNullOrderByPrice();

        //----------------Test CUSTOM Query----------------
        //String name = productRepo.getProductNameFromId(2L);
        //String name = productRepo.getCategoryNameFromProductId(2L);
        System.out.println("debug");

    }
}