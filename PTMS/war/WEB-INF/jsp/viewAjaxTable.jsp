
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
		<th><s:text name="Status" /></th>
	</tr>
	<tr>
	
		<td><s:text name="timesheetAllocationVO.projectDisplay" ></s:text></td>
		<td><s:property value="%{timesheetAllocationVO.sundayHours}" default="0"/></td>
		<td><s:property value="%{timesheetAllocationVO.mondayHours}" default="0"/></td>
		<td><s:property value="%{timesheetAllocationVO.tuesdayHours}" default="0"/></td>
		<td><s:property value="%{timesheetAllocationVO.wednesdayHours}" default="0"/></td>
		<td><s:property value="%{timesheetAllocationVO.thursdayHours}" default="0"/></td>
		<td><s:property value="%{timesheetAllocationVO.fridayHours}" default="0"/></td>
		<td><s:property value="%{timesheetAllocationVO.saturdayHours}" default="0"/></td>
		<td><s:property value="%{timesheetAllocationVO.timesheet_status}" /></td>
		
	</tr>

</table>
