<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.google.appengine.api.datastore.KeyFactory" %>
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

<div id="bodyheader">Create/Edit Project</div>
<br></br>

<div id="bodystyle"><s:actionerror /> 
<s:form	action="createProject" method="post">
	<s:textfield name="projectID" label="Project ID"></s:textfield>
	<s:textfield name="projectName" label="Project Name"></s:textfield>
	<s:select label="Department" name="deptID" list="departments"
		listKey="deptCode" listValue="deptName" required="true"
		value="deptName" />
	<s:select label="Manager" name="manager" list="staffUsers"
		listKey="%{keyToString}" 
		listValue="%{lastName + ' ' +firstName}" 
		required="true" />
	<s:select label="Team" name="team" list="allUsers"
		listKey="%{key}"  listValue="%{lastName + ' ' +firstName}" required="true"
		multiple="true"/>
	
	<s:if test="actionName=='editProject'">
		<s:submit name="actionName" value="Edit Project"></s:submit>
	</s:if>
	<s:if test="actionName=='createProject'">
		<s:submit name="actionName" value="Create Project"></s:submit>
	</s:if>
</s:form></div>

</body>
</html>


