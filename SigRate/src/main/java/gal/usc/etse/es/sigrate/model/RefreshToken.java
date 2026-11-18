package gal.usc.etse.es.sigrate.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.beans.FeatureDescriptor;
import java.time.Instant;

@Entity
@Getter
@Setter
public class RefreshToken {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true, nullable = false)
    private String token;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    private Instant expirationDate;
    private boolean revoked;

}
