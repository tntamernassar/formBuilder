<%@ 
	page import = "java.util.*"  language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"
	
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/jquery.js"></script>
<script>



function displayWizard() {
	
}

</script>




<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>Forum List</title>

<style>
body {
    background-image: url("Images/home_page.jpg");
    background-size: cover;
}
.center_div{
	border:0px solid #7A7A7A;
	border-radius: 7px;
	background-color: rgba(255, 255, 255, 0.7);
	width:97%;
	font-family:  Apple Chancery, cursive;
}
.t_header{
	border-bottom: 1px solid #7A7A7A;
    text-align: center;
    font-size:30px;
}

.t_object {
    border-bottom: 1px solid #7A7A7A;
    text-align: center;
    font-size:30px;
}

.t_object:hover {background-color: #4B9B88;}

table{border-collapse: collapse;}

.float{
	font-family:"Lucida Sans Unicode", "Lucida Grande", sans-serif;
	position:fixed;
	width:60px;
	height:60px;
	top:3%;
	right:3%;
	background-color:#EB4040;
	color:#fff;
	border-radius:50px;
	text-align:center;
	font-size:40px;
	text-decoration:none;
	line-height: 60px; 
	font-weight: 400px;
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
	</div>
	
	</center>
	

	<a href="WizardServlet" class="float" onClick="displayWizard()">+</a>
	
	



</body>
</html>

