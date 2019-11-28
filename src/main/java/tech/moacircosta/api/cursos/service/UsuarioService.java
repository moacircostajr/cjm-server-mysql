package tech.moacircosta.api.cursos.service;

import tech.moacircosta.api.cursos.model.Aluno;
import tech.moacircosta.api.cursos.model.Empresa;
import tech.moacircosta.api.cursos.model.Usuario;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    public List<Usuario> listeUsuariosPorNome(String nome);
    public List<Usuario> listaUsuariosPorEmpresa(Empresa empresa);
    public boolean removaUsuario (Usuario usuario);
    public Optional<Usuario> busqueUsuarioPorEmail(String email);
    public Optional<Usuario> busqueUsuarioPorId(Long id);
    public Boolean registreUsuario(Usuario usuario);
    public Boolean atualizeUsuario(Usuario usuario);
//    public Boolean atualizeUsuarioSemSenha(Usuario converterUsuarioDtoPUsuario);
    public Boolean registreAluno(Aluno aluno);
    public Boolean atualizePontosAluno(Integer pontos, Integer questoes, Integer acertos, Integer erros, Long id);
    public Optional<Aluno> busqueAlunoPorId(Long id);
    public Optional<Aluno> busqueAlunoPorEmail(String email);
    public List<Aluno> listeAlunosPorNome(String nome);
    public List<Aluno> listeAlunosPorEmpresa(Empresa empresa);
    public Page<Aluno> busqueRanking(Empresa empresa);

}
