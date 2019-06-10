<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page
	import="com.axelor.service.ContactServiceImpl,com.axelor.domains.*,java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<style>
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #dddddd;
}
</style>
</head>
<body>

	<%-- <%

ContactServiceImpl service=new ContactServiceImpl();

	List<ContactDetails> contactList=service.getAllcontacts();
	
	request.setAttribute("list",contactList);  

	
%> --%>

	<a href="contactbook?action=insert">Add Contact</a>

	<h2>Address Of </h2>

	<table>
		<tr>
		
			<th>Address</th>
			<th>Add Address</th>
		</tr>

	
<c:forEach items="${list}" var="u">


			<tr>
				<td>${u.getAid() }</td>
				<td>${u.getAddress()}</td>
				<td><a href="contactbook?action=EditAddress&id=${u.getAid()}">Edit Address</a></td>
			</tr>

	</c:forEach>

	</table>



</body>
</html>
