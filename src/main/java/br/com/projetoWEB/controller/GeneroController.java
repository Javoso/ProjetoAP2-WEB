package br.com.projetoWEB.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.com.projetoWEB.dao.GenericDAO;
import br.com.projetoWEB.model.Genero;
import br.com.projetoWEB.model.enumerated.Sexo;
import br.com.projetoWEB.util.jsf.FacesUtil;

@Named
@ViewScoped
public class GeneroController implements Serializable {

	private static final long serialVersionUID = -2437633336322408321L;

	private Long idGenero;
	private Genero genero;

	@Inject
	private GenericDAO<Genero> daoGenero;

	public GeneroController() {
	}

	@PostConstruct
	public void iniciar() {
		this.genero = new Genero();
	}

	public void prepararParaEditar() {
		if (idGenero != null) {
			genero = daoGenero.findById(Genero.class, idGenero);
		}
	}

	public Sexo[] getSexos() {
		return Sexo.values();
	}

	public void cadastrar() {
		if (StringUtils.isNotBlank(this.genero.getNome())) {
			daoGenero.salvar(this.genero);
			FacesUtil.addMensagem().info("Cadastrado com sucesso!").para("msg");
		} else {
			FacesUtil.addMensagem().error("", "", "Erro ao concluir cadastrado").para("msg");
		}
		this.genero = new Genero();
	}

	public List<Genero> getGeneros() {
		return daoGenero.findAll(Genero.class);
	}

	public void excluir() {
		daoGenero.remover(Genero.class, genero.getId());
		FacesUtil.addMensagem().warn("Excluído com sucesso!").para("msg");
		this.genero = new Genero();

	}

	public String editar() {
		FacesUtil.addMensagem().info("Editado com sucesso!").para("msg").mantendoMensagemAposRedirect();
		daoGenero.editar(genero);
		genero = new Genero();
		return "/pages/exibir/generos?faces-redirect=true";
	}

	public Long getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(Long idGenero) {
		this.idGenero = idGenero;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}
}
