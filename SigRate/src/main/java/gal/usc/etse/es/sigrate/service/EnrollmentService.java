package gal.usc.etse.es.sigrate.service;

import gal.usc.etse.es.sigrate.model.Enrollment;
import gal.usc.etse.es.sigrate.model.Signature;
import gal.usc.etse.es.sigrate.model.User;
import gal.usc.etse.es.sigrate.model.dto.EnrollmentDTO;
import gal.usc.etse.es.sigrate.repository.EnrollmentRepository;
import gal.usc.etse.es.sigrate.repository.SignatureRepository;
import gal.usc.etse.es.sigrate.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final SignatureRepository signatureRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository, UserRepository userRepository, SignatureRepository signatureRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
        this.signatureRepository = signatureRepository;
    }

    @Transactional
    public EnrollmentDTO enroll(Long userId, EnrollmentDTO dto) {
        if (dto.signatureId() == null || dto.signatureId().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Falta el código de la asignatura");
        }
        User user = findUser(userId);
        Signature signature = signatureRepository.findById(dto.signatureId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe la asignatura " + dto.signatureId()));
        if (enrollmentRepository.existsByUserIdAndSignatureId(userId, signature.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El usuario ya está matriculado en " + signature.getId());
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setUser(user);
        enrollment.setSignature(signature);
        enrollment.setAcademicYear(dto.AcademicYear());
        return EnrollmentDTO.from(enrollmentRepository.save(enrollment));
    }

    @Transactional(readOnly = true)
    public List<EnrollmentDTO> getByUser(Long userId) {
        findUser(userId);
        return enrollmentRepository.findByUserId(userId).stream().map(EnrollmentDTO::from).toList();
    }

    @Transactional(readOnly = true)
    public EnrollmentDTO get(Long userId, String signatureId) {
        return EnrollmentDTO.from(findEnrollment(userId, signatureId));
    }

    @Transactional
    public void delete(Long userId, String signatureId) {
        enrollmentRepository.delete(findEnrollment(userId, signatureId));
    }

    private User findUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el usuario " + id));
    }

    private Enrollment findEnrollment(Long userId, String signatureId) {
        return enrollmentRepository.findByUserIdAndSignatureId(userId, signatureId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario " + userId + " no está matriculado en " + signatureId));
    }
}
