package gal.usc.etse.es.sigrate.controller;


import gal.usc.etse.es.sigrate.model.dto.ReviewDTO;
import gal.usc.etse.es.sigrate.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("signatures/{signatureId}/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<ReviewDTO> create(@PathVariable String signatureId,
                                            @RequestBody ReviewDTO review) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reviewService.create(signatureId, review));
    }

    @GetMapping
    public ResponseEntity<List<ReviewDTO>> getAll(@PathVariable String signatureId) {
        return ResponseEntity.ok(reviewService.getBySignature(signatureId));
    }

    @GetMapping("/average")
    public  ResponseEntity<Map<String, Double>> average(@PathVariable String signatureId) {
        return ResponseEntity.ok(Map.of("average", reviewService.getAverage(signatureId)));
    }
}
