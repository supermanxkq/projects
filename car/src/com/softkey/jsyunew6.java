package com.softkey;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class jsyunew6{
	private static final Log log = LogFactory.getLog(jsyunew6.class);
	
	//获到锁的版本
	public static native int GetVersion(String InPath);
	//获到锁的ID
	public static native int GetID_1(String InPath);
	public static native int GetID_2(String InPath);
	//返回最后的错误信息
	public static native int get_LastError();
	//查找加密锁
	public static native String FindPort(int start);
	//查找指定的加密锁
	public static native String FindPort_2(int start,int in_data,int verf_data);
	//查找指定的加密锁(使用普通算法二)
	public static native String FindPort_3(int start, long in_data, long verf_data);
	//设置读密码
	public static native int SetReadPassword(String W_hkey, String W_lkey, String new_hkey, String new_lkey, String InPath);
	//设置写密码
	public static native int SetWritePassword(String W_hkey, String W_lkey, String new_hkey, String new_lkey, String InPath);
	//普通算法函数
	public static native int sWriteEx_New(int in_data, String KeyPath);
	public static native int sWrite_2Ex_New(int in_data, String KeyPath);
	public static native int sWriteEx(int InData, String InPath);
	public static native int sWrite_2Ex(int InData, String InPath);
	public static native int sRead(String InPath);
	public static native int sWrite(int InData,String InPath);
	public static native int sWrite_2(int InData,String InPath);
	//从加密锁中读取一批字节
	public static native int YRead(short Address,short len, String HKey, String LKey,String InPath);
	//从缓冲区中获得数据
	public static native short GetBuf(int pos);
	//写一批字节到加密锁中
	public static native int YWrite(short Address, short len,String HKey, String LKey, String InPath);
	//设置要写入的缓冲区的数据
	public static native int SetBuf(int pos,short Data);
	//从加密锁中读字符串-新
	public static native String NewReadString(int Address, int len, String HKey, String LKey,String InPath);
	//写字符串到加密锁中-新
	public static native int NewWriteString(String InString, int Address, String HKey, String LKey,String InPath);
	//兼容旧的读写字符串函数，不再使用
	public static native String YReadString(short Address, short len, String HKey, String LKey,String InPath);
	public static native int YWriteString(String InString, short Address, String HKey, String LKey,String InPath);
	//'设置增强算法密钥一
	public static native int SetCal_2(String Key, String InPath);
	//使用增强算法一对字符串进行加密
	public static native String EncString(String InString, String InPath);
	//使用增强算法一对二进制数据进行加密
	public static native int Cal(String InPath);
	//'设置增强算法密钥二
	public static native int SetCal_New(String Key, String InPath);
	//使用增强算法二对字符串进行加密
	public static native String EncString_New(String InString, String InPath);
	//使用增强算法二对二进制数据进行加密
	public static native int Cal_New(String InPath);
	 //使用增强算法对字符串进行解密
	public static native String DecString(String InString, String Key);
	//设置要加密的缓冲区的数据
	public static native int SetEncBuf(int pos, short Data);
	//从缓冲区中获取加密后的数据
	public static native short GetEncBuf(int pos);

	//***初始化加密锁函数
	public static native int ReSet(String Path);
   
	static{
		try {
		System.loadLibrary("Jsyunew6"); 
		}
		catch( UnsatisfiedLinkError e ){
			log.info("Can//t find library libJsyunew6.so"); 
			System.exit( -1 );
		}
     }
 
}