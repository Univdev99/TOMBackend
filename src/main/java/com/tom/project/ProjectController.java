package com.tom.project;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.tom.common.beans.ConstantStatus;
import com.tom.common.beans.ObjectMapper;
import com.tom.common.beans.Status;
import com.tom.common.constant.Constant;
import com.tom.common.exception.TOMRuntimeException;
import com.tom.professional.ProjectAdditionalTime;
import com.tom.professional.ProjectScheduleByPro;
import com.tom.util.JsonUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/tom/project")
public class ProjectController implements Constant {
	public static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

	@Autowired
	ProjectService projectService;

	@ApiOperation(value = "Create Firm Profile")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "X-Auth-Token", value = "Authorization token", required = true, dataType = "string", paramType = "header"),
			@ApiImplicitParam(name = "requestBody", value = "bbbb", required = true, paramType = "body") })
	@RequestMapping(value = { "/save",
			"/update" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status createProject(HttpServletRequest request) {
		LOGGER.debug("Execute method : createProject()");
		try {
			ObjectMapper mapper = ObjectMapper.getObjectMapper();
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			Project project = mapper.convertValue(json.get(PROJECT), Project.class);
			return new Status(ConstantStatus.OK, "", projectService.createProject(project));
		} catch (Exception e) {
			LOGGER.error("Error in createProject() method :: ", e);
			throw new TOMRuntimeException("Error in createProject()", e);
		}
	}

	@RequestMapping(value = { "/get" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status getProjectByUserId(HttpServletRequest request) {
		LOGGER.debug("Execute method : getProjectByUserId()");
		try {
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (JsonUtil.isRequiredParamNull(json, new String[] { USER_ID })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}
			Boolean isTopRecordNeeded = json.has("isTopRecordNeeded") ? json.get("isTopRecordNeeded").asBoolean()
					: Boolean.FALSE;
			Long userId = json.get(USER_ID).asLong();
			String projectStatus = !JsonUtil.isRequiredParamNull(json, new String[] { PROJECT_STATUS })
					? json.get(PROJECT_STATUS).asText()
					: null;
			return new Status(ConstantStatus.OK, "",
					projectService.getProjectByUserId(userId, projectStatus, isTopRecordNeeded));
		} catch (Exception e) {
			LOGGER.error("Error in getProjectByUserId() method :: ", e);
			throw new TOMRuntimeException("Error in getProjectByUserId()", e);
		}
	}

	@RequestMapping(value = {
			"/getByProjectId", }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status getProjectByProjectId(HttpServletRequest request) {
		LOGGER.debug("Execute method : getProjectByProjectId()");
		try {
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (JsonUtil.isRequiredParamNull(json, new String[] { PROJECT_ID })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}
			Long projectId = json.get(PROJECT_ID).asLong();
			return new Status(ConstantStatus.OK, "", projectService.getProjectByProjectId(projectId));
		} catch (Exception e) {
			LOGGER.error("Error in createProjectScheduleWithFirm() method :: ", e);
			throw new TOMRuntimeException("Error in createProjectScheduleWithFirm()", e);
		}
	}

	@RequestMapping(value = { "/schedule", }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status createProjectScheduleWithPro(HttpServletRequest request) {
		LOGGER.debug("Execute method : createProjectScheduleWithFirm()");
		try {
			ObjectMapper mapper = ObjectMapper.getObjectMapper();
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			ProjectSchedule projectSchedule = mapper.convertValue(json.get(PROJECT_SCHEDULE), ProjectSchedule.class);
			return new Status(ConstantStatus.OK, "", projectService.createProjectScheduleWithPro(projectSchedule));
		} catch (Exception e) {
			LOGGER.error("Error in createProjectScheduleWithFirm() method :: ", e);
			throw new TOMRuntimeException("Error in createProjectScheduleWithFirm()", e);
		}
	}

	@RequestMapping(value = {
			"/schedule/get", }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status getFirmScheduleByScheduleId(HttpServletRequest request) {
		LOGGER.debug("Execute method : getFirmScheduleByScheduleId()");
		try {
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (JsonUtil.isRequiredParamNull(json, new String[] { PROJECT_SCHEDULE_ID })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}
			Long projectScheduleId = json.get(PROJECT_SCHEDULE_ID).asLong();
			return new Status(ConstantStatus.OK, "", projectService.getFirmScheduleByScheduleId(projectScheduleId));
		} catch (Exception e) {
			LOGGER.error("Error in getFirmScheduleByScheduleId() method :: ", e);
			throw new TOMRuntimeException("Error in getFirmScheduleByScheduleId()", e);
		}
	}

	@RequestMapping(value = {
			"/schedule/send", }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status scheduleSendByPro(HttpServletRequest request) {
		LOGGER.debug("Execute method : scheduleSendByPro()");
		try {
			ObjectMapper mapper = ObjectMapper.getObjectMapper();
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			ProjectScheduleByPro projectScheduleByPro = mapper.convertValue(json.get(PROJECT_SCHEDULE_BY_PRO),
					ProjectScheduleByPro.class);
			return new Status(ConstantStatus.OK, "", projectService.scheduleSendByPro(projectScheduleByPro));
		} catch (Exception e) {
			LOGGER.error("Error in scheduleSendByPro() method :: ", e);
			throw new TOMRuntimeException("Error in scheduleSendByPro()", e);
		}
	}

	@RequestMapping(value = {
			"/schedule/getScheduleOfPro", }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status getScheduleOfPro(HttpServletRequest request) {
		LOGGER.debug("Execute method : getScheduleOfPro()");
		try {
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (JsonUtil.isRequiredParamNull(json, new String[] { PROJECT_SCHEDULE_BY_PRO_ID })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}
			Long projectScheduleByProId = json.get(PROJECT_SCHEDULE_BY_PRO_ID).asLong();
			return new Status(ConstantStatus.OK, "",
					projectService.getProScheduleByProScheduleId(projectScheduleByProId));
		} catch (Exception e) {
			LOGGER.error("Error in getScheduleOfPro() method :: ", e);
			throw new TOMRuntimeException("Error in getScheduleOfPro()", e);
		}
	}

	@RequestMapping(value = {
			"/schedule/complete", }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status projectMeetDoneByAdmin(HttpServletRequest request) {
		LOGGER.debug("Execute method : projectMeetDoneByAdmin()");
		try {
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (JsonUtil.isRequiredParamNull(json, new String[] { PROJECT_SCHEDULE_BY_PRO_ID })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}
			Long projectScheduleByProId = json.get(PROJECT_SCHEDULE_BY_PRO_ID).asLong();
			return new Status(ConstantStatus.OK, "", projectService.updateMeetingStatus(projectScheduleByProId));
		} catch (Exception e) {
			LOGGER.error("Error in projectMeetDoneByAdmin() method :: ", e);
			throw new TOMRuntimeException("Error in projectMeetDoneByAdmin()", e);
		}
	}

	@RequestMapping(value = {
			"/schedule/decline", }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status declineMeetingByPro(HttpServletRequest request) {
		LOGGER.debug("Execute method : declineMeetingByPro()");
		try {
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (JsonUtil.isRequiredParamNull(json, new String[] { PROJECT_SCHEDULE_ID })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}
			Long projectScheduleId = json.get(PROJECT_SCHEDULE_ID).asLong();
			return new Status(ConstantStatus.OK, "", projectService.declineMeetingByPro(projectScheduleId));
		} catch (Exception e) {
			LOGGER.error("Error in declineMeetingByPro() method :: ", e);
			throw new TOMRuntimeException("Error in declineMeetingByPro()", e);
		}
	}

	@RequestMapping(value = {
			"/pro/declineAfterMeeting", }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status declineByProAfterMeeting(HttpServletRequest request) {
		LOGGER.debug("Execute method : declineByProAfterMeeting()");
		try {
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (JsonUtil.isRequiredParamNull(json, new String[] { PROJECT_ID, PRO_PROFILE_ID, PROJECT_SCHEDULE_ID })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}
			Long projectId = json.get(PROJECT_ID).asLong();
			Long proProfileId = json.get(PRO_PROFILE_ID).asLong();
			Long projectScheduleId = json.get(PROJECT_SCHEDULE_ID).asLong();
			return new Status(ConstantStatus.OK, "",
					projectService.declineByProAfterMeeting(projectId, proProfileId, projectScheduleId));
		} catch (Exception e) {
			LOGGER.error("Error in declineByProAfterMeeting() method :: ", e);
			throw new TOMRuntimeException("Error in declineByProAfterMeeting()", e);
		}
	}

	@RequestMapping(value = { "/finalize", }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status projectFinalizeByPro(HttpServletRequest request) {
		LOGGER.debug("Execute method : projectFinalizeByPro()");
		try {
			ObjectMapper mapper = ObjectMapper.getObjectMapper();
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			ProjectFinalize projectFinalize = mapper.convertValue(json.get(PROJECT_FINALIZE), ProjectFinalize.class);
			return new Status(ConstantStatus.OK, "", projectService.saveProjectFinalize(projectFinalize, json));
		} catch (Exception e) {
			LOGGER.error("Error in projectFinalizeByPro() method :: ", e);
			throw new TOMRuntimeException("Error in projectFinalizeByPro()", e);
		}
	}

	@RequestMapping(value = {
			"/firm/decline", }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status declineByFirmBeforeMeeting(HttpServletRequest request) {
		LOGGER.debug("Execute method : declineByFirmBeforeMeeting()");
		try {
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (JsonUtil.isRequiredParamNull(json, new String[] { PROJECT_ID, PRO_PROFILE_ID })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}
			Long projectId = json.get(PROJECT_ID).asLong();
			Long proProfileId = json.get(PRO_PROFILE_ID).asLong();
			return new Status(ConstantStatus.OK, "",
					projectService.declineByFirmBeforeMeeting(projectId, proProfileId));
		} catch (Exception e) {
			LOGGER.error("Error in declineByFirmBeforeMeeting() method :: ", e);
			throw new TOMRuntimeException("Error in declineByFirmBeforeMeeting()", e);
		}
	}

	@RequestMapping(value = {
			"/firm/declineAfterMeeting", }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status declineByFirmAfterMeeting(HttpServletRequest request) {
		LOGGER.debug("Execute method : declineByFirmAfterMeeting()");
		try {
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (JsonUtil.isRequiredParamNull(json, new String[] { PROJECT_ID, PRO_PROFILE_ID })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}
			Long projectId = json.get(PROJECT_ID).asLong();
			Long proProfileId = json.get(PRO_PROFILE_ID).asLong();
			return new Status(ConstantStatus.OK, "", projectService.declineByFirmAfterMeeting(projectId, proProfileId));
		} catch (Exception e) {
			LOGGER.error("Error in declineByFirmAfterMeeting() method :: ", e);
			throw new TOMRuntimeException("Error in declineByFirmAfterMeeting()", e);
		}
	}

	@RequestMapping(value = {
			"/firm/finalize", }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status projectFinalizeByFirm(HttpServletRequest request) {
		LOGGER.debug("Execute method : projectFinalizeByFirm()");
		try {
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (JsonUtil.isRequiredParamNull(json, new String[] { PROJECT_FINALIZE_ID })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}
			Long projectFinalizeId = json.get(PROJECT_FINALIZE_ID).asLong();
			return new Status(ConstantStatus.OK, "", projectService.projectFinalizeByFirm(projectFinalizeId));
		} catch (Exception e) {
			LOGGER.error("Error in projectFinalizeByFirm() method :: ", e);
			throw new TOMRuntimeException("Error in projectFinalizeByFirm()", e);
		}
	}

	@RequestMapping(value = {
			"/admin/accept", }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status projectFinalizeByAdmin(HttpServletRequest request) {
		LOGGER.debug("Execute method : projectFinalizeByAdmin()");
		try {
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (JsonUtil.isRequiredParamNull(json, new String[] { PROJECT_FINALIZE_ID, PROJECT_ID })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}
			Long projectFinalizeId = json.get(PROJECT_FINALIZE_ID).asLong();
			Long projectId = json.get(PROJECT_ID).asLong();

			return new Status(ConstantStatus.OK, "",
					projectService.projectFinalizeByAdmin(projectFinalizeId, projectId));
		} catch (Exception e) {
			LOGGER.error("Error in projectFinalizeByAdmin() method :: ", e);
			throw new TOMRuntimeException("Error in projectFinalizeByAdmin()", e);
		}
	}

	@RequestMapping(value = { "/dashboard", }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status getDashBoardProject(HttpServletRequest request) {
		LOGGER.debug("Execute method : getDashBoardProject()");
		try {
			Long userId = null;
			Long proProfileId = null;
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (JsonUtil.isRequiredParamNull(json, new String[] { ARG_FROM })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}
			if (json.has(USER_ID) && json.get(USER_ID) != null) {
				userId = json.get(USER_ID).asLong();
			}
			if (json.has(PRO_PROFILE_ID) && json.get(PRO_PROFILE_ID) != null) {
				proProfileId = json.get(PRO_PROFILE_ID).asLong();
			}
			String argFrom = json.get(ARG_FROM).asText();
			return new Status(ConstantStatus.OK, "", projectService.getDashBoardProject(argFrom, userId, proProfileId));
		} catch (Exception e) {
			LOGGER.error("Error in getDashBoardProject() method :: ", e);
			throw new TOMRuntimeException("Error in getDashBoardProject()", e);
		}
	}

	@RequestMapping(value = {
			"/last2project", }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status getLast2ProjectOfFirm(HttpServletRequest request) {
		LOGGER.debug("Execute method : getLast2ProjectOfFirm()");
		try {
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (JsonUtil.isRequiredParamNull(json, new String[] { USER_ID, PRO_PROFILE_ID })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}
			Long userId = json.get(USER_ID).asLong();
			Long proProfileId = json.get(PRO_PROFILE_ID).asLong();
			return new Status(ConstantStatus.OK, "", projectService.getLast2ProjectOfFirm(userId, proProfileId));
		} catch (Exception e) {
			LOGGER.error("Error in getLast2ProjectOfFirm() method :: ", e);
			throw new TOMRuntimeException("Error in getLast2ProjectOfFirm()", e);
		}
	}

	@RequestMapping(value = {
			"/amends/save", }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status saveAmends(HttpServletRequest request) {
		LOGGER.debug("Execute method : saveAmends()");
		try {
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			Long projectFinalizeId = json.get(PROJECT_FINALIZE_ID).asLong();
			Long userId = json.get(USER_ID).asLong();
			String amends = json.get(AMENDS).asText();
			return new Status(ConstantStatus.OK, "", projectService.saveAmends(projectFinalizeId, userId, amends));
		} catch (Exception e) {
			LOGGER.error("Error in saveAmends() method :: ", e);
			throw new TOMRuntimeException("Error in saveAmends()", e);
		}
	}

	@RequestMapping(value = {
			"/additionaltime", }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status saveProjectAddtionalTime(HttpServletRequest request) {
		LOGGER.debug("Execute method : saveProjectAddtionalTime()");
		try {
			ObjectMapper mapper = ObjectMapper.getObjectMapper();
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
//			if (JsonUtil.isRequiredParamNull(json, new String[] { PROJECT_ADDTIONAL_TIME })) {
//				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
//			}
			ProjectAdditionalTime projectAdditionalTime = mapper.convertValue(json.get(PROJECT_ADDITIONAL_TIME),
					ProjectAdditionalTime.class);
			return new Status(ConstantStatus.OK, "", projectService.saveAddtionalTime(projectAdditionalTime));
		} catch (Exception e) {
			LOGGER.error("Error in saveProjectAddtionalTime() method :: ", e);
			throw new TOMRuntimeException("Error in saveProjectAddtionalTime()", e);
		}
	}

	@RequestMapping(value = {
			"/amends/additional/save", }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status saveAdditonalReqAmends(HttpServletRequest request) {
		LOGGER.debug("Execute method : saveAdditonalReqAmends()");
		try {
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			Long projectAdditionalTimeId = json.get(PROJECT_ADDITIONAL_TIME_ID).asLong();
			Long userId = json.get(USER_ID).asLong();
			String amends = json.get(AMENDS).asText();
			return new Status(ConstantStatus.OK, "",
					projectService.saveAdditonalReqAmends(projectAdditionalTimeId, userId, amends));
		} catch (Exception e) {
			LOGGER.error("Error in saveAdditonalReqAmends() method :: ", e);
			throw new TOMRuntimeException("Error in saveAdditonalReqAmends()", e);
		}
	}

	@RequestMapping(value = {
			"/firm/additional/finalize", }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status addtionalReqFinalizeByFirm(HttpServletRequest request) {
		LOGGER.debug("Execute method : addtionalReqFinalizeByFirm()");
		try {
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (JsonUtil.isRequiredParamNull(json, new String[] { PROJECT_ADDITIONAL_TIME_ID })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}
			Long projectAdditionalTimeId = json.get(PROJECT_ADDITIONAL_TIME_ID).asLong();
			return new Status(ConstantStatus.OK, "",
					projectService.addtionalReqFinalizeByFirm(projectAdditionalTimeId));
		} catch (Exception e) {
			LOGGER.error("Error in addtionalReqFinalizeByFirm() method :: ", e);
			throw new TOMRuntimeException("Error in addtionalReqFinalizeByFirm()", e);
		}
	}

	@RequestMapping(value = {
			"/admin/additional/accept", }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status additonalReqApproveByAdmin(HttpServletRequest request) {
		LOGGER.debug("Execute method : additonalReqApproveByAdmin()");
		try {
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (JsonUtil.isRequiredParamNull(json, new String[] { PROJECT_ADDITIONAL_TIME_ID })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}
			Long projectAdditionalTimeId = json.get(PROJECT_ADDITIONAL_TIME_ID).asLong();
			return new Status(ConstantStatus.OK, "",
					projectService.additonalReqApproveByAdmin(projectAdditionalTimeId));
		} catch (Exception e) {
			LOGGER.error("Error in additonalReqApproveByAdmin() method :: ", e);
			throw new TOMRuntimeException("Error in additonalReqApproveByAdmin()", e);
		}
	}

}
