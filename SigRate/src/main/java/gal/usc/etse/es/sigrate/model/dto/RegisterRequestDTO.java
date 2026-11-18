package gal.usc.etse.es.sigrate.model.dto;

public record RegisterRequestDTO (
    @NotBlank String username,
    @NotBlank @Email String email,
    @NotBlank @Size(min = 8) String password) {

}