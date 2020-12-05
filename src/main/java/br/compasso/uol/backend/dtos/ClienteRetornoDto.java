package br.compasso.uol.backend.dtos;

public class ClienteRetornoDto extends ClienteCreateRequest {

    private long clienteId;

    public long getClienteId() {
        return clienteId;
    }

    public void setClienteId(long cidadeId) {
        this.clienteId = cidadeId;
    }
}
