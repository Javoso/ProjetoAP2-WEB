package br.com.projetoWEB.controller;

import java.io.Serializable;
import java.util.Base64;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.FileUploadEvent;

import br.com.projetoWEB.dao.GenericDAO;
import br.com.projetoWEB.model.Ator;
import br.com.projetoWEB.model.enumerated.Sexo;
import br.com.projetoWEB.model.enumerated.Status;
import br.com.projetoWEB.util.jsf.FacesUtil;

@Named
@ViewScoped
public class AtorController implements Serializable {

	private static final long serialVersionUID = -2437633336322408321L;
	private static final String DATA_BASE64 = "data:image/png;base64,";

	private String base64;
	private Long idAtor;
	private Ator ator;

	@Inject
	private GenericDAO<Ator> daoAtor;

	public AtorController() {
	}

	@PostConstruct
	public void iniciar() {
		this.ator = new Ator();
	}

	public void prepararParaEditar() {
		if (idAtor != null) {
			ator = daoAtor.findById(Ator.class, idAtor);
		}
	}

	public List<Ator> atoresPorNome(String nome) {
		return daoAtor.findByAtributeList(Ator.class, nome, "nome");
	}

	public Sexo[] getSexos() {
		return Sexo.values();
	}

	public void cadastrar() {
		if (StringUtils.isNotBlank(this.ator.getNome()) && StringUtils.isNotBlank(base64)) {
			this.ator.setImagem(base64);
			this.ator.setStatus(Status.ATIVADO);
			daoAtor.salvar(this.ator);
			FacesUtil.addMensagem().info("Cadastrado com sucesso!").para("msg");
		} else {
			FacesUtil.addMensagem().error("", "", "Erro ao concluir cadastrado").para("msg");
		}
		this.ator = new Ator();
	}

	public List<Ator> getAtores() {
		return daoAtor.findByAtributeList(Ator.class, "status", Status.ATIVADO);
	}

	public void excluir() {
		this.ator.setStatus(Status.DESATIVADO);
		daoAtor.editar(ator);
		FacesUtil.addMensagem().warn("Excluído com sucesso!").para("msg");
		this.ator = new Ator();
	}

	public String editar() {
		if (StringUtils.isNotBlank(this.ator.getNome())) {
			if (StringUtils.isNotBlank(base64)) {
				this.ator.setImagem(base64);
			}
			FacesUtil.addMensagem().info("Editado com sucesso!").para("msg").mantendoMensagemAposRedirect();
			daoAtor.editar(ator);
			ator = new Ator();
		}
		return "/pages/exibir/atores?faces-redirect=true";
	}

	public void handleFileUpload(FileUploadEvent event) {
		this.base64 = getEncodedContent(event.getFile().getContents());
	}

	public String getEncodedContent(byte[] arquivo) {
		return DATA_BASE64.concat(Base64.getEncoder().encodeToString(arquivo));
	}

	public Long getIdAtor() {
		return idAtor;
	}

	public void setIdAtor(Long idAtor) {
		this.idAtor = idAtor;
	}

	public Ator getAtor() {
		return ator;
	}

	public void setAtor(Ator ator) {
		this.ator = ator;
	}
}
