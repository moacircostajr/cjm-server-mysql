package tech.moacircosta.api.cursos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter@Setter
@Table(name = "exercicios")
public class Exercicio {

    public Exercicio() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private Empresa empresa;

    @Column(name = "enunciado1", nullable = false)
    private String enunciado1;

    @Lob
    @Column(name = "imagem_enunciado", columnDefinition = "mediumblob")
    private byte[] imagemEnunciado;

    @Column(name="enunciado2")
    private String enunciado2;

    @Column(name = "opcoes")
    private String opcoes;

    @Column(name = "gabarito_objetivo")
    private Integer gabaritoObjetivo;

    @Column(name = "gabarito_subjetivo")
    private String gabaritoSubjetivo;

    @Column(name = "pontuacao")

    private Integer pontuacao;

    @Column(name = "materias")
    private String materias;

    @Column(name = "bancas")
    private String bancas;

    @Column(name = "concursos")
    private String concursos;

    @Column(name = "anos")
    private String anos;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comentario> comentarios;

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

    @Override
    public String toString() {
        return "Exercicio{" +
                "id=" + id +
                ", enunciado1='" + enunciado1 + '\'' +
                ", imagemEnunciado='" + imagemEnunciado + '\'' +
                ", enunciado2='" + enunciado2 + '\'' +
                ", opcoes=" + opcoes +
                ", gabaritoObjetivo=" + gabaritoObjetivo +
                ", gabaritoSubjetivo='" + gabaritoSubjetivo + '\'' +
                ", pontuacao=" + pontuacao +
                ", materias='" + materias + '\'' +
                ", bancas='" + bancas + '\'' +
                ", concursos='" + concursos + '\'' +
                ", anos='" + anos + '\'' +
                ", comentarios=" + comentarios +
                ", dataCriacao=" + dataCriacao +
                ", dataAtualizacao=" + dataAtualizacao +
                '}';
    }
}
