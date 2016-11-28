package com.blog.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

@SuppressWarnings("deprecation")
public class HttpUtil {

	private static final int TIMEOUT=3000000;
	private static final int TIMEOUT_SOCKET=300000;
	public static final String HTTP_GET="get";
	public static final String HTTP_POST="post";

	/**
	 * @param client HTTPClient
	 * @param url 请求的URL
	 * @param params 请求的参数
	 * @param encode 请求的编码格式 eg:HTTP.UTF_8
	 * @return HttpResponse
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private static HttpResponse doPost(HttpClient client,String url,List<BasicNameValuePair> params) throws ClientProtocolException, IOException,ConnectTimeoutException{
		 HttpPost post = new HttpPost(url);
	   	 UrlEncodedFormEntity postEntity;
		 postEntity = new UrlEncodedFormEntity(params,HTTP.UTF_8);
		 post.setEntity(postEntity);
	   	 return client.execute(post);
	}

	private static HttpResponse sendPost(HttpClient client,String url,Map<String,Object> paramMap) throws JSONException, ClientProtocolException, IOException {
		 HttpPost post = new HttpPost(url);
		 StringEntity postEntity;
		 if(paramMap!=null){
			 postEntity = new StringEntity(JSON.encode(paramMap), HTTP.UTF_8);
			 postEntity.setContentType("application/json");
			 post.setEntity(postEntity);
		 }
	   	 return client.execute(post);
	}

	/**
	 * @param client HTTPClient
	 * @param url 请求的URL
	 * @param encode 请求的编码格式 eg:HTTP.UTF_8
	 * @return HttpResponse
	 * @throws Exception
	 */
	private static HttpResponse doGet(HttpClient client,String url) throws Exception{
		HttpGet get = new HttpGet(url);
	   	return client.execute(get);
	}

	/**
	 * @param url 请求的URL
	 * @param paramMap 请求的参数
	 * @param encode 请求的编码格式 eg:HTTP.UTF_8
	 * @return HttpResponse
	 * @throws Exception
	 */
	public static String send(String url,Map<String,Object> paramMap,String methodType)throws Exception{
		HttpClient client =null;
		try {
		HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(params, TIMEOUT);
		HttpConnectionParams.setSoTimeout(params, TIMEOUT_SOCKET);
		client = new DefaultHttpClient(params);

		if(StringUtils.isEmpty(methodType))
			methodType =HTTP_POST;

		if(HTTP_GET.equals(methodType)){
			HttpResponse res=doGet(client,url);
			return getResult(res);
		}else{
			List<BasicNameValuePair> paramList=getParamList(paramMap);
			HttpResponse res=doPost(client,url,paramList);
			return getResult(res);
		}
		}catch(ConnectTimeoutException e){
			throw new Exception("连接超时");
		}catch(Exception e){
			throw new Exception("连接失败，请检查网络设置");
		}finally{
			if(null!=client)client.getConnectionManager().shutdown();
		}
	}


	public static String sendGetRequestWithResult(String endpoint, String requestParameters){
		String result = null;
		if (endpoint.startsWith("http://")){
			// Send a GET request to the servlet
			try{

				// Send data
				String urlStr = endpoint;
				if (requestParameters != null && requestParameters.length () > 0){
					urlStr += "?" + requestParameters;
				}
				URL url = new URL(urlStr);
				URLConnection conn = url.openConnection ();

				// Get the response
				BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
				StringBuffer sb = new StringBuffer();
				String line;
				while ((line = rd.readLine()) != null){
					sb.append(line);
				}
				rd.close();
				result = sb.toString();
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * @param paramMap 请求的参数
	 * @return List<BasicNameValuePair>
	 */
	@SuppressWarnings("rawtypes")
	private static List<BasicNameValuePair> getParamList(Map<String,Object> paramMap){
		List<BasicNameValuePair> paramList = new ArrayList<BasicNameValuePair>();
		Iterator iter = paramMap.entrySet().iterator();
		while (iter.hasNext()) {
			Entry entry = (Entry) iter.next();
			String key = (String)entry.getKey();
			String val = String.valueOf(entry.getValue());
			paramList.add(new BasicNameValuePair(key,val));
		}
		return paramList;
	}

	/**
	 * @param httpResponse HttpResponse
	 * @return 服务端返回值
	 * @throws Exception
	 */
	private static String getResult(HttpResponse httpResponse) throws Exception{
		String str="";
		HttpEntity entity=httpResponse.getEntity();
		Map<String,Object> map = new HashMap<String, Object>();
	   	if(httpResponse.getStatusLine().getStatusCode()==200){
	   		str= EntityUtils.toString(entity,"utf-8");
	   	}else{
	   		map.put("success", false);
	   		map.put("description", EntityUtils.toString(entity,"utf-8"));
	   		str =JSON.encode(map);
	   	}
	   	return str;
	}

	private static String getSendResult(HttpResponse httpResponse) throws Exception{
		String str="";
		HttpEntity entity=httpResponse.getEntity();
		Map<String,Object> map = new HashMap<String, Object>();
	   	if(httpResponse.getStatusLine().getStatusCode()==200){
	   		str= EntityUtils.toString(entity,"utf-8");
	   	}else{
	   		map.put("success", false);
	   		map.put("description", EntityUtils.toString(entity,"utf-8"));
	   		str =JSON.encode(map);
	   	}
	   	return str;
	}

}
