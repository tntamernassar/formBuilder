<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>Create you form</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style_wizard.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/wizard.js">

</script>


</head>
<style>
body{
    background-image: url("Images/wizard_bg.jpeg");
	background-size: cover;
}
</style>
<body>

<a href="#" class="float" onClick="publish()"><b>Publish</b></a>
<a href="#" class="homefloat" onClick="goHome()"><b>Home</b></a>
<div id="handler">

<div id="container">

	<div id="Title_wizard">
	Create a Form
	</div>
	
	<div id="form_name_wizard">
		<input type="text" autocomplete="off"  class="question" id="fname" onchange="FormNameChange(this.value)" placeholder="Form name">
	</div>
	
	<hr>
	
	<div id="LT">
		<input type="text" id="label_name" class="element_label_name" placeholder="Lable name">
	</div>

	

	
	<div id="Types">
		
		 <label class="Types_container">Text
  			<input type="radio" checked="checked" name="radio">
  			<span class="checkmark"></span>
		</label>
		
		<label class="Types_container">Color
  			<input type="radio"  name="radio">
  			<span class="checkmark"></span>
		</label>
		
		<label class="Types_container">Date
  			<input type="radio"  name="radio">
  			<span class="checkmark"></span>
		</label>
		
		<label class="Types_container">Email
  			<input type="radio"  name="radio">
  			<span class="checkmark"></span>
		</label>
		
		<label class="Types_container">Telephone Number
  			<input type="radio"  name="radio">
  			<span class="checkmark"></span>
		</label>
		
		<label class="Types_container">Number
  			<input type="radio"  name="radio">
  			<span class="checkmark"></span>
		</label>
		
		<label class="Types_container">Paragraph
  			<input type="radio"  name="radio">
  			<span class="checkmark"></span>
		</label>
		
	</div> 

	<div id="Add">
		 <input type="submit" id="addComponent" value="Add Label +" onClick="addComponents()">
	</div>
	
</div>


<div id="formDisplay">
	
	
	<div id="header_formDisplay">
	Title
	</div>
	<div id="current_form_display">
		${form}
		<br/>
	</div>

	</div>
</div>


</body>
</html>