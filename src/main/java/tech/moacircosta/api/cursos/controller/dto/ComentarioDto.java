package tech.moacircosta.api.cursos.controller.dto;

import tech.moacircosta.api.cursos.model.Empresa;
import tech.moacircosta.api.cursos.model.Exercicio;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter@Setter
public class ComentarioDto {

    public ComentarioDto() {
    }

    private Long id;

    @NotEmpty
    private Empresa empresa;

    @NotEmpty
    private Exercicio exercicio;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String comentario;

    private Date dataCriacao;

    private Date dataAtualizacao;
}
