package tech.moacircosta.api.cursos.service.impl;

import tech.moacircosta.api.cursos.model.Empresa;
import tech.moacircosta.api.cursos.repository.AlunoRepository;
import tech.moacircosta.api.cursos.repository.UsuarioRepository;
import tech.moacircosta.api.cursos.service.UsuarioService;
import tech.moacircosta.api.cursos.model.Aluno;
import tech.moacircosta.api.cursos.model.Perfil;
import tech.moacircosta.api.cursos.model.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    public UsuarioServiceImpl() {
    }

    @Override
    public Boolean registreAluno(Aluno aluno) {
        Boolean resultado = false;
        try {
            alunoRepository.save(aluno);
            resultado = true;
        } catch (Exception e) {
            log.info(e.toString());
        }
        return resultado;
    }

    @Transactional
    @Override
    public Boolean atualizePontosAluno(Integer pontos, Integer questoes, Integer acertos, Integer erros, Long id) {
        Boolean resultado = false;
        try {
            alunoRepository.atualizarPontos(pontos, questoes, acertos, erros, id);
            resultado = true;
        } catch (Exception e) {
            log.info(e.toString());
        }
        return resultado;
    }

    @Override
    public Optional<Aluno> busqueAlunoPorId(Long id) {
        Optional<Aluno> aluno = null;
        try {
            aluno = alunoRepository.findById(id);
        } catch(Exception e) {
            log.info(e.toString());
        }
        return aluno;
    }

    @Override
    public Optional<Aluno> busqueAlunoPorEmail(String email) {
        Optional<Aluno> aluno = alunoRepository.findByEmail(email);
        return aluno;
    }

    @Override
    public List<Aluno> listeAlunosPorNome(String nome) {
        List<Aluno> alunos = null;
        try {
            alunos = alunoRepository.findAllByNome(nome);
        } catch (Exception e) {
            log.info(e.toString());
        }
        return alunos;
    }

    @Override
    public List<Aluno> listeAlunosPorEmpresa(Empresa empresa) {
        List<Aluno> alunos = null;
        try {
            alunos = alunoRepository.findAllByEmpresa(empresa);
        } catch (Exception e) {
            log.info(e.toString());
        }
        return alunos;
    }

    /*
    public Optional<Aluno> verifiqueCredenciaisAluno(String email, String senha) {
        Optional<Aluno> aluno = null;
        try {
            aluno = alunoRepository.findByEmail(email);
            if (aluno.get().getSenha().equals(senha)) {
                return aluno;
            } else {
                return null;
            }
        } catch (Exception e) {
            log.info(e.toString());
            return null;
        }
    }
*/

    public Page<Aluno> busqueRanking(Empresa empresa) {
        Page<Aluno> alunos = null;
        try {
            alunos = alunoRepository.selectByPontos(empresa, new PageRequest(0, 10));
        } catch (Exception e) {
            log.info(e.toString());
        }
        return alunos;
    }







    @Override
    public Boolean registreUsuario(Usuario usuario) {
        Boolean resultado = false;
        try {
            usuarioRepository.save(usuario);
            resultado = true;
        } catch (Exception e) {
            log.info(e.toString());
        }
        return resultado;
    }

    @Transactional
    @Override
    public Boolean atualizeUsuario(Usuario usuario) {
        Boolean resultado = false;
        try {
            usuarioRepository.update(usuario.getId(), usuario.getEmail(), usuario.getSenha(), usuario.getNome(), usuario.getTelefone(), usuario.getPerfil());
            resultado = true;
        } catch (Exception e) {
            log.info(e.toString());
        }
        return resultado;
    }

    /*@Transactional
    @Override
    public Boolean atualizeUsuarioSemSenha(Usuario usuario) {
        Boolean resultado = false;
        try {
            usuarioRepository.updatePasswordNull(usuario.getId(), usuario.getEmail(), usuario.getNome(), usuario.getTelefone(), usuario.getPerfil());
            resultado = true;
        } catch (Exception e) {
            log.info(e.toString());
        }
        return resultado;
    }*/

    @Override
    public boolean removaUsuario (Usuario usuario) {
        Boolean resultado = false;
        try {
            usuarioRepository.delete(usuario);
            resultado = true;
        } catch (Exception e) {
            log.info(e.toString());
        }
        return resultado;
    }

    @Override
    public Optional<Usuario> busqueUsuarioPorId(Long id) {
        Optional<Usuario> colaborador = null;
        try {
            colaborador = usuarioRepository.findById(id);
        } catch (Exception e) {
            log.info(e.toString());
        }
        return colaborador;
    }

    @Override
    public Optional<Usuario> busqueUsuarioPorEmail(String email) {
        Optional<Usuario> colaborador = null;
        try {
            colaborador = usuarioRepository.findByEmail(email);
        } catch (Exception e) {
            log.info(e.toString());
        }
        return colaborador;
    }

    @Override
    public List<Usuario> listeUsuariosPorNome(String nome) {
        List<Usuario> colaboradores = null;
        try {
            colaboradores = usuarioRepository.findAllByNome(nome);
        } catch (Exception e) {
            log.info(e.toString());
        }
        return colaboradores;
    }

    @Override
    public List<Usuario> listaUsuariosPorEmpresa(Empresa empresa) {
        List<Usuario> usuarios = null;
        try {
            usuarios = usuarioRepository.findAllByPerfilIsNotAndEmpresa(Perfil.ROLE_ALUNO, empresa);
        } catch (Exception e) {
            log.info(e.toString());
        }
        return usuarios;
    }

    /*public Optional<Usuario> verifiqueCredenciaisColaborador(String email, String senhaE) {
        Optional<Usuario> colaborador = null;
        try {
            Optional<Usuario> colaboradorT = colaboradorRepository.findByEmail(email);
            if (colaboradorT.get().getSenha().equals(senhaE)) {
                colaborador = colaboradorT;
            } else {
                return null;
            }
        } catch (Exception e) {
            log.info(e.toString());
        }
        return colaborador;
    }*/


}

