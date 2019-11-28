package tech.moacircosta.api.cursos.controller;

import tech.moacircosta.api.cursos.controller.dto.*;
import tech.moacircosta.api.cursos.model.*;
import tech.moacircosta.api.cursos.service.*;
import tech.moacircosta.api.cursos.security.utils.PasswordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/api/cursos")
public class ControllerGeral {

    private static final Logger log = LoggerFactory.getLogger(ControllerGeral.class);

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    ExercicioService exercicioService;
    @Autowired
    BancasPadraoService bancasPadraoService;
    @Autowired
    ConcursosPadraoService concursosPadraoService;
    @Autowired
    ClassificacaoPadraoService classificacaoPadraoService;

    UtilDto utilDto = new UtilDto();

    /*@Autowired
    RedacaoServiceImpl redacaoService;*/


    public ControllerGeral() {    }



    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/usuario")
    public @ResponseBody Integer registrarUsuario(@RequestBody UsuarioDto usuarioDto) {
        Integer resposta = null;
        usuarioDto.setSenha(PasswordUtils.gerarBCrypt(usuarioDto.getSenha()));
        try {
            Usuario usuario = utilDto.converterUsuarioDtoPUsuario(usuarioDto);
            if (usuarioService.busqueUsuarioPorEmail(usuario.getEmail()).isEmpty()) {
                Boolean resultado = usuarioService.registreUsuario(usuario);
                log.info("Resultado do registro de novo usuario: " + resultado + " " + usuario.getEmail());
                if (resultado==true) { resposta = 201; };
            } else {
                log.info("Tentativa de registro com email duplicado: " + usuarioDto.getEmail());
                resposta = 409;
            }
        } catch (Exception e) {
            log.info("Erro ao efetuar registro do usuario: " + usuarioDto.getEmail());
            log.info(e.toString());
            resposta = 500;
        }
        return resposta;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/usuario/{id_usuario}")
    public @ResponseBody Boolean atualizarUsuario(@PathVariable("id_usuario") Long idUsuario, @RequestBody UsuarioDto usuarioDto) {
        Boolean resultado = false;
        try {
            usuarioDto.setSenha(PasswordUtils.gerarBCrypt(usuarioDto.getSenha()));
            usuarioService.atualizeUsuario(utilDto.converterUsuarioDtoPUsuario(usuarioDto));
            resultado = true;
            log.info("Usuario registrado: " + usuarioDto.getEmail());
        } catch (Exception e) {
            log.info("Erro ao efetuar registro do usuario: " + usuarioDto.getEmail());
        }
        return resultado;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/usuarios")
    public @ResponseBody List<Usuario> listarUsuarios(@Valid @RequestBody EmpresaDto empresaDto) {
        List<Usuario> usuarios = usuarioService.listaUsuariosPorEmpresa(utilDto.converterEmpresaDtoPEmpresa(empresaDto));
        if (usuarios.isEmpty()) {
            log.info("Erro ao listar usuarios");
        } else {
            log.info("Usuarios listados");
        }
        return usuarios;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/usuario/{id_usuario}")
    public @ResponseBody Optional<Usuario> buscarUsuario(@PathVariable("id_usuario") Long id) {
        Optional<Usuario> usuario = usuarioService.busqueUsuarioPorId(id);
        if (usuario.isPresent() == false) {
            log.info("Erro ao obter usuario");
        } else {
            log.info("Dados do usuario enviados");
        }
        return usuario;
    }

    /*@PostMapping(value = "/aluno/inscricao")
    public @ResponseBody Boolean matricular(@Valid @RequestBody AlunoDto alunoDto) {
        Boolean resultado = false;
        alunoDto.setPerfil(Perfil.ROLE_ALUNO);
        Aluno aluno = new UtilDto().converterAlunoDtoPAluno(alunoDto);
        if (usuarioService.busqueAlunoPorEmail(aluno.getEmail()).isEmpty()) {
            resultado = usuarioService.registreAluno(new UtilDto().converterAlunoDtoPAluno(alunoDto));
        }
        if (resultado == false) {
            log.info("Erro ao efetuar registro do aluno: " + alunoDto.getEmail());
        } else {
            log.info("Aluno registrado: " + alunoDto.getEmail());
        }
        return resultado;
    }*/

    @PostMapping(value = "/aluno/inscricao")
    public @ResponseBody Integer matricular(@Valid @RequestBody AlunoDto alunoDto) {
        Integer resposta = null;
        try {
            alunoDto.setPerfil(Perfil.ROLE_ALUNO);
            Aluno aluno = new UtilDto().converterAlunoDtoPAluno(alunoDto);
            if (usuarioService.busqueAlunoPorEmail(aluno.getEmail()).isEmpty()) {
                Boolean resultado = usuarioService.registreAluno(aluno);
                log.info("Resultado do registro de novo aluno: " + resultado + " " + alunoDto.getEmail());
                if (resultado==true) { resposta = 201; };
            } else {
                log.info("Tentativa de registro com email duplicado: " + alunoDto.getEmail());
                resposta = 409;
            }
        } catch (Exception e) {
            log.info("Erro ao efetuar registro do aluno: " + alunoDto.getEmail());
            log.info(e.toString());
            resposta = 500;
        }
        return resposta;
    }


    /*
    @PostMapping(value = "/{id_aluno}/atualizar")
    public @ResponseBody Boolean atualizarAluno(@PathVariable("id_aluno") UUID idAluno, @RequestBody Aluno aluno) {
        Boolean resultado = usuarioService.registreAluno(aluno);
        if (resultado == false) {
            log.info("Erro ao atualizar o aluno: " + idAluno);
        } else {
            log.info("Aluno atualizado: " + idAluno);
        }
        return resultado;
    }
*/
    @GetMapping(value = "/aluno/{id_aluno}")
    public @ResponseBody AlunoDto buscarAluno(@PathVariable("id_aluno") Long idAluno) {
        Optional<Aluno> resultado = usuarioService.busqueAlunoPorId(idAluno);
        AlunoDto alunoDto = null;
        if (resultado.isPresent() == false) {
            log.info("Erro ao acssar o aluno: " + idAluno);
        } else {
            log.info("Dados enviados para o aluno: " + idAluno);
            alunoDto = utilDto.converterAlunoPAlunoDto(resultado.get());
        }
        return alunoDto;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/alunos")
    public @ResponseBody List<Aluno> listarAlunos(@Valid @RequestBody EmpresaDto empresaDto) {
        List<Aluno> alunos = usuarioService.listeAlunosPorEmpresa(utilDto.converterEmpresaDtoPEmpresa(empresaDto));
        if (alunos.isEmpty()) {
            log.info("Erro ao listar alunos da empresa: " + empresaDto.getNomeFantasia());
        } else {
            log.info("Usuarios listados para: " + empresaDto.getNomeFantasia());
        }
        return alunos;
    }

    @PostMapping(value="/aluno/{id_aluno}/pontuacao")
    public @ResponseBody Boolean atualizarPontuacao(@PathVariable("id_aluno") Long idAluno, @Valid @RequestBody PontuacaoDto pontuacaoDto) {
        Boolean resultado = usuarioService.atualizePontosAluno(pontuacaoDto.getPontos(), pontuacaoDto.getQuestoes(), pontuacaoDto.getAcertos(), pontuacaoDto.getErros(), idAluno);
        if (resultado == false) {
            log.info("Erro ao atualizar a pontuação do aluno: " + idAluno);
        } else {
            log.info("Atualizada a pontuação do aluno: " + idAluno);
        }
        return resultado;
    }

    /*TODO: atualizar ranking*/
    @GetMapping(value="/ranking")
    public @ResponseBody Page<Aluno> buscarRanking(@Valid @RequestBody EmpresaDto empresaDto) {
        Page<Aluno> alunosTop = usuarioService.busqueRanking(utilDto.converterEmpresaDtoPEmpresa(empresaDto));
        if (alunosTop.isEmpty()) {
            log.info("Erro ao atualizar a pontuação do aluno.");
        } else {
            log.info("Ranking informado com sucesso.");
        }
        return alunosTop;
    }

    /*@PostMapping(value = "/{id_aluno}/redacoes/enviar")
    public @ResponseBody Boolean receberRedacao(@PathVariable("id_aluno") UUID idAluno, @RequestBody Redacao redacao) {
        Boolean resultado = redacaoService.registreRedacao(redacao);
        if (resultado == false) {
            log.info("Erro ao efetuar registro da redação do aluno: " + idAluno);
        } else {
            log.info("Registrada a redação do aluno: " + idAluno);
        }
        return resultado;
    }

    @GetMapping(value = "/{id_aluno}/redacoes/buscar")
    public @ResponseBody List<Redacao> buscarRedacoesDoAluno(@PathVariable("id_aluno") UUID idAluno) {
        List<Redacao> redacoes = redacaoService.busqueRedacoesPorIdAluno(idAluno);
        if (redacoes.size() == 0) {
            log.info("Não foi encontrada nenhuma redação para o aluno: " + idAluno);
        } else {
            log.info("Redacões entregues ao aluno: " + idAluno);
        }
        return redacoes;
    }

    @GetMapping(value = "{/id_aluno}/redacao/buscar/{id_redacao}")
    public @ResponseBody Redacao buscarRedacao(@PathVariable("id_aluno") UUID idAluno, @PathVariable("id_redacao") UUID idRedacao) {
        Redacao redacao = redacaoService.busqueRedacaoPorId(idRedacao);
        if (redacao == null) {
            log.info("Erro ao entregar a redação solicitada pelo aluno: " + idAluno);
        } else {
            log.info("Redacão entregue ao aluno: " + idAluno);
        }
        return redacao;
    }*/


    @PostMapping(value = "/exercicios?materia={p_materia}&banca={p_banca}&concurso={p_concurso}&ano={p_ano}")
    public @ResponseBody List<Exercicio> buscarExercicios(
            @RequestParam String p_materia,
            @RequestParam String p_banca,
            @RequestParam String p_concurso,
            @RequestParam Integer p_ano,
            @Valid @RequestBody EmpresaDto empresaDto
        ) {
        List<Exercicio> resultado = exercicioService.busquePorEmpresaEMateriaEBancaEConcursoEAno(utilDto.converterEmpresaDtoPEmpresa(empresaDto), p_materia, p_banca, p_concurso, p_ano);
        if (resultado == null) {
            log.info("Erro ao efetuar a busca de exercicios");
        } else {
            log.info("Lista de exercícios entregues ao aluno");
        }
        return resultado;
    }



    @PostMapping(value="/padrao_classificacao")
    public @ResponseBody
    Optional<ClassificacaoPadrao> acessarPadroesClassificacao(@Valid @RequestBody EmpresaDto empresaDto) {
        Optional<ClassificacaoPadrao> classificacaoPadrao = classificacaoPadraoService.busqueClassificacao(utilDto.converterEmpresaDtoPEmpresa(empresaDto));
        if (classificacaoPadrao.isPresent() == false) {
            log.info("Erro ao acessar classificação padrão");
        } else {
            log.info("Classificação padrão enviada");
        }
        return classificacaoPadrao;
    }

    @PostMapping(value="/padrao_bancas")
    public @ResponseBody Optional<BancasPadrao> acessarPadroesBancas(@Valid @RequestBody EmpresaDto empresaDto) {
        Optional<BancasPadrao> bancasPadrao = bancasPadraoService.busqueBancas(utilDto.converterEmpresaDtoPEmpresa(empresaDto));
        if (bancasPadrao.isPresent() == false) {
            log.info("Erro ao acessar bancas padrão.");
        } else {
            log.info("Bancas padrão enviadas");
        }
        return bancasPadrao;
    }

    @PostMapping(value="/padrao_concursos")
    public @ResponseBody Optional<ConcursosPadrao> acessarPadroesConcursos(@Valid @RequestBody EmpresaDto empresaDto) {
        Optional<ConcursosPadrao> concursosPadrao = concursosPadraoService.busqueConcursos(utilDto.converterEmpresaDtoPEmpresa(empresaDto));
        if (concursosPadrao.isPresent() == false) {
            log.info("Erro ao acessar concursos padrão");
        } else {
            log.info("Concursos padrão enviados");
        }
        return concursosPadrao;
    }


    /*TODO: comentar exercicio*/
    /*TODO: fazer exercicios FRONTEND*/

    /*TODO: na pagina inicial, todos os alunos poderão ver o ranking dos 10 mais pontuados*/


    @PreAuthorize("hasRole('ADMIN') or hasHole('OPERADOR') or hasHole('PROFESSOR')")
    @PostMapping(value = "/registrar/exercicio")
    public @ResponseBody Boolean registrarExercicio(@Valid @RequestBody ExercicioDto exercicioDto) throws UnsupportedEncodingException {
        Boolean resultado = exercicioService.registreExercicio(utilDto.converterExercicioDtoPExercicio(exercicioDto));
        if (resultado == false) {
            log.info("Erro ao efetuar registro do exercicio.");
        } else {
            log.info("Exercicio registrado.");
        }
        return resultado;
    }

    @PostMapping(value="/exercicios")
    public @ResponseBody List<Exercicio> listarExercicios(@Valid @RequestBody EmpresaDto empresaDto) {
        List<Exercicio> resultado = exercicioService.listeTodosPorEmpresa(utilDto.converterEmpresaDtoPEmpresa(empresaDto));
        if (resultado == null) {
            log.info("Erro ao efetuar listagem de exercicios.");
        } else {
            log.info("Exercicios listados.");
        }
        return resultado;
    }

    @GetMapping(value="/exercicio/{id}")
    public @ResponseBody
    Optional<ExercicioDto> acessarExercicio(@PathVariable("id") Long idExercicio) {
        Optional<Exercicio> exercicio = exercicioService.busquePorId(idExercicio);
        Optional<ExercicioDto> resultado = Optional.of(utilDto.converterExercicioPExercicioDto(exercicio.get()));
        if (resultado.isPresent() == false) {
            log.info("Erro ao acessar exercicio.");
        } else {
            log.info("Exercicio entregue.");
        }
        return resultado;
    }


    @PreAuthorize("hasRole('ADMIN') or hasHole('OPERADOR') or hasHole('PROFESSOR')")
    @PostMapping(value="/registrar/classificacao")
    public @ResponseBody Boolean registrarPadroesClassificacao(@Valid @RequestBody ClassificacaoPadraoDto classificacaoPadraoDto) {
        Boolean resultado = classificacaoPadraoService.registreClassificacao(utilDto.converterClassificacaoPadraoDtoPClassificacaoPadrao(classificacaoPadraoDto));
        if (resultado == false) {
            log.info("Erro ao efetuar registro dos padrões de classificação.");
        } else {
            log.info("Padrões de classificação registrados.");
        }
        return resultado;
    }

    @PreAuthorize("hasRole('ADMIN') or hasHole('OPERADOR') or hasHole('PROFESSOR')")
    @PostMapping(value="/registrar/bancas")
    public @ResponseBody Boolean registrarPadroesBancas(@Valid @RequestBody BancasPadraoDto bancasPadraoDto) {
        Boolean resultado = bancasPadraoService.registreBancas(utilDto.converterBancasPadraoDtoPBancasPadrao(bancasPadraoDto));
        if (resultado == false) {
            log.info("Erro ao efetuar registro dos padrões de bancas.");
        } else {
            log.info("Padrões de bancas registrados.");
        }
        return resultado;
    }

    @PreAuthorize("hasRole('ADMIN') or hasHole('OPERADOR') or hasHole('PROFESSOR')")
    @PostMapping(value="/registrar/concursos")
    public @ResponseBody Boolean registrarPadroesConcursos(@Valid @RequestBody ConcursosPadraoDto concursosPadraoDto) {
        Boolean resultado = concursosPadraoService.registreConcursos(utilDto.converterConcursosPadraoDtoPConcursosPadrao(concursosPadraoDto));
        if (resultado == false) {
            log.info("Erro ao efetuar registro dos padrões de concursos.");
        } else {
            log.info("Padrões de concursos registrados.");
        }
        return resultado;
    }


    /*TODO: acessar redacoes para correção*/

    /*TODO: acessar progresso do aluno*/

}
