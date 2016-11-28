package com.paySystem.ic.utils.rsa;

import com.paySystem.ic.utils.Utils;


/**
*@Description:解密工具类,需公网部署安全前置以及加密机才能调用解密方法;此工具类适用于支付平台以及电商接口使用。
*@author:张国法
*@Date:2014-11-27 09:47:34
*/
public class DecryptUtil {
	
	String url="192.168.12.27";
	int port=5000;
	String content="";
	
	//传入参数256位的密文以及16位的pinblock1发给加密机，计算出16位的pinBlock2，pinBlock2和对数据库存的密码进行比对
	public String  pwd(String card,String pubKey,String pinblock1 ) {
		//按格式进行组报文内容
		content="A012"+"1000010000000001"+"DD"+pinblock1+pubKey;
		String res = null;
		try {
			//通过socket获取返回结果
			res = CardReaderUtils.socketClinet(url, port, content);
		String pinBlock2=Utils.split(res.toCharArray()).substring(12,28);
		System.out.println("pinBlock-------"+pinBlock2);
		System.out.println("pinBlock-------"+pinBlock2.length());
		
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return res;
		
	}
	
	//需传入交易数据长度必须为双数并且不能包含特殊字符，和256位的密文pubKey
	public  String mac(String sourcedata,String pubKey) {
		String mac="";
		//通过socket发送的内容
		content="A013"+pubKey+sourcedata;
		System.out.println("content-----"+content);
		System.out.println("content-----"+content.length());
		String result;
			try {
				//通过socket发送给安全前置，调用加密机进行解密，返回
				result = CardReaderUtils.socketClinet(url, port, content);

			System.out.println("res-----"+result);
			System.out.println(Utils.split(result.toCharArray()).length());
			mac=Utils.split(result.toCharArray()).substring(12,28);
			System.out.println("mac-----"+mac);	
			
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return mac;
					
		
	}
	
	
	public static void main(String[] args) {


	}
}
