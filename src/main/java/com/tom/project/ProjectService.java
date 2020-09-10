package com.tom.project;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.tom.common.exception.ExceptionWrapper;
import com.tom.professional.ProjectAdditionalTime;
import com.tom.professional.ProjectScheduleByPro;

@Service
public interface ProjectService {

	Project createProject(Project project) throws Exception;

	Project getProject(Long projectId) throws Exception;

	List<Map<String, Object>> getProjectByUserId(Long userId, String projectStatus, Boolean isTopRecordNeeded)
			throws Exception;

	ProjectSchedule createProjectScheduleWithPro(ProjectSchedule projectSchedule) throws Exception;

	Project getProjectByProjectId(Long projectId) throws Exception;

	ProjectSchedule getFirmScheduleByScheduleId(Long projectScheduleId) throws Exception;

	ProjectScheduleByPro scheduleSendByPro(ProjectScheduleByPro projectScheduleByPro) throws Exception;

	ProjectScheduleByPro getProScheduleByProScheduleId(Long projectScheduleByProId) throws Exception;

	Integer updateMeetingStatus(Long projectScheduleByProId) throws Exception;

	Integer declineMeetingByPro(Long projectScheduleId) throws Exception;

	ProjectFinalize saveProjectFinalize(ProjectFinalize projectFinalize, ObjectNode json) throws Exception;

	Integer declineByFirmBeforeMeeting(Long projectId, Long proProfileId) throws Exception;

	Integer declineByFirmAfterMeeting(Long projectId, Long proProfileId) throws Exception;

	Integer declineByProAfterMeeting(Long projectId, Long proProfileId, Long projectScheduleId) throws Exception;

	Integer projectFinalizeByFirm(Long projectFinalizeId) throws Exception;

	Integer projectFinalizeByAdmin(Long projectFinalizeId, Long projectId) throws Exception;

	Integer changeProjectStatus(Long projectId, String projectStatus) throws Exception;

	List<Map<String, Object>> getDashBoardProject(String argFrom, Long userId, Long proProfileId) throws Exception;

	List<Map<String, Object>> getLast2ProjectOfFirm(Long userId, Long proProfileId) throws Exception;

	Integer saveAmends(Long projectFinalizeId, Long userId, String amends) throws Exception;

	ProjectAdditionalTime saveAddtionalTime(ProjectAdditionalTime projectAdditionalTime) throws Exception;

	Integer saveAdditonalReqAmends(Long projectAdditionalTimeId, Long userId, String amends) throws Exception;

	Integer addtionalReqFinalizeByFirm(Long projectAdditionalTimeId) throws Exception;

	Integer declineAdditonalReqByFirm(Long projectAdditionalTimeId) throws Exception;

	Integer additonalReqApproveByAdmin(Long projectAdditionalTimeId) throws Exception;

}
