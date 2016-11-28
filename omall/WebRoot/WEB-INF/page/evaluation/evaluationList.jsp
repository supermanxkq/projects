<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%@page import="com.paySystem.ic.utils.Utils"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
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
	<script type="text/javascript"  src="js/areaSelect.js"></script>
	<style type="text/css">
        .star_wrap, .ulStars { width: 120px; height: 18px; overflow: hidden; float: left; }
        .ulStars li { width: 19px; height: 18px; background: url(<%=basePath%>/images/xing_bg.gif) no-repeat -278px -96px; float: left; margin-right: 5px; cursor: pointer; }
        .ulStars li.good { background: url(<%=basePath%>/images/xing_bg.gif) no-repeat -278px -52px; }
        .ulStars li.bad { background: url(<%=basePath%>/images/xing_bg.gif) no-repeat -278px -73px; }
	</style>
	<script type="text/javascript">
		$(function() {
			var $tabs = $("#tabs").tabs();	
			$('#tabs-2').click(function() { // 绑定单击事件
			    $tabs.tabs('select', 1);
			    return true;
			});	
			getGriTotal();	
		});
		//显示评价统计
		function getGriTotal(){
			var params = {
		  	};
		    $.ajax( {   
		        url : "evaluation/evaluation!getGriTotal",   
		        data : params,  
		        async:false, 
		        dataType : "json",   
		        cache : false,   
		        type : "get",
		        error : function(textStatus, errorThrown) {   
		    		alert("系统ajax交互错误!");	    
		        }, 
		        success : function(data, textStatus) {
		        	var avg = data["json2"];
		        	var ServiceAvg= avg[0][3];
		        	var RealAvg= avg[0][4];
		        	var PostAvg= avg[0][5];
		        	initXing(1,ServiceAvg);
		        	initXing(2,RealAvg);
		        	initXing(3,PostAvg);
		        	$("#listTB").append(data["json1"]);
		        }			   	
			});
		}
		//显示评价记录
		function initXing(index,value){
            var oLis = $("#ulStars_"+index+" li");
             if (value != "" && value!=null) {
             	$("#info_"+index).css("display","none");
                 var iSelectedStar = parseInt(value, 10);
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
		}
		//打开回复窗口
		function reply(mcrId,griId,tabId){
			$("#mcrId").val(mcrId);
			$("#griId").val(griId);
			$("#tabId").val(tabId);
			$("#reply-dialog").dialog({
				resizable: true,
				top: 300,
				height:220,
				width:330,
				modal: true,
				close:function(){
					
				}
			});
		}
	  //内容长度计算
		function toCharLength(str){
			return str.replace(/[^x00-xff]/mg,"JJ").length;
		}
	  //检查长度
	   function checkDescrLenght(){
	   		if(toCharLength($("#content").val())>255){
				pubErrorShow($("#contextErrorMsg"),"回复内容不能大于120个汉字");
				return false;
			}
			$("#contextErrorMsg").hide();
			return true;
	   }
	   //保存回复内容
		function saveReply(){
			var griId=$("#griId").val();
			var mcrId=$("#mcrId").val();
			var content=$("#content").val();
			if(content==""){
				alert("回复内容不能为空");
				return;
			}
			if(!checkDescrLenght()){
				alert("回复内容不能大于120个汉字");
				return;
			}
			var params = {
               "contextDTO.mcrid":mcrId,
               "contextDTO.griReplyId":griId,
               "contextDTO.context":content
            };
            $.ajax({
               url : "evaluation/evaluation!saveReply",
               data : params,
               async:false,
               dataType : "json",
               type : "POST",
               cache : false,
               error:function(errText){
                  alert("ajax加载数据异常!请联系管理员");
               },
               success:callback
           });
		}
		//回调
		function callback(){
			var tabId=$("#tabId").val();
			var $tabs = $("#tabs").tabs();	
			if(tabId=="0"){
				getMerCreditRecord_0();
			    $tabs.tabs('select', 0);
			}else if(tabId=="1"){
				getMerCreditRecord_1();
				$tabs.tabs('select', 1);
			}else{
				getMerCreditRecord_2();
				$tabs.tabs('select', 2);
			}
			$("#griId").val("");
			$("#mcrId").val("");
			$("#content").val("");
			$("#reply-dialog").dialog("close");
		}
		//关闭窗口
		function closeDialog(){
			$("#reply-dialog").dialog("close");
		}
	</script> 
</head>
<body>
	<div class="Position">
		当前位置是：HOME &gt;&gt; 评价管理
	</div>
	<jsp:include page="/WEB-INF/page/share/common.jsp"></jsp:include>
	<div style="margin:2px 4px;width:1150px;">
		<div style="float:left">
			<div style="border:1px solid #999;width:200px;height:300px;;line-height:25px;">
				<table style="width:200px;">
					<tr><td align="right">会员名：</td><td><%=request.getAttribute("userName")%></td></tr>
					<tr><td align="right">QQ号码：</td><td><%=request.getAttribute("qq")%></td></tr>
					<tr><td align="right">站内信息：</td><td><%=request.getAttribute("NetInFo")%></td></tr>
					<tr><td align="right">地址：</td><td><%=request.getAttribute("Address")%></td></tr>
					<tr><td align="right">认证信息：</td><td><%=request.getAttribute("OcaInfo")%></td></tr>
					<tr><td colspan=2 align="center"><a href="#">店铺评分是怎么打的？：</a></td></tr>
					<tr><td colspan=2 align="center"><a href="#">石油电商评价规则：</a></td></tr>
					<tr><td colspan=2 align="center"><a href="#">评价如何修改和删除：</a></td></tr>
					<tr><td colspan=2 align="center"><a href="#">炒作信用"如何惩罚"?：</a></td></tr>
					<tr><td colspan=2 align="center"><a href="#">被差评怎么办?：</a></td></tr>
					<tr><td colspan=2 align="center"><a href="#">手机上评价的步骤?：</a></td></tr>
				</table>
			</div>
		</div>
		<div style="margin-left:15px;float:left;width:920px;">
			<div style="border:1px solid #999;height:300px;">
				<div style="background-color:#DDD;line-height:30px;width:100%;overflow:hidden;">店铺半年内评分动态</div>
				<div style="margin:10px 30px;width:200px;float:left;">
					<div style="margin:20px 0;text-align:center;">
						<table>
							<tr><td>宝贝与描述想符合度</td></tr>
							<tr><td>
				            <ul id="ulStars_1" class="ulStars">
				                <li star="1"></li>
				                <li star="2"></li>
				                <li star="3"></li>
				                <li star="4"></li>
				                <li star="5"></li>
				            </ul>
					        </td><tr>
							<tr><td><span id="info_1">暂时无人打分</span></td></tr>
						</table>
					</div>
					
					<div style="margin:20px 0;text-align:center;">
						<table>
							<tr><td>卖家的服务态度</td></tr>
							<tr><td>
					            <ul id="ulStars_2" class="ulStars">
					                <li star="1"></li>
					                <li star="2"></li>
					                <li star="3"></li>
					                <li star="4"></li>
					                <li star="5"></li>
					            </ul>
					    	</td><tr>
							<tr><td><span id="info_2">暂时无人打分</span></td></tr>
						</table>
					</div>
					
					<div style="margin:20px 0;text-align:center;">
						<table>
							<tr><td>卖家的发货速度</td></tr>
							<tr><td>
					            <ul id="ulStars_3" class="ulStars">
					                <li star="1"></li>
					                <li star="2"></li>
					                <li star="3"></li>
					                <li star="4"></li>
					                <li star="5"></li>
					            </ul>
							</td><tr>
							<tr><td><span id="info_3">暂时无人打分</span></td></tr>
						</table>
					</div>
				</div>
				<div style="float:left;width:600px;margin:10px 0;">
					<div style="width:600px;height:30px;margin-top:35px;"><img src="<%=basePath%>/images/rank.png" style="width:600px;height:30px"></div>
					<div style="width:600px;height:30px;margin:35px 0;"><img src="<%=basePath%>/images/rank.png" style="width:600px;height:30px"></div>
					<div style="width:600px;height:30px;margin-top:35px;"><img src="<%=basePath%>/images/rank.png" style="width:600px;height:30px"></div>
					<div style="margin-top:25px;">
						<ul style="text-align:center;">
							<li style="float:left;width:120px;">
								<div style="width:100px;">1分</div>
								<div style="width:100px;">非常不满意</div>
							</li>
							<li style="float:left;width:120px;">
								<div style="width:100px;">2分</div>
								<div style="width:100px;">不满意</div>
							</li>
							<li style="float:left;width:120px;">
								<div style="width:100px;">3分</div>
								<div style="width:100px;">一般</div>
							</li>
							<li style="float:left;width:120px;">
								<div style="width:100px;">4分</div>
								<div style="width:100px;">满意</div>
							</li>
							<li style="float:left;width:120px;">
								<div style="width:100px;">5分</div>
								<div style="width:100px;">非常满意</div>
							</li>
						</ul>
					</div>
				</div>
				<div style="clear:both"></div>
			</div>
			<div>
				<div style="background-color:#DDD;margin:10px 0;line-height:30px;">卖家信用度累计</div>
				<div>
					<table width="920px" class="listTable" cellpadding="0" cellspacing="0" id="listTB">
						<tr>
							<th width="15%"></th>
							<th width="15%">最近一周</th>
							<th width="15%">最近一个月</th>
							<th width="15%">最近六个月</th>
							<th width="15%">六个月前</th>
							<th width="15%">总计</th>
						</tr>
					</table>
				</div>
				
				<div style="background-color:#DDD;margin:10px 0;line-height:30px;">卖家累积信用</div>
				<div>
					<div style="width:910px" class="search" id="tabs">
					     <ul>
						   <li><a href="#tabs-1">来自买家的评论</a></li>
						   <li><a href="#tabs-2">给他人的评论</a></li>
						   <li><a href="#tabs-3">退货评论</a></li>
					     </ul>
						 <div class="search" id="tabs-1">
						 	<jsp:include page="evaluationTab.jsp">
						 		<jsp:param value="0" name="type"/>
						 	</jsp:include>
						 </div>
						 <div class="search" id="tabs-2">
						 	<jsp:include page="evaluationTab.jsp">
						 		<jsp:param value="1" name="type"/>
						 	</jsp:include>
						 </div>
						 <div class="search" id="tabs-3">
						 	<jsp:include page="evaluationTab.jsp">
						 		<jsp:param value="2" name="type"/>
						 	</jsp:include>
						 </div>
					</div>
				</div>
			</div>
		</div>
		<div style="clear:both"></div>
	</div>
	
	
	<div id="reply-dialog" style="display:none" title="评论回复">
		回复内容：<br>
		<input type="hidden" id="griId">
		<input type="hidden" id="mcrId">
		<input type="hidden" id="tabId">
		<textarea style="width:300px;height:100px" id="content" onblur="checkDescrLenght()"></textarea>
		<br>
		<label id="contextErrorMsg" style="display: none;"></label>
		<br>
		<input type="button" value="确定回复" class="formButton" onclick="saveReply()">
		<input type="button" value="取消" class="formButton" onclick="closeDialog()">
	</div>
</body> 
</html>