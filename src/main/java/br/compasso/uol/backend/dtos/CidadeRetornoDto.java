package br.compasso.uol.backend.dtos;

public class CidadeRetornoDto extends CidadeCreateRequest {

    private long cidadeId;

    public long getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(long cidadeId) {
        this.cidadeId = cidadeId;
    }
}
