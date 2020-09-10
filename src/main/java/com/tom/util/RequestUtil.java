
package com.tom.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestUtil {
	static final Logger LOGGER = LoggerFactory.getLogger(RequestUtil.class);

	private RequestUtil() {

	}

	public static String getParametersAsQueryString(HttpServletRequest request) {
		@SuppressWarnings("rawtypes")
		Enumeration paramNames = request.getParameterNames();
		StringBuilder sb = new StringBuilder();
		while (paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement().toString();
			String[] paramValues = request.getParameterValues(paramName);
			for (int i = 0; i < paramValues.length; i++) {
				String paramValue = paramValues[i];
				sb.append("&");
				sb.append(paramName);
				sb.append("=");
				sb.append(paramValue);
			}
		}
		return sb.toString();
	}

	public static String makeHttpsRequest(String urlValue, Map<String, String> paramters, String type,
			String requestStr) {
		LOGGER.debug("Executing method : makeHttpsRequest()");
		InputStreamReader isr = null;
		BufferedReader bin = null;
		String responseData = null;
		HttpURLConnection con = null;
		try {
			URL url = new URL(urlValue);
// HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			con = (HttpURLConnection) url.openConnection();
			for (Map.Entry<String, String> entry : paramters.entrySet()) {
				con.setRequestProperty(entry.getKey().toString(), entry.getValue().toString());
			}
			con.setRequestMethod(type);
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setReadTimeout(15 * 10000);
// out = con.getOutputStream();
			if (!StringUtil.isNullorEmpty(requestStr)) {
				byte[] requestMessage = requestStr.getBytes();
// con.setRequestProperty("Content-Length", String.valueOf(requestStr.length()));
				con.getOutputStream().write(requestMessage);
			} else {
				con.getOutputStream();
			}
// con.connect();
			isr = new InputStreamReader(con.getInputStream());
			bin = new BufferedReader(isr);
			String inputLine;
			StringBuilder replyBfr = new StringBuilder();
			while ((inputLine = bin.readLine()) != null) {
				replyBfr.append(inputLine);
			}
			responseData = replyBfr.toString();
		} catch (Exception e) {
			LOGGER.error("Getting exception in making request with URL", e);
		}
		return responseData;
	}

}
