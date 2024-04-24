package org.example.productcatalogservice_april.TableInheritanceExamples.MappedSuperclass;

import jakarta.persistence.Entity;

@Entity(name="msc_ta")
public class Ta extends User {
    private Integer numOfRatings;
}
