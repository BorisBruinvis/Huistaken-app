<html>
	<head>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<meta charset="UTF-8" />
		<title>Huistaken lijst</title>
		<link rel = "stylesheet" type = "text/css" href = "/Huistaken-webapp/css/welcome.css">
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		
	</head>
	<body>
		<a href="/Huistaken-webapp/huistakenapp/welcome.jsp">Huistaken lijst</a>
		<a href="/Huistaken-webapp/huistakenapp/huistaken.jsp">Huistaak omschrijvingen</a>
		<a href="/Huistaken-webapp/huistakenapp/account.jsp">Mijn account</a>
		<a href="/Huistaken-webapp/huistakenapp/logout.do">log uit</a>
		
		<div class = "dezeweek">Deze week: </div>
		
		<form action = "/Huistaken-webapp/huistakenapp/Weekchange.do" method = "post">
			<c:if test = "${week.getWeekNr() != 1}">
				<input type = "submit" name = "change" value = "${week.getWeekNr()-1}">
			</c:if>
			<c:if test = "${week.getWeekNr() != 14}">
				<input type = "submit" name = "change" value = "${week.getWeekNr()+1}">
			</c:if>
		</form>
		
		<c:forEach items = "${week.getHuistaakuitvoeringen()}" var = "item">
			<div class = "huistaakbox">
				<div class = "huistaaktitel">-<c:out value = "${item.getHuistaak().getTitel()}"/></div>
				<div class = "naam">Bewoner: <c:out value = "${item.getBewoner().getVoorNaam()}"/></div>
				<div class = "vink">
				
				<c:if test = "${loggedinUser.equals(item.getBewoner())}">
				
					<form action="/Huistaken-webapp/huistakenapp/Checkbox.do" method = "post">
						<c:if test = "${!item.isDeHuistaakGedaan()}">
							<div class = "checkboxtekst">afkruisen <input type="checkbox" name="vink" value="${item.getId()}"></div>
						</c:if>
						<c:if test = "${item.isDeHuistaakGedaan()}">
							<div class = "checkboxtekst">ongedaan maken <input type="checkbox" name="vink" value="${item.getId()}"></div>
						</c:if>
						<div class = "submit"><input type = "submit" value = "submit" name = "submit"></div>
					</form>
					
				</c:if>
				
				</div>
				
				<c:if test = "${item.isDeHuistaakGedaan()}">
					<div class = "checked">X</div>
				</c:if>
				
			</div>

		</c:forEach>
	</body>
</html>