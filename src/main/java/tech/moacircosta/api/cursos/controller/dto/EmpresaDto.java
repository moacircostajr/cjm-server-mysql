package tech.moacircosta.api.cursos.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter@Setter
public class EmpresaDto {

    public EmpresaDto() {    }

    private Integer id;

    @NotEmpty
    private String nomeFantasia;

    @NotEmpty
    private String proprietario;

    private String cnpj;

    private String endereco;

    @NotEmpty
    private String telefone;

    private Date dataCriacao;

    private Date dataAtualizacao;

}
