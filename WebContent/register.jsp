<html>
	<head>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<meta charset="UTF-8" />
		<title>Register page</title>
		<script src = ""></script>
		<link rel = "stylesheet" type = "text/css" href = "css/register.css">
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		
	</head>
	<body>
		<a href = "login.jsp">inloggen</a>
		<form id = "registerform" action = "register.do" method = "post">
			<input value = "email" type = "text" name = "email">
			<input value = "wachtwoord" type = "password" name = "password">
			<input value = "wachtwoord" type = "password" name = "passwordcheck">
			<input value = "353 - 366" type = "text" name = "kamernummer">
			<input type = "submit" value = "zend">
		</form>
	</body>
</html>