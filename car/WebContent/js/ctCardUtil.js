$(document).ready(function(){
	initCard("LKE2300","COM1");
});
/*
交易页面辅助JS
*/
function initCard(MsrType,ComPort){
	
	card.MsrType = MsrType;
	card.ComPort = ComPort;
	//card.ComPort="1";		        //串口号 1-9   
	//card.ComBaud = "9600,O,7,1";    //波特率        
	//card.TimeOut="30";               //超时时间 秒
}
//刷卡
var panField = null;
var trk2Field = null;
function readPan(pan,trk2){
	ajax_start("请刷卡...");
	if (card.MsrType == "" || card.ComPort == "") {
		alert("请设置读卡器参数");
		return;
	}
	panField = pan;
	trk2Field = trk2;
	card.OperType="0";
	var timer = setTimeout("toReadPan()",1000);
	panField.value="";
}

function toReadPan(){
	
	card.Msr_Oper();
	if(card.isOK=='0'){
		panField.value=card.pan;
		if(typeof(trk2Field)!='undefined' && trk2Field!=null){
			trk2Field.value = card.Track2;
		}
		panField.focus();
		ajax_start("读卡成功！");
		setTimeout("ajax_end()",1000);
	}else{
		panField.value="";
		if(typeof(trk2Field)!='undefined' && trk2Field!=null)
			trk2Field.value = "";
		ajax_end();
		alert("读卡失败，请重试...");
	}
}


//读卡器刷卡
var keyCode="";
var readCardFlag = false;
function onkeyupReadPan(pan,panHid,trk2){
	var panLength = pan.value.length;
	if(pan.value.substring(panLength-1,panLength)==";" || pan.value.substring(panLength-1,panLength)=="；"){
		readCardFlag = true;
		pan.value = "";
	}
	//alert(keyCode);
	if(readCardFlag==true){
		panHid.value=panHid.value+pan.value;
		
		pan.value="";
		//alert(keyCode);
		if(event.keyCode==13 && keyCode==16){
			//alert(event.keyCode);
			var cardLeng = panHid.value.indexOf("=");
			var endIdx = panHid.value.indexOf("?");
			pan.value=panHid.value.substring(0,cardLeng);
			if(typeof(trk2)!='undefined'){
				trk2.value=panHid.value.substring(0,endIdx);
			}
			panHid.value="";
			readCardFlag = false;
		}
		keyCode = event.keyCode;
	}
}

