package org.example.productcatalogservice_april.TableInheritanceExamples.MappedSuperclass;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;
}
