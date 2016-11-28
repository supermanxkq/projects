//建立连接
function OpenDevice(){
	var shtPort = -1;		//端口选择
	var ulBaud = 115200;	//波特率
	var st = CMt_32Ctrl.OpenDevice(shtPort,ulBaud);
	
	if(st == 0 || st < 0){
		alert("打开设备失败");
		return false;
	}else{
		DeviceHandle.value = st;
		var sver = CMt_32Ctrl.GetVersion(DeviceHandle.value);
		if(CMt_32Ctrl.lErrorCode == 0)
			Version.value = sver;
		else
			Version.value = "";
			
		var snr = CMt_32Ctrl.DevReadsnr(DeviceHandle.value,20);
		if(CMt_32Ctrl.lErrorCode == 0)
			Serial.value = snr;
		else
			Serial.value = "";
		//alert("打开设备成功");
		return true;
	}
}
//断开连接
function CloseDevice(){
	var ret = CMt_32Ctrl.CloseDevice(DeviceHandle.value);
	Version.value = "";
	Serial.value = "";
	DeviceHandle.value = "-1";
	if(ret == 0){
		//alert("关闭设备成功");
	}else{
		alert("关闭设备失败，错误码为：" + ret);
	}
}
//M1寻卡
function RfCard(){
	var shtM1Mode = 0;	//传入参数：寻卡模式0,4
	var ret = CMt_32Ctrl.RfCard(DeviceHandle.value,shtM1Mode);
	if(CMt_32Ctrl.lErrorCode == 0){
		//alert("寻卡成功！M1卡UID为：" + ret);//CMt_32Ctrl.strM1Uid：传出接口，M1卡UID
		M1uid.value = ret;
		return true;
	}else{
		errDevBeep();
		alert("寻卡失败,请将卡移开重试！");
		return false;
	}
}
//M1卡认证
//mode 认证模式A(0).B 
//block M1卡块号
//passwd 认证密码，ASC码
function RfAuthenticationKey(mode,block,passwd){
	var ret = CMt_32Ctrl.RfAuthenticationKey(DeviceHandle.value,mode,block,passwd);
	if(ret == 0){
		//alert("校验成功");
		return true;
	}else{
		//alert("校验失败，错误码为：" + CMt_32Ctrl.lErrorCode);
		return false;
	}
}
//M1卡读数据
//block M1卡块号 4序列号 8卡号
function RfRead(block){
	
	var ret = CMt_32Ctrl.RfRead(DeviceHandle.value,block);
	if(CMt_32Ctrl.lErrorCode == 0){
		//alert("读数据成功,读出数据内容为：" + ret);//CMt_32Ctrl.strReadData:传出接口，读出的数据
		return ret;
	}else{
		alert("读数据失败，错误码为：" + CMt_32Ctrl.lErrorCode);
		return false;
	}
}

//M1卡写数据
//block M1卡块号 4序列号 8卡号
//writeData 写入数据，ASC码，32位
function RfWrite(block,writeData){
	
	var ret = CMt_32Ctrl.RfWrite(DeviceHandle.value,block,writeData);
	if(ret == 0){
		//alert("写数据成功");
  		return true;
  	}else{
  		alert("写数据失败，错误码为：" + CMt_32Ctrl.lErrorCode);
  		return false;
  	}
	
}

//蜂鸣
function devBeep(){
	var shtBeepTime = 2;		//传入参数：设备单次蜂鸣时间
	var shtBeepSpace = 5;	//传入参数：设备两次蜂鸣之间时间间隔
	var shtBeepNumber = 1;	//传入参数：设备蜂鸣次数
	var ret = CMt_32Ctrl.DevBeep(DeviceHandle.value,shtBeepTime,shtBeepSpace,shtBeepNumber);
	if(ret == 0){
		//alert("设备蜂鸣成功");
	}else{
		alert("设备蜂鸣失败，错误码为：" + CMt_32Ctrl.lErrorCode);
	}
}

//蜂鸣
function errDevBeep(){
	var shtBeepTime = 2;		//传入参数：设备单次蜂鸣时间
	var shtBeepSpace = 1;	//传入参数：设备两次蜂鸣之间时间间隔
	var shtBeepNumber = 3;	//传入参数：设备蜂鸣次数
	var ret = CMt_32Ctrl.DevBeep(DeviceHandle.value,shtBeepTime,shtBeepSpace,shtBeepNumber);
	if(ret == 0){
		//alert("设备蜂鸣成功");
	}else{
		alert("设备蜂鸣失败，错误码为：" + CMt_32Ctrl.lErrorCode);
	}
}

//函数功能：设置非接触存储卡指定块地址的数值
//函数声明:  __int16 __stdcall rf_initval(HANDLE icdev,unsigned char nAdr,unsigned long ulValue);
//入口参数： icdev:     通讯设备标识符
//       nAdr:      块地址
//         ulValue:   传入的值 
//返回值:    <>0 错误
//         =0 正确
function RfInitval(block){
	var ret = CMt_32Ctrl.RfInitval(DeviceHandle.value,block,0);
	if(ret == 0){
		//alert("初始化块值成功");
		return true;
	}else{
		alert("初始化块"+block+"值失败，错误码为：" + CMt_32Ctrl.lErrorCode);
		return false;
	}
}
