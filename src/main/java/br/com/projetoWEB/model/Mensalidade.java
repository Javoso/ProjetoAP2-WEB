package br.com.projetoWEB.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "MENSALIDADE")
public class Mensalidade extends EntityGeneric {

	private static final long serialVersionUID = -6822720094841323764L;

	private PlanoPagamento planoPagamento;
	private BigDecimal valor;
	private String cicloPagamento;

	public PlanoPagamento getPlanoPagamento() {
		return planoPagamento;
	}

	public void setPlanoPagamento(PlanoPagamento planoPagamento) {
		this.planoPagamento = planoPagamento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getCicloPagamento() {
		return cicloPagamento;
	}

	public void setCicloPagamento(String cicloPagamento) {
		this.cicloPagamento = cicloPagamento;
	}

	@Override
	public String toString() {
		return "Mensalidade [id=" + getId() + ", planoPagamento=" + planoPagamento + ", valor=" + valor
				+ ", cicloPagamento=" + cicloPagamento + "]";
	}

}
