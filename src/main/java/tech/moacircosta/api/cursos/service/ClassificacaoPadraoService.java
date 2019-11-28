package tech.moacircosta.api.cursos.service;

import tech.moacircosta.api.cursos.model.ClassificacaoPadrao;
import tech.moacircosta.api.cursos.model.Empresa;

import java.util.Optional;

public interface ClassificacaoPadraoService {

    public Boolean registreClassificacao(ClassificacaoPadrao classificacaoPadrao);
    public Optional<ClassificacaoPadrao> busqueClassificacao(Empresa empresa);

}
