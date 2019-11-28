package tech.moacircosta.api.cursos.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter@Setter
public class Aluno extends Usuario {

    public Aluno() {
    }

//    @GeneratedValue(generator = "uuid2")
//    @GenericGenerator(name = "uuid2", strategy = "uuid2")
//    @Column(columnDefinition = "BINARY(16)")

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "estado")
    private String estado;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Redacao> redacao;

    @Column(name = "pontos")
    private Integer pontos;

    @Column(name = "questoes")
    private Integer questoes;

    @Column(name = "acertos")
    private Integer acertos;

    @Column(name = "erros")
    private Integer erros;

    @Column(name = "data_criacao", nullable = false)
    private Date dataCriacao;

    @Column(name = "data_atualizacao", nullable = false)
    private Date dataAtualizacao;


    @PrePersist
    public void setDataCriacao() {
        final Date dataAtual = new Date();
        this.dataCriacao = dataAtual;
        this.dataAtualizacao = dataAtual;
    }

    @PreUpdate
    public void setDataAtualizacao() {
        this.dataAtualizacao = new Date();
    }
}
