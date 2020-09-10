
package com.tom.common.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.tom.common.Notification;
import com.tom.common.beans.Calender;
import com.tom.common.beans.ConstantStatus;
import com.tom.common.beans.Help;
import com.tom.common.beans.ObjectMapper;
import com.tom.common.beans.Review;
import com.tom.common.beans.Status;
import com.tom.common.constant.Constant;
import com.tom.common.exception.TOMRuntimeException;
import com.tom.user.User;
import com.tom.user.UserService;
import com.tom.util.CommonFuncs;
import com.tom.util.CommonUtil;
import com.tom.util.JsonUtil;
import com.tom.util.StringUtil;

@RestController
@RequestMapping("/tom/component")
public class ComponentController implements Constant {
	static final Logger LOGGER = LoggerFactory.getLogger(ComponentController.class);

	@Autowired
	ComponentService componentService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/select/soft_search", method = RequestMethod.POST)
	public Object softSearchComponent(HttpServletRequest request) {
		List<Map<String, Object>> softSearchList = new ArrayList<>();
		String componentName = null;
		String subType = null;
		String searchParam = null;
		String staticWhereClause = null;
		String entityId = null;
		try {
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			User user = userService.getUserById(CommonUtil.getContextUser().getUserId());

			if (JsonUtil.isRequiredParamNull(json, new String[] { COMPONENT_NAME, SEARCH_PARAM })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}

			componentName = json.get(COMPONENT_NAME).asText();
			if (null != json.get(SUB_TYPE)) {
				subType = json.get(SUB_TYPE).asText();
			}
			if (null != json.get(SEARCH_PARAM)) {
				searchParam = json.get(SEARCH_PARAM).asText();
			}
			if (null != json.get(STATIC_WHERE_CLAUSE)) {
				staticWhereClause = json.get(STATIC_WHERE_CLAUSE).asText();
			}
			softSearchList = componentService.getSearchData(componentName, subType,
					CommonFuncs.validateInput(searchParam), staticWhereClause, entityId, user.getUserId().toString());
		} catch (Exception e) {
			LOGGER.error("Error in softSearchCommonComponent()", e);
			throw new TOMRuntimeException("Error in softSearchCommonComponent()", e);
		}
		return new Status(ConstantStatus.OK_NO_MESSAGE, "", softSearchList);
	}

	@RequestMapping(value = "/select/static", method = RequestMethod.POST)
	public Status staticComponent(HttpServletRequest request) {
		LOGGER.debug("Execute method : staticComponent() ");
		String staticListData = null;
		try {
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (JsonUtil.isRequiredParamNull(json, new String[] { COMPONENT_NAME })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}
			String componentName = json.get(COMPONENT_NAME).asText();
			String subType = null;
			String entityId = null;
			if (null != json.get(SUB_TYPE)) {
				subType = json.get(SUB_TYPE).asText();
			}
			if (null != json.get(STATIC_WHERE_CLAUSE)
					&& !StringUtil.isNullorEmpty(json.get(STATIC_WHERE_CLAUSE).asText())) {
				User user = userService.getUserById(CommonUtil.getContextUser().getUserId());
				return new Status(ConstantStatus.OK_NO_MESSAGE, "", componentService.getSearchData(componentName,
						subType, null, json.get(STATIC_WHERE_CLAUSE).asText(), entityId, user.getUserId().toString()));
			} else {
//				staticListData = componentService.getStaticListData(componentName);
				staticListData = StringUtil.isNullorEmpty(staticListData) ? "[]" : staticListData;
				return new Status(ConstantStatus.OK_NO_MESSAGE, "", staticListData);
			}
		} catch (Exception e) {
			LOGGER.error("Error in create()", e);
			throw new TOMRuntimeException("Error in create()", e);
		}
	}

	@RequestMapping(value = { "/help" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status saveHelp(HttpServletRequest request) {
		LOGGER.debug("Execute method : saveHelp()");
		try {
			ObjectMapper mapper = ObjectMapper.getObjectMapper();
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			Help help = mapper.convertValue(json.get(HELP), Help.class);

			return new Status(ConstantStatus.OK, "", componentService.saveAndSendHelp(help));
		} catch (Exception e) {
			LOGGER.error("Error in saveHelp() method :: ", e);
			throw new TOMRuntimeException("Error in saveHelp()", e);
		}
	}

	@RequestMapping(value = { "/review" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status saveReview(HttpServletRequest request) {
		LOGGER.debug("Execute method : saveReview()");
		try {
			ObjectMapper mapper = ObjectMapper.getObjectMapper();
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			Review review = mapper.convertValue(json.get(REVIEW), Review.class);
			return new Status(ConstantStatus.OK, "", componentService.saveReview(review));
		} catch (Exception e) {
			LOGGER.error("Error in saveReview() method :: ", e);
			throw new TOMRuntimeException("Error in saveReview()", e);
		}
	}

	@RequestMapping(value = {
			"/review/average" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status getLatestAverageReviewOfFirm(HttpServletRequest request) {
		LOGGER.debug("Execute method : saveReview()");
		try {
			ObjectMapper mapper = ObjectMapper.getObjectMapper();
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			Long proProfileId = null;
			Long firmProfileId = null;
			if (!JsonUtil.isRequiredParamNull(json, new String[] { PRO_PROFILE_ID })) {
				proProfileId = json.get(PRO_PROFILE_ID).asLong();
			}
			if (!JsonUtil.isRequiredParamNull(json, new String[] { FIRM_PROFILE_ID })) {
				firmProfileId = json.get(FIRM_PROFILE_ID).asLong();
			}
			return new Status(ConstantStatus.OK, "",
					componentService.getLatestAverageReview(proProfileId, firmProfileId));
		} catch (Exception e) {
			LOGGER.error("Error in saveReview() method :: ", e);
			throw new TOMRuntimeException("Error in saveReview()", e);
		}
	}

	@RequestMapping(value = "/notification/save", method = RequestMethod.POST)
	public Status notificationSave(HttpServletRequest request) {
		LOGGER.debug("Execute method : notificationSave() ");
		try {
			ObjectMapper mapper = ObjectMapper.getObjectMapper();
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (!JsonUtil.isRequiredParamNull(json, new String[] { NOTIFICATION_LIST })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}

			List<Notification> notificationList = mapper.readValue(json.get(NOTIFICATION_LIST).toString(),
					new TypeReference<List<Notification>>() {
					});
			return new Status(ConstantStatus.OK, "", componentService.notificationSave(notificationList));

		} catch (Exception e) {
			LOGGER.error("Error in notificationSave()", e);
			throw new TOMRuntimeException("Error in notificationSave()", e);
		}
	}

	@RequestMapping(value = "/notification/get", method = RequestMethod.POST)
	public Object notificationGet(HttpServletRequest request) {
		LOGGER.debug("Execute method : notificationGet() ");
		try {
			Long userId = null;
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (JsonUtil.isRequiredParamNull(json, new String[] { ARG_FROM })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}

			if (json.get(USER_ID) != null) {
				userId = json.get(USER_ID).asLong();
			}
			String argFrom = json.get(ARG_FROM).asText();
			return new Status(ConstantStatus.OK, "", componentService.getNotification(userId, argFrom));

		} catch (Exception e) {
			LOGGER.error("Error in notificationGet()", e);
			throw new TOMRuntimeException("Error in notificationGet()", e);
		}
	}

	@RequestMapping(value = "/notification/data", method = RequestMethod.POST)
	public Object getNotificationData(HttpServletRequest request) {
		LOGGER.debug("Execute method : getNotificationData() ");
		try {
			Long toUserId = null;
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (JsonUtil.isRequiredParamNull(json, new String[] { "notificationFor" })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}

			if (!json.get("toUserId").isNull()) {
				toUserId = json.get("toUserId").asLong();
			}
			String notificationFor = json.get("notificationFor").asText();
			return new Status(ConstantStatus.OK, "", componentService.getNotificationData(toUserId, notificationFor));

		} catch (Exception e) {
			LOGGER.error("Error in getNotificationData()", e);
			throw new TOMRuntimeException("Error in getNotificationData()", e);
		}
	}

	@RequestMapping(value = {
			"notification/markAsRead", }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Status saveMarkAsRead(HttpServletRequest request) {
		LOGGER.debug("Execute method : saveMarkAsRead()");
		try {
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (JsonUtil.isRequiredParamNull(json, new String[] { NOTIFICATION_ID })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}
			Long notificationId = json.get(NOTIFICATION_ID).asLong();
			return new Status(ConstantStatus.OK, "", componentService.saveMarkAsRead(notificationId));
		} catch (Exception e) {
			LOGGER.error("Error in saveMarkAsRead() method :: ", e);
			throw new TOMRuntimeException("Error in saveMarkAsRead()", e);
		}
	}

	@RequestMapping(value = "/calender/save", method = RequestMethod.POST)
	public Status calenderSave(HttpServletRequest request) {
		LOGGER.debug("Execute method : calenderSave() ");
		try {
			ObjectMapper mapper = ObjectMapper.getObjectMapper();
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (!JsonUtil.isRequiredParamNull(json, new String[] { CALENDER })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}

			Calender calender = mapper.readValue(json.get(CALENDER).toString(), Calender.class);
			return new Status(ConstantStatus.OK, "", componentService.calenderSave(calender));

		} catch (Exception e) {
			LOGGER.error("Error in calenderSave()", e);
			throw new TOMRuntimeException("Error in calenderSave()", e);
		}
	}
	
	@RequestMapping(value = "/calender/get", method = RequestMethod.POST)
	public Object calenderGet(HttpServletRequest request) {
		LOGGER.debug("Execute method : calenderGet() ");
		try {
			Long userId = null;
			ObjectNode json = JsonUtil.objectNodeFromRequest(request);
			if (JsonUtil.isRequiredParamNull(json, new String[] { ARG_FROM })) {
				return new Status(ConstantStatus.BAD_REQUEST, REQUIRED_PARAMS_MSG);
			}

			if (!json.get(USER_ID).isNull()) {
				userId = json.get(USER_ID).asLong();
			}
			String argFrom = json.get(ARG_FROM).asText();
			return new Status(ConstantStatus.OK, "", componentService.calenderGet(userId, argFrom));

		} catch (Exception e) {
			LOGGER.error("Error in calenderGet()", e);
			throw new TOMRuntimeException("Error in calenderGet()", e);
		}
	}

}
