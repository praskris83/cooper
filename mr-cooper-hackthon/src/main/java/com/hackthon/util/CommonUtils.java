/**
 * 
 */
package com.hackthon.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * @author prasad
 *
 */
public class CommonUtils {

  public static boolean isPayDateValid(LocalDate curDate, LocalDate newdate) {
    int days = (int) ChronoUnit.DAYS.between(curDate, newdate);
    return Math.abs(days) <= 5 && newdate.isAfter(LocalDate.now().plusDays(2));
  }
}
