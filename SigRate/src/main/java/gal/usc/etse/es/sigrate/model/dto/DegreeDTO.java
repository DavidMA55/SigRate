package gal.usc.etse.es.sigrate.model.dto;

import gal.usc.etse.es.sigrate.model.Degree;

public record DegreeDTO(int id, String name) {
    public static DegreeDTO from(Degree degree) {
        return new DegreeDTO(degree.getId(), degree.getName());
    }
}