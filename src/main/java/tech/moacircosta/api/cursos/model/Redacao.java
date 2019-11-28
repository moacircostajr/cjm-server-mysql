package tech.moacircosta.api.cursos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter@Setter
@Table(name = "redacoes")
public class Redacao {

    public Redacao() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Empresa empresa;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "tema", nullable = false)
    private String tema;

    @Column(name = "pontuacao")
    private Integer pontuacao;

    /*deve ser aceito somente o formato jpg*/
    @Column(name = "redacao_original")
    private String redacaoOriginal;

    /*deve ser aceito somente o formato jpg*/
    @Column(name = "redacao_corrigida")
    private String redacaoCorrigida;

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
