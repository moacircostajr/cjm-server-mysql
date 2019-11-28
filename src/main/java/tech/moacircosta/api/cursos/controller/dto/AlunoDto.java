package tech.moacircosta.api.cursos.controller.dto;

import tech.moacircosta.api.cursos.model.Empresa;
import tech.moacircosta.api.cursos.model.Perfil;
import tech.moacircosta.api.cursos.security.dto.TokenDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;


@Getter@Setter
public class AlunoDto extends UsuarioDto {

//    private static final String padraoEmail = "[a-zA-Z], 123456789, ._@";
//    private static final String padraoNome = "[a-zA-Z]";
//    private static final String padraoEndereco = "[a-zA-Z], 123456789";



    @NotEmpty
    private String endereco;

    @NotEmpty
    private String bairro;

    @NotEmpty
    private String cidade;

    @NotEmpty
    @Size(min = 2, max = 2)
    private String estado;

    private Integer pontos;

    private Integer questoes;

    private Integer acertos;

    private Integer erros;


}
