package tech.moacircosta.api.cursos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter@Setter
@Table(name = "usuarios")
public class Usuario {

    public Usuario() {
    }

    /*@GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Empresa empresa;

    @Column (name = "email", nullable = false)
    private String email;

    @Column (name = "senha", nullable = false)
    private String senha;

    @Column (name = "nome", nullable = false)
    private String nome;

    @Column (name = "telefone", nullable = false)
    private String telefone;

    @Enumerated(EnumType.STRING)
    @Column (name = "perfil", nullable = false)
    private Perfil perfil;

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
