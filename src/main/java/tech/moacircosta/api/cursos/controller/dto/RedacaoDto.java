package tech.moacircosta.api.cursos.controller.dto;

import tech.moacircosta.api.cursos.model.Empresa;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter@Setter
public class RedacaoDto {

    public RedacaoDto() {
    }

    private Long id;

    @NotEmpty
    private Empresa empresa;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String tema;

    private Integer pontuacao;

    /*deve ser aceito somente o formato jpg*/
    private String redacaoOriginal;

    /*deve ser aceito somente o formato jpg*/
    private String redacaoCorrigida;

    private Date dataCriacao;

    private Date dataAtualizacao;

}
