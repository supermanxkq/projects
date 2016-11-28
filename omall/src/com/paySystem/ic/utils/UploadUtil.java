package com.paySystem.ic.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;


public class UploadUtil {
	
	private static final String BOUNDARY = "---------------------------7db1c523809b2";// 数据分割线
	
	/**
	 * 上传图片
	 * @param file
	 * @param fileName
	 * @return
	 * @throws Exception 
	 */
	public static String uploadImg(File file,String fileName) throws Exception {
		
		String fileNameStr = "";
		//String realPath = ServletActionContext.getServletContext().getRealPath("/");
		String realPath = "D:\\omall\\image\\";
		String dirPrefix = DateTimeTool.dateFormat("yyyyMMdd", new Date());
		String dirName = realPath+Globals.UPLOAD_URL+dirPrefix;
		
		
		if(file!=null){
			File fileDir = new File(dirName);
			if(!fileDir.exists())
			  {
				 fileDir.mkdir();
			  }
			 //图片扩展名
			 String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
			//上传文件名  格式如:20141110043338367
			fileNameStr = DateTimeTool.dateFormat("yyyyMMddhhmmssSSS",new Date())+"."+extensionName;
			//先上传到本地
			String saveFilePath = dirName + "\\"+fileNameStr;
			FileUtils.copyFile(file,new File(saveFilePath));
			
		/*	List<File> files = new Vector<File>();
			files.add(new File(saveFilePath));
			//post上传到服务器
			List<String[]> stringParams = new ArrayList<String[]>();
			stringParams.add(new String[]{"a", "a"});
			newFileName = UploadUtil.longRangeUpload(Globals.UPLOAD_URL+"uploadImg", stringParams, files);*/
		}
		return "\\"+Globals.UPLOAD_URL+dirPrefix+"\\"+fileNameStr;
	}
	
	/**
	 * 
	 * @see java Socket 模拟 http上传文件
	 * 
	 * @see 多文件上传
	 */
	public static String longRangeUpload(String urlstr,List<String[]> stringParams, List<File> files) throws Exception {
		String name = "";
		// 表单字段和文件的数据
		StringBuilder sb = new StringBuilder();
		for(String[] parsm : stringParams){
			String key = parsm[0];
			String value = parsm[1];
			sb.append("\r\n\r\n--" + BOUNDARY + "\r\n");
			sb.append("Content-Disposition: form-data; name=\""+key+"\"" + "\r\n");
			sb.append("\r\n");
			sb.append(value + "\r\n");
		}
		
		// 文件之前的数据
		byte[] before = sb.toString().getBytes("UTF-8");
		// 文件之后的数据
		byte[] after = ("--" + BOUNDARY + "--\r\n").getBytes("UTF-8");
		
		URL url = new URL(urlstr);
		Socket socket = new Socket(url.getHost(), url.getPort());
		socket.setSoTimeout(30000);
		socket.setKeepAlive(true);
		socket.setSendBufferSize(262144);
		socket.setReceiveBufferSize(262144);
		OutputStream out = socket.getOutputStream();
		PrintStream ps = new PrintStream(out, true, "UTF-8");

		long length = 0;
		int blen = 0;
		for (int i = 0; i < files.size(); i++) {
			length += files.get(i).length();

			sb = new StringBuilder();
			sb.append("--" + BOUNDARY + "\r\n");
			sb.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + files.get(i).getName() + "\"" + "\r\n");
			sb.append("Content-Type: image/pjpeg" + "\r\n");
			sb.append("\r\n");

			blen += sb.toString().getBytes("UTF-8").length;
		}
		//请求头
		StringBuffer header = new StringBuffer();
		header.append("POST "+urlstr+" HTTP/1.1\r\n");
		header.append("Host: 192.168.210.251:8080\r\n");
		header.append("Accept: */*\r\n");
		header.append("Accept-Language: zh-cn\r\n");
		header.append("Accept-Charset: utf-8\r\n");
		header.append("Content-Type: multipart/form-data; boundary=" + BOUNDARY + "\r\n");
		header.append("Content-Length: " + String.valueOf(before.length + blen + length + after.length));
		ps.print(header);

		out.write(before);
		byte[] buf = new byte[2048];
		int len;
		for (int i = 0; i < files.size(); i++) {
			sb = new StringBuilder();
			sb.append("--" + BOUNDARY + "\r\n");
			sb.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + files.get(i).getName() + "\"" + "\r\n");
			sb.append("Content-Type: image/pjpeg" + "\r\n");
			sb.append("\r\n");
			out.write(sb.toString().getBytes("UTF-8"));
			InputStream in = new FileInputStream(files.get(i));
			while ((len = in.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			out.flush();
			in.close();
			out.write("\r\n".getBytes("UTF-8"));
			if(files.get(i).exists()){
				files.get(i).delete();
			}
		}

		out.write(after);
		out.flush();
		
		String returnValue = null;
		String sCurrentLine = "";
		InputStream urlStream = socket.getInputStream();   
		BufferedReader reader = new BufferedReader(new InputStreamReader(urlStream, "GBK"));   
		while ((sCurrentLine = reader.readLine()) != null) {       
			if (!"".equals(sCurrentLine)) {   // HTTP协议返回三个空行   
		    	//System.out.println(sCurrentLine);
				if(sCurrentLine.contains("result")){
		    		returnValue = sCurrentLine;
		    		JSONObject datajsonObj = JSONObject.fromObject(returnValue);
		    		String result = datajsonObj.getString("result");
		    		if(result.equals("success")){
		    			name = datajsonObj.getString("filename");
		    		}
		    		
		    		break;
		    	}
		   }
		}
		out.close();
		return name;
	}
	
    /**
     * 读取远程url图片
     * @param imgurl
     * @return
     */
    public static File getURLImg(String imgurl) {
    	File file = null;
        String filename = UUID.randomUUID().toString();
        String extensionName = imgurl.substring(imgurl.lastIndexOf(".") + 1);
        String saveFilePath = ServletActionContext.getServletContext().getRealPath("/") + "upload/images/"+filename+"."+extensionName;
        try {
            //实例化url
            URL url = new URL(imgurl);
            //载入图片到输入流
            java.io.BufferedInputStream bis = new BufferedInputStream(url.openStream());
            //实例化存储字节数组
            byte[] bytes = new byte[100];
            //设置写入路径以及图片名称
            OutputStream bos = new FileOutputStream(new File(saveFilePath));
            int len;
            while ((len = bis.read(bytes)) > 0) {
                bos.write(bytes, 0, len);
            }
            bis.close();
            bos.flush();
            bos.close();
            //关闭输出流
            file = new File(saveFilePath);
            
            return file;
        } catch (Exception e) {
            //如果图片未找到
        	e.printStackTrace();
        }
        return file;
    }
    
    public static void main(String[] args) throws Exception {
    	UploadUtil.uploadImg(new File("D://test.jsp"), "test.jsp");
	}
    
}