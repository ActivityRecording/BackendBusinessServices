package ch.bfh.swos.camerareview.model;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.TemporalType.DATE;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

import org.codehaus.jackson.annotate.JsonManagedReference;

/**
 * Entity implementation class for Entity: TestReport
 *
 */
@Entity
public class TestReport implements Serializable {
	   
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private long id;
	private String title;
	@Temporal(DATE)
	private Date date;
	private String text;
	private int status;
	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade = PERSIST)
	private Author author;
	
	@OneToOne(cascade = PERSIST)
	private Camera camera;
	
	@OneToMany(mappedBy = "testReport", cascade = ALL)
	@JsonManagedReference
	private List<Comment> comments;
	
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public Author getAuthor() {
		return author;
	}
	
	public void setAuthor(Author author) {
		this.author = author;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public Camera getCamera() {
		return camera;
	}
	
	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public void addComment(Comment comment) {
		this.comments.add(comment);
	}
	
	public void removeComment(Comment comment) {
		this.comments.remove(comment);
	}
}