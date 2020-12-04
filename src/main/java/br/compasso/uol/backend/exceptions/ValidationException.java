package br.compasso.uol.backend.exceptions;

import br.compasso.uol.backend.dtos.Error;

import java.util.List;

/**
 * Exceções de Validação
 */
public class ValidationException extends GenericException {

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(String message, Object details) {
        super(message, details);
    }

    public ValidationException(String message, List<Error> errors) {
        super(message, errors);
    }

    public ValidationException(String message) {
        super(message);
    }
}
