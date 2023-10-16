package org.java.app;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Ingrediente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToMany(mappedBy = "ingredienti")
	private List<Pizza> pizze;
	
	@Column(nullable = false)
	private String name;
	private String description;
	
	public Ingrediente() { }
	public Ingrediente(String name, String description) {
		
		setName(name);
		setDescription(description);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Pizza> getPizze() {
		return pizze;
	}
	public void setPizze(List<Pizza> pizze) {
		this.pizze = pizze;
	}
	
	@Override
	public String toString() {
		
		return "[" + getId() + "] " + getName() + "\n" + getDescription();
	}
	
	@Override
	public int hashCode( ) {
		
		return getId();
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof  Ingrediente)) return false;
		
		Ingrediente objIng = (Ingrediente) obj;
		
		return getId() == objIng.getId();
		
	}
}

