package gal.usc.etse.es.sigrate.controller;

import gal.usc.etse.es.sigrate.model.dto.DegreeDTO;
import gal.usc.etse.es.sigrate.model.dto.SignatureDTO;
import gal.usc.etse.es.sigrate.service.DegreeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("degrees")
public class DegreeController {
    private final DegreeService degreeService;

    public DegreeController(DegreeService degreeService) {
        this.degreeService = degreeService;
    }

    @GetMapping
    public ResponseEntity<List<DegreeDTO>> getAll() {
        return ResponseEntity.ok(degreeService.get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DegreeDTO> GetById(@PathVariable int id){
        return ResponseEntity.ok(degreeService.getById(id));
    }

    @PostMapping
    public ResponseEntity<DegreeDTO> getById(@RequestBody DegreeDTO degreeDTO){
        DegreeDTO created = degreeService.create(degreeDTO);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(created.id()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping("/{id}/signatures")
    public ResponseEntity<List<SignatureDTO>> getSignatures(@PathVariable int id) {
        return ResponseEntity.ok(degreeService.getSignatures(id));
    }
}
