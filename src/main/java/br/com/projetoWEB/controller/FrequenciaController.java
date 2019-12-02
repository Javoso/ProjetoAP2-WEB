package br.com.projetoWEB.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.projetoWEB.dao.GenericDAO;
import br.com.projetoWEB.model.FrequenciaAula;
import br.com.projetoWEB.model.Turma;
import br.com.projetoWEB.model.enumerated.Sexo;
import br.com.projetoWEB.util.jsf.FacesUtil;

@Named
@ViewScoped
public class FrequenciaController implements Serializable {

	private static final long serialVersionUID = -2437633336322408321L;

	private Long idFrequenciaAula;
	private FrequenciaAula frequenciaAula;

	List<Turma> turmas = new ArrayList<Turma>();

	@Inject
	private GenericDAO<FrequenciaAula> daoFrequenciaAula;

	@Inject
	private GenericDAO<Turma> daoTurma;

	public FrequenciaController() {
	}

	@PostConstruct
	public void iniciar() {
		this.frequenciaAula = new FrequenciaAula();
		this.turmas = daoTurma.findAll(Turma.class);
	}

	public void prepararParaEditar() {
		if (idFrequenciaAula != null) {
			frequenciaAula = daoFrequenciaAula.findById(FrequenciaAula.class, idFrequenciaAula);
		}
	}

	public Sexo[] getSexos() {
		return Sexo.values();
	}

	public void cadastrar() {
		if (!this.frequenciaAula.getAlunosFrequentes().isEmpty()) {
			daoFrequenciaAula.salvar(this.frequenciaAula);
			FacesUtil.addMensagem().info("Cadastrado com sucesso!").para("msg");
		} else {
			FacesUtil.addMensagem().error("", "", "Erro ao concluir cadastrado").para("msg");
		}
		this.frequenciaAula = new FrequenciaAula();
	}

	public List<FrequenciaAula> getFrequenciaAulas() {
		return daoFrequenciaAula.findAll(FrequenciaAula.class);
	}

	public void excluir() {
		daoFrequenciaAula.remover(FrequenciaAula.class, frequenciaAula.getId());
		FacesUtil.addMensagem().warn("Excluído com sucesso!").para("msg");
		this.frequenciaAula = new FrequenciaAula();

	}

	public String editar() {
		FacesUtil.addMensagem().info("Editado com sucesso!").para("msg").mantendoMensagemAposRedirect();
		daoFrequenciaAula.editar(frequenciaAula);
		frequenciaAula = new FrequenciaAula();
		return "/pages/exibir/frequenciaAulas?faces-redirect=true";
	}

	public Long getIdFrequenciaAula() {
		return idFrequenciaAula;
	}

	public void setIdFrequenciaAula(Long idFrequenciaAula) {
		this.idFrequenciaAula = idFrequenciaAula;
	}

	public FrequenciaAula getFrequenciaAula() {
		return frequenciaAula;
	}

	public void setFrequenciaAula(FrequenciaAula frequenciaAula) {
		this.frequenciaAula = frequenciaAula;
	}
}
