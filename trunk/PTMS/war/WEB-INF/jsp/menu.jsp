<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<head>
<title>My page</title>
<sx:head debug="true" extraLocales="en-us,nl-nl,de-de" />
<style type="text/css">
@import "<%=request.getContextPath()%>/style/basestyle.css";
<%@ page import="com.herguan.cs562.model.User" %>
</style>
</head>
 
<% System.out.println("in jsp " +((User)session.getAttribute("User")).getFirstName());%>
 
<div id="menustyle">
<b>Hello <%= ((User)session.getAttribute("User")).getFirstName() %>!</b>
<b>Hello <%= ((User)session.getAttribute("User")).getRole() %>!</b>  
<ul>
	<%if(((User)session.getAttribute("User")).getRole().equals("Student") ||
			((User)session.getAttribute("User")).getRole().equals("Admin")||
			((User)session.getAttribute("User")).getRole().equals("Supervisor")
			
	){ %>
	<li>Timesheets
	<ul>
		<li><a href="<s:url action="viewTime"/>" >View timesheets</a></li>
		<li><a href="<s:url action="submitTime"/>" >Submit timesheets</a></li>
	</ul>
	</li>
	<% } %>
	<%if(((User)session.getAttribute("User")).getRole().equals("Supervisor") ||
			((User)session.getAttribute("User")).getRole().equals("Admin")
	){ %>	
	<li>Supervisor
	<ul>
		<li><a href="<s:url action="approveTime"/>" >Approve timesheets</a></li>
	</ul>
	</li>
	<% } %>
	
	<%if(((User)session.getAttribute("User")).getRole().equals("Admin")){ %>	
	<li>Admin
	<ul>
		<li><a href="<s:url action="createUser"/>" >Create user</a></li>
		<li><a href="<s:url action="createProject"/>" >Create project</a></li>
		<li><a href="<s:url action="createDepartment"/>" >Create department</a></li>
	</ul>
	</li>
	<% } %>
</ul>


</div>
