package com.tom.common.interceptor;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.tom.common.constant.Constant;
import com.tom.user.UserService;
import com.tom.util.DateUtil;
import com.tom.util.StringUtil;

public class AuthorizationInterceptor extends HandlerInterceptorAdapter implements Constant {
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationInterceptor.class);
	@Autowired
	UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (request.getContentType() != null && !request.getContentType().contains("multipart")) {
			BufferedReader reader = request.getReader();
			StringBuilder jb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				jb.append(line.trim());
			}
			request.setAttribute(REQUEST_BODY, jb.toString());
			MDC.put(REQUEST_PARAMS, jb.toString());
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		String requestEndTime = DateUtil.getCurrentTimestamp().toString();
		String actionName = null != request.getAttribute("actionName") ? request.getAttribute("actionName").toString()
				: "";
		MDC.put(REQUEST_END_TIME, requestEndTime);

		if (null != request.getAttribute(REQUEST_BODY)) {
			MDC.put(REQUEST_PARAMS, request.getAttribute(REQUEST_BODY).toString());
		}

		if (StringUtil.isNullorEmpty(actionName) || !actionName.contains("omnione/login/")) {
			MDC.put("STATUS", SUCCESSCAMEL);
		} else {
			MDC.put(REQUEST_PARAMS, null);
		}

		// To check Is AUDIT action for logging
		if (ONE.equals(MDC.get(IS_AUDIT_ACTION))) {
			LOGGER.info("Execute method : postHandle()");
		}
	}
}