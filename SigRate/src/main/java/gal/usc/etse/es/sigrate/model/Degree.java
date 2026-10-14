package gal.usc.etse.es.sigrate.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
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

    public Degree(int id) {
        this.id = id;
        this.name = "A";
    }

    public Degree() {

    }

}