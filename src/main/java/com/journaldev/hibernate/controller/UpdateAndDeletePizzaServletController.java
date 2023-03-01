package com.journaldev.hibernate.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.journaldev.hibernate.dao.PizzaCRUD;
import com.journaldev.hibernate.model.Dough;
import com.journaldev.hibernate.model.Ingredient;
import com.journaldev.hibernate.model.Pizza;
import com.journaldev.hibernate.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;*/

/**
 * Servlet implementation class UpdateAndDeletePizzaServletContorller
 */
@WebServlet("/UpdateAndDeletePizzaServletController2")
public class UpdateAndDeletePizzaServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAndDeletePizzaServletController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PizzaCRUD pizzaCRUD = new PizzaCRUD();
		pizzaCRUD.deletePizza(Integer.parseInt(request.getParameter("pizzaId")));
		
		request.setAttribute("userId", Integer.parseInt(request.getParameter("userId")));
		request.getRequestDispatcher("jsp/UserPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("DAJE ROMAAAA");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistencePizzeria");
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		
		User user = (User) entityManager.find(User.class, Integer.parseInt(request.getParameter("userId")));
		Dough dough = entityManager.find(Dough.class, Integer.parseInt(request.getParameter("doughId")));
		
		String[] ingredientsId = request.getParameterValues("ingredientsId");
		System.out.println("Gli id degli ingredienti sono: " + Arrays.toString(ingredientsId));
		Set<Ingredient> ingredients = new HashSet<Ingredient>();
		for(String ingredientId : ingredientsId) {
			ingredients.add(entityManager.find(Ingredient.class, Integer.parseInt(ingredientId)));
		}
		System.out.println(ingredients);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		Pizza newPizza = new Pizza(request.getParameter("pizzaName"), user, dough, ingredients);
		PizzaCRUD pizzaCRUD = new PizzaCRUD();
		pizzaCRUD.updatePizza(newPizza, Integer.parseInt(request.getParameter("pizzaId")));
		System.out.println("L'update è stato eseguito");
		
		
		request.setAttribute("userId", user.getId());
		request.getRequestDispatcher("jsp/UserPage.jsp").forward(request, response);
	}

}
