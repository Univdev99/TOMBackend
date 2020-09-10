package com.tom.common.interceptor;

/**
 * 
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.tom.common.constant.Constant;
import com.tom.user.User;
import com.tom.util.CommonUtil;
import com.tom.util.DateUtil;


public class LogbackMDCInterceptor extends HandlerInterceptorAdapter implements Constant {
	private static final Logger LOGGER = LoggerFactory.getLogger(LogbackMDCInterceptor.class);


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.getSession(false);
		String userName = null;
		String userRole = null;
		Long userId = DEFAULT_VALUE;

		User user = CommonUtil.getContextUser();

		if (user != null) {
			if (StringUtils.isNotBlank(user.getWorkEmail())) {
				userName = user.getWorkEmail();
			}

			if (null != user.getUserId()) {
				userId = user.getUserId();
			}
		}

		MDC.put(USER_NAME, userName);
		MDC.put(USER_ROLE, userRole);
		MDC.put(USER_ID, (null != userId ? Long.toString(userId) : null));

		MDC.put(IS_AUDIT_ACTION, Long.toString(DEFAULT_VALUE));
		String userAgent = request.getHeader("User-Agent");
		MDC.put(USER_AGENT, userAgent);
		MDC.put("STATUS", FAILED);
		String serverName = request.getServerName();
		MDC.put(SERVER_NAME, serverName);

		String serverIP = request.getLocalAddr();
		MDC.put(SERVER_IP, serverIP);

		String urlString = request.getRequestURL().toString();
		MDC.put(URL_STRING, urlString);

		String requestStartTime = DateUtil.getCurrentTimestamp().toString();
		MDC.put(REQUEST_START_TIME, requestStartTime);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		MDC.clear();
	}
}
