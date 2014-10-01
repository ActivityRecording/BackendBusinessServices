package ch.bfh.swos.camerareview.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Entity implementation class for Entity: Author
 *
 */
@Entity

public class Author implements Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private long id;
	private String forename; // forename statt prename
	private String surname; // surename statt name
	private static final long serialVersionUID = 1L;
	
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getForename() {
		return forename;
	}
	
	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
}