package gal.usc.etse.es.sigrate.controller;

import gal.usc.etse.es.sigrate.model.Signature;
import gal.usc.etse.es.sigrate.service.SignatureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("signatures")
public class SignatureController {
    @Autowired
    private final SignatureService signature;

    @Autowired
    public SignatureController(Signature signature) {
        this.signature = signature;
    }

    @GetMapping
    public ResponseEntity<List<Signature>> getAllSignatures() {
        return ResponseEntity.ok(signatureService.get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Signature> getSignatureById(@PathVariable String id) {
        return ResponseEntity.ok(signatureService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Signature> add(@RequestBody Signature signature) {
        signature = signatureService.add(signature);
        return ResponseEntity
                .created(MvcUriComponentsBuilder.fromMethodName(SignatureController.class, "getSignature", signature.getId()).build().toUri())
                .body(signature);
    }
}