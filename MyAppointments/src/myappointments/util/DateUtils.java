/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package myappointments.util;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author leonardo
 */
public class DateUtils {
    
    public static final String HOUR_FMT = "HH:mm" ;
    public static final String SHORT_DATE_FMT = "MM/dd/yyyy" ;
    public static final String LONG_DATE_FMT = "MM/dd/yyyy HH:mm" ;
    
    private static Calendar calendar = Calendar.getInstance(Locale.US) ;
    
    public static int getDay(Date date) {
        calendar.clear() ;
        calendar.setTime(date) ;
        return calendar.get(Calendar.DAY_OF_MONTH) ;
    }
    
    public static int getMonth(Date date) {
        calendar.clear() ;
        calendar.setTime(date) ;        
        return calendar.get(Calendar.MONTH) + 1;
    }
    
    public static int getYear(Date date) {
        calendar.clear() ;
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR) ;
    }
    
    public static int getHour(Date date) {
        calendar.clear() ;
        calendar.setTime(date) ;
        return calendar.get(Calendar.HOUR_OF_DAY) ;
    }
    
    public static int getMinutes(Date date) {
        calendar.clear() ;
        calendar.setTime(date) ;        
        return calendar.get(Calendar.MINUTE) ;
    }

    public static int getCurrentYear() {
        return DateUtils.getYear(getCurrentDate()) ;
    }
    
    public static int getCurrentMonth() {
        return DateUtils.getMonth(getCurrentDate()) ;
    }    
    
    public static int getCurrentDay() {
        return DateUtils.getDay(getCurrentDate()) ;
    }        
    
    public static Date getCurrentDate() {
        return new Date(System.currentTimeMillis()) ;
    }

    public static Time getTime(Date date) {                
        return getTimeFromHourFmt(DateUtils.toString(date, HOUR_FMT)) ;
    }
    
    public static Time getTimeFromHourFmt(String hhmm) {
        return Time.valueOf(hhmm + ":00") ;        
    }
       
    public static Date fromString(String s, String fmt) throws ParseException {
        Date date = null ;
        
        try { date = new SimpleDateFormat(fmt).parse(s) ; }
        catch (Exception e) { }
        
        return date ;
    }
    
    public static String toString(Date date, String fmt) {
        return new SimpleDateFormat(fmt).format(date ) ;
    }
       
    public static boolean isToday(Date date) {
        int day = DateUtils.getDay(date) ;
        int month = DateUtils.getMonth(date) ;
        int year  = DateUtils.getYear(date) ;
        
        return (DateUtils.getCurrentDay() == day &&
               DateUtils.getCurrentMonth() == month &&
               DateUtils.getCurrentYear() == year) ;
    }
    
    public static Date newDate
            (int day, int month, int year, int hour, int minutes) {
        calendar.clear() ;
        calendar.set(year, month - 1, day, hour, minutes) ;        
        return calendar.getTime() ;
    }
}
