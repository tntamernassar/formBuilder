<%@ 
	page import = "java.util.*"  language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"
	
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style_home.css" />

<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>Form List</title>

<style>
body {
    background-image: url("Images/home_page.jpg");
    background-size: cover;
}
</style>

</head>
<body>

	<center>

	<h1 id="welcome_text" style="color:#EB4040;font-family:Apple Chancery, cursive;">Welcome to form Builder</h1>
	
	
	<div class="center_div">
	<br/>
		${table}
	<br/>
	${msg}
	</div>
	
	</center>
	

	<a href="WizardServlet" class="float">+</a>


</body>
</html>

