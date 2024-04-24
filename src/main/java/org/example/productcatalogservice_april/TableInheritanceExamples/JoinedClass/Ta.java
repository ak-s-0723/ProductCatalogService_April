package org.example.productcatalogservice_april.TableInheritanceExamples.JoinedClass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name="jc_ta")
@PrimaryKeyJoinColumn(name="userId")
public class Ta extends User {
    private Integer numOfRatings;
}
