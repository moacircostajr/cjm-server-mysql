package tech.moacircosta.api.cursos.repository;

import tech.moacircosta.api.cursos.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

    List<Empresa> findAllByNomeFantasia(String nomeFantasia);

}
