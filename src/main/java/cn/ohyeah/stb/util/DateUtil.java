package cn.ohyeah.stb.util;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * ʱ�䴦������
 * @author maqian
 * @version 1.0
 */
public class DateUtil {
	
	/**
	 * ��ȡ��8����ʱ������
	 */
	public static TimeZone getDefaultTimeZone() {
		return TimeZone.getTimeZone("GMT+08:00");
	}
	
	/**
	 * ��ȡʱ��Ϊ��8����Calendar����
	 */
	public static Calendar getDefaultCalendar() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(getDefaultTimeZone());
		return calendar;
	}
	
	/**
	 * ��ʽ��ʱ���ַ�����"yyyy/MM/dd HH:mm:ss"
	 * @param time
	 */
	public static String formatTimeStr(Date time) {
		Calendar calendar = getDefaultCalendar();
		calendar.setTime(time);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		
		String mstr = (month >= 10)?Integer.toString(month+1):("0"+month);
		String dstr = (day >= 10)?Integer.toString(day):("0"+day);
		String hstr = (hour >= 10)?Integer.toString(hour):("0"+hour);
		String minstr = (minute >= 10)?Integer.toString(minute):("0"+minute);
		String sstr = (second >= 10)?Integer.toString(second):("0"+second);
		return year+"/"+mstr+"/"+dstr+" "+hstr+":"+minstr+":"+sstr;
	}
	
	/**
	 * �ж��Ƿ�ͬһ��
	 * @param t1
	 * @param t2
	 */
	public static boolean isSameYear(java.util.Date t1, java.util.Date t2) {
		Calendar c = getDefaultCalendar();
		c.setTime(t1);
		int year = c.get(Calendar.YEAR);
		c.setTime(t2);
		if (c.get(Calendar.YEAR) == year) {
			return true;
		}
		return false;
	}
	
	/**
	 * �ж��Ƿ�ͬһ��
	 * @param t1
	 * @param t2
	 */
	public static boolean isSameMonth(java.util.Date t1, java.util.Date t2) {
		Calendar c = getDefaultCalendar();
		c.setTime(t1);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		c.setTime(t2);
		if (c.get(Calendar.YEAR) == year
				&& c.get(Calendar.MONTH) == month) {
			return true;
		}
		return false;
	}
	
	/**
	 * �ж��Ƿ�ͬһ��
	 * @param t1
	 * @param t2
	 */
	public static boolean isSameDay(java.util.Date t1, java.util.Date t2) {
		Calendar c = getDefaultCalendar();
		c.setTime(t1);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		c.setTime(t2);
		if (c.get(Calendar.YEAR) == year
				&& c.get(Calendar.MONTH) == month
				&& c.get(Calendar.DAY_OF_MONTH) == day) {
			return true;
		}
		return false;
	}
	
	/**
	 * ���ʱ���е����
	 * @param t
	 */
	public static int getYear(java.util.Date t) {
		Calendar c = getDefaultCalendar();
		c.setTime(t);
		return c.get(Calendar.YEAR);
	}
	
	/**
	 * ���ʱ���е��·�(��1��ʼ����)
	 * @param t
	 */
	public static int getMonth(java.util.Date t) {
		Calendar c = getDefaultCalendar();
		c.setTime(t);
		return c.get(Calendar.MONTH)+1;
	}
	
	/**
	 * ���ʱ���е�����(��1��ʼ����)
	 * @param t
	 */
	public static int getDay(java.util.Date t) {
		Calendar c = getDefaultCalendar();
		c.setTime(t);
		return c.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * ʱ���n��
	 * @param c
	 * @param n
	 */
	public static Calendar addYear(Calendar c, int n) {
		c.set(Calendar.YEAR, c.get(Calendar.YEAR)+n);
		return c;
	} 
	
	
	public static java.util.Date addYear(java.util.Date date, int n) {
		Calendar c = getDefaultCalendar();
		c.setTime(date);
		return addYear(c, n).getTime();
	}
	
	/**
	 * ʱ���n����
	 * @param c
	 * @param n
	 */
	public static Calendar addMonth(Calendar c, int n) {
		int month = c.get(Calendar.MONTH);
		int incY = (month+n)/12;
		int m = (month+n)%12;
		c.set(Calendar.YEAR, c.get(Calendar.YEAR)+incY);
		c.set(Calendar.MONTH, m);
		return c;
	}
	
	public static java.util.Date addMonth(java.util.Date date, int n) {
		Calendar c = getDefaultCalendar();
		c.setTime(date);
		return addMonth(c, n).getTime();
	}
	
	public static java.util.Date createTime(int year, int month, int day) {
		Calendar c = getDefaultCalendar();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month-1);
		c.set(Calendar.DAY_OF_MONTH, day);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	public static java.util.Date createTime(int year, int month, int day, int hour, int minute, int second) {
		Calendar c = getDefaultCalendar();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month-1);
		c.set(Calendar.DAY_OF_MONTH, day);
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, minute);
		c.set(Calendar.SECOND, second);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	/**
	 * �ж�date1�Ƿ���date2֮ǰ
	 * @param date1
	 * @param date2
	 */
	public static boolean before(java.util.Date date1, java.util.Date date2) {
		return date1.getTime()<date2.getTime();
	}
	
	/**
	 * �ж�date1�Ƿ���date2֮��
	 * @param date1
	 * @param date2
	 */
	public static boolean after(java.util.Date date1, java.util.Date date2) {
		return date1.getTime()>date2.getTime();
	}
	
	/**
	 * �ж�date�Ƿ���date1��date2֮��
	 * @param date
	 * @param date1
	 * @param date2
	 */
	public static boolean between(java.util.Date date, java.util.Date date1, java.util.Date date2) {
		return date.getTime()>=date1.getTime()&&date.getTime()<=date2.getTime();
	}
	
	/**
	 * ����ʱ�䵽�³�
	 * @param c
	 */
	public static Calendar setBeginningOfMonth(Calendar c) {
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c;
	}
	
	/**
	 * ����ʱ�䵽��ĩ
	 * @param c
	 */
	public static Calendar setEndOfMonth(Calendar c) {
		addMonth(c, 1);
		setBeginningOfMonth(c);
		long millis = c.getTime().getTime();
		c.setTime(new java.util.Date(millis-1));
		return c;
	}
	
	/**
	 * ����³���ʱ��
	 * @param t
	 */
	public static java.util.Date getMonthStartTime(java.util.Date t) {
		Calendar c = getDefaultCalendar();
		c.setTime(t);
		setBeginningOfMonth(c);
		return c.getTime();
	}
	
	/**
	 * �����ĩ��ʱ��
	 * @param t
	 */
	public static java.util.Date getMonthEndTime(java.util.Date t) {
		Calendar c = getDefaultCalendar();
		c.setTime(t);
		setEndOfMonth(c);
		return c.getTime();
	}
	
	/**
	 * ����¸����³���ʱ��
	 * @param t
	 */
	public static java.util.Date getNextMonthStartTime(java.util.Date t) {
		Calendar c = getDefaultCalendar();
		c.setTime(t);
		addMonth(c, 1);
		setBeginningOfMonth(c);
		return c.getTime();
	}
}
