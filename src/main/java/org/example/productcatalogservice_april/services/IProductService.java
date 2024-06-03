package org.example.productcatalogservice_april.services;

import org.example.productcatalogservice_april.dtos.ProductDto;
import org.example.productcatalogservice_april.models.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();

    Product getProduct(Long productId);

    Product createProduct(Product product);

    Product replaceProduct(Long id,Product product);

    Product getProductDetails(Long productId, Long userId);
}
