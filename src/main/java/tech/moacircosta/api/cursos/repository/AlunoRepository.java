package tech.moacircosta.api.cursos.repository;

import tech.moacircosta.api.cursos.model.Empresa;
import tech.moacircosta.api.cursos.model.Aluno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Optional<Aluno> findByEmail(String email);
    List<Aluno> findAllByNome(String nome);
    List<Aluno> findAllByEmpresa(Empresa empresa);

    @Modifying
    @Query("update Aluno a set a.pontos = ?1, a.questoes = ?2, a.acertos = ?3, a.erros = ?4 where a.id = ?5")
    void atualizarPontos(Integer pontos, Integer questoes, Integer acertos, Integer erros, Long id);

    @Query("select a.nome, a.pontos from Aluno a where a.empresa = ?1 order by a.pontos desc")
    Page<Aluno> selectByPontos(Empresa empresa, Pageable pageable);
}
