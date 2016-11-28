<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<script src="js/ctCardUtil.js"></script>
	<script src="js/ajax_alert.js"></script>
	<script type="text/javascript">
	//写卡
	function writePan(track2){
		card.OperType="1";
		card.Track2 = track2;
		//card.Track3 = "991988999900000000010=1561560000999939300003000000090909000001=0235139?0";
		while(card.isOK != '0') {
			if (card.isOK == '') {
				card.Msr_Oper();
			} else {
				if(confirm("刷卡出错，是否重刷？")) {
					card.Msr_Oper();
				} else {
					stopFlag = false;
					//transUtil.showMessage("卡号取消写磁，退出处理......");
					//resumeFail(pan);
					alert("取消写磁，退出处理！");
					break;
				}
			}
		}
		if (card.isOK == '0'){
			card.isOK = "";
			alert("卡号写磁成功！");
		}
		ajax_end();
	}
	</script>
</head>
<body>
<!-- <input type="button" name="refCard" id="refCard" value="刷卡 " class="smlButton" onclick="readPan(document.getElementById('startNo'));" /> -->
<OBJECT ID='card' CLASSID='CLSID:87C3BE0A-6D8B-4B9E-9708-2556DF2115B2' height='0' width='0' CODEBASE='<%=basePath%>tools/MSRDriver.CAB#version=1,4,0,0'/>
</body>