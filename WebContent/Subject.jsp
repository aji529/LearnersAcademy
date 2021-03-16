<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SubjectPage</title>
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
	<form action="SubjectServlet" method="post">
		<div align='center'>

			<div class='field'>
				<span>Subject Name :</span><span><input type="text"
					name="sub_Name" value="<%=request.getAttribute("sub_Name")%>" /></span>
			</div>
			<div class='field'>
				<span>Subject Class :</span> <select name="sub_Class"
					style="padding-left: 5px; width: 176px;" multiple>
					<c:if test="${cid.size() eq 0}">
						<c:forEach items="${listCategory}" var="category">
							<option value="${category.classId}">
								${category.className}</option>
						</c:forEach>
					</c:if>

					<c:if test="${cid.size() ne 0}">
						<c:forEach items="${listCategory}" var="category">
							<option value="${category.classId}"
								<c:if test="${cid.contains(category.classId)}">selected="selected"</c:if>>
								${category.className}</option>
						</c:forEach>

					</c:if>

				</select>
			</div>
		</div>

		<div align='center'>
			<button type="submit" value="AddSub">Save</button>
		</div>

		<div style="margin: 40px">
			<table class="center">
				<tr>
					<th>Id</th>
					<th>Subject Name</th>
					<th>Class</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
				<c:forEach items="${subjectList}" var="subj">
					<c:if test="${subj.classList.size() eq 0}">
						<tr>
							<td>${subj.subId}</td>
							<td>${subj.subName}</td>
							<td></td>
							<td><a href='SubEditServlet?id=${subj.subId}'>Edit</a></td>
							<td><a href='SubDelServlet?id=${subj.subId}&cid=0'>Delete</a></td>
						</tr>
					</c:if>
					<c:if test="${subj.classList ne null}">
						<c:forEach items="${subj.classList}" var="cls">
							<tr>
								<td>${subj.subId}</td>
								<td>${subj.subName}</td>
								<td>${cls.className}</td>
								<td><a href='SubEditServlet?id=${subj.subId}'>Edit</a></td>
								<td><a
									href='SubDelServlet?id=${subj.subId}&cid=${cls.classId}'>Delete</a></td>
							</tr>
						</c:forEach>
					</c:if>

				</c:forEach>
			</table>
		</div>

	</form>
	
</body>
</html>