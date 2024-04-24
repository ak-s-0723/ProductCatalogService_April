package org.example.productcatalogservice_april.TableInheritanceExamples.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name="st_mentor")
@DiscriminatorValue(value = "2")
public class Mentor extends User {
    private Integer numOfHours;
}
