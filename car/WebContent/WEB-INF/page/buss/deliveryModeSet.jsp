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
	<title>配送方式管理</title>
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
	<script src="js/jquery/jquery.ui.tabs.js"></script>
	<script src="js/common.js"></script>
	<script src="js/pubValidate.js"></script>
    <script src="js/pubValiPattern.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
    <script>

       var dmNameFlag = false;
       var logisticsComIdFlag = false;
       var enterCostFlag = false;
       var statusFlag = false;
       var  descFlag = false;
       var  cashOnDeliverySignFlag=false;
       //保存修改时候的名称
       var dmNameBak;
       $(document).ready(function(){
	       var method = document.getElementById("method"); 
	       if(method.value=='checkUI'){
               setInputDisabled();
               $("#descr").attr("readonly","true");
               $("#descr").css({"background-color":"#F0F0F0","color":"#6D6D6D"});
               $("#backButton").attr("disabled",false);
	        }
            dmNameBak =  $("#dmName").val();
       });

       function dmNameBlur(obj){
    	   var type = ["isNull"];  
           var errorMsg = ["配送方式名称不能为空!"];
           dmNameFlag = showErrorMsg(obj,type,errorMsg,"dmNameErrorMsg","dmNameErrorMsg");
           
           var dmName = $("#dmName").val();
           if(dmNameBak==dmName){
           		dmNameFlag = true;
           		return;
           }
           var url = "buss/deliverymode!checkName";
           var params = {
               "deliveryModeDTO.dmName":dmName
           };
           $.ajax({
               url : url,
               data : params,
               async:false,
               dataType : "json",
               type : "POST",
               cache : false,
               error:function(errText){
                  alert("ajax加载数据异常!请联系管理员");
               },
               success:function(data){
                    if(data.flag){
                    	dmNameFlag = false;
                       pubErrorShow($("#dmNameErrorMsg"),"该配送方式名称已存在!");
                    }else{
                       dmNameFlag = true;
                       $("#dmNameErrorMsg").hide();
                    }
                    
               }
               
           });
      }

       function logisticsComIdBlur(obj){
             var type = ["isNull"];
             var errorMsg = ["物流公司不能为空!"];
             logisticsComIdFlag = showErrorMsg(obj,type,errorMsg,"logisticsComIdErrorMsg","logisticsComIdErrorMsg");
      }
      
      function enterCostBlur(obj){
      	  var type = ["isNull"];
          var errorMsg = ["报价费用不能为空!"];
          enterCostFlag = showErrorMsg(obj,type,errorMsg,"enterCostErrorMsg","enterCostErrorMsg");
          
          if(obj.value>99.9999){
          	enterCostFlag=false;
          	pubErrorShow($("#enterCostErrorMsg"),"报价费用不能大于99.9999");
          	return;
          }
           if(obj.value<-9.9999){
          	enterCostFlag=false;
          	pubErrorShow($("#enterCostErrorMsg"),"报价费用不能小于-9.9999");
          	return;
          }
          
          
      }
      
      function statusBlur(obj){
      	 var type = ["isSelectNull"];
         var errorMsg = ["请选择状态!"];
         statusFlag = showErrorMsg(obj,type,errorMsg,"statusErrorMsg","statusErrorMsg");
      }
      
      function openSelectWin(){
		   $("#logistName").val("");
           $("#logistId").val("");
      		 // 打开对话框   
			$("#dialog-confirm").dialog({
				resizable: true,
				top: 370,
				height:400,
				width:650,
				modal: true,
				buttons: {
					'取消': function() {
						$(this).dialog('close');
					},
					'确定': function(){
				      	var value=$("input[name='selected']:checked").val();
				      	var id=value.split("_")[0];
				      	var name=value.split("_")[1];
				      	$("#logisticsComId").val(id);
				      	$("#logisticsComName").val(name);
				      	$("#logisticsComIdErrorMsg").hide();
				      	$("#dialog-confirm").dialog("close"); 
				      }
				}
			});    
      		query(${logisticsDTO.page });
      }
      
      function query(page){
      		var logistName = $("#logistName").val();
      		var logistId= $("#logistId").val();
      		var params = {
      			"logisticsDTO.logistName" : logistName,
				"logisticsDTO.logistId" : logistId,
				"logisticsDTO.status":1,
		        "orderProperty" : $("#orderProperty").val(),
		        "orderDirection" : $("#orderDirection").val(),
		        "logisticsDTO.page" :page
		  	};
		    var tbId = "listTable";
	   		var tb = $("#"+tbId);
	    	var thLength = $("#"+tbId+" th").length;
	   		var first = tb.find('tr:eq(0)');  
		  	var actionUrl = "buss/deliverymode!getLogisticsList"; 
		    $.ajax( {   
		        url : actionUrl,   
		        data : params,  
		        async:false, 
		        dataType : "json",   
		        cache : false,   
		        type : "POST",
		        error : function(textStatus, errorThrown) {   
		    		alert("系统ajax交互错误!");	    
		        }, 
		        success : function(data, textStatus) { 
					var consoleDlg = $("#dialog-confirm"); 
					consoleDlg.attr("title","物流选择");
					$("#pageNav").html(data.pagehtml);
		        	$("#otherhtml").html(data.otherhtml);
		        	first.nextAll().remove();
		        	var list = data.list;
		        	if (list.length>0){
		        		for(var i=0;i<list.length;i++) {
		        			var tr = $("<tr></tr>");
		        			var html = "";
		        			for(var j=0;j<list[i].length;j++){
		        				html += "<td >"+list[i][j]+"</td>";
		        			}
		              		tr.html(html);
		              		if (i%2==1) tr.addClass("ghhs");
		              		tb.append(tr);
		            	}
		        	} else {
		        			var tr = $("<tr></tr>");
		              		var html = "<td colspan='"+thLength+"' >没有找到相关数据</td>";		              		
		              		tr.html(html);
		              		tb.append(tr);
		        	}
		        }   
		    });
      }
      
       function check(){
       	  dmNameBlur(getEle("dmName"));
    	  logisticsComIdBlur(getEle("logisticsComName"));
    	  enterCostBlur(getEle("enterCost"));
    	  statusBlur(getEle("status"));
    	  checkDescrLenght(getEle("descr"));
    	  cashOnDeliverySignChange(getEle("cashOnDeliverySign"));
    	  if(!dmNameFlag || !logisticsComIdFlag || !enterCostFlag || !statusFlag || !descFlag ||!cashOnDeliverySignFlag){
    	  	 alert("信息填写有误，请按提示信息重新填写!");
    	  	 return false;
    	  }
    	  return true;
       }

       function getEle(ele){
           var element = document.getElementById(ele);
           return element;
       }
       function toCharLength(str){
			return str.replace(/[^x00-xff]/mg,"JJ").length;
		}
	   function checkDescrLenght(obj){
	   		if(toCharLength(obj.value)>255){
				descFlag = false
				pubErrorShow($("#descErrorMsg"),"描述不能大于120个汉字");
				return;
			}
			descFlag = true;
			pubErrorShow($("#descErrorMsg"),"");
	   }
	   
	   function cashOnDeliverySignChange(obj){
	      var type = ["isSelectNull"];
          var errorMsg = ["请选择是否支持货到付款!"];
          cashOnDeliverySignFlag = showErrorMsg(obj,type,errorMsg,"cashOnDeliverySignErrorMsg","cashOnDeliverySignErrorMsg");
	   }
	   
	   function chkPrice(obj){ 
			obj.value = obj.value.replace(/[^\d.]/g,""); 
			//必须保证第一位为数字而不是. 
			obj.value = obj.value.replace(/^\./g,""); 
			//保证只有出现一个.而没有多个. 
			obj.value = obj.value.replace(/\.{2,}/g,"."); 
			//保证.只出现一次，而不能出现两次以上 
			obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); 
		}
    </script>
</head>
<body>
    
	<div class="Position">
		当前位置是：HOME &gt;&gt; 业务参数 &gt;&gt; 配送方式设置
	</div>
	<s:form action="buss/deliverymode" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
		<s:hidden name="method" id="method"></s:hidden>
		<s:hidden name="deliveryModeDTO.dmId" id="dmId"></s:hidden>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTable">
		    <tr>
		      	<th align="right"><span class="Color5">* </span><strong>配送方式名称：</strong></th>
		        <td><s:textfield name="deliveryModeDTO.dmName" id="dmName" maxlength="15" onblur="dmNameBlur(this);" cssClass="formInput"/> <label id="dmNameErrorMsg" style="display: none;"></label></td>
		   	</tr>
		   	<tr>
		   		<th align="right"><span class="Color5">* </span><strong>物流公司：</strong></th>
		        <td>
		        <s:hidden name="deliveryModeDTO.logisticsComId" id="logisticsComId"/>
		        <s:textfield name="deliveryModeDTO.logisticsComName" id="logisticsComName"  readonly="true" onblur="logisticsComIdBlur(this);"  cssClass="formInput" />
		        <input id="selectInput" type="button" class="formButton" value="选择" onclick="openSelectWin();"/>
		        <label id="logisticsComIdErrorMsg" style="display: none;"></label></td>
		   	</tr>
			<tr>
		      	<th align="right"><span class="Color5">* </span><strong>状态：</strong></th>
		        <td><s:select id="status" name="deliveryModeDTO.status" list="#request.status" headerKey="-1" headerValue="请选择" listKey="key" listValue="value" onchange="statusBlur(this);" cssClass="formSelect"/><label id="statusErrorMsg" style="display: none;"></label></td>
		      	
		   	</tr>

			<tr>
		      	<th align="right"><span class="Color5">* </span><strong>报价费用：</strong></th>
		        <td><s:textfield name="deliveryModeDTO.enterCost"  onblur="enterCostBlur(this);" id="enterCost" maxlength="7" onkeyup="chkPrice(this);"  cssClass="formInput"/>%<label id="enterCostErrorMsg" style="display: none;"></label>
		        	<br><font size="2" color="gray" >注：保价费用相对于运费，如：运费100元，保价费用为1%，则保价费用计算得出：1元</font>
		        </td>
		      
		 	</tr>
		 	<tr>
		      	<th align="right"><span class="Color5">* </span><strong>支持货到付款：</strong></th>
		        <td><s:select id="cashOnDeliverySign" name="deliveryModeDTO.cashOnDeliverySign" list="#request.signs"  headerKey="-1" headerValue="请选择" listKey="key" listValue="value" onchange="cashOnDeliverySignChange(this);" cssClass="formSelect"/><label id="cashOnDeliverySignErrorMsg" style="display: none;"></label>
		        <label id="psPwdErrorMsg" style="display: none;"></label></td>
		        
		  	</tr>
			<tr>
		      	<th align="right"><strong>描述：</strong></th><td><s:textarea  name="deliveryModeDTO.descr" id="descr" maxLength="255"  cssClass="formTextarea" cols="80" rows="5" onblur="checkDescrLenght(this)"></s:textarea><label id="descErrorMsg" style="display: none;"></label></td>
		   	</tr>
	 	</table>
	 	<div class="formTableBottom">
	 	<s:if test="method=='addSave'">
			<my:permission key='sy-6600-01' value='支付方式添加'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:if>
		<s:elseif test="method=='editSave'">
		    <my:permission key='sy-6600-03' value='支付方式修改'>
		 		<input id="submitInput" type="submit" class="formButton" value="保 存" onclick="return check();"/>
		 	</my:permission>
		</s:elseif>
		<s:else>
		</s:else>
		<input type="button" id="backButton" class="formButton" value="返 回" onclick="go('buss/deliverymode!list')"/>
		</div>
	 	</s:form>

	 	<div id="dialog-confirm" style="display: none;" title="物流选择">
			<table class="searchTable" cellpadding="0" cellspacing="0">
				<tr>
				    <td>编号:</td>
					<td><s:textfield id="logistId" name="logisticsDTO.logistId" cssClass="formInput" maxlength="20" theme="simple"/></td>
					<td>物流公司:</td>
					<td><s:textfield id="logistName" name="logisticsDTO.logistName" cssClass="formInput" maxlength="20" theme="simple"/></td>
		        	<td><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
				</tr>
			</table>
			<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0">
				<tr>
					<th width="3%">选择</th>
					<th width="6%">物流编号</th>
					<th width="10%">物流公司</th>
					<th width="10%">官方地址</th>
					<th width="10%">创建时间</th>
				</tr>
			</table>
			<div class="listBottom">
				<div class="Fr" id="pageNav">
					<s:property value="pageHTML" escape="false"/>
				</div>
			</div>
		</div>
		
	 </body> 
</html>