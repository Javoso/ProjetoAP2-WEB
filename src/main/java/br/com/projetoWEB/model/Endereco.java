package br.com.projetoWEB.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ENDERECO")
public class Endereco extends EntityGeneric {

	private static final long serialVersionUID = -4526457160154303563L;

	private String rua;
	private String numeroCasa;
	private String pontoReferencia;
	private String complemento;

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumeroCasa() {
		return numeroCasa;
	}

	public void setNumeroCasa(String numeroCasa) {
		this.numeroCasa = numeroCasa;
	}

	public String getPontoReferencia() {
		return pontoReferencia;
	}

	public void setPontoReferencia(String pontoReferencia) {
		this.pontoReferencia = pontoReferencia;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	@Override
	public String toString() {
		return "Endereco [id=" + getId() + ", rua=" + rua + ", numeroCasa=" + numeroCasa + ", pontoReferencia="
				+ pontoReferencia + ", complemento=" + complemento + "]";
	}

}
