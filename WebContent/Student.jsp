<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>



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
		<span> <a href="HomePage.jsp">Home</a> <a href="LogoutServlet">Logout</a></span>
	</div>
	
	<div style="margin: 20px" align="center">Add Students to the list</div>
	<form action="StudentServlet" method="post">
		<div align='center'>

			<div class='field'>
				<span>Student Name :</span><span> <input type="text"
					name="stud_Name" value="<%=request.getAttribute("stud_Name")%>"/>
				</span>
			</div>
			
			<div class='field'>
				<span style="padding-right: 10px;">Student Age :</span><span
					class='field'><input type="number" name="stud_Age" value="<%=request.getAttribute("stud_Age")%>"/></span>
			</div>
			
			<div class='field'>
				<span>Student Class :</span> <select name="stud_Class"
					style="padding-left: 5px; width: 176px;">
					<option value="">Select Class</option>
					<c:forEach items="${listCategory}" var="category">
						<option value="${category.classId}"
							<c:if test="${category.classId eq cid}">selected="selected"</c:if>>
							${category.className}</option>

                    >
					</c:forEach>
				</select>
			</div>
		</div>

		<div align='center' style="margin-top: 30px;">
			<button type="submit" name = "action" value="save">Save Student</button>
			

		</div>

		<div style="margin: 40px">
			<table class="center">
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Age</th>
					<th>Class</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
				<c:forEach items="${studentsList}" var="stud">
					<tr>
						<td>${stud.studId}</td>
						<td>${stud.studName}</td>
						<td>${stud.studAge}</td>
						<td>${stud.studCl.className}</td>
						<td><a
							href='StudEditServlet?id=${stud.studId}&cid=${stud.studCl.classId}'>Edit</a></td>
						<td><a href='StudDelServlet?id=${stud.studId}'>Delete</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>

	</form>
	
</body>
</html>