package br.com.projetoWEB.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.projetoWEB.model.enumerated.DiasDaSemana;
import br.com.projetoWEB.model.enumerated.FaixaDeGraduacao;

@Entity
@Table(name="TURMA")
public class Turma extends EntityGeneric {
	
	private static final long serialVersionUID = -2300528889780644315L;
	
	private String nomeDaTurma;
	private FaixaDeGraduacao faixaDeGraduacaoDaTurma;
	private List<DiasDaSemana> diasDaSemana;
	private int minimoDeAlunos;
	private int maximoDeAlunos;
	private int idadeMinimaDosAlunos;
	private int idadeMaximaDosAlunos;

	public String getNomeDaTurma() {
		return nomeDaTurma;
	}

	public void setNomeDaTurma(String nomeDaTurma) {
		this.nomeDaTurma = nomeDaTurma;
	}

	public FaixaDeGraduacao getFaixaDeGraduacaoDaTurma() {
		return faixaDeGraduacaoDaTurma;
	}

	public void setFaixaDeGraduacaoDaTurma(FaixaDeGraduacao faixaDeGraduacaoDaTurma) {
		this.faixaDeGraduacaoDaTurma = faixaDeGraduacaoDaTurma;
	}

	public List<DiasDaSemana> getDiasDaSemana() {
		return diasDaSemana;
	}

	public void setDiasDaSemana(List<DiasDaSemana> diasDaSemana) {
		this.diasDaSemana = diasDaSemana;
	}

	public int getMinimoDeAlunos() {
		return minimoDeAlunos;
	}

	public void setMinimoDeAlunos(int minimoDeAlunos) {
		this.minimoDeAlunos = minimoDeAlunos;
	}

	public int getMaximoDeAlunos() {
		return maximoDeAlunos;
	}

	public void setMaximoDeAlunos(int maximoDeAlunos) {
		this.maximoDeAlunos = maximoDeAlunos;
	}

	public int getIdadeMinimaDosAlunos() {
		return idadeMinimaDosAlunos;
	}

	public void setIdadeMinimaDosAlunos(int idadeMinimaDosAlunos) {
		this.idadeMinimaDosAlunos = idadeMinimaDosAlunos;
	}

	public int getIdadeMaximaDosAlunos() {
		return idadeMaximaDosAlunos;
	}

	public void setIdadeMaximaDosAlunos(int idadeMaximaDosAlunos) {
		this.idadeMaximaDosAlunos = idadeMaximaDosAlunos;
	}
	
	@Override
	public String toString() {
		return "Turma [id=" + getId() + ", nomeDaTurma=" + nomeDaTurma + ", faixaDeGraduacaoDaTurma="
				+ faixaDeGraduacaoDaTurma + ", diasDaSemana=" + diasDaSemana + ", minimoDeAlunos=" + minimoDeAlunos
				+ ", maximoDeAlunos=" + maximoDeAlunos + ", idadeMinimaDosAlunos=" + idadeMinimaDosAlunos
				+ ", idadeMaximaDosAlunos=" + idadeMaximaDosAlunos + "]";
	}

}
