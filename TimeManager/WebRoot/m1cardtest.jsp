<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>" />
<title>TEST</title>

<script language="javascript">
function OpenDevice()//建立连接
{
	CMt_32Ctrl.shtPort = 0;		//端口选择
	CMt_32Ctrl.ulBaud = 115200;	//波特率
	if(CMt_32Ctrl.OpenDevice())
	{
		alert(CMt_32Ctrl.msg);
	}
	else
		alert(CMt_32Ctrl.msg);
}

function DevReadSnr()//读取设备产品序列号
{
	if(CMt_32Ctrl.DevReadSnr())
	{
		alert(CMt_32Ctrl.msg + "设备产品序列号为：" + CMt_32Ctrl.strDeviceSerial);//CMt_32Ctrl.strDeviceSerial为传出接口，设备产品序列号
	}
	else
		alert(CMt_32Ctrl.msg);
}

function GetVersion()//读取设备版本号
{
	if(CMt_32Ctrl.GetVersion())
	{
		alert(CMt_32Ctrl.msg + "设备版本号为：" + CMt_32Ctrl.strDeviceVersion);//CMt_32Ctrl.strDeviceVersion为传出接口,设备版本号
	}
	else
		alert(CMt_32Ctrl.msg);
}

function CloseDevice()//断开连接
{
	CMt_32Ctrl.CloseDevice();
	alert(CMt_32Ctrl.msg);
}

function DevBeep()//蜂鸣器蜂鸣
{
	CMt_32Ctrl.shtBeepTime = 2;		//传入参数：设备单次蜂鸣时间
	CMt_32Ctrl.shtBeepSpace = 5;	//传入参数：设备两次蜂鸣之间时间间隔
	CMt_32Ctrl.shtBeepNumber = 2;	//传入参数：设备蜂鸣次数
	CMt_32Ctrl.DevBeep();
	alert(CMt_32Ctrl.msg);
}

function DevReadEeprom()//读EEPROM
{
	CMt_32Ctrl.shtDataLen = 10;	//传入参数：HEX码数据长度
	CMt_32Ctrl.shtAddress = 50;	//传入参数：地址
	if(CMt_32Ctrl.DevReadEeprom())
		alert(CMt_32Ctrl.msg + "读出数据内容为：" + CMt_32Ctrl.strReadData);//CMt_32Ctrl.strReadData:传出接口：读出的EEPROM数据内容
	else
		alert(CMt_32Ctrl.msg);
}

function DevWriteEeprom()//写EEPROM
{
	CMt_32Ctrl.DataLen = 10;	//传入参数：HEX码数据长度
	CMt_32Ctrl.Address = 50;	//传入参数：地址
	CMt_32Ctrl.strWriteData = "00112233445566778899";	//传入参数：写入EEPROM的数据内容，ASC码
	CMt_32Ctrl.DevWriteEeprom();
	alert(CMt_32Ctrl.msg);
}

function RfCard()//M1寻卡
{
	CMt_32Ctrl.shtM1Mode = 0;	//传入参数：寻卡模式0,4
	if(CMt_32Ctrl.RfCard())
		alert(CMt_32Ctrl.msg + "M1卡UID为：" + CMt_32Ctrl.strM1Uid);//CMt_32Ctrl.strM1Uid：传出接口，M1卡UID
	else
		alert(CMt_32Ctrl.msg);
}

function RfAuthenticationKey()//M1卡认证
{
	CMt_32Ctrl.shtM1Mode = 0;		//传入参数：认证模式A.B
	CMt_32Ctrl.shtM1Block = 4;	//传入参数：M1卡块号
	CMt_32Ctrl.strWriteData = "111111111111";//传入参数:认证密码，ASC码
	CMt_32Ctrl.RfAuthenticationKey();
	alert(CMt_32Ctrl.msg);
}

function RfRead()//M1卡读数据
{
	CMt_32Ctrl.shtM1Block = 4;	//传入参数：M1卡块号
	if(CMt_32Ctrl.RfRead())
		alert(CMt_32Ctrl.msg + "读出数据内容为：" + CMt_32Ctrl.strReadData);//CMt_32Ctrl.strReadData:传出接口，读出的数据
	else
		alert(CMt_32Ctrl.msg);
}

function RfWrite()//M1卡写数据
{
	CMt_32Ctrl.shtM1Block = 4;	//传入参数：M1卡块号
	CMt_32Ctrl.strWriteData = "00112233445566778899AABBCCDDEEFF";//传入参数：写入数据，ASC码，32位
  	CMt_32Ctrl.RfWrite();
	alert(CMt_32Ctrl.msg);
}

function RfInitval()//M1卡初始化值
{
	CMt_32Ctrl.shtM1Block = 4;			//传入参数：M1卡块号
	CMt_32Ctrl.shtM1BlockVal = 100;	//传入参数：块值
  CMt_32Ctrl.RfInitval();
	alert(CMt_32Ctrl.msg);
}

function RfIncrement()//M1卡增值
{
	CMt_32Ctrl.shtM1Block = 4;			//传入参数：M1卡块号
	CMt_32Ctrl.shtM1BlockVal = 30;	//传入参数：块值
  CMt_32Ctrl.RfIncrement();
  alert(CMt_32Ctrl.msg);
}

function RfDecrement()//M1卡减值
{
	CMt_32Ctrl.shtM1Block = 4;			//传入参数：M1卡块号
	CMt_32Ctrl.shtM1BlockVal = 20;	//传入参数：块值
  CMt_32Ctrl.RfDecrement();
	alert(CMt_32Ctrl.msg);
}

function RfReadval()//M1卡读块值
{
	CMt_32Ctrl.shtM1Block = 4;		//传入参数：M1卡块号
  if(CMt_32Ctrl.RfReadval())
  	alert(CMt_32Ctrl.msg + "块值为：" + CMt_32Ctrl.shtM1BlockVal);//CMt_32Ctrl.shtM1BlockVal：传出接口：M1卡某块的块值
  else
		alert(CMt_32Ctrl.msg);
}

function RfHalt()//M1卡中止
{
  CMt_32Ctrl.RfHalt();
	alert(CMt_32Ctrl.msg);
}

function RfTransfer()//M1卡传送
{
  CMt_32Ctrl.RfTransfer();
	alert(CMt_32Ctrl.msg);
}

function RfRestore()//M1卡回传
{
  CMt_32Ctrl.RfRestore();
	alert(CMt_32Ctrl.msg);
}

//-------------------- 非接CPU卡		-------------------//
function OpenCard()//非接CPU卡上电
{
	CMt_32Ctrl.shtNOContactMode = 0;	//传入参数：寻卡模式参数 0,1
  if(CMt_32Ctrl.OpenCard())
  	alert(CMt_32Ctrl.msg + "非接触CPU卡ATR为：" + CMt_32Ctrl.strNoContactAtr);//CMt_32Ctrl.strNoContactAtr:传出接口，非接触式卡片ATR
  else
		alert(CMt_32Ctrl.msg);
}

function ExchangePro()//非接CPU卡发送命令
{
	CMt_32Ctrl.shtDataLen = 5;	//传入参数：命令数据长度，HEX码长度，是传入数据的二分之一
	CMt_32Ctrl.strWriteData = "0084000008";	//传入参数：命令
  if(CMt_32Ctrl.ExchangePro())
  	alert(CMt_32Ctrl.msg + "命令返回数据为：" + CMt_32Ctrl.strReadData);//CMt_32Ctrl.strReadData：传出接口：发送命令返回的数据
  else
		alert(CMt_32Ctrl.msg);
}

function CloseCard()//非接CPU卡下电
{
  CMt_32Ctrl.CloseCard();
	alert(CMt_32Ctrl.msg);
}


//---------------------------		接触式CPU卡及SAM卡		---------------------------//
function ICCPowerOn()//接触式CPU卡及SAM卡上电复位
{
	CMt_32Ctrl.shtCardType = 0;		//传入参数：指定操作卡类型0:CPU 1:SAM1 2:SAM2 3:SAM3 
  if(CMt_32Ctrl.ICCPowerOn())
  	alert(CMt_32Ctrl.msg + "接触式卡片ATR为：" + CMt_32Ctrl.strContactAtr);//CMt_32Ctrl.strContactAtr:传出接口，接触式卡片ATR
  else
		alert(CMt_32Ctrl.msg);
}

function ICCCommandExchange()//接触式CPU卡及SAM卡发送命令
{
	CMt_32Ctrl.shtDataLen = 5;	//传入参数：命令数据长度，HEX码长度
	CMt_32Ctrl.strWriteData = "0084000008";	//传入参数：命令
  if(CMt_32Ctrl.ICCCommandExchange())
  	alert(CMt_32Ctrl.msg + "命令返回数据内容为：" + CMt_32Ctrl.strReadData);//CMt_32Ctrl.strReadData:传出接口：发送命令返回的数据
  else
		alert(CMt_32Ctrl.msg);
}

function ICCPowerOff()//接触式CPU卡及SAM卡下电
{
  CMt_32Ctrl.ICCPowerOff();
	alert(CMt_32Ctrl.msg);
}

</script>
        
</head> 

<body>
<OBJECT classid=clsid:BBC3DC17-8DB4-4FEA-90CF-5458101762AA width=0 height=0 align='center' codebase='<%=basePath%>tools/mt_32.CAB#version=1,0,0,1' id='CMt_32Ctrl' HSPACE=0 VSPACE=0></OBJECT>
<form name="form1">
<table width="1390" border="0" cellpadding="0" cellspacing="0">
  <tr bgcolor="#999999">
    <td height="1">&nbsp;</td>
    设备操作
  </tr>
</table>
<br>
<input type="button" onclick="javascript:OpenDevice()" value="建立连接" >
<input type="button" onclick="javascript:DevReadSnr()" value="读产品序列号" >
<input type="button" onclick="javascript:GetVersion()" value="读设备版本号" >
<input type="button" onclick="javascript:CloseDevice()" value="断开连接" >
<input type="button" onclick="javascript:DevBeep()" value="蜂鸣" >
<br>
<br>
<table width="1390" border="0" cellpadding="0" cellspacing="0">
  <tr bgcolor="#999999">
    <td height="1">&nbsp;</td>
    M1卡操作
  </tr>
</table>
<br>
<input type="button" onclick="javascript:RfCard()" value="寻卡" >
<input type="button" onclick="javascript:RfAuthenticationKey()" value="认证" >
<input type="button" onclick="javascript:RfRead()" value="读数据" >
<input type="button" onclick="javascript:RfWrite()" value="写数据" >
<input type="button" onclick="javascript:RfInitval()" value="初始化值" >
<input type="button" onclick="javascript:RfIncrement()" value="加值" >
<input type="button" onclick="javascript:RfDecrement()" value="减值" >
<input type="button" onclick="javascript:RfReadval()" value="读块值" >
<input type="button" onclick="javascript:RfTransfer()" value="传送" >
<input type="button" onclick="javascript:RfRestore()" value="回送">
<input type="button" onclick="javascript:RfHalt()" value="中止卡片" >
<br>
<br>
<table width="1390" border="0" cellpadding="0" cellspacing="0">
  <tr bgcolor="#999999">
    <td height="1">&nbsp;</td>
    非接触式CPU卡操作
  </tr>
</table>
<br>
<input type="button" onclick="javascript:OpenCard()" value="激活卡片" >
<input type="button" onclick="javascript:ExchangePro()" value="发送命令" >
<input type="button" onclick="javascript:CloseCard()" value="下电" >
<br>
<br>
<table width="1390" border="0" cellpadding="0" cellspacing="0">
  <tr bgcolor="#999999">
    <td height="1">&nbsp;</td>
    接触式CPU卡及SAM卡操作
  </tr>
</table>
<br>
<input type="button" onclick="javascript:ICCPowerOn()" value="上电复位" >
<input type="button" onclick="javascript:ICCCommandExchange()" value="发送命令" >
<input type="button" onclick="javascript:ICCPowerOff()" value="下电" >
</form>
<table width="1390" border="0" cellpadding="0" cellspacing="0">
  <tr bgcolor="#999999">
    <td height="1">&nbsp;</td>
  </tr>
</table>
<input type="text" id="cardNo"><input type="button" onclick="javascript:readM1s()" value="M1卡刷卡" >
<script src="js/jquery-1.4.2.min.js"></script>
<script src="js/m1cardUtil.js"></script>

</body>
</html>