package br.compasso.uol.backend.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.lang.Nullable;

@ApiModel(value = "DTO para filtragem de busca de Clientes.")
public class ClienteFetchRequest {

    @ApiModelProperty(notes = "Filtra clientes que CONTÉM uma parte do Nome Completo [case insensitive].")
    private String nome;

    @Nullable
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
