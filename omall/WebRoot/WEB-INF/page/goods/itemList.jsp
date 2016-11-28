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
	<script src="js/jquery-easyui/jquery.easyui.min.js"></script>
	<script src="js/jquery.validate.js"></script>
	<script src="js/jquery.metadata.js"></script>
	<script src="js/additional-methods.min.js"></script>
	<script src="js/common.validate.js"></script>
	<link href="js/jquery/css/jquery.ui.all.css"  rel="stylesheet"  type="text/css" />	
	<link href="js/jquery-easyui/easyui.css"  rel="stylesheet"  type="text/css" />	
	<script src="js/jquery/jquery.ui.core.js"></script>
	<script src="js/jquery/jquery.ui.widget.js"></script>
	<script src="js/jquery/jquery.ui.mouse.js"></script>
	<script src="js/jquery/jquery.ui.button.js"></script>
	<script src="js/jquery/jquery.ui.draggable.js"></script>
	<script src="js/jquery/jquery.ui.position.js"></script>
	<script src="js/jquery/jquery.ui.resizable.js"></script>
	<script src="js/jquery/jquery.ui.dialog.js"></script>
	<script src="js/common.js"></script>
	<script type="text/javascript">
		$().ready(function() {
			$("#itemForm").validate();
		});
		// 查询方法
		function query(page) {
			var goodsName = $.trim($("#goodsName").val());
            var goodsItem = $.trim($("#goodsItem").val());
            var merId = $.trim($("#merId").val());
            var typeId;
            if($('#typeId').length > 0) {
            	try {
            		typeId = $('#typeId').combobox('getValue');
            	} catch (e){}	
            }	
            var upoff = $.trim($('#upoffId').val());
            var goodPunishSta = $.trim($("#goodPunishStaId").val());
            
			var params = {
		        "goodsDTO.goodsName" : goodsName,
		        "goodsDTO.goodsItem": goodsItem,
		        "goodsDTO.merId": merId,
		        "goodsDTO.typeId": typeId,
		        "goodsDTO.goodSaleSta": upoff,
		        "goodsDTO.goodsSta": goodPunishSta,
		        "orderProperty" : $("#orderProperty").val(),
		        "orderDirection" : $("#orderDirection").val(),
		        "goodsDTO.page" : page
		    }; 
		   ajaxData("base/goods!jsonPageList",params);
		   $('#chooseAll').bind('click',chooseAll);
		}
		
		// 批量下架
		function batchOffline() {
			if($("input[name='goodsDTO.goodsCheckId']:checked").length < 1) {
				alert("请勾选需要下架的商品!");
				return;
			}
			var checkV = $("input[name='goodsDTO.goodsCheckId']:checked");
			for(i=0;i<checkV.length;i++) {
				if($(checkV[i]).attr('up-off') == 'off') {
					alert('请勿选择已下架的商品重复下架!');
					return ;
				}
			}
			$('#methodId').val("batchOfflineGood");
			$('#batchForm').submit();
		}
		//批量上架
		function batchOnline() {
			if($("input[name='goodsDTO.goodsCheckId']:checked").length < 1) {
				alert("请勾选需要上架的商品!");
				return;
			}
			var checkV = $("input[name='goodsDTO.goodsCheckId']:checked");
			for(i=0;i<checkV.length;i++) {
				if($(checkV[i]).attr('up-off') == 'up') {
					alert('请勿选择已上架的商品重复上架!');
					return ;
				}
			}
			$('#methodId').val("batchUpLineGood");
			$('#batchForm').submit();
		}
		//选择全部
		function chooseAll() {
			if ($('#chooseAll').attr("checked") =="checked") {  
                $("input[name='goodsDTO.goodsCheckId']:checkbox").each(function() {
                    $(this).attr("checked", "checked");  
                })  
            } else {
            	$("input[name='goodsDTO.goodsCheckId']:checkbox").each(function() {
                    $(this).removeAttr("checked");  
                }) 
            }
		}
		//替换空格
		function replaceBlank(obj) {
			obj.value = obj.value.replace(/\s+/g,'');
		}
		//更多筛选条件
		function more_param(obj) {
			$('#button1').hide();
			$('#button2').show();
			$('#sencod-tr').show();
			$('#merId-th').show();
			$('#merId').show();
			$(obj).hide();
			$('#typeId').combotree({
				url:'base/goods!getTypeListAjax',
				method:'get'
			});
		}
		
	</script> 
	<style>
	</style>
</head>
<body onload="query(${goodsDTO.page});" >
	
	<div class="Position">
		当前位置是：基本设置 &gt;&gt; 商品管理 &gt;&gt; 商品信息管理
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	
	<div class="search">	
		<div class="Fl">
			<s:if test="#request.userLevel == 2">
				<my:permission key='sy-1705-02' value='商品添加'>
					<input type="button" class="formButton" value="添加商品" onclick="go('base/goods!addUI')"/>
				</my:permission>
			</s:if>	
		</div>	
		<table  class="searchTable" cellpadding="0" cellspacing="0">
			<s:if test="#request.userLevel != 0">
				<tr>
					<th align="right"  >所有分类:</th>
					<td><select  class="easyui-combotree" id="typeId" name="goodsDTO.typeId" data-options="url:'base/goods!getTypeListAjax',method:'get'" style="width:200px;"></select></td>
					<th align="right"  >上下架:</th>
					<td><s:select id="upoffId" list="#request.upoffList" listKey="key" listValue="value" name="goodsDTO.goodSaleSta" headerKey="-1" headerValue="全部" cssClass="formSelect" theme="simple"></s:select> </td>
				    <th align="right"  >处理状态:</th>
					<td><s:select id="goodPunishStaId" list="#request.goodsIllSta" listKey="key" listValue="value" name="goodsDTO.unRuleMaSta" headerKey="-1" headerValue="全部" cssClass="formSelect" theme="simple"></s:select> </td>
				    <th align="right" >商品名称:</th>
					<td><s:textfield id="goodsName" name="goodsDTO.goodsName" cssClass="formInput" maxlength="60" theme="simple"/></td>
					<th align="right"  >商品货号:</th>
					<td><s:textfield id="goodsItem" name="goodsDTO.goodsItem" onkeyup='replaceBlank(this)'  cssClass="formInput" maxlength="60" theme="simple"/></td>
					<td><input type="button" class="formButton"  value="查 询" onclick="query();"/></td>
				</tr>
			</s:if>
			<s:else>
				<tr>
				    <th align="right" >商品名称:</th>
					<td><s:textfield id="goodsName" name="goodsDTO.goodsName" cssClass="formInput" maxlength="60" theme="simple"/></td>
					<th align="right"  >商品货号:</th>
					<td><s:textfield id="goodsItem" name="goodsDTO.goodsItem" onkeyup='replaceBlank(this)'  cssClass="formInput" maxlength="60" theme="simple"/></td>
					<th align="right"  id="merId-th" style="display:none">商户名称:</th>
					<td><s:textfield id="merId" cssStyle="display:none" name="goodsDTO.merId" cssClass="formInput" maxlength="60" theme="simple"/></td>
		        	<td><input type="button" id="button1" class="formButton"  value="查 询" onclick="query();"/></td>
				</tr>
				<tr id='sencod-tr' style="display:none;">
				    <th align="right"  id="comboxtree-th"  >所有分类:</th>
					<td><select  id="typeId" name="goodsDTO.typeId" style="width:200px;"></select></td>
					<th align="right"  id="upoff-th"  >上下架:</th>
					<td><s:select id="upoffId"  list="#request.upoffList" listKey="key" listValue="value" name="goodsDTO.goodSaleSta" headerKey="-1" headerValue="全部" cssClass="formSelect" theme="simple"></s:select> </td>
					<th align="right"  id="dealsta-tr" >商品状态:</th>
					<td><s:select id="goodPunishStaId" list="#request.goodsSta" listKey="key" listValue="value" name="goodsDTO.goodsSta" headerKey="-1" headerValue="全部" cssClass="formSelect" theme="simple"></s:select> </td>
		        	<td><input type="button" id="button2" class="formButton"  value="查 询" onclick="query();"/></td>
				</tr>
				<tr>
					<th align="right" ></th>
					<td></td>
					<th align="right"  ></th>
					<td></td>
					<th align="right"></th>
					<td><a href="javascript:void(0)" onclick="more_param(this)"><img src="dtree/img/plus.gif"></img>更多高级筛选项</a></td>
		        	<td><br></td>
				</tr>
			</s:else>
		</table>
	</div>
	<form action="base/goods" id="batchForm" method="POST">
		<input type="hidden" name="method" id="methodId"/>
		<table width="96%" id="listTable" class="listTable" cellpadding="0" cellspacing="0" >
			<tr>
				<th width="3%"><input type="checkbox" id="chooseAll" />全选</th>
				<th width="10%"><a name="goodsId" class="sort">商品编号</a></th>
				<th width="10%"><a name="goodsItem" class="sort">商品货号</a></th>
				<th width="10%"><a name="goodsName" class="sort">商品名称</a></th>
				<th width="5%"><a name="typeId" class="sort">商品分类</a></th>
				<th width="5%"><a name="goodSaleSta" class="sort">上下架</a></th>
				<th width="5%">所属商户</th>
				<th width="5%">
					<s:if test="#request.userLevel != 0">
						<a name="unRuleMaSta" class="sort">
						处理状态
						</a>
					</s:if>
					<s:else>
						<a name="goodsSta" class="sort">
						商品状态
						</a>
					</s:else>
				</th>
				<th width="5%">相关操作</th>
			</tr>
		</table>
	</form>
	<div class="listBottom">
		<div class="Fl">
			<s:if test="#request.userLevel == 2">
				<my:permission key='sy-1705-02' value='商品添加'>
					<input type="button" class="formButton" value="添加商品" onclick="go('base/goods!addUI')"/>
				</my:permission>
			</s:if>
			<s:if test="#request.userLevel == 2">
				<my:permission key='sy-1705-05' value='商品上架'>
					<input type="button" class="formButton" value="批量上架" onclick="batchOnline()"/>
				</my:permission>
			</s:if>
			<my:permission key='sy-1705-06' value='商品下架'>
				<input type="button" class="formButton" value="批量下架" onclick="batchOffline()"/>
			</my:permission>
			
		</div>	
		<div class="Fr" id="pageNav">
			<s:property value="pageHTML" escape="false"/>
		</div>
	</div>
</body> 
</html>