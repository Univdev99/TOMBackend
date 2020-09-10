package com.tom.login;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tom.common.beans.ConstantStatus;
import com.tom.common.beans.Status;
import com.tom.common.constant.Constant;
import com.tom.common.exception.TOMRuntimeException;
import com.tom.firm.FirmService;
import com.tom.professional.ProfessionalService;
import com.tom.user.User;
import com.tom.user.UserService;
import com.tom.util.CommonUtil;

import io.swagger.annotations.Api;

@RestController
@Api(value = "login", tags = "Login Controller")
@RequestMapping("/tom/login")
public class LoginController implements Constant {
	static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	private static final String HEADER_USERNAME = "X-Username";

	@Autowired
	UserService userService;

	@Autowired
	private LoginService loginService;

	@Autowired
	private FirmService firmService;

	@Autowired
	private ProfessionalService proService;

	@RequestMapping(value = { "" }, method = RequestMethod.POST)
	public Status login(HttpServletResponse httpResponse, HttpServletRequest httpRequest) {
		LOGGER.debug("Execute method : login()");

		Map<String, Object> map = new HashMap<>();
		try {
			User user = CommonUtil.getContextUser();
			user = userService.getUserById(user.getUserId());
			map.put(USER, user);
			if (user.getRoleId().toString().equals(FIRM_USER_TYPE)) {
				map.put(FIRM_PROFILE, firmService.getFirmByUserId(user.getUserId()));
			} else if (user.getRoleId().toString().equals(PRO_USER_TYPE)) {
				map.put(PRO_PERSONAL_PROFILE, proService.getProPersonalByUserId(user.getUserId()));
			}
			return new Status(ConstantStatus.OK, "", map);
		} catch (Exception e) {
			LOGGER.error("Error in login()", e);
			throw new TOMRuntimeException("Error in login()", e);
		}
	}

	@RequestMapping(value = { "/badCredentials" }, method = RequestMethod.POST)
	public Object badCredentials(HttpServletResponse httpResponse, HttpServletRequest httpRequest) {
		LOGGER.debug("Execute method : badCredentials()");
		try {
			if (CommonUtil.getContextUser() == null) {
				Map<String, Object> map = new HashMap<>();
				map.put("status", "invalid password");
//				map.put("invalidJson", loginService.invalidLoginAttempt(httpRequest.getHeader(HEADER_USERNAME), false));
				return new Status(ConstantStatus.CONFLICT, "", map);
			}
			return new Status(ConstantStatus.FORBIDDEN, "You are not authorized to perform this action.");
		} catch (Exception e) {
			LOGGER.error("Error in badCredentials()", e);
			throw new TOMRuntimeException("Error in badCredentials()", e);
		}

	}

	@RequestMapping(value = { "/disabledUser" }, method = RequestMethod.POST)
	public Object disabledUser(HttpServletResponse httpResponse, HttpServletRequest httpRequest) {
		LOGGER.debug("Execute method : badCredentials()");
		try {
			return new Status(ConstantStatus.FORBIDDEN,
					"Sorry, your account is disabled.<br><br> Kindly contact your clinic administrator or OmniMD Support for further assistance.");

		} catch (Exception e) {
			LOGGER.error("Error in disabledUser()", e);
			throw new TOMRuntimeException("Error in disabledUser()", e);
		}

	}

	@RequestMapping(value = { "/lockedUser" }, method = RequestMethod.POST)
	public Object lockedUser(HttpServletResponse httpResponse, HttpServletRequest httpRequest) {
		LOGGER.debug("Execute method : lockedUser()");
		try {
			return new Status(ConstantStatus.FORBIDDEN,
					"Sorry, your account is Locked.<br><br> This could happen due to wrong login attempts more than permissible limit. If you wish to unlock your account, please click <a href='javascript:;' class='activateUser'>Unlock My User Account.</a>");

		} catch (Exception e) {
			LOGGER.error("Error in lockedUser()", e);
			throw new TOMRuntimeException("Error in lockedUser()", e);
		}

	}

	@RequestMapping(value = { "/invalidToken" }, method = RequestMethod.POST)
	public Object invalidToken(HttpServletResponse httpResponse, HttpServletRequest httpRequest) {
		LOGGER.debug("Execute method : invalidToken()");
		try {
			return new Status(ConstantStatus.OK_NO_MESSAGE, "");

		} catch (Exception e) {
			LOGGER.error("Error in invalidToken()", e);
			throw new TOMRuntimeException("Error in invalidToken()", e);
		}

	}

	@RequestMapping(value = { "/invalidIP" }, method = RequestMethod.POST)
	public Object invalidIP(HttpServletResponse httpResponse, HttpServletRequest httpRequest) {
		LOGGER.debug("Execute method : invalidIP()");
		try {

			Map<String, Object> map = new HashMap<>();
			map.put("status", "invalidIP");
			map.put("invalidJson", loginService.invalidLoginAttempt(httpRequest.getHeader(HEADER_USERNAME), true));
			return map;

		} catch (Exception e) {
			LOGGER.error("Error in invalidIP()", e);
			throw new TOMRuntimeException("Error in invalidIP()", e);
		}

	}

	@RequestMapping(value = { "/inActiveEntityClinic" }, method = RequestMethod.POST)
	public Object inActiveEntityClinic(HttpServletResponse httpResponse, HttpServletRequest httpRequest) {
		LOGGER.debug("Execute method : inActiveEntityClinic()");
		try {
			return new Status(ConstantStatus.FORBIDDEN,
					"You are not authorized to login as either Entity or Business(s) or Clinic(s) assigned to you are inactive.");
		} catch (Exception e) {
			LOGGER.error("Error in inActiveEntityClinic()", e);
			throw new TOMRuntimeException("Error in inActiveEntityClinic()", e);
		}
	}

	@RequestMapping(value = { "/wrongRandomKey" }, method = RequestMethod.POST)
	public Object wrongRandomKey(HttpServletResponse httpResponse, HttpServletRequest httpRequest) {
		LOGGER.debug("Execute method : wrongRandomKey()");
		try {

			return new Status(ConstantStatus.INTERNAL_SERVER_ERROR, null);
		} catch (Exception e) {
			LOGGER.error("Error in wrongRandomKey()", e);
			throw new TOMRuntimeException("Error in wrongRandomKey()", e);
		}
	}

	@RequestMapping(value = { "/logout" }, method = RequestMethod.POST)
	public void logout(HttpServletResponse httpResponse, HttpServletRequest httpRequest) {
		LOGGER.debug("Execute method : logout()");

	}

}
