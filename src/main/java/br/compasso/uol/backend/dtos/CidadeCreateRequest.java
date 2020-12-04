package br.compasso.uol.backend.dtos;

import br.compasso.uol.backend.enums.EstadoEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel(value = "DTO para inserção de nova Cidade.")
public class CidadeCreateRequest {

    @NotBlank(message = "{cidade.nome.vazio}")
    @Size(max = 255, message = "{cidade.nome.tamanho}")
    @ApiModelProperty(notes = "O nome da cidade.")
    private String nome;

    @NotNull(message = "{cidade.estado.vazio}")
    @ApiModelProperty(notes = "O estado da cidade.")
    private EstadoEnum estado;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EstadoEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoEnum estado) {
        this.estado = estado;
    }
}
