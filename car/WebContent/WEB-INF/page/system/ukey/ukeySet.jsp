<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<base href="<%=basePath%>" />
	<title>用户管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<link rel="shortcut icon" href="<%=basePath%>favicon.ico" type="image/x-icon" />
	<link href="css/style.css" rel="stylesheet" type="text/css" />	
	<script src="js/jquery-1.4.2.min.js"></script>
	<link href="js/jquery/css/jquery.ui.all.css"  rel="stylesheet"  type="text/css" />	
	<script src="js/jquery/jquery.ui.core.js"></script>
	<script src="js/jquery/jquery.ui.widget.js"></script>
	<script src="js/jquery/jquery.ui.mouse.js"></script>
	<script src="js/jquery/jquery.ui.button.js"></script>
	<script src="js/jquery/jquery.ui.draggable.js"></script>
	<script src="js/jquery/jquery.ui.position.js"></script>
	<script src="js/jquery/jquery.ui.resizable.js"></script>
	<script src="js/jquery/jquery.ui.dialog.js"></script>
	<script src="js/common.js"></script>
	<script type="text/javascript"><!--
		
		
		function setCipherCode(){
		    var cipherCode = $("#cipherCode").val();
		    var wPwd1 = $("#wPwd1").val();
		    var wPwd2 = $("#wPwd2").val();
		    if(cipherCode==""){
				window.alert("请输入要设置的密钥！");
				return false;
			}
			if (cipherCode.length!=9){
		    	alert("请输入9位密钥!");	 
		    	$("#cipherCode").focus();
		    	return false;
			}
		    //if(wPwd1==""||wPwd2==""){
				//window.alert("请输入写密码！");
				//return false;
			//}
			try{
				var DevicePath,mylen,ret;
				var s_simnew1 = new ActiveXObject("Syunew6A.s_simnew6");
				DevicePath = s_simnew1.FindPort(0);
				if( s_simnew1.LastError!= 0 ){
					window.alert ( "未发现加密锁，请插入加密锁");
					return false;
				}
				
				//读取锁的唯一ID
				var keyID = toHex(s_simnew1.GetID_1(DevicePath))+toHex(s_simnew1.GetID_2(DevicePath));
				if(keyID==""){
					window.alert ( "未发现加密锁，请插入加密锁");
					return false;
				}
				
                addr=100;
	
	            //写入字符串到地址1
	            nlen = s_simnew1.YWriteString( cipherCode,addr+1, "ffffffff", "ffffffff", DevicePath);
	            if( s_simnew1.LastError !=0 ) {
	                window.alert("写入密钥错误。") ;
	                return false;
	            }
	           //写入字符串的长度到地址0
	            s_simnew1.SetBuf(nlen,0);
	            ret = s_simnew1.YWrite( addr+0, 1, "ffffffff", "ffffffff", DevicePath);
	            if( ret != 0 ){
	            	window.alert("写入字符串长度错误。错误码："+ret );
	            }else{
	            	alert("设置成功！");	
	            }
			}catch(e){  
				alert(e.name + ": " + e.message);
				return false;
			}
		}
		
		function getValue(){
			var DevicePath,ret,n,mylen;
			try{
				//建立操作我们的锁的控件对象，用于操作我们的锁
		        var aObject = new ActiveXObject("Syunew6A.s_simnew6");
		        
		        //查找是否存在锁,这里使用了FindPort函数
				DevicePath = aObject.FindPort(0);
				if( aObject.LastError!= 0 ){
					alert("请插入UKEY再进行操作");
					return false;
				}

				addr=100;
				//先从地址0读到以前写入的字符串的长度
	            ret = aObject.YRead(addr+0, 1, "ffffffff", "ffffffff", DevicePath);
	            nlen =aObject.GetBuf(0);
	            if( ret != 0 ){
	                window.alert("读取字符串长度错误。错误码：" );
	                return false;
	            }
	          	//再读取相应长度的字符串
	            outString = aObject.YReadString(addr+1, nlen, "ffffffff", "ffffffff", DevicePath);
	            if( aObject.LastError != 0 ){
	            	window.alert("读取字符串(带长度)错误。错误码：" );
	            } else{
	            	window.alert("已成功读取字符串(带长度)：" + outString);
	            }
				return false;
			}catch (e){
				alert(e.name + ": " + e.message);
				return false;
			}
			
		}
		
	--></script> 
</head>
<body>
	<div class="Position">
		当前位置是：HOME &gt;&gt; 系统设置 &gt;&gt; UKEY密钥设置
	</div>
	
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
	 	<tr>
	  		<th align="right" width="30%">请输入要设置的密钥：</th>
	        <td width="70%"><input type="text" name="cipherCode" id="cipherCode" maxlength="9" class="formInput"/></td>
	  	</tr>
	</table>
	
	<div class="formTableBottom">
		<my:permission key='sy-9501-01' value='UKEY密钥设置'>
	 		<input id="submitInput" type="button" class="formButton" value="设 置" onclick="return setCipherCode();"/>
	 		<!-- <input id="submitInput" type="button" class="formButton" value="显示" onclick="return getValue();"/> -->
	 	</my:permission>
	 </div>
</body>
</html>