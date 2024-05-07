package org.example.productcatalogservice_april.Stubs;

import org.example.productcatalogservice_april.models.Product;
import org.example.productcatalogservice_april.services.IProductService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Primary
public class ProductServiceStub implements IProductService {

    private Map<Long,Product> hm;

    public ProductServiceStub() {
        hm = new HashMap<>();
    }

    @Override
    public List<Product> getAllProducts() {
       return (List)hm.values();
    }

    @Override
    public Product getProduct(Long productId) {
        if(hm.containsKey(productId)) {
            return hm.get(productId);
        }

        return null;
    }

    @Override
    public Product createProduct(Product product) {
       hm.put(product.getId(),product);
       return hm.get(product.getId());
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        hm.put(id,product);
        return hm.get(id);
    }
}
