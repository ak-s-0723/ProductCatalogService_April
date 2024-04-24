package org.example.productcatalogservice_april.TableInheritanceExamples.TablePerClass;

import jakarta.persistence.Entity;

@Entity(name="tpc_mentor")
public class Mentor extends User {
    private Integer numOfHours;
}
