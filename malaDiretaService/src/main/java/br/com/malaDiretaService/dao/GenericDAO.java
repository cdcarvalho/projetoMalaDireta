package br.com.malaDiretaService.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.malaDiretaService.util.JPAUtil;

@SuppressWarnings("unchecked")
public class GenericDAO<T> {
	private EntityManager entityManager;

	public GenericDAO() {
		this.entityManager = JPAUtil.getEntityManager();
	}
	
	public void salvar(T entity) {
		entityManager.persist(entity);
		entityManager.flush();
	}

	public void atualizar(T entity) {
		entityManager.merge(entity);
		entityManager.flush();
	}

	public T buscarPorId(Integer id) {
		return (T) entityManager.find(getTypeClass(), id);
	}

	public T buscaDetach(Integer id) {
		T object = (T) buscarPorId(id);
		entityManager.detach(object);
		return object;
	}

	public void delete(T entity) {
		entity = entityManager.merge(entity);
		entityManager.remove(entity);
		entityManager.flush();
	}

	public void delete(Integer id) {
		T object = buscarPorId(id);
		delete(object);
	}

	public List<T> listarPor(String atributo, Object valor) {
		Query query = entityManager.createQuery("FROM " + getTypeClass().getSimpleName() + " o WHERE o." + atributo + " = :valor");
		query.setParameter("valor", valor);
		return query.getResultList();
	}

	public List<T> listarPorLike(String atributo, Object valor) {
		Query query = entityManager.createQuery("FROM " + getTypeClass().getSimpleName() + " o WHERE o." + atributo + " like :valor");
		query.setParameter("valor", "%" + valor + "%");
		return query.getResultList();
	}

	public T buscarPor(String atributo, Object valor) {
		Query query = entityManager.createQuery("FROM " + getTypeClass().getSimpleName() + " o WHERE o." + atributo + " = :valor");
		query.setParameter("valor", valor);
		try {
			return (T) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<T> listar() {
		return entityManager.createQuery("FROM " + getTypeClass().getSimpleName() + " o").getResultList();
	}

	public Query criarQuery(String query) {
		return entityManager.createQuery(query);
	}

	private Class<?> getTypeClass() {
		Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		return clazz;
	}
}
