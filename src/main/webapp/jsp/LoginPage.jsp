<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
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
	<form action="http://localhost:8080/PIZZERIA_FINAL/UserServletController2" method="get">
		<% 
		if(request.getAttribute("error") != null){ %>
			<p><%= request.getAttribute("error") %></p>
		<%} %>
		<p>Insert Username and Password!</p>
		<table>
			<tr>
				<td>Username</td>
				<td> <input type="text" name="username" placeholder="Insert Username"> </td>
			</tr>
			
			<tr>
				<td>Password</td>
				<td> <input type="text" name="password" placeholder="Insert Password"> </td>
			</tr>
			
			<tr>
				<td colspan="2">
					<input type="submit" value="Submit">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
