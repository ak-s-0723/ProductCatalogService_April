package org.example.productcatalogservice_april.services;

import org.example.productcatalogservice_april.models.Product;
import org.example.productcatalogservice_april.repositories.ProductRepo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service
public class StorageProductService implements IProductService {

    private ProductRepo productRepo;

    public StorageProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
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
