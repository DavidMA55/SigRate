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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Signature> getSignatures() {
        return signatures;
    }

    public void setSignatures(List<Signature> signatures) {
        this.signatures = signatures;
    }
}