<html>
	<head>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<meta charset="UTF-8" />
		<title>Huistaak omschrijvingen</title>
		<script src = ""></script>
		<link rel = "stylesheet" type = "text/css" href = "/Huistaken-webapp/css/huistaken.css">
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		
	</head>
	<body>
		<a href="/Huistaken-webapp/huistakenapp/welcome.jsp">Huistaken lijst</a>
		<a href="/Huistaken-webapp/huistakenapp/huistaken.jsp">Huistaak omschrijvingen</a>
		<a href="/Huistaken-webapp/huistakenapp/account.jsp">Mijn account</a>
		<a href="/Huistaken-webapp/huistakenapp/logout.do">log uit</a>
		
		<c:forEach items = "${week.getHuistaken()}" var = "item">
			<div class = "huistaaktitelbox">
				<div class = "huistaaktitel"><c:out value = "${item.getTitel()}"/> Huistaak ${item.getHuistaakId() }</div>
			</div>
			<div class = "huistaakomschrijvingbox">
				<div class = "huistaakomschrijving"><c:out value = "${item.getOmschrijving()}"></c:out></div>
			</div>
		</c:forEach>
		<form action = "/Huistaken-webapp/huistakenapp/huistakenaanpassen.do" method = "post">
			<input type = "text" name = "titelid" value = "1 - 14">
			<input type = "text" name = "titel" value = "nieuwe titel">
			<input type = "text" name = "omschrijving" value = "nieuwe omschrijving">
			<input type = "submit" name = "submit" value = "aanpassen">
		</form>
	</body>
</html>