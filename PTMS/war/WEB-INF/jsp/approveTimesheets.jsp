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
<div id="bodyheader">Approve Timesheets</div>
<br></br>
<div id="bodystyle">
<s:form action="approveTimeDB" method="post">
<s:if test="approveTSList != null">
	<table id="datatable">
		<tr>
			<th><s:text name="First Name" /></th>
			<th><s:text name="Last Name" /></th>
			<th><s:text name="Project Code" /></th>
			<th><s:text name="Sunday" /></th>
			<th><s:text name="Monday" /></th>
			<th><s:text name="Tuesday" /></th>
			<th><s:text name="Wednesday" /></th>
			<th><s:text name="Thursday" /></th>
			<th><s:text name="Friday" /></th>
			<th><s:text name="Saturday" /></th>
		</tr>

		<s:iterator value="userAllocList" status="status">
		<s:iterator value="tsVO" status="status">
			<tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
				<td><s:property value="firstName" /></td>
				<td class="nowrap"><s:property value="lastName" /></td>
				
						
				
						<td><s:property value="projectDisplay"></s:property></td>
						<td><s:property value="sundayHours" default="0"/></td>
						<td><s:property value="mondayHours" default="0"/></td>
						<td><s:property value="tuesdayHours" default="0" /></td>
						<td><s:property	value="wednesdayHours" default="0" /></td>
						<td><s:property	value="thursdayHours" default="0" /></td>
						<td><s:property value="fridayHours" default="0"  /></td>
						<td><s:property	value="saturdayHours" default="0" /></td>

					
				
			</tr>
			</s:iterator>
		</s:iterator>
	</table>
</s:if> <br></br>
<s:else>
No timesheets in the system.
</s:else></div>
<s:submit value="Approve Timesheet"></s:submit>
</s:form>
</body>
</html>

