package com.atoz.lj.pay.common.utility;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UtilityDateBean {

	private static final Logger log = LoggerFactory.getLogger(UtilityDateBean.class);
	private static String DATE_SEPERATOR = "/";
	public static final String DATE_FORMAT = "dd"+DATE_SEPERATOR+"MM"+DATE_SEPERATOR+"yyyy";
	public static final String DATE_TIME_FORMAT = "dd"+DATE_SEPERATOR+"MM"+DATE_SEPERATOR+"yyyy HH:mm";
	public static final String XML_DATE_FORMAT = "yyyy-MM-dd";
	public static final String TIMESTAMP_FORMAT = "dd"+DATE_SEPERATOR+"MMM"+DATE_SEPERATOR+"yyyy HH:mm";
	public static final String TIMESECOND_FORMAT = "dd"+DATE_SEPERATOR+"MMM"+DATE_SEPERATOR+"yyyy HH:mm:ss";
	public static final String TIME_FORMAT = "HH:mm";
	public static final String COMPRESS_FORMAT = "yyyyMMddHHmmss";
	
	public static final String DATE_FORMAT_JS = "dd"+DATE_SEPERATOR+"mm"+DATE_SEPERATOR+"yy";
	public static final String TIMESTAMP_FORMAT_JS = "dd"+DATE_SEPERATOR+"M"+DATE_SEPERATOR+"yy hh:mm";
	public static final String TIME_FORMAT_JS = "hh:mm";
	public static final String TIME_FORMAT_AMPM = "hh:mm aa";
	public static final String MONTHYEAR_FORMAT = "MMyyyy";
	public static final String MMM_YEAR = "MMM yyyy";
	
	public static final String DD_MMM_YYYY_FORMAT="dd MMM yyyy";
	public static final String CALENDAR_DATE_FORMAT="yyyy-MM-dd'T'HH:mm:ss";
	

	public static String displayTimeZone(TimeZone tz) {

		long hours = TimeUnit.MILLISECONDS.toHours(tz.getRawOffset());
		long minutes = TimeUnit.MILLISECONDS.toMinutes(tz.getRawOffset())
                                  - TimeUnit.HOURS.toMinutes(hours);
		// avoid -4:-30 issue
		minutes = Math.abs(minutes);

		String result = "";
		if (hours > 0) {
			result = String.format("(GMT+%d:%02d)", hours, minutes);
		} else {
			result = String.format("(GMT%d:%02d)", hours, minutes);
		}

		return result;

	}
	
	public enum MalayMonth{
		JAN(1,"Januari"),
		FEB(2,"Februari"),
		MAR(3,"Mac"),
		APR(4,"April"),
		MAY(5,"Mei"),
		JUN(6,"Jun"),
		JUL(7,"Julai"),
		AUG(8,"Ogos"),
		SEP(9,"September"),
		OCT(10,"Oktober"),
		NOV(11,"November"),
		DEC(12,"Disember"),
		;
		
		private int month;
		private String monthStr;
		
		MalayMonth(int month, String monthStr){
			this.month = month;
			this.monthStr = monthStr;
		}
		
		public int getMonth(){
			return this.month;
		}
		
		public String getMonthStr(){
			return this.monthStr;
		}
		
		public static String getMonthByInt(int month){
			 for (MalayMonth obj : MalayMonth.values()) {
		        if(obj.getMonth() == month) {
		            return obj.getMonthStr();
		        }
		    }
			 return "";
		}
	}
	
	public static TimeZone getDefaultTimeZone() {
		return TimeZone.getTimeZone(getTimeZoneString());
	}
	
	public static String getTimeZoneString() {
		return "Asia/Kuala_Lumpur";
	}
	
	public static boolean getIsFirstDayOfMonth(Date date) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return 1 == calendar.get(Calendar.DAY_OF_MONTH) ? true : false;
	}

	public static boolean getIsLastDayOfMonth(Date date) throws Exception {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		return lastDay == calendar.get(Calendar.DAY_OF_MONTH) ? true : false;
	}
	
	public static Date convertDateFromString(String value) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		try {
			if (value != null) {
				Date date = sdf.parse(value);
				return date;
			}
			return null;
		} catch (ParseException e) {
			log.error(e.getMessage());
			return null;
		}
	}
	
	public static Date convertDateFromString(String value,String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		try {
			if (value != null) {
				Date date = sdf.parse(value);
				return date;
			}
			return null;
		} catch (ParseException e) {
			log.error(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Is date format matched
	 * 
	 * @param dateFormat
	 * @param strDate
	 * @return
	 */
	public static boolean checkDateFormat(String dateFormat, String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

		try {
			Date tempDate = sdf.parse(strDate);
			if (!sdf.format(tempDate).equals(strDate)) {
				throw new Exception("Invalid date.");
			}
			return true;
		} catch (ParseException e) {
			log.error(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return false;
	}
	
	/**
	 * convert date to string, if format is null default date format will be applied
	 * @param inDate
	 * @param format
	 * @return
	 */
	public static String convertDateToString(Date inDate ,String format){
		String outDate = ""; 
		if (inDate != null){
			SimpleDateFormat sdf = new SimpleDateFormat(format == null ? DATE_FORMAT : format,Locale.US);
			sdf.setTimeZone(getDefaultTimeZone());
			outDate = sdf.format(inDate);
		}
		return outDate;
	}
	
	public static Date getCurrentDate(){
		return new LocalDateTime().toDate();
	}
	
	public static Date getCurrentDate(String dateFormat, String timeZoneStr) {

		Date todayDate = new Date();
		/* Specifying the format */
		DateFormat todayDateFormat = new SimpleDateFormat(dateFormat);
		/* Setting the Timezone */
		todayDateFormat.setTimeZone(TimeZone.getTimeZone(timeZoneStr));
		/* Picking the date value in the required Format */
		String strTodayDate = todayDateFormat.format(todayDate);

		return new LocalDateTime().toDate();
	}
	
	public static GregorianCalendar getCalendar(Date date){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar;
	}
	
	public static GregorianCalendar getCurrentDateCalendar(){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(getCurrentDate());
		return calendar;
	}
	
	public static Date setDayAndSetTime(Date date, int hour, int minute, int second, int daysToModify){
		Calendar cal = getCalendar(addDaysFromDate(date, daysToModify));
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	public static Date getBeginingOfDay(Date date){
		Calendar cal = getCalendar(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	public static Date getEndOfDay(Date date){
		Calendar cal = getCalendar(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}
	
	/**
	 * Calculate difference in day. Date 2 must be later than date 1
	 * @param dt1
	 * @param dt2
	 * @return
	 */
	public static long calculateDifferentInDays(Date dt1, Date dt2){
		long diff = dt2.getTime() - dt1.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	/**
	 * Calculate difference in day. Date 2 must be later than date 1
	 * @param dt1
	 * @param dt2
	 * @return
	 */
	public static long calculateDifferentInDaysExcludeTime(Date dt1, Date dt2){
		Date date1 = getBeginingOfDay(dt1);
		Date date2 = getBeginingOfDay(dt2);
		long diff = date2.getTime() - date1.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	public static long calculateDifferenceInMonths(Date beginningDate, Date endingDate) {
        Calendar cal1 = new GregorianCalendar();
        cal1.setTime(beginningDate);
        Calendar cal2 = new GregorianCalendar();
        cal2.setTime(endingDate);
        
        int month1 = cal1.get(Calendar.YEAR) * 12 + cal1.get(Calendar.MONTH);
        int month2 = cal2.get(Calendar.YEAR) * 12 + cal2.get(Calendar.MONTH);
        return month2 - month1;
    }
	
	public static Date addDaysFromDate(Date date, int daysToModify){
		Calendar cal = getCalendar(date);
		cal.add(Calendar.DATE, daysToModify);
		return cal.getTime();
	}
	
	public static Date addMonthsFromDate(Date date, int monthsToModify){
		Calendar cal = getCalendar(date);
		cal.add(Calendar.MONTH, monthsToModify);
		return cal.getTime();
	}
	
	public static Date addYearsFromDate(Date date, int yearsToModify){
		Calendar cal = getCalendar(date);
		cal.add(Calendar.YEAR, yearsToModify);
		return cal.getTime();
	}
	
	public static Date addMilisecondFromDate(Date date, int milisecondToModify){
		Calendar cal = getCalendar(date);
		cal.add(Calendar.MILLISECOND, milisecondToModify);
		return cal.getTime();
	}
	
	public static Date addHourFromDate(Date date, int minuteToModify){
      Calendar cal = getCalendar(date);
      cal.add(Calendar.HOUR, minuteToModify);
      return cal.getTime();
  }
	
	
	public static String getFullDateOnMalayMonth(Date date, String seperator){
		  String seperate = " ";
		  if(seperator!=null){
			  seperate = seperator;
		  }
		  GregorianCalendar calendar = new GregorianCalendar();
	      calendar.setTime(date);
	      String result = "";
	      result+=calendar.get(Calendar.DATE);
	      result+=seperate+MalayMonth.getMonthByInt(calendar.get(Calendar.MONTH)+1);//jan is 0
	      result+=seperate+calendar.get(Calendar.YEAR);;
	      return result;
	}
	
	public static Date getLastDateOfMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }
	
	public static Date getFirstDateOfMonth(Date date){
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      cal.set(Calendar.DAY_OF_MONTH, 1);
      return cal.getTime();
	}
	
	public static Date getSpecificDateOfMonth(String day, Date date){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return convertDateFromString(day+DATE_SEPERATOR+String.valueOf(getMonthInt(date))+DATE_SEPERATOR+calendar.get(Calendar.YEAR));
	}
	
	public static String getMonthMMMMformatByInteger(int month) {
		String monthString = new DateFormatSymbols().getMonths()[month-1];
		return monthString;
	}
	
	public static Date convertDateFromDateAndTimeString(Date date, String time){
		
		if(date==null){
			date = new Date();
		}
		
		try {
			SimpleDateFormat df = new SimpleDateFormat(TIME_FORMAT_AMPM);
			Date timeDate = df.parse(time);
			GregorianCalendar timeCalendar = new GregorianCalendar();
			timeCalendar.setTime(timeDate);
			GregorianCalendar calendar = getCalendar(date);
			calendar.set(Calendar.HOUR_OF_DAY, timeCalendar.get(Calendar.HOUR_OF_DAY));
			calendar.set(Calendar.MINUTE, timeCalendar.get(Calendar.MINUTE));
			calendar.set(Calendar.SECOND, 0);
			
			return calendar.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static String convertDateToTimeString(Date inDate, String timeFormat){
		String outDate = ""; 
		if (inDate != null){
			SimpleDateFormat sdf = new SimpleDateFormat(StringUtils.isBlank(timeFormat) ? TIME_FORMAT_AMPM : timeFormat,Locale.US);
			sdf.setTimeZone(getDefaultTimeZone());
			outDate = sdf.format(inDate);
		}
		return outDate;
	}
	
	public static int getCurrentMonth() {
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}
	
	public static int getCurrentYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}
	
	public static String convertDateToMonthYearString(Date inDate){
		String outDate = "-"; 
		if (inDate != null){
			SimpleDateFormat sdf = new SimpleDateFormat(MONTHYEAR_FORMAT);
			sdf.setTimeZone(getDefaultTimeZone());
			outDate = sdf.format(inDate);
		}
		return outDate;
	}
	
	public static Date getFirstDayOfTheMonth(Date date){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH,1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set( Calendar.MINUTE, 0 );
		calendar.set( Calendar.SECOND, 0 );
	    calendar.set( Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	public static boolean isDate1IsOnPreviousMonthOfDate2(Date date1, Date date2){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date1);
		GregorianCalendar calendar2 = new GregorianCalendar();
		calendar2.setTime(date2);
		if(calendar.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)){
			return calendar.get(Calendar.MONTH) < calendar2.get(Calendar.MONTH);
		}else if(calendar.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)-1){
			return calendar.get(Calendar.MONTH) == (calendar2.get(Calendar.MONTH) + 11 );
		}else{
			return false;
		}
	}
	
	public static boolean isDate1IsSameMonthYearWithDate2(Date date1, Date date2){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date1);
		GregorianCalendar calendar2 = new GregorianCalendar();
		calendar2.setTime(date2);
		return calendar.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) &&
				calendar.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH);
	}

	public static boolean isDate1IsSameDayWithDate2(Date date1, Date date2){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date1);
		GregorianCalendar calendar2 = new GregorianCalendar();
		calendar2.setTime(date2);
		return calendar.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) &&
				calendar.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH) &&
				calendar.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH);
	}
	
	public static boolean isDate1IsSameWeekWithDate2(Date date1, Date date2){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date1);
		GregorianCalendar calendar2 = new GregorianCalendar();
		calendar2.setTime(date2);
		return calendar.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) &&
				calendar.get(Calendar.WEEK_OF_YEAR) == calendar2.get(Calendar.WEEK_OF_YEAR);
	}
	
	public static boolean isDate1IsSameYearWithDate2(Date date1, Date date2){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date1);
		GregorianCalendar calendar2 = new GregorianCalendar();
		calendar2.setTime(date2);
		return calendar.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR);
	}
	
	public static int getMonthInt(Date date){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH);
		return month+1;
	}
	
	public static int getYearInt(Date date){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		return year;
	}
	
	public static int getNumberOfDaysForTheMonth(Date date){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	public static int getNumberOfDaysForTheYear(Date date){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
	}
	
	public static Date getNextYearOfDate(Date date){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR)+1);
		return calendar.getTime();
	}
	
	public static Date getFirstDateOfYear(Date date){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return convertDateFromString("01/01/"+calendar.get(Calendar.YEAR));
	}
	
	public static Date getLastDateOfYear(Date date){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return convertDateFromString("31/12/"+calendar.get(Calendar.YEAR));
	}
	
	public static boolean isDateOnSatSun(Date date){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
			return true;
		}
		return false;
	}
	
	public static int getDayOfWeek(Date date){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}
	
	public static int getWeekOfMonth(Date date){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(Calendar.WEEK_OF_MONTH);
	}
	
	/**
	 * 
	 * @param date
	 * @param calendarDayofweek - example Calendar.WEDNESDAY
	 * @return
	 */
	public static Date getNextDateOfSpecificDay(Date date, int calendarDayOfweek){
		Date newDate = addDaysFromDate(date, 1);
		while(true){
			if(getDayOfWeek(newDate) == calendarDayOfweek){
				break;
			}else{
				newDate = addDaysFromDate(newDate, 1);
			}
		}
		return newDate;
	}
	
	/**
	 * Get the specific day within same week
	 * @param date
	 * @param calendarDayOfWeek - example Calendar.WEDNESDAY
	 * @return
	 */
	public static Date getTheDateOnSpecificDayWithinSameWeek(Date date, int calendarDayOfWeek){
		if(getDayOfWeek(date)!=calendarDayOfWeek){
			return addDaysFromDate(date, calendarDayOfWeek - getDayOfWeek(date)); 
		}else{
			return date;
		}
	}
}
