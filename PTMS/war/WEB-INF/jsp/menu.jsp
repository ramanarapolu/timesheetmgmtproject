<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<head>
<title>My page</title>
<sx:head debug="true" extraLocales="en-us,nl-nl,de-de" />
<style type="text/css">
@import "<%=request.getContextPath()%>/style/basestyle.css";
</style>
</head>


<div id="menustyle">
<ul>
	<li>Timesheets
	<ul>
		<li><a href="<s:url action="viewTime"/>" >View timesheets</a></li>
		<li><a href="<s:url action="submitTime"/>" >Submit timesheets</a></li>
	</ul>
	</li>
	<li>Supervisor
	<ul>
		<li><a href="<s:url action="approveTime"/>" >Approve timesheets</a></li>
	</ul>
	</li>
	<li>Admin
	<ul>
		<li><a href="<s:url action="createUser"/>" >Create user</a></li>
		<li><a href="<s:url action="createProject"/>" >Create project</a></li>
		<li><a href="<s:url action="createDepartment"/>" >Create department</a></li>
	</ul>
	</li>
</ul>


</div>
