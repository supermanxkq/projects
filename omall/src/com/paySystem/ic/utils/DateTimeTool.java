package com.paySystem.ic.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * @ProjectName:MCIU_DS
 * @ClassName:DateTimeTool
 * @Description:封装的时间转换和获取的方法
 * @date: 2014-7-17
 * @author: 王楠
 * @version: V1.0
 */
public class DateTimeTool {

	
	
	/**
	*@Title:getNowDate
	*@Description:获取现在时间
	*@Params:@return
	*@Return:Date
	*@author:王楠
	*@Date:2014-7-17下午05:50:09
	*/
	public static Date getNowDate() {
	   Date currentTime = new Date();
	   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   String dateString = formatter.format(currentTime);
	   ParsePosition pos = new ParsePosition(8);
	   Date currentTime_2 = formatter.parse(dateString, pos);
	   return currentTime_2;
	   
	}
	
	/**
	*@Title:getNowShortDate
	*@Description:获取现在时间
	*@Params:@param n
	*@Params:@param date 日期
	*@Params:@return 返回短时间格式   yyyy-MM-dd
	*@Return:Date
	*@author:王楠
	*@Date:2014-7-17下午05:49:29
	*/
	public static Date getNowShortDate(int n,Date date){
		
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");  
		Date day = nDaysAgo(n,date);
		
		ParsePosition pos = new ParsePosition(0);
		//Date date = format1.parse(str,pos);
		return date;
		
	}

	/**
	*@Title:dateFormat
	*@Description:String类型按格式转化成Date类型
	*@Params:@param pattern
	*@Params:@param date 日期
	*@Params:@return
	*@Return:Date
	*@author:王楠
	*@Date:2014-7-17下午05:50:47
	*/
	public static Date dateFormat(String pattern, String date) {
		if(StringUtils.isBlank(date))
			return null;
		try {
			if (pattern == null || pattern.equals("")) {
				return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
			} else {
				return new SimpleDateFormat(pattern).parse(date);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}

	/**
	*@Title:dateFormat
	*@Description:Date类型按格式转化成String类型
	*@Params:@param pattern
	*@Params:@param date 日期
	*@Params:@return
	*@Return:String
	*@author:王楠
	*@Date:2014-7-17下午05:51:10
	*/
	public static String dateFormat(String pattern, Date date) {
		if(date==null){
			return "";
		}
		if (pattern == null || pattern.equals("")) {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		} else {
			return new SimpleDateFormat(pattern).format(date);
		}
	}

	/**
	*@Title:getDateByMonthFirst
	*@Description:获取本月的第一天
	*@Params:@param date
	*@Params:@return
	*@Return:Date
	*@author:王楠
	*@Date:2014-7-17下午05:51:28
	*/
	public static Date getDateByMonthFirst(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	/**
	*@Title:getDateByMonthLast
	*@Description:获取本月最后一天
	*@Params:@param date
	*@Params:@return
	*@Return:Date
	*@author:王楠
	*@Date:2014-7-17下午05:51:50
	*/
	public static Date getDateByMonthLast(Date date){
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));   
		return calendar.getTime();
	}
	
	/**
	*@Title:getDateByToday
	*@Description:获取当前日期
	*@Params:@return
	*@Return:String
	*@author:王楠
	*@Date:2014-7-17下午05:52:04
	*/
	public static String getDateByToday() {
		return DateTimeTool.dateFormat("yyyy-MM-dd", new Date());
	}
	
	

	/**
	*@Title:queryStartDate
	*@Description:查询的起始日期
	*@Params:@param date
	*@Params:@return
	*@Return:String
	*@author:王楠
	*@Date:2014-7-17下午05:52:26
	*/
	public static String queryStartDate(String date) {
		StringBuffer sb = new StringBuffer();
		return sb.append(date).append(" ").append("00:00:00").toString();
	}

	/**
	*@Title:queryEndDate
	*@Description:查询的结束日期
	*@Params:@param date
	*@Params:@return
	*@Return:String
	*@author:王楠
	*@Date:2014-7-17下午05:52:44
	*/
	public static String queryEndDate(String date) {
		StringBuffer sb = new StringBuffer();
		return sb.append(date).append(" ").append("23:59:59").toString();
	}

	/**
	*@Title:doDateFormatStringBeginningOfyyyymmdd000000
	*@Description:日期类型转换 Date ->yyyy-MM-01 00:00:00
	*@Params:@param date
	*@Params:@return
	*@Return:Date
	*@author:王楠
	*@Date:2014-7-17下午05:53:02
	*/
	public static Date doDateFormatStringBeginningOfyyyymmdd000000(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		Date datetime = null;
		try {
			if (date == null) {
				datetime = null;
			} else {
				datetime = format.parse(format.format(date));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return datetime;
	}
	
	 /**
	 *@Title:getDiffDay
	 *@Description:获取日期相差天数
	 *@param:@param beginDate  字符串类型开始日期
	 *@param:@param endDate    字符串类型结束日期
	 *@param:@return
	 *@return:Long             日期相差天数
	 *@author:谢
	 *@thorws:
	 */
	public static Long getDiffDay(String beginDate,String endDate) {
	 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	 Long checkday=0l; 
	//开始结束相差天数
	 try {
	checkday = (formatter.parse(endDate).getTime()- formatter.parse(beginDate).getTime())/(1000*24*60*60);
	} catch (ParseException e) {
	
	e.printStackTrace();
	checkday=null;
	}
	 return checkday;
	 }  
	 
	 
	 /**
	 *@Title:getDiffDay
	 *@Description:获取日期相差天数
	 *@param:@param beginDate Date类型开始日期
	 *@param:@param endDate   Date类型结束日期
	 *@param:@return
	 *@return:Long            相差天数
	 *@author: 谢
	 *@thorws:
	 */
	public static Long getDiffDay(Date beginDate,Date endDate) {
	 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 String strBeginDate = format.format(beginDate);
	 
	 String strEndDate = format.format(endDate);
	 return getDiffDay(strBeginDate,strEndDate);
	 }  

	 /**
	 *@Title:getDiffMonth
	 *@Description:日期相差月数
	 *@param:@param beginDate  开始日期
	 *@param:@param endDate    结束日期
	 *@param:@return
	 *@return:int
	 *@author: 谢
	 *@thorws:
	 */
	public static int getDiffMonth(String beginDate,String endDate) {
	 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	 Date dbeginDate = null;
	 Date dendDate = null;
	try {
	dbeginDate = formatter.parse(beginDate);
	dendDate = formatter.parse(endDate);
	} catch (ParseException e) {
	 System.out.println("String ————> Date 转换失败!");
	 e.printStackTrace();
	}
	 return getDiffMonth(dbeginDate,dendDate);
	 } 
	
	
	 /**
	 *@Title:getDiffMonth
	 *@Description:获取日期相差月数
	 *@param:@param beginDate  开始日期
	 *@param:@param endDate    结束日期
	 *@param:@return
	 *@return:int    返回天数
	 *@author: 谢
	 *@thorws:
	 */
	public static int getDiffMonth(Date beginDate,Date endDate) {
	 Calendar calbegin = Calendar.getInstance();
	 Calendar calend = Calendar.getInstance();
	 calbegin.setTime(beginDate);
	 calend.setTime(endDate);
	 int m_begin = calbegin.get(Calendar.MONTH)+1; //获得合同开始日期月份
	 int m_end = calend.get(Calendar.MONTH)+1;
	  //获得合同结束日期月份
	 int checkmonth = m_end-m_begin+(calend.get(Calendar.YEAR)-calbegin.get(Calendar.YEAR))*12;
	//获得合同结束日期于开始的相差月份
	 return checkmonth;
	 }
	
	/**
	*@Title:doDateFormatStringBeginningOfyyyymmdd235959
	*@Description:日期类型转换 Date ->yyyy-MM-yy 00:00:00
	*@Params:@param date
	*@Params:@return
	*@Return:String
	*@author:王楠
	*@Date:2014-7-17下午05:53:29
	*/
	public static String doDateFormatStringBeginningOfyyyymmdd235959(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		String datetime = null;
		try {
			if (date == null) {
				datetime = "";
			} else {
				datetime = format.format(date);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return datetime;
	}
	
	/**
	*@Title:timecheck
	*@Description:是否在时间段内
	*@Params:@param status
	*@Params:@param end
	*@Params:@return
	*@Return:boolean
	*@author:王楠
	*@Date:2014-7-17下午05:53:45
	*/
	public static boolean timecheck(Integer status, Integer end) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		if (hour >= status && hour < end) {
			return true;
		}
		return false;
	}

	/**
	*@Title:ticketOrderTime
	*@Description:提前10天
	*@Params:@param now
	*@Params:@return
	*@Return:String
	*@author:王楠
	*@Date:2014-7-17下午05:53:59
	*/
	public static String ticketOrderTime(Date now) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long l = now.getTime() + 9 * 24 * 60 * 60 * 1000;
		now.setTime(l);
		return sdf.format(now);

	}

	/**
	*@Title:doDateFormatString
	*@Description: 日期类型转换 Date ->String
	*@Params:@param date
	*@Params:@return
	*@Return:String
	*@author:王楠
	*@Date:2014-7-17下午05:54:16
	*/
	public static String doDateFormatString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String datetime = null;
		try {
			if (date == null) {
				datetime = "";
			} else {
				datetime = format.format(date);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return datetime;
	}

	/**
	*@Title:nMinuteAgo
	*@Description:N分钟之前
	*@Params:@param n
	*@Params:@param date
	*@Params:@return
	*@Return:Date
	*@author:王楠
	*@Date:2014-7-17下午05:54:29
	*/
	public static Date nMinuteAgo(Integer n, Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + n);
		return cal.getTime();
	}
	
    /**
    *@Title:nDaysAfter
    *@Description:N天之后
    *@Params:@param n
    *@Params:@param date
    *@Params:@return
    *@Return:Date
    *@author:王楠
    *@Date:2014-7-17下午05:54:53
    */
    public static Date nDaysAfter(Integer n, Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + n);
		return cal.getTime();
	}

    /**
    *@Title:nDaysAgo
    *@Description:N天之前
    *@Params:@param n
    *@Params:@param date
    *@Params:@return
    *@Return:Date
    *@author:王楠
    *@Date:2014-7-17下午05:55:06
    */
    public static Date nDaysAgo(Integer n, Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - n);
		return cal.getTime();
	}
    
    /**
    *@Title:nMonthsAgo
    *@Description:N月之前
    *@Params:@param n 
    *@Params:@param date
    *@Params:@return
    *@Return:Date
    *@author:王楠
    *@Date:2014-7-17上午11:08:17
    */
    public static Date nMonthsAgo(Integer n, Date date) {
    	Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - n);
		return cal.getTime();
    }
	
	/**
	*@Title:nMonthsAfter
	*@Description:N月之后
	*@Params:@param n
	*@Params:@param date
	*@Params:@return
	*@Return:Date
	*@author:王楠
	*@Date:2014-7-17下午05:55:25
	*/
	public static Date nMonthsAfter(Integer n, Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + n);
		return cal.getTime();
	}

    /**
    *@Title:nYearsAgo
    *@Description:N年之前
    *@Params:@param n
    *@Params:@param date
    *@Params:@return
    *@Return:Date
    *@author:王楠
    *@Date:2014-7-17下午05:55:36
    */
    public static Date nYearsAgo(Integer n, Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + n);
		return cal.getTime();
	}
	
	/**
	*@Title:isSameDay
	*@Description:是否同一天
	*@Params:@param pattern
	*@Params:@param date1 
	*@Params:@param date2
	*@Params:@return
	*@Return:Boolean
	*@author:王楠
	*@Date:2014-7-17下午05:55:51
	*/
	public static Boolean isSameDay(String pattern,Date date1,Date date2){
		SimpleDateFormat format = null;
		if (StringUtils.isNotBlank(pattern)) {
			format = new SimpleDateFormat(pattern);
		}else{
			format = new SimpleDateFormat("yyyy-MM-dd");
		}
		try {
			date1 = format.parse(format.format(date1));
			date2 = format.parse(format.format(date2));
			if(date1.compareTo(date2)==0){
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	*@Title:getYear
	*@Description:获取年
	*@Params:@param date
	*@Params:@return
	*@Return:int
	*@author:王楠
	*@Date:2014-7-17下午05:56:17
	*/
	public static int getYear(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(Calendar.YEAR);
	}

	/**
	*@Title:getMonth
	*@Description:获取月
	*@Params:@param date
	*@Params:@return
	*@Return:int
	*@author:王楠
	*@Date:2014-7-17下午05:56:30
	*/
	public static int getMonth(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(Calendar.MONTH) + 1;
	}

	/**
	*@Title:getDay
	*@Description:获取日
	*@Params:@param date
	*@Params:@return
	*@Return:int
	*@author:王楠
	*@Date:2014-7-17下午05:56:39
	*/
	public static int getDay(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(Calendar.DAY_OF_MONTH);
	}

	/**
	*@Title:getHour
	*@Description:获取时
	*@Params:@param date
	*@Params:@return
	*@Return:int
	*@author:王楠
	*@Date:2014-7-17下午05:56:51
	*/
	public static int getHour(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(Calendar.HOUR_OF_DAY);
	}

	/**
	*@Title:getMinute
	*@Description:获取分
	*@Params:@param date
	*@Params:@return
	*@Return:int
	*@author:王楠
	*@Date:2014-7-17下午05:57:03
	*/
	public static int getMinute(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(Calendar.MINUTE);
	}

	/**
	*@Title:getSecond
	*@Description:获取秒
	*@Params:@param date
	*@Params:@return
	*@Return:int
	*@author:王楠
	*@Date:2014-7-17下午05:57:12
	*/
	public static int getSecond(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(Calendar.SECOND);
	}
	
	public static void main(String[] args) {
/*		System.out.println(DateTimeTool.isSameDay("yyyy-MM", new Date(),DateTimeTool.nDaysAgo(5, new Date()) ));
		System.out.println(DateTimeTool.dateFormat("", DateTimeTool.nDaysAgo(0, new Date())));
		System.out.println(DateTimeTool.dateFormat("yyyy-MM-dd", DateTimeTool.nMonthsAfter(3,new Date())));
		boolean flag = false;
		List<Integer> intList = new ArrayList<Integer>();
		for (int i = 0 ;i<10 ;i++){
			intList.add(i);
		}
		for(int j=0;j<30;j++){
			if(intList.get(j).intValue()==12){
				flag = true;
				break;
			}
			else{
				
				System.out.println("运行次数："+j);
			}
		}
		System.out.print(flag);*/
	/*	boolean flag = Pattern.compile("^(([1-9]{1,2}[.]?)|(0.))(\\d{0,2})?$").matcher("99").matches();
		System.out.println(flag);*/
		
		/*String now = dateFormat("yyyyMMddhhmmssSSS",new Date());
		System.out.println(now);*/
		
		/*System.out.println(getNowShortDate()+"=======");*/
		/*System.out.println(getDiffDay(nDaysAgo(4, new Date()),new Date()));
		
		System.out.println(DateTimeTool.dateFormat("", DateTimeTool.nDaysAfter(3, new Date())));
		System.out.println(DateTimeTool.dateFormat("", (new Date())));
		System.out.println(DateTimeTool.getDateByMonthFirst(new Date()));
		System.out.println(DateTimeTool.getDateByMonthLast(new Date()));
		System.out.println(DateTimeTool.getDay(new Date()));
		System.out.println(new Date());*/
	}
}
