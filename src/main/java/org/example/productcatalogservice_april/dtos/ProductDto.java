package org.example.productcatalogservice_april.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.productcatalogservice_april.models.Category;

@Getter
@Setter
public class ProductDto {
    private String name;

    private String description;

    private String imageUrl;

    private Double price;

    private Category category;
}
