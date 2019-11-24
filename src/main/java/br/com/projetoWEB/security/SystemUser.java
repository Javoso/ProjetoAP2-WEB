package br.com.projetoWEB.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.com.projetoWEB.model.Usuario;

public class SystemUser extends User {

	private static final long serialVersionUID = -5531278404716119483L;
	
	private Usuario usuario;

	public SystemUser(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getEmail(), usuario.getSenha(), authorities);
		this.usuario = usuario;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

}
