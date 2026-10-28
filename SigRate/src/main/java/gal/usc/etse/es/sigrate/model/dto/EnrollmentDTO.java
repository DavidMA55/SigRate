package gal.usc.etse.es.sigrate.model.dto;

import gal.usc.etse.es.sigrate.model.Enrollment;

public record EnrollmentDTO(Long id, Long userId, String signatureId, String AcademicYear) {
    public static EnrollmentDTO from(Enrollment e) {
        return new EnrollmentDTO(e.getId(), e.getUser().getId(), e.getSignature().getId(), e.getAcademicYear());
    }
}
