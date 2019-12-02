package br.com.projetoWEB.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.projetoWEB.model.enumerated.FaixaDeGraduacao;
import br.com.projetoWEB.model.enumerated.Sexo;
import br.com.projetoWEB.model.enumerated.Status;

@Entity
@Table(name = "ALUNO")
public class Aluno extends EntityGeneric {

	private static final long serialVersionUID = 411211519375977329L;

	private String CPF;
	@OneToOne(cascade = CascadeType.ALL)
	private Endereco endereco;

	private FaixaDeGraduacao faixaDeGraduacao;
	private Sexo sexo;
	private PlanoPagamento planoDePagamento;
	private String nomeResponsavel;
	private String telefoneDoResponsavel;
	private String nomeAluno;
	private Status status;

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cpf) {
		this.CPF = cpf;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public FaixaDeGraduacao getFaixaDeGraduacao() {
		return faixaDeGraduacao;
	}

	public void setFaixaDeGraduacao(FaixaDeGraduacao faixaDeGraduacao) {
		this.faixaDeGraduacao = faixaDeGraduacao;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public PlanoPagamento getPlanoDePagamento() {
		return planoDePagamento;
	}

	public void setPlanoDePagamento(PlanoPagamento planoDePagamento) {
		this.planoDePagamento = planoDePagamento;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public String getTelefoneDoResponsavel() {
		return telefoneDoResponsavel;
	}

	public void setTelefoneDoResponsavel(String telefoneDoResponsavel) {
		this.telefoneDoResponsavel = telefoneDoResponsavel;
	}

	public String getNomeAluno() {
		return nomeAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
