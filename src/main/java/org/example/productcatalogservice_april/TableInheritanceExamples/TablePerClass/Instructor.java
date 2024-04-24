package org.example.productcatalogservice_april.TableInheritanceExamples.TablePerClass;

import jakarta.persistence.Entity;

@Entity(name="tpc_instructor")
public class Instructor extends User {
    private String company;
}
