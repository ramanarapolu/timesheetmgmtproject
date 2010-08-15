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
<div id="bodyheader">View Departments</div>
<br></br>
<div id="bodystyle">
<table id="datatable">
	<tr>
		<th><s:text name="Department Code" /></th>
		<th><s:text name="Department Name" /></th>
	</tr>
	<s:iterator value="departments" status="status">
		<tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
			<td><a href="<s:url action="editDepartment"><s:param name="deptID" value="deptCode"/>
				<s:param name="deptName" value="deptName"/></s:url>">
					<s:property value="deptCode" /></a></td>

			<td class="nowrap"><s:property value="deptName" /></td>
		</tr>
	</s:iterator>
</table>
<br></br>

</div>


</body>
</html>
