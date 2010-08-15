<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.google.appengine.api.datastore.KeyFactory"%>
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
<script>
     function show_details() {
     dojo.event.topic.publish("show_detail");
     }
    </script>
<body id="layout">

<div id="bodyheader">Create/Edit User</div>
<br></br>

<div id="bodystyle"><s:actionerror /> <s:form action="createUser"
	method="post">
	<s:textfield name="user.firstName" label="First Name"></s:textfield>
	<s:textfield name="user.lastName" label="Last Name"></s:textfield>
	<s:textfield name="user.login" label="Login"></s:textfield>
	<s:textfield name="user.password" label="Password"></s:textfield>

	<s:select label="Department" name="deptID" list="departments"
		listKey="deptCode" listValue="deptName" required="true"
		value="deptName" />
		<s:form id="frm_demo" name="frm_demo" theme="simple">
	<s:select label="Role" name="role_name" list="roles" listKey="key"
		listValue="role_name" required="true" value="key"
		onchange="javascript:show_details();return false;" />

	<sx:div showLoadingText="false" id="details" 
		theme="ajax" listenTopics="show_detail" formId="frm_demo">
		
		
		<s:if test="role_name='Student'"> Student</s:if>
		<s:if test="role_name='Staff'"> Staff</s:if>
		
	</sx:div>
	</s:form>
	<s:if test="actionName=='editUser'">
		<s:submit name="actionName" value="Edit User"></s:submit>
	</s:if>
	<s:if test="actionName=='createUser'">
		<s:submit name="actionName" value="Create User"></s:submit>
	</s:if>
	
</s:form></div>

</body>
</html>


