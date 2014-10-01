package ch.bfh.mle.backend.rest.controller;

import java.util.Collection;

public interface IController<E> {

	public E create(E entity);
	public E update(E entity);
	
	public E get(long id);
	public Collection<E> getAll();
	
	public void delete(long id);
}
