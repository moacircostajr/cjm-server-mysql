package tech.moacircosta.api.cursos.controller.dto;

import tech.moacircosta.api.cursos.model.Empresa;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter@Setter
public class ConcursosPadraoDto {

    public ConcursosPadraoDto() {    }


    private Integer id;

    @NotNull
    private Empresa empresa;

    @NotEmpty
    private List<String> concursos;


}
