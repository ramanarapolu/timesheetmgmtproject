<%@taglib uri="/struts-tags" prefix="s"%>
<head>
<title>My page</title>
<style type="text/css">
@import "<%=request.getContextPath()%>/style/basestyle.css";
<%@page
 
import="com.herguan.cs562.model.User"
 
%>
</style>
</head>

<% System.out.println("in jsp " +((User)session.getAttribute("User")).getFirstName());%>

<div id="menustyle"><b>Hello <%= ((User)session.getAttribute("User")).getFirstName() %>!</b>
<b>Hello <%= ((User)session.getAttribute("User")).getRole() %>!</b>

<ul>
	<%if(((User)session.getAttribute("User")).getRole().equals("Student") ||
			((User)session.getAttribute("User")).getRole().equals("Supervisor")
			
	){ %>
	<li>Timesheets
	<ul>
		<li><a href="<s:url action="viewTime"/>">View timesheets</a></li>
		<%if(((User)session.getAttribute("User")).getRole().equals("Student") ||
			((User)session.getAttribute("User")).getRole().equals("Supervisor")
			
	){ %>
		<li><a href="<s:url action="showSubmitTime"/>">Submit
		timesheets</a></li>
		<% } %>
	</ul>
	</li>
	<% } %>
	<%if(((User)session.getAttribute("User")).getRole().equals("Supervisor") 
	){ %>
	<li>Supervisor
	<ul>
		<li><a href="<s:url action="approveTime"/>">Approve
		timesheets</a></li>
	</ul>
	</li>
	<% } %>

	<%if(((User)session.getAttribute("User")).getRole().equals("Admin")){ %>
	<li>Admin
	<ul>
		<li><a href="<s:url action="createUser"/>">Create user</a></li>
		<li>Create/View project
		<ul>
			<li><a href="<s:url action="showCreateProject"/>">Create
			project</a></li>
			<li><a href="<s:url action="showProject"/>">View projects</a></li>
		</ul>
		</li>
		<li>Create/View department
		<ul>
			<li><a href="<s:url action="showCreateDepartment"/>">Create
			department</a></li>
			<li><a href="<s:url action="showDepartment"/>">View
			department</a></li>
		</ul>
		</li>
	</ul>
	</li>
	<% } %>
	<li><a href="<s:url action="logout"/>" >Logout</a></li>

</ul>


</div>
