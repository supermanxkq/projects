package com.blog.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

/**
 * 短信公用类
 * 张国法 
 * @version 2014-10-31 上午14:36:30
 */
public class ShortMessUtil {
	
	//电商项目发送验证码短信方法
	public static String sendCodeMessage(String telephone,String content1){
		String url = "http://42.96.149.47:1086/sdk/BatchSend.aspx";
		String content;
		String result="";
		try {
		content = java.net.URLEncoder.encode(content1,"UTF-8");
		result = HttpUtil.sendGetRequestWithResult(url,"CorpID=SDK1992&Pwd=123456&Mobile="+telephone+"&Content="+content+"&Cell=&SendTime=");
		Pattern pattern = Pattern.compile("[0-9]*");
		boolean r=pattern.matcher(result).matches();
		
		if(r){
			result="发送验证码成功；";
		}else{
			result="发送验证码失败；";
		}
		
		
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	//电商项目发送群发短信方法;群发短信电话号码为多个 是用英文逗号隔开
	public static String sendMessage(String telephone,String content1){
		String url = "http://42.96.149.47:1086/sdk/BatchSend.aspx";
		String result="";
		String content;
		try {
		content = java.net.URLEncoder.encode(content1,"UTF-8");

		result = HttpUtil.sendGetRequestWithResult(url,"CorpID=SDK1993&Pwd=MCIU123456&Mobile="+telephone+"&Content="+content+"&Cell=&SendTime=");
		Pattern pattern = Pattern.compile("[0-9]*");
		boolean r=pattern.matcher(result).matches();
		if(r){
			result="发送成功；";
		}else{
			result="发送失败；";
		}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	//支付平台发送验证码短信方法
	public static String sendPayCodeMessage(String telephone,String content1){
		String url = "http://223.223.176.90:8080/OilingMachine/omall/verificationCode";
		String result="";
		String content;
		try {
			content = java.net.URLEncoder.encode(content1,"UTF-8");

		result = HttpUtil.sendGetRequestWithResult(url,"CorpID=SDK1992&Pwd=123456&Mobile="+telephone+"&Content="+content+"&Cell=&SendTime=");
		Pattern pattern = Pattern.compile("[0-9]*");
		boolean r=pattern.matcher(result).matches();
		if(r){
			result="发送验证码成功；";
		}else{
			result="发送验证码失败；";
		}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
}
