package gal.usc.etse.es.sigrate.model;

import jakarta.persistence.*;


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
    )
    private List<Review> reviews;

    public String getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public Degree getDegree() {
        return degree;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
