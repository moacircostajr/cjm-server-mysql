package tech.moacircosta.api.cursos.security;

import java.util.ArrayList;
import java.util.List;

import tech.moacircosta.api.cursos.model.Aluno;
import tech.moacircosta.api.cursos.model.Perfil;
import tech.moacircosta.api.cursos.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


public class JwtUserFactory {

	private JwtUserFactory() {
	}

	/**
	 * Converte e gera um JwtUser com base nos dados de um aluno ou de um colaborador.
	 * 
	 * @param object
	 * @return JwtUser
	 */
	public static JwtUser create(Object object) {
		JwtUser jwtUser = null;
		if (object instanceof Aluno) {
			jwtUser =  new JwtUser(((Aluno) object).getId(), ((Aluno) object).getEmail(), ((Aluno) object).getSenha(),
					mapToGrantedAuthorities(((Aluno) object).getPerfil()));
		} else if (object instanceof Usuario) {
			jwtUser = new JwtUser(((Usuario) object).getId(), ((Usuario) object).getEmail(), ((Usuario) object).getSenha(),
					mapToGrantedAuthorities(((Usuario) object).getPerfil()));
		}
		return jwtUser;
	}

	/**
	 * Converte o perfil do usuário para o formato utilizado pelo Spring Security.
	 * 
	 * @param perfil
	 * @return List<GrantedAuthority>
	 */
	private static List<GrantedAuthority> mapToGrantedAuthorities(Perfil perfil) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(perfil.toString()));
		return authorities;
	}

}
