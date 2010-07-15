<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../../style/style.css" type="text/css" rel="stylesheet" />
<title>Please login</title>
</head>
<body id="layout">
<div id="layout">
<div id="header">
<table>
	<tr>
		<td class="headerheight">Timesheet Management System</td>
	</tr>
</table>
</div>
<div><s:actionerror /> <s:form action="home" method="post">
	<s:textfield name="username" label="UserName"></s:textfield>
	<s:textfield name="password" label="Password"></s:textfield>
	<s:submit name="login" value="login"></s:submit>
</s:form></div>
<div id="footer">
<table>
	<tr>
		<td>CS 562 Student Project - Summer 2010</td>
	</tr>
</table>
</div>
</div>
</body>
</html>

<!-- 
<div id="menustyle">
<a href="<s:url action="friendsLink"/>" >Create User</a><br>
<a href="<s:url action="officeLink"/>" >Create Department</a><br>
background-image: url(../images/bg.jpg);
 -->
