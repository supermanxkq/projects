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
		<title>卡信息管理</title>
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
		<script src="js/jquery/jquery.ui.resizable.js"></script>
		<script src="js/jquery/jquery.ui.dialog.js"></script>
		<script src="js/common.js"></script>
		<script type="text/javascript" src="js/areaSelect.js"></script>
		<script type="text/javascript">
		
		//查询方法
		function query(page) {
			var cardNo = $.trim($("#cardNo").val());
			var status = $.trim($("#status").val());
			var params = {
				"cardsDTO.cardNo" : cardNo,
		        "cardsDTO.status" : status,
		        "orderProperty" : $("#orderProperty").val(),//为了获得有序排列 在后台的action他是一个hashMap的键值对！
		        "orderDirection" : $("#orderDirection").val(),
		        "cardsDTO.page" : page
		    }; 
		   ajaxData("card/cards!jsonPageList",params);// !是跳转的意思,相当于？
		}
		
		//加载详情页面
		var loadData = function(id) {
			
		  	var params = {   
		        "cardsDTO.cardNo" : id
		  	};  
		  	
		  	var actionUrl = "card/cards!viewUI"; 
		    $.ajax( {   
		        url : actionUrl,   
		        data : params,   
		        dataType : "json",   
		        cache : false,   
		        type : "POST",
		        error : function(textStatus, errorThrown) {   
		    		alert("系统ajax交互错误!");	    
		        }, 
		        success : function(data, textStatus) { 
					var consoleDlg = $("#dialog-confirm"); 
					consoleDlg.attr("title","查看详情");
					var rowData = data.objResult;
					var saleName ="";
					if(rowData.saleOrgName!=null){
						 saleName = rowData.saleOrgName ;
					}else if(rowData.saleMerName!=null){
						 saleName = rowData.saleMerName
					}
					consoleDlg.find("#cardDetailNo").html(rowData.cardNo); 
		    		consoleDlg.find("#saleName").html(saleName); 
		    		consoleDlg.find("#amount").html(rowData.amount); 
		    		consoleDlg.find("#buyMemName").html(rowData.buyMemName);
		    		consoleDlg.find("#makeTime").html(rowData.makeTime); 
		    		consoleDlg.find("#startTime").html(rowData.startTime);

		            // 打开对话框   
					$("#dialog-confirm").dialog({
						resizable: true,
						top: 370,
						height:360,
						width:600,
						modal: true,
						buttons: {
							'确定': function() {
								$(this).dialog('close');
							}
						}
					});    
					
		        }   
		    });
		};
</script>
	</head>
	<body onload="query(${cardsDTO.page });">
		<div class="Position">
			当前位置是：HOME &gt;&gt; 卡信息管理 &gt;&gt; 卡信息列表
		</div>
		<div id="dialog-confirm" style="display: none;" title="详细信息">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formTable">
				<tr>
					<th align="right" width="20%">
						卡号：
					</th>
					<td width="30%" id="cardDetailNo"></td>
					<th align="right" width="20%">
						售卡机构/商户：
					</th>
					<td width="30%" id="saleName"></td>
				</tr>
				<tr>
					<th align="right">
						初始面值
					</th>
					<td id="amount"></td>
					<th align="right">
						购卡人
					</th>
					<td id="buyMemName"></td>
				</tr>
				<tr>
					<th align="right">
						制卡时间
					</th>
					<td id="makeTime"></td>
					<th align="right">
						启用时间
					</th>
					<td id="startTime"></td>
				</tr>
			</table>
		</div>
		<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
		<form id="form1" name="form1" action="card/cards!export" method="post" >
		<div class="search">
			<table class="searchTable" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<img src="images/fd.jpg" />
					</td>
					<td>
						卡号:
					</td>
					<td>
						<s:textfield id="cardNo" name="cardsDTO.cardNo"
							cssClass="formInput" maxlength="20" theme="simple" />
					</td>
					<td>
						状态:
					</td>
					<td>
						<s:select name="cardsDTO.status" id="status"
							list="#request.statusValues" listKey="key" listValue="value"
							headerKey="-1" headerValue="--请选择--" cssClass="formInput"
							theme="simple" />
					</td>
					<td>
						<input type="button" class="formButton" onclick="query();"
							value="查 询" />
					</td>
					<td>
						<input type="button" name="Submit2" class="formButton" onclick="javascript:document.form1.submit()" value="导出" />
					</td>
				</tr>
			</table>
		</div>
		</form>
		<table width="96%" id="listTable" class="listTable" cellpadding="0"
			cellspacing="0">
			<tr>
				<th width="3%">
					序号
				</th>
				<th width="10%">
					<a name="cardNo" class="sort">卡号</a>
				</th>
				<th width="10%">
					<a name="binName" class="sort">卡名称</a>
				</th>
				<th width="12%">
					<a name="orgName" >发卡机构</a>
				</th>
				<th width="8%">
					<a name="binFlag" >卡标志</a>
				</th>
				<th width="8%">
					<a name="holdName" >持卡人</a>
				</th>
				<th width="10%">
					<a name="levelName" >卡级别</a>
				</th>
				<th width="10%">
					<a name="validTime" class="sort">有效期</a>
				</th>
				<th width="7%">
					<a name="memsign" class="sort">会员</a>
				</th>
				<th width="7%">
					<a name="pwdsign" class="sort">密码</a>
				</th>
				<th width="10%">
					<a name="status" class="sort">状态</a>
				</th>
				<th width="5%">
					相关操作
				</th>
			</tr>
		</table>
		<div class="listBottom">
			<div class="Fr" id="pageNav">
				<s:property value="pageHTML" escape="false" />
			</div>
		</div>
	</body>
</html>