package tech.moacircosta.api.cursos.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter@Setter
public class PontuacaoDto {
    @NotEmpty
    private Integer pontos;
    @NotEmpty
    private Integer questoes;
    @NotEmpty
    private Integer acertos;
    @NotEmpty
    private Integer erros;
}
