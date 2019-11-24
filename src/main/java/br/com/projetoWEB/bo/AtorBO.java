package br.com.projetoWEB.bo;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.projetoWEB.dao.GenericDAO;
import br.com.projetoWEB.model.Ator;

@Service
public class AtorBO {

	@Inject
	private GenericDAO<Ator> dao;

	public void salvar(Ator entity) {
		if (isNull(entity)) {
			dao.salvar(entity);
		}
	}

	public Ator editar(Ator entity) {
		return dao.editar(entity);
	}

	public Ator buscarPorId(Class<Ator> entity, Long id) {
		return dao.findById(entity, id);
	}

	public void remover(Class<Ator> entity, Long id) {
		if (nonNull(id)) {
			dao.remover(entity, id);
		}
	}

	public List<Ator> todos() {
		return dao.findAll(Ator.class);
	}
}
