<html>
	<head>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<meta charset="UTF-8" />
		<title>Register page</title>
		<script src = ""></script>
		<link rel = "stylesheet" type = "text/css" href = "">
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		
	</head>
	<body>
		<a href = "register.jsp">registreren</a>
		<form action = "login.do" method = "post">
			<input type = "text" value = "email" name = "email">
			<input type = "password" value = "wachtwoord" name = "password">
			<input type = "submit" value = "zend">
		</form>
	</body>
</html>