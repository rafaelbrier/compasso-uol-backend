package br.compasso.uol.backend.exceptions;

import br.compasso.uol.backend.dtos.Error;

import java.util.Collections;
import java.util.List;

/**
 * Exceção Genérica
 */
public class GenericException extends RuntimeException {

    private final List<Error> errors;

    public GenericException(final String message, final Throwable cause) {
        super(message, cause);
        this.errors = Collections.singletonList(new Error(message, cause.getStackTrace()));
    }

    public GenericException(final String message, final Object details) {
        super(message);
        this.errors  = Collections.singletonList(new Error(message, details));
    }

    public GenericException(final String message, final List<Error> errors) {
        super(message);
        this.errors = errors;
    }

    public GenericException(final String message) {
        this(message, message);
    }

    public List<Error> getErrors() {
        return errors;
    }
}
