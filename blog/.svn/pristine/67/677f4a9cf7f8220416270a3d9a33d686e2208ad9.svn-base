package com.blog.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

public class ClientUtils {
	public static Logger log = Logger.getLogger(ClientUtils.class);

	/** 
	 * socket参数初始化设置
	 * @param socket
	 * @throws SocketException
	 */
	public static void socketInit(Socket socket) throws SocketException {
		try{
			/**表示立即发送数据*/
			socket.setTcpNoDelay(true); 
			/**表示允许重用Socket所绑定的本地地址*/
			socket.setReuseAddress(true);
			/**表示接受数据时的超时时间。*/
			socket.setSoTimeout(100000);
			/**表示当执行close()；方法时候，是否理解关闭底层的socket。*/
			socket.setSoLinger(true,60);
	//		socket.setSoLinger(false, -1);
			/**表示发送数据的缓冲大小*/
			socket.setSendBufferSize(2048);
			/**表示接受数据的缓冲区的大小*/
			socket.setReceiveBufferSize(2048);
			/**表示长时间处于空闲状态的SOCEKT，是否自动把他/她关闭*/
			socket.setKeepAlive(true);
			/**表示是否支持发送一个字节的TCP紧急数据*/
			socket.setOOBInline(true);  
		} catch (IllegalArgumentException  e) {
            e.printStackTrace();
            log.error("exception setting socket error: "+ e);
        }
	}
	/**
	 * 使用 socket 方式提交数据
	 * @param url
	 * @param port
	 * @param content
	 * @return 
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public static String socketClinet(String url,Integer port,String content) throws InterruptedException  {
		String res = new String();
		Socket socket = null;
		
        try {
            socket = new Socket(url, port);
            socketInit(socket);
            //向服务器端第一次发送字符串
            OutputStream netOut = socket.getOutputStream();          
            byte[] sends=Utils.ASCII_To_BCD(content.getBytes(),content.length());
    	    log.info("send len:"+sends.length);
    	    log.info("send data:"+sends);
    	    	
            //向服务器端第二次发送字符串      
    	    netOut.write(sends); 
    	    netOut.flush();
    	    
    	    char[] data = new char[1024]; 
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "GB2312"));   
        	int len = in.read(data,0,data.length);
            log.info("rev len:"+len);
            res = new String(data).trim();
            log.info("rev data:"+res);
            in.close();
            netOut.close();
          
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
		return res;
	}
	
	 public static String http(String url, Map<String, String> params,String encoded) {

			URL u = null;
			HttpURLConnection con = null;
			// 构建请求参数
			StringBuffer sb = new StringBuffer();
			if (params != null) {
				for (Entry<String, String> e : params.entrySet()) {
					sb.append(e.getKey());
					sb.append("=");
					sb.append(e.getValue());
					sb.append("&");
				}

				sb.substring(0, sb.length() - 1);
			}

			//System.out.println("send_url:" + url);
			//System.out.println("send_data:" + sb.toString());

			// 尝试发送请求
			try {
				u = new URL(url);
				con = (HttpURLConnection) u.openConnection();
				con.setRequestMethod("POST");
				con.setDoOutput(true);
				con.setDoInput(true);
				con.setUseCaches(false);
				con.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
				OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), encoded);
				osw.write(sb.toString());
				osw.flush();
				osw.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (con != null) {
					con.disconnect();
				}
			}

			// 读取返回内容
			StringBuffer buffer = new StringBuffer();
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), encoded));
				String temp;
				while ((temp = br.readLine()) != null) {
					buffer.append(temp);
					buffer.append("\n");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return buffer.toString();
		}
	 
	 /**
	 * URLConnection连接
	 * @param urlString
	 * @return
	 */
	 public static String getReturnByUrl(String urlString) {
    	URL url = null; 
    	String returnValue = null;
    	String sCurrentLine = "";
		try{
			url = new URL(urlString);
			URLConnection connection = url.openConnection();// 打开连接   
			connection.setConnectTimeout(20000);//连接超时（单位：毫秒）
			connection.setReadTimeout(20000);//读操作超时（单位：毫秒）
			connection.connect();   
			InputStream urlStream = connection.getInputStream();   
			BufferedReader reader = new BufferedReader(new InputStreamReader(urlStream, "GBK"));   
			while ((sCurrentLine = reader.readLine()) != null) {       
			    if (!"".equals(sCurrentLine)) {   // HTTP协议返回三个空行   
			    	returnValue = sCurrentLine;
			   }
			}
		}catch(Exception e){
			returnValue = null;
			log.info("连接失败 ");
		}
		return returnValue;
    }
	 
	public static void main(String[] args){
		try {
/*			Map parms=new HashMap(); 
			ClientUtils client = new ClientUtils();
			String cpnoderno = "C20120602121644344627";
			parms.put("method", "querystate");
			parms.put("cporderno", cpnoderno);
			parms.put("sign", MD5.MD5Encode("method=querystate&cporderno="+cpnoderno+"cuncun8.com").toUpperCase());
			String response = new String(client.postMethod("http://ec.cuncun8.com/rechargeinterface.sc", parms),"UTF-8");
			System.out.println(response);*/
			
			/*StringBuffer sb = new StringBuffer();
			sb.append("00-49-60-00-15-00-00-60-30-00-00-00-00-01-00-20");
			sb.append("18-00-00-02-c0-00-12-10-00-00-16-51-53-04-25-30");
			sb.append("30-31-30-30-30-30-33-37-33-30-30-30-30-30-30-30");
			sb.append("31-30-30-30-30-32-30-32-00-26-12-12-24-15-10-12");
			sb.append("11-20-16-20-11-00-00-00-02-30-31-");*/
			{
				String content = Utils.split("00000000000030351200080000030351".toCharArray());
				System.out.println(content);
				String s1=ClientUtils.socketClinet("192.185.5.22", 9000, content);
				System.out.println(s1);
				System.out.println(s1.length());
				String aa = s1.substring(0, 32);
				String bb = s1.substring(32, 64);
				String cc = s1.substring(64, 96);
				String dd = s1.substring(96, 128);
				System.out.println("1扇区3块写入的数据："+aa);
				System.out.println("2扇区3块写入的数据："+bb);
				System.out.println("4 7扇区3块写入的数据："+cc);
				System.out.println("2扇区3块写入的数据："+dd);
				System.out.println("-------------------------");
				System.out.println("1扇区3块读密码："+aa.substring(0,12)+"-写密码："+aa.substring(20,32));
				System.out.println("2扇区3块读密码："+bb.substring(0,12)+"-写密码："+bb.substring(20,32));
				System.out.println("4 7扇区3块读密码："+cc.substring(0,12)+"-写密码："+cc.substring(20,32));
				System.out.println("8扇区3块读密码："+dd.substring(0,12)+"-写密码："+dd.substring(20,32));
			}
			
			/*for (int i = 0;i<10000;i++) {
				System.out.println(i+"-"+ClientUtils.getReturnByUrl("http://192.168.5.50:8000/api/transaApi!trades?tradeType=&from=2013-09-13&to=2013-09-15&merId=&merName=&termName=&hiddenTest=true&organId=00000001&page=1&pagenum=999999"));
			}*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
