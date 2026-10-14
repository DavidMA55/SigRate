package gal.usc.etse.es.sigrate.model;

import gal.usc.etse.es.sigrate.model.dto.SignatureDTO;
import gal.usc.etse.es.sigrate.model.dto.UserDTO;
import jakarta.persistence.*;
import lombok.*;


import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Signature {
    @Id
    @NonNull
    private String id;
    private int year;
    @ManyToOne(fetch = FetchType.LAZY)
    private Degree degree;
    @OneToMany(
            mappedBy = "signature",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Review> reviews = new ArrayList<>();

    public Signature(String id, int year, int degreeId) {
        this.id = id;
        this.year = year;
        this.degree = new Degree(degreeId);
    }

    public static Signature from(SignatureDTO signature) {
        return new Signature(signature.id(), signature.year(), signature.degreeId());
    }
}
