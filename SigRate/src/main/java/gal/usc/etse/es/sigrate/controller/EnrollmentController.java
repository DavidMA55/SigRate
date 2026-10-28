package gal.usc.etse.es.sigrate.controller;

import gal.usc.etse.es.sigrate.model.Enrollment;
import gal.usc.etse.es.sigrate.model.dto.EnrollmentDTO;
import gal.usc.etse.es.sigrate.service.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("users/{userId}/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentDTO>> getAll(@PathVariable Long userId) {
        return ResponseEntity.ok(enrollmentService.getByUser(userId));
    }

    @GetMapping("/{signatureId}")
    public ResponseEntity<EnrollmentDTO> getOne(@PathVariable Long userId,
                                                @PathVariable String signatureId) {
        return ResponseEntity.ok(enrollmentService.get(userId, signatureId));
    }

    @PostMapping
    public ResponseEntity<EnrollmentDTO> enroll(@PathVariable Long userId,
                                                @RequestBody EnrollmentDTO body) {
        EnrollmentDTO created = enrollmentService.enroll(userId, body);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{signatureId}").buildAndExpand(created.signatureId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @DeleteMapping("/{signatureId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId,
                                       @PathVariable String signatureId) {
        enrollmentService.delete(userId, signatureId);
        return ResponseEntity.noContent().build();
    }
}
