<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>" />
		<title>收集箱</title>
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
		<script src="js/jquery/jquery.ui.autocomplete.js"></script>
		<script src="js/jquery/jquery.ui.resizable.js"></script>
		<script src="js/jquery/jquery.ui.dialog.js"></script>
		<script src="js/common.js"></script>
		<script src="js/pubValidate.js"></script>
		<script type="text/javascript" src="js/areaSelect.js"></script>
		<script src="js/datepicker/WdatePicker.js"></script>
		<link href="resources/css/style.css" rel="stylesheet" type="text/css" />
		<script src="js/common.js"></script>
		<script src="js/datetimetool.js"></script>
		<script type="text/javascript">

		/**验证主任务标志*/
		var  mainTaskNameFlag=false;
		/*截止日期**/
		var  byDateFlag=false;
		/**评论标志**/
		var descrFlag=false;
		/**描述标志*/
		var commentsFlag=false;
		/**验证主任务名称*/
		function checkMainTaskName(obj){
			if(obj.value.length==0){
				$("#mainTaskNameMsg").html("任务名称不能为空!");
				$("#mainTaskNameMsg").show();
				mainTaskNameFlag=false;
				}else{
					if(obj.value.length>255){
						$("#mainTaskNameMsg").html("任务名称过长！");
						$("#mainTaskNameMsg").show();
						mainTaskNameFlag=false;
						}else{
							$("#mainTaskNameMsg").hide();
							mainTaskNameFlag=true;
							}
					}
		    return mainTaskNameFlag;
			}
		/**验证提醒日期*/
		function checkByDate(obj){
		    var type = ["isNull"];
		    var errorMsg = ["提醒日期不能为空!"];
		    byDateFlag = showErrorMsg(obj,type,errorMsg,"byDateMsg","byDateMsg");
			/**提醒日期必须大于创建日期*/
			var byDate=$("#byDate").val();
			var createDate=$("#createTime").val();
		    if(comptime(byDate,createDate)==1){
				   $("#byDateMsg").hide();
		    	byDateFlag=true;
			   }else{
				   $("#byDateMsg").html("提醒日期必须大于创建日期!");
				   $("#byDateMsg").show();
				   byDateFlag=false;
				  }
		    return byDateFlag;
			}
		/**描述验证*/
		function checkDescr(){
				var descrValue=$("#descr").val();
				if(descrValue.length>255){
					$("#descrMsg").html("描述内容过多，最多255字！");
					$("#descrMsg").show();
					descrFlag=false;
				}else{
					$("#descrMsg").hide();
					descrFlag=true;
				}
		}
		/**评论验证*/
		function checkComments(){
				var commentsValue=$("#comments").val();
				if(commentsValue.length>255){
					$("#commentsMsg").html("评论内容过多，最多255字！");
					$("#commentsMsg").show();
					commentsFlag=false;
				}else{
					$("#commentsMsg").hide();
					commentsFlag=true;
				}
		}

		
		/**提交验证*/
		function check(){
			var mainTaskObj=document.getElementById("mainTaskName");
			var byDateObj=document.getElementById("byDate");
			checkComments();
			checkDescr();
			checkMainTaskName(mainTaskObj);
			checkByDate(byDateObj);
			if(mainTaskNameFlag&&byDateFlag&&commentsFlag&&descrFlag){
				return true;
				}else{
				return false;
			}
	  }
</script>
	</head>
	<body>
		<div class="Position">
			当前位置是：收集箱 &gt;&gt; 主任务修改
		</div>
		<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
		<s:hidden name="subTaskDTO.mainTaskId" id="mainTaskIdForSubTask" />
		<s:hidden name="subTaskDTO.subTaskId" id="subTaskId" />
		<%--主任务修改对话框开始--%>
		<div id="dialog-confirm" style="display: block;" title="修改主任务">
			<s:form action="collection/collection" method="post" theme="simple">
				<s:hidden name="method" />
				<s:hidden name="mainTaskDTO.mainTaskId" id="mainTaskId" />
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formTable">
					<tr>
						<th style="text-align: center; width: 10%;">
							主任务名称：
						</th>
						<td style="text-align: left;">
							<s:textfield name="mainTaskDTO.mainTaskName" id="mainTaskName"
								cssClass="formInput" theme="simple" onblur="checkMainTaskName(this)"  maxlength="255"
								cssStyle="width:680px;" />
							<label id="mainTaskNameMsg" class="errorMsg"
								style="display: none;"></label>
						</td>
					</tr>
					<tr>
						<th style="text-align: center;">
							任务描述：
						</th>
						<td>
							<s:textarea name="mainTaskDTO.descr" id="descr"
								cssClass="formTextarea" cols="30" rows="20"
								cssStyle="resize:none;"  maxlength="255" onblur="checkDescr();"/>
								<label id="descrMsg" class="errorMsg"
								style="display: none;position:relative;top:-10px;" ></label>
						</td>
					</tr>
					<tr>
						<th style="text-align: center;">
							优先级：
						</th>
						<td width="30%">
							<s:select name="mainTaskDTO.level" id="level"
								list="#session.levelValue" listKey="key" listValue="value"
								cssClass="formSelect" theme="simple" />
						</td>
					</tr>
					<tr>
						<th style="text-align: center;">
							提醒方式：
						</th>
						<td width="30%">
							<s:radio name="mainTaskDTO.remindWay" id="remindWay"
								list="#request.remindWays" listKey="key" listValue="value"  title="到截止日期会自动提醒！"/>
						</td>
					</tr>
					<tr>
						<th style="text-align: center;">
							创建时间：
						</th>
						<td width="30%">
							<s:textfield id="createTime" name="mainTaskDTO.createTimeString"
								cssStyle="width:150px;" maxlength="20" theme="simple"
								cssClass="formInput" readonly="true" />
						</td>
					</tr>
					<tr>
						<th style="text-align: center;">
							提醒时间：
						</th>
						<td width="30%">
							<s:textfield id="byDate" name="mainTaskDTO.byDateString"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"
								cssStyle="width:150px;" maxlength="20" theme="simple"
								cssClass="formInput"  onchange="checkByDate(this)"/>
									<label id="byDateMsg" class="errorMsg"
								style="display: none;"></label>
						</td>
					</tr>
					<tr>
						<th style="text-align: center;">
							主任务评论：
						</th>
						<td width="30%">
							<s:textarea name="mainTaskDTO.comments" id="comments"
								cssClass="formTextarea" cols="45" rows="20" maxlength="255" 
								cssStyle="resize:none;" onblur="checkComments();" />
								<label id="commentsMsg" class="errorMsg"  
								style="display: none;position:relative;top:-10px;"></label>
						</td>
					</tr>
					<tr>
						<th></th>
						<td>
							<input type="submit" class="formButton" value="保 存" onclick="return check();" />
							<input type="button" class="formButton" value="返回"
								onclick="go('collection/collection!list')" />
						</td>
					</tr>
				</table>
			</s:form>
		</div>
		<%--主任务修改对话框结束--%>
	</body>
</html>