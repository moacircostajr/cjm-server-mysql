package tech.moacircosta.api.cursos.controller.dto;

import tech.moacircosta.api.cursos.model.Empresa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter@Setter
public class ClassificacaoPadraoDto {

    public ClassificacaoPadraoDto() { }

    private Integer id;

    @NotNull
    private Empresa empresa;

    @NotEmpty
    private String classificacao;

}
