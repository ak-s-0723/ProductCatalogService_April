package org.example.productcatalogservice_april.TableInheritanceExamples.JoinedClass;

import jakarta.persistence.Entity;

@Entity(name="jc_finance")
public class financePerson extends User{
    private double salary;
}
