package no.bekk.database.dao;

import java.util.Collection;
import java.util.List;

public interface Dao<K, E> {

	void persist(E entity);

	void persistAll(Collection<E> entities);

	void remove(E entity);

	E getById(K id);

	E findById(K id);

	List<E> getAll();

	long count();

}