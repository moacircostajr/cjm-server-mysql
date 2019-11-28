package tech.moacircosta.api.cursos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bancas_padrao")
@Getter@Setter
public class BancasPadrao {

    public BancasPadrao() {    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Empresa empresa;

    @Column(name = "bancas")
    @ElementCollection(targetClass=String.class)
    private List<String> bancas;
}
