<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<style>
.field {
	margin-top: 10px;
	margin-bottom: 10px;
}
</style>
</head>
<body>
	<div>Welcome to Learners Academy</div>
	<div class="field">
		
		<form action="initStudents" method="post">
			<button type="submit" name = "action" value="save">Manage Student</button>
		</form>
	</div>

	<div class="field">
		<form action="initSubjects" method="post">
			<button type="submit">Manage Subject</button>
		</form>
	</div>

	<div class="field">
		<form action="initTeachers" method ="post">
			<button type="submit">Manage Teachers</button>
		</form>
	</div>

	<div class="field">
		<form action="initClasses" method="post">
			<button type="submit" >Manage Class</button>
		</form>
	</div>
</body>
</html>