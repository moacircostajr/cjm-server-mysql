package tech.moacircosta.api.cursos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter@Setter
@Table(name = "comentarios")
public class Comentario {

    public Comentario() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Empresa empresa;

    @ManyToOne
    private Exercicio exercicio;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "comentario", nullable = false)
    private String comentario;

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
