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
    private int degree;
    @OneToMany
    private List<Review> reviews;
}
