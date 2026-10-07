package gal.usc.etse.es.sigrate.controller;

import gal.usc.etse.es.sigrate.model.Signature;
import gal.usc.etse.es.sigrate.model.dto.SignatureDTO;
import gal.usc.etse.es.sigrate.service.SignatureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("signatures")
public class SignatureController {
    private final SignatureService signatureService;

    @Autowired
    public SignatureController(SignatureService signatureService) {
        this.signatureService = signatureService;
    }

    @GetMapping
    public ResponseEntity<List<SignatureDTO>> getAllSignatures() {
        return ResponseEntity.ok(signatureService.get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SignatureDTO> getById(@PathVariable String id) {
        return ResponseEntity.ok(signatureService.getById(id));
    }

    @PostMapping
    public ResponseEntity<SignatureDTO> add(@RequestBody SignatureDTO signature) {
        signature = signatureService.addSignature(signature);
        return ResponseEntity
                .created(MvcUriComponentsBuilder.fromMethodName(SignatureController.class, "getSignature", signature.id()).build().toUri())
                .body(signature);
    }
}