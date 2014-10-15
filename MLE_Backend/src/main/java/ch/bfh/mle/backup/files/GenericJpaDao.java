package ch.bfh.mle.backup.files;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

public abstract class GenericJpaDao<E> implements IDao<E> {
	
	@PersistenceContext
	protected EntityManager entityManager;
	private Class<E> entityClass;
	    
	public GenericJpaDao(Class<E> entityClass) {
		this.entityClass = entityClass;
	}
	

	public E create() {
		try {
			return entityClass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public E read(long id) {
		return entityManager.find(entityClass, id);
	}
	
	public Collection<E> read() {
		return (Collection<E>) entityManager.createQuery("SELECT x FROM " + entityClass.getSimpleName() + " x", entityClass)
				.getResultList();
	}


	public E update(E entity) {
		return entityManager.merge(entity);
	}
	

	public void delete(E entity) {
		entity = entityManager.merge(entity);
		entityManager.remove(entity);
	}
}
