<html>
	<head>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<meta charset="UTF-8" />
		<title>Mijn account</title>
		<script src = ""></script>
		<link rel = "stylesheet" type = "text/css" href = "/Huistaken-webapp/css/welcome.css">
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		
	</head>
	<body>
		<a href="/Huistaken-webapp/huistakenapp/welcome.jsp">Huistaken lijst</a>
		<a href="/Huistaken-webapp/huistakenapp/huistaken.jsp">Huistaak omschrijvingen</a>
		<a href="/Huistaken-webapp/huistakenapp/account.jsp">Mijn account</a>
		<a href="/Huistaken-webapp/huistakenapp/logout.do">log uit</a>
		<div class = "naam">
			Naam: <c:out value = "${loggedinUser.getVoorNaam()}"></c:out>
			<c:out value = "${loggedinUser.getAchterNaam()}"></c:out>
		</div>
		<div class = "email">
			Email: <c:out value = "${loggedinUser.getAccount().getEmail()}"></c:out>
		</div>
		
		<div class = "Verander">Verander account: </div>
		<form action = "/Huistaken-webapp/huistakenapp/Bewoneraanpassing.do" method = "post">
			<input type = "text" name = "voornaam" value = "voornaam">
			<input type = "text" name = "achternaam" value = "achternaam">
			<input type = "text" name = "email" value = "email">
			<input type = "text" name = "kamernummer" value = "kamernummer">
			<input type = "submit" value = "wijzig">
		</form>
		<div class = "Verwijder">Verwijder account: </div>
		<form action = "/Huistaken-webapp/huistakenapp/Verwijderaccount.do" method = "post">
			<input type = "text" name = "email" value = "email">
			<input type = "password" name = "password" value = "wachtwoord">
			<input type = "submit" value = "verwijder">
		</form>
	</body>
</html>