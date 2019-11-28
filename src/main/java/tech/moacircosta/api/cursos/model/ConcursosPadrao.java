package tech.moacircosta.api.cursos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "concursos_padrao")
@Getter@Setter
public class ConcursosPadrao {

    public ConcursosPadrao() {    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Empresa empresa;

    @Column(name = "concursos")
    @ElementCollection(targetClass=String.class)
    private List<String> concursos;


}
