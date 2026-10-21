package gal.usc.etse.es.sigrate.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue
    private Long id;
    private int rating;
    private String text;
    private LocalDateTime date;
    @ManyToOne(fetch = FetchType.LAZY)
    private Signature signature;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
