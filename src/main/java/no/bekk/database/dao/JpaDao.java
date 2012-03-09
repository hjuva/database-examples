package no.bekk.database.dao;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

public abstract class JpaDao<K, E> implements Dao<K, E> {

	private final Class<E> entityClass;

	@PersistenceContext
	private EntityManager entityManager;

	protected Class<E> getEntityClass() {
		return entityClass;
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@SuppressWarnings("unchecked")
	public JpaDao() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
	}

	@Override
	public void persist(final E entity) {
		entityManager.persist(entity);
	}

	@Override
	public void persistAll(final Collection<E> entities) {
		for (E entity : entities) {
			persist(entity);
		}
	}

	@Override
	public void remove(final E entity) {
		entityManager.remove(entity);
	}

	@Override
	public E findById(final K id) {
		return entityManager.find(entityClass, id);
	}

	@Override
	public E getById(final K id) {
		E entity = entityManager.find(entityClass, id);
		if (entity == null) {
			throw new EntityNotFoundException("Could not find entity with id " + id);
		}
		return entity;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<E> getAll() {
		return getEntityManager().createQuery("from " + getEntityClass().getName()).getResultList();
	}

	@Override
	public long count() {
		return (Long) getEntityManager().createQuery("select count(*) from " + getEntityClass().getName()).getSingleResult();
	}

}
