package br.com.projetoWEB.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FREQUENCIA")
public class FrequenciaAula extends EntityGeneric {

	private static final long serialVersionUID = 5344281560915634162L;

	@ManyToOne
	private Turma turma;
	private Date dataDaFrequencia;
	private String anotacoes;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(joinColumns = @JoinColumn(name = "FREQUENCIA_ID"), inverseJoinColumns = @JoinColumn(name = "ALUNO_ID"))
	private List<Aluno> alunosFrequentes;

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Date getDataDaFrequencia() {
		return dataDaFrequencia;
	}

	public void setDataDaFrequencia(Date dataDaFrequencia) {
		this.dataDaFrequencia = dataDaFrequencia;
	}

	public String getAnotacoes() {
		return anotacoes;
	}

	public void setAnotacoes(String anotacoes) {
		this.anotacoes = anotacoes;
	}

	public List<Aluno> getAlunosFrequentes() {
		return alunosFrequentes;
	}

	public void setAlunosFrequentes(List<Aluno> alunosFrequentes) {
		this.alunosFrequentes = alunosFrequentes;
	}

	@Override
	public String toString() {
		return "FrequenciaAula [id=" + getId() + ", turma=" + turma + ", dataDaFrequencia=" + dataDaFrequencia
				+ ", anotacoes=" + anotacoes + ", alunosFrequentes=" + alunosFrequentes + "]";
	}

}
