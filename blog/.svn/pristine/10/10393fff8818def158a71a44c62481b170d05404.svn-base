<%@page contentType="text/html; charset=gbk"%>
<%@include file="../common/admin_head.jsp"%>


<html>
<head>
<title>后台管理系统 版本:1.0</title>
<ss:head/>
<link href="<%=basepath%>/css/admin.css" rel="stylesheet" type="text/css" />
 <script language="JavaScript" type="text/javascript">
 	var url = "#";
 	var root;
	//响应菜单单击事件
	function treeNodeSelected(arg) {
		var node = dojo.widget.byId(arg.source.widgetId);
		if(node.isFolder){
			if(dojo.widget.byId(arg.source.widgetId).isExpanded){
				dojo.widget.byId(arg.source.widgetId).collapse();
			}else{
				dojo.widget.byId(arg.source.widgetId).expand();
			}		
		}else{
			processSelected(arg.source.widgetId);
		}
	}
	
	//处理菜单导航
	function processSelected(menuid){
		var tmp = (new Date()).getTime();
        if (menuid=='admin'){
			url = "/admin/admin_browseAdmin.action?newquery=yes";	
		}else if (menuid=='article') {
			url = "/admin/article_browseArticle.action?newquery=yes";	
		}else if (menuid=='exit'){
			url = "/admin/admin_logout.action";	
		};
		
		//添加临时参数,标识这是一次全新的请求
		if (url.indexOf("?")!=-1){
			url = "<%=basepath%>"+url+"&tmp="+tmp;
		}else{
			url = "<%=basepath%>"+url+"?tmp="+tmp;
		}
		if (menuid=='exit'){
			window.parent.location=url;
		}else{
			window.parent.mainFrame.location=url;
		}		
	}
	
	//响应菜单展开事件
	function treeNodeExpanded(arg) {
	    alert('id['+arg.source.widgetId+'], name['+ arg.source.title+ '] expanded');
	}
	
	//响应菜单收缩事件
	function treeNodeCollapsed(arg) {
	    alert('id['+arg.source.widgetId+'], name['+ arg.source.title+ '] collapsed');
	}
	
	//注册菜单事件处理
	dojo.addOnLoad(function(){
	    root = dojo.widget.byId('adminctrl');
	    dojo.event.topic.subscribe(root.eventNames.titleClick, treeNodeSelected);;
	});
	
	//展开所有菜单项
	function expandAll(){
       for(var i=0; i<root.children.length; i++) {
          var child = root.children[i];
          dojo.lang.forEach(child.getDescendants(),function(node) {node.expand(); });
       }     
	}
</script>
</head>
<body style="padding:10px;">
<ss:tree label="<b>后台管理</b>" id="adminctrl" showRootGrid="true" showGrid="true">
			<ss:treenode label="<img src='../images/icon_news.gif'/>文章管理"
				id="article">
			
				</ss:treenode>
				

		
			<ss:treenode label="<img src='../images/icon_exit.gif'/>安全退出"
				id="exit" />
		</ss:tree>

<br/>
</body>
<script type="text/javascript">
	//展开所有菜单项
	window.setTimeout("expandAll();",2000);
</script>
</html>