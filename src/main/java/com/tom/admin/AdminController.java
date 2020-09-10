package com.tom.admin;

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
import com.tom.project.Project;
import com.tom.project.ProjectService;
import com.tom.user.User;
import com.tom.util.JsonUtil;

@RestController
@RequestMapping("/tom/admin")
public class AdminController implements Constant {
	public static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	AdminService adminService;

	@Autowired
	ProjectService projectService;

	@RequestMapping(value = { "/create" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status addNewAdmin(HttpServletRequest request) {
		LOGGER.debug("Execute method : addNewAdmin()");
		try {
			ObjectMapper mapper = ObjectMapper.getObjectMapper();
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			User user = mapper.convertValue(json.get("adminSignup"), User.class);
			if (user.getUserId() == null) {
				if (adminService.isAdminExist(user.getWorkEmail())) {
					return new Status(ConstantStatus.CONFLICT, "", user.getWorkEmail().concat(" is already exist"));
				}
			}
			return new Status(ConstantStatus.OK, "", adminService.createAdmin(user));
		} catch (Exception e) {
			LOGGER.error("Error in addNewAdmin() method :: ", e);
			throw new TOMRuntimeException("Error in addNewAdmin()", e);
		}
	}

	@RequestMapping(value = { "/get" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status getAdminList(HttpServletRequest request) {
		LOGGER.debug("Execute method : getAdminList()");
		try {
			return new Status(ConstantStatus.OK, "", adminService.getAdminList());
		} catch (Exception e) {
			LOGGER.error("Error in getAdminList() method :: ", e);
			throw new TOMRuntimeException("Error in getAdminList()", e);
		}
	}

	@RequestMapping(value = {
			"/firm/search" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status getAllFirms(HttpServletRequest request) {
		LOGGER.debug("Execute method : getAllFirms()");
		try {
			return new Status(ConstantStatus.OK, "", adminService.getAllFirms());
		} catch (Exception e) {
			LOGGER.error("Error in getAllFirms() method :: ", e);
			throw new TOMRuntimeException("Error in getAllFirms()", e);
		}
	}

	@RequestMapping(value = { "/firm/all" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status getAllFirmProfileList(HttpServletRequest request) {
		LOGGER.debug("Execute method : getAllFirmProfileList()");
		try {
			return new Status(ConstantStatus.OK, "", adminService.getAllFirmProfileList());
		} catch (Exception e) {
			LOGGER.error("Error in getAllFirmProfileList() method :: ", e);
			throw new TOMRuntimeException("Error in getAllFirmProfileList()", e);
		}
	}

	@RequestMapping(value = { "/pro/search" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status getAllPro(HttpServletRequest request) {
		LOGGER.debug("Execute method : getAllPro()");
		try {
			return new Status(ConstantStatus.OK, "", adminService.getAllPro());
		} catch (Exception e) {
			LOGGER.error("Error in getAllPro() method :: ", e);
			throw new TOMRuntimeException("Error in getAllPro()", e);
		}
	}

	@RequestMapping(value = { "/pro/all" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status getAllProProfileList(HttpServletRequest request) {
		LOGGER.debug("Execute method : getAllProProfileList()");
		try {
			return new Status(ConstantStatus.OK, "", adminService.getAllProProfileList());
		} catch (Exception e) {
			LOGGER.error("Error in getAllProProfileList() method :: ", e);
			throw new TOMRuntimeException("Error in getAllProProfileList()", e);
		}
	}

	@RequestMapping(value = {
			"/user/active", }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status userActiveByAdmin(HttpServletRequest request) {
		LOGGER.debug("Execute method : userActiveByAdmin()");
		try {
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (JsonUtil.isRequiredParamNull(json, new String[] { USER_ID, USER_STATUS })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}
			Long userId = json.get(USER_ID).asLong();
			String userStatus = json.get(USER_STATUS).asText();
			return new Status(ConstantStatus.OK, "", adminService.userActiveByAdmin(userId, userStatus));
		} catch (Exception e) {
			LOGGER.error("Error in userActiveByAdmin() method :: ", e);
			throw new TOMRuntimeException("Error in userActiveByAdmin()", e);
		}
	}

	@RequestMapping(value = {
			"/project/complete" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status projectCompletedByAdmin(HttpServletRequest request) {
		LOGGER.debug("Execute method : projectCompletedByAdmin()");
		try {
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (JsonUtil.isRequiredParamNull(json, new String[] { PROJECT_COMPLETE_ID })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}
			Long projectCompleteId = json.get(PROJECT_COMPLETE_ID).asLong();
			return new Status(ConstantStatus.OK, "", adminService.projectCompletedByAdmin(projectCompleteId));
		} catch (Exception e) {
			LOGGER.error("Error in projectCompletedByAdmin() method :: ", e);
			throw new TOMRuntimeException("Error in projectCompletedByAdmin()", e);
		}
	}

	@RequestMapping(value = {
			"/payment/receive" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status paymentConfirmedByAdmin(HttpServletRequest request) {
		LOGGER.debug("Execute method : paymentConfirmedByAdmin()");
		try {
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (JsonUtil.isRequiredParamNull(json, new String[] { PROJECT_COMPLETE_ID, PROJECT_ID })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}
			Long projectCompleteId = json.get(PROJECT_COMPLETE_ID).asLong();
			Long projectId = json.get(PROJECT_ID).asLong();
			adminService.paymentConfirmedByAdmin(projectCompleteId);
			return new Status(ConstantStatus.OK, "", projectService.changeProjectStatus(projectId, COMPLETED));
		} catch (Exception e) {
			LOGGER.error("Error in paymentConfirmedByAdmin() method :: ", e);
			throw new TOMRuntimeException("Error in paymentConfirmedByAdmin()", e);
		}
	}

	@RequestMapping(value = { "/jobStatus" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status getAllProJobStatus(HttpServletRequest request) {
		LOGGER.debug("Execute method : getAllProJobStatus()");
		try {
			return new Status(ConstantStatus.OK, "", adminService.getAllProJobStatus());
		} catch (Exception e) {
			LOGGER.error("Error in getAllProJobStatus() method :: ", e);
			throw new TOMRuntimeException("Error in getAllProJobStatus()", e);
		}
	}

	@RequestMapping(value = { "/profit" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status saveAdminProfit(HttpServletRequest request) {
		LOGGER.debug("Execute method : saveAdminProfit()");
		try {
			ObjectMapper mapper = ObjectMapper.getObjectMapper();
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
//			if (JsonUtil.isRequiredParamNull(json, new String[] { ADMIN_PROFIT })) {
//				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
//			}
			AdminProfit adminProfit = mapper.convertValue(json.get(ADMIN_PROFIT), AdminProfit.class);
			return new Status( ConstantStatus.OK, "", adminService.saveAdminProfit(adminProfit));
		} catch (Exception e) {
			LOGGER.error("Error in saveAdminProfit() method :: ", e);
			throw new TOMRuntimeException("Error in saveAdminProfit()", e);
		}
	}
	
	@RequestMapping(value = { "/profit/get" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status getAdminProfit(HttpServletRequest request) {
		LOGGER.debug("Execute method : getAdminProfit()");
		try {
			return new Status( ConstantStatus.OK, "", adminService.getAdminProfit());
		} catch (Exception e) {
			LOGGER.error("Error in getAdminProfit() method :: ", e);
			throw new TOMRuntimeException("Error in getAdminProfit()", e);
		}
	}
}
