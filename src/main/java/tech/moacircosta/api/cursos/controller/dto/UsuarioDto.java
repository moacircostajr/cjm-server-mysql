package tech.moacircosta.api.cursos.controller.dto;

import tech.moacircosta.api.cursos.model.Empresa;
import tech.moacircosta.api.cursos.model.Perfil;
import tech.moacircosta.api.cursos.security.dto.TokenDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter@Setter
public class UsuarioDto {

    public UsuarioDto() {
    }

    private Long id;

    @NotNull
    private Empresa empresa;

    @NotEmpty
    @Email
    private String email;

    private String senha;

    @NotEmpty
    private String nome;

    @NotEmpty
    private String telefone;

    private Perfil perfil;

    private TokenDto autenticacao;

    private Date dataCriacao;

    private Date dataAtualizacao;
}
