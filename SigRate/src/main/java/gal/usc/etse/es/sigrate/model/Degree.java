package gal.usc.etse.es.sigrate.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Degree {
    @Id
    private int id;
    private String name;

    @OneToMany(
            mappedBy = "degree",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Signature> signatures;
}
