package gal.usc.etse.es.sigrate.repository;

import gal.usc.etse.es.sigrate.model.Signature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Repository
public interface SignatureRepository extends JpaRepository<Signature, Long> {
    Optional<Signature> findById(Long id);
    Optional<Signature> findByName(String name);

    @Query("SELECT s FROM Signature s WHERE s.name LIKE %:name%")
    List<Signature> buscarPorNombre(@Param("name") String name);
}