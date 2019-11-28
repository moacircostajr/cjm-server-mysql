package tech.moacircosta.api.cursos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "classificacao_padrao")
@Getter@Setter
public class ClassificacaoPadrao {

    public ClassificacaoPadrao() { }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Empresa empresa;

    @Column(name = "classificacao")
    private String classificacao;

}
