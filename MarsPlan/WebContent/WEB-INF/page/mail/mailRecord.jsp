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
	<title>邮件发送记录</title>
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
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script src="js/datepicker/WdatePicker.js"></script>
	
	
		 <script type="text/javascript" charset="utf-8" src="mailEdit/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="mailEdit/ueditor.all.js"> </script>
    <script type="text/javascript" charset="utf-8" src="mailEdit/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="mailEdit/lang/zh-cn/zh-cn.js"></script>
	
    <style type="text/css">
        .clear {
            clear: both;
        }
        </style>
	<script type="text/javascript">
		//实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
   
    function setContent(isAppendTo) {
        var arr = [];
        arr.push("使用editor.setContent('欢迎使用ueditor')方法可以设置编辑器的内容");
        UE.getEditor('editor').setContent('', isAppendTo);
    }
		//查询方法
		function query(page) {
			if($.browser.msie){
				$("#mailContent").css("width","100%");
			}
			var sendDate=document.getElementById("sendDatee").value;
			var params = {
				"mailRecordDTO.sendDate" : sendDate,
		        "orderProperty" : $("#orderProperty").val(),//为了获得有序排列 在后台的action他是一个hashMap的键值对！
		        "orderDirection" : $("#orderDirection").val(),
		        "mailRecordDTO.page" : page
		    }; 
		   ajaxData("mail/record!jsonPageList",params);// !是跳转的意思
			/*
		   $( "#subject" ).autocomplete({
				source: availableTags
			});
		   $( "#toMail" ).autocomplete({
				source: availableTags
			});
			*/
		}
		
	
		/*删除数据*/
		function deleteData(id){
			var actionUrl = "mail/record!delete";
			var params={
					"mailRecordDTO.id":id
			}
			if(confirm("是否确认删除？")){
				$.ajax({
					async:false,
					url : actionUrl,   
			        data : params,   
			        dataType : "json",   
			        cache : false,   
			        type : "POST",
			        error : function(textStatus, errorThrown) {   
		    			alert("系统ajax交互错误!");	    
		        	}, 
		        	success:function(data, textStatus){
		        		query(1);
			        }
				});
			}
			
		}
		function lookData(id){
			var actionUrl = "mail/record!lookData";
			var params={
					"mailRecordDTO.id":id
			}
			$.ajax({
				async:false,
				url : actionUrl,   
		        data : params,   
		        dataType : "json",   
		        cache : false,   
		        type : "POST",
		        error : function(textStatus, errorThrown) {   
	    			alert("系统ajax交互错误!");	    
	        	}, 
	        	success:function(data, textStatus){
					$("#subject").html(data.subject);
					$("#createTime").html(data.createTime);
					//$("#mailContent").val(data.content);
					$("#ajaxContent").html(data.content);
					//setContent(data.content);
					$("#dialog-confirm").dialog({
						resizable: false,
						top: 370,
						height:350,
						width:700,
						modal: true,
						buttons:{
							'关闭':function(){
								$(this).dialog("close");
							}
						}
					});
	        	}
			});
		}
	</script> 
</head>
<body onload="query(${memberDTO.page });">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 邮件发送&gt;&gt; 邮件发送记录查看
	</div>
	
	
	
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	
	<div id="dialog-confirm" style="display: none;text-align: center;" title="记录详情" >
				<table width="100%" border="0" bordercolor="#000000" cellpadding="0" cellspacing="0"
					class="formTable"  >
					<tr>
						<th style="text-align: center;">
								<label for="tags" ">邮件主题: </label>
						</th>
						<td  style="text-align: left;">
							<label id="subject"></label>
						</td>
						<th>
							<label for="tags" ">发送日期: </label>
						</th>
						<td style="text-align: left;">
							<label id="createTime" ></label>
						</td>
					</tr>
					</tr>
						<tr>
						<td colspan="4" id="ajaxContent" style="text-align: left;">
								
						</td>
					</tr>
				</table>
		</div>
	
	
		<div class="search">
			<table class="searchTable" cellpadding="0" cellspacing="0">
				<tr>
		            <td><img src="images/fd.jpg" /></td>
					<td>发送日期:</td>
					<td>
						<td><s:textfield id="sendDatee" name="termConsDto.beginDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-#{%d-7}',maxDate:'%y-%M-#{%d+7}'})" cssStyle="width:150px;" maxlength="20" theme="simple" readonly="true" /></td>
					</td>
		        	<td><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
				</tr>
			</table>
		</div>
		<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
			<tr>
				<th width="3%">序号</th>
				<th width="10%"><a name="memId">发送编号</a></th>
				<th width="10%"><a name="fromMail">发送邮箱</a></th>
				<th width="10%"><a name="toMail" >目标邮箱</a></th>
				<th width="5%"><a name="sendDate" >发送日期</a></th>
				<th width="8%"><a name="type" >发送类型</a></th>
				<th width="8%"><a name="control" >操作</a></th>
			</tr>
		</table>
		<div class="listBottom">
			<div class="Fr" id="pageNav">
				<s:property value="pageHTML" escape="false"/>
			</div>
		</div>
</body> 
</html>