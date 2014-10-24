package ch.bfh.mle.backend.service;

import java.util.Collection;

/**
 * Das Interface definiert die CRUD-Funktionen fuer Services.
 * @author Stefan
 * @param <E> 
 */
public interface IService<E> {

	void create(E entity);
        E read(Long id);
        Collection<E> read();
        E update(E entity);
	void delete(Long id);
}
