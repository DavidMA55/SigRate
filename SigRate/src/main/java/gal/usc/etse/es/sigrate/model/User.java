package gal.usc.etse.es.sigrate.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
/*
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
*/
public class User {
    @Id
    @GeneratedValue
    private String id;
    private String username;
    private String email;
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Review> reviews = new ArrayList<>();
}
