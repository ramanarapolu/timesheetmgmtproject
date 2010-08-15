<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<html>
<head>
<sx:head debug="true" />
</head>
<body>

<table width="100%">
	<tr>
		<td width="100%"><sx:tabbedpanel id="test">

			<sx:div id="one" label="Create Student" theme="ajax"
				labelposition="top">
				<div id="bodyheader">Create/Edit User</div>
				<br></br>

				<div id="bodystyle"><s:actionerror /> <s:form
					action="createStudent" method="post">
					<s:textfield name="user.firstName" label="First Name"></s:textfield>
					<s:textfield name="user.lastName" label="Last Name"></s:textfield>
					<s:textfield name="user.login" label="Login"></s:textfield>
					<s:textfield name="user.password" label="Password"></s:textfield>
					<s:textfield name="user.studentID" label="Student ID"></s:textfield>
					<s:textfield name="user.programID" label="Program ID"></s:textfield>
					<s:textfield name="user.programName" label="Program Name"></s:textfield>
					
					<s:if test="actionName=='editUser'">
						<s:submit name="actionName" value="Edit User"></s:submit>
					</s:if>
					<s:if test="actionName=='createUser'">
						<s:submit name="actionName" value="Create User"></s:submit>
					</s:if>

				</s:form></div>


			</sx:div>

			<sx:div id="one" label="Create Staff" theme="ajax"
				labelposition="top">
				<div id="bodyheader">Create/Edit User</div>
				<br></br>

				<div id="bodystyle"><s:actionerror /> <s:form
					action="createStaff" method="post">
					<s:textfield name="user.firstName" label="First Name"></s:textfield>
					<s:textfield name="user.lastName" label="Last Name"></s:textfield>
					<s:textfield name="user.login" label="Login"></s:textfield>
					<s:textfield name="user.password" label="Password"></s:textfield>
					<s:textfield name="user.employeeID" label="Employee ID"></s:textfield>
					<s:select label="Department" name="deptID" list="departments"
						listKey="deptCode" listValue="%{deptCode + '-' +deptName}"  required="true"
						value="%{deptCode + '-' +deptName}"  />
					
					
					<s:if test="actionName=='editUser'">
						<s:submit name="actionName" value="Edit User"></s:submit>
					</s:if>
					<s:if test="actionName=='createUser'">
						<s:submit name="actionName" value="Create User"></s:submit>
					</s:if>

				</s:form></div>


			</sx:div>


		</sx:tabbedpanel></td>
	</tr>
</table>
</body>
</html>