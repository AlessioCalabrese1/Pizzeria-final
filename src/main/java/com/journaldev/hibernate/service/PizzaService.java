package com.journaldev.hibernate.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.journaldev.hibernate.dao.PizzaCRUD;
import com.journaldev.hibernate.model.Pizza;

@Path("pizzas")
public class PizzaService {

	// URI:
	// /PIZZERIA_FINAL/rest/pizzas
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pizza> getPizzas_JSON() {
		List<Pizza> pizzas = PizzaCRUD.getAllPizzas();
		pizzas.forEach(pizza -> {
			pizza.getUser().setPizza(null);
			pizza.getDough().setPizzas(null);
		});
		return pizzas;
	}
}
