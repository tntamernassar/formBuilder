<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>Insert title here</title>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style_submit.css" />
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/submit.js" ></script>


</head>

<style>
	body{
	   background-image: url("Images/submit_form_bg.jpeg");
	   background-size: cover;
	}
</style>

<body>

<a href="#" class="float" onclick="submit()"><b>Answer</b></a>
<a href="#" class="home_float" onClick="goHome()"><b>Home</b></a>

<div id="formDisplay">
	
	
	<div id="header_formDisplay">
		<form method="POST" action="SubmitForm" id="resultForm" name="resultForm">
		
			${formName}
			
		</form>
	</div>
	<hr>
	<div id="current_form_display">
		${structure}
		<br/>
	</div>

	</div>
	
	

	



</body>
</html>