package org.example.productcatalogservice_april.TableInheritanceExamples.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name="st_instructor")
@DiscriminatorValue(value = "3")
public class Instructor extends User {
    private String company;
}
