package tech.moacircosta.api.cursos.repository;

import tech.moacircosta.api.cursos.model.ClassificacaoPadrao;
import tech.moacircosta.api.cursos.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ClassificacaoPadraoRepository extends JpaRepository<ClassificacaoPadrao, Integer> {

//    @Query("select classificacao from ClassificacaoPadrao c where c.empresa=?1")
//    Optional<String> findByEmpresa(Empresa empresa);

    Optional<ClassificacaoPadrao> findByEmpresa(Empresa empresa);
}
