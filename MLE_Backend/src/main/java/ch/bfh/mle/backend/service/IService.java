package ch.bfh.mle.backend.service;

import java.util.Collection;

public interface IService<E> {

	void create(E entity);
        E read(Long id);
        Collection<E> read();
        E update(E entity);
	void delete(Long id);
}
