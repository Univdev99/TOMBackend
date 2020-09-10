package com.tom.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

//import org.apache.xmlbeans.impl.piccolo.util.DuplicateKeyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.tom.common.beans.ObjectMapper;
import com.tom.common.constant.Constant;
import com.tom.common.exception.TOMRuntimeException;

/**
 * @author Anant Shah
 *
 */
public class JsonUtil implements Constant {
	static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

	private JsonUtil() {
		throw new IllegalAccessError("Utility class");
	}

	/**
	 * Convert request body into object node
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 * @author Anant Shah
	 * @ModifiedBy Anant Shah
	 * @ModifiedDate Feb 24, 2017
	 */
	public static ObjectNode objectNodeFromRequest(HttpServletRequest request, Boolean... isUnAuthorizeReq) throws Exception {
		LOGGER.debug("Execute method : objectNodeFromRequest()");
		ObjectMapper mapper = ObjectMapper.getObjectMapper();
		if (null != request.getAttribute(REQUEST_BODY)) {
			return (ObjectNode) mapper.readTree((String) request.getAttribute(REQUEST_BODY));
		}

		if (0 != isUnAuthorizeReq.length && isUnAuthorizeReq[0]) {
			BufferedReader reader = request.getReader();
			StringBuilder jb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				jb.append(line.trim());
			}
			MDC.put(REQUEST_PARAMS, jb.toString());
			return (ObjectNode) mapper.readTree(jb.toString());
		}
		return null;
	}

	/**
	 * @param json
	 * @param params
	 * @return
	 * @throws Exception
	 * @author Anant Shah
	 * @ModifiedBy Anant Shah
	 * @ModifiedDate Mar 24, 2017
	 */
	public static boolean hasRequiredParams(ObjectNode json, String[] params) throws Exception {
		LOGGER.debug("Execute method : hasRequiredParams()");
		boolean flag = true;
		for (String param : params) {
			if (null == json.get(param)) {
				flag = false;
				break;
			}

		}
		return flag;
	}

	/**
	 * For ex: { "bean":{"data":1,"data1":}} then you can check for data1 also, will return true of value is null or parameter is not passed.
	 * 
	 * @param json
	 * @param params
	 * @return
	 * @throws Exception
	 * @author BRanpariya
	 * @ModifiedBy BRanpariya
	 * @ModifiedDate Apr 21, 2017
	 */
	public static boolean hasRequiredParamsFromChild(ObjectNode json, String[] params) throws Exception {
		LOGGER.debug("Execute method : hasRequiredParams()");
		boolean flag = true;
		for (String param : params) {
			if (null == json.findValue(param) || "".equals(json.findValue(param).asText())) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	/**
	 * If Json not contain define property or Property have null value in it then it will return false Also check for child parameter based on checkForChild flag. for ex: if prescription [
	 * prescriptionDetail[ drugDetail:'a'] ] then it will check for drugDetail also.
	 * 
	 * @param json
	 * @param params
	 * @return
	 * @author BRanpariya
	 * @ModifiedBy pbhavsar
	 * @ModifiedDate Mar 22, 2017
	 * 
	 * Ex : check for below type of json : { "type" : "startsWith", "searchParam" : "test" }
	 */
	/* Check for null values of parameters */
	public static boolean isRequiredParamNull(ObjectNode json, String[] params) {
		boolean returnFlag = false;
		for (String param : params) {
			if (null == json.get(param) || NullNode.getInstance() == json.get(param) || StringUtil.isNullorEmpty(json.get(param).asText())) {
				returnFlag = true;
				break;
			}
		}
		return returnFlag;
	}

	/**
	 * @param json
	 * @param params
	 * @return
	 * @author pbhavsar
	 * @ModifiedBy
	 * @ModifiedDate
	 * 
	 * Ex : check for below type of json :
	 * 
	 * { "customDrug": { "status": "1" }, "clinicId": "1" }
	 */
	public static boolean isRequiredParamObjectNull(ObjectNode json, String[] params) {
		boolean returnFlag = false;
		for (String param : params) {
			if (null == json.get(param) || NullNode.getInstance() == json.get(param)
					|| (!json.get(param).isObject() && !json.get(param).isArray() && StringUtil.isNullorEmpty(json.get(param).asText()))) {

				returnFlag = true;
				break;
			}
		}
		return returnFlag;
	}

	/**
	 * @param request
	 * @param params
	 * @return
	 * @author KBaria
	 * @throws IOException
	 * @ModifiedBy
	 * @ModifiedDate
	 * 
	 * 
	 */
	public static void setRequestBodyParamters(HttpServletRequest request, Map<String, Object> params) throws IOException {
		ObjectMapper mapper = ObjectMapper.getObjectMapper();
		if (null != request.getAttribute(REQUEST_BODY) && !StringUtil.isNullorEmptyWithNullString(request.getAttribute(REQUEST_BODY).toString())) {
			final ObjectNode requestBody = (ObjectNode) mapper.readTree(request.getAttribute(REQUEST_BODY).toString());
			if (null != requestBody && null != params) {
				params.forEach((k, v) -> {
					// if (requestBody.has(k) && !requestBody.get(k).isNull()) {
					// throw new OmniMDRuntimeException("Property " + k + " already exist in json.", new DuplicateKeyException());
					// }
					requestBody.set(k, mapper.convertValue(v, JsonNode.class));
				});
				request.setAttribute(REQUEST_BODY, requestBody);
			}
		}
	}
}
