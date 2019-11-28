package tech.moacircosta.api.cursos.repository;

import tech.moacircosta.api.cursos.model.ConcursosPadrao;
import tech.moacircosta.api.cursos.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ConcursosPadraoRepository extends JpaRepository<ConcursosPadrao, Integer> {

//    @Query("select concursos from ConcursosPadrao p where p.empresa=?1")
//    List<String> findByEmpresa(Empresa empresa);

    Optional<ConcursosPadrao> findByEmpresa(Empresa empresa);
}
