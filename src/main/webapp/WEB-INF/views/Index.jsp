<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Spring Boot & Data JPA</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>
	<div class="container">
		<div class="col-md-offset-2 col-md-7">
			<h2 class="text-center">Welcome to Spring Boot & Data JPA Example</h2>
		</div>
	</div>
	<div class="panel-body">
		<table class="table table-striped table-bordered">
		<tr>	
			<td><a href="/customer/showForm">Add Customer</a></td>
			<td><a href="/customer/list">Get Customer List</a></td>
		</tr>
		</table>
	</div>
</body>
</html>
