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
	<title>商户领卡</title>
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
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script src="js/datepicker/WdatePicker.js"></script>
	<script type="text/javascript">
		//查询方法
		function query(page) {
			var modStockId = $.trim($("#modStockId").val());
			var beginDate = $.trim($("#beginDate").val());
			var endDate = $.trim($("#endDate").val());
			var status = $.trim($("#status").val());
			var params = {
				"modStockDTO.Id" : modStockId,
		        "modStockDTO.status" : status,
		        "modStockDTO.beginDate":beginDate,
		        "modStockDTO.endDate":endDate,
		        "orderProperty" : $("#orderProperty").val(),
		        "orderDirection" : $("#orderDirection").val(),
		        "modStockDTO.page" : page
		    }; 
		   ajaxData("stock/mercard!jsonPageList",params);
		}

		//审核/查看页面 [查询]按钮触发 
		function queryStock(){
			var modId = $("#modSerId").val();
			var queryType = $("#queryType").val();
			var cardNo = $("#cardNo").val();
			var cardNoId =$("#cardNoId").val();
			if(cardNo==cardNoId){
			     return false;
			}
			if(queryType=="0"){
                  if(cardNo==""||cardNo==null){
                         alert("单卡查询提醒您:请填写要查询的卡号!");
                         return false;
                      }
				}
			var params = {
					"modStockDTO.Id":modId,
					"modStockDTO.queryType":queryType,
					"modStockDTO.cardNo":cardNo
					};
			var actionUrl = "stock/headquin!stockDetial";
			$.ajax({
                  url:actionUrl,
                  data:params,
                  dataType:"json",
                  cache:false,
                  type:"POST",
                  error:function(data,status){
                   alert("系统加载库存变动信息失败");   
                 },
                  success:function(data){
                   var showModify = $("#show_Detail");
                   var row = Math.ceil(Math.random( )*1000000);
      			   var tr = $("<tr id='r_"+row+"'></tr>");
                   var html ="";
                   if(queryType=="0"){
                        //单卡查询
                       html+="<td>"+data.obj.modStockId+"<input type='hidden' name='modStockDTO.Ids' value='"+data.obj.modStockId+"'/></td>";
                       html+="<td>"+data.obj.cardNoId+"<input type='hidden' id='cardNoId' name='modStockDTO.cardNos' value='"+data.obj.cardNoId+"'></td>";
                       html+="<td>"+data.obj.stockStatusDesc+"</td>";
                       html+="<td>"+data.obj.stockFlagDesc+"</td>"
                       tr.html(html);
                       $("#cardDetailTable").append(tr);
                     }
                     else{
                        //查询全部
                        var cardNos = data.obj;
                        for(var i =0;i<cardNos.length;i++){
                         if(i%4==0)
                             html+="<tr>"
                         html+="<td>"+cardNos[i]+"</td>";
                         if(i%4==-1)
                             html+="</tr>";
                        }
                        $("#cardNosTable").find('tr:eq(0)').nextAll().remove();
		                $("#cardNosTable").append(html);
                     }
                 }
				}); 
			}
		//审核/详情页面查询方式变更，触发：
		function changeQueryType(){
			var queryType = $("#queryType").val();
			if(queryType=="0"){
				$("#cardNosTable tr:gt(0)").remove();//删除除了第一行外的所有行
				$("#danka").css("display","block");
                $("#quanbu").css("display","none");
              
				}
			else{
				$("#cardDetailTable tr:gt(0)").remove();
			    $("#cardNo").val("");
			    $("#cardNo").css("readOnly","true");
				$("#danka").css("display","none");
                $("#quanbu").css("display","block");
				    
				}
			}
		
		//加载审核/详情页面 
		function sureInStock(id,stockFlag,flag){
			
			var showModify = $("#show_Detail");
            showModify.find("#modStockSeriId").html(id);
            showModify.find("#modStodkStatus").html(stockFlag);
            $("#modSerId").val(id); 
            if(flag == 1){
                   button={
            	        '取消': function() {
                	   $("#cardDetailTable tr:gt(0)").remove();
                	   $("#cardNosTable tr:gt(0)").remove();//删除除了第一行外的所有行
				              $(this).dialog('close');
			                 }
                       }
                }
            else{
            	button={
            	'取消': function() {
            		$("#cardDetailTable tr:gt(0)").remove();
             	   $("#cardNosTable tr:gt(0)").remove();//删除除了第一行外的所有行
			 	      $(this).dialog('close');
			         },
	             '确认入库':function(){
				    sureStockIn();//确认入库
			        $(this).dialog('close');
			        ajaxData("stock/mercard!jsonPageList",condition);
		          }
                }
            }
			$("#show_Detail").dialog({
				resizable: true,
				top: 240,
				height:400,
				width:800,
				modal: true,
				title:"商户领卡信息查看",
				buttons:button
			});
			
		}
		//确认入库操作
		function sureStockIn(){
		  var modSerId = $("#modSerId").val();
		  var actionUrl = "stock/mercard!sureStockIn";
		  $.ajax({
		          url:actionUrl,
                  data:"modStockDTO.id="+modSerId,
                  dataType:"json",
                  cache:false,
                  type:"POST",
                    success:function(data){
                     alert(data.msg);
                     query($("#currPage").text());
                  },
                  error:function(data,status){
                   alert("系统ajax信息交互错误，入库失败!");   
                 }
                
		  });		 
		}
	</script> 
</head>
<body onload="query(${modStockDTO.page });">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 库存管理 &gt;&gt; 商户领卡
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	
	<div class="search">
		<table class="searchTable" cellpadding="0" width="96%" cellspacing="0">
			<tr>
	            <td><img src="images/fd.jpg" /></td>
			    <td>流水号:</td>
				<td><s:textfield id="modStockId" name="modStockDTO.id" cssClass="formInput" maxlength="20" theme="simple"/></td>
				<td>入库状态</td>
	        	<td><s:select name="modStockDTO.status" id="status" list="#request.invChangeValues" listKey="key" headerKey="-1" headerValue="全部" listValue="value" cssClass="formSelect" theme="simple"/></td>
			     <td>操作时间:</td>
				<td><s:textfield id="beginDate" name="modStockDTO.beginDate" readonly="true" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssClass="Wdate formInput2" maxlength="20" theme="simple"/>至<s:textfield id="endDate" name="modStockDTO.endDate" readonly="true" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssClass="Wdate formInput2" maxlength="20" theme="simple"/></td>
			    <td align="center"><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
			</tr>			
		</table>
	</div>
	
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
		<tr>
			<th width="3%">序号</th>
			<th width="10%">流水号</th>
			<th width="12%">出库方</th>
			<th width="12%">入库方</th>
			<th width="6%">数量</th>
			<th width="6%">状态</th>
			<th width="10%">创建时间</th>
			<th width="10%">入库时间</th>
			<th width="6%">相关操作</th>
		</tr>
	</table>
	<div class="listBottom">
	<s:if test="#session.user_session.userLevel==0">
		<div class="Fl">
			<my:permission key='sy-2203-02' value='机构领卡添加'>
				<input type="button" class="formButton" value="添加" onclick="go('stock/mercard!addUI')"/>
			</my:permission>
		</div>
	</s:if>
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div><%--
	//显示弹出框 
	--%><div id="show_Detail" style="display: none;">
	<s:hidden id="modSerId" name="modStockDTO.Id"></s:hidden>
	    <table class="formTable">
	        <tr>
		  		<th align="right" width="20%">流水号：</th>
		        <td width="30%" id="modStockSeriId"></td>
		        <th align="right" width="20%"><span class="Color5">* </span>状态：</th>
		        <td width="30%" id="modStodkStatus"></td>
			</tr>
			<tr>
			    
		  		<th align="right" width="20%">卡号：</th>
		        <td width="30%"><s:textfield id="cardNo" name="modStockDTO.cardNo" cssClass="formInput" maxlength="20" theme="simple"/></td>
		        <th align="right" width="20%"><span class="Color5">* </span>查询操作：</th>
		        <td width="30%"><select onchange="changeQueryType();" name="modStockDTO.queryType" id="queryType"><option value="0">单卡查询</option><option value="1">全部查询</option></select></td>
		        <td align="center"><input type="button" class="formButton"  onclick="queryStock();" value="查 询" /></td>
			</tr>
	    </table>
	    <div id="quanbu" style="display: none;">
	     <table width="96%" id="cardNosTable" class="listTable" cellpadding="0" cellspacing="0" >
			<tr>
				<th width="10%">卡号</th>
				<th width="10%">卡号</th>
				<th width="10%">卡号</th>
				<th width="10%">卡号</th>
			</tr>
		</table>
		</div>
		<div id="danka" style="display:block">
		<table width="96%" id="cardDetailTable" class="listTable" cellpadding="0" cellspacing="0" >
			<tr>
				<th width="10%">流水号</th>
				<th width="10%">卡号</th>
				<th width="10%">状态</th>
				<th width="10%">本次动向</th>
			</tr>
		</table>
		</div>
	</div>
</body> 
</html>