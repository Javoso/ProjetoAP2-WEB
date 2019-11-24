package br.com.projetoWEB.security;

import static java.util.Objects.nonNull;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import br.com.projetoWEB.model.Usuario;

@Named
@RequestScoped
public class UsuarioLogado {

	public Usuario getUsuarioLogado() {
		Usuario usuarioLogado = null;
		SystemUser user = getUserLogado();
		if (nonNull(user)) {
			usuarioLogado = user.getUsuario();
		}
		return usuarioLogado;
	}

	private SystemUser getUserLogado() {
		SystemUser user = null;
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) FacesContext
				.getCurrentInstance().getExternalContext().getUserPrincipal();
		if (nonNull(auth) && nonNull(auth.getPrincipal())) {
			user = (SystemUser) auth.getPrincipal();
		}
		return user;
	}

	public String doLogout() throws ServletException, IOException {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		RequestDispatcher dispatcher = ((ServletRequest) context.getRequest())
				.getRequestDispatcher("/j_spring_security_logout");
		dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
		FacesContext.getCurrentInstance().responseComplete();
		return null;
	}
	
	public boolean isAdmin() {
		return FacesContext.getCurrentInstance().getExternalContext().isUserInRole("ADMIN");
	}

}
