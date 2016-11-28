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
	<title>短信群发管理</title>
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
	<script src="js/datepicker/WdatePicker.js"></script>
	<script src="js/common.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script type="text/javascript">

	        
		//查询方法
		function query(page) {
			var smsId = $.trim($("#smsId").val());  //参数名称 
			var smsTitle = $.trim($("#smsTitle").val());//使用标题
			var smsStatus = $.trim($("#smsStatus").val());//使用状态 
            var oilId = $.trim($("#oilId").val());//服务类型
            var smsType = $.trim($("#smsType").val());//时间（起始）
            var createTime = $.trim($("#createTime").val());//时间（截止）
            var beginDate = $.trim($("#beginDate").val());
			var endDate = $.trim($("#endDate").val());
			var params = {
				"smsDto.smsId" : smsId,
		        "smsDto.smsTitle" : smsTitle,
		        "smsDto.smsStatus" : smsStatus,
		        "smsDto.oilId":oilId,
		        "smsDto.smsType":smsType,
		        "smsDto.beginDate":beginDate,
		        "smsDto.endDate":endDate,
		        "smsDto.createTime":createTime,
		        "orderProperty" : $("#orderProperty").val(),
		        "orderDirection" : $("#orderDirection").val(),
		        "smsDto.page" : page
		    }; 
		   ajaxData("message/shortMesSend!jsonPageList",params);
		}
		
				//加载详情页面
		var loadData = function(id,obj) {
			var type=obj.title;//得到操作方式
		  	var params = {   
		        "smsDto.smsId" : id
		  	};
		  	var actionUrl = "message/shortMesSend!loadOrder"; 
		    $.ajax( {  
		    	async:false,
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
					var consoleDlg = $("#dialog-confirm"); 
					consoleDlg.attr("title","查看详情");
					consoleDlg.find("#dialog_smsId").html(data.obj.smsId); 
					//alert("1111");
		    		if(data.obj.smsStatus==0){
		    			consoleDlg.find("#dialog_status").html("未审核");
		    		}else if(data.obj.status==2){
		    			consoleDlg.find("#dialog_status").html("审核失败");
		    		}else if(data.obj.status==1){
		    			consoleDlg.find("#dialog_status").html("审核成功");
		    		}
		    		
		    		//alert("2222");
		    		//consoleDlg.find("#dialog_smsId").html(data.obj.smsId);
		    		consoleDlg.find("#dialog_smsTitle").html(data.obj.smsTitle); 
		    		consoleDlg.find("#dialog_smsContent").html(data.obj.smsContent);
		    		consoleDlg.find("#dialog_num").html(data.obj.num);
		    		consoleDlg.find("#dialog_price").html(data.obj.totalPrice); 
		    		consoleDlg.find("#dialog_createTime").html(data.obj.createTime);
		    		if(data.obj.smsContent==null){
		    			consoleDlg.find("#smsContent").val("");
			    	}else{
			    		consoleDlg.find("#smsContent").val(data.obj.smsContent);
				    }
		            // 打开对话框   ,判断 状态
		            if(type==="查看"){
		            	consoleDlg.find("#smsContent").attr("readonly",true);
		            	$("#dialog-confirm").dialog({
		            		resizable: true,
							top: 370,
							height:400,
							width:750,
							modal: true,
							buttons:{
								'关闭': function() {
								$(this).dialog('close');
								}
							}
		            	});
		            }else {
		            	consoleDlg.find("#smsContent").attr("readonly",false);
		            	$("#dialog-confirm").dialog({
						resizable: true,
						top: 370,
						height:400,
						width:750,
						modal: true,
						buttons: {
							'关闭': function() {
								$(this).dialog('close');
							},
							'审核通过':function(){
								if(confirm("是否确认审核通过？")){
									success();
									$(this).dialog('close');
									query(1);
								}
								
							},
							'审核不通过':function(){
								if(confirm("是否确认审核不通过？")){
									noSuccess();
									$(this).dialog('close');
									query(1);
								}
							}
						}
					}); 
		            }
		            }else{
		            	alert("数据加载出错!!!");
		            }
		        }   
		    });
		};
		
				/*
			审核通过
		*/
		function success(){
			//var orderId = document.getElementById("orderId").value;
				var smsId = $("#dialog_smsId").text();
				var params = {
					"smsDto.smsId" : smsId				
			    };
				$.ajax({
				    async:false,
					url:"message/shortMesSend!auditSuccess",
					type:"post",
					data:params,
					dataType:"json",
					success:function(data, textStatus){	
					}
				});
		}
		function noSuccess(){
			var smsId = $("#dialog_smsId").text();
			var descr=$("#descr").val();
			var params = {
				"smsDto.smsId" : smsId
		    };
			$.ajax({
				async:false,
				url:"message/shortMesSend!auditFailure",
				type:"post",
				data:params,
				dataType:"json",
				success:function(data, textStatus){					
				}
			});
		}
	</script> 
</head>
<body onload="query(${smsDto.page });">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 短信费管理 &gt;&gt; 短信参数管理
	</div>

	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>

	<div id="dialog-confirm" style="display: none;" title="编辑">
			<%--	 <s:include value="/WEB-INF/page/order/batchSale/batchSaleSet.jsp"/>--%>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formTable">
				<tr>
<!--					<td align="left" width="30%">-->
<!--						<s:hidden  id="dialog_smsId"></s:hidden>-->
<!--					</td>-->
					<th align="right" width="20%">短信编号：</th>
					<td align="left" width="30%" id="dialog_smsId"></td>
				</tr>
				<tr>				
					<th align="right" width="20%">短信标题：</th>
					<td align="left" width="30%" id="dialog_smsTitle"></td>
					<th align="right" width="20%">短信创建时间：</th>
					<td align="left" width="30%" id="dialog_createTime"></td>
				</tr>
				<tr>
					<th align="right" width="20%">本次条数：</th>
					<td align="left" width="30%" id="dialog_num"></td>
					<th align="right" width="20%">本次费用：</th>
					<td align="left" width="30%" id="dialog_price"></td>
				</tr>				
				<tr>
					<th align="right">短信内容：</th>
		        	<td colspan="4"><textarea id="dialog_smsContent" class="formTextarea" readonly="true" cols="70" rows="5" maxlength="230"  ></textarea>
		        		<span class="Color3">(255字符以内！)</span>
		        	</td>
				</tr>
				
			</table>
			
		</div>
		
	<div class="search">
		<table class="searchTable" cellpadding="0" cellspacing="0">
			<tr>
<!--	            <td><img src="images/fd.jpg" /></td>-->
	            <td>短信标题:</td>
	            <td><s:textfield id="smsTitle" name="smsDto.smsTitle" cssStyle="width:147.5px;" cssClass="formInput" maxlength="20" theme="simple"/></td>
				<td></td>
				<td>目标人群:</td>
				<td><s:select name="smsDto.smsType" id="smsType" list="#request.type" listKey="key" headerKey="-1" headerValue="全部" listValue="value" cssStyle="width:153.5px;" cssClass="formSelect" theme="simple"/></td>				
				<td>状态:</td>
				<td><s:select name="smsDto.smsStatus" id="smsStatus" list="#request.status" listKey="key" headerKey="-1" headerValue="全部" listValue="value" cssStyle="width:153.5px;" cssClass="formSelect" theme="simple"/></td>				
				 <td>创建时间:</td>
				<td><s:textfield id="beginDate" name="smsDto.beginDate" readonly="true" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssClass="Wdate formInput2" maxlength="20" theme="simple"/>至<s:textfield id="endDate" name="smsDto.endDate" readonly="true" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssClass="Wdate formInput2" maxlength="20" theme="simple"/></td>
				
				<td align="center"><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
			</tr>
						
		</table>
	</div>
	<div class="search">	
		<s:if test="#session.user_session.userLevel==2">
			<div class="Fl">
				<my:permission key='sy-1611-02' value='短信参数添加'>
					<input type="button" class="formButton" value="添加" onclick="go('message/shortMesSend!addUI')"/>
				</my:permission>
			</div>
		</s:if>
	</div>
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="3%">序号</th>
			<th width="7%"><a  class="sort">短信标题</a></th>
			<th width="6%"><a  class="sort">目标人群</a></th>
			<th width="5%"><a  class="sort">条数</a></th>
			<th width="5%"><a  class="sort">费用</a></th>
			<th width="5%"><a  class="sort">状态</a></th>
			<th width="6%"><a  class="sort">操作人</a></th>
			<th width="7%"><a  class="sort">创建时间</a></th>
			<th width="7%"><a  class="sort">更新时间</a></th>
			<th width="5%">相关操作</th>
		</tr>
	</table>
	<div class="listBottom">
	<s:if test="#session.user_session.userLevel==2">
		<div class="Fl">
			<my:permission key='sy-1611-02' value='短信参数添加'>
				<input type="button" class="formButton" value="添加" onclick="go('message/shortMesSend!addUI')"/>
			</my:permission>
		</div>
	</s:if>
		<div class="Fr" id="pageNav">
		</div>
	</div>
</body> 
</html>
