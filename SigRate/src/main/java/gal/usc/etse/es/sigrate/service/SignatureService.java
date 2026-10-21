package gal.usc.etse.es.sigrate.service;

import gal.usc.etse.es.sigrate.model.Degree;
import gal.usc.etse.es.sigrate.model.Signature;
import gal.usc.etse.es.sigrate.model.dto.SignatureDTO;
import gal.usc.etse.es.sigrate.repository.DegreeRepository;
import gal.usc.etse.es.sigrate.repository.SignatureRepository;
import gal.usc.etse.es.sigrate.exception.DuplicatedSignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SignatureService {
    private final SignatureRepository signatureRepository;
    private final DegreeRepository degreeRepository;

    public SignatureService(SignatureRepository signatureRepository,
                            DegreeRepository degreeRepository) {
        this.signatureRepository = signatureRepository;
        this.degreeRepository = degreeRepository;
    }

    public SignatureDTO addSignature(SignatureDTO dto) throws DuplicatedSignatureException {
        if (signatureRepository.existsById(dto.id())) {
            throw new DuplicatedSignatureException(dto);
        } else {
            Degree degree = degreeRepository.findById(dto.degreeId())
                    .orElseThrow(()-> new ResponseStatusException(
                            HttpStatus.BAD_REQUEST, "El grado no existe " + dto.degreeId()));

            Signature signature = new Signature();
            signature.setId(dto.id());
            signature.setYear(dto.year());
            signature.setDegree(degree);

            return SignatureDTO.from(signatureRepository.save(signature));
        }
    }

    public List<SignatureDTO> get() {
        return signatureRepository.findAll().stream().map(SignatureDTO::from).toList();
    }

    public SignatureDTO getById(String id) {
        return signatureRepository.findById(id).map(SignatureDTO::from).orElse(null);
    }
}