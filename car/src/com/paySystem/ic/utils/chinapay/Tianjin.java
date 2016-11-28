package com.paySystem.ic.utils.chinapay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.RandomUtils;

public class Tianjin {
	
	public static Logger TraceLog = Logger.getLogger(Tianjin.class);
	
	public static void main(String[] args) throws IOException{
		
		
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "GBK");
		String url = "http://bianmin-test.chinapay.com:9080/witsingletran/WitSinTranServlet";
//		String url = Common.getChinapay_3des().getString("url");
		if(StringUtils.isEmpty(url)){
			
			url = "http://bianmin-test.chinapay.com:9080/witsingletran/WitSinTranServlet";
		}
		PostMethod postMethod = new PostMethod(url);
	    
		String merId = Globals.CHINAPAY_MERID;
		String transDate = DateTimeTool.dateFormat("yyyyMMdd", new Date());
		String orderNo = RandomUtils.getNumber("", 2);
		String transType = "0003";
		String openBankId = "0102";
		String cardType = "0";
		String cardNo = "900758403049921840";
		
		String usrName1 = "\\u751f\\u4ea7\\u6d4b\\u8bd5"; //非必须	
//		String tianUn = toUnicode(usrName);
		String tianUn = usrName1;
		TraceLog.debug("tianUn=" + tianUn);
//		System.out.println("tianUn=" + tianUn);
		String tmpUsrName = tianUn;
		TraceLog.debug("tmpUsrName=" + tmpUsrName);
//		System.out.println("tmpUsrName=" + tmpUsrName);
		String certType = "01"; //非必须		
		String certId = "430621198710131015"; //非必须
		String curyId = "156";
		String transAmt = "000000000012";
		String purpose = "00";
		String priv1 ="00"; //非必须
		String tmpPriv1 = toUnicode(priv1);
		TraceLog.debug("uPriv1=" + tmpPriv1);
		String version = "20100831"; 
		String gateId = "7008";
		
		
		TraceLog.debug("gbk版:" + new String(usrName1.getBytes(),"GBK"));
		
		TraceLog.debug("gbk版:" + tozhCN(usrName1));
		TraceLog.debug("gbk版:" + tozhCN(tmpPriv1));
		StringBuffer retStr = new StringBuffer();
		
		retStr.append(merId).append(transDate).append(orderNo).append(transType).append(openBankId)
		.append(cardType).append(cardNo).append(tmpUsrName).append(certType).append(certId)
		.append(curyId).append(transAmt).append(tmpPriv1).append(version).append(gateId);
		
		SignData signData = new SignData();
		String data0 = new String(Base64.encode(retStr.toString().getBytes()));
		String chkValue = signData.signForCP(merId,data0);
//		ResourceBundle chinapay_3des = ResourceBundle.getBundle("path");
//		String chkValue = SecureUtil.digitalSign(merId, new String(Base64.encode(retStr.toString().getBytes())), chinapay_3des.getString("keyPath")+"MerPrK.key");
		
		//填入各个表单域的值
		NameValuePair[] data = { 
				new NameValuePair("merId", merId),
				
				new NameValuePair("transDate", transDate),
				new NameValuePair("orderNo", orderNo),
				new NameValuePair("transType", transType),
				new NameValuePair("openBankId", openBankId),
				new NameValuePair("cardType", cardType),
				new NameValuePair("cardNo", cardNo),
				new NameValuePair("usrName", tmpUsrName),
				new NameValuePair("certType", certType),
				new NameValuePair("certId", certId),
				new NameValuePair("curyId", curyId),
				new NameValuePair("transAmt", transAmt),
				new NameValuePair("purpose", purpose),
				new NameValuePair("priv1", tmpPriv1),
 				new NameValuePair("version", version),
				new NameValuePair("gateId", gateId),
				new NameValuePair("chkValue",chkValue)
		};
		
		// 将表单的值放入postMethod中
		postMethod.setRequestBody(data);
		// 执行postMethod
		int statusCode = 0;
		try {
			statusCode = httpClient.executeMethod(postMethod);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// HttpClient对于要求接受后继服务的请求，像POST和PUT等不能自动处理转发
		if (statusCode != HttpStatus.SC_OK) {
			
			TraceLog.debug("Method failed:" + postMethod.getStatusLine());
		}
		// 读取内容
		InputStream resInputStream = null;
		try {
			resInputStream = postMethod.getResponseBodyAsStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 处理内容
		BufferedReader reader = new BufferedReader(new InputStreamReader(resInputStream));
		String tempBf = null;
		StringBuffer html=new StringBuffer(); 
		while((tempBf = reader.readLine()) != null){ 
			
			html.append(tempBf); 
		}
		String tian = html.toString();
		System.out.println("返回内容"+new String(html.toString().getBytes(),"GBK"));
		int dex = tian.lastIndexOf("=");
		TraceLog.debug(tian.substring(dex + 1));
		TraceLog.debug(tian.substring(0,dex+1));
		
		String tiakong = tian.substring(0,dex+1);
		String tian2 = new String(Base64.encode(tiakong.getBytes()));
		boolean tiankogn = new SignData().verifyForCP("999999999999999", tian2,tian.substring(dex + 1));
		TraceLog.debug("" + tiankogn);
//		System.out.println(html.toString()); 
		
	}

	public static String tozhCN(String unicode) {
		StringBuffer gbk = new StringBuffer();
		String[] hex = unicode.split("\\\\u");
		for (int i = 1; i < hex.length; i++) { // 注意要从 1 开始，而不是从0开始。第一个是空。
			int data = Integer.parseInt(hex[i], 16); // 将16进制数转换为 10进制的数据。
			gbk.append((char) data); // 强制转换为char类型就是我们的中文字符了。
		}
		//System.out.println("这是从 Unicode编码 转换为 中文字符了: " + gbk.toString());
		return gbk.toString();
	}
 
 /**
     * 将String串转换为Unicode编码
     * 2010-10-20
     *
     */
    public static String toUnicode(String zhStr) {
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < zhStr.length(); i++) {
            char c = zhStr.charAt(i);
            unicode.append("\\u" + Integer.toHexString(c));
        }
        System.out.println(unicode.toString());
        return unicode.toString();
    }
}
