package br.compasso.uol.backend.dtos;

import br.compasso.uol.backend.utils.MessageUtils;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

/**
 * Classe padrão de Resposta para retorno das requisições
 */
public final class Response<T> {

    private final LocalDateTime timestamp;
    private final int status;
    private final String message;
    private final T data;
    private final T error;

    /**
     * Se status entre 200 e 299 seta o atributo {@code data} com o parâmetro {@code dataOrError}
     * Caso contrário seta o atributo {@code error} com o parâmetro {@code dataOrError}
     */
    private Response(@NonNull int status, @NonNull String message, @Nullable T dataOrError) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        if (status >= 200 && status < 300) {
            this.data = dataOrError;
            this.error = null;
        } else {
            this.data = null;
            this.error = dataOrError;
        }
    }


    /**
     * Gera um objeto {@link Response} com o corpo, status e mensagem informados
     *
     * @param body    o corpo da {@link Response}
     * @param status  o status da {@link Response}
     * @param message a mensagem da {@link Response}
     * @param <T>     a classe do Corpo
     * @return o objeto {@link Response} respectivo
     */
    public static <T> Response<T> of(@Nullable T body, @NonNull int status, @NonNull String message) {
        return new Response<>(status, message, body);
    }

    /**
     * Gera um objeto {@link Response} com o corpo e {@link HttpStatus} informados
     * Veja também {@link Response#of(Object, int, String)}
     */
    public static <T> Response<T> of(@Nullable T body, @NonNull HttpStatus httpStatus) {
        return new Response<>(httpStatus.value(), httpStatus.getReasonPhrase(), body);
    }

    /**
     * Gera um objeto {@link Response} com o corpo informado e status {@link HttpStatus#OK}
     * Veja também {@link Response#of(Object, int, String)}
     */
    public static <T> Response<T> ok(@Nullable T body) {
        return of(body, HttpStatus.OK.value(), MessageUtils.buscarMensagem("response.code200"));
    }

    /**
     * Gera um objeto {@link Response} com o corpo informado e status {@link HttpStatus#CREATED}
     * Veja também {@link Response#of(Object, int, String)}
     */
    public static <T> Response<T> created(@Nullable T body) {
        return of(body, HttpStatus.CREATED.value(), MessageUtils.buscarMensagem("response.code201"));
    }

    /**
     * Gera um objeto {@link Response} com o corpo informado e status {@link HttpStatus#NO_CONTENT}
     * Veja também {@link Response#of(Object, int, String)}
     */
    public static <T> Response<T> deleted() {
        return of(null, HttpStatus.NO_CONTENT.value(), MessageUtils.buscarMensagem("response.code204"));
    }

    /**
     * Gera um objeto {@link Response} com o corpo informado e status {@link HttpStatus#BAD_REQUEST}
     * Veja também {@link Response#of(Object, int, String)}
     */
    public static <T> Response<T> badRequest(@Nullable T body) {
        return of(body, HttpStatus.BAD_REQUEST.value(), MessageUtils.buscarMensagem("response.code400"));
    }

    /**
     * Gera um objeto {@link Response} com o corpo informado e status {@link HttpStatus#UNAUTHORIZED}
     * Veja também {@link Response#of(Object, int, String)}
     */
    public static <T> Response<T> unauthorized(@Nullable T body) {
        return of(body, HttpStatus.UNAUTHORIZED.value(), MessageUtils.buscarMensagem("response.code401"));
    }

    /**
     * Gera um objeto {@link Response} com o corpo informado e status {@link HttpStatus#FORBIDDEN}
     * Veja também {@link Response#of(Object, int, String)}
     */
    public static <T> Response<T> forbidden(@Nullable T body) {
        return of(body, HttpStatus.FORBIDDEN.value(), MessageUtils.buscarMensagem("response.code403"));
    }

    /**
     * Gera um objeto {@link Response} com o corpo informado e status {@link HttpStatus#NOT_FOUND}
     * Veja também {@link Response#of(Object, int, String)}
     */
    public static <T> Response<T> notFound(@Nullable T body) {
        return of(body, HttpStatus.NOT_FOUND.value(), MessageUtils.buscarMensagem("response.code404"));
    }

    /**
     * Gera um objeto {@link Response} com o corpo informado e status {@link HttpStatus#INTERNAL_SERVER_ERROR}
     * Veja também {@link Response#of(Object, int, String)}
     */
    public static <T> Response<T> internalError(@Nullable T body) {
        return of(body, HttpStatus.INTERNAL_SERVER_ERROR.value(), MessageUtils.buscarMensagem("response.code500"));
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public T getError() {
        return error;
    }
}
