package org.example.productcatalogservice_april.services;

import org.example.productcatalogservice_april.dtos.UserDto;
import org.example.productcatalogservice_april.models.Product;
import org.example.productcatalogservice_april.repositories.ProductRepo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

//@Primary
@Service
public class StorageProductService implements IProductService {

    private ProductRepo productRepo;

    private RestTemplate restTemplate;

    public StorageProductService(ProductRepo productRepo,RestTemplate restTemplate) {
        this.productRepo = productRepo;
        this.restTemplate = restTemplate;
    }


    @Override
    public Product getProductDetails(Long productId, Long userId) {
        //RestTemplate restTemplate = new RestTemplate();
        UserDto user = restTemplate.getForEntity("http://userservice/users/{uid}", UserDto.class,userId).getBody();
        System.out.println("USER EMAIL = "+user.getEmail());
        if(user !=null) {
            Product product = productRepo.findProductById(productId).get();
            return product;
        }

        return null;
    }


    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product getProduct(Long productId) {
       Optional<Product> optionalProduct = productRepo.findProductById(productId);
       if(optionalProduct.isEmpty()) {
           return null;
       }

       return optionalProduct.get();
    }

    @Override
    public Product createProduct(Product product) {
        Product resultProduct = productRepo.save(product);
        return resultProduct;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }
}
