package com.paySystem.ic.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 身份证工具，可以生成一系列身份证号码，和查询一个身份证归属地、出生日期、性别等
 * 
 * @version 2012-2-28 上午09:27:38
 */
public class IDUtil {

	private List<String> idList = new ArrayList<String>();
	private static Properties address = new Properties();

	/** 将前6位数字对应的归属地初始化 */
	static {
		try {
			address.load(IDUtil.class.getClassLoader().getResourceAsStream(
					"address.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** 生成身份证 birthday的格式是yyyyMMdd */
	public List<String> createID(String birthday, int num, String sixheardernum) {
		String str = sixheardernum + birthday;
		for (int i = 0; i < 9999; i++) {
			int lastfournum = 0;
			String str2 = "";
			lastfournum = lastfournum + i;
			int a = (lastfournum + "").length();
			switch (a) {
			case 3:
				str2 = str + "0" + lastfournum;
				break;
			case 2:
				str2 = str + "00" + lastfournum;
				break;
			case 1:
				str2 = str + "000" + lastfournum;
				break;
			default:
				str2 = str + lastfournum;
				break;
			}
			if (checkID(str2)) {
				idList.add(str2);
			}
		}
		return idList.subList(0, num);
	}

	/** 判断身份证 */
	public boolean checkID(String ID) {
		String checkedValue = ID;
		checkedValue = checkedValue.trim();
		if (checkedValue.length() != 15 && checkedValue.length() != 18)
			return false;
		String dateValue;
		if (checkedValue.length() == 15)
			dateValue = "19" + checkedValue.substring(6, 12);
		else
			dateValue = checkedValue.substring(6, 14);
		if (!checkDate(dateValue))
			return false;
		if (checkedValue.length() == 18)
			return checkPersonID(checkedValue);
		return true;
	}

	/** 日期校验 */
	public boolean checkDate(String sDate) {
		String checkedDate = sDate;
		int year, month, day;
		// 日期为空 长度不等于8或14 返回错误
		int maxDay[] = new int[] { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31,
				30, 31 };
		// if(checkedDate == null ) return false;
		checkedDate = checkedDate.trim();
		if (checkedDate.length() != 8) {
			return false;
		}
		year = Integer.parseInt(checkedDate.substring(0, 4).trim());
		month = Integer.parseInt(checkedDate.substring(4, 6).trim());
		day = Integer.parseInt(checkedDate.substring(6, 8).trim());

		// 日期中1至4位 年小于1900 返回错误
		if (year < 1900) {
			return false;
		}
		// 日期中5至6位 月在1至12区间之外 返回错误
		if (month < 1 || month > 12) {
			return false;
		}
		// 日期中7至8位 日在1至maxDay[month]区间之外 返回错误
		if (day > maxDay[month] || day == 0) {
			return false;
		}
		// 非闰年2月份日期大于29
		if (day == 29 && month == 2 && (year % 4 != 0 || year % 100 == 0)
				&& (year % 4 != 0 || year % 400 != 0)) {
			return false;
		}
		return true;
	}

	/** 校验身份证最后一位是否正确 */
	public boolean checkPersonID(String personID) {
		String strJiaoYan[] = new String[] { "1", "0", "X", "9", "8", "7", "6",
				"5", "4", "3", "2" };
		int intQuan[] = new int[] { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5,
				8, 4, 2, 1 };
		int intTemp = 0;
		for (int i = 0; i < personID.length() - 1; i++) {
			intTemp += Integer.parseInt(personID.substring(i, i + 1))
					* intQuan[i];
		}
		intTemp %= 11;
		return personID.substring(personID.length() - 1).equals(
				strJiaoYan[intTemp]);
	}

	/** 获取身份证归属地 */
	public String getAddress(String IDStr) {
		String addressStr = IDStr.substring(0, 6);
		if (addressStr.charAt(4) != '0'
				|| (addressStr.charAt(4) == '0' && addressStr.charAt(3) != '1')) {
			return address.getProperty(addressStr.substring(0, 2) + "0000")
					+ address.getProperty(addressStr.substring(0, 4) + "00")
					+ address.getProperty(addressStr);
		} else {
			return address.getProperty(addressStr.substring(0, 2) + "0000")
					+ address.getProperty(addressStr);
		}
	}

	/** 获取身份证出生日期 */
	public String getBirthday(String IDStr) {
		String str = IDStr.substring(6, 14);
		return str.substring(0, 4) + "年" + str.substring(4, 6) + "月"
				+ str.substring(6, 8) + "日";
	}

	/** 获取身份证性别 */
	public String getSex(String IDStr) {
		int temp = Integer.parseInt(IDStr.substring(IDStr.length() - 1, IDStr
				.length()));
		return temp % 2 == 1 ? "男" : "女";
	}

	public static void main(String[] args) {
		IDUtil cID = new IDUtil();
		System.out.println("归属地：" + cID.getAddress("350521198701210517"));
		System.out.println("生日：" + cID.getBirthday("350521198701210517"));
		System.out.println("性别：" + cID.getSex("350521198701210517"));
	}

}