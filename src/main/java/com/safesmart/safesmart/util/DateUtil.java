package com.safesmart.safesmart.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
	
	
	public static LocalDateTime convertStringToDateTime(String date) {
		LocalDateTime returnDate = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
		return returnDate;
	}
	
	// convert date "YYYY-mm-dd" to "dd-MM-yyyy" fromat
		public static String convertToStringDateFormat(String date) {
			String convertedDate = null;
			if (date != null) {
				String[] arrOfDate = date.split("-", 3);
				convertedDate = arrOfDate[2] + "-" + arrOfDate[1] + "-" + arrOfDate[0];
			}
			return convertedDate;

		}

}
