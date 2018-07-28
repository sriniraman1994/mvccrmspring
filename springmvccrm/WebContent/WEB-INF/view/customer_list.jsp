<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link type = "text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/style.css"/>
</head>
<body>
	<div id="wrapper">Customer RelationShip Management:</div>
	<div id="table-div">
		<div id="content">
			<table id="customer_table">
				<tr>
					<th>first-name</th>
					<th>last-name</th>
					<th>email</th>
				</tr>
				<c:forEach var="temp" items="${customers}">
					<tr>
						<td>${temp.firstName}</td>
						<td>${temp.lastName}</td>
						<td>${temp.email}</td>
					</tr>
				</c:forEach>
				

			</table>
		</div>
	</div>
</body>

</html>