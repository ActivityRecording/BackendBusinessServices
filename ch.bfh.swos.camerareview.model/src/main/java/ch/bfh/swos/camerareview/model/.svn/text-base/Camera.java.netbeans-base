package ch.bfh.swos.camerareview.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Entity implementation class for Entity: Camera
 *
 */
@Entity

public class Camera implements Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private long id;
	private String description;
	private String producer;
	private String model; // String statt int
	private int productionYear; // int statt Date
	private static final long serialVersionUID = 1L;

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getProducer() {
		return this.producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}
	
	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	public int getProductionYear() {
		return this.productionYear;
	}

	public void setProductionYear(int productionYear) {
		this.productionYear = productionYear;
	}
}