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
	<script src="js/ajaxfileupload.js"></script>
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
	<script src="js/jquery/jquery.ui.tabs.js"></script>
	<script src="js/jquery/jquery.editable.min.js"></script>
	<script src="js/common.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<script src="js/datepicker/WdatePicker.js"></script>
	<script src="js/pubValidate.js"></script>
	<script type="text/javascript">
		var initialed = 0;
		var initialfor=0;
		$().ready(function() {
			$("#tabs").tabs({
				select: function(event, ui) { 
				// tab切换行为监听
					var goodsName = $('#goodsNameId').val();
					if($.trim(goodsName) == "") {
		            	pubErrorShow($("#goodNameErrorMsg"), ["商品名称不能为空!"]);
						return false;
					}else if($.trim(goodsName).length>50){
						pubErrorShow($("#goodNameErrorMsg"), ["商品名称不能超过50个字!"]);
						return false;
						}else{
							$("#goodNameErrorMsg").hide();
		    				$("#goodNameErrorMsg").html("");

							}
					  
					if(!$('#typeId').combobox('getValue')) {
						pubErrorShow($("#typeIdErrorMsg"), ["请选择商品分类!"]);
						return false;
					}
					if($.trim($('#goodsItemId').val()) == "") {
						pubErrorShow($("#goodItemErrorMsg"), ["商品货号不能为空!"]);
						return false;
					}
					
					if($('#rateInputId').length > 0) {
						if($('#rateInputId').val()== "" || isNaN($('#rateInputId').val())) {
							pubErrorShow($("#rateErrorMsg"), ["折扣率必须填写有效的折扣率!"]);
							return false;
						} else {
							var inputArray = $('input[name="dynamicGoodsPrice"]');
							for(i=0;i<inputArray.length;i++) {
								var vv = inputArray[i].value;
								$(inputArray[i]).next().next().val(vv * $('#rateInputId').val());
							}						
						}
					}
					
					if(ui.panel.id == 'tabs-2') {
						$('#showAgain').val($('#goodsNameId').val());
							  $("#showAgain").attr("readonly","true");
		              $("#showAgain").css({"background-color":"#D3D3D3","color":"#6D6D6D"});
						if(initialed == 0) {
							ajaxGetAttr();	
						}				
					} else if(ui.panel.id == 'tabs-3') {
						$('#showAgain2').val($('#goodsNameId').val());
							  $("#showAgain2").attr("readonly","true");
		              $("#showAgain2").css({"background-color":"#D3D3D3","color":"#6D6D6D"});
						if(initialfor == 0) {
							ajaxGetFormat();		
						}			
					}
				} 
			});
			
			//初始化商品分类树形
			$('#typeId').combotree({
				url:'base/goods!getTypeListAjax',
				method:'get',
				onSelect:function() {
					$("#typeIdErrorMsg").html("");
					$("#typeIdErrorMsg").attr("display","none");
					$("#typeIdErrorMsg").removeClass();
				}
			});
			
		});
		
		var witchLoading = "mainloading";
		var witchDiv = "mainResult";
		var maxRow = 1;
		// 异步上传图片操作
		function ajaxFileUpload(fileId,showdivId,loadingId,maxlength){  
		    $("#"+loadingId).show();
		    $.ajaxFileUpload({  
		        url:'base/goods!ajaxUploadPic',  
		        fileElementId: fileId,    
		        dataType:'json',         
		        success:function(data, status){   
		        	$("#"+loadingId).hide();
		        	var jsonData = data;
		        	if(jsonData.success==true) {
		        		// 生成时间戳
		        		var timestamp = Date.parse(new Date()); 
		        		//$("#"+showdivId).append("<span img-data='"+jsonData.url+"' class='imgSpan' pic-id='"+timestamp+"_pic'><img width='60px;' src='"+jsonData.url+"'><a href='javascript:void(0)' onclick='deleteImg(this,2)'><img src='images/ctc.jpg' class='delete'/></a></span>");
		        		if(showdivId=='mainResult'||  showdivId=='itemResult' || showdivId=='descResult') {
		        			$("#"+showdivId).append("<span img-data='"+jsonData.url+"' class='imgSpan' pic-id='"+timestamp+"_pic'><img width='60px;' src='"+jsonData.url+"'><a href='javascript:void(0)' onclick='deleteImg(this,1)'><img src='images/ctc.jpg' class='delete'/></a></span>");
		        			$('#hidden-div').append("<input type='hidden' name='goodsDTO."+showdivId+'Hidden'+"' id='"+timestamp+"_pic' value='"+jsonData.url+"'/>");
		        			if($("#"+showdivId).children().length >=maxlength) {
		        				$('#'+fileId).parent().hide();
		        			}
		        		} else {
		        			$('#'+fileId).parent().hide();
		        			$("#"+showdivId).append("<span img-data='"+jsonData.url+"' class='imgSpan' pic-id='"+timestamp+"_pic'><img width='60px;' src='"+jsonData.url+"'><a href='javascript:void(0)' onclick='deleteImg(this,2)' a-file-id='"+fileId+"'><img src='images/ctc.jpg' class='delete'/></a></span>");
		        			var hiddenInput = $("#"+showdivId).parent().find('input[class=img-hidden]');
		        			hiddenInput.val(jsonData.url);
		        		}
		        	} else {
		        		alert('上传图片失败!');
		        	}
		        },  
		        error: function(data, status, e){ 
		           alert('上传图片失败!');
		           $("#"+loadingId).hide();
		        }  
		    });  
		}  
		
		// 弃用
		function clickUploadButton(obj,showd,showl) {
			if(obj == 1) {
				if($('#mainResult').children().length >= 1) {
					alert('商城展示图片只能上传一张!');
					return;
				}
				witchLoading = "mainloading";
				witchDiv = "mainResult";
			} else if(obj == 2) {
				if($('#itemResult').children().length > 5) {
					alert('商品图片不能超过6张!');
					return;
				}
				witchLoading = "itemloading";
				witchDiv = "itemResult";
			} else if(obj == 3) {
				if($('#descResult').children().length > 5) {
					alert('商品描述图片不能超过6张!');
					return;
				}
				witchLoading = "descloading";
				witchDiv = "descResult";
			} else if(obj == 4) {
				if($('#'+showd).children().length > 0) {
					alert('库存图片不能超过1张!');
					return;
				}
				witchLoading = showl;
				witchDiv = showd;
			} else {
				return;
			}
			$('#fileId').click();
		}
		
		// 图片后缀名校验
		function fileChange(obj) {
			var strRegex = "(.jpg|.png|.gif|.ps|.jpeg)$"; //用于验证图片扩展名的正则表达式
	        var re=new RegExp(strRegex);
	        if (!re.test($(obj).val().toLowerCase())){
	        	alert('文件类型不符合!');
	        	return;
	        }
	        if($('#'+$(obj).attr('show-div')).children().length >= $(obj).attr('max-length')) {
	        	alert('最多只能上传'+$(obj).attr('max-length')+"张");
	        	//$(obj).val("");
	        	var fileObj = $(obj);
	        	fileObj.replaceWith(fileObj.clone());
	        	return;
	        }
			ajaxFileUpload($(obj).attr('id'),$(obj).attr('show-div'),$(obj).attr('loading-id'),$(obj).attr('max-length'));
		}
		
		// 删除图片
		function deleteImg(obj,deleteType) {
			if(confirm("确认要删除？")){
				if(deleteType == 1) {
					var parrent = $(obj).parent(".imgSpan");
					var showDivDom = parrent.parent();
					var dataId = parrent.attr('pic-id');
					$('#'+dataId).remove();
					parrent.remove();
					if(showDivDom.attr('id') == 'mainResult') {
						$('#f1Id').parent().show();
					} else if(showDivDom.attr('id') == 'itemResult') {
						$('#f2Id').parent().show();
					} else if(showDivDom.attr('id') == 'descResult') {
						$('#f3Id').parent().show();
					}
				}else if(deleteType ==2) {
					if($(obj).attr('a-file-id')!="") {
						$('#'+$(obj).attr('a-file-id')).parent().show();
					}
					$(obj).parent().parent().parent().find("input[class=img-hidden]").val(" ") ;
					$(obj).parent().remove();
				}
			}	
		}
		
		// 校验折扣率
		function valThis(obj) {
			var tempValue = obj.value;
			if($.trim(tempValue)=="") {
				pubErrorShow($("#rateErrorMsg"), ["请填写折扣率!"]);
				return false;
			}
			if(isNaN(tempValue)) {
				pubErrorShow($("#rateErrorMsg"), ["折扣率必须填写有效的数字!"]);
				return false;
			}
			if(tempValue <=0 ||  tempValue >1) {
				pubErrorShow($("#rateErrorMsg"), ["折扣率范围必须是大于0小于等于1"]);
				return false;
			}
			$("#rateErrorMsg").hide();
			$("#rateErrorMsg").html("");
		}
		
		// 提交保存
		function submitForms() {
			var goodsName = $('#goodsNameId').val();
			if($.trim(goodsName) == "") {
            	pubErrorShow($("#goodNameErrorMsg"), ["商品名称不能为空!"]);
				return false;
			}else if($.trim(goodsName).length>50){
				pubErrorShow($("#goodNameErrorMsg"), ["商品名称不能超过50个字!"]);
				return false;
				}else{
					$("#goodNameErrorMsg").hide();
    				$("#goodNameErrorMsg").html("");

					}
			if(!$('#typeId').combobox('getValue')) {
				pubErrorShow($("#typeIdErrorMsg"), ["请选择商品分类!"]);
				return false;
			}
			if($.trim($('#goodsItemId').val()) == "") {
				pubErrorShow($("#goodItemErrorMsg"), ["商品货号不能为空!"]);
				return false;
			}
			
			if(!!$('#isProId').attr('checked')) {
				if($('#proSelectId').val() == -1) {
					alert('请选择活动!');
					return false;
				}
			}
			
			var rateInput = $('.rate-class');
			if(rateInput.length > 0) {
				var tempValue = rateInput[0].value;
				if($.trim(tempValue)=="") {
					alert('请填写折扣率!');
					return false;
				}
				if(isNaN(tempValue)) {
					alert('折扣率必须填写有效的数字!');
					return false;
				}
				if(tempValue <=0 ||  tempValue >1) {
					alert('折扣率范围必须是大于0小于等于1');
					return false;
				}
			}
			
			if($('#goodDescId').val().length > 1000) {
				alert("商品信息描述不能超过1000字!");
				return false;
			}
			
			var dynamicGoodPrices = $('input[name="dynamicGoodsPrice"]');			
			for(i=0;i<dynamicGoodPrices.length;i++) {
				if($.trim(dynamicGoodPrices[i].value)== "") {
					alert('请填写商品店铺价格!');
					return false;
				}
				if(isNaN(dynamicGoodPrices[i].value)) {
					alert('请填写正确的店铺价格!');
					return false;
				}
				if(!(dynamicGoodPrices[i].value >0)) {
					alert('店铺价格必须大于0!');
					return false;
				}
			}
			
			var purchasePrice = $('input[name="purchasePrice"]');		
			for(i=0;i<purchasePrice.length;i++) {
				if($.trim(purchasePrice[i].value)== "") {
					alert('请填写商品进价!');
					return false;
				}
				if(isNaN(purchasePrice[i].value)) {
					alert('请填写正确的进价!');
					return false;
				}
				if(!(purchasePrice[i].value >0)) {
					alert('进价必须大于0!');
					return false;
				}
			}
			
			var dynamicMarketGoodPrices = $('input[name="dynamicMarketGoodsPrice"]');			
			for(i=0;i<dynamicMarketGoodPrices.length;i++) {
				if($.trim(dynamicMarketGoodPrices[i].value)== "") {
					alert('请填写商品市场价格!');
					return false;
				}
				if(isNaN(dynamicMarketGoodPrices[i].value)) {
					alert('商品市场价格必须是数字!');
					return false;
				}
				if(!(dynamicMarketGoodPrices[i].value > 0)) {
					alert('商品市场价格必须是数字!');
					return false;
				}
			}
			
			var dynamicStockNos = $('input[name="dynamicStockNo"]');	
			var ex = /^\d+$/;		
			for(i=0;i<dynamicStockNos.length;i++) {
				if($.trim(dynamicStockNos[i].value)== "") {
					alert('请填写商品库存数!');
					return false;
				}
				if(isNaN(dynamicStockNos[i].value)) {
					alert('商品库存数必须是数字!');
					return false;
				}
				if(dynamicStockNos[i].value < 1) {
					alert('商品库存数必须是大于0的整数!');
					return false;
				}
				if (!ex.test(dynamicStockNos[i].value)) {
				   alert('商品库存数必须是整数!');
				   return false;
				}
			}
			
			var attrInput = $('input[class=input-attr]');
			for(i=0;i<attrInput.length;i++) {
				if($.trim(attrInput[i].value) == "") {
					alert('商品属性值不能为空!');
					return false;
				}
			}
			
			if(!validateDynamic()) {
				alert('商品属性配置不能重复!');
				return false;
			}
			
			var formatInput = $('input[class=format-class]');
			for(i=0;i<formatInput.length;i++) {
				if($.trim(formatInput[i].value) == "") {
					alert('规格不能为空!');
					return false;
				}
				if(formatInput[i].value.length > 60) {
					alert('规格值长度不能超过60');
					return false;
				}
			}
			
			
			$('#commonForm').submit();
		}
		// 描述长度校验
		function goodsDescOnblur(obj) {
			if($(obj).val().length > 1000) {
				pubErrorShow($("#goodDescErrorMsg"), ["商品信息描述不能超过1000字!"]);
			} else {
				$("#goodDescErrorMsg").hide();
				$("#goodDescErrorMsg").html("");
			}
		}
		
		//商品名称校验
		function goodsNameOnblur(obj) {
			var type = ["isNull"];
            var goodNameErrorMsg=["商品名称不能为空!"];
            var flag= showErrorMsg(obj,type,goodNameErrorMsg,"goodNameErrorMsg","goodNameErrorMsg");
         
            if(flag==true){
            	if($(obj).val().length > 50) {
    				pubErrorShow($("#goodNameErrorMsg"), ["商品名称不能超过50字!"]);
    			} else {
    				$("#goodNameErrorMsg").hide();
    				$("#goodNameErrorMsg").html("");
    			}
                }
            
		}
		// 商品货号校验
		function goodsItemOnBlur(obj) {
			var type = ["isNull"];
            var goodItemErrorMsg=["商品货号不能为空!"];
            showErrorMsg(obj,type,goodItemErrorMsg,"goodItemErrorMsg","goodItemErrorMsg");
		}
		// 选择活动时select变化
		function choosePro(obj) {
			if(obj.checked){
				$('#proSelectId').attr("disabled",false);
			}else {
				$('#proSelectId').val(-1);
			    $('#proSelectId').attr("disabled",true); 	
			    $('#rateId').html("");
			}
		}
		// 动态校验商品属性重复问题
		function validateDynamic() {
			var $allChildren = $('#att-div').children();
			var map = {};
			for(i=0;i<$allChildren.length;i++) {
				// 找到所有兄弟节点,jquery是逆向查找的
				var $brothers = $($allChildren[i]).find('input[name="dynamicGoodsPrice"]').prevAll();
				var keys = "";
				for(j=0;j<  $brothers.length ; j++) {
					if($($brothers[j]).is('input')) {
						keys = keys +"_"+$($brothers[j]).val();
					} else if($($brothers[j]).is('span')) {
						keys = keys +"_"+$($brothers[j]).find('input[class=combo-value]').val();
					} else if($($brothers[j]).is('select') && !$($brothers[j]).hasClass('edit-select')) {
						keys = keys +"_"+$($brothers[j]).val();
					}
				}
				if(map[keys] &&　map[keys]　== 1) {
					return false;
				} else {
					map[keys] = 1
				}
			}
			return true;
		}
		// 添加一行属性
		function addrow(obj) {
			if($('#att-div').children().length >= maxRow) {
				alert('不能添加更多属性!');
				return;
			}
			var copys = $(obj).parent().parent().clone(true);
			var randtime = new Date().getTime();
			copys.find('a[class=class-plus]').html("【&minus;】");
			copys.find('span[class=img-span]').attr("id","img_"+randtime);
			copys.find('img[class=loading-span]').attr("id","loading_"+randtime);
			copys.find('span[class=img-span]').html("");
			var showHtml = copys.html().replace("addrow(this)","deleterow(this)");
			var htmlObj = $("<div style='margin-top:20px;float:none;width:100%'>"+showHtml+"</div>");
			var fileClick = htmlObj.find('input[class=inputstyle]');
			fileClick.attr('id','fId_'+randtime);
			fileClick.attr('show-div','img_'+randtime);
			fileClick.attr('loading-id','loading_'+randtime);
			fileClick.parent().show();
			htmlObj.find('span[class=combo]').remove();
			$('#att-div').append(htmlObj);
			
			$('.edit-select').combobox();
		}
		// 删除一行属性
		function deleterow(obj) {
			$(obj).parent().parent().remove();
		}
		// 异步获取属性
		function ajaxGetAttr() {
				var typeId = $('#typeId').combobox('getValue');
			$.get("base/goods!ajaxQueryAttr", {"goodsDTO.typeId":typeId},
				function(result){
					initialed = 1;
					var trs = "<div style='margin-top:50px;float:none;width:100%'><div style='float:left;'><a href='javascript:void(0)' class='class-plus' onclick='addrow(this)'>【+】</a>";  
					$('#attCount').val(result.attList.length);
					$.each(result.attList,function(n,value) {   
			            if((value.isEn == 0 || value.isEn ==2) && value.attValueList.length > 0) {
			            	if(value.isEn ==0) {
		            			trs = trs + value.attrName+":<select name='dynamicAttr_s_"+n+"_"+value.attrId+"'  style='width:80px;'>";
		            		} else {
		            			trs = trs + value.attrName+":<select name='dynamicAttr_i_"+n+"_"+value.attrId+"' class='edit-select' style='width:80px;'>";
		            		}
			            	if(value.isEn == 0 && maxRow < 2147483647) {
				            	maxRow *= value.attValueList.length;
				            } else {
				                // Java整型最大范围
				            	maxRow = 2147483647;
					        }
			            	for(i=0;i<value.attValueList.length;i++) {
			            		if(value.isEn ==0) {
			            			trs = trs +"<option value='"+value.attValueList[i].attrEnName+"'>"+value.attValueList[i].attrEnName+"</option>"
			            		} else {
			            			trs = trs +"<option value='"+value.attValueList[i].attrEnName+"'>"+value.attValueList[i].attrEnName+"</option>"
			            		}
			            	}
			            	trs = trs+"</select>  ";
			            	
			            } else {
			            	maxRow = 2147483647;
		            		trs = trs + value.attrName+":<input type='text' name='dynamicAttr_i_"+n+"_"+value.attrId+"' class='input-attr' style='width:30px;' value=' '/>"
			            }
				  });  
					trs = trs + "店铺价格：<input type='text' name='dynamicGoodsPrice' onkeyup='calculateProPrice(this)' class='short-input' />(元)进价：<input type='text' name='purchasePrice'  onkeyup='checkNum(this)' class='short-input' />(元) 市场价格：<input type='text' name='dynamicMarketGoodsPrice' onkeyup='checkNum(this)' class='short-input' />(元) 活动价格：<input type='text' name='14' class='short-input' readonly='readonly' value=' '/>(元) 库存：<input type='text' name='dynamicStockNo' class='short-input'/> 选择图片：</div>"
			        var randtime = new Date().getTime();
			        var clickDom = $("<div style='float:left;'><div class='div1'><div class='div2'></div><input type='file' id='fId_"+randtime+"' class='inputstyle' name='goodsDTO.uploadFile' show-div='img_"+randtime+"' max-length='1' loading-id='loading_"+randtime+"' onchange='fileChange(this)' ></div></div>");
			        //clickDom.bind('click',function() {
			        //	clickUploadButton(4,'img_'+randtime,'loading_'+randtime);});
			        trs += "<span id='img_"+randtime+"' class='img-span' style='float:left;'></span><input type='hidden' class='img-hidden' name='dynamicPicUrl'  value=' '/><img id='loading_"+randtime+"' class='loading-span' src='images/loading.gif' style='display:none;'/></div>";
			        var trObj = $(trs);
			        var ot = trObj.find('span[class=img-span]');
			        clickDom.insertAfter(ot);
				    $('#att-div').append(trObj);		
				    $('.edit-select').combobox();
				},
				'json');
				
		}
		
		// 异步获取规格
		function ajaxGetFormat() {
			var typeId = $('#typeId').combobox('getValue');
			$.get("base/goods!ajaxQueryFormatGroup", {"goodsDTO.typeId":typeId},
				function(result){
					initialfor = 1;
					$.each(result.formatGroup,function(n,value) {   
						var trs = "<div style='margin-top:10px;margin-bottom:10px;'><fieldset style='margin:20px;padding-bottom:20px;padding-left:20px;'><legend>"+value.groupName+"</legend><table width='100%' border='0' cellpadding='0' cellspacing='0' class='formTable'>";  
						$.each(value.formatList,function(index,format) {
							trs = trs+"<tr><th align='right' width='30%'>"+format.formatName+"&nbsp; :&nbsp;</th> "+"<td width='70%'><input type='text' name='formatId_"+format.formatId+"' class='format-class'></td>";
						});
						trs = trs+"<br/></fieldset></div>";
					    $('#format-div').append(trs);		
				  });  
				},
				'json');
		}
		
		// 计算活动价格
		function calculateProPrice(obj) {
			checkNum(obj);
			
			if($('#rateInputId').length > 0) {
				var rate = $('#rateInputId').val();
				if(isNaN(obj.value)) {
					return false;
				}
				var proPrice = rate * obj.value;
				$(obj).next().next().val(proPrice);
			}
		}
		
		// 切换活动时候页面变化
		function changeselect() {
			if($('#proSelectId').val() == -1) return;
			$.get("base/goods!ajaxQueryPromotionByID", {"goodsDTO.proId":$('#proSelectId').val()},
				function(result){
					if(result.promotion.rateSign == "1") {
						$('#rateId').html("折扣率：<input type='hidden' id='rateInputId' value='"+result.promotion.suggRate+"' />"+result.promotion.suggRate);
					} else {
						$('#rateId').html("折扣率：<input type='text' id='rateInputId' name='goodsDTO.rate' class='rate-class' onblur='valThis(this)' style='width:30px;' /><label id='rateErrorMsg' style='display: none;'></label>");
					}
				},
				'json');
		}
		
		function checkNum(obj){ 
			//先把非数字的都替换掉，除了数字和.
			obj.value = obj.value.replace(/[^\d.]/g,"");
			//必须保证第一个为数字而不是.
			obj.value = obj.value.replace(/^\./g,"");
			//保证只有出现一个.而没有多个.
			obj.value = obj.value.replace(/\.{2,}/g,".");
			//保证.只出现一次，而不能出现两次以上
			obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	    } 
		
	</script> 
	<style type="text/css">
		.table-tr {
			width:18%;
			text-align:right
		}
		.table-r-tr {
			padding-left:10px;
		}
		.upload-button {
			background-image: url(images/z1.gif);
		}
		.imgSpan{
			display : inline-block;
			position : relative;
		}
		.imgSpan .delete{
			position : absolute;
			top : 0px;
			right : 0px;
			width : 12px;
			height : 12px;
			display:inline;
		}
		.short-input {
			width:30px;
		}
		.div1{
		float: left;
		height: 20px;
		background:url('images/z1.gif' );
		width: 20px;
		position:relative;
		}
		.div2{
		text-align:center;
		padding-top:12px;
		font-size:15px;
		font-weight:800
		}
		.inputstyle{
		    width: 20px;
		    height: 20px;
		    cursor: pointer;
		    font-size: 30px;
		    outline: medium none;
		    position: absolute;
		    filter:alpha(opacity=0);
		    -moz-opacity:0;
		    opacity:0; 
		    left:0px;
		    top: 0px;
		}
	</style>
</head>
<body>
	<div class="Position">
		当前位置是：基本设置 &gt;&gt; 商品管理 &gt;&gt; 商品信息管理
	</div>
	<div style="width:100%;text-align: center">
		<div id="tabs" style="width:1000px; margin-right: auto;margin-left: auto; height:auto;">
			<ul>
				<li><a href="#tabs-1">商品通用信息</a></li>
				<li><a href="#tabs-2" >商品库存信息</a></li>
				<li><a href="#tabs-3">商品规格管理</a></li>
			</ul>
			<s:form action="base/goods" id="commonForm" method="post" theme="simple" >
			<s:hidden name="method" id="method"></s:hidden>
			<input type="hidden" id="attCount" name="dynamicCount" value="0"/>
			<div id="tabs-1">
					<div>
					    <table style="text-align:left;width:100%;padding-left:10px; border-spacing:10px; " class="formTable">
					        <tr>
					    		<td class="table-tr">商品名称：<br/><br/><br/><br/><br/></td>
					    		<td class="table-r-tr"><s:textarea name="goodsDTO.goodsName" onblur="goodsNameOnblur(this)" id="goodsNameId" theme="simple" cssClass="formTextarea"></s:textarea><label id="goodNameErrorMsg" style="display: none;"></label></td>
					    	</tr>
					    	                  
					    	<tr>
					    		<td  class="table-tr">商品分类：</td>
					    		<td class="table-r-tr"><select  id="typeId" name="goodsDTO.typeId"  style="width:305px;"></select><label id="typeIdErrorMsg" style="display: none;"></label></td>
					    	</tr>
					    	<tr>
					    		<td class="table-tr">商品货号：</td>
					    		<td class="table-r-tr"><s:textfield name="goodsDTO.goodsItem" id="goodsItemId" cssClass="formInput" theme="simple"  style="width:300px;" onblur="goodsItemOnBlur(this)" maxlength="15"></s:textfield><label id="goodItemErrorMsg" style="display: none;"></label></td>
					    	</tr>
					    	<tr>
					    		<td class="table-tr">是否是活动商品：</td>
					    		<td class="table-r-tr"><s:checkbox name="123" id="isProId" onclick="choosePro(this)" theme="simple" value="0" fieldValue="1"></s:checkbox>是 &nbsp;&nbsp;<label >活动名称：</label><s:select disabled="true" headerKey="-1" headerValue="请选择" id="proSelectId" list="#request.promotionList" onchange="changeselect()" listKey="proid" listValue="proname"  theme="simple" name="goodsDTO.proId"></s:select><span id="rateId"></span> </td>
					    	</tr>
					    	<tr>
					    		<td class="table-tr">上架：</td>
					    		<td class="table-r-tr"><input type="checkbox" name="goodsDTO.goodsSaleStas"  value="0"/> 打钩允许销售，否则不允许销售</td>
					    	</tr>
					    	<tr>
					    		<td class="table-tr">是否为免运费商品：</td>
					    		<td class="table-r-tr"><input type="checkbox" name="goodsDTO.isFreeTran" value="1" /> 打钩则表示免运费，否则按照正常方式计价</td>
					    	</tr>
					    	<tr>
					    		<td class="table-tr">详细描述：<br/><br/><br/><br/><br/></td>
					    		<td class="table-r-tr"><s:textarea name="goodsDTO.goodDescr" onblur='goodsDescOnblur(this)' id="goodDescId" theme="simple" cssClass="formTextarea"></s:textarea><label id="goodDescErrorMsg" style="display: none;"></label></td>
					    	</tr>
					    	<tr>
					    		<td class="table-tr">商城主界面展示图片：</td>
					    		<td class="table-r-tr">
					    			<div id="mainResult" style="float:left;"></div>
					    			<div style="float:left;margin-left:20px;">
						    			<div class="div1">
										    <div class="div2"></div>
										    <input type="file" id='f1Id' class="inputstyle" name='goodsDTO.uploadFile' show-div='mainResult' max-length='1' loading-id='mainloading' onchange="fileChange(this)" >
										</div>
										<span><img id="mainloading" src="images/loading.gif" style="display:none;"/></span>
					    			</div>
					    		</td>
					    	</tr>
					    	<tr>
					    		<td class="table-tr">上传商品图片：</td>
					    		<td class="table-r-tr">
					    		<div id="itemResult" style="float:left;"></div><div style="float:left;margin-left:20px;">
					    		<div class="div1">
								    <div class="div2"></div>
								    <input type="file" id='f2Id' class="inputstyle" name='goodsDTO.uploadFile' show-div='itemResult' max-length='5' loading-id='itemloading' onchange="fileChange(this)" >
								</div>
					    		<span><img id="itemloading" src="images/loading.gif" style="display:none;"/></span></div></td>
					    	</tr>
					    	<tr>
					    		<td class="table-tr">商品详情介绍图片：</td>
					    		<td class="table-r-tr">
					    		<div id="descResult" style="float:left;"></div>
					    		<div style="float:left;margin-left:20px;">
					    			<div class="div1">
									    <div class="div2"></div>
									    <input type="file" id='f3Id' class="inputstyle" name='goodsDTO.uploadFile' show-div='descResult' max-length='20' loading-id='descloading' onchange="fileChange(this)" >
									</div>
					    			<span><img id="descloading" src="images/loading.gif" style="display:none;"/></span>
					    		</div></td>
					    	</tr>
					    </table>
					</div>
					<div id="hidden-div">
												
					</div>
			</div>
			<div  id="tabs-2">
				<div align="left" >
					商品名称：<!--<input type="text" id="showAgain" class="formInput" disabled="disabled"/>-->
					<textarea  id="showAgain" class="formInput" style="width:600px;"></textarea>
					<div id="att-div">
						
					</div>
				</div>
			</div>
			<div  id="tabs-3">
				<div align="left">
					商品名称：
						<textarea  id="showAgain2" class="formInput" style="width:600px;"></textarea>
					<div id="format-div">
						
					</div>
				</div>	
			</div>
			</s:form>
	    </div>
	    <div class='formTableBottom' >
			   <input type="submit" value="保存" class="formButton" id="submitInput" onclick="submitForms()"/>
			   <input type="button" class="formButton" onclick="go('base/goods!list')" value="返回"/>
		</div>
	 </div>
</body> 
</html>