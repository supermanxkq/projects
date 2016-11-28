<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>批量售卡管理</title>
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
	<script src="js/pubValiPattern.js"></script>
	<script src="js/pubValidate.js"></script>
	<script src="js/base/member.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<style type="text/css">
	.tdh{
	background-color: #5b5b5b;
	border-bottom-color:#CCC solid 1px 
	}
	</style>
	<script type="text/javascript">
	//根据卡BIN来加载面值和起始卡号
	function changeCardBin(){
		var binId = $("#binId").val();
		if(binId!=-1){
		$("#binIdMsg").text("");
		var params = {
				"saleOrderDTO.binId" : binId
		    }; 
		 var actionUrl = "order/batchSale!changeCardBin";
		 $.ajax({   
		        url : actionUrl,   
		        data : params,   
		        dataType : "json",
		        cache : false, 
		        type : "POST",
		        error : function(textStatus, errorThrown) {   
		    		alert("系统ajax交互错误!");  
		        },
		        success : function(data, textStatus) {
		            if(data.flag){
			            $("#initAmt").val(data.obj.initAmt);
			            $("#beginCardNo").val(data.obj.beginCardNo);
			            $("#binIdMsg").text("");
			            return true;
			            //$("#binIdError").hide();
				        }else{
			        	$("#binIdMsg").text("该卡BIN下没有可以出售的卡号,或该卡Bin没有设置订单审核额度！");
			            //$("#binIdError").addClass("errorMsg");
			            //$("#binIdError").show();
			            $("#initAmt").val("");
			            $("#beginCardNo").val("");
			            return false;
		        }
		      }   
		    });
	}else{
		$("#binIdMsg").text("请选择卡BIN!");
		return false;
        //$("#binIdError").addClass("errorMsg");
        //$("#binIdError").show();
		}
		};
	//根据卡等级来加载开卡积分
	function changeCardLevel(){
		var levelId = $("#levelId").val();
		if(levelId!=-1){
			
			$("#levelIdMsg").text("");
		    var params = {
				"saleOrderDTO.levelId" : levelId
		    }; 
		 var actionUrl = "order/batchSale!changeCardLevel";
		 $.ajax({   
		        url : actionUrl,   
		        data : params,   
		        dataType : "json",
		        cache : false, 
		        type : "POST",
		        error : function(textStatus, errorThrown) {   
		    		alert("系统ajax交互错误!");  
		        },
		        success : function(data, textStatus) {
		            if(data.flag){
			            $("#newPoint").val(data.obj.newPoint);
			            return true;
				        }
		        }
		    });
	}else{
	   	$("#levelIdMsg").text("请选择卡等级!");
    	return false;
    	
        //$("#levelIdError").addClass("errorMsg");
        //$("#levelIdError").show();
    }
		};
	//根据支付方式来确定是否激活卡号
	function changePaidType(){
		var paidType = $("#paidType").val();
		var flag = $('[name="saleOrderDTO.active"]:checked').val();
		if(paidType=="0"){
			$('[name="saleOrderDTO.active"]:checked').val(1);
			}else{
				$('[name="saleOrderDTO.active"]:checked').val(0);
		}
	};
	//验证是否面值
		function checkInitAmt(aa){
			var method = $("#method").val;
			var initAmt = $("#initAmt").val();
			var binId = $("#binId").val();
			
			if(initAmt.length == 0){
				$("#initAmtError").text("卡面值不能为空!");
				return false;
				}
			//json数据传输
			var params = {
				"saleOrderDTO.binId" : binId,
				"saleOrderDTO.initAmt" : initAmt
		    }; 
		    if(binId!=-1){
			if(method=="editSave"){
				var initAmt = $("#initAmt").val();
				var cardQty = $("#cardQty").val();
				var orderAmt = cardQty*initAmt;
				$("#orderAmt").val(orderAmt);
				setInputDisabled();
				}else{
					
				    var actionUrl = "order/batchSale!checkInitAmt";
				    $.ajax({   
				        url : actionUrl,   
				        data : params,   
				        dataType : "json",
				        cache : false, 
				        type : "POST",
				        error : function(textStatus, errorThrown) {   
				    		alert("系统ajax交互错误!");  
				        },
				        success : function(data) {
				            if (!data.flag) {
				            	 $("#initAmtError").text("超过卡上限额度");
					            // $("#initAmtError").addClass("errorMsg");
					            // $("#initAmtError").show();
				               return false;
				           	}else{
				           		//$("#initAmtError").hide();
					           	}
				        }
				    });
			}
		}else{
			$("#initAmtError").text("请选择卡BIN!");
	        //$("#initAmtError").addClass("errorMsg");
	        //$("#initAmtError").show();
			}
	};
			var addCardNoFlag = false;
			function addCardNo(){
				var beginCardNo = $("#beginCardNo").val();
				var cardQty = $("#cardQty").val();
				var binId = $("#binId").val();
				var levelId = $("#levelId").val();
				var method = $("#method").val();
				if(beginCardNo.length==0||levelId==-1||cardQty==0||binId==-1){
					alert("不存在可以添加的卡号！");
					return false;
					}else{
				addCardNoFlag = true;
				var tb = $("#cardNoTb");
				var saleOrderId = $("#saleOrderId").val();
				//json数据传输
			    if(method == "editSave"){
			    	var actionUrl = "order/batchSale!editCardNo";
			    	var params = {
						"saleOrderDTO.saleOrderId" : saleOrderId
			    		};
				    }else{
				    	if(!payMenFalg&&!cardQytFlag&&!paidAmtFalg&&binId==-1&&levelId==-1){
							alert("信息填写有误,不能添加卡号!!!!!");
							return false;
							}
					    var actionUrl = "order/batchSale!addCardNo";
					    var params = {
								"saleOrderDTO.binId" : binId,
								"saleOrderDTO.cardQty" : cardQty
					    	}; 
					}
			    $.ajax({ 
			    	async: false,  
			        url : actionUrl,   
			        data : params,   
			        dataType : "json",
			        cache : false, 
			        type : "POST",
			        error : function(textStatus, errorThrown) {   
			    		alert("系统ajax交互错误!");  
			        },
			        success : function(data) {
				        	if (!data.flag) {
				            	 alert("卡数量不足！！");
				               return false;
					    }else{
				           		$("#cardNo").val(data.obj.cardNo);
				        		for(var i=0;i<data.obj.cardNo.length+1;i=i+3) {
		        					var tr = $("<tr></tr>");
		        					var html = "";
		        					if(data.obj.cardNo[i]!=null){
		        						html += "<td  align='center' >"+(i+1)+"</td>";
			        					html += "<td  align='center' >"+data.obj.cardNo[i]+"</td>";
			        					}
		        					if(data.obj.cardNo[i+1]!=null){
		        						html += "<td  align='center' >"+(i+2)+"</td>";
			        					html += "<td  align='center' >"+data.obj.cardNo[i+1]+"</td>";
			        					}
		        					if(data.obj.cardNo[i+2]!=null){
		        						html += "<td  align='center' >"+(i+3)+"</td>";
			        					html += "<td  align='center' >"+data.obj.cardNo[i+2]+"</td>";
			        					}
		        					tr.html(html);
				          			tb.append(tr);
		        				}
				        		$("#suerAdd").attr("disabled","disabled");
			          			$("#cardNo").val(data.obj.cardNo);
				           	}
			        }
			    });
			}
		};
			
		var payMenFalg = false; 
		var cardQytFlag = false;
		var paidAmtFalg= false;
		
		
		function replaceToNumPoint(obj){
		   if(isNaN(obj.value)){
			 	obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符
			 	obj.value = obj.value.replace(/^[.]/g,"");  //验证第一个字符是数字而不是.
			 	obj.value = obj.value.replace(/[.]{2,}/g,""); //清除第一个点以外的点
			 	
			 }
	       }
		
		function setPayMenFlag(){
				var payMenName = $.trim($("#payMenName").val());
				if(payMenName.length==0){
					
					
					$("#payMenMsg").text("购买人名称不能为空!");
					return false;
				}else{
					$("#payMenMsg").text("");
					return true;
				}
				
			};
		function setcardQytFlag(){
		
			var cardQty = $("#cardQty").val();
				if(cardQty==""){
					$("#cardQytMsg").text("数量不能为空!");
					return false;
				}else{
					$("#cardQytMsg").text("");
					return true;
				}
			
		};
		function setpaidAmtMenFlag(){
			
			var paidAmt = $("#paidAmt").val();
				if(paidAmt==""){
					$("#paidAmtMsg").text("实际支付金额不能为空!");
					return false;
				}else{
					$("#paidAmtMsg").text("");
					return true;
				}
			
		};
		function check(){
		
			var cardQty = $("#cardQty").val();
			var telNo = $("#telNo").val();
			var paidAmt = $("#paidAmt").val();
			var binId = $("#binId").val();
			var levelId = $("#levelId").val();
			var initAmt = $("#initAmt").val();
			var descr = $("#descr").val();
			changeCardLevel();
			setcardQytFlag();	
			setpaidAmtMenFlag();
			setPayMenFlag();
			if(descr.length>255){
                  alert("机构描述输入字符过长，请重新输入!");
                  return false;
				}
			if(setcardQytFlag()&&setpaidAmtMenFlag()&&setPayMenFlag()){
		 		return true;
			}else{
				alert("没有添加卡号或信息填写有误!");
				return false;
			}
		}
		function setOrderAmt(obj){
			setcardQytFlag(obj);
			var initAmt = $("#initAmt").val();
			var cardQty = $("#cardQty").val();
			var orderAmt = cardQty*initAmt;
			$("#orderAmt").val(orderAmt);
			};
		$(document).ready(function (){
	       var method = document.getElementById("method"); 
            if(method.value=='editSave'){
            	addCardNo();
            	$("#binId").attr("disabled","disabled");
            	$("#binId").attr("readonly","readonly");
            	
            }
	        });
	</script>
  </head>
  <body>

  <div class="Position">
		当前位置是：HOME &gt;&gt;订单信息管理 &gt;&gt;批量售卡订单管理
	</div>
	<jsp:include page="/WEB-INF/page/member/member/memberHelp.jsp"></jsp:include>
	<s:form action="order/batchSale"  method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
	<s:hidden name="method" id="method"/>
	<s:hidden name="saleOrderDTO.saleOrderId" id = "saleOrderId"/>
	<s:hidden name="saleOrderDTO.operatorId" id = "operatorId"/>
	<s:hidden name="saleOrderDTO.cardNo" id = "cardNo"/>
	<s:hidden name="saleOrderDTO.createTime" id = "createTime"/>
	<s:hidden name="saleOrderDTO.orgId" id = "orgId"/>
	<s:if test="method=='editSave'">
		<s:hidden name="saleOrderDTO.binId" id = "binId"/>
	</s:if>
	<s:if test="#session.user_session.userLevel!=2">
		<div class="List_tit">批量售卡</div>
		<div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
				<tr>
			  		<th align="right" width="20%">卡BIN：</th>
			        <td width="30%">
				        <s:select name="saleOrderDTO.binId" id="binId" list="#request.cardBinValue" listKey="key"  listValue="value" headerKey="-1"  headerValue="--请选择--"   cssClass="formInput"  theme="simple" onchange="changeCardBin();"/>
				        <span class="Color5"><label id="binIdMsg"></label></span> 
				 		<label id="binIdError"></label>
			        </td>
			        <th align="right">卡等级：</th>
			 		<td>
			 		<s:select name="saleOrderDTO.levelId"  id="levelId" list="#request.cardLevelValue" listKey="key" listValue="value" headerKey="-1"  headerValue="--请选择--"   cssClass="formInput"  theme="simple" onchange="changeCardLevel();"/>
			 		<span class="Color5"><label id="levelIdMsg"></label></span> 
				 	<label id="levelIdError"></label>
			 		</td>
				</tr>
			 	<tr>
			 		<th align="right" width="20%">卡面值：</th>
			        <td width="30%">
			        <s:textfield name="saleOrderDTO.initAmt"  id="initAmt"   maxlength="20" cssClass="formInput"  theme="simple"  onblur="checkInitAmt(this);"/>
			        <label id="initAmtMsg"></label>
				 	<label id="initAmtError"></label>
			        </td>
			 		<th align="right" width="20%">卡积分：</th>
			        <td><s:textfield name="saleOrderDTO.newPoint" id="newPoint"  cssClass="formInput" theme="simple" readonly="true"/></td>
			  	</tr>
			  	<tr>
			  		<th align="right">起始卡号：</th>
			        <td>
				        <s:textfield name="saleOrderDTO.beginCardNo" id="beginCardNo" readonly="true" cssClass="formInput" theme="simple"/>
			        </td>
			        <th align="right"><span class="Color5">* </span>数量：</th>
			        <td><s:textfield name="saleOrderDTO.cardQty" id="cardQty" onchange="setcardQytFlag();" onblur="setOrderAmt();" cssClass="formInput" theme="simple" />
			         <span class="Color5"><label id="cardQytMsg"></label></span>
			         <label id="cardQytError"></label>
			        </td>
			  	</tr>
			  	<tr>
			  		<th align="right">订单金额：</th>
			        <td><s:textfield name="saleOrderDTO.orderAmt" id="orderAmt"  maxlength="4" onkeypress="return onlyNum(this);" onkeyup="replaceToNum(this);" cssClass="formInput" theme="simple" readonly="true" /></td>
			        <th align="right"><span class="Color5">* </span>实际支付金额：</th>
			        <td><s:textfield name="saleOrderDTO.paidAmt" id="paidAmt"  maxlength="8" onkeyup="replaceToNumPoint(this);"  cssClass="formInput" theme="simple" onblur="setpaidAmtMenFlag();"/>
			         <span class="Color5"><label id="paidAmtMsg"></label></span>
			         <label id="paidAmtError"></label>
			        </td>
			  	</tr>
			  	<tr>
			  		<th align="right">支付方式：</th>
			        <td>
			        	<s:select name="saleOrderDTO.paidType" id="paidType"   list="#request.payTypeValue" listKey="key" listValue="value"  cssClass="formInput" theme="simple" onchange="changePaidType();"/>
			        </td>
			        <th align="right"><span class="Color5">* </span>购卡人：</th>
			        <td>
			        <s:textfield name="saleOrderDTO.payMenName" id="payMenName" readonly="true" cssClass="formInput" theme="simple" onblur="setPayMenFlag();"/>
			        <s:hidden name="saleOrderDTO.payMenId" id="payMenId"/>
			        <img alt="查找会员" src="images/search.gif" style="cursor:pointer;" onclick="ajaxParentMemId();"/>
			        <span class="Color5"><label id="payMenMsg" ></label></span> 
		 			<label id="payMenError" ></label>
			        </td>
			  	</tr>
			  	<tr>
			  		<th align="right">是否激活：</th>
			        <td>
			        <s:radio  name="saleOrderDTO.activateSign"  id="activateSign" list="#request.activateValue" listKey="key" listValue="value" value="1" theme="simple" />
			        </td>
			        <th align="right"><span class="Color5"></span>手机号码：</th>
			        <td>
			        <s:textfield name="saleOrderDTO.telNo" id="telNo"  cssClass="formInput" theme="simple" readonly="true" maxlength="11"/>
			        <label id="telNosMsg" style="display: none;"></label> 
		 			<label id="telNoError" style="display: none;"></label>
			        </td>
			  	</tr>
			  		<tr>
			  		<th align="right">是否实名制：</th>
			        <td>
			        <s:radio  name="saleOrderDTO.realNameSign"  id="realNameSign" list="#request.realNameSign" listKey="key" listValue="value" value="1" theme="simple" />
			        </td>
			        <s:if test="method=='addSave'">
				  		<th align="right">点击添加按钮，添加所要出售的卡号：</th>
				  		<td>
					        <input type="button" id = "suerAdd" class="formButton" value="确认添加" onclick="addCardNo()"/>
					    </td>
				    </s:if>
			  	</tr>
			  	<tr>
				    <th align="right">订单描述：</th>
			        <td colspan="3">
				        <s:textarea name="saleOrderDTO.descr" id="descr" rows="2" cols="78"/>
			        </td>
			  	</tr>
			</table>
			</div>
		<div class="List_tit">卡片信息</div>
		<div>
			<table id="cardNoTb" width="96%" class="formTable" cellpadding="0" cellspacing="0">
				<tr>
			  		<th align="center" class="tdh">序号</th>
			  		<th align="center" class="tdh">卡号</th>
			  		<th align="center" class="tdh">序号</th>
			  		<th align="center" class="tdh">卡号</th>
			  		<th align="center" class="tdh">序号</th>
			  		<th align="center" class="tdh">卡号</th>
			  	</tr>
			</table>
			</div>
	</s:if>
	 <div class="formTableBottom">
	 	<s:if test="method=='addSave'">
			<my:permission key='sy-4102-02' value='批量售卡添加'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:if>
		<s:else>
			<my:permission key='sy-4102-04' value='批量售卡修改'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:else>
		<input type="button" class="formButton" value="返 回" onclick="go('order/saleorders!list')"/>
	 </div>
	 </s:form>
</body>
</html>