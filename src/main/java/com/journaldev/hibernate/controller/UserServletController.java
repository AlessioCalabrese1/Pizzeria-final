package com.journaldev.hibernate.controller;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.journaldev.hibernate.model.User;

/*import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;*/

/**
 * Servlet implementation class UserServletController
 */
@WebServlet("/UserServletController2")
public class UserServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServletController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistencePizzeria");
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();

		User user = null;
		try {
			user = (User) entityManager.createNamedQuery("findUserByUsernameAndPassword")
					.setParameter("username", username).setParameter("password", password).getSingleResult();
			System.out.println(user);
		} catch (Exception e) {
			System.out.println("User not found");
			request.setAttribute("error", "Incorrect username or password, try again!");
			request.getRequestDispatcher("jsp/LoginPage.jsp").forward(request, response);
		}

		entityManager.getTransaction().commit();

		request.setAttribute("userId", user.getId());
		System.out.println("sono prima del riderizionamento");
		request.getRequestDispatcher("jsp/UserPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}