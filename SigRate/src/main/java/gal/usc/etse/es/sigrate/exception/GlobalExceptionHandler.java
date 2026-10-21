package gal.usc.etse.es.sigrate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicatedSignatureException.class)
    public ResponseEntity<String> handle(DuplicatedSignatureException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("Ya existe la asignatura: " + e.getSignatureDTO().id());
    }

    @ExceptionHandler(DuplicatedUserException.class)
    public ResponseEntity<String> handle(DuplicatedUserException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}
