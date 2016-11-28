<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'index.jsp' starting page</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script type="text/javascript">
		//清空已选
			function clearSelect(){
				var obj=document.getElementById("selTempN");
				for(var i=0;i<obj.options.length;i++){
					obj.options[i].selected="selected";
				}
				copyToList("selTempN","noselTempN");
			}
	function copyToList(from,to){
			 fromList=document.getElementById(from);
			 toList=document.getElementById(to);
			if(toList.options.length>0&&toList.options[0].value=='temp'){
				toList.options.length=0;
			}
			var sel=false;
			for(i=0;i<fromList.options.length;i++){
				var current=fromList.options[i];
				if(current.selected){
					sel=true;
					if(current.value=='temp'){
							return;
						}
					txt=current.text;
					val=current.value;
					toList.options[toList.length]=new Option(txt,val);
					fromList.options[i]=null;
					i--;
				}
			}
	}


	//一般不常用的功能
	window.onload = function(){ 
	    document.getElementById("add_all").onclick=function(){  
                var firstElement=document.getElementById("noselTempN");  
                //获取option  
                var len=firstElement.length;  
                //获取右边  
                var secondElement=document.getElementById("selTempN");  
                for(var i=0;i<len;i++){  
                    secondElement.appendChild(firstElement[0]);  
                }  
            }
	    //简化的（全部有右边移到左边）  
        document.getElementById("remove_all").onclick=function(){  
            var secondElement=document.getElementById("selTempN");  
            //获取option  
            var len=secondElement.length;  
            //获取右边  
            var firstElement=document.getElementById("noselTempN");  
            for(var i=0;i<len;i++){  
                firstElement.appendChild(secondElement[0]);  
            }  
        }         
        
	} 

	var select='';
	var selectIds='';
	function confirm(){
		var selTempN=document.getElementById("selTempN");
		for(var i=0;i<selTempN.options.length;i++){
			var option=selTempN.options[i];
			select+=option.text+',';
			selectIds+=option.value+',';
		}
		if(select.length!=0){
			select=select.substring(0,select.length-1);
		}
		if(selectIds.length!=0){
			selectIds=selectIds.substring(0,selectIds.length-1);
		}
		alert("选项："+select);
		alert("编号："+selectIds);
	}
	
	</script>
	</head>
	<body>
		<table>
			<tr>
				<td>
					<select name="first" size="10" multiple="multiple" id="noselTempN"
						ondblclick="copyToList('noselTempN','selTempN')">
						<option value="选项1">
							选项1
						</option>
						<option value="选项2">
							选项2
						</option>
						<option value="选项3">
							选项3
						</option>
						<option value="选项4">
							选项4
						</option>
						<option value="选项5">
							选项5
						</option>
						<option value="选项6">
							选项6
						</option>
						<option value="选项8">
							选项8
						</option>
					</select>
				</td>
				<td width="69" valign="middle">
					<input name="add" id="add" type="button" value="-->"
						onclick="copyToList('noselTempN','selTempN')" />
					<input name="add_all" id="add_all" type="button" value="==>" />
					<input name="remove" id="remove" type="button" value="<--"
						onclick="copyToList('selTempN','noselTempN')" />
					<input name="remove_all" id="remove_all" type="button" value="<==" />
				</td>
				<td width="127" align="left">
					<select name="second" size="10" multiple="multiple" id="selTempN"
						ondblclick="copyToList('selTempN','noselTempN')">
						<option value="选项9">
							选项9
						</option>
					</select>
				</td>
			</tr>
		</table>
		<span style="cursor: hand;" onclick="clearSelect();">清空所选</span>
		<span style="cursor: hand;" onclick="confirm();">确认选择</span>
	</body>
</html>
