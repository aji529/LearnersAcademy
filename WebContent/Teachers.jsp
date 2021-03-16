<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Teachers</title>
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

	<div style="margin: 20px" align="center">Manage Teachers</div>
	<form action="TeacherServlet" method="post">
		<div align='center'>

			<div class='field'>
				<span style="padding-right: 60px;">Teacher Name :</span><span><input
					type="text" name="fac_Name"
					value="<%=request.getAttribute("teacher_Name")%>"
					style="width: 300px;" /></span>
			</div>


			<div class='field'>
				<span>Class_Subject allocation: </span> <select
					name="sub_Class_alloc" style="padding-left: 5px; width: 300px;"
					multiple>

					<c:forEach items="${listMapping}" var="maps">
						<option value="${maps.assId}"
							<c:if test="${maps.assId eq mid}">selected="selected"</c:if>>${maps.assId}-->
							<c:forEach items="${listCategory}" var="cls">
								<c:if test="${cls.classId eq maps.classID }">${cls.className}</c:if>
							</c:forEach> -----
							<c:forEach items="${listSubs}" var="subs">
								<c:if test="${subs.subId eq maps.subId }">${subs.subName}</c:if>
							</c:forEach>
						</option>
					</c:forEach>

				</select>
			</div>

			<div align='center' class='field'>
				<button type="submit" value="Save">Save</button>
			</div>

			<div style="margin: 40px">
				<table class="center">
					<tr>
						<th>Id</th>
						<th>Teacher Name</th>
						<th>Class</th>
						<th>Subject</th>
						<th>Edit</th>
						<th>Delete</th>
					</tr>
					<c:forEach items="${faclist}" var="fac">
						<c:if test="${fac.scPair.size() eq 0}">
							<tr>
								<td>${fac.teachId}</td>
								<td>${fac.teachName}</td>
								<td>No Class Assigned</td>
								<td>No Subject Assigned</td>
								<td><a href='FacEditServlet?id=${fac.teachId}'>Edit</a></td>
								<td><a href='FacDelServlet?id=${fac.teachId}'>Delete</a></td>
							</tr>
						</c:if>
						<c:if test="${fac.scPair.size() ne 0}">
							<c:forEach items="${fac.scPair}" var="scp">
								<tr>
									<td>${fac.teachId}</td>
									<td>${fac.teachName}</td>
									<td><c:forEach items="${listCategory}" var="cls">
											<c:if test="${cls.classId eq  scp.classID}">${cls.className}</c:if>
										</c:forEach></td>
									<td><c:forEach items="${listSubs}" var="subs">
											<c:if test="${subs.subId eq  scp.subId}">${subs.subName}</c:if>
										</c:forEach></td>
									<td><a href='FacEditServlet?id=${fac.teachId}'>Edit</a></td>
									<td><a href='FacDelServlet?id=${fac.teachId}'>Delete</a></td>
								</tr>
							</c:forEach>
						</c:if>
					</c:forEach>
				</table>

			</div>

		</div>
	</form>

</body>
</html>