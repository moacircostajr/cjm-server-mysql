package tech.moacircosta.api.cursos.service;

import tech.moacircosta.api.cursos.model.ConcursosPadrao;
import tech.moacircosta.api.cursos.model.Empresa;

import java.util.Optional;

public interface ConcursosPadraoService {

    public Boolean registreConcursos(ConcursosPadrao concursosPadrao);
    public Optional<ConcursosPadrao> busqueConcursos(Empresa empresa);

}
