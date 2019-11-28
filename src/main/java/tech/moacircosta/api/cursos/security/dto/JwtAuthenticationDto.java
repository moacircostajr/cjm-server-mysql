package tech.moacircosta.api.cursos.security.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class JwtAuthenticationDto {
	
	private String email;
	private String senha;

	public JwtAuthenticationDto() {
	}

	@NotEmpty
	@Email
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotEmpty
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "JwtAuthenticationRequestDto [email=" + email + ", senha=" + senha + "]";
	}

}
