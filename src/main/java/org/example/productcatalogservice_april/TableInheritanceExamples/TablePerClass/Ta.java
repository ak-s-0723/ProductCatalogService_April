package org.example.productcatalogservice_april.TableInheritanceExamples.TablePerClass;

import jakarta.persistence.Entity;

@Entity(name="tpc_ta")
public class Ta extends User {
    private Integer numOfRatings;
}
