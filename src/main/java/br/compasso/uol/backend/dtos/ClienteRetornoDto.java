package br.compasso.uol.backend.dtos;

import br.compasso.uol.backend.models.Cidade;

public class ClienteRetornoDto extends ClienteCreateRequest {

    private long id;
    private Cidade cidade;

    public long getId() {
        return id;
    }

    public void setId(long cidadeId) {
        this.id = cidadeId;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}
