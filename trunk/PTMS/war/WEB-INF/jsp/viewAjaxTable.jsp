
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
	
		<td><s:text name="timesheetAllocationVO.projectDisplay"></s:text></td>
		<td><s:text name="%{timesheetAllocationVO.sundayHours}"/></td>
		<td><s:text name="%{timesheetAllocationVO.mondayHours}"/></td>
		<td><s:text name="%{timesheetAllocationVO.tuesdayHours}"/></td>
		<td><s:text name="%{timesheetAllocationVO.wednesdayHours}" /></td>
		<td><s:text name="%{timesheetAllocationVO.thursdayHours}"/></td>
		<td><s:text name="%{timesheetAllocationVO.fridayHours}"/></td>
		<td><s:text name="%{timesheetAllocationVO.saturdayHours}" /></td>
	</tr>

</table>
