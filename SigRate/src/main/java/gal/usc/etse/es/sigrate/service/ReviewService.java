package gal.usc.etse.es.sigrate.service;

import gal.usc.etse.es.sigrate.model.Review;
import gal.usc.etse.es.sigrate.model.Signature;
import gal.usc.etse.es.sigrate.model.User;
import gal.usc.etse.es.sigrate.model.dto.ReviewDTO;
import gal.usc.etse.es.sigrate.repository.EnrollmentRepository;
import gal.usc.etse.es.sigrate.repository.ReviewRepository;
import gal.usc.etse.es.sigrate.repository.SignatureRepository;
import gal.usc.etse.es.sigrate.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final SignatureRepository signatureRepository;
    private final UserRepository userRepository;
    private final EnrollmentRepository enrollmentRepository;

    public ReviewService(ReviewRepository reviewRepository,
                         SignatureRepository signatureRepository,
                         UserRepository userRepository,
                         EnrollmentRepository enrollmentRepository) {
        this.reviewRepository = reviewRepository;
        this.signatureRepository = signatureRepository;
        this.userRepository = userRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    @Transactional
    public ReviewDTO create(String signatureId, ReviewDTO dto){
        if (dto.rating() < 1 || dto.rating() > 5) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "La nota debe estar entre 1 y 5");
        }
        if (dto.userId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Falta el id del usuario");

        }
        Signature signature = findSignature(signatureId);
        User user = userRepository.findById(dto.userId())
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No existe el usuario " + dto.userId()));

        if (!enrollmentRepository.existsByUserIdAndSignatureId(user.getId(), signature.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Solo puedes reseñas asignaturas que hayas cursado");
        }

        Review review = new Review();
        review.setSignature(signature);
        review.setUser(user);
        review.setRating(dto.rating());
        review.setText(dto.text());
        review.setDate(LocalDateTime.now());

        return ReviewDTO.from(reviewRepository.save(review));
    }

    @Transactional(readOnly = true)
    public List<ReviewDTO> getBySignature(String signatureId){
        findSignature(signatureId);
        return reviewRepository.findBySignatureId(signatureId).stream()
                .map(ReviewDTO::from).toList();
    }

    @Transactional(readOnly = true)
    public double getAverage(String signatureId){
        findSignature(signatureId);
        Double avg = reviewRepository.averageRatingBySignatureId(signatureId);
        return avg == null ? 0.0 : avg;
    }

    private Signature findSignature(String id) {
        return signatureRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No existe la asignatura " + id));
    }
}
