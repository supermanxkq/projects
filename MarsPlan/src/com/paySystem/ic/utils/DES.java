package com.paySystem.ic.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
  
public class DES {  
    /** 
     * 加密数据 
     * @param encryptString  注意：这里的数据长度只能为8的倍数 
     * @param encryptKey
     * @param opmode 加密还是解密模式 
     * @return String
     * @throws Exception 
     */   
    public static String ALG_DES(String dataString, String encryptKey, int opmode) throws Exception {  
        SecretKeySpec key = new SecretKeySpec(Hex.s2b(encryptKey), "DES");  
        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding"); //算法/模式/填充 
        cipher.init(opmode, key);
        byte[] resultData = cipher.doFinal(Hex.s2b(dataString));
        return Hex.b2s(resultData);
    }  
      
   /*** 
    * 3DES 加密 ECB
    * @param decryptString 
    * @param decryptKey 
    * @return String  
    * @throws Exception 
    */  
   
   public static String ALG_TriDes(String decryptString, String Key16,int opmode)throws Exception{
	   
	   String keyLeft = Key16.substring(0, 16);
	   String keyRight = Key16.substring(16);
	   if(opmode == 0){ /* 3DES_ECB 加密*/
		   String encryptStr = ALG_DES(decryptString,keyLeft,Cipher.ENCRYPT_MODE );
		   String decryptStr = ALG_DES(encryptStr,keyRight,Cipher.DECRYPT_MODE);
		   return ALG_DES(decryptStr,keyLeft,Cipher.ENCRYPT_MODE); 
	   }else{ /* 3DES_ECB 解密*/
			String encryptStr = ALG_DES(decryptString,keyLeft,Cipher.DECRYPT_MODE );
			String decryptStr = ALG_DES(encryptStr,keyRight,Cipher.ENCRYPT_MODE);
			return ALG_DES(decryptStr,keyLeft,Cipher.DECRYPT_MODE);    
	   }
   }
   
   /**
	 * 加密
	 * @param keydata 密钥
	 * @param data 加密内容
	 * @return
	 * @throws Exception 
	 */
	public static String getEncString(String key16,String data) throws Exception{
		String keyLeft = key16.substring(0, 16);
		String keyRight = key16.substring(16);
		String encryptStr = ALG_DES(data,keyLeft,Cipher.ENCRYPT_MODE );
		String decryptStr = ALG_DES(encryptStr,keyRight,Cipher.DECRYPT_MODE);
		return ALG_DES(decryptStr,keyLeft,Cipher.ENCRYPT_MODE); 
	}
   
	/**
	 * 解密
	 * @param keydata 密钥
	 * @param encyptString 解密内容
	 * @return
	 * @throws Exception
	 */
	public static String getDesString(String key16,String encyptString) throws Exception{
		String keyLeft = key16.substring(0, 16);
		String keyRight = key16.substring(16);
		String encryptStr = ALG_DES(encyptString,keyLeft,Cipher.DECRYPT_MODE );
		String decryptStr = ALG_DES(encryptStr,keyRight,Cipher.ENCRYPT_MODE);
		return ALG_DES(decryptStr,keyLeft,Cipher.DECRYPT_MODE);    
	}
	
	public static void main(String[] args) throws Exception {
		   
		System.out.println(ALG_TriDes("E859712F1FE5EC9D9D98B57A2DA83EB5","298A82B69C72A73C8D9E8F6A6A49D730",0));
		System.out.println(ALG_TriDes("E657C433F0102D7BAA5C8570333C274F","298A82B69C72A73C8D9E8F6A6A49D730",1));
		   
		System.out.println(ALG_TriDes("E859712F1FE5EC9D9D98B57A2DA83EB5","298a82b69c72a73c8d9e8f6a6a49d730",1));
	   
		String key = "9C90A23E18486D0340D5868B3BADA121";
		String data = "1122334400008000";
		  
		String encyptString = DES.getEncString(key, data);
		System.out.println("加密后:"+encyptString);
		data = DES.getDesString(key, encyptString);
		System.out.println("解密后："+data);
		
		//密钥
		String key2 = "298a82b69c72a73c8d9e8f6a6a49d730";
		//读序列号密钥
		String data2 = "4977991D316CC237AF123EC3B303B345";
		//读序列号密码(前12位)
		String val2 = DES.getDesString(key2, data2);
		System.out.println("读序列号 解密后："+val2);
		System.out.println("读序列号密码"+val2.substring(0,12));
		//读出序列号 00000000000000010000000000000000
		String seriNo = "12000800000000010000000000000000";
		seriNo = seriNo.substring(0,16);
		seriNo = seriNo + Utils.inversion(seriNo);
		
		//--------------------------------
		
		//读卡号密钥 
		String data3 = "E859712F1FE5EC9D9D98B57A2DA83EB5";
		String val3 = DES.getDesString(key2, data3);
		//第一次对卡序列号加密密钥
		System.out.println("解密后："+val3);
		
		String xuliehaojiami = DES.getEncString(val3, seriNo);
		//第二次对卡序列号加密密钥
		System.out.println("加密后："+xuliehaojiami);
		
		String xuliehaojiami2 = DES.getEncString(xuliehaojiami, seriNo);
		System.out.println("加密后："+xuliehaojiami2);
		System.out.println("读卡号密码"+xuliehaojiami2.substring(0,12));
		
	}  
} 
