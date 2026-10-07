package gal.usc.etse.es.sigrate.model;

import gal.usc.etse.es.sigrate.model.dto.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class User {
    @Id
    @GeneratedValue
    private String id;
    private String username;
    private String email;
    @OneToMany(
            mappedBy = "users",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Review> reviews = new ArrayList<>();

    public User(String id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email= email;
    }

    public static User from(UserDTO user) {
        return new User(user.id(), user.username(), user.email());
    }
}
