package br.compasso.uol.backend.dtos;

import br.compasso.uol.backend.enums.SexoEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@ApiModel(value = "DTO para inserção de novo Cliente.")
public class ClienteCreateRequest {

    @NotBlank(message = "{cliente.nome.completo.vazio}")
    @Size(max = 255, message = "{cliente.nome.completo.tamanho}")
    @ApiModelProperty(notes = "O Nome Completo do cliente.")
    private String nomeCompleto;

    @NotNull(message = "{cliente.sexo.vazio}")
    @ApiModelProperty(notes = "O Sexo do cliente.")
    private SexoEnum sexo;

    @NotNull(message = "{cliente.data.nascimento.vazio}")
    private LocalDate dataNascimento;

    @NotNull(message = "{cliente.idade.vazio}")
    @Positive(message = "{cliente.idade.negativa}")
    private int idade;

    @NotNull(message = "{cliente.cidade.id.vazio}")
    private long cidadeId;

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public SexoEnum getSexo() {
        return sexo;
    }

    public void setSexo(SexoEnum sexo) {
        this.sexo = sexo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public long getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(long cidadeId) {
        this.cidadeId = cidadeId;
    }
}
