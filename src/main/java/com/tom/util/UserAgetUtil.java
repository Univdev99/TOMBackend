package com.tom.util;

import com.tom.common.constant.Constant;

public class UserAgetUtil implements Constant {

	private static final String EDGE = "Edge";
	private static final String MSIE = "MSIE";
	private static final String OPERA = "Opera";
	private static final String CHROME = "Chrome";
	private static final String FIREFOX = "Firefox";
	private static final String SAFARI = "Safari";
	private static final String RV = "rv";

	public static String getBrowserName(String userAgent) {
		String browser = "unknown";
		String version = "unknown";

		if (!StringUtil.isNullorEmpty(userAgent)) {
			if (userAgent.contains(EDGE)) {
				browser = "Microsoft Edge";
				String substring = userAgent.substring(userAgent.indexOf(EDGE)).split(SPACE)[0];
				version = substring.split(FORWARD_SLASH)[1];
			} else if (userAgent.contains(MSIE)) { // Internet Explorer
				browser = "Microsoft Internet Explorer";
				String substring = userAgent.substring(userAgent.indexOf(MSIE)).split(SPACE)[0];
				version = substring.split(FORWARD_SLASH)[1];
			} else if (userAgent.contains(OPERA)) {
				browser = OPERA;
				String substring = userAgent.substring(userAgent.indexOf(OPERA)).split(SPACE)[0];
				version = substring.split(FORWARD_SLASH)[1];
			} else if (userAgent.contains(CHROME)) {
				browser = "Google Chrome";
				String substring = userAgent.substring(userAgent.indexOf(CHROME)).split(SPACE)[0];
				version = substring.split(FORWARD_SLASH)[1];
			} else if (userAgent.contains(FIREFOX)) {
				browser = "Mozilla Firefox";
				String substring = userAgent.substring(userAgent.indexOf(FIREFOX)).split(SPACE)[0];
				version = substring.split(FORWARD_SLASH)[1];
			} else if (userAgent.contains(SAFARI) && userAgent.contains("version")) {
				browser = SAFARI;
				String substring = userAgent.substring(userAgent.indexOf(SAFARI)).split(SPACE)[0];
				version = substring.split(FORWARD_SLASH)[1];
			} else if (userAgent.contains(RV)) { // Internet Explorer 11
				browser = "Internet Explorer Edge";
				String substring = userAgent.substring(userAgent.indexOf(RV), userAgent.indexOf(')'));
				version = substring.split(COLON)[1];
			}
		}
		return browser + SPACE + version;
	}
}
