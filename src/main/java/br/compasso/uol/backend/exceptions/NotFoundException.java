package br.compasso.uol.backend.exceptions;

import br.compasso.uol.backend.dtos.Error;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * Exceção de NotFound. Deve ser lançada quando algum recurso buscado e obrigatório para a operação não foi encontrado.
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND)
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
