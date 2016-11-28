<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String type = request.getParameter("type");
	String op="评价人";
	if(type.equals("1")){
		op="被评价人";
	}
%>   
<script type="text/javascript">
		$(function() {
			getMerCreditRecord_<%=type%>(${creditRecordDTO.page });
		});
		//获取评价记录 包括来自买家的评论、给他人的评论、退货评论
		function getMerCreditRecord_<%=type%>(page){
			var appType=$("#appType_<%=type%>").val();
			var griType=$("#griType_<%=type%>").val();
			var params = {
				type:"<%=type%>",
				appType:appType,
				griType:griType,
				"creditRecordDTO.page":page
		  	};
		    $.ajax( {   
		        url : "evaluation/evaluation!queryMerCreditRecord",   
		        data : params,  
		        async:false, 
		        dataType : "json",   
		        cache : false,   
		        type : "get",
		        error : function(textStatus, errorThrown) {   
		    		alert("系统ajax交互错误!");	    
		        }, 
		        success : function(data, textStatus) {
		        	var list = data["list"];
		        	var html="";
		        	$("#tab_<%=type%> tbody").html("");
		        	for(var i=0;i<list.length;i++){
		        		html+="<tr>";
		        		var type="";
		        		if(list[i]["appType"]=="0"){
		        			type="好评";
		        		}else if(list[i]["appType"]=="1"){
		        			type="中评";
		        		}else{
		        			type="差评";
		        		}
		        		var griType ="【手动评论】";
		        		if(list[i]["griType"]==2){
		        			griType ="【来自系统评论】";
		        		}
		        		var memType= "【商家】";
		        		if(list[i]["memType"]==1){
		        			memType="【平台】";
		        		}
		        		html+="<td style=\"padding:10px 0;border-top:1px solid #DDD;\" align=\"center\">"+type+"</td>";
		        		html+="<td style=\"padding:10px 0;border-top:1px solid #DDD;\" align=\"left\">"+griType+"："+list[i]["context"]+"<br>【"+list[i]["updateTIme"]+"】";
		        		var contextDTOs=list[i]["contextDTOs"];
		        		if(contextDTOs.length!=0){
		        			html+="<br>——————————<br>";
		        			for(var j=0;j<contextDTOs.length;j++){
		        				html+="【回复】:"+contextDTOs[j]["context"]+"<br>";
		        			}
		        		}
		        		html+="</td>";
		        		html+="<td style=\"padding:10px 0;border-top:1px solid #DDD;\" align=\"center\">"+memType+"："+list[i]["memId"]+"</td>";
		        		html+="<td style=\"padding:10px 0;border-top:1px solid #DDD;\" align=\"center\"><a href=\"#\">"+list[i]["goodsName"]+"</a></td>";
		        		html+="<td style=\"padding:10px 0;border-top:1px solid #DDD;\" align=\"center\">&nbsp;";
		        		if(contextDTOs.length==0 && "<%=type%>"!="1"){
		        			html+="<input type=\"button\" value=\"回复\" class=\"formButton\" onclick=\"reply("+list[i]["mcrId"]+","+list[i]["griId"]+",<%=type%>)\"/>";
		        		}
		        		html+="</td>";
		        		html+="</tr>";
		        	}
		        	$("#pageNav_<%=type%>").html(data["pageHtml"]);
		        	if(html==""){
		        		 html = "<tr><td colspan='5' align='center' style='height:40px;'>没有找到相关数据</td></tr>";
		        		 $("#pageNav_<%=type%>").html("");
		        	}
		        	$("#tab_<%=type%> tbody").append(html);
		        }			   	
			});
		}
</script> 
<table width="890px" cellpadding="0" cellspacing="0" id="tab_<%=type%>">
	<thead>
		<th style="padding:10px 0;" width="100px">
			<select name="appType" id="appType_<%=type%>" onchange="getMerCreditRecord_<%=type%>()">
				<option value="">评价</option>
				<option value="0">好评</option>
				<option value="1">中评</option>
				<option value="2">差评</option>
			</select>
		</th>
		<th style="padding:10px 0;" width="200px">
			<select name="griType" id="griType_<%=type%>" onchange="getMerCreditRecord_<%=type%>()">
				<option value="">评论</option>
				<option value="1">手动评论</option>
				<option value="2">系统自动评论</option>
			</select>
		</th>
		<th style="padding:10px 0;" width="200px"><%=op%></th>
		<th style="padding:10px 0;" width="200px">宝贝信息</th>
		<th style="padding:10px 0;" width="200px">操作</th>
	</thead>
	<tbody>
	
	</tbody>
</table>
<div class="Fr" id="pageNav_<%=type%>">
	<s:property value="pageHTML" escape="false"/>
</div>
