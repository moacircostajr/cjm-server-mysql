package tech.moacircosta.api.cursos.repository;

import tech.moacircosta.api.cursos.model.Empresa;
import tech.moacircosta.api.cursos.model.Exercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ExercicioRepository extends JpaRepository<Exercicio, Long> {

    Optional<Exercicio> findById(UUID id);
    List<Exercicio> findAllByEmpresaAndMateriasContainsAndBancasContainsAndConcursosContainsAndAnosContains(Empresa empresa, String materias, String bancas, String concursos, Integer anos);
    List<Exercicio> findAllByEmpresa(Empresa empresa);
}
