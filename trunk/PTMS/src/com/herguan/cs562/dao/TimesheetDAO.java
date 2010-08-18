package com.herguan.cs562.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.herguan.cs562.db.PMF;
import com.herguan.cs562.model.Allocation;
import com.herguan.cs562.model.ApproveTimesheetScreenVO;
import com.herguan.cs562.model.Project;
import com.herguan.cs562.model.Timesheet;
import com.herguan.cs562.model.TimesheetAllocationVO;
import com.herguan.cs562.model.TimesheetScreenVO;
import com.herguan.cs562.model.TimesheetStatusEnum;
import com.herguan.cs562.model.User;
import com.herguan.cs562.model.UserAllocationVO;
import com.herguan.cs562.model.ViewTimesheetScreenVO;
import com.herguan.cs562.model.WeekEnum;

public class TimesheetDAO {

	public static void updateTimesheets(List<UserAllocationVO> userAllocationList){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
		
		UserAllocationVO userAllocationVO = null;
		
		List<TimesheetAllocationVO> tsAllocList = null;
		
		TimesheetAllocationVO timesheetAllocationVO = null;
		
		Key userkey = null;
		String project = null;
		User user = null;
		Key allocKey = null;
		Allocation allocation = null;
		Set<Key> timesheetKeys = null;
		Key timesheetKey = null;
		Timesheet timesheet = null;
		if(userAllocationList != null && userAllocationList.size() >0){
			for (Iterator<UserAllocationVO> it = userAllocationList.iterator(); it.hasNext();) {
				
				userAllocationVO = it.next();
				userkey = userAllocationVO.getUserKey();
				
				tsAllocList =	userAllocationVO.getTsVO();
				if(tsAllocList != null && tsAllocList.size() >0){
					for (Iterator<TimesheetAllocationVO> it1 = tsAllocList.iterator(); it1.hasNext();) {
						
						timesheetAllocationVO = it1.next();
						project = timesheetAllocationVO.getProjectDisplay().substring(0, 
								timesheetAllocationVO.getProjectDisplay().indexOf("-"));
						System.out.println("proejct "+project);
						
						user = pm.getObjectById(User.class, userkey);
						
						for (Iterator<Key> it2 = user.getAllocationSet().iterator(); it2.hasNext();) {
							
							allocKey = it2.next();
							if(allocKey.equals(KeyFactory.createKey(Allocation.class
									.getSimpleName(), user.getLogin()
									+project))){
								System.out.println("update timesheets for allockey"+allocKey);
								
								allocation = pm.getObjectById(Allocation.class, allocKey);
								timesheetKeys = allocation.getTimesheetKeys();
								
								for (Iterator<Key> it3 = timesheetKeys.iterator(); it3.hasNext();) {
									
									timesheetKey = it3.next();
									timesheet = pm.getObjectById(Timesheet.class,timesheetKey);
									timesheet.setTimesheet_status(TimesheetStatusEnum.APPROVED);
									pm.makePersistent(timesheet);
								}
							}
						}
					}
				}
				
			}
		}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			pm.close();
		}
		
	}
	public static List<ApproveTimesheetScreenVO> getApproveTimesheetScreenVO(
			User user) {

		List<ApproveTimesheetScreenVO> approveTSList = null;
		ApproveTimesheetScreenVO approveTimesheetScreenVO = null;

		try {
			PersistenceManager pm = PMF.get().getPersistenceManager();

			List<User> dbUsers = UserDAO.getAllUsers();
			
			Query query = pm.newQuery(Project.class, "managerKey == keyParam");
			query.declareImports("import com.google.appengine.api.datastore.Key;");
			query.declareParameters("Key keyParam");

			List<Project> results = (List<Project>) query.execute(user.getKey());
	
			
			User u = null;
			Allocation allocation = null;
			Set<Key> timesheetKeys = null;
			TimesheetAllocationVO timesheetAllocationVO = null;
			Key timesheetKey = null;
			Timesheet t = null;
			Map<Key, List<UserAllocationVO>> projectUserMap = null;
			List<UserAllocationVO> userAllocList = null;
			UserAllocationVO userAllocationVO = null;
			List<TimesheetAllocationVO> tsVOList = null;
			Key allocationKey = null;
			
			if (results.size() > 0) {
				approveTSList = new ArrayList<ApproveTimesheetScreenVO>();
			}
			
			if (results.iterator().hasNext()) {

				for (Project p : results) {

					approveTimesheetScreenVO = new ApproveTimesheetScreenVO();
					approveTimesheetScreenVO.setProjectKey(p.getKey());
					approveTimesheetScreenVO.setProjectName(p.getProjectName());
					approveTimesheetScreenVO.setProjectCode(p.getProjectCode());
					approveTimesheetScreenVO.setDepartment(p.getDeptName());

					Set<Key> userKeys = null;

					userKeys = p.getUsersTeam();
					projectUserMap = new HashMap<Key, List<UserAllocationVO>>();
					// get the list of user's allocation
					for (Iterator<User> it = dbUsers.iterator(); it.hasNext();) {

						u = it.next();
						if (userKeys.contains(u.getKey())) {
							approveTimesheetScreenVO.setTeam(u.getLastName()
									+ " " + u.getFirstName());
						}

						Set<Key> allocationsetKeys = u.getAllocationSet();

						if (u.getAllocationSet() != null
								&& u.getAllocationSet().size() > 0) {
							userAllocList = new ArrayList<UserAllocationVO>();
						}
						List<String> weeks = new ArrayList<String>();

						for (Iterator<Key> it1 = allocationsetKeys.iterator(); it1
								.hasNext();) {
							userAllocationVO = new UserAllocationVO();
							userAllocationVO.setUserKey(u.getKey());
							userAllocationVO.setFirstName(u.getFirstName());
							userAllocationVO.setLastName(u.getLastName());

							allocationKey = it1.next();

							allocation = pm.getObjectById(Allocation.class,
									allocationKey);
							timesheetKeys = allocation.getTimesheetKeys();
							if (timesheetKeys != null
									&& timesheetKeys.size() > 0) {
								tsVOList = new ArrayList<TimesheetAllocationVO>();
							}

							for (Iterator<Key> it2 = timesheetKeys.iterator(); it2
									.hasNext();) {

								timesheetKey = it2.next();

								t = pm.getObjectById(Timesheet.class,
										timesheetKey);

								timesheetAllocationVO = new TimesheetAllocationVO();
								if (t.getFridayHours() != null) {
									timesheetAllocationVO.setFridayHours(t
											.getFridayHours().toString());
								}
								if (t.getMondayHours() != null) {
									timesheetAllocationVO.setMondayHours(t
											.getMondayHours().toString());
								}

								timesheetAllocationVO.setProjectDisplay(p
										.getProjectCode()
										+ "-" + p.getProjectName());
								timesheetAllocationVO.setProjectName(p
										.getProjectName().toString());

								if (t.getSaturdayHours() != null) {
									timesheetAllocationVO.setSaturdayHours(t
											.getSaturdayHours().toString());
								}
								if (t.getSundayHours() != null) {
									timesheetAllocationVO.setSundayHours(t
											.getSundayHours().toString());
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

								tsVOList.add(timesheetAllocationVO);
							}
							userAllocationVO.setTsVO(tsVOList);
							userAllocList.add(userAllocationVO);
						}
						projectUserMap.put(p.getKey(), userAllocList);
						
					}
					approveTimesheetScreenVO.setProjectUserMap(projectUserMap);
					approveTSList.add(approveTimesheetScreenVO);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		
		if(approveTSList != null && approveTSList.size() >0){
			System.out.println("list is not null");
			ApproveTimesheetScreenVO a = null;
			Set keySet = null;
			Map asVomap = null;
			Key key = null;
			for (Iterator<ApproveTimesheetScreenVO> it = approveTSList.iterator(); it.hasNext();) {
				System.out.println("approve ts vo");
				a = it.next();
				System.out.println(" values :"+a.getProjectCode()+" ; "+a.getProjectName()+":"+a.getDepartment()+":"+a.getTeam());
				asVomap = a.getProjectUserMap();
				System.out.println(" asVomap :"+asVomap);
				if(asVomap != null && asVomap.size() >0){
					System.out.println(" asVomap :"+asVomap.keySet());
					keySet = asVomap.keySet();
					for (Iterator<Key> it1= keySet.iterator(); it1.hasNext();) {
						key = it1.next();
						System.out.println(" asVomap :"+asVomap.get(key));
						
					}
				}
				
				
			
				
			}
		}
		return approveTSList;
	}

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
