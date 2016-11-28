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
		<title>群组邮件发送</title>
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
		<script type="text/javascript" src="js/areaSelect.js"></script>
		<script src="js/datepicker/WdatePicker.js"></script>

		<script type="text/javascript" charset="utf-8"
			src="mailEdit/ueditor.config.js"></script>
		<script type="text/javascript" charset="utf-8"
			src="mailEdit/ueditor.all.js"> </script>
		<script type="text/javascript" charset="utf-8"
			src="mailEdit/ueditor.all.min.js"> </script>
		<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
		<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
		<script type="text/javascript" charset="utf-8"
			src="mailEdit/lang/zh-cn/zh-cn.js"></script>

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
			UE.getEditor('editor');
			if($.browser.msie){
				$("#subject").css("width","81%");
			}
			var beginDate = $("#beginDate").val();
			var endDate = $("#endDate").val();
			var params = {
		        "orderProperty" : $("#orderProperty").val(),//为了获得有序排列 在后台的action他是一个hashMap的键值对！
		        "orderDirection" : $("#orderDirection").val(),
		    	"memGroupsDTO.beginDate" : beginDate,
				"memGroupsDTO.endDate" : endDate,
		        "memGroupsDTO.page" : page
		    }; 
		   ajaxData("mail/groupMail!jsonPageList",params);// !是跳转的意思
		  /*
		   $( "#subject" ).autocomplete({
				source: availableTags
			});
			
		   $( "#toMail" ).autocomplete({
				source: availableTags
			});
			*/
		}
		//全选
		function checkAll(){
			var status=document.getElementById("checkAll").checked;
			var email=document.getElementsByName("email");
			if(status==true){
				for(var i=0;i<email.length;i++){
					email[i].checked=true;
				}
			}else{
				for(var i=0;i<email.length;i++){
					email[i].checked=false;
				}
			}
			
		}
		//编辑内容，发送邮件
		function editContent(){
			setContent('');
			$("#dialog-confirm").css("overflow","hidden");
			$("#subject").val("");
			var check=$("input:checkbox[name='email']:checked");
			if(check.length==0){
				$( "#dialog-check" ).dialog({
					modal: true,
					height:90,
					width:230,
					resizable:false
				});
				return;
			}
			var email=document.getElementsByName("email");
			var emailStr="";
			for(var i=0;i<email.length;i++){
				if(email[i].checked){
					//拼接会员邮箱
					emailStr+=";"+email[i].title+"<群号:"+email[i].value+">";
				}
			}
			emailStr=emailStr.substring(1);
			$("#toMail").html(emailStr);
			$("#dialog-confirm").dialog({
				resizable: false,
				top: 370,
				height:550,
				width:1024,
				modal: true,
				buttons:{
					'取消':function(){
						$("#dialog-confirm").css("overflow","auto");
						$(this).dialog("close");
					},
					'发送邮件':function(){
						sendMail();
					}
				}
			});
		}
		//开始发送邮件 
		function sendMail(){
			
			var groupId="";
			var email=document.getElementsByName("email");
			for(var i=0;i<email.length;i++){
				if(email[i].checked){
					//拼接群组编号
					groupId+=";"+email[i].value;
				}
			}
			groupId=groupId.substring(1);
			var mailContent="";//邮件内容
			var emailSubject=$.trim($("#subject").val());//邮件主题
			var arr = [];
        	arr.push(UE.getEditor('editor').getContent());
     	   	mailContent=$.trim(arr.join());
			if(mailContent.length==0||emailSubject.length==0){//当邮件没有内容或者全是空格时不允许发送
				$( "#dialog-message" ).dialog({
					modal: true,
					height:90,
					width:230,
					resizable:false
				});
				return;
			}
			
			var actionUrl = "mail/groupMail!sendMail"; 
			var params={
				"groupId":groupId,
				"emailContent":mailContent,		
				"emailSubject":emailSubject		
			};
			$.ajax({
				url : actionUrl,   
		        data : params,   
		        dataType : "json",   
		        cache : false,   
		        type : "POST",
		        error : function(textStatus, errorThrown) {   
	    			alert("系统ajax交互错误!");	    
	        	}, 
	        	success:function(data, textStatus){
					alert("邮件已发送");
					$("#dialog-confirm").css("overflow","auto");
					$("#dialog-confirm").dialog("close");
		        }
			});
		}
		//邮箱选择，同时控制全选按钮的选中状态
		function check(){
			var email=document.getElementsByName("email");
			for(var i=0;i<email.length;i++){
				if(!email[i].checked){
					//当有邮件复选框不是选中状态，则全选按钮也不选中,程序退出
					document.getElementById("checkAll").checked=false;
					return;
				}
				if(i==email.length-1){
					//当邮件复选框全是选中状态时，则全选按钮也选中
					document.getElementById("checkAll").checked=true;
				}
			}
		}
	</script>
	</head>
	<body onload="query(${memberDTO.page });">
		<div class="Position">
			当前位置是：HOME &gt;&gt; 邮件发送&gt;&gt; 群组邮件发送
		</div>

		<div id="dialog-message" title="提示信息" style="display: none;">
			<p align="center" style="font-size: 130%">
				以上两项为必添项！
			</p>
		</div>

		<div id="dialog-check" title="提示信息" style="display: none;">
			<p align="center" style="font-size: 130%">
				您还没有选择群组呢！
			</p>
		</div>

		<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
		<div id="dialog-confirm"
			style="display: none; text-align: center; width: 1024px;"
			title="编辑邮件内容">
			<table width="100%" border="0" bordercolor="#000000" cellpadding="0"
				cellspacing="0" class="formTable">
				<tr>
					<th style="text-align: center; width: 10%;">
						<label for="tags">
							收件人:
						</label>

					</th>
					<td style="text-align: left;">
						<label id="toMail"></label>
					</td>
				</tr>
				<tr>
					<th style="text-align: center;">
						<label for="tags">
							邮件主题:
						</label>

					</th>
					<td style="text-align: left;">
						<input id="subject" style="width: 95%;" maxlength="200">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<script id="editor" type="text/plain"
							style="width:100%;height:260px;"></script>
					</td>
				</tr>
			</table>
		</div>

		<div class="search">
			<table class="searchTable" cellpadding="0" cellspacing="0">

				<tr>
					<td>
						创建日期:
					</td>
					<td>
						<s:textfield id="beginDate" name="memGroupsDTO.beginDate"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
							cssStyle="width:150px;" maxlength="20" theme="simple"
							readonly="true" />
					</td>
					<td>
						至：
					</td>
					<td>
						<s:textfield id="endDate" name="memGroupsDTO.endDate"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
							cssStyle="width:150px;" maxlength="20" theme="simple"
							readonly="true" />
					</td>
					<td><input type="button" class="formButton" onclick="query();" value="查 询" /></td>
				</tr>
			</table>
		</div>
		<table width="96%" id="listTable" class="listTable" cellpadding="0"
			cellspacing="0">
			<tr>
				<th width="3%">
					<input type="checkbox" id="checkAll" onclick="checkAll();" />
					全选
				</th>
				<th width="3%">
					序号
				</th>
				<th width="3%">
					<a name="groupId">群编号</a>
				</th>
				<th width="10%">
					<a name="groupName">群组名称</a>
				</th>
				<th width="10%">
					<a name="userName">创建人</a>
				</th>
				<th width="10%">
					<a name="createTime">创建时间</a>
				</th>
				<th width="5%">
					<a name="groupCount">群组人数</a>
				</th>
				<th width="8%">
					<a name="status">启用状态</a>
				</th>
			</tr>
		</table>
		<div class="listBottom">
			<div class="Fl">
				<s:if test="#session.user_session.userLevel!=2">
					<input type="button" class="formButton" value="发送邮件"
						onclick="editContent();" />
				</s:if>
			</div>
			<div class="Fr" id="pageNav">
				<s:property value="pageHTML" escape="false" />
			</div>
		</div>
	</body>
</html>