package com.herguan.cs562.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Key;
import com.herguan.cs562.db.PMF;
import com.herguan.cs562.model.Allocation;
import com.herguan.cs562.model.Project;
import com.herguan.cs562.model.Timesheet;
import com.herguan.cs562.model.TimesheetAllocationVO;
import com.herguan.cs562.model.TimesheetScreenVO;
import com.herguan.cs562.model.User;
import com.herguan.cs562.model.ViewTimesheetScreenVO;
import com.herguan.cs562.model.WeekEnum;

public class TimesheetDAO {

	public static void updateAllocation(Allocation allocation,
			Timesheet timesheet) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			Allocation all = pm.getObjectById(Allocation.class, allocation
					.getKey());
			all.getTimesheetKeys().add(timesheet.getKey());
			pm.makePersistent(all);
			System.out.println("Success allocation creation");
		} finally {
			pm.close();
		}
	}

	public static void createTimesheet(Timesheet timesheet) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			pm.makePersistent(timesheet);
			System.out.println("Success Timesheet creation");
		} finally {
			pm.close();
		}
	}

	public static ViewTimesheetScreenVO getViewTimesheetScreenVO(User user) {

		ViewTimesheetScreenVO viewTimesheetScreenVO = null;

		try {
			PersistenceManager pm = PMF.get().getPersistenceManager();

			Allocation allocation = null;
			Set<Key> timesheetKeys = null;
			Key allocationKey = null;
			Key timesheetKey = null;
			Timesheet t = null;
			String departmentName = null;
			Project project = null;
			String supervisorName = null;

			TimesheetAllocationVO timesheetAllocationVO = null;
			Map<String, TimesheetAllocationVO> weekTimesheetAllocationMap = new HashMap<String, TimesheetAllocationVO>();

			Set<Key> allocationsetKeys = user.getAllocationSet();

			if (allocationsetKeys == null || allocationsetKeys.size() == 0) {
				return null;
			}
			List<String> weeks = new ArrayList<String>();

			for (Iterator<Key> it = allocationsetKeys.iterator(); it.hasNext();) {
				allocationKey = it.next();

				allocation = pm.getObjectById(Allocation.class, allocationKey);
				project = pm.getObjectById(Project.class, allocation
						.getProjectKey());

				timesheetKeys = allocation.getTimesheetKeys();

				if (allocation.getIsActive().equals(Boolean.TRUE)) {
					departmentName = project.getDeptName();
					User supervisor = pm.getObjectById(User.class, project
							.getManagerKey());
					supervisorName = supervisor.getLastName() + ","
							+ supervisor.getFirstName();
				}
				for (Iterator<Key> it1 = timesheetKeys.iterator(); it1
						.hasNext();) {
					timesheetKey = it1.next();

					t = pm.getObjectById(Timesheet.class, timesheetKey);

					timesheetAllocationVO = new TimesheetAllocationVO();
					if (t.getFridayHours() != null) {
						timesheetAllocationVO.setFridayHours(t.getFridayHours()
								.toString());
					}
					if (t.getMondayHours() != null) {
						timesheetAllocationVO.setMondayHours(t.getMondayHours()
								.toString());
					}

					timesheetAllocationVO.setProjectDisplay(project
							.getProjectCode()
							+ "-" + project.getProjectName());
					timesheetAllocationVO.setProjectName(project
							.getProjectName().toString());
					if (t.getSaturdayHours() != null) {
						timesheetAllocationVO.setSaturdayHours(t
								.getSaturdayHours().toString());
					}
					if (t.getSundayHours() != null) {
						timesheetAllocationVO.setSundayHours(t.getSundayHours()
								.toString());
					}
					if (t.getThursdayHours() != null) {
						timesheetAllocationVO.setThursdayHours(t
								.getThursdayHours().toString());
					}

					timesheetAllocationVO.setTimesheet_status(t
							.getTimesheet_status());
					if (t.getTotalWeekHours() != null) {
						timesheetAllocationVO.setTotalWeekHours(t
								.getTotalWeekHours().toString());
					}
					if (t.getTuesdayHours() != null) {
						timesheetAllocationVO.setTuesdayHours(t
								.getTuesdayHours().toString());
					}
					if (t.getWednesdayHours() != null) {
						timesheetAllocationVO.setWednesdayHours(t
								.getWednesdayHours().toString());
					}
					timesheetAllocationVO.setWeekId(t.getWeekId());

					if (timesheetAllocationVO.getWeekId().equals(
							WeekEnum.CURRENT.toString())) {

						weekTimesheetAllocationMap.put(WeekEnum.CURRENT
								.toString(), timesheetAllocationVO);
						weeks.add(WeekEnum.CURRENT.toString());

					} else if (timesheetAllocationVO.getWeekId().equals(
							WeekEnum.CURRENT_MINUS1.toString())) {

						weeks.add(WeekEnum.CURRENT_MINUS1.toString());
						weekTimesheetAllocationMap.put(WeekEnum.CURRENT_MINUS1
								.toString(), timesheetAllocationVO);
					} else if (timesheetAllocationVO.getWeekId().equals(
							WeekEnum.CURRENT_PLUS1.toString())) {

						weeks.add(WeekEnum.CURRENT_PLUS1.toString());
						weekTimesheetAllocationMap.put(WeekEnum.CURRENT_PLUS1
								.toString(), timesheetAllocationVO);
					} else if (timesheetAllocationVO.getWeekId().equals(
							WeekEnum.CURRENT_MINUS2.toString())) {

						weeks.add(WeekEnum.CURRENT_MINUS2.toString());
						weekTimesheetAllocationMap.put(WeekEnum.CURRENT_MINUS2
								.toString(), timesheetAllocationVO);

					} else if (timesheetAllocationVO.getWeekId().equals(
							WeekEnum.CURRENT_PLUS2.toString())) {

						weeks.add(WeekEnum.CURRENT_PLUS2.toString());
						weekTimesheetAllocationMap.put(WeekEnum.CURRENT_PLUS2
								.toString(), timesheetAllocationVO);
					}

				}

			}

			Integer employeeID = null;
			Integer studentID = null;

			if (user.getRole().equals("Student")) {
				studentID = user.getStudentID();

			}
			if (user.getRole().equals("Staff")
					|| user.getRole().equals("Supervisor")) {
				employeeID = user.getEmployeeID();

			}

			viewTimesheetScreenVO = new ViewTimesheetScreenVO();
			viewTimesheetScreenVO.setDepartmentName(departmentName);
			viewTimesheetScreenVO.setEmployeeID(employeeID);
			viewTimesheetScreenVO.setStudentID(studentID);
			viewTimesheetScreenVO.setFirstName(user.getFirstName());
			viewTimesheetScreenVO.setLastName(user.getLastName());
			viewTimesheetScreenVO.setSupervisorName(supervisorName);
			viewTimesheetScreenVO.setWeeks(weeks);
			viewTimesheetScreenVO
					.setWeekTimesheetAllocationMap(weekTimesheetAllocationMap);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return viewTimesheetScreenVO;
	}

	public static TimesheetScreenVO getTimesheetScreenVO(User user) {

		TimesheetScreenVO timesheetScreenVO = null;

		try {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Allocation allocation = null;
			Set<Key> allocationsetKeys = user.getAllocationSet();

			if (allocationsetKeys == null || allocationsetKeys.size() == 0) {
				return null;
			}
			Set<Key> timesheetKeys = null;
			Key allocationKey = null;
			List<Timesheet> timesheetList = null;
			for (Iterator<Key> it = allocationsetKeys.iterator(); it.hasNext();) {
				allocationKey = it.next();
				allocation = pm.getObjectById(Allocation.class, allocationKey);
				if (allocation != null && allocation.getIsActive()) {
					timesheetKeys = allocation.getTimesheetKeys();
					break;
				}

			}

			Timesheet t = null;
			Key timesheetKey = null;
			if (timesheetKeys.size() > 0) {
				timesheetList = new ArrayList();
			}

			Map<String, TimesheetAllocationVO> weekTimesheetMap = new HashMap<String, TimesheetAllocationVO>();

			TimesheetAllocationVO timesheetAllocationVO = null;
			Project project = pm.getObjectById(Project.class, allocation
					.getProjectKey());

			for (Iterator<Key> it = timesheetKeys.iterator(); it.hasNext();) {
				timesheetKey = it.next();
				t = pm.getObjectById(Timesheet.class, timesheetKey);

				timesheetAllocationVO = new TimesheetAllocationVO();
				if (t.getFridayHours() != null) {
					timesheetAllocationVO.setFridayHours(t.getFridayHours()
							.toString());
				}
				if (t.getMondayHours() != null) {
					timesheetAllocationVO.setMondayHours(t.getMondayHours()
							.toString());
				}

				timesheetAllocationVO.setProjectDisplay(project
						.getProjectCode()
						+ "-" + project.getProjectName());
				timesheetAllocationVO.setProjectName(project.getProjectName());
				if (t.getSaturdayHours() != null) {
					timesheetAllocationVO.setSaturdayHours(t.getSaturdayHours()
							.toString());
				}
				if (t.getSundayHours() != null) {
					timesheetAllocationVO.setSundayHours(t.getSundayHours()
							.toString());
				}
				if (t.getThursdayHours() != null) {
					timesheetAllocationVO.setThursdayHours(t.getThursdayHours()
							.toString());
				}

				timesheetAllocationVO.setTimesheet_status(t
						.getTimesheet_status());
				if (t.getTotalWeekHours() != null) {
					timesheetAllocationVO.setTotalWeekHours(t
							.getTotalWeekHours().toString());
				}
				if (t.getTuesdayHours() != null) {
					timesheetAllocationVO.setTuesdayHours(t.getTuesdayHours()
							.toString());
				}
				if (t.getWednesdayHours() != null) {
					timesheetAllocationVO.setWednesdayHours(t
							.getWednesdayHours().toString());
				}
				timesheetAllocationVO.setWeekId(t.getWeekId());

				timesheetList.add(t);
				if (t.getWeekId().equals(WeekEnum.CURRENT.toString())) {
					weekTimesheetMap.put(WeekEnum.CURRENT.toString(),
							timesheetAllocationVO);
				} else if (t.getWeekId().equals(
						WeekEnum.CURRENT_MINUS1.toString())) {
					weekTimesheetMap.put(WeekEnum.CURRENT_MINUS1.toString(),
							timesheetAllocationVO);
				} else if (t.getWeekId().equals(
						WeekEnum.CURRENT_PLUS1.toString())) {
					weekTimesheetMap.put(WeekEnum.CURRENT_PLUS1.toString(),
							timesheetAllocationVO);
				} else if (t.getWeekId().equals(
						WeekEnum.CURRENT_MINUS2.toString())) {
					weekTimesheetMap.put(WeekEnum.CURRENT_MINUS2.toString(),
							timesheetAllocationVO);
				} else if (t.getWeekId().equals(
						WeekEnum.CURRENT_PLUS2.toString())) {
					weekTimesheetMap.put(WeekEnum.CURRENT_PLUS2.toString(),
							timesheetAllocationVO);
				}

			}

			Integer employeeID = null;
			Integer studentID = null;
			String supervisorName = null;

			if (user.getRole().equals("Student")) {
				studentID = user.getStudentID();

			}
			if (user.getRole().equals("Staff")
					|| user.getRole().equals("Supervisor")) {
				employeeID = user.getEmployeeID();

			}

			User supervisor = pm.getObjectById(User.class, project
					.getManagerKey());
			supervisorName = supervisor.getLastName() + ","
					+ supervisor.getFirstName();

			List<String> weeks = new ArrayList<String>();
			weeks.add(WeekEnum.CURRENT.toString());
			weeks.add(WeekEnum.CURRENT_PLUS1.toString());
			weeks.add(WeekEnum.CURRENT_PLUS2.toString());
			weeks.add(WeekEnum.CURRENT_MINUS2.toString());
			weeks.add(WeekEnum.CURRENT_MINUS1.toString());

			timesheetScreenVO = new TimesheetScreenVO(employeeID, studentID,
					project, allocation, user.getFirstName(), user
							.getLastName(), supervisorName, project
							.getDeptName(), weeks, timesheetList,
					weekTimesheetMap);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return timesheetScreenVO;
	}

}
