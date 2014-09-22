package ch.bfh.swos.camerareview.service;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

public abstract class GenericJpaDao<E> implements IDao<E> {
	
	@PersistenceContext
	protected EntityManager entityManager;
	private Class<E> entityClass;
	
	public GenericJpaDao(Class<E> entityClass) {
		this.entityClass = entityClass;
	}
	
	@Transactional
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

	@Transactional
	public E update(E entity) {
		return entityManager.merge(entity);
	}
	
	@Transactional
	public void delete(E entity) {
		entity = entityManager.merge(entity);
		entityManager.remove(entity);
	}
}