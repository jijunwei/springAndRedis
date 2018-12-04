package com.example.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public enum DateType {
		YEAR, MONTH, DAY, HH, MI, SS, YYYY_MM_DD, YYYYMMDD
	}

	public static java.sql.Date maxDate() {
		return java.sql.Date.valueOf("9999-09-09");
	}

	/**
	 * 得到当前应用服务器的系统日期
	 *
	 * @return 系统日期
	 */
	public static String sysTimestamp() {
		return new Timestamp(System.currentTimeMillis()).toString();
	}

	/**
	 * 得到当前应用服务器的系统日期
	 *
	 * @return 系统日期
	 */
	public static java.sql.Date sysDate() {
		return new java.sql.Date(System.currentTimeMillis());
	}

	/**
	 * 得到当前应用服务器的系统时间
	 *
	 * @return 系统日期
	 */
	public static java.sql.Time sysTime() {
		return new java.sql.Time(System.currentTimeMillis());
	}

	/**
	 * 得到当前应用服务器的系统日期<br>
	 * 字符串类型
	 *
	 * @return 系统日期
	 */
	public static String sysDate4Str() {
		return new java.sql.Date(System.currentTimeMillis()).toString();
	}

	/**
	 * 得到d1与d2之间相差数值
	 * <p>
	 * 数值可以为年份，DateType.YEAR<br>
	 * 数值可以为月份，DateType.MONTH<br>
	 * 数值可以为天数，DateType.DAY<br>
	 * 数值可以为小时，DateType.HH<br>
	 * 数值可以为分钟，DateType.MI<br>
	 * 数值可以为秒，DateType.SS
	 *
	 * @param d1
	 *            日期1(较大的日期)
	 * @param d2
	 *            日期2(较小的日期)
	 * @param dataType
	 *            数值类型
	 * @return 相差数值 <br>
	 *         d1早于d2时，返回-1。<br>
	 *         dateType类型不正确时，返回-2。
	 */
	public static int dateBetween(Date d1, Date d2, DateType dateType) {

		if (!d2.before(d1) && d2==d1) {
			return -1;
		}
		Calendar c1 = Calendar.getInstance();
		c1.setTime(d1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(d2);

		if (DateType.YEAR.equals(dateType)) {
			return c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR);
		}
		if (DateType.MONTH.equals(dateType)) {
			return (c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR)) * 12
					+ c1.get(Calendar.MONTH) - c2.get(Calendar.MONTH);
		}
		if (DateType.DAY.equals(dateType)) {
			return (int) ((c1.getTimeInMillis() - c2.getTimeInMillis()) / 86400000L);
		}
		if (DateType.HH.equals(dateType)) {
			return (int) ((c1.getTimeInMillis() - c2.getTimeInMillis()) / 3600000L);
		}
		if (DateType.MI.equals(dateType)) {
			return (int) ((c1.getTimeInMillis() - c2.getTimeInMillis()) / 60000L);
		}
		if (DateType.SS.equals(dateType)) {
			return (int) ((c1.getTimeInMillis() - c2.getTimeInMillis()) / 1000L);
		}
		return -2;
	}

	/**
	 * 得到date与当前系统时间的(毫秒)差值
	 *
	 * @param d1
	 *            日期1(较大的日期)
	 * @param d2
	 *            日期2(较小的日期)
	 * @return 相差数值 <br>
	 *          d1早于d2时，返回-1。<br>
	 */
	public static String dateCount(Date d1, Date d2){

		if (!d2.before(d1) && d2==d1) {
			return "-1";
		}
		return "[用时:"+(d1.getTime() - d2.getTime())+"msec] ";
	}

	/**
	 * 将精确到秒的字符串转换为日期类型
	 * <p>
	 * 字符串格式需要为yyyy-MM-dd hh:mm:ss
	 *
	 * @param date
	 *            将精确到秒的字符串
	 * @return Timestamp对象
	 * @throws RuntimeException
	 *             日期类型转换错误
	 */
	public static Date string2Timestamp(String date) {
		SimpleDateFormat clsFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			clsFormat.parse(date);
			return new Timestamp(clsFormat.parse(date).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将精确到秒的字符串转换为日期类型
	 * <p>
	 * 字符串格式需要为yyyy-MM-dd hh:mm:ss
	 *
	 * @param date
	 *            将精确到秒的字符串
	 * @return Timestamp对象
	 * @throws RuntimeException
	 *             日期类型转换错误
	 */
	public static Timestamp str2Timestamp(String date, String pattern) {
		SimpleDateFormat clsFormat = new SimpleDateFormat(pattern);
		try {
			clsFormat.parse(date);
			return new Timestamp(clsFormat.parse(date).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将精确到天的字符串转换为日期类型
	 * <p>
	 * 字符串格式需要为yyyy-MM-dd
	 *
	 * @param date
	 *            将精确到天的字符串
	 * @return java.sql.Date对象
	 * @throws ParseException
	 * @throws RuntimeException
	 *             日期类型转换错误
	 */
	public static java.sql.Date string2Date(String date) {
		SimpleDateFormat clsFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return new java.sql.Date(clsFormat.parse(date).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static java.sql.Date string2Date4fm(String date,String formatstr) {
		SimpleDateFormat clsFormat = new SimpleDateFormat(formatstr);
		try {
			return new java.sql.Date(clsFormat.parse(date).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将字符型时间转换为Time型
	 * <p>
	 * 字符串格式需要为HH:mm:ss
	 *
	 * @param date
	 *            将精确到天的字符串
	 * @return Timestamp对象
	 * @throws ParseException
	 * @throws RuntimeException
	 *             日期类型转换错误
	 */
	public static java.sql.Time string2Time(String time) {
		SimpleDateFormat clsFormat = new SimpleDateFormat("HH:mm:ss");
		try {
			return new java.sql.Time(clsFormat.parse(time).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	// 将精确到天的字符串对象转换为日期类型
	public static java.sql.Date object2Date(Object date) {
		return string2Date((String) date);
	}

	/**
	 * 将字符串转为指定格式的日期
	 * <p>
	 * 日期字符串与字符串格式需要匹配
	 *
	 * @param date
	 *            日期字符串
	 * @param frm
	 *            字符串格式
	 * @return Timestamp对象
	 * @throws ParseException
	 * @throws RuntimeException
	 *             日期类型转换错误
	 */
	public static java.sql.Date string2Date(String date, String frm) {
		SimpleDateFormat clsFormat = new SimpleDateFormat(frm);
		try {
			clsFormat.parse(date);
			return new java.sql.Date(clsFormat.parse(date).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将日期类型转换为精确到天的字符串
	 * <p>
	 * 字符串格式为yyyy-MM-dd
	 *
	 * @param date
	 *            日期类型
	 * @return String 日期
	 *
	 */
	public static String date2String(Date date) {
		SimpleDateFormat clsFormat = new SimpleDateFormat("yyyy-MM-dd");
		return clsFormat.format(date);
	}

	/**
	 * 将日期类型转换为指定格式的字符串
	 *
	 *
	 * @param date
	 *            日期类型
	 * @param frm
	 *            需要转换的格式,具体格式参加java说明
	 * @return String 日期
	 *
	 */
	public static String date2String(Date date, String frm) {
		SimpleDateFormat clsFormat = new SimpleDateFormat(frm);
		return clsFormat.format(date);
	}

	/**
	 * 日期加运算
	 *
	 * @param date
	 *            需要加的日期
	 * @param dateType
	 *            需要加的类型<br>
	 *            数值可以为年份，DateType.YEAR<br>
	 *            数值可以为月份，DateType.MONTH<br>
	 *            数值可以为天数，DateType.DAY<br>
	 *            数值可以为小时，DateType.HH<br>
	 *            数值可以为分钟，DateType.MI<br>
	 *            数值可以为秒，DateType.SS
	 * @param num
	 *            需要加的数值
	 * @return 加完之后的日期
	 */
	public static Date addDate(Date date, DateType dateType, int num) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date);
		if (DateType.YEAR.equals(dateType)) {
			c1.add(Calendar.YEAR, num);
			return c1.getTime();
		}
		if (DateType.MONTH.equals(dateType)) {
			c1.add(Calendar.MONTH, num);
			return c1.getTime();
		}
		if (DateType.DAY.equals(dateType)) {
			c1.add(Calendar.DAY_OF_MONTH, num);
			return c1.getTime();
		}
		if (DateType.HH.equals(dateType)) {
			c1.add(Calendar.HOUR_OF_DAY, num);
			return c1.getTime();
		}
		if (DateType.MI.equals(dateType)) {
			c1.add(Calendar.MINUTE, num);
			return c1.getTime();
		}
		if (DateType.SS.equals(dateType)) {
			c1.add(Calendar.SECOND, num);
			return c1.getTime();
		}
		return date;
	}

	/**
	 * 返回前一天。
	 *
	 * @param date
	 * @return
	 */
	public static java.sql.Date theDayBefore(Date date) {
		Calendar cdate = Calendar.getInstance();
		cdate.setTime(date);
		cdate.add(Calendar.DATE, -1);
		java.sql.Date ldate = new java.sql.Date(cdate.getTime().getTime());
		return ldate;
	}

	/**
	 * 得到当前月的第一天
	 *
	 * @return
	 */
	public static java.sql.Date getNowMonth1() {
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 1);
		java.sql.Date sysDate = new java.sql.Date(date.getTime().getTime());
		return sysDate;

	}

	/**
	 * 得到指定日期的在这个月的第一日. 00:00:00
	 *
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		if (date != null)
			cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 得到上月1日
	 *
	 * @return
	 */
	public static java.sql.Date getlastMonth() {
		Calendar date = Calendar.getInstance();
		date.add(Calendar.MONTH, -1);
		date.set(Calendar.DAY_OF_MONTH, 1);
		java.sql.Date sysDate = new java.sql.Date(date.getTime().getTime());
		return sysDate;

	}

	/**
	 * 获得次月1日日期
	 *
	 * @param calcdate
	 * @return
	 * @throws ParseException
	 */
	public static String getMinDate(String calcdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		date.setTime(sdf.parse(calcdate));
		date.add(Calendar.MONTH, 1);// 设置月为下个月
		date.set(Calendar.DAY_OF_MONTH, date.getMinimum(Calendar.DAY_OF_MONTH));// 设置日为月第一天
		String min_date = sdf.format(date.getTime());
		return min_date;
	}

	/**
	 * 获得次月1日日期
	 *
	 * @param calcdate
	 * @return
	 * @throws ParseException
	 */
	public static java.sql.Date getMinDate(Date calcdate) {
		Calendar date = Calendar.getInstance();
		date.setTime(calcdate);
		date.add(Calendar.MONTH, 1);// 设置月为下个月
		date.set(Calendar.DAY_OF_MONTH, date.getMinimum(Calendar.DAY_OF_MONTH));// 设置日为月第一天
		java.sql.Date sysDate = new java.sql.Date(date.getTime().getTime());
		return sysDate;
	}

	/**
	 * 日期比较
	 * <p>
	 * 开始日期在结束日期之前返回-1<br>
	 * 开始日期等于结束日期返回0<br>
	 * 开始日期在结束日期之后返回1
	 *
	 * @param startDate
	 *            起始日期
	 * @param endDate
	 *            结束日期
	 * @return
	 */
	public static int compareDate(Date startDate, Date endDate) {

		// 去除掉时分秒的时间
		startDate = DateUtil.string2Date(startDate.toString());
		endDate = DateUtil.string2Date(endDate.toString());

		if (startDate.before(endDate)) {
			return -1;
		}
		if (startDate.equals(endDate)) {
			return 0;
		}
		if (startDate.after(endDate)) {
			return 1;
		}
		return -2;
	}

	/**
	 * 得到本年指定日期的相关月份的最后一日 00:00:00
	 *
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfMonthThisYear(Date date) {
		Calendar cal = Calendar.getInstance();
		if (date != null)
			cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH,
				cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 得到指定日期的当月最后一天
	 *
	 * @param date
	 *            指定日期
	 * @return 当月最后一天
	 */
	public static java.sql.Date getLastDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		if (date != null)
			cal.setTime(date);
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return new java.sql.Date(cal.getTime().getTime());
	}

	/**
	 * 得到前一个月的1号
	 *
	 * @param date
	 *            指定日期
	 * @return 前一个月的1号
	 */
	public static java.sql.Date getBeforeMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		if (date != null)
			cal.setTime(date);
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return new java.sql.Date(cal.getTime().getTime());
	}

	/**
	 * 得到日期中的“日”
	 *
	 * @param date
	 *            指定日期
	 * @return 日期中的“日”
	 */
	public static int getDayOfDate(Date date) {
		Calendar cal = Calendar.getInstance();
		if (date != null)
			cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}


	/**
	 * 得到日期中的“月”
	 *
	 * @param date
	 *            指定日期
	 * @return 日期中的“月”
	 */
	public static int getMonthOfDate(Date date) {
		Calendar cal = Calendar.getInstance();
		if (date != null)
			cal.setTime(date);
		return cal.get(Calendar.MONTH);
	}


	public boolean equals(Object obj) {
		System.out.println("hello");
		return false;
	}

	/**
	 * 得到指定日期的在这个月的第一日
	 *
	 * @param date
	 * @return
	 */
	public static java.sql.Date getFirstDayOfDateMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		if (date != null)
			cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		java.sql.Date ldate = new java.sql.Date(cal.getTime().getTime());
		return ldate;
	}

	/**
	 * 将java.sql.Timestamp类型日期，或java.util.Date类型转换为java.sql.Date类型
	 *
	 * @param obj
	 * @return
	 */
	public static java.sql.Date obj2SqlDate(Object obj) {
		if (obj == null) {
			throw new RuntimeException("入参为空");
		}
		if (obj instanceof Date) {
			return new java.sql.Date(((Date) obj).getTime());
		}
		throw new RuntimeException("入参不为日期类型");
	}

	/**
	 * 将java.sql.Date类型日期，或java.util.Date类型转换为java.sql.Timestamp类型
	 *
	 * @param obj
	 * @return
	 */
	public static Timestamp obj2Timestamp(Object obj) {
		if (obj instanceof Date) {
			return new Timestamp(((Date) obj).getTime());
		}
		throw new RuntimeException("入参不为日期类型");
	}

	/**
	 * 计算年龄
	 * <p>
	 * 当前系统日期减去生日，得到年龄
	 *
	 * @param birthday
	 *            生日
	 * @return 年龄
	 */
	public static double calcAge(Date birthday) {
		return DateUtil.calcAge(birthday,
				new java.sql.Date(System.currentTimeMillis()));

	}

	/**
	 * 计算年龄
	 * <p>
	 * 指定日期减去生日，得到年龄
	 *
	 * @param birthday
	 *            生日
	 * @param date
	 *            指定日期
	 * @return 年龄
	 */
	public static double calcAge(Date birthday, Date date) {
		Calendar _birthday = Calendar.getInstance();
		_birthday.setTime(birthday);
		Calendar _sysDate = Calendar.getInstance();
		_sysDate.setTimeInMillis(date.getTime());
		return _sysDate.get(Calendar.YEAR)
				- _birthday.get(Calendar.YEAR)
				+ (_sysDate.get(Calendar.MONTH) - _birthday.get(Calendar.MONTH))
				/ 12.00
				+ (_sysDate.get(Calendar.DAY_OF_MONTH) - _birthday
				.get(Calendar.DAY_OF_MONTH)) / 365.00;
	}


	/**
	 * 获取指定日期的下个月 一号--yyyy-MM-dd
	 * @param dateString
	 * @return
	 */
	public static Date  getFirstDayOfMonthChange(String dateString) {
		Calendar c = Calendar.getInstance();//获得一个日历的实例
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try{
			date = sdf.parse(dateString);//初始日期
		}catch(Exception e){
			e.printStackTrace();
		}
		c.setTime(date);
		c.set(Calendar.DATE,1);//设为当前月的1号
		String strDate = sdf.format(c.getTime());
		Date cDate = null;
		try {
			cDate = sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		java.sql.Date dd = new java.sql.Date(cDate.getTime());
		return dd;
	}

	/**
	 * 获取当月最后一天
	 *
	 */
	public static Date getLastDayOfMonth(String dateString){
		Calendar c = Calendar.getInstance();//获得一个日历的实例
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try{
			date = sdf.parse(dateString);//初始日期
		}catch(Exception e){
			e.printStackTrace();
		}
		c.setTime(date);
		c.set(Calendar.DATE,1);//设为当前月的1号
		c.roll(Calendar.DATE, -1);
		String strDate = sdf.format(c.getTime());
		Date cDate = null;
		try {
			cDate = sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		java.sql.Date dd = new java.sql.Date(cDate.getTime());
		return dd;
	}
	public static Date  getDayOfMonthChange(String dateString) {
		Calendar c = Calendar.getInstance();//获得一个日历的实例
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try{
			date = sdf.parse(dateString);//初始日期
		}catch(Exception e){
			e.printStackTrace();
		}
		c.setTime(date);
		//c.set(Calendar.DATE,1);//设为当前月的1号
		String strDate = sdf.format(c.getTime());
		Date cDate = null;
		try {
			cDate = sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		java.sql.Date dd = new java.sql.Date(cDate.getTime());
		return dd;
	}
	/**
	 *将指定格式的字符串转换为Date
	 * @param str 时间字符串
	 * @param pattern 时间格式
	 * @return 返回日期
	 *
	 */
	public static Date parseDateByPatternString(String str, String pattern) {
		DateFormat formatDate= new SimpleDateFormat(pattern);
		try {
			return formatDate.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 *返回入参期后的指定天数日期的信息
	 * @param 日期
	 * @return 格式：Fri Jul 15 00:00:00 CST 2016
	 *
	 */
	public static Date subDay(Date date, int day) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_YEAR, day);
		return c.getTime();
	}

	/**
	 * 根据身份证号返回年龄 (如果身份不合法则返回null)
	 * @param identity 身份证号
	 * @return age 年龄
	 */
	public static String getAgeByIdentity(String identity){
		String age = null;
		try{
			String year = identity.substring(6, 10);
			age =  Integer.parseInt((sysTimestamp().substring(0, 4))) - Integer.parseInt(year) + "";
		}catch(Exception e) {
			e.printStackTrace();
		}
		return age;
	}

	/**
	 * 判断两个时间相差多少
	 * @param d1 较大的时间
	 * @param d2
	 * @param type 天
	 * @return
	 * @throws ParseException
	 */
	public static int DateDiffer(Date d1,Date d2) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date1 = DateUtil.date2String(d1,"yyyy-MM-dd");
		String date2 = DateUtil.date2String(d2,"yyyy-MM-dd");
		int dateBetween = DateUtil.dateBetween(sdf.parse(date1), sdf.parse(date2), DateType.DAY);
		return dateBetween;

	}

	public static String getNowYMD(String pattern){
		Date date = new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(pattern);
//		SimpleDateFormat sdf=new SimpleDateFormat("HHmmssSSS");
		return sdf.format(date);
	}

	public static String getSubDate(String date){

		return date.replaceAll("-","").substring(0,8);
	}

	public static int getDaysOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	//主函数测试
	public static void main(String[] args) throws Exception {
		System.out.println();
	}
}
