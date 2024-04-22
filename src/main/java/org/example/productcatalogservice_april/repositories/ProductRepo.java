package org.example.productcatalogservice_april.repositories;

import org.example.productcatalogservice_april.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Product is what we want to store and Long is datatype of pK
@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {

    Product save(Product product);

    Optional<Product> findProductById(Long id);
}
