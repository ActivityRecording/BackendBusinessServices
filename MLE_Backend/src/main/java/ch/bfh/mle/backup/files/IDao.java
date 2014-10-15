package ch.bfh.mle.backup.files;

import java.util.Collection;

public interface IDao<E> {

	E create();
	
	E read(long id);
        
	Collection<E> read();
	
	E update(E entity);
        
	void delete(E entity);
}
