package com.journaldev.hibernate.dao;

import java.util.List;

/*import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;*/

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.journaldev.hibernate.model.Pizza;

public class PizzaCRUD {

	public static List<Pizza> getAllPizzas() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistencePizzeria");
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();

		List<Pizza> pizzas = entityManager.createNamedQuery("selectAllPizza").getResultList();

		entityManager.getTransaction().commit();
		entityManager.close();

		return pizzas;
	}

	public Pizza findPizzaById(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistencePizzeria");
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();

		Pizza oldPizza = entityManager.find(Pizza.class, id);

		entityManager.getTransaction().commit();
		entityManager.close();

		return oldPizza;
	}

	public void insertNewPizza(Pizza newPizza) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistencePizzeria");
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();

		entityManager.persist(newPizza);

		entityManager.getTransaction().commit();
	}

	public void deletePizza(int pizzaId) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistencePizzeria");
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();

		Pizza pizza = entityManager.find(Pizza.class, pizzaId);
		entityManager.remove(pizza);
		;

		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public void updatePizza(Pizza updatedPizza, int pizzaId) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistencePizzeria");
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();

		Pizza oldPizza = entityManager.find(Pizza.class, pizzaId);

		oldPizza.setName(updatedPizza.getName());
		oldPizza.setDough(updatedPizza.getDough());
		oldPizza.setIngredients(updatedPizza.getIngredients());

		entityManager.getTransaction().commit();
		entityManager.close();
	}

}
