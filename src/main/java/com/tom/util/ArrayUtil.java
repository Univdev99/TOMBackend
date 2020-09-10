package com.tom.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArrayUtil {
	static final Logger LOGGER = LoggerFactory.getLogger(ArrayUtil.class);

	private ArrayUtil() {
		throw new IllegalAccessError("Utility class");
	}
	
	/**
	 * To Convert String array to Long array 
	 * @param stringArray
	 * @return
	 * @author CCJoshi 
	 * @ModifiedBy CCJoshi
	 * @ModifiedDate Apr 3, 2018
	 */
	public static Long[] convertToLongArray(String[] stringArray) {
		Long[] blankArray = {};
		if (stringArray == null) {
			return blankArray;
		}
		
		Long[] longArray = new Long[stringArray.length];
		for (int i = 0; i < stringArray.length; i++) {
			longArray[i] = Long.valueOf(stringArray[i]);
		}
		return longArray;
	}
}
