package com.tom.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.tom.common.exception.TOMRuntimeException;
import com.tom.common.security.UserContext;
import com.tom.user.User;

/**
 * Return User Object from Security Context
 * 
 * @author UJShah
 *
 */
public class CommonUtil {

	static final Logger LOGGER = LoggerFactory.getLogger(CommonUtil.class);

	private CommonUtil() {
		throw new IllegalAccessError("Utility class");
	}

	public static User getContextUser() {
		LOGGER.debug("Execute method : getContextUser()");
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (authentication != null) {
				UserContext userContext = (UserContext) authentication.getPrincipal();
				if (userContext != null) {
					return userContext.getUser();
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error in getContextUser() method :: " + e);
			throw new TOMRuntimeException("Error in getContextUser()", e);
		}
		return null;
	}

	/**
	 * @author VDave
	 * @param time
	 * @param timeFormat
	 * @return hour minute second for java.sql.time object base on timeFormat.
	 * @throws ParseException
	 */
	public static Time getTime(String time, SimpleDateFormat timeFormat) throws ParseException {
		LOGGER.debug("Execute method : getTime()");
		Date dt = timeFormat.parse(time);
		return new java.sql.Time(dt.getTime());
	}

	public static void replaceAll(StringBuilder sb, Pattern pattern, String replacement) {
		Matcher m = pattern.matcher(sb);
		int start = 0;
		while (m.find(start)) {
			sb.replace(m.start(), m.end(), replacement);
			start = m.start() + replacement.length();
		}
	}

	public static void createZipFileinFolder(String localDownloadDirectory) throws IOException {
		// Logic to make zip file
		File dir = new File(localDownloadDirectory);
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return !name.endsWith(".zip");
			}
		};
		String[] children = dir.list(filter);

		LOGGER.info("children Length=====" + children.length);
		LOGGER.info("children =====" + children);

		if (children == null || children.length == 0) {
			LOGGER.info("No ERA files available in  ERAFiles/Temp directory.");
		} else {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			String outFilename = localDownloadDirectory + "/" + "GW_" + dateFormat.format(new Date()).toString() + ".zip";
			ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(outFilename));

			for (int g = 0; g < children.length; g++) {
				// Get filename of file or directory
				String filename = localDownloadDirectory + "/" + children[g];
				LOGGER.info("filename>>>>" + filename);
				File file = new File(filename);
				if (file.isFile()) {
					FileInputStream in = new FileInputStream(new File(filename));
					zos.putNextEntry(new ZipEntry(children[g]));
					int j = -1;
					while ((j = in.read()) > -1) {
						zos.write(j);
					}
					in.close();
					zos.closeEntry();

					File outputFile = new File(filename);
					outputFile.delete();
				}
			}
			zos.close();
		}
	}
}
