package com.tom.firm;

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
import com.tom.util.JsonUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "Firm Controller", tags = "Firm")
@RestController
@RequestMapping("/tom/firm")
public class FirmController implements Constant {
	public static final Logger LOGGER = LoggerFactory.getLogger(FirmController.class);

	@Autowired
	FirmService firmService;
	

	@ApiOperation(value = "Create Firm Profile")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "X-Auth-Token", value = "Authorization token", required = true, dataType = "string", paramType = "header"),
			@ApiImplicitParam(name = "requestBody", value = "bbbb", required = true, paramType = "body") })
	@RequestMapping(value = { "/profile/update",
			"/profile/save" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status createFirmProfile(HttpServletRequest request) {
		LOGGER.debug("Execute method : createFirmProfile()");
		try {
			ObjectMapper mapper = ObjectMapper.getObjectMapper();
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			FirmProfile firmProfile = mapper.convertValue(json.get(FIRM_PROFILE), FirmProfile.class);
			firmService.createFirmProfile(firmProfile);

			return new Status(ConstantStatus.OK, "", "123");
		} catch (Exception e) {
			LOGGER.error("Error in createFirmProfile() method :: ", e);
			throw new TOMRuntimeException("Error in createFirmProfile()", e);
		}
	}

	@RequestMapping(value = {
			"/profile/get" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status getFirmProfile(HttpServletRequest request) {
		LOGGER.debug("Execute method : getFirmProfile()");
		try {
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (JsonUtil.isRequiredParamNull(json, new String[] { FIRM_PROFILE_ID })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}
			Long firmProfileId = json.get(FIRM_PROFILE_ID).asLong();
			return new Status(ConstantStatus.OK, "", firmService.getFirmProfile(firmProfileId));
		} catch (Exception e) {
			LOGGER.error("Error in getFirmProfile() method :: ", e);
			throw new TOMRuntimeException("Error in getFirmProfile()", e);
		}
	}

	@RequestMapping(value = {
			"/project/accpeted" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status getAccpetedFirmProject(HttpServletRequest request) {
		LOGGER.debug("Execute method : getAccpetedFirmProject()");
		try {
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (JsonUtil.isRequiredParamNull(json, new String[] { USER_ID })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}
			Long userId = json.get(USER_ID).asLong();
			return new Status(ConstantStatus.OK, "", firmService.getAccpetedFirmProject(userId));
		} catch (Exception e) {
			LOGGER.error("Error in getAccpetedFirmProject() method :: ", e);
			throw new TOMRuntimeException("Error in getAccpetedFirmProject()", e);
		}
	}

	@RequestMapping(value = {
			"/project/history" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status getFirmProjectHistory(HttpServletRequest request) {
		LOGGER.debug("Execute method : getFirmProjectHistory()");
		try {
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (JsonUtil.isRequiredParamNull(json, new String[] { USER_ID })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}
			Long userId = json.get(USER_ID).asLong();
			return new Status(ConstantStatus.OK, "", firmService.getFirmProjectHistory(userId));
		} catch (Exception e) {
			LOGGER.error("Error in getFirmProjectHistory() method :: ", e);
			throw new TOMRuntimeException("Error in getFirmProjectHistory()", e);
		}
	}

	@RequestMapping(value = {
			"/project/complete" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status projectCompleted(HttpServletRequest request) {
		LOGGER.debug("Execute method : projectCompleted()");
		try {
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (JsonUtil.isRequiredParamNull(json, new String[] { PROJECT_COMPLETE_ID })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}
			Long projectCompletedId = json.get(PROJECT_COMPLETE_ID).asLong();
			return new Status(ConstantStatus.OK, "", firmService.projectCompleted(projectCompletedId));
		} catch (Exception e) {
			LOGGER.error("Error in projectCompleted() method :: ", e);
			throw new TOMRuntimeException("Error in projectCompleted()", e);
		}
	}
	

}
