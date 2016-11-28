package com.paySystem.ic.utils;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import sun.misc.BASE64Encoder;

/** 
 * @creator  作者 :杰
 * @version  创建时间：2011-12-20 下午04:19:10 
 * 类说明   
 */

public class Skey_RSA {
		
		private static String publicKey; 
		//生成密钥
		public void builtKey() throws Exception{
				KeyPairGenerator kpg=KeyPairGenerator.getInstance("RSA");
		        kpg.initialize(1024);
		        KeyPair kp=kpg.genKeyPair();
		        PublicKey pbkey=kp.getPublic();
		        PrivateKey prkey=kp.getPrivate();
		        FileOutputStream f2 = new FileOutputStream("C://Skey_RSA_priv.dat");
		        ObjectOutputStream b2 = new ObjectOutputStream(f2);
		        b2.writeObject(prkey);
		        publicKey  = (new BASE64Encoder()).encode(pbkey.getEncoded());
		        System.err.println("公:"+publicKey);
		}
	
	   public static void main(String args[]) throws Exception{
		   new Skey_RSA().builtKey();
		   try {
	            String plaintext = "中文中文中文中文中文中文";
	            System.out.println("plaintextt.getByte().length:" + plaintext.getBytes().length);
	            String cipherText = RSAUtils.encrypt(plaintext, RSAUtils.getPublicKey(publicKey));
	            System.out.println("cipherText:" + cipherText);
	            System.err.println("cipherText lenght:" + cipherText.length());
	            System.err.println("cipherText byte[]:" + cipherText.getBytes().length);
	            String plainText = RSAUtils.decrypt(cipherText, RSAUtils.getPrivateKey());

	            System.out.println("需要加密字段:" + plaintext);
	            System.out.println("解密后字段:" + plainText);
	        } catch (Exception e) {
	            e.printStackTrace();
	        } 
	    }

}
