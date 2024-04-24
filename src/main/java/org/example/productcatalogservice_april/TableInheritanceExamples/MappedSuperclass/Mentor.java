package org.example.productcatalogservice_april.TableInheritanceExamples.MappedSuperclass;

import jakarta.persistence.Entity;

@Entity(name="msc_mentor")
public class Mentor extends User {
    private Integer numOfHours;
}
