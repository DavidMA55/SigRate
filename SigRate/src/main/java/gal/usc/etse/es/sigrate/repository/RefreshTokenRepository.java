package gal.usc.etse.es.sigrate.repository;

import gal.usc.etse.es.sigrate.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByTokenRevokedFalse(String token);
}
