<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> 
<%@ page import= "java.io.*"%>
<%@ page import="java.util.*" %>
<%@ page import="hib.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gestion des Distributeurs</title>
</head>
<body>
	<p><%=request.getAttribute("msg")%></p>
	<h2>Add new Distributeur:</h2>
	<form method ="GET" action="distributeurmanager">
		N serie: <input type="text" name="serie" maxlength="50"/><br/>
		Type: <input type="text" name="type" maxlength="20"/><br/>
		Adresse: <input type="text" name="adr" maxlength="50"/><br/>
		Emplacement: <input type="text" name="emp" maxlength="50"/><br/>
		Longitude: <input type="number" name="lon" maxlength="50"/><br/>
		Latitude: <input type="number" name="lat" maxlength="50"/><br/>
		Intervention: <input type="text" name="int"/><br/>
		Commentaire: <input type="text" name="com" maxlength="50"/><br/>
	<input type='submit' name='action' value='store'/>
	</form>
	<h2>Modify Distributeur:</h2>
	<form method ="GET" action="distributeurmanager">
		N serie existant: <input type="text" name="serie" maxlength="50"/><br/>
		Adresse: <input type="text" name="adr" maxlength="50"/><br/>
		Emplacement: <input type="text" name="emp" maxlength="50"/><br/>
		Longitude: <input type="number" name="lon" maxlength="50"/><br/>
		Latitude: <input type="number" name="lat" maxlength="50"/><br/>
		Intervention: <input type="text" name="int"/><br/>
		Commentaire: <input type="text" name="com" maxlength="50"/><br/>
	<input type='submit' name='action' value='modify'/>
	</form>
	<h2>Delete Distributeur:</h2>
	<form method ="GET" action="distributeurmanager">
		N serie existant: <input type="text" name="serie" maxlength="50"/><br/>
	<input type='submit' name='action' value='delete'/>
	</form>
	
</body>
</html>