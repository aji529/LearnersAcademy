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

	<div class='field' style="margin: 20px" align="center">Students-List</div>
	<div style="margin: 40px">
		<table class="center">
			<tr>
				<th>Student Id</th>
				<th>Student Name</th>
				<th>Student Age</th>
			</tr>
			<c:forEach items="${studentList}" var="stud">
				<c:if test="${stud.studCl.classId eq clsId}">
					<tr>
						<td>${stud.studId}</td>
						<td>${stud.studName}</td>
						<td>${stud.studAge}</td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
	</div>



	<div class='field' style="margin: 20px" align="center">Teachers
		and Subject Handled</div>
	<div style="margin: 40px">
		<table class="center">
			<tr>
				<th>Teacher Id</th>
				<th>Teacher Name</th>
				<th>Subject Handled</th>
			</tr>


			<c:forEach items="${teacherList}" var="fac">
				<c:if test="${fac.scPair.size() ne 0}">
					<c:forEach items="${fac.scPair}" var="scp">
						<c:if test="${scp.classID eq clsId}">
							<tr>
								<td>${fac.teachId}</td>
								<td>${fac.teachName}</td>
								<td><c:forEach items="${subjectList}" var="subs">
										<c:if test="${subs.subId eq  scp.subId}">${subs.subName}</c:if>
									</c:forEach></td>
							</tr>
						</c:if>
					</c:forEach>
				</c:if>
			</c:forEach>

		</table>
	</div>


	<div class="field">
		<form action="initClasses" method="post">
			<button type="submit">Go back to Manage Class</button>
		</form>
	</div>


</body>
</html>