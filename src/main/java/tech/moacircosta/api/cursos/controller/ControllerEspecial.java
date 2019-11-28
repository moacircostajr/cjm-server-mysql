package tech.moacircosta.api.cursos.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.moacircosta.api.cursos.model.Empresa;
import tech.moacircosta.api.cursos.service.UsuarioService;
import tech.moacircosta.api.cursos.model.Perfil;
import tech.moacircosta.api.cursos.model.Usuario;
import tech.moacircosta.api.cursos.security.utils.PasswordUtils;
import tech.moacircosta.api.cursos.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/cursos")
@CrossOrigin(origins = "*")

public class ControllerEspecial {



    @Autowired
    EmpresaService empresaService;
    @Autowired
    UsuarioService usuarioService;

    private static final Logger log = LoggerFactory.getLogger(ControllerEspecial.class);



/* cria novo registro de administrador sem que seja necessário fazer login */



    @GetMapping(value = "/aiwauGi5ieZ8uvuej3h4g5j34h53ks")
    public @ResponseBody List<Usuario> novoAdmin() {
        log.info("Gerando novo admin");

        ArrayList<Usuario> listaUsuarios = new ArrayList<>();

        Empresa novaEmpresaCJM = new Empresa();
        Usuario novoUsuario1CJM = new Usuario();
        Usuario novoUsuario2CJM = new Usuario();
        Empresa novaEmpresaFP = new Empresa();
        Usuario novoUsuarioFP = new Usuario();

        novaEmpresaCJM.setNomeFantasia("Curso Jardem Moura");
        novaEmpresaCJM.setProprietario("Moacir Costa e Tenente Moura");
        novaEmpresaCJM.setTelefone("88-9-9755-6328, 85-9-9624-8800");
        novoUsuario1CJM.setEmpresa(novaEmpresaCJM);
        novoUsuario1CJM.setEmail("moacircostajr@gmail.com");
        novoUsuario1CJM.setSenha(PasswordUtils.gerarBCrypt("controlftp12"));
        novoUsuario1CJM.setNome("Moacir Costa");
        novoUsuario1CJM.setTelefone("088-9-9755-6328");
        novoUsuario1CJM.setPerfil(Perfil.ROLE_ADMIN);

        novoUsuario2CJM.setEmpresa(novaEmpresaCJM);
        novoUsuario2CJM.setEmail("jardem.f.moura@gmail.com");
        novoUsuario2CJM.setSenha(PasswordUtils.gerarBCrypt("4206"));
        novoUsuario2CJM.setNome("Jardem Moura");
        novoUsuario2CJM.setTelefone("085-9-9624-8800");
        novoUsuario2CJM.setPerfil(Perfil.ROLE_ADMIN);



        novaEmpresaFP.setNomeFantasia("Fechando Prova");
        novaEmpresaFP.setProprietario("Moacir Costa");
        novaEmpresaFP.setTelefone("88-9-9755-6328");
        novoUsuarioFP.setEmpresa(novaEmpresaFP);
        novoUsuarioFP.setEmail("moacir.jessika@gmail.com");
        novoUsuarioFP.setSenha(PasswordUtils.gerarBCrypt("controlftp12"));
        novoUsuarioFP.setNome("Moacir Costa");
        novoUsuarioFP.setTelefone("088-9-9755-6328");
        novoUsuarioFP.setPerfil(Perfil.ROLE_ADMIN);

        try {
            if (empresaService.registreEmpresa(novaEmpresaCJM)) {
                usuarioService.registreUsuario(novoUsuario1CJM);
                usuarioService.registreUsuario(novoUsuario2CJM);
            }
            listaUsuarios.add(novoUsuario1CJM);
            listaUsuarios.add(novoUsuario2CJM);
            if (empresaService.registreEmpresa(novaEmpresaFP)) {
                usuarioService.registreUsuario(novoUsuarioFP);
            }
            listaUsuarios.add(novoUsuarioFP);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listaUsuarios;
    }






}
