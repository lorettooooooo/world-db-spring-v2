<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
<form action="login" method="post">
	<input type="text" name="username" placeholder="username"/>
	<input type="submit" value="Controlla validit� username">
</form>
${loginErrorMessage}
</body>
</html>