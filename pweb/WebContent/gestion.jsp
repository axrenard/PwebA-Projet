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
	<% List<Distributeur> app = (List<Distributeur>) request.getAttribute("app"); %>
	<%if (app.size() > 0) { %>
	<h2>Distributeurs a approvisionner:</h2>
	<table border='1'>
	<tr>
	<th>N serie</th>
	<th>Type</th>
	</tr>
	<%for (Iterator<Distributeur> it = app.iterator(); it.hasNext();) {
		Distributeur dst = (Distributeur) it.next();%>
		<tr>
		<td><%=dst.get_serie()%></td>
		<td><%=dst.get_type()%></td>
		</tr>
	<%} %>	
	</table>
	<%} %>
	<% List<Distributeur> ver = (List<Distributeur>) request.getAttribute("ver"); %>
	<%if (ver.size() > 0) { %>
	<h2>Distributeurs a verifier:</h2>
	<table border='1'>
	<tr>
	<th>N serie</th>
	<th>Type</th>
	<th>Etat</th>
	<th>Piece</th>
	<th>Puce</th>
	<th>SansContact</th>
	</tr>
	<%for (Iterator<Distributeur> it = ver.iterator(); it.hasNext();) {
		Distributeur dst = (Distributeur) it.next();
		Rapport rapp= dst.get_rapports().iterator().next();%>
		<tr>
		<td><%=dst.get_serie()%></td>
		<td><%=dst.get_type()%></td>
		<td><%=rapp.get_etat()%></td>
		<td><%=rapp.get_piece()%></td>
		<td><%=rapp.get_puce()%></td>
		<td><%=rapp.get_sanscontact()%></td>
		</tr>
	<%} %>	
	</table>
	<%} %>
	<% List<Distributeur> hor = (List<Distributeur>) request.getAttribute("hor"); %>
	<%if (hor.size() > 0) { %>
	<h2>Distributeurs hors service:</h2>
	<table border='1'>
	<tr>
	<th>N serie</th>
	<th>Type</th>
	<th>Erreurs</th>
	</tr>
	<%for (Iterator<Distributeur> it = hor.iterator(); it.hasNext();) {
		Distributeur dst = (Distributeur) it.next();
		Rapport rapp= dst.get_rapports().iterator().next();%>
		<tr>
		<td><%=dst.get_serie()%></td>
		<td><%=dst.get_type()%></td>
		<td><%=rapp.get_erreurs()%></td>
		</tr>
	<%} %>	
	</table>
	<%} %>
	<% List<Distributeur> tri = (List<Distributeur>) request.getAttribute("tri"); %>
	<%if (tri.size() > 0) { %>
	<h2>Distributeurs par montant:</h2>
	<table border='1'>
	<tr>
	<th>N serie</th>
	<th>Type</th>
	<th>Adresse</th>
	<th>Emplacement</th>
	<th>Longitude</th>
	<th>Latitude</th>
	<th>Intervention</th>
	<th>Commentaire</th>
	<th>Montant</th>
	</tr>
	<%for (Iterator<Distributeur> it = tri.iterator(); it.hasNext();) {
		Distributeur dst = (Distributeur) it.next();
		Rapport rapp= dst.get_rapports().iterator().next();%>
		<tr>
		<td><%=dst.get_serie()%></td>
		<td><%=dst.get_type()%></td>
		<td><%=dst.get_adresse()%></td>
		<td><%=dst.get_emplacement()%></td>
		<td><%=dst.get_longitude()%></td>
		<td><%=dst.get_latitude()%></td>
		<td><%=dst.get_intervention()%></td>
		<td><%=dst.get_commentaire()%></td>
		<td><%=rapp.get_erreurs()%></td>
		</tr>
	<%} %>	
	</table>
	<%} %>
	<p><%=request.getAttribute("msg")%></p>
	<h2>Add new Distributeur:</h2>
	<form method ="GET" action="distributeurmanager">
		N serie non existant: <input type="text" name="serie" maxlength="50"/><br/>
		Type: <input type="text" name="type" maxlength="20"/><br/>
		Adresse: <input type="text" name="adr" maxlength="50"/><br/>
		Emplacement: <input type="text" name="emp" maxlength="50"/><br/>
		Longitude: <input type="number" step="0.0001" name="lon" maxlength="50"/><br/>
		Latitude: <input type="number" step="0.0001" name="lat" maxlength="50"/><br/>
		Intervention: <input type="date" name="int"/><br/>
		Commentaire: <input type="text" name="com" maxlength="50"/><br/>
	<input type='submit' name='action' value='store'/>
	</form>
	<h2>Modify Distributeur:</h2>
	<form method ="GET" action="distributeurmanager">
		N serie existant: <input type="text" name="serie" maxlength="50"/><br/>
		Adresse: <input type="text" name="adr" maxlength="50"/><br/>
		Emplacement: <input type="text" name="emp" maxlength="50"/><br/>
		Longitude: <input type="number" step="0.0001" name="lon" maxlength="50"/><br/>
		Latitude: <input type="number" step="0.0001" name="lat" maxlength="50"/><br/>
		Intervention: <input type="date" name="int"/><br/>
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