<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<base href="<%=basePath%>" />
	<title>长短款处理</title>
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
	<script src="js/jquery/jquery.ui.autocomplete.js"></script>
	<script src="js/jquery/jquery.ui.resizable.js"></script>
	<script src="js/jquery/jquery.ui.dialog.js"></script>
	<script src="js/common.js"></script>
	<script src="js/pubValiPattern.js"></script>
	<script src="js/pubValidate.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script src="js/datepicker/WdatePicker.js"></script>
	<script type="text/javascript">
		function query(page){
			var createTime=$.trim($("#createTime").val());
			var endTime=$.trim($("#endTime").val());
			var operateType=$("#searchOper").val();
			var cardNo=$.trim($("#searchCardNo").val());
			if(cardNo.length>0&&cardNo.length<6){
				alert("卡号长度不正确！");
				return;
			}
			if(createTime.length==0&&endTime.length>0){
				alert("请选择开始时间！");
				return;
			}
			var params = {
		        "orderProperty" : $("#orderProperty").val(),//为了获得有序排列 在后台的action他是一个hashMap的键值对！
		        "orderDirection" : $("#orderDirection").val(),
		        "recordDTO.createTime":createTime,
		        "recordDTO.endTime":endTime,
		        "recordDTO.operateType":operateType,
		        "recordDTO.cardNo":cardNo,
		        "recordDTO.page" : page
		    }; 
		   ajaxData("account/accRecord!jsonPageList",params);// !是跳转的意思,相当于？
		}	
		/**初始化控件内容**/
		function init(){
			document.getElementsByName("operType")[0].checked=true;
			document.getElementById("accType").options[0].selected="selected";
			$("#fundMsg").text("");
			$("#cardNoMsg").text("");
			$("#cardNo").val("");
			$("#fund").val("0.00");
			$("#status").text("");
			$("#memName").text("");
			$("#memSex").text("");
			$("#memTelNo").text("");
			$("#memOrg").text("");
			$("#bindMem").text("");
			$(".member").css("display","none");
		}
		/**判断卡号是否合法**/
		function checkCardNo(){
			var value=$.trim($("#cardNo").val());
			var reg=/^\d{6,8}$/i;
			if(value.length==0){
				$("#cardNoMsg").text("卡号不能为空");
				return false;
			}
			var params={
				"accountsDTO.cardNo":value,
				"accountsDTO.accTypeID":$("#accType").val()
			};
			if(reg.test(value)){
				$("#cardNoMsg").text("");
				$.ajax({
					url:"account/accounts!queryAccByCardNo",
					type:"post",
					data:params,
					dataType:"json",
					error : function(textStatus, errorThrown) { 
		    			alert("系统ajax交互错误!");	    
			        },
			        success : function(data, textStatus) {
						//alert(data[1].organName);
						//账户状态 0：启用；1：禁用；2：删除；3：冻结
						if(data[0]!=null){
							$("#accId").val(data[0].accId);
							if(data[0].status==0){
								$("#status").text("启用");
							}else if(data[0].status==1){
								$("#status").text("禁用");
							}else if(data[0].status==2){
								$("#status").text("删除");
							}else if(data[0].status==3){
								$("#status").text("冻结");
							}
						}else{
							$("#status").text("无此帐户");
						}
						if(data[1]!=null){
							$("#memName").text(data[1].memRealName);
							if(data[1].sex==1){
								$("#memSex").text("男");
							}else{
								$("#memSex").text("女");
							}
							$("#memTelNo").text(data[1].teleNo);
							$("#memOrg").text(data[1].organName);
							$("#bindMem").text("已绑定");
							$(".member").removeAttr("style");
						}else{
							$("#bindMem").text("未绑定会员");
							$(".member").css("display","none ");
						}
			        }
				});
				return true;
			}else{
				$("#cardNoMsg").text("卡号为6位数字");
				return false;
			}
		}
		/**判断金额是否合法**/
		function checkFund(){
			var value=$.trim($("#fund").val());
			var reg=/^[0-9]{1,6}(([\\.][0-9]{1,2})|([\\.]))?$/i;
			if(value.length==0){
				$("#fund").val("0.00");
				$("#fundMsg").text("");
				return true;
			}else if(reg.test(value)){
				$("#fundMsg").text("");
				return true;
			}else{
				$("#fundMsg").text("金额为正数保留两位小数");
				return false;
			}
		}
		function selectChange(){
			var value=$.trim($("#cardNoMsg").val());
			if(value.length==0){
				$("#cardNoMsg").text("");
			}
			checkCardNo();
		}
		function editContent(){
			init();
			$("#dialog-confirm").dialog({
				resizable: false,
				top: 370,
				height:360,
				width:620,
				modal: true,
				buttons:{
					'取消':function(){
						$(this).dialog("close");
					},
					'确定':function(){
						update();
					}
				}
			});
		}
		/**更新帐户**/
		function update(){
			var oper=document.getElementsByName("operType");
			var operType="";
			for(var i=0;i<oper.length;i++){
				if(oper[i].checked){
					operType=oper[i].value;
				}
			}
			if($("#status").text()!="启用"){
				alert("帐户状态不正常，无法操作！");
				return;
			}
			var flag=checkCardNo()&&checkFund();
			var params={
				"accountsDTO.accId":$("#accId").val(),
				//"accountsDTO.accTypeID":$("#accType").val(),
				"operType":operType,
				"fund":$("#fund").val()
			}
			if(flag==true){
				$.ajax({
					url:"account/accounts!longShortFund",
					type:"post",
					data:params,
					dataType:"json",
					error : function(textStatus, errorThrown) { 
		    			alert("系统ajax交互错误!");	    
			        },
			        success : function(data, textStatus) {
			        	alert("操作成功");
			        	$("#dialog-confirm").dialog("close");
			        	query(1);
			        }
				});
				
			}else{
				return;
			}
		}
	</script>
  </head>
  	
  <body onload="query(1);">
  	<div class="Position">
		当前位置是：HOME &gt;&gt; 帐户管理&gt;&gt; 长短款处理
	</div>
	
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
  	<div id="dialog-confirm" style="display: none;text-align: center;" title="操作窗口" >
  	
				<table width="100%" border="0" bordercolor="#000000" cellpadding="0" cellspacing="0"
					class="formTable"  >
					<input type="hidden" id="accId" />
					<tr>
						<th  style="text-align: center;">
								<label for="tags" >卡号：</label>
						</th>
						<td style="text-align: left;">
							<input  type="text" id="cardNo" onblur="checkCardNo();" maxlength="6" />
							<label id="cardNoMsg" style="color: #FF0000;" ></label> 
						</td>
						<th  style="text-align: center;">
								<label for="tags" ">操作类型：</label>
						</th>
						<td style="text-align: left;">
							<s:radio list="#request.longShort" name="operType" listKey="key" listValue="value" value="1" theme="simple" ></s:radio>
						</td>
					</tr>
					<tr>
						<th>金额：</th>
						<td style="text-align: left" >
							<input type="text" id="fund"  maxlength="10" value="0.00" onblur="checkFund();"/>
							<label id="fundMsg" style="color:  #FF0000;"></label> 
						</td>
						<th>帐户类型：</th>
						<td style="text-align: left" >
							<s:select id="accType" onchange="selectChange();" list="#request.accType" listKey="key" listValue="value" theme="simple">
							</s:select>
						</td>
					</tr>
					<tr>
						<th>帐户状态：</th>
						<td>
							<label id="status" style="color: #FF0000;"></label>
						</td>
						<th>是否绑定会员：</th>
						<td>
							<label id="bindMem" style="color: #FF0000;"></label>
						</td>
					</tr>
					
					<tr class="member">
						<th colspan="5" style="text-align: center;">持卡人信息</th>
					</tr>
					<tr class="member">
						<th>会员姓名：</th>
						<td>
							<label id="memName"></label>
						</td>
						<th>会员姓别：</th>
						<td>
							<label id="memSex"></label>
						</td>
					</tr>
					<tr class="member">
						<th>会员电话：</th>
						<td>
							<label id="memTelNo"></label>
						</td>
						<th>所属机构：</th>
						<td>
							<label id="memOrg"></label>
						</td>
					</tr>
				</table>
		</div>
	
	<div class="search" style="height: 5%;">
			<table class="searchTable" cellpadding="0" cellspacing="0">
				<tr>
		            <td><img src="images/fd.jpg" /></td>
					<td>操作日期:</td>
					<td>
						<td><s:textfield id="createTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssStyle="width:150px;" maxlength="20" theme="simple" readonly="true" /></td>
					</td>
					<td>至</td>
					<td>
						<td><s:textfield id="endTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" cssStyle="width:150px;" maxlength="20" theme="simple" readonly="true" /></td>
					</td>
		        	
		        	<td>操作类型：</td>
					<td style="text-align: left" >
						<s:select  list="#request.longShort" id="searchOper" headerKey="-1" headerValue="全部" listKey="key" listValue="value"  theme="simple">
						</s:select>
					</td>
					<td>六位卡号：</td>
					<td>
						<input type="text" id="searchCardNo" maxlength="6" />
					</td>
					<td><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
				</tr>
			</table>
	</div>
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
			<tr>
				<th width="3%" >序号</th>
				<th width="8%"><a name="cardNoo">所属卡号</a></th>
				<th width="8%"><a name="fundd" >调整金额</a></th>
				<th width="10%"><a name="acctypee">帐户类型</th>
				<th width="10%"><a name="createTimee" >操作时间</a></th>
				<th width="5%"><a name="sex" >操作人</a></th>
				<th width="8%"><a name="status" >操作类型</a></th>
			</tr>
	</table> 
	
		<div class="listBottom">
			<div class="Fl">
					<input type="button" class="formButton" value="添加" onclick="editContent();"/>
			</div>
			<div class="Fr" id="pageNav">
				<s:property value="pageHTML" escape="false"/>
			</div>
		</div>
  </body>
</html>
