package com.tom.project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.tom.common.beans.ObjectMapper;
import com.tom.common.constant.Constant;
import com.tom.common.dao.GenericDao;
import com.tom.professional.ProjectAdditionalTime;
import com.tom.professional.ProjectScheduleByPro;
import com.tom.util.CommonUtil;
import com.tom.util.JsonUtil;

@Service
public class ProjectServiceImpl implements ProjectService, Constant {

	@Autowired
	GenericDao<Project> projectDao;

	@Autowired
	GenericDao<ProjectFinalize> projectFinalizeDao;

	@Autowired
	GenericDao<ProjectSchedule> projectScheduleDao;

	@Autowired
	GenericDao<ProjectScheduleByPro> projectScheduleByProDao;

	@Autowired
	GenericDao<ProjectAdditionalTime> projectAdditionalTimeDao;

	@Override
	public Project createProject(Project firmProfile) throws Exception {
		return projectDao.saveOrUpdate(firmProfile);
	}

	@Override
	public Project getProject(Long firmProfileId) throws Exception {
		return projectDao.get(Project.class, firmProfileId);
	}

	@Override
	public List<Map<String, Object>> getProjectByUserId(Long userId, String projectStatus, Boolean isTopRecordNeeded)
			throws Exception {
		StringBuilder queryString = new StringBuilder();
		Map<String, Object> map = new HashMap<>();
		String strForTo2Record = "";
		if (isTopRecordNeeded) {
			strForTo2Record = " TOP 2 ";
		}
		queryString.append(
				"EXEC TOM_SearchProject @argUserId = :userId, @argProjectStatus = :projectStatus, @strForTo2Record = :strForTo2Record");
		map.put(USER_ID, userId);
		map.put("strForTo2Record", strForTo2Record);
		map.put(PROJECT_STATUS, projectStatus);
		List<Map<String, Object>> projectList = projectDao.executeSqlSelectMap(queryString, map);
		if (!projectList.isEmpty()) {
			return projectList;
		} else {
			return null;
		}
	}

	@Override
	public ProjectSchedule createProjectScheduleWithPro(ProjectSchedule projectSchedule) throws Exception {
		return projectScheduleDao.saveOrUpdate(projectSchedule);
	}

	@Override
	public Project getProjectByProjectId(Long projectId) throws Exception {
		return projectDao.get(Project.class, projectId);
	}

	@Override
	public ProjectSchedule getFirmScheduleByScheduleId(Long projectScheduleId) throws Exception {
		return projectScheduleDao.get(ProjectSchedule.class, projectScheduleId);
	}

	@Override
	public ProjectScheduleByPro scheduleSendByPro(ProjectScheduleByPro projectScheduleByPro) throws Exception {
		return projectScheduleByProDao.saveOrUpdate(projectScheduleByPro);
	}

	@Override
	public ProjectScheduleByPro getProScheduleByProScheduleId(Long projectScheduleByProId) throws Exception {
		return projectScheduleByProDao.get(ProjectScheduleByPro.class, projectScheduleByProId);
	}

	@Override
	public Integer updateMeetingStatus(Long projectScheduleByProId) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder query = new StringBuilder();
		query.append(
				" update ProjectScheduleByProTable SET isCompletedByAdmin = 1 WHERE projectScheduleByProId = :projectScheduleByProId ");
		Map<String, Object> map = new HashMap<>();
		map.put(PROJECT_SCHEDULE_BY_PRO_ID, projectScheduleByProId);
		return projectScheduleByProDao.executeSqlUpdate(query, map);
	}

	@Override
	public Integer declineMeetingByPro(Long projectScheduleId) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder query = new StringBuilder();
		query.append(
				" update ProjectScheduleTable SET isDeclineByPro = 1 WHERE projectScheduleId = :projectScheduleId ");
		Map<String, Object> map = new HashMap<>();
		map.put(PROJECT_SCHEDULE_ID, projectScheduleId);
		return projectScheduleByProDao.executeSqlUpdate(query, map);
	}

	@Override
	public ProjectFinalize saveProjectFinalize(ProjectFinalize projectFinalize, ObjectNode json) throws Exception {
		ProjectFinalize saveProjectFinalize = projectFinalizeDao.saveOrUpdate(projectFinalize);
		if (saveProjectFinalize.getProjectFinalizeId() != null) {
			ObjectMapper mapper = ObjectMapper.getObjectMapper();
			ProjectAdditionalTime projectAdditionalTime = mapper.convertValue(json.get(PROJECT_FINALIZE),
					ProjectAdditionalTime.class);
			projectAdditionalTime.setProjectFinalizeId(projectFinalize.getProjectFinalizeId());
			projectAdditionalTime.setStatus(0);
			saveAddtionalTime(projectAdditionalTime);
		}
		return saveProjectFinalize;
	}

	@Override
	public Integer declineByFirmBeforeMeeting(Long projectId, Long proProfileId) throws Exception {
		StringBuilder query = new StringBuilder();
		query.append(
				" update ProjectScheduleTable SET isDeclineByFirm = 1 WHERE projectId = :projectId AND proProfileId= :proProfileId ");
		Map<String, Object> map = new HashMap<>();
		map.put(PROJECT_ID, projectId);
		map.put(PRO_PROFILE_ID, proProfileId);
		return projectScheduleByProDao.executeSqlUpdate(query, map);
	}

	@Override
	public Integer declineByFirmAfterMeeting(Long projectId, Long proProfileId) throws Exception {
		StringBuilder query = new StringBuilder(" UPDATE PSBPT SET isDeclineByFirmAfterMeeting = 1 ");
		query.append(" FROM ProjectScheduleByProTable PSBPT ");
		query.append(" INNER JOIN ProjectScheduleTable PST ON PST.projectScheduleId = PSBPT.projectScheduleId ");
		query.append(" WHERE  PST.proProfileId = :proProfileId AND PST.projectId = :projectId");
		Map<String, Object> map = new HashMap<>();
		map.put(PROJECT_ID, projectId);
		map.put(PRO_PROFILE_ID, proProfileId);
		return projectScheduleByProDao.executeSqlUpdate(query, map);
	}

	@Transactional
	@Override
	public Integer declineByProAfterMeeting(Long projectId, Long proProfileId, Long projectScheduleId)
			throws Exception {
		StringBuilder query = new StringBuilder(" UPDATE PSBPT SET isDeclineByProAfterMeeting = 1 ");
		query.append(" FROM ProjectScheduleByProTable PSBPT ");
		query.append(" INNER JOIN ProjectScheduleTable PST ON PST.projectScheduleId = PSBPT.projectScheduleId ");
		query.append(" WHERE  PST.proProfileId = :proProfileId AND PST.projectId = :projectId");
		query.append(" AND PST.projectScheduleId = :projectScheduleId");
		Map<String, Object> map = new HashMap<>();
		map.put(PROJECT_ID, projectId);
		map.put(PRO_PROFILE_ID, proProfileId);
		map.put(PROJECT_SCHEDULE_ID, projectScheduleId);
		return projectScheduleByProDao.executeSqlUpdate(query, map);
	}

	@Override
	public Integer projectFinalizeByFirm(Long projectFinalizeId) throws Exception {
		StringBuilder query = new StringBuilder();
		query.append(
				" update ProjectFinalizetable SET isFinalizeByFirm = 1 WHERE projectFinalizeId = :projectFinalizeId");
		Map<String, Object> map = new HashMap<>();
		map.put(PROJECT_FINALIZE_ID, projectFinalizeId);
		return projectFinalizeDao.executeSqlUpdate(query, map);
	}

	@Override
	public Integer projectFinalizeByAdmin(Long projectFinalizeId, Long projectId) throws Exception {
		StringBuilder query = new StringBuilder();
		query.append(
				" update ProjectFinalizetable SET isFinalizeByAdmin = 1 WHERE projectFinalizeId = :projectFinalizeId");
		Map<String, Object> map = new HashMap<>();
		map.put(PROJECT_FINALIZE_ID, projectFinalizeId);
		projectFinalizeDao.executeSqlUpdate(query, map);
		return updateProjectStatusAsAccepted(projectId);
	}

	@Override
	public Integer changeProjectStatus(Long projectId, String projectStatus) throws Exception {
		StringBuilder query = new StringBuilder();
		query.append(
				" update ProjectTable SET status = :status, modifiedDate = GETUTCDATE() WHERE projectId = :projectId");
		Map<String, Object> map = new HashMap<>();
		map.put(PROJECT_ID, projectId);
		map.put(STATUS, projectStatus);
		return projectDao.executeSqlUpdate(query, map);
	}

	private Integer updateProjectStatusAsAccepted(Long projectId) throws Exception {
		StringBuilder query = new StringBuilder();
		query.append(" update ProjectTable SET status = :status  WHERE projectId = :projectId");
		Map<String, Object> map = new HashMap<>();
		map.put(STATUS, ACCEPTED);
		map.put(PROJECT_ID, projectId);
		return projectDao.executeSqlUpdate(query, map);
	}

	@Override
	public List<Map<String, Object>> getDashBoardProject(String argFrom, Long userId, Long proProfileId)
			throws Exception {
		StringBuilder queryString = new StringBuilder();
		Map<String, Object> map = new HashMap<>();
		queryString.append(
				"EXEC TOM_DashboardProjectList @argUserId = :userId, @argFrom = :argFrom, @proProfileId = :proProfileId");
		map.put(USER_ID, userId);
		map.put(PRO_PROFILE_ID, proProfileId);
		map.put(ARG_FROM, argFrom);
		map.put(PRO_PROFILE_ID, proProfileId);
		List<Map<String, Object>> projectList = projectDao.executeSqlSelectMap(queryString, map);
		if (!projectList.isEmpty()) {
			return projectList;
		} else {
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> getLast2ProjectOfFirm(Long userId, Long proProfileId) throws Exception {
		StringBuilder query = new StringBuilder();
		Map<String, Object> column = new HashMap<>();
		query.append(" SELECT TOP 2 PT.projectName,PT.createdDate,PT.projectId, PST.proProfileId,PT.isShortOrLong,  ");
		query.append(" CT.cityName,CT.cityId, PT.expertise, PT.workLocation, PT.hours, PT.hoursDuration, ");
		query.append(" PT.targetDate, PT.projectDescription  ");
		query.append(" FROM ProjectTable PT ");
		query.append(
				" LEFT JOIN ProjectScheduleTable PST ON PST.projectId  = PT.projectId AND PST.proProfileId = :proProfileId ");
		query.append(" LEFT JOIN CityMasterTable CT ON CT.cityId  = PT.cityId");
		query.append(" WHERE PT.createdById = :userId AND PT.status = 'Open' order by PT.createdDate desc ");
		column.put(USER_ID, userId);
		column.put(PRO_PROFILE_ID, proProfileId);
		List<Map<String, Object>> projectList = projectDao.executeSqlSelectMap(query, column);
		if (!projectList.isEmpty()) {
			return projectList;
		} else {
			return null;
		}

	}

	@Override
	public Integer saveAmends(Long projectFinalizeId, Long userId, String amends) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder query = new StringBuilder();
		query.append(
				" update ProjectFinalizeTable SET amends = :amends,modifiedDate = GETUTCDATE(),modifiedById = :modifiedById  WHERE projectFinalizeId = :projectFinalizeId ");
		Map<String, Object> map = new HashMap<>();
		map.put(PROJECT_ADDITIONAL_TIME_ID, projectFinalizeId);
		map.put(AMENDS, amends);
		map.put("modifiedById", userId);
		return projectFinalizeDao.executeSqlUpdate(query, map);
	}

	@Override
	public ProjectAdditionalTime saveAddtionalTime(ProjectAdditionalTime projectAdditionalTime) throws Exception {

		StringBuilder query = new StringBuilder();
		query.append(
				" update ProjectAdditionalTimeTable SET status = 0,modifiedDate = GETUTCDATE(),modifiedById = :modifiedById  WHERE projectFinalizeId = :projectFinalizeId ");
		Map<String, Object> map = new HashMap<>();
		map.put(PROJECT_FINALIZE_ID, projectAdditionalTime.getProjectFinalizeId());
		map.put(MODIFIEDBY_ID, CommonUtil.getContextUser().getUserId());
		projectAdditionalTimeDao.executeSqlUpdate(query, map);
		return projectAdditionalTimeDao.saveOrUpdate(projectAdditionalTime);
	}

	@Override
	public Integer saveAdditonalReqAmends(Long projectAdditionalTimeId, Long userId, String amends) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder query = new StringBuilder();
		query.append(
				" update ProjectAdditionalTimeTable SET amends = :amends,modifiedDate = GETUTCDATE(),modifiedById = :modifiedById  WHERE projectAdditionalTimeId = :projectAdditionalTimeId ");
		Map<String, Object> map = new HashMap<>();
		map.put(PROJECT_ADDITIONAL_TIME_ID, projectAdditionalTimeId);
		map.put(AMENDS, amends);
		map.put("modifiedById", userId);
		return projectAdditionalTimeDao.executeSqlUpdate(query, map);
	}

	@Override
	public Integer addtionalReqFinalizeByFirm(Long projectAdditionalTimeId) throws Exception {
		StringBuilder query = new StringBuilder();
		query.append(
				" update ProjectAdditionalTimeTable SET isFinalizeByFirm = 1,modifiedById = :modifiedById WHERE projectAdditionalTimeId = :projectAdditionalTimeId");
		Map<String, Object> map = new HashMap<>();
		map.put(PROJECT_ADDITIONAL_TIME_ID, projectAdditionalTimeId);
		map.put(MODIFIEDBY_ID, CommonUtil.getContextUser().getUserId());
		return projectAdditionalTimeDao.executeSqlUpdate(query, map);
	}

	@Override
	public Integer declineAdditonalReqByFirm(Long projectAdditionalTimeId) throws Exception {
		StringBuilder query = new StringBuilder();
		query.append(
				" update ProjectAdditionalTimeTable SET isDeclineByFirm = 1,modifiedById = :modifiedById WHERE projectAdditionalTimeId = :projectAdditionalTimeId");
		Map<String, Object> map = new HashMap<>();
		map.put(PROJECT_ADDITIONAL_TIME_ID, projectAdditionalTimeId);
		map.put(MODIFIEDBY_ID, CommonUtil.getContextUser().getUserId());
		return projectAdditionalTimeDao.executeSqlUpdate(query, map);
	}

	@Override
	public Integer additonalReqApproveByAdmin(Long projectAdditionalTimeId) throws Exception {
		StringBuilder query = new StringBuilder();
		query.append(
				" update ProjectAdditionalTimeTable SET isFinalizeByAdmin = 1,modifiedById = :modifiedById WHERE projectAdditionalTimeId = :projectAdditionalTimeId");
		Map<String, Object> map = new HashMap<>();
		map.put(PROJECT_ADDITIONAL_TIME_ID, projectAdditionalTimeId);
		map.put(MODIFIEDBY_ID, CommonUtil.getContextUser().getUserId());
		this.mergDatatoFinalize(projectAdditionalTimeId);
		return projectAdditionalTimeDao.executeSqlUpdate(query, map);

	}

	private void mergDatatoFinalize(Long projectAdditionalTimeId) throws Exception {
		StringBuilder query = new StringBuilder();
		query.append(
				"EXEC TOM_MergeAddtionalReqToFinalizeDetails @projectAdditionalTimeId = :projectAdditionalTimeId ");
		Map<String, Object> map = new HashMap<>();
		map.put(PROJECT_ADDITIONAL_TIME_ID, projectAdditionalTimeId);
		projectAdditionalTimeDao.executeSqlUpdate(query, map);
	}
}
