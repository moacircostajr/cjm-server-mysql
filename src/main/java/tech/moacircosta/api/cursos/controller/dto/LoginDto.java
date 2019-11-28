package tech.moacircosta.api.cursos.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter@Setter
public class LoginDto {
    @NotEmpty
    private String email;
    @NotEmpty
    private String senha;
}
