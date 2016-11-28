package com.paySystem.ic.utils.chinapay;


import org.apache.log4j.Logger;

import chinapay.SecureLink;

import com.paySystem.ic.utils.Globals;


/**
 * 签名类
 * 
 * @author guobin
 * 
 */
public class SignData {

	public static Logger TraceLog = Logger.getLogger(SignData.class);
	

	/**
	 * 根据商户ID，待签名数据，调用签名API得到签名串(该签名串用户发送给CP)
	 * 
	 * @param merId
	 * @param data
	 * @return
	 */
	public String signForCP(String merId, String data) {
		chinapay.PrivateKey key = new chinapay.PrivateKey();
		chinapay.SecureLink t;
		boolean flag;
		String ChkValue2;
		// 得到商户私钥存放路径
		String path = Globals.CHINAPAY_KEYPATH;
		if (path == null || "".equals(path)) {
			TraceLog.error("can't find the PrivateKey path!");
			return null;
		}
		//初始化，签名
		flag = key.buildKey(merId, 0, path);
		if (flag == false) {
			TraceLog.error("build key error! merId=" + merId + " path=" + path);
			return null;
		}
		t = new chinapay.SecureLink(key);
		ChkValue2 = t.Sign(data); // Value2为签名后的字符串
		return ChkValue2;
	}

	/**
	 * 通过CP公钥，验证CP传回的数据签名
	 * 
	 * @param bean
	 * @param ChkValue2
	 * @return
	 */
	public boolean verifyForCP(String merId, String data, String chkValue) {
		chinapay.PrivateKey key = new chinapay.PrivateKey();
		boolean flag;
		// 得到CP公钥存放路径
		String path = Globals.CHINAPAY_PUB_KEYPATH;
		if (path == null || "".equals(path)) {
			TraceLog.error("can't find the PubKey path!");
			return false;
		}

//		String path = "D://log/";
		// 初始化，验签
		flag = key.buildKey(merId, 0, path);
		if (!flag) {
			TraceLog.error("verifyForCP:生成公钥失败!merId=" + merId + ",path=" + path);
			return false;
		}
		SecureLink t = new chinapay.SecureLink(key);
		boolean flag1 = t.verifyAuthToken(data, chkValue); // chkValue为ChinaPay应答传回的域段
		if (!flag1) {
			//签名验证错误处理
			TraceLog.error("verifyForCP error!data=" + data + ",chkValue="
					+ chkValue);
			return false;
		}
		return true;
	}
}
