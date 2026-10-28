package gal.usc.etse.es.sigrate.service;

import gal.usc.etse.es.sigrate.model.Degree;
import gal.usc.etse.es.sigrate.model.dto.DegreeDTO;
import gal.usc.etse.es.sigrate.model.dto.SignatureDTO;
import gal.usc.etse.es.sigrate.repository.DegreeRepository;
import gal.usc.etse.es.sigrate.repository.SignatureRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DegreeService {
    private final DegreeRepository degreeRepository;
    private final SignatureRepository signatureRepository;

    public DegreeService(DegreeRepository degreeRepository,
                         SignatureRepository signatureRepository) {
        this.degreeRepository = degreeRepository;
        this.signatureRepository = signatureRepository;
    }

    @Transactional(readOnly = true)
    public List<DegreeDTO> get(){
        return degreeRepository.findAll().stream().map(DegreeDTO::from).toList();
    }

    @Transactional(readOnly = true)
    public DegreeDTO getById(int id){
        return DegreeDTO.from(findDegree(id));
    }

    @Transactional
    public DegreeDTO create(DegreeDTO dto){
        if (dto.name() == null || dto.name().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre del grado no puede estar vacío");
        }
        if (degreeRepository.existsById(dto.id())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Ya existe un grado con el id " + dto.id());
        }
        Degree degree = new Degree();
        degree.setId(dto.id());
        degree.setName(dto.name());
        return DegreeDTO.from(degreeRepository.save(degree));
    }

    @Transactional(readOnly = true)
    public List<SignatureDTO> getSignatures(int id){
        findDegree(id);
        return signatureRepository.findByDegreeId(id).stream()
                .map(SignatureDTO::from).toList();
    }

    private Degree findDegree(int id){
        return degreeRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "El grado no existe " + id));
    }
}
