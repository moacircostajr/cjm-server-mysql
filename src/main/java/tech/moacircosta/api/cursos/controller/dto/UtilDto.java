package tech.moacircosta.api.cursos.controller.dto;

import tech.moacircosta.api.cursos.model.*;
import tech.moacircosta.api.cursos.repository.Util;
import tech.moacircosta.api.cursos.security.utils.PasswordUtils;

import java.io.UnsupportedEncodingException;

public class UtilDto {

    public AlunoDto converterAlunoPAlunoDto(Aluno aluno) {
        AlunoDto alunoDto = new AlunoDto();
        alunoDto.setEmpresa(aluno.getEmpresa());
        alunoDto.setId(aluno.getId());
        alunoDto.setEmail(aluno.getEmail());
        alunoDto.setNome(aluno.getNome());
        alunoDto.setEndereco(aluno.getEndereco());
        alunoDto.setBairro(aluno.getBairro());
        alunoDto.setCidade(aluno.getCidade());
        alunoDto.setEstado(aluno.getEstado());
        alunoDto.setTelefone(aluno.getTelefone());
        alunoDto.setPontos(aluno.getPontos());
        alunoDto.setQuestoes(aluno.getQuestoes());
        alunoDto.setAcertos(aluno.getAcertos());
        alunoDto.setErros(aluno.getErros());
        alunoDto.setPerfil(aluno.getPerfil());
        return alunoDto;
    }


    public Aluno converterAlunoDtoPAluno(AlunoDto alunoDto) {
        Aluno aluno = new Aluno();
        aluno.setId(alunoDto.getId());
        aluno.setEmpresa(alunoDto.getEmpresa());
        aluno.setEmail(alunoDto.getEmail());
        aluno.setSenha(PasswordUtils.gerarBCrypt(alunoDto.getSenha()));
        aluno.setNome(alunoDto.getNome());
        aluno.setEndereco(alunoDto.getEndereco());
        aluno.setBairro(alunoDto.getBairro());
        aluno.setCidade(alunoDto.getCidade());
        aluno.setEstado(alunoDto.getEstado());
        aluno.setTelefone(alunoDto.getTelefone());
        aluno.setPontos(alunoDto.getPontos());
        aluno.setQuestoes(alunoDto.getQuestoes());
        aluno.setAcertos(alunoDto.getAcertos());
        aluno.setErros(alunoDto.getErros());
        aluno.setPerfil(alunoDto.getPerfil());
        return aluno;
    }


    public UsuarioDto converterUsuarioPUsuarioDto(Usuario usuario) {
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(usuario.getId());
        usuarioDto.setEmpresa(usuario.getEmpresa());
        usuarioDto.setEmail(usuario.getEmail());
        usuarioDto.setNome(usuario.getNome());
        usuarioDto.setTelefone(usuario.getTelefone());
        usuarioDto.setPerfil(usuario.getPerfil());
        return usuarioDto;
    }

    public Usuario converterUsuarioDtoPUsuario(UsuarioDto usuarioDto) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDto.getId());
        usuario.setEmpresa(usuarioDto.getEmpresa());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setSenha(usuarioDto.getSenha());
        usuario.setPerfil(usuarioDto.getPerfil());
        usuario.setTelefone(usuarioDto.getTelefone());
        usuario.setNome(usuarioDto.getNome());
        return usuario;
    }

    public Exercicio converterExercicioDtoPExercicio(ExercicioDto exercicioDto) throws UnsupportedEncodingException {
        Exercicio exercicio = new Exercicio();
        exercicio.setId(exercicioDto.getId());
        exercicio.setEmpresa(exercicioDto.getEmpresa());
        exercicio.setEnunciado1(exercicioDto.getEnunciado1());
        if (exercicioDto.getImagemEnunciado() != null) {
            exercicio.setImagemEnunciado(Util.decodificar(exercicioDto.getImagemEnunciado()));
        }
        exercicio.setEnunciado2(exercicioDto.getEnunciado2());
        exercicio.setOpcoes(exercicioDto.getOpcoes());
        exercicio.setGabaritoObjetivo(exercicioDto.getGabaritoObjetivo());
        exercicio.setGabaritoSubjetivo(exercicioDto.getGabaritoSubjetivo());
        exercicio.setPontuacao(exercicioDto.getPontuacao());
        exercicio.setMaterias(exercicioDto.getMaterias());
        exercicio.setBancas(exercicioDto.getBancas());
        exercicio.setConcursos(exercicioDto.getConcursos());
        exercicio.setAnos(exercicioDto.getAnos());
        return exercicio;
    }

    public ExercicioDto converterExercicioPExercicioDto(Exercicio exercicioB) {
        ExercicioDto exercicioDtoB = new ExercicioDto();
        exercicioDtoB.setId(exercicioB.getId());
        exercicioDtoB.setEmpresa(exercicioB.getEmpresa());
        exercicioDtoB.setEnunciado1(exercicioB.getEnunciado1());
        if (exercicioB.getImagemEnunciado() != null) {
            exercicioDtoB.setImagemEnunciado(Util.codificar(exercicioB.getImagemEnunciado()));
        }
        exercicioDtoB.setEnunciado2(exercicioB.getEnunciado2());
        exercicioDtoB.setOpcoes(exercicioB.getOpcoes());
        exercicioDtoB.setGabaritoObjetivo(exercicioB.getGabaritoObjetivo());
        exercicioDtoB.setGabaritoSubjetivo(exercicioB.getGabaritoSubjetivo());
        exercicioDtoB.setPontuacao(exercicioB.getPontuacao());
        exercicioDtoB.setMaterias(exercicioB.getMaterias());
        exercicioDtoB.setBancas(exercicioB.getBancas());
        exercicioDtoB.setConcursos(exercicioB.getConcursos());
        exercicioDtoB.setAnos(exercicioB.getAnos());
        exercicioDtoB.setComentarios(exercicioB.getComentarios());
        exercicioDtoB.setDataAtualizacao(exercicioB.getDataAtualizacao());
        exercicioDtoB.setDataCriacao(exercicioB.getDataCriacao());
        return exercicioDtoB;
    }

    public Empresa converterEmpresaDtoPEmpresa(EmpresaDto empresaDto) {
        Empresa empresa = new Empresa();
        empresa.setId(empresaDto.getId());
        empresa.setNomeFantasia(empresaDto.getNomeFantasia());
        empresa.setCnpj(empresaDto.getCnpj());
        empresa.setProprietario(empresaDto.getProprietario());
        empresa.setEndereco(empresaDto.getEndereco());
        empresa.setTelefone(empresaDto.getTelefone());
        return empresa;
    }

    public ClassificacaoPadrao converterClassificacaoPadraoDtoPClassificacaoPadrao(ClassificacaoPadraoDto classificacaoPadraoDto) {
        ClassificacaoPadrao classificacaoPadrao = new ClassificacaoPadrao();
        classificacaoPadrao.setId(classificacaoPadraoDto.getId());
        classificacaoPadrao.setEmpresa(classificacaoPadraoDto.getEmpresa());
        classificacaoPadrao.setClassificacao(classificacaoPadraoDto.getClassificacao());
        return classificacaoPadrao;
    }

    public BancasPadrao converterBancasPadraoDtoPBancasPadrao(BancasPadraoDto bancasPadraoDto) {
        BancasPadrao bancasPadrao = new BancasPadrao();
        bancasPadrao.setId(bancasPadraoDto.getId());
        bancasPadrao.setEmpresa(bancasPadraoDto.getEmpresa());
        bancasPadrao.setBancas(bancasPadraoDto.getBancas());
        return bancasPadrao;
    }

    public ConcursosPadrao converterConcursosPadraoDtoPConcursosPadrao(ConcursosPadraoDto concursosPadraoDto) {
        ConcursosPadrao concursosPadrao = new ConcursosPadrao();
        concursosPadrao.setId(concursosPadraoDto.getId());
        concursosPadrao.setEmpresa(concursosPadraoDto.getEmpresa());
        concursosPadrao.setConcursos(concursosPadraoDto.getConcursos());
        return concursosPadrao;
    }
}
