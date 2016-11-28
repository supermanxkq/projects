<%@ page contentType="text/html; charset=GBK" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>无标题文档</title>
</head>
<%



if (request.getParameter("txtname") != null )
{
out.println("<p>");
out.println("用户名是：");
out.println(request.getParameter("txtname"));
out.println("</p>");

out.println("<p>");
out.println("密码是：");
out.println(request.getParameter("txtpass"));
out.println("</p>");

out.println("<p>");
out.println("锁ID是：");
out.println(request.getParameter("t_ID"));
out.println("</p>");

out.println("<p>");

}
else
{

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
	try
	 {
		var DevicePath,mylen,ret;
		var s_simnew31=new ActiveXObject("Syunew6A.s_simnew6");
		DevicePath = s_simnew31.FindPort(0);//'查找加密锁
		if( s_simnew31.LastError!= 0 )
		{
			window.alert ( "未发现加密锁，请插入加密锁");
		}
		else
		{
			ret=s_simnew31.YRead(0,1,"ffffffff","ffffffff",DevicePath);
					mylen =s_simnew31.GetBuf(0);
			frmlogin.txtname.value=s_simnew31.YReadString(1,mylen, "ffffffff", "ffffffff", DevicePath);
			ret=s_simnew31.YRead(20,1,"ffffffff","ffffffff",DevicePath);
					mylen =s_simnew31.GetBuf(0);
			frmlogin.txtpass.value=s_simnew31.YReadString(21,mylen,"ffffffff", "ffffffff", DevicePath);
			frmlogin.t_ID.value=toHex(s_simnew31.GetID_1(DevicePath))+toHex(s_simnew31.GetID_2(DevicePath));
				frmlogin.submit ();
		}
	}
	catch(err)  
	{  
		  txt="错误,原因是" + err.description + "\n\n"  
		  txt+="请检查是否安装驱动程序"
		  alert(txt)  
	 }  
}

function button1_onclick() 
{
	try
	 {
		var DevicePath,mylen,ret;
		var s_simnew1 = new ActiveXObject("Syunew6A.s_simnew6");
		DevicePath = s_simnew1.FindPort(0);
		if( s_simnew1.LastError!= 0 )
		{
		window.alert ( "未发现加密锁，请插入加密锁");
		}
		else
		{
		if(frmlogin.txtname.value=="" || frmlogin.txtpass.value=="")
		{
			window.alert ( "请输入用户名密码后才进行操作。");return 0;
		}
		mylen = s_simnew1.YWriteString(frmlogin.txtname.value, 1, "ffffffff", "ffffffff", DevicePath);
		if( s_simnew1.LastError!= 0 )
		{ 
			window.alert ( "写入失败1");return 0;
		}
		s_simnew1.SetBuf( mylen, 0);
		ret = s_simnew1.YWrite(0, 1, "ffffffff", "ffffffff",DevicePath);
		if( ret != 0 )
		{ 
			window.alert ( "写入失败2");return 0;
		}
		mylen = s_simnew1.YWriteString(frmlogin.txtpass.value, 21, "ffffffff", "ffffffff", DevicePath);
		if( s_simnew1.LastError!= 0 )
		{ 
			window.alert ( "写入失败3");return 0;
		}
		s_simnew1.SetBuf( mylen, 0);
		ret = s_simnew1.YWrite(20, 1, "ffffffff", "ffffffff",DevicePath);
		if( ret != 0 )
		{ 
			window.alert ( "写入失败4");return 0;
		}
		window.alert ( "写入成功");
		}
	}
	catch(e)  
	  {  
		alert(e.name + ": " + e.message);
		return false;
	  }  
}

function button2_onclick() 
{
	try
	{
		var DevicePath,mylen,ret;
		var s_simnew1 = new ActiveXObject("Syunew6A.s_simnew6");
		DevicePath = s_simnew1.FindPort(0);
		if( s_simnew1.LastError!= 0 )
		{
			window.alert ( "未发现加密锁，请插入加密锁");
		}
		else
		{
			ret=s_simnew1.SetWritePassword(frmlogin.w_hkey.value,frmlogin.w_lkey.value,frmlogin.new_w_hkey.value,frmlogin.new_w_lkey.value, DevicePath);
			if( ret != 0 )
			{ 
				window.alert ( "设置写密码错误");return 0;
			}
			window.alert ( "设置写密码成功");
		}
	}
	catch(err)  
	{  
	  txt="错误,原因是" + err.description + "\n\n"  
	  txt+="请检查是否安装驱动程序"
	  alert(txt)  
	}  
}

function button3_onclick() 
{
	try
	{
		var DevicePath,mylen,ret;
		var s_simnew1 = new ActiveXObject("Syunew6A.s_simnew6");
		DevicePath = s_simnew1.FindPort(0);
		if( s_simnew1.LastError!= 0 )
		{
			window.alert ( "未发现加密锁，请插入加密锁");
		}
		else
		{
			ret=s_simnew1.SetReadPassword(frmlogin.w_hkey_2.value,frmlogin.w_lkey_2.value,frmlogin.r_hkey.value,frmlogin.r_lkey.value, DevicePath);
			if( ret != 0 )
			{ 
				window.alert ( "设置读密码错误");return 0;
			}
			window.alert ( "设置读密码成功");
		}
	}
	catch(err)  
	{  
	  txt="错误,原因是" + err.description + "\n\n"  
	  txt+="请检查是否安装驱动程序"
	  alert(txt)  
	}  
}


</SCRIPT>

<body bgcolor="#ffffff" >

<form name="frmlogin" method="post" action="WriteSN.jsp">
<table width="354" height="192" border="0">
  <tr>

    <td colspan="2"><b>用户登入<b>
    <input type="hidden" name="skeyuserinfo" value="">
    <input type="hidden" name="skeyuserid" value=""></td>
  </tr>
  <tr>
    <td><input name="t_ID" type="text" id="t_ID" style="VISIBILITY: hidden"></td>
  </tr>
  <tr>
    <td colspan="2"><input name="chkonly" type="checkbox" id="chkonly" checked>
      请插入加密锁后，再进行登录</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><input type="submit" name="Submit" value="提交" onclick="login_onclick()">
    &nbsp;<input name="reset" type="reset" id="reset" value="重置"></td>
        <td>&nbsp;
  </tr>
</table>

<p>
  请输入用户名：<input name="txtname" type="text" id="txtname" >
</p>
<p> 
请输入用户密码：<input name="txtpass" type="password" id="txtpass" >
</p>
<p> 
  <input type="button" name="button1" value="写入用户名及密码" onClick="button1_onclick()">
</p>
<p>&nbsp;</p>
<p>设置写密码：</p>
<p>原写密码：
  <input name="w_hkey" type="text" id="w_hkey" value="ffffffff" >
  --
  <input name="w_lkey" type="text" id="w_lkey" value="ffffffff" >
</p>
<p>新写密码：
  <input name="new_w_hkey" type="text" id="new_w_hkey" value="ffffffff" >
  --
  <input name="new_w_lkey" type="text" id="new_w_lkey" value="ffffffff" >
</p>
<p>
  <input type="button" name="button2" value="设置" onClick="button2_onclick()">
</p>
<p>&nbsp;</p>
<p>设置读密码：</p>
<p>写密码：
  <input name="w_hkey_2" type="text" id="w_hkey_2" value="ffffffff" >
  --
  <input name="w_lkey_2" type="text" id="w_lkey_2" value="ffffffff" >
</p>
<p>新的读密码：
  <input name="r_hkey" type="text" id="r_hkey" value="ffffffff" >
  --
  <input name="r_lkey" type="text" id="r_lkey" value="ffffffff" >
</p>
<p>
  <input type="button" name="button3" value="设置" onClick="button3_onclick()">
</p>
<p>注意设置读密码是用“写”密码设置</p>
<p>&nbsp;</p>
</form>
</body>
</html>
<%
}
%>

