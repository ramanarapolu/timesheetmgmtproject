<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style type="text/css"> @import "<%=request.getContextPath() %>/style/basestyle.css";</style>
<div id="header">
<table>
	<tr>
		<td class="headerheight">Timesheet Management System</td>
	</tr>
</table>
</div>
Welcome to Timesheet Management System. Please login!
<div class="floatright"><s:actionerror /> <s:form action="home" method="post">
	<s:textfield name="username" label="UserName"></s:textfield>
	<s:textfield name="password" label="Password"></s:textfield>
	<s:submit name="login" value="login"></s:submit>
</s:form></div>

