<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%@page import="com.paySystem.ic.utils.Utils"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String goodsId = (String)request.getAttribute("goodsId");
	String goodsName = (String)request.getAttribute("goodsName");
	String goodsLittPho = (String)request.getAttribute("goodsLittPho");
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<base href="<%=basePath%>" />
	<title>评价管理</title>
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
	<script src="js/jquery/jquery.ui.tabs.js"></script>
	<script src="js/common.js"></script>
	<script src="js/pubValidate.js"></script>
    <script src="js/pubValiPattern.js"></script>
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<style type="text/css">
        .star_wrap, .ulStars { width: 120px; height: 18px; overflow: hidden; float: left; }
        .ulStars li { width: 19px; height: 18px; background: url(<%=basePath%>/images/xing_bg.gif) no-repeat -278px -96px; float: left; margin-right: 5px; cursor: pointer; }
        .ulStars li.good { background: url(<%=basePath%>/images/xing_bg.gif) no-repeat -278px -52px; }
        .ulStars li.bad { background: url(<%=basePath%>/images/xing_bg.gif) no-repeat -278px -73px; }
        .txtStar { position: absolute; left: 0; top: -30px; }
	</style>
	<script type="text/javascript">
		$(function() {
			 for(var i=1;i<=3;i++){
			 	//initXing(i);
			 }
		});
		//显示星星
		function initXing(index){
			var ulStars = $("#ulStars_"+index);
            var txtStar = $("#txtStar_"+index);
            var star_wrap = $("#star_wrap_"+index);
            var oLis = $("#ulStars_"+index+" li");
            oLis.each(function (i) {
                $(this).click(function () {
                    var iStar = parseInt($(this).attr("star"), 10);
                    txtStar.val(iStar);
                }).hover(function () {
                    var iStar = parseInt($(this).attr("star"), 10);
                    txtStar.val(iStar);
                    for (var i = 0; i < oLis.length; i++) {
                        var _temp = oLis[i];
                        if (_temp.attributes["star"].value <= iStar) {
                            if (iStar >= 3) {
                                _temp.className = "good";
                            }
                            else {
                                _temp.className = "bad";
                            }
                        }
                        else {
                            _temp.className = "";
                        }
                    }
                }, function () {
                    if (txtStar.val() != "") {
                        var iSelectedStar = parseInt(txtStar.val(), 10);
                        for (var i = 0; i < oLis.length; i++) {
                            var _temp = oLis[i];
                            if (_temp.attributes["star"].value > iSelectedStar) {
                                _temp.className = "";
                            }
                            else {
                                var iSelfStar = parseInt(_temp.attributes["star"].value, 10);
                                if (iSelfStar >= 3) {
                                    _temp.className = "good";
                                }
                                else {
                                    if (iSelectedStar >= 3) {
                                        _temp.className = "good";
                                    }
                                    else {
                                        _temp.className = "bad";
                                    }
                                }
                            }
                        }
                    }
                });
            });
		}
		//计算长度
		function toCharLength(str){
			return str.replace(/[^x00-xff]/mg,"JJ").length;
		}
		//检查评价内容字符长度
	   var descFlag=true;
	   function checkDescrLenght(){
	   		if(toCharLength($("#context").val())>255){
				descFlag = false
				pubErrorShow($("#contextErrorMsg"),"评价内容不能大于120个汉字");
				return;
			}
			descFlag = true;
			$("#contextErrorMsg").hide();
	   }
	  //提交检查
		function check(){
			 checkDescrLenght();
			 if(descFlag==false){
			 	return false;
			 }
			 return true;
		}
	</script> 
</head>
<body>
	<div id="mainContent">
	<div class="Position">
		当前位置是：HOME &gt;&gt; 评价管理
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	<s:form action="/evaluation/evaluation" method="post" onsubmit="document.getElementById('submitInput').disabled = true;return true;" theme="simple">
	<s:hidden name="method" id="method"></s:hidden>
	<s:hidden name="creditRecordDTO.orderId" id="method"></s:hidden>
	<div>
		<div style="margin:20px 40px;">
			<div style="float:left;width:220px;margin-top:30px;">
				<img src="<%=goodsLittPho%>" style="width:150px;height:120px"><br/>
				<a href="#"><%=goodsName %></a>
			</div>
			<div style="float:left">
				<div>
					<s:radio name="creditRecordDTO.appType" listKey="key" listValue="value"  list="#request.appTypes" theme="simple"></s:radio>
				</div>
				<div>
					<s:textarea  name="contextDTO.context" id="context" maxLength="255"  cssClass="formTextarea" style="width:380px;height:200px" onblur="checkDescrLenght()"></s:textarea>
					<br>
					<label id="contextErrorMsg" style="display: none;"></label>
				</div>
			</div>
			<div style="clear:both"></div>
		</div>
		<!-- 
		<div style="margin:20px 40px;">
			<table style="width:400px;">
				<tr>
					<td>宝贝与描叙的相符度</td>
					<td align="right">
						<div id="starBox_1" class="starBox">
					        <div id="star_wrap_1" class="star_wrap">
					            <ul id="ulStars_1" class="ulStars">
					                <li star="1"></li>
					                <li star="2"></li>
					                <li star="3"></li>
					                <li star="4"></li>
					                <li star="5"></li>
					            </ul>
					        </div>
					        <s:hidden name="creditRecordDTO.goodsMatch" id="txtStar_1" ></s:hidden>
					    </div>
					</td>
				</tr>
				<tr>
					<td>卖家的服务态度</td>
					<td>
						<div id="starBox_2" class="starBox">
					        <div id="star_wrap_2"  class="star_wrap">
					            <ul id="ulStars_2" class="ulStars">
					                <li star="1"></li>
					                <li star="2"></li>
					                <li star="3"></li>
					                <li star="4"></li>
					                <li star="5"></li>
					            </ul>
					        </div>
					        <s:hidden name="creditRecordDTO.serviceAtt" id="txtStar_2" ></s:hidden>
					    </div>
					</td>
				</tr>
				<tr>
					<td>卖家的发货速度</td>
					<td>
						<div id="starBox_3" class="starBox">
					        <div id="star_wrap_3"  class="star_wrap">
					            <ul id="ulStars_3" class="ulStars">
					                <li star="1"></li>
					                <li star="2"></li>
					                <li star="3"></li>
					                <li star="4"></li>
					                <li star="5"></li>
					            </ul>
					        </div>
					        <s:hidden name="creditRecordDTO.sendSpead" id="txtStar_3" ></s:hidden>
					    </div>
					</td>
				</tr>
			</table>
		</div>
		 -->
		<div style="margin-left:300px;">
			<s:if test="method=='addSave'">
			 	<input id="submitInput" type="submit" class="formButton" value="发表评论" onclick="return check();"/>
			</s:if>
		</div>
	</div>
	</s:form>
	</div>
	<dl class="H_return" style="display:none" id="resultDIV">
			<dt></dt>
			<dd>
				<p class="Color1 F16 B">
					<%String result= (String)request.getAttribute("result");%>
					<%=result%>
				</p>
				
				<script language="javascript" >
					function countDown(secs){ 
						if(--secs>0){ 
							setTimeout("countDown("+secs+")",1000);
						}else{ 
							window.close();
						 	window.opener.updateBack();
						}
					}
					$(function() {
						if("<%=result%>"!="null"){
							$("#mainContent").css("display","none");
							$("#resultDIV").css("display","block");
							countDown(2);
						}
					});
					
				</script>	
			</dd>
		</dl>
</body> 
</html>