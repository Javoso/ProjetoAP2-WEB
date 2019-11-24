package br.com.projetoWEB.util.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

public class FacesUtil {
	
	private FacesUtil(){}

	public static FacesUtil addMensagem() {
		return new FacesUtil();
	}

	private String idComponente;

	public FacesUtil info(String resumo) {
		info(resumo, "");
		return this;
	}

	public FacesUtil info(String resumo, String detalhe) {
		resumo(idComponente, resumo, detalhe, FacesMessage.SEVERITY_INFO);
		return this;
	}

	public FacesUtil warn(String id, String resumo, String detalhe) {
		resumo(id, resumo, detalhe, FacesMessage.SEVERITY_WARN);
		return this;
	}

	public FacesUtil warn(String resumo) {
		warn(resumo, "");
		return this;
	}

	public FacesUtil warn(String resumo, String detalhe) {
		warn(idComponente, resumo, detalhe);
		return this;
	}

	public FacesUtil fatal(String id, String resumo, String detalhe) {
		resumo(id, resumo, detalhe, FacesMessage.SEVERITY_FATAL);
		return this;
	}

	public FacesUtil error(String id, String resumo, String detalhe) {
		resumo(id, resumo, detalhe, FacesMessage.SEVERITY_ERROR);
		return this;
	}

	private FacesUtil resumo(String id, String resumo, String detalhe, Severity severity) {
		FacesContext.getCurrentInstance().addMessage(id, new FacesMessage(severity, resumo, detalhe));
		return this;
	}

	public FacesUtil para(String id) {
		this.idComponente = id;
		atualizarComponente(id);
		return this;
	}

	public static void atualizarComponente(String ids) {
		PrimeFaces.current().ajax().update(ids);
	}

	public void mantendoMensagemAposRedirect() {
		manterMensagem();
	}

	public static void manterMensagem() {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}

}
