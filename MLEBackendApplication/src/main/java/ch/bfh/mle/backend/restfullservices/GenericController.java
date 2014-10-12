package ch.bfh.mle.backend.restfullservices;

import java.util.Collection;
import ch.bfh.mle.backend.servicelayer.IDao;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


public class GenericController<E> implements IController<E> {

	protected IDao<E> dao;
	
	@Override
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public E create(@RequestBody E entity) {
		
		E created = dao.update(entity);
		System.out.println("Entity created");
		return created;
	}

	@Override
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public E update(@RequestBody E entity) {
		
		E updated = dao.update(entity);
		System.out.println("Entity updated");
		return updated;
	}

	
	@RequestMapping(value = "{id}", method = RequestMethod.GET,
                        headers="Accept=application/json")
	//@ResponseBody
	public E get(@PathVariable long id) {
		
		System.out.println("Entity requested with id = " + id);
		return dao.read(id);
	}

	@Override
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Collection<E> getAll() {
		
		System.out.println("Collection of Entity requested");
		return dao.read();
	}

	@Override
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable long id) {
		
		E entity = dao.read(id);
		dao.delete(entity);
		System.out.println("Delete Entity with id = " + id);
	}
}
