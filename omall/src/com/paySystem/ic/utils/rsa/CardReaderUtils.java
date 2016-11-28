package com.paySystem.ic.utils.rsa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.paySystem.ic.utils.Utils;

/**
*@Description:socket通信工具类。
*@author:张国法
*@Date:2014-11-27 09:47:34
*/
public class CardReaderUtils {
	
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
            System.out.println("exception setting socket error: "+ e);
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
            //gz
            String sendLen = Integer.toHexString(content.length()/2);
            
            System.out.println(sendLen);
           
   	    	StringBuilder reqData = new StringBuilder();
   	    	
   	    	while (sendLen.length()<4) {
				  sendLen = "0"+sendLen;				  
			}
   	    	reqData.append(sendLen);
    	   	reqData.append(content);
            //
            //向服务器端第一次发送字符串
            OutputStream netOut = socket.getOutputStream();          
            byte[] sends=Utils.ASCII_To_BCD(reqData.toString().getBytes(),reqData.length());
            System.out.println("send len:"+sends.length);
            System.out.println("send data:"+content);
            
            //System.out.println(Utils.split(sends.toString()));
            System.out.println(Utils.split(sends.toString().toCharArray()));
    	    	
            //向服务器端第二次发送字符串      
    	    netOut.write(sends); 
    	    netOut.flush();  
    	    char[] data = new char[1024]; 
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "ISO-8859-1"));   
        	int len = in.read(data,0,data.length);
        	System.out.println("rev len:"+len);
            res = new String(data).substring(0,len);
            System.out.println(Utils.split(res.toCharArray()));
            
            System.out.println("rev data:"+res);
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


}
