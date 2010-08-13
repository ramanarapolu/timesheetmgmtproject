
<%@ taglib prefix="s" uri="/struts-tags"%>


Timesheet for the week of <s:property value="chosenWeek" />

<table id="datatable">
	<tr>
		<th><s:text name="Project Code" /></th>
		<th><s:text name="Sunday" /></th>
		<th><s:text name="Monday" /></th>
		<th><s:text name="Tuesday" /></th>
		<th><s:text name="Wednesday" /></th>
		<th><s:text name="Thursday" /></th>
		<th><s:text name="Friday" /></th>
		<th><s:text name="Saturday" /></th>
	</tr>
	<tr>
	
		<td><s:text name="%{timesheetAllocationVO.projectDisplay}"></s:text></td>
		<td><s:textfield label="Sunday" name="timesheetAllocationVO.sundayHours"
			value="%{timesheetAllocationVO.sundayHours}" theme="simple" /></td>
		<td><s:textfield label="Monday"
			name="timesheetAllocationVO.mondayHours" value="%{timesheetAllocationVO.mondayHours}"
			theme="simple" /></td>
		<td><s:textfield label="Tuesday"
			name="timesheetAllocationVO.tuesdayHours" value="%{timesheetAllocationVO.tuesdayHours}"
			theme="simple" /></td>
		<td><s:textfield label="Wednesday"
			name="timesheetAllocationVO.wednesdayHours" value="%{timesheetAllocationVO.wednesdayHours}"
			theme="simple" /></td>
		<td><s:textfield label="Thursday"
			name="timesheetAllocationVO.thursdayHours" value="%{timesheetAllocationVO.thursdayHours}"
			theme="simple" /></td>
		<td><s:textfield label="Friday"
			name="timesheetAllocationVO.fridayHours" value="%{timesheetAllocationVO.fridayHours}"
			theme="simple" /></td>
		<td><s:textfield label="Saturday"
			name="timesheetAllocationVO.saturdayHours" value="%{timesheetAllocationVO.saturdayHours}"
			theme="simple" /></td>
	</tr>

</table>
<s:submit value="Create Timesheet"></s:submit>