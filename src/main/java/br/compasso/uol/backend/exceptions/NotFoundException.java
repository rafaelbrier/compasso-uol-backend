package br.compasso.uol.backend.exceptions;

import br.compasso.uol.backend.dtos.Error;

import java.util.List;

/**
 * Exceções de NotFound
 */
public class NotFoundException extends GenericException {

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(String message, Object details) {
        super(message, details);
    }

    public NotFoundException(String message, List<Error> errors) {
        super(message, errors);
    }

    public NotFoundException(String message) {
        super(message);
    }
}
