package com.paySystem.ic.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import com.google.gson.Gson;
import com.paySystem.ic.web.dto.ReturnDTO;
import com.paySystem.ic.web.dto.system.UserSession;
import com.paySystem.ic.web.ui.OptionsInteger;
import com.paySystem.ic.web.ui.OptionsString;
import com.redpass.rsa.RSA;

public class Utils {

	private static final Log log = LogFactory.getLog(Utils.class);
	
	/**
	 * 
	*@Title:makeMD5
	*@Description:MD5加密算法，对密码进行加密
	*@Params:@param str
	*@Params:@return
	*@Return:String
	*@author:张国法
	*@Date:2014-12-11下午02:46:23
	 */
	public static String makeMD5(String str) {
	      MessageDigest messageDigest = null;

	      try {
	        messageDigest = MessageDigest.getInstance("MD5");

	        messageDigest.reset();

	        messageDigest.update(str.getBytes("UTF-8"));
	      } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	      } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	      }

	      byte[] byteArray = messageDigest.digest();

	      StringBuffer md5StrBuff = new StringBuffer();

	      for (int i = 0; i < byteArray.length; i++) {
	        if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
	           md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
	        else
	           md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
	      }

	      return md5StrBuff.toString();
	   }
    /**
	 * 使用I/O流输出 json格式的数据
	 * 
	 * @param response
	 * @param object
	 */
	public static String printInfo(Object object) {
		HttpServletResponse response = ServletActionContext.getResponse();
		Gson gson = new Gson();
		String result = gson.toJson(object);
		response.setContentType("text/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache"); // 取消浏览器缓存
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
			log.info("使用I/0输入出错" + e);
		}
		out.print(result);
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 异步输出字节流 对比上面的方法的区别在于头不一样。 用于解决ajaxfileupload在chrome下面的兼容性问题
	 * 
	 * @Title: printInfoWithoutType
	 *@Description: 异步输出字节流
	 *@param:@return
	 *@param:@throws Exception
	 *@return:String
	 *@author: Jacky
	 *@Thorws:
	 */
	public static String printInfoWithoutType(Object object) {
		HttpServletResponse response = ServletActionContext.getResponse();
		Gson gson = new Gson();
		String result = gson.toJson(object);
		response.setContentType("text/html; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache"); // 取消浏览器缓存
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
			log.info("使用I/0输入出错" + e);
		}
		out.print(result);
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 获取选项列表名称
	 * 
	 * @param list
	 * @param id
	 * @return
	 */
	public static String getOptionsIntegerName(List<OptionsInteger> list,
			Integer id) {
		for (OptionsInteger optionsInteger : list) {
			if (id != null && id.equals(optionsInteger.getKey())) {
				return optionsInteger.getValue();
			}
		}
		return "";
	}

	/**
	 * 获取选项列表名称
	 * 
	 * @param list
	 * @param id
	 * @return
	 */
	public static String getOptionsStringName(List<OptionsString> list,
			String id) {
		for (OptionsString optionsString : list) {
			if (StringUtils.isNotBlank(id) && id.equals(optionsString.getKey())) {
				return optionsString.getValue();
			}
		}
		return "";
	}

	/**
	 * 获取下拉框HTML
	 * 
	 * @param OptionsIntegers
	 * @return
	 */
	public static String getSelectHtml(List<OptionsInteger> OptionsIntegers,
			String selectName, String selectId, Boolean initFlag) {
		StringBuffer html = new StringBuffer();
		html.append("<select name='" + selectName + "' id='" + selectId
				+ "' class='formInput'>");
		if (initFlag) {
			html.append("<option value='-1'>请选择</option>");
		}
		if (OptionsIntegers != null && !OptionsIntegers.isEmpty()) {
			for (OptionsInteger optionsInteger : OptionsIntegers) {
				html.append("<option value='" + optionsInteger.getKey() + "'>"
						+ optionsInteger.getValue() + "</option>");
			}
		}
		html.append("</select>");
		return html.toString();
	}

	/**
	 * 获取下拉框HTML
	 * 
	 * @param OptionsIntegers
	 * @return
	 */
	public static String getSelectStringHtml(
			List<OptionsString> optionsStrings, String selectName,
			String selectId, String value) {
		StringBuffer html = new StringBuffer();
		html.append("<select name='" + selectName + "' id='" + selectId
				+ "' class='formInput'>");
		html.append("<option value='-1'>请选择</option>");
		if (optionsStrings != null && !optionsStrings.isEmpty()) {
			for (OptionsString optionsString : optionsStrings) {
				if (optionsString.getKey().equals(value)) {
					html.append("<option value='" + optionsString.getKey()
							+ "' selected='selected'>"
							+ optionsString.getValue() + "</option>");
				} else {
					html.append("<option value='" + optionsString.getKey()
							+ "'>" + optionsString.getValue() + "</option>");
				}
			}
		}
		html.append("</select>");
		return html.toString();
	}

	/**
	 * 获取字符串
	 * 
	 * @param object
	 * @return
	 */
	public static String getString(Object object) {
		if (object != null) {
			return object.toString();
		} else {
			return "";
		}
	}

	/**
	 * ENCODE解码
	 * 
	 * @param object
	 * @return
	 */
	public static String enCode(String string) {
		try {
			return StringUtils.isNotBlank(string) ? java.net.URLEncoder.encode(
					string.trim(), "utf-8") : "";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * DECODE解码
	 * 
	 * @param object
	 * @return
	 */
	public static String deCode(String string) {
		try {
			return StringUtils.isNotBlank(string) ? java.net.URLDecoder.decode(
					string.trim(), "utf-8") : "";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 转码
	 * 
	 * @return
	 */
	public static String transCoding(String paramName) {
		String result = null;
		try {
			if (ServletActionContext.getRequest().getParameter(paramName) != null) {
				result = new String(ServletActionContext.getRequest()
						.getParameter(paramName).getBytes("iso-8859-1"),
						"utf-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 字符串转整型
	 * 
	 * @param string
	 * @return
	 */
	public static Integer strToInt(String string) {
		if (StringUtils.isNotBlank(string)) {
			return Integer.valueOf(string);
		} else {
			return null;
		}
	}

	/**
	 * 字符串转BigDecimal
	 * 
	 * @param string
	 * @return
	 */
	public static BigDecimal strToBigDecimal(String string) {
		if (StringUtils.isNotBlank(string)) {
			return new BigDecimal(string);
		} else {
			return null;
		}
	}

	/**
	 * 是否验证权限
	 * 
	 * @param privilegeId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Boolean checkPermission(String privilegeId) {
		if (Globals.IS_VERIFY) {
			Set<String> privileges = (Set<String>) ServletActionContext
					.getRequest().getSession().getAttribute(
							Globals.USER_PRIVILEGES);
			if (privileges != null && privileges.contains(privilegeId)) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	/**
	 * 获取IP地址
	 * 
	 * @return
	 */
	public static String getIpAddr() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ipAddress = null;
		// ipAddress = this.getRequest().getRemoteAddr();
		ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}

		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}

	/**
	 * 获得客户端IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getClientIP() {
		String userIP = "";
		HttpServletRequest request = ServletActionContext.getRequest();
		String originIP = request.getRemoteAddr();
		String realIP = request.getHeader("x-forwarded-for");
		if (realIP == null || realIP.length() == 0)
			userIP = originIP;
		else
			userIP = realIP;
		return userIP;
	}

	/**
	 * 获取用户session
	 * 
	 * @return
	 */
	public static UserSession getUserSession() {
		UserSession us = (UserSession) ServletActionContext.getRequest()
				.getSession().getAttribute(Globals.USER_SESSION);
		return us;
	}

	/**
	 * UKEY验证
	 * 
	 * @param keyFlag
	 *            密钥标志 1普通密钥 2代理商密钥
	 * @param rnd
	 *            随机数
	 * @param return_EncData
	 *            客户端加密后的值
	 * @param keyID
	 *            客户端keyID
	 * @param userKeyID
	 *            登陆用户数据库keyID
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static ReturnDTO checkUKEY(Integer keyFlag, String rnd,
			String return_EncData, String keyID, String userKeyID) {
		// log.info(System.getProperty("java.library.path"));
		ReturnDTO dto = new ReturnDTO();
		try {
			if (Globals.IS_VERIFY_UKEY) {
				if (StringUtils.isBlank(keyID)) {
					dto.setMsg("请插入UKEY再进行操作！");
					return dto;
				}

				if (StringUtils.isBlank(userKeyID)) {
					dto.setMsg("该用户未绑定UKEY！");
					return dto;
				}

				if (!keyID.toUpperCase().equals(userKeyID)) {
					dto.setMsg("当前插入的UKey不是您持有的！");
					return dto;
				}

				// UKEY验证开始
				com.softkey.jsyunew6 j9 = new com.softkey.jsyunew6();
				String DevicePath;

				DevicePath = j9.FindPort(0);

				if (DevicePath == null) {
					// dto.setMsg("未找到加密锁，请插入加密锁后，再进行操作。");
					dto.setMsg("SERVERS UKEY NO FOUND！");
					return dto;
				}

				String outString = "";
				if (keyFlag == 1) {
					outString = j9.EncString(rnd, DevicePath);
				} else if (keyFlag == 2) {
					outString = j9.EncString_New(rnd, DevicePath);
				}

				if (j9.get_LastError() != 0) {
					dto.setMsg("加密字符串出现错误！");
					log.info("rnd=" + rnd + ",return_EncData=" + return_EncData
							+ ",keyID=" + keyID + ",userKeyID=" + userKeyID);
					log.info("j9.EncString(rnd, DevicePath) = " + outString
							+ "【EncString错误码】" + j9.get_LastError());
					return dto;
				}

				if (!outString.equals(return_EncData)) {
					dto.setMsg("该加密锁不可用，请插入正确的加密锁后，再进行操作！");
					return dto;
				}
				// UKEY验证结束
				dto.setFlag(true);
				return dto;
			} else {
				dto.setFlag(true);
				return dto;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("UKEY验证出错了" + e.toString());
		}
		return dto;
	}

	/**
	 * 取反
	 * 
	 * @param num
	 * @return
	 */
	public static String inversion(String num) {
		String result = "";
		for (int j = 0; j < num.length(); j++) {
			String single = num.substring(j, j + 1);
			result = result
					+ Integer.toHexString(Integer.valueOf(single) ^ 0X0F);
		}

		return result;
	}

	/**
	 * 获取读序列号密码
	 * 
	 * @param seriNokey
	 *            序列号密钥
	 * @param key
	 *            密钥
	 * @return 读序列号密码
	 * @throws Exception
	 */
	public static String getCardSeriNoKey(String seriNoKey) throws Exception {
		// 密钥
		String key = Globals.M1CARD_KEY;
		String seriNoPwd = DES.getDesString(key, seriNoKey);
		// System.out.println("读序列号 解密后："+seriNoPwd);
		seriNoPwd = seriNoPwd.substring(0, 12);
		// System.out.println("读序列号密码"+seriNoPwd);
		return seriNoPwd;
	}

	/**
	 * 获取读卡号密码
	 * 
	 * @param seriNo
	 *            序列号
	 * @param readCardNoKey
	 *            读卡号密钥
	 * @return
	 * @throws Exception
	 */
	public static String getReadCardNoKey(String seriNo, String readCardNoKey)
			throws Exception {
		// 密钥
		String key = Globals.M1CARD_KEY;
		// 读卡号密钥解密
		String cardkeySecure = DES.getDesString(key, readCardNoKey);

		// 第一次对卡序列号加密密钥
		String xuliehaojiami = DES.getEncString(cardkeySecure, seriNo);
		// System.out.println("加密后："+xuliehaojiami);
		// 第二次对卡序列号加密密钥
		String readCardNoPwd = DES.getEncString(xuliehaojiami, seriNo);

		readCardNoPwd = readCardNoPwd.substring(0, 12);
		return readCardNoPwd;
	}

	/**
	 * 获取写卡号密码
	 * 
	 * @param writeCardNoKey
	 *            写卡号密钥
	 * @return
	 * @throws Exception
	 */
	public static String getWriteCardNoPwd(String writeCardNoKey)
			throws Exception {
		// 密钥
		String key = Globals.M1CARD_KEY;
		String writeCardNoPwd = DES.getDesString(key, writeCardNoKey);
		writeCardNoPwd = writeCardNoPwd.substring(0, 12);
		return writeCardNoPwd;
	}

	/**
	 * 读取TXT文本
	 * 
	 * @param fileSrc
	 * @return
	 */
	public static List<String> readTxt(File file) {
		List<String> list = null;
		try {
			list = new ArrayList<String>();
			// File file=new File(fileSrc);//源文件位置
			FileReader fr = new FileReader(file);// 创建文件输入流
			BufferedReader in = new BufferedReader(fr);// 包装文件输入流，可整行读取
			String line;
			while ((line = in.readLine()) != null) {
				list.add(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 使用Java反射机制遍历实体类的属性和类型
	 * 
	 * @throws Exception
	 */
	public static void getField() throws Exception {

	}

	/**
	 * 把BCD解压成ASCII
	 * 
	 */
	public static String split(char[] src) {
		int len = src.length * 2;
		int i;
		char ch;
		char dest[] = new char[len];
		// char[] src=ToUnsignedChar(strc);
		for (i = 0; i < len / 2; i++) {
			ch = (char) (src[i] / 0x10);
			if ((ch <= 9) && (ch >= 0)) {
				dest[i * 2] = (char) (ch + 0x30);
				dest[i * 2] &= 0xff;
			} else if ((ch <= 0x0f) && (ch >= 0x0a)) {
				dest[i * 2] = (char) (ch + 0x37);
				dest[i * 2] &= 0xff;
			} else {
				dest[i * 2] = 0x30;
				dest[i * 2] &= 0xff;
			}
			ch = (char) (src[i] % 0x10);
			if ((ch <= 9) && (ch >= 0)) {
				dest[i * 2 + 1] = (char) (ch + 0x30);
				dest[i * 2 + 1] &= 0xff;
			} else if ((ch <= 0x0f) && (ch >= 0x0a)) {
				dest[i * 2 + 1] = (char) (ch + 0x37);
				dest[i * 2 + 1] &= 0xff;
			} else {
				dest[i * 2 + 1] = 0x30;
				dest[i * 2 + 1] &= 0xff;
			}
		}
		return new String(dest);
	}

	private static byte asc_to_bcd(byte asc) {
		byte bcd;

		if ((asc >= '0') && (asc <= '9'))
			bcd = (byte) (asc - '0');
		else if ((asc >= 'A') && (asc <= 'F'))
			bcd = (byte) (asc - 'A' + 10);
		else if ((asc >= 'a') && (asc <= 'f'))
			bcd = (byte) (asc - 'a' + 10);
		else
			bcd = (byte) (asc - 48);

		return bcd;
	}

	/**
	 * 把ASCII压缩成BCD
	 */
	public static byte[] ASCII_To_BCD(byte[] ascii, int asc_len) {
		int bcdlen = (asc_len + 1) / 2;
		byte[] bcd = new byte[bcdlen];
		int j = 0;
		for (int i = 0; i < (asc_len + 1) / 2; i++) {
			bcd[i] = asc_to_bcd(ascii[j++]);
			bcd[i] = (byte) (((j >= asc_len) ? 0x00 : asc_to_bcd(ascii[j++])) + (bcd[i] << 4));
		}
		return bcd;
	}

	// 将HttpServletRequest传入获得所有参数与参数值Map的方法
	@SuppressWarnings("unchecked")
	public static Map<String, String> processParameterMap(
			HttpServletRequest request) {
		final Map<String, String> returnMap = new HashMap<String, String>();
		String key = "";
		String value = "";
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			key = names.nextElement();
			value = request.getParameter(key);
			returnMap.put(key, value);
		}
		return returnMap;
	}

	public static String getAuthCodeStr(String authCode) {
		return "欢迎使用瑞特红卡，本次短信验证码为：" + authCode + " ，工作人员不会向您索取，请勿泄露。";
	}

	public static void main(String[] args) throws Exception {
		System.out.println(System.getProperty("java.library.path"));
		;
		System.out.println(Utils.inversion("0000000000000101"));
		System.out
				.println(Utils
						.deCode("%D6%D0%B9%FA%B6%CC%D0%C5%CD%F8%B7%A2%CB%CD%B2%E2%CA%D4"));

		// 公钥指数e
		BigInteger e = new BigInteger(
				"10030123539446421279035559581668070616148901584561745989261348864214076624150096041660120102260309817204518950616998301393228862650046769070770209198526901");
		// 公钥的模n
		BigInteger n = new BigInteger(
				"110331107161847987585239244516642343476414931580042600603311339373335515419916990952386873237326112470500040843898389090004526569833260021057512675499081830783424709187439513025681589364470457788313283974218113183949165773688933719281352800243488472412019923466042938029341530955211848209832740210585438313017");
		// 私钥d
		BigInteger d = new BigInteger(
				"77413634529210721602008284339323216115774740568069489613626644782937410283866155342202837334028870239309992564681602809765372414855433931106127939211572206899592353000991229856982425826181207364762275509041749014510496981401537496164136033524297432282421719166710087814999213803857235316225040242734808694761");
		String encondM = RSA.enCode("{\"phone\":\"13888888888\"}", e, n);
		System.out.println("明文加密：" + encondM);
		System.out.println("密文解密：" + RSA.deCode(encondM, d, n));

	}

	/**
	 * 自动生成ID流水号方法
	 * 
	 * @param em
	 *            EntityManager 必填项
	 * @param id
	 *            要生成流水号的属性名称，如：termId 必填项
	 * @param entityName
	 *            要生成流水号号的实体类，如：Terminals 必填项
	 * @param maxLength
	 *            该列的长度 必填项
	 * @param midIndex
	 *            要在第几位插入字符 不需要则传 0
	 * @param midString
	 *            要在中间插入的字符串
	 * @param prefix
	 *            流水号前缀
	 * @param suffix
	 *            流水号后缀
	 * @return id
	 */
	@SuppressWarnings("unchecked")
	public static String createSerialNum(EntityManager em, String id,
			String entityName, int maxLength, int midIndex, String midString,
			String prefix, String suffix) {

		String pref = (prefix == null) ? "" : prefix;
		String suff = (suffix == null) ? "" : suffix;
		String mids = (midString == null) ? "" : midString;
		int prefixLength = pref.length();
		int suffixLength = suff.length();
		int midStrLen = mids.length();

		String validateSql = "select count(o) from " + entityName
				+ " o where o." + id + "=?1";
		/*
		 * long count = (Long) em.createQuery(validateSql).setParameter(1,
		 * id).getSingleResult(); boolean flag = (count>0);
		 */
		String createId = "";
		String tempString = "";
		String querySql = "select o. " + id + " from " + entityName
				+ " o order by o." + id + " desc";
		List<String> string = em.createQuery(querySql).setMaxResults(1)
				.getResultList();
		if (string == null || string.isEmpty()) {
			createId = "1";
		} else {
			createId = string.get(0);
			if (createId.length() > 14 && createId.length() < 16) {
				createId = createId.substring(8, 15);
			}
			// --------------------------------
			else if (createId.length() == 16) {
				createId = createId.substring(8, 16);
			}

			Integer newCreateId = Integer.valueOf(createId);
			createId = newCreateId.toString();
			String tempStr = createId.substring(prefixLength, (createId
					.length() - suffixLength));
			Integer newTemp = Integer.valueOf(tempStr) + 1;
			tempString = newTemp.toString();
		}
		while (tempString.length() < (maxLength - prefixLength - suffixLength - midStrLen)) {
			tempString = "0" + tempString;
		}
		while ((Long) em.createQuery(validateSql).setParameter(1, tempString)
				.getSingleResult() > 0) {
			Integer newid = Integer.valueOf(tempString) + 1;
			tempString = newid.toString();
			while (tempString.length() < maxLength - prefixLength
					- suffixLength - midStrLen) {
				tempString = "0" + tempString;
			}
		}
		tempString = pref + tempString + suff;
		if (StringUtils.isNotBlank(midString) && midIndex != 0) {
			String str1 = tempString.substring(0, midIndex - 1);
			String str2 = tempString.substring(midIndex + midStrLen - 1,
					tempString.length());
			tempString = str1 + midString + str2;
		}
		return tempString;
	}

	/***
	 * 
	 *@Title:getBasePath
	 *@Description:获取基本地址
	 *@Params:@return
	 *@Return:String
	 *@author:孟凡岭
	 *@Date:2014-12-11下午02:18:38
	 */
	public static String getBasePath() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		return basePath;
	}
}