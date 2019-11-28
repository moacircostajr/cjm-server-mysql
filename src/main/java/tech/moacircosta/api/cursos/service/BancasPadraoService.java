package tech.moacircosta.api.cursos.service;

import tech.moacircosta.api.cursos.model.BancasPadrao;
import tech.moacircosta.api.cursos.model.Empresa;

import java.util.Optional;

public interface BancasPadraoService {

    public Boolean registreBancas(BancasPadrao bancasPadrao);
    public Optional<BancasPadrao> busqueBancas(Empresa empresa);

}
