package ch.bfh.mle.backend.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * GenericService ist eine generische Implementation der CRUD-Funktionen von Entitaeten, 
 * welche im IService Interface definiert sind. 
 * Die Konkreten Serviceklassen benoetigen einen Kostruktor in dem die Entitaetsklasse uebergeben wird.
 * @author Boris Haueter
 * @param <E> 
 */
public abstract class GenericService<E> implements IService<E> {

	@PersistenceContext
	protected EntityManager entityManager;
	private Class<E> entityClass;

        /**
         * Erzeugt einen neuen Service mit der mitgegebenen Entitaet.
         * @param entityClass 
         */
	public GenericService(Class<E> entityClass) {
            this.entityClass = entityClass;
        }
        
        public void setEntityManager(EntityManager entityManager){
            this.entityManager = entityManager;
        }
        
        /**
         * Speichert eine Entitaet auf der Datenbank.
         * @param entity 
         */
        @Override
	public void create(E entity) {
            entityManager.persist(entity);
	}

        /**
         * Liest eine Entitaet mit der id auf der Datenbank und gibt sie zurueck.
         * @param id
         * @return 
         */
        @Override
	public E read(Long id) {
            return entityManager.find(entityClass, id);
	}
	
        /**
         * Liest alle Entitaeten von der Datenbank und gibt sie zurueck.
         * @return 
         */
        @Override
	public List<E> read() {
            return (List<E>) entityManager.createQuery("SELECT x FROM " + entityClass.getSimpleName() + " x", entityClass).getResultList();
	}

        /**
         * Updated eine Entitaet auf der Datenbank und gibt die geaenderte Entitaet zurueck.
         * @param entity
         * @return 
         */
        @Override
	public E update(E entity) {
            return entityManager.merge(entity);
	}

        /**
         * Loescht eine Entitaet von der Datenbank.
         * Die Entitaet muss vorhanden sein, sonst wird eine IllegalArgumentException
         * geworfen.
         * @param id 
         */
        @Override
	public void delete(Long id) {
            E entity = entityManager.find(entityClass, id);
            if (entity == null){
                throw new IllegalArgumentException("Entity not found");
            }
            entityManager.remove(entity);
	}
}
