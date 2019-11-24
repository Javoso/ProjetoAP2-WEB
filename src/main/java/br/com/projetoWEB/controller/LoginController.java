package br.com.projetoWEB.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.projetoWEB.util.jsf.FacesUtil;

@Named
@SessionScoped
public class LoginController implements Serializable {

	private static final long serialVersionUID = -2437633336322408321L;

	private String email;

	public void preRender() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		if ("true".equals(request.getParameter("invalid"))) {
			FacesUtil.addMensagem().error("", "", "Usuário ou senha inválido!");
		}
	}

	public void logar() throws ServletException, IOException {
		FacesUtil.addMensagem().info("Usuário logado com sucesso");
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		RequestDispatcher dispatcher = request.getRequestDispatcher("/j_spring_security_check");
		dispatcher.forward(request, response);
		FacesContext.getCurrentInstance().responseComplete();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
