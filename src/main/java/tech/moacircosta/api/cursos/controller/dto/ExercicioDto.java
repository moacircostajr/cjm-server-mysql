package tech.moacircosta.api.cursos.controller.dto;

import tech.moacircosta.api.cursos.model.Comentario;
import tech.moacircosta.api.cursos.model.Empresa;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter@Setter
public class ExercicioDto {

    public ExercicioDto() {
    }

    private Long id;

    @NotNull
    private Empresa empresa;

    @NotEmpty
    private String enunciado1;

    private String imagemEnunciado;

    private String enunciado2;

    private String opcoes;

    private Integer gabaritoObjetivo;

    private String gabaritoSubjetivo;

    @NotNull
    private Integer pontuacao;

    private String materias;

    private String bancas;

    private String concursos;

    private String anos;

    private List<Comentario> comentarios;

    private Date dataCriacao;

    private Date dataAtualizacao;
}
