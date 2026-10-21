package gal.usc.etse.es.sigrate.model.dto;

import gal.usc.etse.es.sigrate.model.Review;

import java.time.LocalDateTime;

public record ReviewDTO (Long id, Long userId, String signatureId,
                        int rating, String text, LocalDateTime date){

    public static ReviewDTO from(Review review){
        return new ReviewDTO(
                review.getId(),
                review.getUser().getId(),
                review.getSignature().getId(),
                review.getRating(),
                review.getText(),
                review.getDate()
        );
    }
}
