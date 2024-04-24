package org.example.productcatalogservice_april.TableInheritanceExamples.JoinedClass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name="jc_mentor")
@PrimaryKeyJoinColumn(name="userId")
public class Mentor extends User {
    private Integer numOfHours;
}
