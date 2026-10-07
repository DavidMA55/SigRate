package  gal.usc.etse.es.sigrate.model.dto;

import gal.usc.etse.es.sigrate.model.Signature;

public record SignatureDTO(String id, int year, String degreeId) {
    public static SignatureDTO FromEntity(Signature signature) {
        return new SignatureDTO(signature.getId(), signature.getYear(), signature.getDegree().getId());
    }
}