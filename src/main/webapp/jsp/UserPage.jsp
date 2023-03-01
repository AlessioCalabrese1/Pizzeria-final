<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="com.journaldev.hibernate.model.Pizza"%>
<%@page import="com.journaldev.hibernate.model.User"%>
<%@page import="java.util.List"%>
<%@page import="com.journaldev.hibernate.model.Dough"%>
<%@page import="com.journaldev.hibernate.model.Ingredient"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Page</title>
<style type="text/css">
	table, tr, td{
		font-size: .9em;
		border: 3px groove;
		padding: 5px;
		background-color: #dddddd;
	}

</style>
</head>
<body>
	<form action="http://localhost:8080/PIZZERIA_FINAL/PizzaServletController2" method="post">
		
		<!-- Tabella per selezionare l'impasto -->
		<p>Check the dough you want!</p>
		<table>
			<% 
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistencePizzeria");
			EntityManager entityManager = emf.createEntityManager();
			entityManager.getTransaction().begin();
			
			User user = entityManager.find(User.class, request.getAttribute("userId"));
			
			List<Dough> doughs = entityManager.createNamedQuery("selectAllDough").getResultList();
			
			List<Ingredient> ingredients = entityManager.createNamedQuery("selectAllIngredient").getResultList();
			
			entityManager.getTransaction().commit();
			
			for(Dough dough : doughs){
				
			%>
				<tr>
					<input type="radio" name="doughId" value="<%= dough.getId() %>">
	  				<label for="html"><%= dough.getName() %></label><br>
				</tr>
			<% } %>
		</table>
			
		<input type="text" name="userId" value="<%= user.getId() %>" style="display: none;">
		
		<!-- Tabella per selezionare gli ingredienti -->
		<p>Check the ingredients you want!</p>
		<table>
			<% 
		
			for(Ingredient ingredient : ingredients){
			%>
			<tr>
				<input type="checkbox" name="ingredientsId" value="<%= ingredient.getId() %>">
  				<label for="html"><%= ingredient.getName() %></label><br>
			</tr>
			<% } %>
		</table>
		
		<table>
			<tr>
				<td>Insert pizza name!</td>
				<td> <input type="text" name="pizzaName" placeholder="Insert pizza name"> </td>
			</tr>
		
			<tr>
				<td colspan="2">
					<input type="submit" value="Submit">
				</td>
			</tr>
		</table>
	</form>
	
	<div>
		<table>
			<tr>
				<td>Pizza Name</td>
				<td>Dough</td>
				<td>Ingredients</td>
				<td>Actions</td>
			</tr>
			<%
				for(Pizza pizza : user.getPizza()){
			%>
			<tr>
				<td><%= pizza.getName() %></td>
				<td><%= pizza.getDough() %></td>
				<td>
					<ul>
					<% for(Ingredient ingredient : pizza.getIngredients()){ %>
						<li>
							<%= ingredient.getName() %>
						</li>
					<% } %>
					</ul>
				</td>
				<td>
					<form action="http://localhost:8080/PIZZERIA_FINAL/PizzaServletController2" method="get">
						<input type="text" name="pizzaId" value="<%= pizza.getId() %>" style="display: none;">
						<input type="submit" value="Update">
					</form>
					
					<form action="http://localhost:8080/PIZZERIA_FINAL/UpdateAndDeletePizzaServletController2" method="get">
						<input type="text" name="pizzaId" value="<%= pizza.getId() %>" style="display: none;">
						<input type="text" name="userId" value="<%= user.getId() %>" style="display: none;">
						<input type="submit" value="Delete">
					</form>
				</td>
			</tr>
			<% } %>
		</table>
	</div>
</body>
</html>