package br.com.projetoWEB.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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
import br.com.projetoWEB.model.Filme;
import br.com.projetoWEB.util.jsf.FacesUtil;

@Named
@ViewScoped
public class FilmeController implements Serializable {

	private static final long serialVersionUID = -2437633336322408321L;
	private static final String DATA_BASE64 = "data:image/png;base64,";

	private Long idFilme;
	private Filme filme;
	private List<String> atores = new ArrayList<>();
	private List<String> atoresComplet = new ArrayList<>();
	private Ator atorMostrar;
	private String base64;

	@Inject
	private GenericDAO<Filme> daoFilme;

	public FilmeController() {
	}

	@PostConstruct
	public void iniciar() {
		this.filme = new Filme();
		this.atorMostrar = new Ator();
	}

	public void prepararParaEditar() {
		if (idFilme != null) {
			filme = daoFilme.findById(Filme.class, idFilme);
		}
	}

	public void cadastrar() {
		if (StringUtils.isNotBlank(this.filme.getNome()) && StringUtils.isNotBlank(base64)) {
			this.filme.setImagem(base64);
			daoFilme.salvar(this.filme);
			FacesUtil.addMensagem().info("Cadastrado com sucesso!").para("msg");
		} else {
			FacesUtil.addMensagem().error("", "", "Erro ao concluir cadastrado").para("msg");
		}
		this.filme = new Filme();
		this.atores = new ArrayList<>();
	}

	public void handleFileUpload(FileUploadEvent event) {
		event.getFile().getContentType();
		this.base64 = getEncodedContent(event.getFile().getContents());
	}

	public String getEncodedContent(byte[] arquivo) {
		return DATA_BASE64.concat(Base64.getEncoder().encodeToString(arquivo));
	}

	public List<Filme> getFilmes() {
		return daoFilme.findAll(Filme.class);
	}

	public void excluir() {
		daoFilme.remover(Filme.class, filme.getId());
		FacesUtil.addMensagem().warn("Excluído com sucesso!").para("msg");
		this.filme = new Filme();

	}

	public String editar() {
		if (StringUtils.isNotBlank(this.filme.getNome())) {
			if (StringUtils.isNotBlank(base64)) {
				this.filme.setImagem(base64);
			}
			FacesUtil.addMensagem().info("Editado com sucesso!").para("msg").mantendoMensagemAposRedirect();
			daoFilme.editar(filme);
			filme = new Filme();
		}
		return "/pages/exibir/filmes?faces-redirect=true";
	}

	public Long getIdFilme() {
		return idFilme;
	}

	public void setIdFilme(Long idAtor) {
		this.idFilme = idAtor;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	public List<String> getAtores() {
		return atores;
	}

	public void setAtores(List<String> atores) {
		this.atores = atores;
	}

	public List<String> getAtoresComplet() {
		return atoresComplet;
	}

	public void setAtoresComplet(List<String> atoresComplet) {
		this.atoresComplet = atoresComplet;
	}

	public Ator getAtorMostrar() {
		return atorMostrar;
	}

	public void setAtorMostrar(Ator atorMostrar) {
		this.atorMostrar = atorMostrar;
	}
}
