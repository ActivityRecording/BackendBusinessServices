package ch.bfh.mle.backend.service;

import java.util.Collection;

public interface IService<E> {

	E create();
        E read(long id);
        Collection<E> read();
        E update(E entity);
	void delete(E entity);
}
