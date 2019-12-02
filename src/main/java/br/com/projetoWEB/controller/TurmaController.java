package br.com.projetoWEB.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.com.projetoWEB.dao.GenericDAO;
import br.com.projetoWEB.model.Turma;
import br.com.projetoWEB.util.jsf.FacesUtil;

@Named
@ViewScoped
public class TurmaController implements Serializable {

	private static final long serialVersionUID = -2437633336322408321L;
	
	private Long idTurma;
	private Turma turma;
	
	@Inject
	private GenericDAO<Turma> daoTurma;

	public TurmaController() {
	}

	@PostConstruct
	public void iniciar() {
		this.turma = new Turma();
	}

	public void prepararParaEditar() {
		if (idTurma != null) {
			turma = daoTurma.findById(Turma.class, idTurma);
		}
	}

	public void cadastrar() {
		if (StringUtils.isNotBlank(this.turma.getNomeDaTurma())) {
			daoTurma.salvar(this.turma);
			FacesUtil.addMensagem().info("Cadastrado com sucesso!").para("msg");
		} else {
			FacesUtil.addMensagem().error("", "", "Erro ao concluir cadastrado").para("msg");
		}
		this.turma = new Turma();
	}
	
	public List<Turma> getTurmas() {
		return daoTurma.findAll(Turma.class);
	}

	public void excluir() {
		daoTurma.remover(Turma.class, turma.getId());
		FacesUtil.addMensagem().warn("Excluído com sucesso!").para("msg");
		this.turma = new Turma();

	}

	public String editar() {
		if (StringUtils.isNotBlank(this.turma.getNomeDaTurma())) {
			FacesUtil.addMensagem().info("Editado com sucesso!").para("msg").mantendoMensagemAposRedirect();
			daoTurma.editar(turma);
			turma = new Turma();
		}
		return "/pages/exibir/turmas?faces-redirect=true";
	}

	public Long getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(Long idAtor) {
		this.idTurma = idAtor;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

}
