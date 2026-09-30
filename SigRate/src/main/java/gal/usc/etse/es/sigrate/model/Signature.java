package gal.usc.etse.es.sigrate.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table
public class Signature {
    @Id
    private String id;
    private int year;
    @ManyToOne(fetch = FetchType.LAZY)
    private Degree degree;
    @OneToMany(
            mappedBy = "signature",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )    private List<Review> reviews;
}
