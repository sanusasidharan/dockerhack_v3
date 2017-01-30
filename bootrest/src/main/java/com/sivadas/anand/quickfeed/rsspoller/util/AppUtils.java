package com.sivadas.anand.quickfeed.rsspoller.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class AppUtils {

	public static String DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss z";
	
	// Fri, 16 Dec 2016 05:30:00 GMT
	public static String toSimpleDateFormat(Date thisDate) {
		
		String formattedDate = null;
		if (null != thisDate) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
			formattedDate =  simpleDateFormat.format(thisDate);
		}
		
		return formattedDate;
	}
	
	
	public static Date toDateFromString(String formattedDateString) throws ParseException {
		
		Date convertedDate = null;
		if (StringUtils.isNotBlank(formattedDateString)) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
			convertedDate = simpleDateFormat.parse(formattedDateString);
		}
		
		return convertedDate;
	}

}
