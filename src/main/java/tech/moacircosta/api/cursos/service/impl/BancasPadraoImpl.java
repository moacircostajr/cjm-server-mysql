package tech.moacircosta.api.cursos.service.impl;

import tech.moacircosta.api.cursos.model.Empresa;
import tech.moacircosta.api.cursos.model.BancasPadrao;
import tech.moacircosta.api.cursos.repository.BancasPadraoRepository;
import tech.moacircosta.api.cursos.service.BancasPadraoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BancasPadraoImpl implements BancasPadraoService {

    public BancasPadraoImpl() {
    }

    private static final Logger log = LoggerFactory.getLogger(BancasPadraoImpl.class);

    @Autowired
    BancasPadraoRepository bancasPadraoRepository;

    @Transactional
    @Override
    public Boolean registreBancas(BancasPadrao bancasPadrao) {
        Boolean resultado = false;
        try {
            bancasPadraoRepository.save(bancasPadrao);
            resultado = true;
        } catch (Exception e) {
            log.info(e.toString());
        }
        return resultado;
    }


    @Override
    public Optional<BancasPadrao> busqueBancas(Empresa empresa) {
        Optional<BancasPadrao> bancasPadrao = null;
        try {
            bancasPadrao = bancasPadraoRepository.findByEmpresa(empresa);
        } catch(Exception e) {
            log.info(e.toString());
        }
        return bancasPadrao;
    }
}
