<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<base href="<%=basePath%>" />
	<script src="js/m1CardUtil.js"></script>
	<script type="text/javascript">
	
	//读M1卡
	function readM1Card(textId){
		if(typeof(CMt_32Ctrl) == 'undefined'){
			$("body").prepend("<OBJECT classid=clsid:BBC3DC17-8DB4-4FEA-90CF-5458101762AA width=0 height=0 align='center' codebase='<%=basePath%>tools/mt_32.CAB#version=1,0,0,1' id='CMt_32Ctrl' HSPACE=0 VSPACE=0></OBJECT>");
		}
		
		try{
			if(OpenDevice()){
				if(RfCard()){
					var actionUrl = "common/rfM1Card!getSeriNoPwd";
				    $.ajax( {
				        url : actionUrl,
				        //data : params,
				        dataType : "json",
				        cache : false,
				        type : "POST",
				        async : false,
				        error : function(textStatus, errorThrown) {
				          	alert("系统ajax交互错误!");
				        },
				        success : function(data, textStatus) {
				        	//alert("读序列号密码"+data.msgResult);
				        	//读序列号认证
				        	if(RfAuthenticationKey(0,4,data.msgResult)){
				        		//读序列号
				        		var seriNo = RfRead(4);
				        		//alert("序列号"+seriNo);
				        		var params = {
			        		   		"seriNo" : seriNo
			        		    };
			        		    var actionUrl = "common/rfM1Card!getReadCardNoPwd";
			        		    $.ajax( {
			        		        url : actionUrl,
			        		        data : params,
			        		        dataType : "json",
			        		        cache : false,
			        		        type : "POST",
			        		        async : false,
			        		        error : function(textStatus, errorThrown) {
			        		          	alert("系统ajax交互错误!");
			        		        },
			        		        success : function(data, textStatus) {
			        		            if(data.ajaxResult == "ajaxsuccess") {          
			        		            	//alert("读卡号密码"+data.msgResult);
				       			        	//读卡号认证
				       			        	if(RfAuthenticationKey(0,8,data.msgResult)){
				       			        		//读卡号
				       			        		var cardNo = RfRead(8);
				    			        		//alert("卡号"+cardNo.substring(0,16));
				    			        		cardNo = cardNo.substring(0,16);
				    			        		
								        		var params = {
							        		   		"cardNo" : cardNo
							        		    };
							        		    var actionUrl = "common/rfM1Card!getCardNoView";
							        		    $.ajax( {
							        		        url : actionUrl,
							        		        data : params,
							        		        dataType : "json",
							        		        cache : false,
							        		        type : "POST",
							        		        async : false,
							        		        error : function(textStatus, errorThrown) {
							        		          	alert("系统ajax交互错误!");
							        		        },
							        		        success : function(data, textStatus) {
							        		            if(data.ajaxResult == "ajaxsuccess") {          
							    			        		$("#"+textId).val(data.msgResult);
							    			        		$("#"+textId).focus();
							    			        		devBeep();
							        		            } else if(data.ajaxResult == "ajaxfailure") {             
							        		                alert(data.msgResult);
							        		            }
							        		        }
							        		    });
				       			        	}else{
								        		alert("认证失败!");
								        	}
			        		            } else if(data.ajaxResult == "ajaxfailure") {             
			        		                alert(data.msgResult);
			        		            }
			        		        }
			        		    });
				        	}else{
				        		alert("认证失败!");
				        	}
				        }
				    });
				}
			}
		}catch (e){
			//alert(e.name + ": " + e.message);
		}finally{
			CloseDevice();
		}
	}
	
	//写M1卡
	function writeM1Card(track2){
		if(typeof(CMt_32Ctrl) == 'undefined'){
			$("body").prepend("<OBJECT classid=clsid:BBC3DC17-8DB4-4FEA-90CF-5458101762AA width=0 height=0 align='center' codebase='<%=basePath%>tools/mt_32.CAB#version=1,0,0,1' id='CMt_32Ctrl' HSPACE=0 VSPACE=0></OBJECT>");
		}
		try{
			if(OpenDevice()){
				if(RfCard()){
					var actionUrl = "common/rfM1Card!getWriteCardNoPwd";
				    $.ajax( {
				        url : actionUrl,
				        //data : params,
				        dataType : "json",
				        cache : false,
				        type : "POST",
				        async : false,
				        error : function(textStatus, errorThrown) {
				          	alert("系统ajax交互错误!");
				        },
				        success : function(data, textStatus) {
				        	//alert(data.msgResult);
				        	//写卡号认证
				       		var flag = RfAuthenticationKey(1,8,data.msgResult);
				       		if(flag){
				       			flag = RfWrite(8,track2);
				       			if(flag){
				       				devBeep();
				       				alert("写卡成功");
				       			}
				       		}else{
				        		alert("认证失败!");
				        	}
				       		while(!flag){
				       			if(confirm("刷卡出错，是否重刷？")) {
				       				var flag = RfAuthenticationKey(1,8,data.msgResult);
						       		if(flag){
						       			flag = RfWrite(8,track2);
						       			if(flag){
						       				devBeep();
						       				alert("写卡成功");
						       			}
						       		}else{
						        		alert("认证失败!");
						        	}
				       			}else{
				       				alert("取消写卡，退出处理！");
									break;
				       			}
				       		}
				        }
				    });
				}
			}
		}catch (e){
			//alert(e.name + ": " + e.message);
		}finally{
			ajax_end();
			CloseDevice();
		}
	}
	
	//写序列号卡
	function writeSeriNo(){
		if(typeof(CMt_32Ctrl) == 'undefined'){
			$("body").prepend("<OBJECT classid=clsid:BBC3DC17-8DB4-4FEA-90CF-5458101762AA width=0 height=0 align='center' codebase='<%=basePath%>tools/mt_32.CAB#version=1,0,0,1' id='CMt_32Ctrl' HSPACE=0 VSPACE=0></OBJECT>");
		}
		try{
			if(OpenDevice()){
				if(RfCard()){
					
		        	//alert(data.msgResult);
		        	//写卡号认证
		       		var flag = RfAuthenticationKey(1,4,"0698010D9BA6");
		       		if(flag){
		       			flag = RfWrite(4,"12000800000000010000000000000000");
		       			if(flag){
		       				devBeep();
		       				alert("写序列号成功");
		       			}
		       		}
				}
			}
		}catch (e){
			//alert(e.name + ": " + e.message);
		}finally{
			ajax_end();
			CloseDevice();
		}
	}
	
	</script>
</head>
<body>
<input type="hidden" name="Version" id="Account" maxlength="100" size="20" /> 
<input type="hidden" name="Serial" id="Account" maxlength="100" size="20"/>
<input type="hidden" name="DeviceHandle" id="Account" maxlength="100" size="5" value="-1"/> 
<input type="hidden" name="M1uid" id="Account" maxlength="10" size="10"/> 
</body>