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

import com.journaldev.hibernate.model.Pizza;

/*import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;*/

@Entity
@Table(name = "USER")
@NamedQuery(name = "findUserByUsernameAndPassword",
query = "SELECT u FROM User u WHERE u.username LIKE :username AND u.password LIKE :password")
@NamedQuery(name = "findUserById",
query = "SELECT u FROM User u WHERE u.id = :id")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@OneToMany(mappedBy = "user")
	private List<Pizza> pizza;
	
	public User() {}
	public User(String _username, String _password) {
		setUsername(_username);
		setPassword(_password);
	}
	public User(String _username, String _password, List<Pizza> _pizzas) {
		setUsername(_username);
		setPassword(_password);
		setPizza(_pizzas);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Pizza> getPizza() {
		return pizza;
	}

	public void setPizza(List<Pizza> pizza) {
		this.pizza = pizza;
	}
	
	@Override
	public String toString() {
		return "User: " + getId() + " - " + getUsername() + " - " + getPizza();
	}
}
