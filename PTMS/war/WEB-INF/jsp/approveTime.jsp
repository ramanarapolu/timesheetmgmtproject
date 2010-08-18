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
<div id="bodyheader">My Projects</div><br></br>
<div id="bodystyle">
<s:if test="approveTSList != null">
<table id="datatable">
	<tr>
		<th><s:text name="Project Code" /></th>
		<th><s:text name="Project Name" /></th>
		<th><s:text name="Project Team" /></th>
		<th><s:text name="Project Department" /></th>
	</tr>

	<s:iterator value="approveTSList" status="status">
	
		<tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
		<td><a href="<s:url action="approveTimesheets">
		<s:param name="projectID" value="projectCode"/>
				</s:url>">
					<s:property value="projectCode"/></a></td>
			
			<td class="nowrap"><s:property value="projectName"/></td>
			<td class="nowrap"><s:property value="team"/></td>
			<td class="nowrap"><s:property value="department"/></td>
			
		</tr>
	</s:iterator>
</table>
</s:if>
<br></br>
<s:else>
No projects in the system.
</s:else>

</div>


</body>
</html>

