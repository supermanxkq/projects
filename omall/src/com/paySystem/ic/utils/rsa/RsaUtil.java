package com.paySystem.ic.utils.rsa;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 *@Title:测试公钥加密解密工具类
 *@Params:@param args
 *@Return:void
 *@author:张国法
 *@Date:2014-11-27下午02:22:42
 */
public class RsaUtil {


	
	//用公钥计算pubkey密文；测试公钥加密需传入md5加密后的长度为32位的密码
	public static String pubKey(String pwd){
		String pubKey="";
		String modulus = "A7394A88F2D358E0E27C5AB7CF5F3DCD7D5A264D324481209783A90488B76D440DE8F1B699B2219A9E8B6B5A6C8D752D82AB743D111B43E7241B913B1434E531CB1769B2E1C649E028975B19B6975565BD2DC3065ED8B2B2A1D2D7DCDDEEEFB76C48889DA8EC13078C41BE13E6552661706764463C453B4BA5A39184DCE7775F";
		//公钥指数
		String public_exponent = "010001";
		RSAPublicKey publicKey=PublicKeyUtils.getPublicKey(modulus, public_exponent);
		try {
			pubKey=PublicKeyUtils.encryptByPublicKey(pwd,publicKey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pubKey;
	}
	//使用公钥计算pinblock需传入卡号以及md5加密后的长度为32位的密码；
	public static String pinblock(String cardNo,String key){
		String pinblock=PublicKeyUtils.createPinblock(cardNo, "123456", key);
		return pinblock;
	}
	
	//使用公钥计算mac需传入交易参数以及md5加密后的长度为32位的密码；
	public static String Mac_919(String sourcedata,String key){
		String mac=PublicKeyUtils.Mac_919(key, "0000000000000000", sourcedata);
		return mac;
	}
	
	//使用私钥对密文pub_Key进行解密，需传入密文
	public static String decryptByPrivateKey(String cardNo,String pub_Key){
		//公钥指数
		String modulus = "A7394A88F2D358E0E27C5AB7CF5F3DCD7D5A264D324481209783A90488B76D440DE8F1B699B2219A9E8B6B5A6C8D752D82AB743D111B43E7241B913B1434E531CB1769B2E1C649E028975B19B6975565BD2DC3065ED8B2B2A1D2D7DCDDEEEFB76C48889DA8EC13078C41BE13E6552661706764463C453B4BA5A39184DCE7775F";
		//私钥模
		String private_exponent = "5CF45DD84E92E91EE907132E85D97D7352B0E0059D61B31DB8DB79028A34DC4A2E2944E3A9FFF8EA08239BE341E04838AA20409709814C7799FCB0F8543D2B0162845D497F260A896B53098E4C68C2631D1FB53A3181F6AA43F19CDBEC6E8D0C55CCE45F27C834D4276FE9D528894E4049DDA707842E6445AFC3D9259641CC31";
		
		RSAPrivateKey priKey = PublicKeyUtils.getPrivateKey(modulus, private_exponent);
		String pinblock_key="";
		String pinblock_pwd="";
		try {
			pinblock_key = PublicKeyUtils.decryptByPrivateKey(pub_Key,priKey);
			//再次调用pinblock进行转加密生成16位的pinblock_key
			pinblock_pwd = pinblock(cardNo,pinblock_key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//返回pinblock_key，这个返回值就是解密后的密码 ，去和数据库进行对比
		return pinblock_pwd;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		pubKey("12345678123456781234567812345678");
		System.out.println("--------"+pubKey("12345678123456781234567812345678"));
		System.out.println("--------"+pubKey("12345678123456781234567812345678").length());
	}

}
