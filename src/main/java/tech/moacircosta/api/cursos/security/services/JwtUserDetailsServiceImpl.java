package tech.moacircosta.api.cursos.security.services;

import tech.moacircosta.api.cursos.model.Usuario;
import tech.moacircosta.api.cursos.service.UsuarioService;
import tech.moacircosta.api.cursos.security.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Usuario> usuario = usuarioService.busqueUsuarioPorEmail(username);
        if (usuario.isPresent()) {
            return JwtUserFactory.create(usuario.get());
        }

        throw new UsernameNotFoundException("Email " + username + " não encontrado.");
    }

}
