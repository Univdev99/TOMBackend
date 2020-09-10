package com.tom.util;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class CommonFuncs {
	public static final int APPT_ATTRIB_ROS = 0x200;
	public static final int APPT_ATTRIB_HPI = 0x100;
	public static final int APPT_ATTRIB_PHYEXAM = 0x400;
	public static final int PATIENT_ATTRIB_VITALSIGNS = 0x2;
	public static int ATTRIB_PRESCRIPTION = 0x800;

	private CommonFuncs() {
		throw new IllegalAccessError("Utility class");
	}

	public static String maskZipFormat(String zipStr) {
		String str = zipStr;
		if (zipStr != null && zipStr.length() == 9 && zipStr.indexOf('-') == -1) {
			str = zipStr.substring(0, 5) + "-" + zipStr.substring(5, 9);
		}
		return str;
	}

	public static void saveToFolder(String content, String fileName) throws IOException {

		DataOutputStream out = null;
		try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
			out = new DataOutputStream(outputStream);
			out.writeBytes(content);

		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	boolean checkIsAttribExist(Integer currentAttrib, Integer attrib) {
		return (currentAttrib & attrib) != 0 ? true : false;
	}

	Integer addAttribToExist(Integer currentAttrib, Integer attrib) {
		return currentAttrib | attrib;
	}

	Integer reomveAttribToExist(Integer currentAttrib, Integer attrib) {
		return currentAttrib & ~attrib;
	}

	/**
	 * Escape single quote.
	 * 
	 * @author BRanpariya
	 * @param str
	 * @return
	 */
	public static String escapeSQLQuote(String str) {
		if (str != null) {
			str = str.replace("'", "''");
		}
		return str;
	}

	public static String replace(String psWord, String psReplace, String psNewSeg) {
		StringBuilder lsNewStr = new StringBuilder();
		int liFound = 0;
		int liLastPointer = 0;
		do {
			liFound = psWord.indexOf(psReplace, liLastPointer);
			if (liFound < 0) {
				lsNewStr.append(psWord.substring(liLastPointer, psWord.length()));
			} else {
				if (liFound > liLastPointer) {
					lsNewStr.append(psWord.substring(liLastPointer, liFound));
				}
				lsNewStr.append(psNewSeg);
				liLastPointer = liFound + psReplace.length();
			}
		} while (liFound > -1);
		return lsNewStr.toString();
	}

	public static String baseDir = "d:/Projects/emrs3";

	public static String convertToSQLUI(String str) throws Exception {
		String val = "";
		if (str != null && str.length() == 32) {
			val = str.substring(0, 8) + "-" + str.substring(8, 12) + "-" + str.substring(12, 16) + "-" + str.substring(16, 20) + "-" + str.substring(20);
		}
		return val;
	}

	public static String maskTaxIdFormat(String taxStr) {
		String str = "";
		if (taxStr != null && taxStr.length() == 9 && taxStr.indexOf('-') == -1) {
			str = taxStr.substring(0, 2) + "-" + taxStr.substring(2, 9);
		}
		return str;
	}

	public static String convert2USDate(String date) {
		if (date == null || "".equals(date)) {
			return "";
		}
		if (date.indexOf('-') == -1) {
			return date;
		}
		int d1 = date.indexOf('-');
		int d2 = date.indexOf('-', d1 + 1);
		int d3 = date.indexOf(' ') > -1 ? date.indexOf(' ') : date.length();

		try {

			return date.substring(d1 + 1, d2) + "/" + date.substring(d2 + 1, d3) + "/" + date.substring(0, d1);
		} catch (Exception e) {
			return date;
		}
	}

	public static String maskPhoneFormatForHCFAHTML(String phoneStr) {
		String str = "";
		if (phoneStr != null && phoneStr.length() == 10 && phoneStr.indexOf('-') == -1) {
			str = "(" + phoneStr.substring(0, 3) + ")-" + phoneStr.substring(3, 6) + "-" + phoneStr.substring(6, 10);
		}
		return str;
	}

	public static String getCommaSaparetedValue(String[] parameter) {
		if (parameter != null && parameter.length > 0) {
			String str = StringUtils.trim(StringUtils.join(parameter, ","));
			if (!str.isEmpty()) {
				return str;
			} else {
				return null;
			}
		} else {
			return null;
		}

	}

	public static String validateInput(String stringToCheck) {

		String string = "";
		if ((stringToCheck == null) || (stringToCheck.equals(""))) {
			return "";
		} else {

			string = stringToCheck.trim();
			StringBuilder stringBuilder = new StringBuilder();

			for (int i = 0; i < string.length(); i++) {
				char c1 = string.charAt(i);
				int c2 = (int) c1;

				switch (c2) {
				case 39:
					stringBuilder.append("''");
					break;
				default:
					stringBuilder.append(c1);
					break;
				}
			}
			return stringBuilder.toString();
		}
	}

	/**
	 * Retrieves Sent time
	 * 
	 * @return
	 * @throws Exception
	 */
	public static java.util.Date getSentTime() throws Exception {

		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
		String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);

		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		String currentTime = sdf.format(cal.getTime()) + ".5Z";
		java.util.Date currentTimeDate = (java.util.Date) sdf.parse(currentTime);

		return currentTimeDate;
	}

	public static String setNullIfBlank(String data) {
		data = StringUtils.isNotBlank(data) ? data : null;
		return data;
	}

	/**
	 * Get velocity template from specific path, replace value and return in String format
	 * 
	 * @param url
	 * @param templateMap
	 * @return
	 * @throws Exception
	 * 
	 * @author Anuja
	 */
	public static String getVelocityTemplate(String url, HashMap<String, Object> templateMap, VelocityEngine velocityEngine) throws Exception {
		VelocityContext velocityContext = new VelocityContext();
		Template template = velocityEngine.getTemplate(url);
		for (Map.Entry<String, Object> entry : templateMap.entrySet()) {
			velocityContext.put(entry.getKey(), entry.getValue());
		}
		StringWriter stringWriter = new StringWriter();
		template.merge(velocityContext, stringWriter);
		return stringWriter.toString();
	}

	public static StringBuilder getComaseperatedString(List<String> list) throws Exception {

		StringBuilder builder = new StringBuilder();

		for (Iterator iterator = list.iterator(); iterator.hasNext();) {

			String string = (String) iterator.next();

			if (builder.length() > 0)

				builder.append(", ");

			builder.append("\"").append(string).append("\"");

		}

		return builder;

	}

	public static String getMonthLabel(int monthNo) {
		String monthCode;
		switch (monthNo) {
		case 0:
			monthCode = "JAN";
			break;
		case 1:
			monthCode = "FEB";
			break;
		case 2:
			monthCode = "MAR";
			break;
		case 3:
			monthCode = "APR";
			break;
		case 4:
			monthCode = "MAY";
			break;
		case 5:
			monthCode = "JUN";
			break;
		case 6:
			monthCode = "JUL";
			break;
		case 7:
			monthCode = "AUG";
			break;
		case 8:
			monthCode = "SEP";
			break;
		case 9:
			monthCode = "OCT";
			break;
		case 10:
			monthCode = "NOV";
			break;
		case 11:
			monthCode = "DEC";
			break;
		default:
			monthCode = "";
		}
		return monthCode;
	}

	/**
	 * RAJ MEVADA
	 * 
	 * @param datePattern
	 * Enter pattern if required like ('MM-dd-yyyy' as default)
	 * @return currentDate into string
	 */
	public static String getdateIntoString(String datePattern) {

		String patten = "MM-dd-yyyy";
		if (!StringUtil.isNullorEmptyWithNullString(datePattern)) {
			patten = datePattern;
		}

		System.out.println(patten);
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(patten);
		return formatter.format(date);
	}

	public static String getServerName(HttpServletRequest request) {
		String serverName = "";

		if (null != request && null != request.getRequestURL()) {
			if (request.getServerName().contains("localhost")) {
				serverName = "Local Server";
			} else if (request.getServerName().contains("omniqa.omnimd.com") || request.getServerName().contains("omniqm.omnimd.com")) {
				serverName = "QA Server";
			} else if (request.getServerName().contains("omnionestaging.omnimd.com") || request.getServerName().contains("omnionestagingqm.omnimd.com")) {
				serverName = "Staging Server";
			} else if (request.getServerName().contains("omnione.omnimd.com") || request.getServerName().contains("omnioneqm.omnimd.com")) {
				serverName = "Production Server";
			}

		}
		return serverName;
	}
}
