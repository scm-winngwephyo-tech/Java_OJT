<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div align="center">
	<h1>Student List</h1>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>Roll Number</th>
			<th>Name</th>
			<th>Major</th>
			<th>Year</th>
			<th>Email</th>
			<th>Dob</th>
			<th>Action</th>
		</tr>
		<c:forEach var="student" items="${listStudent}">
			<tr>
				<td>${student.id}</td>
				<td>${student.roll_number}</td>
				<td>${student.name}</td>
				<td>${student.major}</td>
				<td>${student.year}</td>
				<td>${student.email}</td>
				<td>${student.dob}</td>
				<td><a href="editStudent?id=${student.id}">Edit</a>
					&nbsp;&nbsp;&nbsp;&nbsp; <a href="deleteStudent?id=${student.id}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<h4>
		New Student Register <a href="${pageContext.request.contextPath}/createStudent">here</a>
	</h4>
</div>