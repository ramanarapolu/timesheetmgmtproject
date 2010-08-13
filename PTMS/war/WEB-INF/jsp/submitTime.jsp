<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<sx:head debug="true" />
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
<div id="bodyheader">Submit Timesheet</div>
<br></br>
<div id="bodystyle">
<table id="datatable">
	<tr>
		<th class="datacolumn"><s:text name="ID" /></th>
		<th class="datacolumn"><s:text name="First Name" /></th>
		<th class="datacolumn"><s:text name="Last Name" /></th>
		<th class="datacolumn"><s:text name="Supervisor" /></th>
		<th class="datacolumn"><s:text name="Department" /></th>
	</tr>
	<tr>
		<td class="datacolumn"><s:if test="user.role=='Student'">
			<s:text name="timesheetScreenVO.studentID" />
		</s:if> <s:if test="user.role=='Staff'">
			<s:text name="timesheetScreenVO.employeeID" />
		</s:if></td>
		<td class="datacolumn"><s:text name="timesheetScreenVO.firstName" /></td>
		<td class="datacolumn"><s:text name="timesheetScreenVO.lastName" /></td>
		<td class="datacolumn"><s:text
			name="timesheetScreenVO.supervisorName" /></td>
		<td class="datacolumn"><s:text
			name="timesheetScreenVO.departmentName" /></td>
	</tr>
</table>

<br></br>

<s:form id="frm_demo" name="frm_demo" theme="simple"  action="submitTime" method="post">
	<table>
		<tr>
			<td><s:select id="select0" label="Weeks" name="chosenWeek"
				headerKey="-1" headerValue="Select Week" list="weeks" multiple="false"
				required="true" onchange="javascript:show_details();return false;" />
			</td>

		</tr>
		<tr>
		
			<td>
				
			<s:url id="d_url" action="AjaxCall.action" >
			
			</s:url> 
			
				<sx:div showLoadingText="false" id="details" href="%{d_url}"
					theme="ajax" listenTopics="show_detail" formId="frm_demo">
				</sx:div>

			</td>
		
		</tr>
	</table>
</s:form>
</div>
</body>
</html>
