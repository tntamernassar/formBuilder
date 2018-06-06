<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>${formName} submissions</title>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style_submissions.css" />
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/submissions.js"></script>

</head>

<style>
body{
    background-image: url("Images/submissions_bg.jpeg");
	background-size: cover;
}


</style>
<body>

<a href="#" class="float" onClick="goHome()"><b>Home</b></a>

<div class="title_handler"><center><b>${formName}</b></center></div>
<div class="center_div">
	<br />
		<center>${table}</center>
	<br/>
	</div>


</body>
</html>