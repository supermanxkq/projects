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
	<title>节日邮件发送</title>
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
			$("#dialog-confirm").css("overflow","hidden");
			UE.getEditor('editor');
			if($.browser.msie){
				$("#subject").css("width","73%");
			}
			var date=$.trim($("#holidayDate").val());
			var params = {
				"mailHolidayDTO.holidayDate" : date,
		        "orderProperty" : $("#orderProperty").val(),//为了获得有序排列 在后台的action他是一个hashMap的键值对！
		        "orderDirection" : $("#orderDirection").val(),
		        "memberDTO.page" : page
		    }; 
		   ajaxData("mail/holidayMail!jsonPageList",params);// !是跳转的意思
			/*
		   $( "#subject" ).autocomplete({
				source: availableTags
			});
		   $( "#toMail" ).autocomplete({
				source: availableTags
			});
			*/
		 
		}

		function showDialog(){
			setContent('');
			$("#dialog-confirm").css("overflow","hidden");
			init();
			$("#dialog-confirm").dialog({
				resizable: false,
				top: 370,
				height:550,
				width:1024,
				modal: true,
				buttons:{
					'关闭':function(){
						$("#dialog-confirm").css("overflow","auto");
						$(this).dialog("close");
					},
					'确认添加':function(){
						add();
						query(1	);
					}
				}
			});
		}
		function add(){
			var name=$.trim($("#holidayName").val());
			var date=$.trim($("#holidayDate").val());
			var subject=$.trim($("#subject").val());
			var content="";//邮件内容
			var arr = [];
        	arr.push(UE.getEditor('editor').getContent());
     	   	content=$.trim(arr.join());
			
			
			if(name.length==0||date.length==0||content.length==0||subject.length==0){
				$( "#dialog-message" ).dialog({
					modal: true,
					height:90,
					width:230,
					resizable:false
				});
				return;
			}
			var actionUrl = "mail/holidayMail!add";
			var params={
					"mailHolidayDTO.holidayName":name,
					"mailHolidayDTO.subject":subject,	
					"mailHolidayDTO.holidayDate":date,	
					"mailHolidayDTO.content":content	
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
					alert("节日已添加");
					$("#dialog-confirm").css("overflow","auto");
					$("#dialog-confirm").dialog("close");
					query(1);
		        }
			});
		}
		/*编辑查看数据*/
		function editData(id){
			$("#dialog-confirm").css("overflow","hidden");
			var actionUrl = "mail/holidayMail!editData";
			var params={
					"mailHolidayDTO.id":id	
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
		        	$("#holidayName").val(data.holidayName);
		        	$("#holidayDate").val(data.holidayDate);
		        	$("#content").val(data.content);
		        	$("#subject").val(data.subject);
					$("#dialog-confirm").dialog({
						resizable: false,
						top: 370,
						height:550,
						width:1024,
						modal: true,
						buttons:{
							'关闭':function(){
								$("#dialog-confirm").css("overflow","auto");
								$(this).dialog("close");
							},
							'修改信息':function(){
								update(id);
								
							}
						}
					});
		        }
			});
		}
		/*更新数据*/
		function update(id){
			var name=$.trim($("#holidayName").val());
			var date=$.trim($("#holidayDate").val());
			var subject=$.trim($("#subject").val());
			var content="";//邮件内容
			var arr = [];
        	arr.push(UE.getEditor('editor').getContent());
     	   	content=$.trim(arr.join());
			if(name.length==0||date.length==0||content.length==0){
				$( "#dialog-message" ).dialog({
					modal: true,
					height:90,
					width:230,
					resizable:false
				});
				return;
			}
			var actionUrl = "mail/holidayMail!update";
			var params={
					"mailHolidayDTO.id":id,
					"mailHolidayDTO.holidayDate":date,
					"mailHolidayDTO.subject":subject,
					"mailHolidayDTO.holidayName":name,
					"mailHolidayDTO.content":content
			};
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
	        		$("#dialog-confirm").css("overflow","auto");
	        		$("#dialog-confirm").dialog("close");
	        		query(1);
		        }			
			});
		}
		/*删除数据*/
		function deleteData(id){
			var actionUrl = "mail/holidayMail!delete";
			var params={
					"mailHolidayDTO.id":id
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
	        		query(1);
		        }
			});
		}
		/*初始化元素内容*/
		function init(){
			$("#holidayName").val("");
			$("#holidayDate").val("");
			$("#subject").val("");
		}
	</script> 
</head>
<body onload="query(${memberDTO.page });">
	<div class="Position"> 
		当前位置是：HOME &gt;&gt; 邮件发送&gt;&gt; 节日邮件管理 
	</div>
	
	<div id="dialog-message" title="提示信息" style="display: none;">
	<p align="center" style="font-size: 130%">
		以上四项为必添项!
	</p>
	</div>
	
	
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	
	<div id="dialog-confirm" style="display: none;text-align: center;width: 1024px;" title="节日邮件" >
		<input type="hidden"  id="id" />
				<table width="100%" border="0" bordercolor="#000000" cellpadding="0" cellspacing="0"
					class="formTable"  >
					<tr>
						<th style="text-align: center;width: 10%;">
							节日名称：
						</th>
						<td style="text-align: left;">
							<input type="text" id="holidayName" />
						</td>
						<th style="text-align: center;width: 10%;">
							节日日期：
						</th>
						<td style="text-align: left;">
							<s:textfield id="holidayDate" name="termConsDto.beginDate" onfocus="WdatePicker({dateFmt:'MM-dd'})" cssStyle="width:150px;" maxlength="20" theme="simple" readonly="true" />
						</td>
						
					</tr>
					<tr>
						<th style="text-align: center;width: 10%;">主题：</th>
						<td colspan="3" style="text-align: left;">
							<input type="text" id="subject" style="width: 75%;" maxlength="200" />
						</td>
					</tr>
					<tr>
						<td colspan="4">
								<script id="editor" type="text/plain" style="width:100%;height:260px;"></script>
						</td>
					</tr>
				</table>
		</div>
	
	<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
			<tr>
				<th width="3%">序号</th>
				<th width="10%"><a name="holidayName">节日名称</a></th>
				<th width="10%"><a name="holidayDate" >节日时间</a></th>
				<th width="10%"><a name="subjectt" >邮件主题</a></th>
				<th width="5%"><a name="status" >是否已过</a></th>
				<th width="8%"><a name="control" >操作</a></th>
			</tr>
		</table>
		<div class="listBottom">
			<div class="Fl">
				<s:if test="#session.user_session.userLevel!=2">
					<input type="button" class="formButton" value="添加邮件" onclick="showDialog();"/>
				</s:if>
		</div>
		<div class="listBottom">
			<div class="Fr" id="pageNav">
				<s:property value="pageHTML" escape="false"/>
			</div>
		</div>
</body> 
</html>