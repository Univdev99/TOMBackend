package com.tom.user;

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
//import com.tom.common.beans.TreeNode;
import com.tom.common.constant.Constant;
import com.tom.common.encryption.SHA1Encryptor;
import com.tom.common.exception.TOMRuntimeException;
import com.tom.util.CommonUtil;
import com.tom.util.DateUtil;
import com.tom.util.JsonUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value = "user", tags = "USER")
@RequestMapping("/tom/user")
public class UserController implements Constant {
	static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = {
			"/changePassword" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status changePassword(HttpServletRequest request) {
		LOGGER.debug("Execute method : changePassword()");
		try {
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (JsonUtil.isRequiredParamNull(json, new String[] { USER_ID, USER_PASSWORD, "userNewPassword" })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}
			Long userId = json.get(USER_ID).asLong();
			User user = userService.getUserById(userId);
			String userJsonPassword = json.get(USER_PASSWORD).asText();
			String userPassword = user.getPassword();
			String userNewPassword = json.get("userNewPassword").asText();
			if (SHA1Encryptor.sHA1(userJsonPassword).equals(userPassword)) {
				user.setPassword(SHA1Encryptor.sHA1(userNewPassword));
				userService.saveOrUpdate(user);
				return new Status(ConstantStatus.OK, "password matched successfully", new String[] { "passwordMatch" });
			} else {
				return new Status(ConstantStatus.OK, "password not matched", new String[] { "passwordNotMatch" });
			}
		} catch (Exception e) {
			LOGGER.error("Error in changePassword() method :: ", e);
			throw new TOMRuntimeException("Error in changePassword()", e);
		}
	}

}
