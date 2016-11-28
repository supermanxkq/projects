<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.softkey.SoftKey"%>
<jsp:useBean id="sha1" scope="page" class="com.softkey.SoftKey"></jsp:useBean>

<html> 
<body>
<p>
<% 

if (request.getParameter("KeyID") != null )
{

	//获取客户端返回的唯一ID
	out.print( "<p>");
	out.print( "KeyID是：");
	out.print( request.getParameter("KeyID"));
	out.print( "</p>");

	//获取客户端返回设置在Key中的用户名
	out.print( "<p>");
	out.print( "用户名是：");
	out.print( request.getParameter("UserName"));
	out.print( "</p>");

	//获取客户端返回设置在Key中的用户登录密码
	out.print( "<p>");
	out.print( "用户登录密码是：");
	out.print( request.getParameter("Password"));
	out.print( "</p>");

	//输出当前随机数
	out.print( "<p>");
	out.print( "随机数是：");
	out.print( session.getAttribute("rnd"));
	out.print( "</p>");

	//返回用户锁对随机数的加密结果
	out.print( "<p>");
	out.print( "用户返回的对随机数进行加密结果是：");
	out.print( request.getParameter("return_EncData"));
	out.print( "</p>");


	//这里在服务器端对随机数进行同样的加密运算

	String strData,m_StrEnc,Key;
	//Key：即加密密钥，这个要与设置在加密锁中的密钥一致
	Key="1234567890ABCDEF1234567890ABCDEF";
	//strData：要进行加密的数据
	strData=""+session.getAttribute("rnd");
	
	out.print( "<p>");
	out.print( "服务器运算的随机数是：");
	out.print(  strData );
	out.print( "<p>");
	
	//在服务器端对数据进行加密运算
	m_StrEnc = new SoftKey().StrEnc(strData,Key);
	  
	out.print( "<p>");
	out.print( "服务器运算的结果是：");
	out.print(  m_StrEnc );
	out.print( "<p>");
	//比较客户端加密锁返回的加密结果与服务端的加密结果是否相符，如果相符就认为是合法用户，由于使用了随机数，从而实现了一次一密的高安全性，可以用于高安全性的身份验证
	if (m_StrEnc.trim().equals(request.getParameter("return_EncData").trim()))
	{
		out.print( "该用户是合法用户");
	}
	else
	{
		out.print( "该用户不是合法用户");
	}
	out.print( "</p>");
	out.print( "<p>");
	out.print( "<p>");
	out.print( "<p>");

}
else
{
	int number=(int)(Math.random()*65535)+1;
	session.setAttribute("rnd",new Integer(number));
	String s_rnd= (new   Integer(number)).toString();

 %>
 
<SCRIPT LANGUAGE=javascript>
 var digitArray = new Array('0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f');

function toHex( n ) {

        var result = ''
        var start = true;

        for ( var i=32; i>0; ) {
                i -= 4;
                var digit = ( n >> i ) & 0xf;

                if (!start || digit != 0) {
                        start = false;
                        result += digitArray[digit];
                }
        }

        return ( result == '' ? '0' : result );
}

function login_onclick() 
{
	var DevicePath,ret,n,mylen;
	try
	{
	
			//建立操作我们的锁的控件对象，用于操作我们的锁
            var aObject = new ActiveXObject("Syunew6A.s_simnew6");
            
            //查找是否存在锁,这里使用了FindPort函数
			DevicePath = aObject.FindPort(0);
			if( aObject.LastError!= 0 )
			{
				window.alert ( "Not Find Key ,Pls insert Key.");
				window.location.href="err.html";
				return ;
			}
			//读取锁的唯一ID
			frmlogin.KeyID.value=toHex(aObject.GetID_1(DevicePath))+toHex(aObject.GetID_2(DevicePath));
			if( aObject.LastError!= 0 )
			{
				window.alert( "Err to GetID,ErrCode is:"+aObject.LastError.toString());
				return ;
			}
			
			//获取设置在锁中的用户名，使用默认的读密码"FFFFFFFF","FFFFFFFF
			ret=aObject.YRead(0,1,"ffffffff","ffffffff",DevicePath);
			mylen =aObject.GetBuf(0);
			frmlogin.UserName.value=aObject.YReadString(1,mylen, "ffffffff", "ffffffff", DevicePath);
			if( aObject.LastError!= 0 )
			{
				window.alert(  "Err to GetUserName,ErrCode is:"+aObject.LastError.toString());
				return ;
			}

			//获到设置在锁中的用户密码,,使用默认的读密码"FFFFFFFF","FFFFFFFF"
			ret=aObject.YRead(20,1,"ffffffff","ffffffff",DevicePath);
			mylen =aObject.GetBuf(0);
			frmlogin.Password.value=aObject.YReadString(21,mylen,"ffffffff", "ffffffff", DevicePath);
			if( aObject.LastError!= 0 )
			{
				window.alert( "Err to GetPwd,ErrCode is:"+aObject.LastError.toString());
				return ;
			}

			//这里返回对随机数的HASH结果
			frmlogin.return_EncData.value=aObject.EncString(frmlogin.rnd.value,DevicePath);
			if( aObject.LastError!= 0 )
			{
					window.alert( "Err to StrEnc,ErrCode is:"+aObject.LastError.toString());
					return ;
			}
			 frmlogin.submit();	

		}
	catch (e) 
	{
		alert(e.name + ": " + e.message);
	}
}
</SCRIPT>
 <OBJECT ID="Spindial1" WIDTH=3 HEIGHT=3 VISIBILITY="hidden" CLASSID="CLSID:469F3884-962C-4F42-8BCC-C6564386A4FA" CODEBASE="ypcab.cab#Version=1.0.0.1"></OBJECT>
 
 <form name="frmlogin" method="post" action="auth.jsp">
<input name="KeyID" type="text" id="KeyID" size="20" />
用户名<input name="UserName" type="text" id="UserName" size="20" />
密码<input name="Password" type="text" id="Password" size="20" /> 
<input name="rnd" type="text" id="rnd" value="<%=session.getAttribute("rnd") %>" />
<input name="return_EncData" type="text" id="return_EncData" value=""   />
<input type="button" name="Submit" value="提交" onclick="login_onclick()"/>
</form>
</BODY>
</HTML>
<%
}
%>
