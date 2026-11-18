package gal.usc.etse.es.sigrate.model.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO (
    @NotBlank String username,
    @NotBlank @Email String email,
    @NotBlank @Size(min = 8) String password) {

}