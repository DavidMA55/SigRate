package gal.usc.etse.es.sigrate.exception;

public class DuplicatedUserException extends RuntimeException {
    public DuplicatedUserException(String username) {
        super("Ya existe el usuario " + username);
    }
}
