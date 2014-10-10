package ch.bfh.mle.backend.servicelayer;

import java.util.Collection;

/**
 * Generelles DAO(Data Acces Object)-Interface mit CRUD-Operationen
 * @author Boris Haueter
 * @param <E> 
 */
public interface IDao<E> {

	E create();
	
	E read(long id);
        
	Collection<E> read();
	
	E update(E entity);
        
	void delete(E entity);
}
