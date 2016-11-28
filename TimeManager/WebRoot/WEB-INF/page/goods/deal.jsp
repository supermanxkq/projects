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
	<title>商品信息管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<link rel="shortcut icon" href="<%=basePath%>favicon.ico" type="image/x-icon" />
	<link href="css/style.css" rel="stylesheet" type="text/css" />	
	<script src="js/jquery-1.8.2.min.js"></script>
	<script src="js/jquery.validate.js"></script>
	<script src="js/jquery.metadata.js"></script>
	<script src="js/additional-methods.min.js"></script>
	<script src="js/common.validate.js"></script>
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
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script src="js/datepicker/WdatePicker.js"></script>
	<script src="js/pubValidate.js"></script>
	<script type="text/javascript">
		$().ready(function() {
			$("#tabs").tabs();	
			$("#dealForm").validate({
				showErrors:function(errorMap, errorList) {  
		            var msg = "";  
		            $.each(errorList, function(i,v){  
		               msg += (v.message+"\r\n");  
		            });  
		            if(msg!="") alert(msg);  
		        },
		        onfocusout: false,
		        onsubmit:true
			});
		});
		
		// 确认处理
		function dealButton() {
			var illegal = $('#illegalSelectId').val();
			var illegalCase1 = $('#illegalCaseId1').val();
			var goodDealWayId = $('#goodDealWayId').val();
			var scoreId = $('#scoreId').val();
			if(illegal == -1) {
				alert("请选择违规类型!");
				return false;
			}
			if(illegalCase1 == -1) {
				alert("请选择触犯案例!");
				return false;
			}
			if(goodDealWayId == -1) {
				alert("请选择商品或店铺处理方式!");
				return false;
			}
			if($.trim(scoreId) == "" || isNaN(scoreId) || scoreId < 0 || scoreId > 48) {
				alert("请正确填写扣除分值!");
				return false;
			}
			
			return true;
		}
		
		function validateScore(obj) {
			
			if($.trim(obj.value) == "" || isNaN(obj.value) || obj.value < 0 || obj.value > 48){
				pubErrorShow($("#scoreErrorMsgId"), ["请填写正确的要扣除的分值!"]);
				return;
			} else {
				$("#scoreErrorMsgId").hide();
				$("#scoreErrorMsgId").html("");
			}
		}
		
		function changeIllegal() {
			var illegal = $('#illegalSelectId').val();
			$.ajax({
               url : "base/goods!getVioRegulByTypeId",
               data : {"unruleTypeDTO.unruleTypeId":illegal},
               async:false,
               dataType : "json",
               type : "GET",
               cache : false,
               error:function(errText){
                  alert("ajax加载数据异常!请联系管理员");
               },
               success:function(data){
               	    var list = data.list;
               	    $("#illegalCaseId1").empty();
              		$("#illegalCaseId1").append(" <option value='-1'>请选择...</option>");
               	    for(var i=0;i<list.length;i++){
               	    	var key = list[i][0];
               	    	var value = list[i][1];
           				$("#illegalCaseId1").append("<option value="+key+">"+value+"</option>");
               	    }
               }
           });
		}
		
	</script> 
</head>
<body>
	<div class="Position">
		当前位置是：基本设置 &gt;&gt; 商品管理 &gt;&gt; 商品信息管理
	</div>
	<div style="width:100%;text-align: center">
		<div id="tabs" style="width:1000px; margin-right: auto;margin-left: auto; height:800px;">
			<ul>
				<li><a href="#tabs-1">商品违规处理</a></li>
			</ul>
			<div id="tabs-1">
			<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
			<div align="left">
				 <fieldset style='margin:20px;padding-bottom:20px;padding-left:20px;'>
				    <legend>商品信息</legend>
				    <table style="text-align: left;width:100%;padding-left:20px;height:100px;">
				    	<tr style="padding-top:10px;"><td width="10%">商品编号：</td><td  width="200"><s:textfield name="goodsDTO.goodsId"  theme="simple" disabled="true"/></td><td width="10%">商品货号：</td><td width="50%"><s:textfield  name="goodsDTO.goodsItem"  theme="simple" disabled="true"/></td></tr>
				    	<tr style="padding-top:10px;"><td width="10%">商品名称：</td><td colspan="3"><s:textfield name="goodsDTO.goodsName"  theme="simple" disabled="true"  cssStyle="width:500px;"/></td></tr>
				    	<tr style="padding-top:10px;"><td width="10%">所属商户：</td><td><s:textfield name="goodsDTO.merId"  theme="simple" disabled="true"  /></td><td width="10%"></td><td></td></tr>
				    </table>
				 </fieldset>
			</div>
			<form id="dealForm" action="base/goods" method="POST">
				<s:hidden name="method" id="method"></s:hidden>
				<s:hidden name="platHandleDTO.goodsId" value="%{goodsDTO.goodsId}"></s:hidden>
				<div style="margin-top:20px;" align="left">
					<fieldset style='margin:20px;padding-bottom:20px;padding-left:20px;'>
					
					    <legend>处罚处理</legend>
					    <table style="text-align: left;width:100%;padding-left:20px;height:100px;">
					    	<tr style="padding-top:10px;">
						    	<td width="10%">违规类型：</td><td  width="25%">
						    	<s:select cssClass="formSelect" id="illegalSelectId" onchange="changeIllegal()" name="platHandleDTO.goodPunishType" list="#request.illegalType" listKey="key" listValue="value"  theme="simple" headerKey="-1" headerValue="请选择.."/></td>
						    	<td width="10%">触犯案例：</td><td width="20%">
						    		<s:select list="#request.case1" id="illegalCaseId1" cssClass="formSelect" listKey="unruleId" listValue="unruleWay"  name="platHandleDTO.vioRugleId"  theme="simple"  headerKey="-1" headerValue="请选择.."/>
						    	</td>
						    	<td width="10%">扣除分值：</td><td><s:textfield id="scoreId" name="platHandleDTO.deductScore" cssStyle="width:50px;" onblur="validateScore(this)" theme="simple" cssClass="{required:true,max:40,min:0,messages:{required:'请填写扣除分值!',max:'分值不能超过40分!',min:'分值不能小于0分!'}}"></s:textfield><label id="scoreErrorMsgId" style="display: none;"></label></td>
					    	</tr>
					    </table>
					 </fieldset>
				</div>
				<div style="margin-top:20px;" align="left">
					<fieldset style='margin:20px;padding-bottom:20px;padding-left:20px;'>
					    <legend>商品处理</legend>
					    <table style="text-align: left;width:100%;padding-left:20px;height:100px;">
					    	<tr style="padding-top:10px;"><td width="6%">商品以及店铺处理方式：</td><td  width="25%">
					    	<s:select name="platHandleDTO.phType" cssClass="formSelect " id="goodDealWayId" list="#request.punishMethod" listKey="key" listValue="value"  theme="simple" headerKey="-1" headerValue="请选择.." /></td></tr>
					    </table>
					 </fieldset>
				</div>
				<div style="margin-top:20px;text-align:left;padding-left:20px;">
					<table>
						<tr><td>处理原因：</td><td><s:textfield name="platHandleDTO.phReason" id="dealReason" cssStyle="width:100%" theme="simple" cssClass="{required:true,maxlength:20,messages:{required:'请填写处理原因',maxlength:'处理原因不要太长哦'}}"></s:textfield></td></tr>
						<tr><td style="margin:0px;padding:0px;">处理意见说明：</td><td><s:textarea id="dealAdvise" name="platHandleDTO.phDescr" theme="simple" cols="100" rows="10" cssClass="{required:true,maxlength:50,messages:{required:'请填写处理意见说明',maxlength:'处理意见不要太长哦'}}"></s:textarea> <br></td></tr>
					</table>
				</div>
				<div style="text-align:center;margin-top:30px;">
					<input type="submit" value="确认处理" class="formButton" onclick="return dealButton()"/>&nbsp;&nbsp;
					<input type="button" value="取消" class="formButton" onclick="go('base/goods!list')" >
				</div>
			</form>
			<div class="listBottom">
				<div class="Fr" id="pageNav">
					<s:property value="pageHTML" escape="false"/>
				</div>
			</div>
			</div>
	    </div>
	 </div>
</body> 
</html>