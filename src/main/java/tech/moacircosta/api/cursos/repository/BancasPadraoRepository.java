package tech.moacircosta.api.cursos.repository;

import tech.moacircosta.api.cursos.model.BancasPadrao;
import tech.moacircosta.api.cursos.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BancasPadraoRepository extends JpaRepository<BancasPadrao, Integer> {

//    @Query("select bancas from BancasPadrao b where b.empresa=?1")
//    List<String> findByEmpresa(Empresa empresa);

    Optional<BancasPadrao> findByEmpresa(Empresa empresa);
}
