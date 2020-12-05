package br.compasso.uol.backend.dtos;

import br.compasso.uol.backend.enums.EstadoEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.lang.Nullable;

@ApiModel(value = "DTO para filtragem de busca de Cidades.")
public class CidadeFetchRequest {

    @ApiModelProperty(notes = "Filtra cidades com o Nome igual.")
    private String nome;
    @ApiModelProperty(notes = "Filtra cidades com o Estado igual.")
    private EstadoEnum estado;

    @Nullable
    public String getNome() {
        return nome;
    }

    @Nullable
    public EstadoEnum getEstado() {
        return estado;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEstado(EstadoEnum estado) {
        this.estado = estado;
    }
}
