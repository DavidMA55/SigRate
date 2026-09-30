package gal.usc.etse.es.sigrate.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
