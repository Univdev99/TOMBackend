package com.tom.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Chandani Joshi
 *
 */
public class DateUtil {

	static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);

	private DateUtil() {
		throw new IllegalAccessError("Utility class");
	}

	// no need of this because we provide date and time to server are always UTC
	// time.
	/*
	 * static { TimeZone.setDefault(TimeZone.getTimeZone("GMT")); }
	 */
	public static final String ONLY_DATE_FORMAT = "MM/dd/yyyy";
	public static final String FOLDER_NAME = "yyyyMMdd";
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy_MM_dd_hh_mm_ss";
	static final String HYPHEN = "'-'";

	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(getCurrentDate().getTime());
	}

	public static Date getCurrentDate() {
		try {
			SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
			dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));

			// Local time zone
			SimpleDateFormat dateFormatLocal = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");

			// Time in GMT
			return dateFormatLocal.parse(dateFormatGmt.format(new Date()));
		} catch (Exception e) {
			return new Date(Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTimeInMillis());
		}
	}

	public static Date convertStringToDate(String dateInString) throws Exception {
		String usDateString = convert2USDate(dateInString);
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		return formatter.parse(usDateString);
	}

	public static String convert2USDate(String date) {
		if (date == null || "".equals(date)) {
			return "";
		}
		if (date.indexOf(HYPHEN) == -1) {
			return date;
		}
		int d1 = date.indexOf(HYPHEN);
		int d2 = date.indexOf(HYPHEN, d1 + 1);
		int d3 = date.indexOf("' '");

		try {
			return date.substring(d1 + 1, d2) + "/" + date.substring(d2 + 1, d3) + "/" + date.substring(0, d1);
		} catch (Exception e) {
			LOGGER.debug("error in convert2USDate" + e);
			return date;
		}
	}

	/**
	 * This method return String time format to Timestamp Format
	 * 
	 * @param dateStr
	 * @return
	 * @throws Exception
	 * @author BRPatel
	 * @ModifiedBy BRPatel
	 * @ModifiedDate 27-Mar-2017
	 */
	public static Timestamp convertStringToTimeStamp(String dateStr) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		java.util.Date parsedDate = dateFormat.parse(dateStr);
		return new Timestamp(parsedDate.getTime());
	}

	/**
	 * This method is used to calculate different between two date
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * @author BRPatel
	 * @ModifiedBy BRPatel
	 * @ModifiedDate 27-Mar-2017
	 */
	public static long getDateDiffferenceInDays(Date date1, Date date2) {
		long dateDiff = date1.getTime() - date2.getTime();
		return dateDiff / (1000 * 24 * 60 * 60);
	}

	/**
	 * This metod return string Date to date format.
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 * @author BRPatel
	 * @ModifiedBy BRPatel
	 * @ModifiedDate 27-Mar-2017
	 */
	public static Date stringToDateWithoutTime(String date) throws ParseException {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date d1 = dateFormat.parse(date);
		c.setTime(d1);
		return d1;

	}

	/**
	 * This method return TimeStamp conversion of Date string and given Format
	 * 
	 * @param Date
	 * String and Format
	 * @return
	 * @throws ParseException
	 * @author DTalati
	 * @ModifiedBy DTalati
	 * @ModifiedDate 30-May-2017
	 */
	public static Timestamp parseTimestamp(String dateStr, String format) throws ParseException {
		Date date = new SimpleDateFormat(format).parse(dateStr);
		return new Timestamp(date.getTime());
	}

	/**
	 * This method return string Date adding the no of days passed in parameter.
	 * 
	 * @param days
	 * in number
	 * @return
	 * @throws ParseException
	 * @author DTalati
	 * @ModifiedBy DTalati
	 * @ModifiedDate 30-May-2017
	 */
	public static String addDaysInCurrentDays(int days) {
		Calendar calendar = Calendar.getInstance();
		Date additionDate = addDaysInCalendar(calendar, days);
		return new SimpleDateFormat(ONLY_DATE_FORMAT).format(additionDate);
	}

	/**
	 * This method return Date adding the no of days to calendar passed in parameter.
	 * 
	 * @param calendar
	 * and days in number
	 * @return
	 * @throws ParseException
	 * @author DTalati
	 * @ModifiedBy DTalati
	 * @ModifiedDate 30-May-2017
	 */
	public static Date addDaysInCalendar(Calendar calendar, int days) {
		calendar.add(Calendar.DAY_OF_YEAR, days);
		return calendar.getTime();
	}

	public static String getSystemHours() {
		String retVal = "" + Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		if (retVal.length() == 1) {
			retVal = "0" + retVal;
		}
		return retVal;
	}

	public static String getSystemMinutes() {
		String retVal = "" + Calendar.getInstance().get(Calendar.MINUTE);
		if (retVal.length() == 1) {
			retVal = "0" + retVal;
		}
		return retVal;
	}

	public static String getSystemSeconds() {
		String retVal = "" + Calendar.getInstance().get(Calendar.SECOND);
		if (retVal.length() == 1) {
			retVal = "0" + retVal;
		}
		return retVal;
	}

	public static String formatTimestamp(Timestamp timestamp, String format) {
		if (timestamp == null) {
			return null;
		}
		if (format == null || "".equals(format)) {
			format = ONLY_DATE_FORMAT;
		}
		return new SimpleDateFormat(format).format(timestamp);
	}

	public static Date getDateOFStartOfDay() {
		Date curretnDate = getCurrentDate();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(curretnDate);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	public static Timestamp getTimeStampOfStartOfDay() {
		return new Timestamp(getDateOFStartOfDay().getTime());
	}

	public static String getCustomDateFormat(String date, String pattern) {
		String output = "";
		try {
			output = new SimpleDateFormat(pattern).format(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date));
		} catch (ParseException e) {
			LOGGER.error("error occured" + e);
		}

		return output;
	}

	public static Timestamp getCurrentTimestamp(Integer day) {

		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(getCurrentTimestamp().getTime());
		cal.add(Calendar.DAY_OF_MONTH, day);

		return new Timestamp(new Date(cal.getTimeInMillis()).getTime());
	}
	
	public static String getCurrentTimeinString() {
		 SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		 sdf.setTimeZone(TimeZone.getTimeZone("IST"));
		 
		 return sdf.format(new Date());
	}
}
