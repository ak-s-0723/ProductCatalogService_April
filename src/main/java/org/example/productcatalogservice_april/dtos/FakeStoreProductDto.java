package org.example.productcatalogservice_april.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;

    private String title;

    private String description;

    private String category;

    private Double price;

    private String image;

    private FakeStoreRatingDto rating;
}
