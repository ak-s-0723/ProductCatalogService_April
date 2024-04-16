package org.example.productcatalogservice_april.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.productcatalogservice_april.models.Category;

@Getter
@Setter
public class ProductDto {
    private Long id;

    private String title;

    private String description;

    private String image;

    private Double price;

    private String category;
}
