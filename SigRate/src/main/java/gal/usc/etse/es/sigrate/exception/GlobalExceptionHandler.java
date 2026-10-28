package gal.usc.etse.es.sigrate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicatedSignatureException.class)
    public ProblemDetail handle(DuplicatedSignatureException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT,
                "Ya existe la asignatura: " + e.getSignatureDTO().id());
    }

    @ExceptionHandler(DuplicatedUserException.class)
    public ProblemDetail handle(DuplicatedUserException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ProblemDetail handle(ResponseStatusException e) {
        return ProblemDetail.forStatusAndDetail(e.getStatusCode(), e.getReason());
    }
}