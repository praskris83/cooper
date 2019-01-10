/**
 * 
 */
package com.hackthon.util;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Properties;

/**
 * @author prasad
 *
 */
public class CommonUtils {

	static Properties props = null;

	static final String PROPERTY_FILE = "template.properties";

	public static boolean isPayDateValid(LocalDate curDate, LocalDate newdate) {
		int days = (int) ChronoUnit.DAYS.between(curDate, newdate);
		return Math.abs(days) <= 5 && newdate.isAfter(LocalDate.now().plusDays(2));
	}

	public static boolean isCallBackDateValid(LocalDate callbackDt) {
		return callbackDt.isAfter(LocalDate.now());
	}

	public static LocalDate getCallbackDate(List<LocalDate> callBackDates) {
		return callBackDates.stream().filter(newDate -> isCallBackDateValid(newDate)).findFirst().orElse(null);
	}

	public static LocalDate getNewPaymentDate(LocalDate curDate, List<LocalDate> newDates) {
		return newDates.stream().filter(newDate -> isPayDateValid(curDate, newDate)).findFirst().orElse(null);
	}

	public static void load() throws IOException {
		try (InputStream stream = CommonUtils.class.getClassLoader().getResourceAsStream(PROPERTY_FILE)) {
			props = new Properties();
			props.load(stream);
		}
	}

	public static String getTemplate(String key) throws IOException {
		if (props == null) {
			load();
		}
		return props.getProperty(key);
	}

	public static boolean confirmMsgParser(String msg) {
		if (msg.contains("yes") || msg.contains("mm") || msg.contains("ok")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean findReadyToChangeStringPattern(String msg) {
		if (msg.contains("need to change paymentdate") || msg.contains("need to change date")
				|| msg.contains("change date")) {
			return true;
		}
		return false;

	}

}
