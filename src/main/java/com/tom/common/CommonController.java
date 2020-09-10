package com.tom.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.tom.common.beans.ConstantStatus;
import com.tom.common.beans.ObjectMapper;
import com.tom.common.beans.Status;
import com.tom.common.constant.Constant;
import com.tom.common.exception.TOMRuntimeException;
import com.tom.common.security.AuthenticationService;
import com.tom.common.security.TokenInfo;
import com.tom.firm.FirmProfile;
import com.tom.firm.FirmService;
import com.tom.login.LoginService;
import com.tom.professional.ProfessionalProfile;
import com.tom.professional.ProfessionalService;
import com.tom.user.User;
import com.tom.user.UserService;
import com.tom.user.UserToken;
import com.tom.user.UserTokenService;
import com.tom.user.UserValidator;
import com.tom.util.CommonUtil;
import com.tom.util.DateUtil;
import com.tom.util.JsonUtil;

import io.swagger.annotations.Api;

@RestController
@Api(value = "user", tags = "USER")
@RequestMapping("/common")
public class CommonController implements Constant {
	static final Logger LOGGER = LoggerFactory.getLogger(CommonController.class);

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private UserService userService;

	@Autowired
	private LoginService loginService;

	@Autowired
	AuthenticationService authenticationService;

	@Autowired
	private FirmService firmService;

	@Autowired
	private ProfessionalService professionalService;

	@Autowired
	private UserTokenService userTokenService;

	@RequestMapping(value = "/user/save", method = RequestMethod.POST, produces = "application/json")
	public Status saveUser(HttpServletRequest request, HttpServletResponse res) {
		LOGGER.debug("Execute method : saveUser()");

		try {
			ObjectMapper mapper = ObjectMapper.getObjectMapper();
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			User user = mapper.convertValue(json.get(USER), User.class);
			Map<String, Object> map = new HashMap<String, Object>();

			BindException errors = new BindException(user, "user");
			userValidator.validate(user, errors);
			if (errors.hasErrors()) {
				return new Status(ConstantStatus.CONFLICT, user.getWorkEmail().concat(" is already exist."), null);
			}
			user = userService.saveUser(user);
			map.put(USER, user);
			createTokenAfterUserCreation(user.getWorkEmail(), user.getPassword(), user.getRoleId().toString(), res,
					request);
			if (null != user.getRoleId() && user.getRoleId().toString().equals(FIRM_USER_TYPE)) {
				FirmProfile firmProfile = new FirmProfile();
				firmProfile.setFirstName(user.getFirstName());
				firmProfile.setLastName(user.getLastName());
				firmProfile.setBusinessName(user.getBusinessName());
				firmProfile.setWorkEmail(user.getWorkEmail());
				firmProfile.setUserId(user.getUserId());
				firmService.createFirmProfile(firmProfile);
				map.put(FIRM_PROFILE_ID, firmProfile.getFirmProfileId());
			} else if (null != user.getRoleId() && user.getRoleId().toString().equals(PRO_USER_TYPE)) {
				ProfessionalProfile professionalProfile = new ProfessionalProfile();
				professionalProfile.setFirstName(user.getFirstName());
				professionalProfile.setLastName(user.getLastName());
				professionalProfile.setUserId(user.getUserId());
				professionalService.createProProfile(professionalProfile, null);
				map.put(PRO_PROFILE_ID, professionalProfile.getProfessionalProfileId());
			}
			return new Status(ConstantStatus.OK, USER_SAVED_SUCCESS, map);
		} catch (TOMRuntimeException o) {
			LOGGER.debug("Exception in saveUser() -> " + o.getMessage());
			return new Status(ConstantStatus.BAD_REQUEST, o.getMessage());
		} catch (Exception e) {
			LOGGER.error("Error in saveUser()", e);
			throw new TOMRuntimeException("Error in saveUser()", e);
		}
	}

	private void createTokenAfterUserCreation(String username, String password, String role,
			HttpServletResponse httpResponse, HttpServletRequest httpRequest) throws Exception {
		TokenInfo tokenInfo = authenticationService.authenticate(username, password, role);
		if (tokenInfo != null) {
			httpResponse.setHeader(XAUTHTOKEN, tokenInfo.getToken());
			httpRequest.setAttribute(REQUEST_ATTR_DO_NOT_CONTINUE, null);
			UserToken userToken = new UserToken();
			userToken.setTokenId(tokenInfo.getToken());
			userToken.setUser(CommonUtil.getContextUser());
			userToken.setNode(httpRequest.getServerName());
			userToken.setCreatedDate(DateUtil.getCurrentTimestamp());
			try {
				userTokenService.save(userToken);
			} catch (Exception e) {
				throw new TOMRuntimeException("Exception in saving token", e);
			}
		} else {
			httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}

	@RequestMapping(value = { "/firm/profile/update",
			"/firm/profile/save" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status createFirmProfile(HttpServletRequest request) {
		LOGGER.debug("Execute method : createFirmProfile()");
		try {
			ObjectMapper mapper = ObjectMapper.getObjectMapper();
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			FirmProfile firmProfile = mapper.convertValue(json.get(FIRM_PROFILE), FirmProfile.class);
			firmService.createFirmProfile(firmProfile);

			return new Status(ConstantStatus.OK, "");
		} catch (Exception e) {
			LOGGER.error("Error in createFirmProfile() method :: ", e);
			throw new TOMRuntimeException("Error in createFirmProfile()", e);
		}
	}

	@RequestMapping(value = {
			"/forgot/sendEmail" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status checkAndSendEmail(HttpServletRequest request) {
		LOGGER.debug("Execute method : checkAndSendEmail()");
		try {
			String message = "	";
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (JsonUtil.isRequiredParamNull(json, new String[] { EMAIL })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}

			String email = json.get(EMAIL).asText();
			Map<String, User> userMap = loginService.getUserDataBasedOnEmail(email);
			if (null != userMap) {
				userService.generateAndSendEmailToUser(userMap.get(USER));
				message = "Reset password link sent to  given Mail";
				return new Status(ConstantStatus.OK, "", message);
//				return new Status(ConstantStatus.CONFLICT, "Account is not exist with given email");
			}
			return new Status(ConstantStatus.OK, "", null);
		} catch (Exception e) {
			LOGGER.error("Error in checkAndSendEmail() method :: ", e);
			throw new TOMRuntimeException("Error in checkAndSendEmail()", e);
		}
	}

	@RequestMapping(value = {
			"/user/getByToken" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status userGetByToken(HttpServletRequest request) {
		LOGGER.debug("Execute method : userGetByToken()");
		try {
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (JsonUtil.isRequiredParamNull(json, new String[] { TOKEN })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}

			String token = json.get(TOKEN).asText();
			Map<String, Object> userMap = loginService.getUserByToken(token);
			if (userMap != null) {
				return new Status(ConstantStatus.OK, "", userMap);
			}
			return new Status(ConstantStatus.OK, "User is not found", "User is not found");
		} catch (Exception e) {
			LOGGER.error("Error in userGetByToken() method :: ", e);
			throw new TOMRuntimeException("Error in userGetByToken()", e);
		}
	}

	@RequestMapping(value = {
			"/password/change" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status changePassword(HttpServletRequest request) {
		LOGGER.debug("Execute method : changePassword()");
		try {
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (JsonUtil.isRequiredParamNull(json, new String[] { USER_ID, CHANGED_PASSWORD })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}

			Long userId = json.get(USER_ID).asLong();
			String newPassWord = json.get(CHANGED_PASSWORD).asText();
			return new Status(ConstantStatus.OK, "", loginService.changePassWord(userId, newPassWord));
		} catch (Exception e) {
			LOGGER.error("Error in changePassword() method :: ", e);
			throw new TOMRuntimeException("Error in changePassword()", e);
		}
	}

	private Object changePassWord(Long userId, String newPassWord) {
		// TODO Auto-generated method stub
		return null;
	}

}
