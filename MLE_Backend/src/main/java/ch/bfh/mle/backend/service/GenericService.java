package ch.bfh.mle.backend.service;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public abstract class GenericService<E> implements IService<E> {

	@PersistenceContext
	protected EntityManager entityManager;
	private Class<E> entityClass;
	    
	public GenericService(Class<E> entityClass) {
		this.entityClass = entityClass;
        }
        
	public void create(E entity) {
		entityManager.persist(entity);
	}

	public E read(Long id) {
		return entityManager.find(entityClass, id);
	}
	
	public Collection<E> read() {
		return (Collection<E>) entityManager.createQuery("SELECT x FROM " + entityClass.getSimpleName() + " x", entityClass)
				.getResultList();
	}


	public E update(E entity) {
		return entityManager.merge(entity);
	}
	

	public void delete(Long id) {
		E entity = entityManager.find(entityClass, id);
		entityManager.remove(entity);
	}
}
