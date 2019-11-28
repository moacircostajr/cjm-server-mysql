package tech.moacircosta.api.cursos.service.impl;

import tech.moacircosta.api.cursos.model.Empresa;
import tech.moacircosta.api.cursos.repository.EmpresaRepository;
import tech.moacircosta.api.cursos.service.EmpresaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    EmpresaRepository empresaRepository;

    private static final Logger log = LoggerFactory.getLogger(EmpresaServiceImpl.class);

    @Override
    public Boolean registreEmpresa(Empresa empresa) {
        Boolean resultado = false;
        try {
            empresaRepository.save(empresa);
            resultado = true;
        } catch (Exception e) {
            log.info(e.toString());
        }
        return resultado;
    }

    @Override
    public List<Empresa> busqueEmpresa(String nomeFantasia) {
        List<Empresa> empresaList = null;
        try {
            empresaList = empresaRepository.findAllByNomeFantasia(nomeFantasia);
        } catch (Exception e) {
            log.info(e.toString());
        }
        return empresaList;
    }

    @Override
    public Optional<Empresa> busqueEmpresaPorId(Integer idEmpresa) {
        Optional<Empresa> empresaOptional = null;
        try {
            empresaOptional = empresaRepository.findById(idEmpresa);
        } catch (Exception e) {
            log.info(e.toString());
        }
        return empresaOptional;
    }
}
