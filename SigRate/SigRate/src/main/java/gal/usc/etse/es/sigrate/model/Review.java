package gal.usc.etse.es.sigrate.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue
    private Long Id;
    private int rating;
    private String text;
    private Date date;
    @ManyToOne(fetch = FetchType.LAZY)
    private Signature signature;
    @ManyToOne(fetch = FetchType.LAZY)
    private User users;
}
