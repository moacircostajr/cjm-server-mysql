package tech.moacircosta.api.cursos.service.impl;

import tech.moacircosta.api.cursos.model.ConcursosPadrao;
import tech.moacircosta.api.cursos.model.Empresa;
import tech.moacircosta.api.cursos.repository.ConcursosPadraoRepository;
import tech.moacircosta.api.cursos.service.ConcursosPadraoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConcursosPadraoImpl implements ConcursosPadraoService {

    public ConcursosPadraoImpl() {
    }

    private static final Logger log = LoggerFactory.getLogger(ConcursosPadraoImpl.class);

    @Autowired
    ConcursosPadraoRepository concursosPadraoRepository;

    @Override
    public Boolean registreConcursos(ConcursosPadrao concursosPadrao) {
        Boolean resultado = false;
        try {
            concursosPadraoRepository.save(concursosPadrao);
            resultado = true;
        } catch (Exception e) {
            log.info(e.toString());
        }
        return resultado;
    }

    @Override
    public Optional<ConcursosPadrao> busqueConcursos(Empresa empresa) {
        Optional<ConcursosPadrao> concursosPadrao = null;
        try {
            concursosPadrao = concursosPadraoRepository.findByEmpresa(empresa);
        } catch(Exception e) {
            log.info(e.toString());
        }
        return concursosPadrao;
    }
}
