package com.journaldev.hibernate.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;*/

@Entity
@Table(name = "PIZZA")
public class Pizza {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pizza_id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "dough_id", nullable = false)
	private Dough dough;
	
	@ManyToMany(targetEntity = Ingredient.class, cascade = CascadeType.DETACH)
	@JoinTable(name = "PIZZA_INGREDIENT",
				joinColumns = @JoinColumn(name = "pizza_id"),
				inverseJoinColumns = @JoinColumn(name = "ingredient_id")  )
	private Set<Ingredient> ingredients;
	
	public Pizza() {}
	public Pizza(String _name, User _user, Dough dough, Set<Ingredient> _ingredients) {
		setName(_name);
		setUser(_user);
		setDough(dough);
		setIngredients(_ingredients);
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Dough getDough() {
		return dough;
	}

	public void setDough(Dough dough) {
		this.dough = dough;
	}

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
}
