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
		<title>商品销售</title>
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
		<link href="resources/css/style.css" rel="stylesheet" type="text/css" />
		<script src="js/common.js"></script>
		<script src="js/pubValiPattern.js"></script>
		<script src="js/pubValidate.js"></script>
		<script type="text/javascript">
		var countFlag=false;
		function checkCount(){
			    var id=$("#id").val();
			    var count=$("#count").val();
				if(count.trim()==""){
					$("#countMsg").html("");
					$("#countMsg").html("请输入销售商品数量!");
					$("#countMsg").show();
					countFlag=false;
				}else{
					if(count==0){
						$("#countMsg").html("");
						$("#countMsg").html("购买数量不能为0!");
						$("#countMsg").show();
						countFlag=false;
					}else{
						$("#countMsg").hide();
						/**Ajax传递给struts的参数，用来实例化对象*/
						var params = {
							"goods.id":id,
							"shopCar.count":count
					    };
						var dataUrl="managers/goods!checkCount";
						$.ajax({
							url : dataUrl,
							data : params,
							dataType : "json",
							cache : false,
							type : "POST",
							error : function(textStatus, errorThrown) {
							},
							 beforeSend : function(){
							 },
							success : function(data, textStatus) {
								if(data==true){
									countFlag=false;
									$("#countMsg").html("");
									$("#countMsg").html("购物车销售数量超过库存的数量！请减少出售的数量!")
									$("#countMsg").show();
								}else{
									countFlag=true;
									$("#countMsg").html("");
									$("#countMsg").hide();
								}
							}
						});
					}
				}
			}
		function check(){
			checkCount();
			if(countFlag){
				return true;
			}else{
				return false;
			}
		}
</script>
	</head>
	<body>
		<div class="Position">
			当前位置是：入库管理 &gt;&gt;商品销售
		</div>
		<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
		<%--主任务修改对话框开始--%>
			<s:form action="shopcar/shopcar" method="post" theme="simple" onsubmit="return check();">
				<s:hidden name="method" id="method" />
				<s:hidden name="goods.id" id="id" />
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formTable">
					<tr>
						<th style="text-align: center; width: 10%;">
							商品名称:
						</th>
						<td style="text-align: left;">
							${goods.goodsName}
						</td>
						<th style="text-align: center; width: 10%;">
							品牌:
						</th>
						<td style="text-align: left;">
						${goods.brand}
						</td>
					</tr>
					<tr>
						<th style="text-align: center; width: 10%;">
							型号:
						</th>
						<td style="text-align: left;">
						${goods.model }
						</td>
						<th style="text-align: center; width: 10%;">
							价格:
						</th>
						<td style="text-align: left;">
						${goods.price}元
						</td>
					</tr>
					<tr>
						<th style="text-align: center; width: 10%;">
							库存数量:
						</th>
						<td style="text-align: left;">
						${goods.count }
						</td>
						<th style="text-align: center; width: 10%;">
							厂家名称:
						</th>
						<td style="text-align: left;">
						${goods.merchant }
						</td>
					</tr>
					<tr>
						<th style="text-align: center; width: 10%;">
							状态:
						</th>
						<td style="text-align: left;">
							${goodsDTO.statusName }
						</td>
						<th style="text-align: center; width: 10%;">
							进货日期:
						</th>
						<td style="text-align: left;">
						${goods.purchaseDate}
						</td>
					</tr>
					<tr>
						<th style="text-align: center; width: 10%;">
							类别:
						</th>
						<td style="text-align: left;" colspan="3">
						${goodsDTO.goodsTypeName }
						</td>
					</tr>
					<tr>
						<th style="text-align: center; width: 10%;">
							厂家地址:
						</th>
						<td style="text-align: left;">
						${goods.address }
						</td>
						<th style="text-align: center; width: 10%;">
							商品描述:
						</th>
						<td style="text-align: left;">
							${goods.goodsDesc }
						</td>
					</tr>
				</table>
			
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formTable">
					<tr>
						<th style="text-align: center; width: 10%;">
							购买数量:
						</th>
						<td style="text-align: left;">
							<s:textfield name="shopCar.count" id="count" maxlength="5"
								cssClass="formInput" theme="simple" onblur="checkCount();" onkeyup="replaceToNum(this);" />
								<label id="countMsg" class="errorMsg"  
								style="display: none;"></label>
						</td>
					</tr>
				</table>
					<div class="formTableBottom">
				<s:if test="method=='addSave'">
				<input id="submitInput" type="button" class="formButton"
							value="查看购物车"  onclick="go('shopcar/shopcar')"/>
						<input id="submitInput" type="submit" class="formButton"
							value="加入购物车"  />
				</s:if>
				<input type="button" class="formButton" value="返 回"
					onclick="go('managers/goods!list')" />
			</div>
			</s:form>
	</body>
</html>