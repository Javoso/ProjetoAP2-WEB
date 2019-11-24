package br.com.projetoWEB.model.enumerated;

public enum Status {

	ATIVADO("ativado"), DESATIVADO("desativado");

	private Status(String status) {
		this.status = status;
	}

	String status;

	public String getStatus() {
		return status;
	}

}
