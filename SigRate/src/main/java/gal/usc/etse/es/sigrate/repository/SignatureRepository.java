package gal.usc.etse.es.sigrate.repository;

import gal.usc.etse.es.sigrate.model.Signature;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Repository
public interface SignatureRepository extends JpaRepository<Signature, String> {
    Optional<Signature> findById(@NonNull String id);

    @Query("SELECT s FROM Signature s WHERE s.id LIKE %:id%")
    List<Signature> buscarPorId(@Param("id") String id);
    List<Signature> findByDegreeId(int degreeId);
}