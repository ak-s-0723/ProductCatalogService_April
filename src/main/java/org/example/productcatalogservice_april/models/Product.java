package org.example.productcatalogservice_april.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.*;

@Getter
@Setter
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product extends BaseModel {
    private String name;

    private String description;

    private String imageUrl;

    private Double price;
    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

   // private boolean isPrimeSaleSpecific;
}


//JsonManagedReference is added here and JsonBackReference will be added in Category
//to make sure, when we will make call from Postman for getProduct(2), it will get all
//values of product from product table and then it goes to category table and again try
//to get value from product table , hence converting into infinite loop.
//So this managedreference and backreference helps in solving that problem
//Source - https://www.baeldung.com/jackson-annotations
