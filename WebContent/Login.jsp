<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LearnerAcademy</title>
</head>
<body>
	<form method ='post' action='LoginServlet' >
	<div align ='center' style = "margin: 60px;">
	
		<div style ="margin: 10px;">
			<span    style="margin-right: 20px;">Name:</span>
			<span><input type='text' name = 'uname'/></span>
		</div>
		
		<div style ="margin: 10px;">
			<span>Password:</span>
			<span> <input type ='text' name = 'upass'/></span>	
		</div>
		
		<div style ="margin: 20px;">
			<span> <input type= 'submit' value= 'Login'/></span>
		</div>
	</div>
	
</form>
</body>
</html>