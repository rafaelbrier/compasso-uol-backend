package br.compasso.uol.backend.exceptions;

import br.compasso.uol.backend.dtos.Error;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * Exceção de Validação
 */
@ResponseStatus(value= HttpStatus.BAD_REQUEST)
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
