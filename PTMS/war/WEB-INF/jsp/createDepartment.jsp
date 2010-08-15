<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
<style type="text/css">
@import "<%=request.getContextPath()%>/style/basestyle.css";
</style>
</head>
<body id="layout">

<div id="bodyheader">Create/Edit Department</div>
<br></br>

<div id="bodystyle"><s:actionerror /> 
	<s:form action="createDepartment" method="post">
	
	<s:if test="actionName=='editDepartment'">
		<s:textfield name="deptID" label="Department Code" readonly="true"></s:textfield>
	</s:if> 
	<s:if test="actionName=='createDepartment'">
		<s:textfield name="deptID" label="Department Code"></s:textfield>
	</s:if>
	
	<s:textfield name="deptName" label="Department Name"></s:textfield>
	<s:if test="actionName=='editDepartment'">
		<s:submit name="actionName" value="Edit Department"></s:submit>
	</s:if>
	<s:if test="actionName=='createDepartment'">
		<s:submit name="actionName" value="Create Department"></s:submit>
	</s:if>
</s:form></div>

</body>
</html>
