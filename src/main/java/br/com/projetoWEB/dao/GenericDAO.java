package br.com.projetoWEB.dao;

import static java.util.Objects.nonNull;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import br.com.projetoWEB.model.enumerated.Status;
import br.com.projetoWEB.util.jpa.Transactional;

public class GenericDAO<T> implements Serializable {

	private static final long serialVersionUID = 6760213737577381273L;

	@Inject
	private EntityManager manager;

	@Transactional
	public void salvar(T entity) {
		manager.persist(entity);
	}

	@Transactional
	public T editar(T entity) {
		return manager.merge(entity);
	}

	@Transactional
	public T findById(Class<T> entity, Long id) {
		return manager.find(entity, id);
	}

	@Transactional
	public List<T> findAll(Class<T> entity) {
		CriteriaBuilder builder = this.manager.getCriteriaBuilder();
		CriteriaQuery<T> cq = builder.createQuery(entity);
		Root<T> root = cq.from(entity);
		cq.select(root);
		return manager.createQuery(cq).getResultList();
	}

	public List<T> findByAtributeList(Class<T> entity, String valor, String nameAtribute) {
		CriteriaBuilder builder = this.manager.getCriteriaBuilder();
		CriteriaQuery<T> cq = builder.createQuery(entity);
		Root<T> root = cq.from(entity);
		if (StringUtils.isNotBlank(valor) && StringUtils.isNoneBlank(nameAtribute)) {
			Predicate condicao = builder.equal(root.get(nameAtribute), valor);
			cq.where(condicao);
		}
		return this.manager.createQuery(cq).getResultList();
	}

	public List<T> findByAtributeList(Class<T> entity, String nameAtribute, Object valor) {
		CriteriaBuilder builder = this.manager.getCriteriaBuilder();
		CriteriaQuery<T> cq = builder.createQuery(entity);
		Root<T> root = cq.from(entity);
		if (nonNull(valor) && StringUtils.isNoneBlank(nameAtribute)) {
			Predicate condicao = builder.equal(root.get(nameAtribute), valor);
			cq.where(condicao);

		}
		return this.manager.createQuery(cq).getResultList();
	}

	public T findByAtributeSingle(Class<T> entity, String nameAtribute, String valor) {
		CriteriaBuilder builder = this.manager.getCriteriaBuilder();
		CriteriaQuery<T> cq = builder.createQuery(entity);
		Root<T> root = cq.from(entity);
		if (StringUtils.isNotBlank(valor) && StringUtils.isNoneBlank(nameAtribute)) {
			Predicate condicao = builder.equal(root.get(nameAtribute), valor);
			cq.where(condicao);
		}
		return this.manager.createQuery(cq).getSingleResult();
	}

	public T findByAtributeSingle(Class<T> entity, String nameAtribute, Object valor) {
		CriteriaBuilder builder = this.manager.getCriteriaBuilder();
		CriteriaQuery<T> cq = builder.createQuery(entity);
		Root<T> root = cq.from(entity);
		if (nonNull(valor) && StringUtils.isNoneBlank(nameAtribute)) {
			Predicate condicao = builder.equal(root.get(nameAtribute), valor);
			cq.where(condicao);
		}
		return this.manager.createQuery(cq).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<T> findByAtributeWithJoinStatusAtivado(String namedQuery, String parameter, String value) {
		return this.manager.createNamedQuery(namedQuery).setParameter(parameter, value)
				.setParameter("status", Status.ATIVADO).getResultList();
	}

	@Transactional
	public void remover(Class<T> entity, Long id) {
		T t = findById(entity, id);
		manager.remove(t);
	}

}
