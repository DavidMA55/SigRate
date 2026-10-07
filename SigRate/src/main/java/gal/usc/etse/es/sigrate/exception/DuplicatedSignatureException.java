package gal.usc.etse.es.sigrate.exception;

import gal.usc.etse.es.sigrate.model.dto.SignatureDTO;

public class DuplicatedSignatureException extends Exception {
    private final SignatureDTO signatureDTO;

    public DuplicatedSignatureException(SignatureDTO signatureDTO) {
        this.signatureDTO = signatureDTO;
    }

    public SignatureDTO getSignatureDTO() {
        return signatureDTO;
    }
}