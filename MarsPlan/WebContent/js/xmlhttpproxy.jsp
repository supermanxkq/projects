<%@ page language="java" pageEncoding="UTF-8"%>   
<%@ page import="java.util.Map,java.util.HashMap,java.util.Enumeration"%>
<%@page import="java.net.HttpURLConnection"%>   
<%--   
/**  
 * 为解决JavaScript跨域访问资源时，遇到因同源策略而造成访问资源失败的问题。  
 * (而实际上，使用AJAX的XMLHttpRequest时碰到这种情况的几率居多。)  
 *  
 * 使用此代理可以实现请求转发：  把跨域的资源信息获取回本域，然后再把该信息响应给本域提交请求的对象。  
 *  
 * 中文编码问题: 跨域资源返回时都做了decode操作，默认UTF8编码。  
 *  
 * 参数问题: 访问此代理的参数中必须含有url的参数，作为跨域访问的目标url对象。  
 *          除了url参数外的其他参数，将生成为跨域访问时目标url的参数，共同转发过去。  
 */  
 --%>   
<%   
    String url = null;   
    Map<String, String> req_map = new HashMap<String, String>();   
    Enumeration<?> _enum = request.getParameterNames();   
    while (_enum.hasMoreElements()) {   
        String paramName = (String) _enum.nextElement();   
        String paramValue = request.getParameter(paramName);   
        req_map.put(paramName, paramValue);   
    }   
    if (!req_map.isEmpty())   
        url = req_map.remove("url");  
    if ((url != null) && (url.length() > 0)) {   
        if (!req_map.isEmpty()) {   
            StringBuffer url_sbf = new StringBuffer(url);   
            for (Map.Entry<String, String> entry : req_map.entrySet()) {//generate parameters   
                String _par_key = entry.getKey();   
                String _par_value = entry.getValue();   
                if (_par_key != null && _par_key != "") {   
                    if (url_sbf.indexOf("?") == -1)   
                        url_sbf.append("?");   
                    else  
                        url_sbf.append("&");   
                    url_sbf.append(_par_key).append("=").append(   
                            _par_value);   
                }   
            }   
            url = url_sbf.toString();   
        }   
        java.net.URL _url = new java.net.URL(url);   
        java.net.HttpURLConnection urlcon = (HttpURLConnection)_url.openConnection(); 
        urlcon.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        java.io.InputStream is = urlcon.getInputStream();   
        java.io.BufferedReader buffer = new java.io.BufferedReader(   
                new java.io.InputStreamReader(is));   
        StringBuffer bs = new StringBuffer();   
        String lineStr = null;   
        while ((lineStr = buffer.readLine()) != null) {   
            String stri = java.net.URLDecoder.decode(lineStr, "UTF-8");   
            bs.append(stri).append("\n");   
        }  
        if (bs.toString().indexOf("<?xml version=") != -1) {//if XML file, for AJAX   
            response.setContentType("text/xml; charset=UTF-8");   
            response.setHeader("Cache-Control", "no-cache");   
            out.println(bs.toString());   
        } else  
            out.println(bs.toString());   
    }   
%>  