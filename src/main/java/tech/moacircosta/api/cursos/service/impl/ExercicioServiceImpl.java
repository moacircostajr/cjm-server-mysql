package tech.moacircosta.api.cursos.service.impl;

import tech.moacircosta.api.cursos.model.Empresa;
import tech.moacircosta.api.cursos.model.Exercicio;
import tech.moacircosta.api.cursos.repository.ExercicioRepository;
import tech.moacircosta.api.cursos.service.ExercicioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExercicioServiceImpl implements ExercicioService {

    @Autowired
    private ExercicioRepository exercicioRepository;

    private static final Logger log = LoggerFactory.getLogger(ExercicioServiceImpl.class);

    public ExercicioServiceImpl() {
    }

    @Override
    public Boolean registreExercicio(Exercicio exercicio) {
        Boolean resultado = false;
        try {
            exercicioRepository.save(exercicio);
            resultado = true;
        } catch (Exception e) {
//            log.info(e.toString());
            e.printStackTrace();
        }
        return resultado;
    }

    @Override
    public Optional<Exercicio> busquePorId(Long id) {
        Optional<Exercicio> resultado = null;
        try {
            resultado = exercicioRepository.findById(id);
        } catch (Exception e) {
            log.info(e.toString());
        }
        return resultado;
    }


    @Override
    public List<Exercicio> busquePorEmpresaEMateriaEBancaEConcursoEAno(Empresa empresa, String materia, String banca, String concurso, Integer ano) {
        List<Exercicio> resultado = null;
        try {
            resultado = exercicioRepository.findAllByEmpresaAndMateriasContainsAndBancasContainsAndConcursosContainsAndAnosContains(empresa, materia, banca, concurso, ano);
        } catch (Exception e) {
            log.info(e.toString());
        }
        return resultado;
    }


    @Override
    public List<Exercicio> listeTodosPorEmpresa(Empresa empresa) {
        List<Exercicio> resultado = null;
        try {
            resultado = exercicioRepository.findAllByEmpresa(empresa);
        } catch (Exception e) {
            log.info(e.toString());
        }
        return resultado;
    }

}
