package com.journaldev.hibernate.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;*/

@Entity
@Table(name = "DOUGH")
@NamedQuery(name = "selectAllDough", query = "SELECT d FROM Dough d")
public class Dough {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dough_id")
	private int id;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "dough")
	private List<Pizza> pizzas;

	public Dough() {
	}

	public Dough(String _name) {
		setName(_name);
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

	public List<Pizza> getPizzas() {
		return pizzas;
	}

	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}

	@Override
	public String toString() {
		return getId() + " - " + getName();
	}
}
