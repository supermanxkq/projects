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
		<title>卡管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<link rel="shortcut icon" href="<%=basePath%>favicon.ico"
			type="image/x-icon" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script src="js/jquery-1.4.2.min.js"></script>
		<link href="js/jquery/css/jquery.ui.all.css" rel="stylesheet"
			type="text/css" />
		<script src="js/jquery/jquery.ui.core.js"></script>
		<script src="js/jquery/jquery.ui.widget.js"></script>
		<script src="js/jquery/jquery.ui.mouse.js"></script>
		<script src="js/jquery/jquery.ui.button.js"></script>
		<script src="js/jquery/jquery.ui.draggable.js"></script>
		<script src="js/jquery/jquery.ui.position.js"></script>
		<script src="js/jquery/jquery.ui.resizable.js"></script>
		<script src="js/jquery/jquery.ui.dialog.js"></script>
		<script type='text/javascript' src='js/jquery.autocomplete.js'></script>
		<link rel="stylesheet" type="text/css"
			href="js/jquery.autocomplete.css" />
		<script src="js/common.js"></script>
		<script src="js/pubValiPattern.js"></script>
		<script language="javascript" type="text/javascript" defer="defer"
			src="<%=basePath%>js/datepicker/WdatePicker.js"></script>

		<script type="text/javascript">
		 var dataBack;
		 var queryCardNo = function(){
			var cardNo = $("#cardNo").val();
			if (cardNo==''){
				$("#cardStatusValue").html("卡号不能为空!");
		    	return false;
			}
			 
			var params = {
		        "lockDTO.cardViewNo" : cardNo
		    }; 
		   
			var actionUrl = "card/cardlock!queryCardNo";   
		    $.ajax( {   
		        url : actionUrl,   
		        data : params,   
		        dataType : "json",
		        cache : false, 
		        type : "POST",
		        error : function(textStatus, errorThrown) {   
		    		alert("系统ajax交互错误11!");  
		        },
		        success : function(data, textStatus) {
		        //给dataBack赋值！
		        	dataBack=data;
		        	if(data.flag){
		        		 $("#cardStatusValue").html("卡号状态正常");
		        		 $("#flag").val("1");
		        	}else{
		           	    $("#cardStatusValue").html("此号未处于正常使用状态");
		                $("#flag").val("0");
		           	}
		        	 
		          /* else if (data.obj.status==0) {
		             	
		                $("#cardStatusValue").html("此号是总部入库状态，未处于正常使用状态");
		                $("#flag").val("0");
		           	}else if(data.obj.status==1){
		           	    $("#cardStatusValue").html("此号是总部出库状态，此号未处于正常使用状态");
		                $("#flag").val("0");
		           	}else if(data.obj.status==2){
		           	  $("#cardStatusValue").html("此号处于发卡机构入库，未处于正常使用状态");
		                $("#flag").val("0");
		           	}else if(data.obj.status==3){
		              $("#cardStatusValue").html("此号处于发卡机构出库，未处于正常使用状态");
		                $("#flag").val("0");
		           	}else if(data.obj.status==4){
		           	   $("#cardStatusValue").html("此号处于商户入库，未处于正常使用状态");
		                $("#flag").val("0");
		           	}else if(data.obj.status==5){
		           	  $("#cardStatusValue").html("此号处于商户出库，未处于正常使用状态");
		                $("#flag").val("0");
		           	}else if(data.obj.status==6){
		           	 $("#cardStatusValue").html("此号处于待激活，未处于正常使用状态");
		                $("#flag").val("0");
		           	}else if(data.obj.status==8){
		           	    $("#cardStatusValue").html("此号处于挂失状态，未处于正常使用状态");
		                $("#flag").val("0");
		           	}else if(data.obj.status==9){
		           	   $("#cardStatusValue").html("此号处于已补卡状态，未处于正常使用状态");
		                $("#flag").val("0");
		           	}else if(data.obj.status==10){
		            	$("#cardStatusValue").html("此号处于已换卡状态，未处于正常使用状态");
		                $("#flag").val("0");
		           	}else if(data.obj.status==11){
		           	    $("#cardStatusValue").html("此号处于已退卡状态，未处于正常使用状态");
		                $("#flag").val("0");
		           	}
		           	*/
		           	if(data.obj!=null && data.obj.holdmemId!="null"){
		              $("#merName").val(data.obj.merName);
		              $("#memIdNum").val(data.obj.memIdNum);
		            }else{
		             $("#merName").val("");
		             $("#memIdNum").val("");
		            }
		        }
		    });
		}
		
			var validateDescr= function(){
				var textareaLength=$("#descr").val().length;  
				$("#descrValue").html("");
				if(textareaLength>=200){ 
					$("#descrValue").html("200以内字符！");
					return false;
				}
				else{
					return true;
				} 
			}
		
		var check = function(){
			var cardNo = $("#cardNo").val();
			var organId = $("#organId").val();
			var lossTime = $("#lostTime").val();
			var lossAddress = $("#lostPlace").val();
			var flag = $("#flag").val();
			if (cardNo==''){
				alert("卡号不能为空!");
		    	$("#cardNo").focus();
		    	return false;
			}
			if(flag!="1"){
	         alert("当前卡状态不正确!");
			$("#cardNo").focus();
			return false;
			}
			
			if(dataBack.obj.holdmemId=="null"){
			    alert("会员信息不正确，请核对信息");
			    $("#cardNo").focus();
			    return false;
				}
			if(!(validateDescr())){
				return false;
			}
	}
</script>
	</head>
	<body>
		<div class="Position">
			当前位置是：HOME &gt;&gt; 卡信息管理 &gt;&gt; 挂失
		</div>
		<s:form action="card/cardlock!save" theme="simple">
			<input type="hidden" id="flag" />
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formTable">
				<tr>
					<th align="right" width="20%">
						<span class="Color5">* </span>卡号：
					</th>
					<td align="left" width="30%">
						<input type="text" name="lockDTO.cardViewNo" id="cardNo"
							maxlength="19" onkeyup="replaceToNum(this);" class="formInput2" onblur="queryCardNo();" />
						<s:hidden name="lockDTO.organId" id="organId"></s:hidden>
					</td>
					
					<th align="right" width="20%">
						当前卡状态：
					</th>
					<td align="left" width="30%" id="cardStatusValue"></td>
				</tr>
				
				<tr>
				  <th align="right">
						会员姓名：
					</th>
					<td align="left">
						<input name="lockDTO.merName" id="merName" 
							maxlength="20" class="formInput2" readonly />

					</td>
					<th align="right">
						会员证件号：
					</th>
					<td align="left">
						<input type="text" id="memIdNum" name="lockDTO.memIdNum"
							maxlength="20" class="formInput2" readonly/>
					</td>
				</tr>
				<tr>
					<th align="right">
						丢失时间：
					</th>
					<td align="left">
						<input name="lockDTO.lostTime" id="lostTime" class="Wdate"
							type="text"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'%y-%M-%d'})"
							readonly="readonly" />
							<!--  
							onFocus="WdatePicker({skin:'blue',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'',maxDate:'#F{$dp.$D(new Date())}'})"
							-->

					</td>
					<th align="right">
						丢失地点：
					</th>
					<td align="left">
						<input type="text" id="lostPlace" name="lockDTO.lostPlace"
							maxlength="20" class="formInput2" />
					</td>
				</tr>
				<tr>
					<th align="right">
						描述：
					</th>
					<td align="left" colspan="3">
						<textarea id="descr" name="lockDTO.descr" class="formTextarea"
							onblur="validateDescr();"></textarea>
						<span id="descrValue" class="Color5"></span>
					</td>
				</tr>
			</table>
			<div class="formTableBottom">
				<my:permission key='sy-2104-02' value='挂失添加'>
					<input type="submit" class="formButton" value="保 存"
						onclick="return check();" />
				</my:permission>
				<input type="button" class="formButton" value="返 回"
					onclick="go('card/cardlock!list')" />
			</div>
		</s:form>
	</body>
</html>