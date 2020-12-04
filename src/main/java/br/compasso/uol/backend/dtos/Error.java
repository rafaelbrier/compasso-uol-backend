package br.compasso.uol.backend.dtos;

public class Error {

    private final String message;
    private final Object details;

    public Error(String message, Object details) {
        this.message = message;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public Object getDetails() {
        return details;
    }
}
