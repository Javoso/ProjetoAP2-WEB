package br.com.projetoWEB.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "filmesPorAtor", query = "SELECT f FROM Filme f JOIN f.atores a WHERE a.nome = :nomeAtor AND a.status = :status")
public class Filme implements Serializable {
	private static final long serialVersionUID = -5654084668634006220L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	@Lob
	private String imagem;

	@ManyToOne
	private Genero genero;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "FILME_ATOR", joinColumns = { @JoinColumn(name = "FILME_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "ATOR_ID") })
	private List<Ator> atores = new ArrayList<>();

	private Date dataLancamento;

	private String descricao;

	public Filme() {

	}

	public Filme(String nome, Genero genero, List<Ator> atores, String descricao) {
		super();
		this.nome = nome;
		this.genero = genero;
		this.atores = atores;
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public List<Ator> getAtores() {
		return atores;
	}

	public void setAtores(List<Ator> atores) {
		this.atores = atores;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public Date getDataLancamento() {

		return dataLancamento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Filme other = (Filme) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Filme [id=" + id + ", nome=" + nome + ", genero=" + genero + ", dataLancamento="
				+ dataLancamento + ", descricao=" + descricao + "]";
	}

}
