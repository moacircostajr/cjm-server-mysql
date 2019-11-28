package tech.moacircosta.api.cursos.repository;

import tech.moacircosta.api.cursos.model.Empresa;
import tech.moacircosta.api.cursos.model.Perfil;
import tech.moacircosta.api.cursos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
    List<Usuario> findAllByNome(String nome);
    Optional<Usuario> findById(UUID id);
    List<Usuario> findAllByPerfilIsNotAndEmpresa(Perfil perfil, Empresa empresa);

    @Modifying
    @Query("update Usuario u set u.email=?2, u.senha=?3, u.nome=?4, u.telefone=?5, u.perfil=?6 where u.id=?1")
    void update(Long id, String email, String senha, String nome, String telefone, Perfil perfil);

/*    @Modifying
    @Query("update Usuario u set u.email=?2, u.nome=?3, u.telefone=?4, u.perfil=?5 where u.id=?1")
    void updatePasswordNull(Long id, String email, String nome, String telefone, Perfil perfil);*/
}
