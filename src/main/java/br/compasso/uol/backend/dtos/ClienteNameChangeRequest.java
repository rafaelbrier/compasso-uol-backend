package br.compasso.uol.backend.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ApiModel(value = "DTO para alteração de nome completo do Cliente.")
public class ClienteNameChangeRequest {

    @NotBlank(message = "{cliente.nome.completo.vazio}")
    @Size(max = 255, message = "{cliente.nome.completo.tamanho}")
    @ApiModelProperty(notes = "O novo Nome Completo do cliente.")
    private String nomeCompleto;

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }
}
