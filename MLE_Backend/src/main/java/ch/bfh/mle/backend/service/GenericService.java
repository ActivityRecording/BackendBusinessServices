package ch.bfh.mle.backend.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public abstract class GenericService<E> implements IService<E> {

	@PersistenceContext
	protected EntityManager entityManager;
	private Class<E> entityClass;
	    
	public GenericService(Class<E> entityClass) {
		this.entityClass = entityClass;
        }
        
        @Override
	public void create(E entity) {
		entityManager.persist(entity);
	}

        @Override
	public E read(Long id) {
		return entityManager.find(entityClass, id);
	}
	
        @Override
	public List<E> read() {
		return (List<E>) entityManager.createQuery("SELECT x FROM " + entityClass.getSimpleName() + " x", entityClass)
				.getResultList();
	}

        @Override
	public E update(E entity) {
		return entityManager.merge(entity);
	}

        @Override
	public void delete(Long id) {
		E entity = entityManager.find(entityClass, id);
		entityManager.remove(entity);
	}
}
