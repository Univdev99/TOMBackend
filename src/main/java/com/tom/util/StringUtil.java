/**
 * 
 */
package com.tom.util;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Chandani Joshi
 *
 */
public class StringUtil {
	static final Logger LOGGER = LoggerFactory.getLogger(StringUtil.class);

	private StringUtil() {

	}

	/**
	 * <pre>
	 * StringUtil.ifNull(null, "Z")  = "Z"
	 * StringUtil.ifNull("ABC", "Z") = "ABC"
	 * </pre>
	 * 
	 * @param string
	 * the String to check
	 * @param replacement
	 * the String to return if string-to-check is null
	 * @return
	 */
	public static String ifNull(String string, String replacement) {
		try {
			if (string == null) {
				return replacement;
			} else {
				return string;
			}
		} catch (Exception e) {
			LOGGER.error("Error in ifNull()", e);
			throw e;
		}
	}

	/**
	 * <pre>
	 * StringUtil.in("b", "a,b,c")  = true
	 * StringUtil.in("e", "a,b,c")  = false
	 * </pre>
	 */
	public static boolean in(String string, String valuesStr) {
		try {
			return in(string, valuesStr, ",");
		} catch (Exception e) {
			LOGGER.error("Error in in()", e);
			throw e;
		}
	}

	/**
	 * <pre>
	 * StringUtil.in("a,b,c", "a,b,c:x,y,z", ":")  = true
	 * StringUtil.in("a,b", "a,b,c:x,y,z", ":") = false
	 * </pre>
	 */
	public static boolean in(String string, String valuesStr, String delimeter) {
		try {
			String[] values = valuesStr.split(delimeter);
			return ArrayUtils.contains(values, string);
		} catch (Exception e) {
			LOGGER.error("Error in in()", e);
			throw e;
		}
	}

	/**
	 * <pre>
	 * StringUtil.notIn("b", "a,b,c")  = false
	 * StringUtil.notIn("e", "a,b,c")  = true
	 * </pre>
	 */
	public static boolean notIn(String string, String valuesStr) {
		try {
			return notIn(string, valuesStr, ",");
		} catch (Exception e) {
			LOGGER.error("Error in notIn()", e);
			throw e;
		}
	}

	/**
	 * <pre>
	 * StringUtil.notIn("a,b,c", "a,b,c:x,y,z", ":")  = false
	 * StringUtil.notIn("a,b", "a,b,c:x,y,z", ":") = true
	 * </pre>
	 */
	public static boolean notIn(String string, String valuesStr, String delimeter) {
		try {
			return !(in(string, valuesStr, delimeter));
		} catch (Exception e) {
			LOGGER.error("Error in notIn()", e);
			throw e;
		}
	}

	/**
	 * Check if string is null or empty
	 * 
	 * @param str
	 * @return
	 *
	 */
	public static boolean isNullorEmpty(String str) {
		try {
			return null == str || "".equals(str.trim()) || StringUtils.isBlank(str) || StringUtils.isEmpty(str) || "null".equals(str);
		} catch (Exception e) {
			LOGGER.error("Error in isNullorEmpty()", e);
			throw e;
		}
	}

	/**
	 * Check if string builder is null or empty
	 * 
	 * @param str
	 * @return
	 *
	 */
	public static boolean isNullorEmpty(StringBuilder str) {
		try {
			return null == str || str.length() == 0 || StringUtils.isBlank(str.toString());
		} catch (Exception e) {
			LOGGER.error("Error in isNullorEmpty()", e);
			throw e;
		}
	}

	/**
	 * Check if string is null or empty
	 * 
	 * @param str
	 * @return
	 *
	 */
	public static boolean isNullorEmptyWithNullString(String str) {
		try {
			return null == str || "".equals(str.trim()) || StringUtils.isBlank(str) || StringUtils.isEmpty(str) || "null".equals(str.toLowerCase());
		} catch (Exception e) {
			LOGGER.error("Error in isNullorEmpty()", e);
			throw e;
		}
	}

	public static String[] removeElements(String[] arr, String element) throws Exception {
		if (arr == null) {
			throw new RuntimeException("Array can not be null");
		}

		String[] result = new String[0];

		for (int i = 0; i < arr.length; i++) {
			if (element.equals(arr[i])) {
			} else {
				result = (String[]) ArrayUtils.add(result, arr[i]);
			}
		}
		return result;
	}

	/**
	 * @param zipStr
	 * @return
	 * @author Yogi Thanki
	 * @ModifiedBy Yogi Thanki
	 * @ModifiedDate Mar 24, 2017
	 */
	public static String maskZipFormat(String zipStr) throws Exception {
		LOGGER.debug("Execute method : maskZipFormat()");
		String str = zipStr;
		if (zipStr != null && zipStr.length() == 9 && zipStr.indexOf('-') == -1) {
			str = zipStr.substring(0, 5) + "-" + zipStr.substring(5, 9);
		}
		return str;
	}

	/**
	 * Convert special character from string into numeric reference. e.g. & into &#38;#38;
	 * 
	 * @param str
	 * @return
	 * 
	 * @author Anuja
	 */
	public static String encodeWithNumericReference(String str) {
		if (null != str && !"".equals(str.trim())) {
			str = str.replace("&", "&#38;#38;");
			str = str.replace("<", "	&#38;#60;");
			str = str.replace(">", "&#62;");
			str = str.replace("\"", "&#39;");
			str = str.replace("'", "&#34;");
		}
		return str;
	}

}
