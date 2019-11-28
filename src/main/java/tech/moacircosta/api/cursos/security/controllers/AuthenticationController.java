package tech.moacircosta.api.cursos.security.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import tech.moacircosta.api.cursos.controller.dto.UsuarioDto;
import tech.moacircosta.api.cursos.model.Usuario;
import tech.moacircosta.api.cursos.security.utils.JwtTokenUtil;
import tech.moacircosta.api.cursos.security.utils.PasswordUtils;
import tech.moacircosta.api.cursos.service.UsuarioService;
import tech.moacircosta.api.cursos.controller.dto.UtilDto;
import tech.moacircosta.api.cursos.security.services.JwtUserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import tech.moacircosta.api.cursos.security.response.Response;
import tech.moacircosta.api.cursos.security.dto.JwtAuthenticationDto;
import tech.moacircosta.api.cursos.security.dto.TokenDto;

@Controller
@RequestMapping("/api/cursos/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {

	private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);
	private static final String TOKEN_HEADER = "Authorization";
	private static final String BEARER_PREFIX = "Bearer ";


	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsServiceImpl userDetailsService;

	@Autowired
	private UsuarioService usuarioService;


	/**
	 * Gera e retorna um novo autenticacao JWT.
	 * 
	 * @param authenticationDto
	 * @param result
	 * @return ResponseEntity<Response<TokenDto>>
	 * @throws AuthenticationException
	 */
	@PostMapping
	public ResponseEntity<Response> gerarTokenJwt(
			@Valid @RequestBody JwtAuthenticationDto authenticationDto, BindingResult result)
			throws AuthenticationException {

		if (result.hasErrors()) {
			log.error("Erro validando lançamento: {}", result.getAllErrors());
			return null;
		}

		log.info("Gerando autenticacao para o email {}.", authenticationDto.getEmail());
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationDto.getEmail(), authenticationDto.getSenha()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		/* caso não haja a autenticação, os comandos seguintes não serão executados */
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationDto.getEmail());
		Response<Object> response = new Response<>();
        Optional<Usuario> usuario = this.usuarioService.busqueUsuarioPorEmail(authenticationDto.getEmail());
        UsuarioDto usuarioDto = new UtilDto().converterUsuarioPUsuarioDto(usuario.get());
        String token = jwtTokenUtil.obterToken(userDetails);
        usuarioDto.setAutenticacao(new TokenDto(token));
        response.setData(usuarioDto);
        return ResponseEntity.ok(response);
	}

	/**
	 * Gera um novo autenticacao com uma nova data de expiração.
	 * 
	 * @param request
	 * @return ResponseEntity<Response<TokenDto>>
	 */
	@PostMapping(value = "/refresh")
	public ResponseEntity<Response<TokenDto>> gerarRefreshTokenJwt(HttpServletRequest request) {
		log.info("Gerando refresh autenticacao JWT.");
		Response<TokenDto> response = new Response<TokenDto>();
		Optional<String> token = Optional.ofNullable(request.getHeader(TOKEN_HEADER));
		
		if (token.isPresent() && token.get().startsWith(BEARER_PREFIX)) {
			token = Optional.of(token.get().substring(7));
        }
		
		if (!token.isPresent()) {
			response.getErrors().add("Token não informado.");
		} else if (!jwtTokenUtil.tokenValido(token.get())) {
			response.getErrors().add("Token inválido ou expirado.");
		}
		
		if (!response.getErrors().isEmpty()) { 
			return ResponseEntity.badRequest().body(response);
		}
		
		String refreshedToken = jwtTokenUtil.refreshToken(token.get());
		response.setData(new TokenDto(refreshedToken));
		return ResponseEntity.ok(response);
	}


}
