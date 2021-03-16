<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Class Management</title>

<style>
table, th, td {
	border: 1px solid black;
}

table.center {
	margin-left: auto;
	margin-right: auto;
	width: 400px;
}

.field {
	margin-top: 10px;
	margin-bottom: 10px;
}
</style>
</head>
<body>
	<div class='field'>
		<span> <a href="HomePage.jsp">Home</a> <a href="Logout">Logout</a></span>
	</div>

	<div class='field' style="margin: 20px" align="center">Add/Edit Classes</div>
	<form action="ClassServlet" method="post">
		<div align='center'>

			<div class='field'>
				<span>Class Name :</span><span><input type="text"
					name="class_Name" value="<%=request.getAttribute("class_Name")%>" /></span>
			</div>

			<div align='center'>
				<button type="submit" value="Add Class">Save</button>
			</div>
		</div>

		<div style="margin: 40px">
			<table class="center">
				<tr>
					<th>Class-Id</th>
					<th>Name</th>
					<th>Edit</th>
					<th>Delete</th>
					<th>View Reports</th>
				</tr>
				<c:forEach items="${listCategory}" var="cls">
					<tr>
						<td>${cls.classId}</td>
						<td>${cls.className}</td>
						<td><a href='ClassEditServlet?id=${cls.classId}'>Edit</a></td>
						<td><a href='ClassDeleteServlet?id=${cls.classId}'>Delete</a></td>
						<td><a href='ViewClassReport?id=${cls.classId}'>View Report</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>

	</form>

</body>
</html>