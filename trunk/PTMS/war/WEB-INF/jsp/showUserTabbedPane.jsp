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


			<sx:div id="one" label="View Student" theme="ajax"
				labelposition="top">
				<div id="bodyheader">View Student Users</div>
				<br></br>

				<div id="bodystyle">


					<table id="datatable">
						<tr>
							<th><s:text name="First Name" /></th>
							<th><s:text name="Last Name" /></th>
							<th><s:text name="Login" /></th>
							<th><s:text name="Role" /></th>
							<th><s:text name="Student ID" /></th>
						</tr>


						<s:iterator value="allUsers" status="status">
						<s:if test="role=='Student'" >
						<!-- "%{typeAsString=='BOOL'} || %{typeAsString=='ENUM'}" "role=='Student'"-->
						
							<tr>
								<td class="nowrap"><s:property
									value="firstName" /></td>
								<td class="nowrap"><s:property value="lastName" /></td>
								<td class="nowrap"><s:property value="login" /></td>
								<td class="nowrap"><s:property value="role" /></td>
								<td class="nowrap"><s:property value="studentID" /></td>

							</tr>
						</s:if>
						</s:iterator>
					</table>
				 <br></br>
				</div>

			</sx:div>

			<sx:div id="one" label="View Staff" theme="ajax"
				labelposition="top">
				<div id="bodyheader">View Staff Users</div>
								<br></br>

				<div id="bodystyle">


					<table id="datatable">
						<tr>
							<th><s:text name="First Name" /></th>
							<th><s:text name="Last Name" /></th>
							<th><s:text name="Login" /></th>
							<th><s:text name="Role" /></th>
							<th><s:text name="Employee ID" /></th>
						</tr>


						<s:iterator value="allUsers" status="status">
						<s:if test= "%{role=='Staff' || role=='Supervisor'}">
							<!-- "%{typeAsString=='BOOL'} || %{typeAsString=='ENUM'}" "role=='Student'"-->
							<tr>
								<td class="nowrap"><s:property	value="firstName" /></td>
								<td class="nowrap"><s:property value="lastName" /></td>
								<td class="nowrap"><s:property value="login" /></td>
								<td class="nowrap"><s:property value="role" /></td>
								<td class="nowrap"><s:property value="employeeID" /></td>

							</tr>
						</s:if>
						</s:iterator>
					</table>
				 <br></br>
				</div>

			</sx:div>


		</sx:tabbedpanel></td>
	</tr>
</table>
</body>
</html>