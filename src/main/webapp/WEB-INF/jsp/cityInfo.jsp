<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<a href="home"> <input type="button" value="torna alla home">
	</a>
	<div><p>utente loggato: ${username}</p></div>
	<form action="cityInfo" method="post">
		cerca una citt�: <input type="text" name="cityName"> <input
			type="submit" value="Cerca">
	</form>
	${errorMessage}
	<table>
		<c:forEach items="${citiesList}" var="city">
			<tr>
				<td>${city.name}</td>
				<td>${city.id}</td>
				<td>${city.population}</td>
				<td>${city.countryCode}</td>
				<td>${city.district}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>