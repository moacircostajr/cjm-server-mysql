package tech.moacircosta.api.cursos.service.impl;

import tech.moacircosta.api.cursos.model.Empresa;
import tech.moacircosta.api.cursos.model.ClassificacaoPadrao;
import tech.moacircosta.api.cursos.repository.ClassificacaoPadraoRepository;
import tech.moacircosta.api.cursos.service.ClassificacaoPadraoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClassificacaoPadraoImpl implements ClassificacaoPadraoService {

    public ClassificacaoPadraoImpl() {
    }

    private static final Logger log = LoggerFactory.getLogger(ClassificacaoPadraoImpl.class);

    @Autowired
    ClassificacaoPadraoRepository classificacaoPadraoRepository;

    @Override
    @Transactional
    public Boolean registreClassificacao(ClassificacaoPadrao classificacaoPadrao) {
        Boolean resultado = false;
        try {
            classificacaoPadraoRepository.save(classificacaoPadrao);
            resultado = true;
        } catch (Exception e) {
            log.info(e.toString());
        }
        return resultado;
    }

    @Override
    public Optional<ClassificacaoPadrao> busqueClassificacao(Empresa empresa) {
        Optional<ClassificacaoPadrao> classificacaoPadrao = null;
        try {
            classificacaoPadrao = classificacaoPadraoRepository.findByEmpresa(empresa);
        }
        catch(Exception e) {
            log.info(e.toString());
        }
        return classificacaoPadrao;
    }

}
