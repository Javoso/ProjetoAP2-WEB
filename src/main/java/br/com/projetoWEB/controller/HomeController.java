package br.com.projetoWEB.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.projetoWEB.dao.GenericDAO;
import br.com.projetoWEB.model.Aluno;
import br.com.projetoWEB.model.Turma;
import br.com.projetoWEB.model.FrequenciaAula;

@Named
@ViewScoped
public class HomeController implements Serializable {

	private static final long serialVersionUID = 4751917024593216166L;

	@Inject
	private GenericDAO<Aluno> daoAluno;

	@Inject
	private GenericDAO<Turma> daoTurma;

	@Inject
	private GenericDAO<FrequenciaAula> daoFrequenciaAula;

	public int getNumeroAlunos() {
		return daoAluno.findAll(Aluno.class).size();
	}

	public int getNumeroTurmas() {
		return daoTurma.findAll(Turma.class).size();
	}

	public int getNumeroFrequenciaAulas() {
		return daoFrequenciaAula.findAll(FrequenciaAula.class).size();
	}
}
