package br.com.projetoWEB.model.enumerated;

public enum FaixaDeGraduacao {

	BRANCA("Iniciante", "#FEFEFE"),
	AMARELA("6º KYU", "#FFFF01"),
	VERMELHA("5º KYU", "#FE0002"),
	LARANJA("4º KYU", "#F85E14"),
	VERDE("3º KYU", "#0A7A0A"),
	ROXO("2º KYU", "#0A7A0A"),
	MARRON("1º KYU", "#93613C"),
	PRETA("1º DAN", "#000000");

	private String faixa;
	private String cor;

	FaixaDeGraduacao(String faixa, String cor) {
		this.faixa = faixa;
		this.cor = cor;
	}

	public String getFaixa() {
		return faixa;
	}

	public String getCor() {
		return cor;
	}
}
