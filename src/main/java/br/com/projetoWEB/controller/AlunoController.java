package br.com.projetoWEB.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.com.projetoWEB.dao.GenericDAO;
import br.com.projetoWEB.model.Aluno;
import br.com.projetoWEB.model.enumerated.Sexo;
import br.com.projetoWEB.model.enumerated.Status;
import br.com.projetoWEB.util.jsf.FacesUtil;

@Named
@ViewScoped
public class AlunoController implements Serializable {

	private static final long serialVersionUID = -2437633336322408321L;
	
	private Long idAluno;
	private Aluno aluno;

	@Inject
	private GenericDAO<Aluno> daoAluno;

	public AlunoController() {
	}

	@PostConstruct
	public void iniciar() {
		this.aluno = new Aluno();
	}

	public void prepararParaEditar() {
		if (idAluno != null) {
			aluno = daoAluno.findById(Aluno.class, idAluno);
		}
	}

	public List<Aluno> alunoesPorNome(String nome) {
		return daoAluno.findByAtributeList(Aluno.class, nome, "nomeAluno");
	}

	public Sexo[] getSexos() {
		return Sexo.values();
	}

	public void cadastrar() {
		if (StringUtils.isNotBlank(this.aluno.getNomeAluno()) && StringUtils.isNotBlank(this.aluno.getNomeResponsavel())) {
			this.aluno.setStatus(Status.ATIVADO);
			daoAluno.salvar(this.aluno);
			FacesUtil.addMensagem().info("Cadastrado com sucesso!").para("msg");
		} else {
			FacesUtil.addMensagem().error("", "", "Erro ao concluir cadastrado").para("msg");
		}
		this.aluno = new Aluno();
	}

	public List<Aluno> getAlunoes() {
		return daoAluno.findByAtributeList(Aluno.class, "status", Status.ATIVADO);
	}

	public void excluir() {
		this.aluno.setStatus(Status.DESATIVADO);
		daoAluno.editar(aluno);
		FacesUtil.addMensagem().warn("Excluído com sucesso!").para("msg");
		this.aluno = new Aluno();
	}

	public String editar() {
		if (StringUtils.isNotBlank(this.aluno.getNomeAluno())) {
			FacesUtil.addMensagem().info("Editado com sucesso!").para("msg").mantendoMensagemAposRedirect();
			daoAluno.editar(aluno);
			aluno = new Aluno();
		}
		return "/pages/exibir/alunos?faces-redirect=true";
	}
	
	public Long getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
}
