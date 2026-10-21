package gal.usc.etse.es.sigrate.repository;

import gal.usc.etse.es.sigrate.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findBySignatureId(String signatureId);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.signature.id = :signatureId")
    Double averageRatingBySignatureId(@Param("signatureId") String signatureId);
}
