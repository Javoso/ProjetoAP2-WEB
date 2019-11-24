package br.com.projetoWEB.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.projetoWEB.dao.GenericDAO;
import br.com.projetoWEB.model.Ator;
import br.com.projetoWEB.model.Filme;
import br.com.projetoWEB.model.Genero;

@Named
@ViewScoped
public class HomeController implements Serializable {

	private static final long serialVersionUID = 4751917024593216166L;

	@Inject
	private GenericDAO<Ator> daoAtor;

	@Inject
	private GenericDAO<Filme> daoFilme;

	@Inject
	private GenericDAO<Genero> daoGenero;

	public int getNumeroAtores() {
		return daoAtor.findAll(Ator.class).size();
	}

	public int getNumeroFilmes() {
		return daoFilme.findAll(Filme.class).size();
	}

	public int getNumeroGeneros() {
		return daoGenero.findAll(Genero.class).size();
	}
}
