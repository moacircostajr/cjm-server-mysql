package tech.moacircosta.api.cursos.service;

import tech.moacircosta.api.cursos.model.Empresa;
import tech.moacircosta.api.cursos.model.Exercicio;

import java.util.List;
import java.util.Optional;

public interface ExercicioService {

    public Boolean registreExercicio(Exercicio exercicio);
    public Optional<Exercicio> busquePorId(Long id);
    public List<Exercicio> busquePorEmpresaEMateriaEBancaEConcursoEAno(Empresa empresa, String materia, String banca, String concurso, Integer ano);
    public List<Exercicio> listeTodosPorEmpresa(Empresa empresa);
}
