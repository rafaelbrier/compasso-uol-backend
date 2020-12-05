package br.compasso.uol.backend.models;

import br.compasso.uol.backend.enums.SexoEnum;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cliente_id", nullable = false)
    private long id;

    @Column(name = "nome_completo", nullable = false)
    private String nomeCompleto;
    @Column(name = "sexo", nullable = false)
    @Enumerated(EnumType.STRING)
    private SexoEnum sexo;
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;
    @Column(name = "idade", nullable = false)
    private int idade;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "cidadeId")
    private Cidade cidade;

    public static String[] relations() {
        return new String[]{"cidade"};
    }

    public long getId() {
        return id;
    }

    public void setId(long clienteId) {
        this.id = clienteId;
    }

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

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}
