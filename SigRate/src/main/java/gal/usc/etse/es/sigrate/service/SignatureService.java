package gal.usc.etse.es.sigrate.service;

import gal.usc.etse.es.sigrate.model.Signature;
import gal.usc.etse.es.sigrate.model.dto.SignatureDTO;
import gal.usc.etse.es.sigrate.repository.SignatureRepository;
import gal.usc.etse.es.sigrate.exception.DuplicatedSignatureException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignatureService {
    private final SignatureRepository signatureRepository;

    public SignatureService(SignatureRepository signatureRepository) {
        this.signatureRepository = signatureRepository;
    }

    public SignatureDTO addSignature(SignatureDTO signatureDTO) throws DuplicatedSignatureException {
        if (!signatureRepository.existsById(signatureDTO.id())) {
            signatureRepository.save(Signature.from(signatureDTO));
            return signatureDTO;
        } else {
            throw new DuplicatedSignatureException(signatureDTO);
        }
    }

    public List<SignatureDTO> get() {
        return signatureRepository.findAll().stream().map(SignatureDTO::from).toList();
    }

    public SignatureDTO getById(String id) {
        return signatureRepository.findById(id).map(SignatureDTO::from).orElse(null);
    }
}