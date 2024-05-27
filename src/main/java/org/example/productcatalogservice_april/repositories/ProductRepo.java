package org.example.productcatalogservice_april.repositories;

import org.example.productcatalogservice_april.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//Product is what we want to store and Long is datatype of pK
@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {

    Product save(Product product);

    Optional<Product> findProductById(Long id);

    List<Product> findProductByPriceBetween(Double low, Double high);

   // List<Product> findProductByIsPrimeSpecial(Boolean val);
   // List<Product> findProductByIsPrimeSpecialTrue();

    //List<Product> findAllOrderByIdDesc();
    //List<Product> findAllByOrderByIdDesc();

    @Query("select p.name from Product p where p.id=:id1")
    String getProductNameFromId(@Param("id1") Long id);

    @Query("select c.name from Product p join Category c on p.category.id=c.id and p.id=?1")
    String getCategoryNameFromProductId(Long id);

    Page<Product> findByNameEqualsOrderB(String query, Pageable pageable);
}