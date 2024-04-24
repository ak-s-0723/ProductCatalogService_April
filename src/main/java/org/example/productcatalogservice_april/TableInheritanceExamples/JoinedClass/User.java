package org.example.productcatalogservice_april.TableInheritanceExamples.JoinedClass;

import jakarta.persistence.*;

@Entity(name="jc_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;
}
