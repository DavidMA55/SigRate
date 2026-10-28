package gal.usc.etse.es.sigrate.repository;

import gal.usc.etse.es.sigrate.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    boolean existsByUserIdAndSignatureId(Long userId, String signatureId);
    Optional<Enrollment> findByUserIdAndSignatureId(Long userId, String signatureId);
    List<Enrollment> findByUserId(Long userId);
}
