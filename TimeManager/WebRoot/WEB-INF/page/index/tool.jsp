<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<title>${title}</title>
<meta name="Keywords" content="" />
<meta name="Description" content="" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link rel="shortcut icon" href="<%=basePath%>favicon.ico" type="image/x-icon" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<!--[if lt ie 8]>
<script type="text/javascript">
   $(function(){
    $("img").click(function(){
var url = $(this).parent().parent().parent().attr("href");
window.open(url);
})
});
</script>
<![endif]-->
</head>

<body style="background-color:#F0F0F0">
<ul class="H_tool">
  <li><a href="http://www.weather.com.cn/static/html/weather.shtml" target="_blank">
    <dl class="H_tool_dl">
      <dt><img src="images/tool/tool_01.jpg" width="64" height="64" alt="天气" /></dt>
      <dd><em>天气预报</em> <span>专业天气预报，覆盖全国</span></dd>
    </dl>
    </a></li>
    <li><a href="http://caipiao.hao123.com/" target="_blank">
    <dl class="H_tool_dl">
      <dt><img src="images/tool/tool_02.jpg" width="64" height="64" alt="" /></dt>
      <dd><em>彩票开奖</em> <span>最全的开奖结果、走势分析</span></dd>
    </dl>
    </a></li>
    <li><a href="http://www.hao123.com/haoserver/kuaidi.htm" target="_blank">
    <dl class="H_tool_dl">
      <dt><img src="images/tool/tool_03.jpg" width="64" height="64" alt="" /></dt>
      <dd><em>快递查询</em> <span>快捷好用的快递查询</span></dd>
    </dl>
    </a></li>
    <li><a href="http://app.baidu.com/saturdaycalendar" target="_blank">
    <dl class="H_tool_dl">
      <dt><img src="images/tool/tool_04.jpg" width="64" height="64" alt="" /></dt>
      <dd><em>万年历</em> <span>阴阳历、节假日、黄道吉日</span></dd>
    </dl>
    </a></li>
    <li><a href="http://huoche.kuxun.cn/oemshike-search.html" target="_blank">
    <dl class="H_tool_dl">
      <dt><img src="images/tool/tool_05.jpg" width="64" height="64" alt="" /></dt>
      <dd><em>列车时刻</em> <span>火车时刻表</span></dd>
    </dl>
    </a></li>
    <li><a href="http://affiliate.qunar.com/affiliate/ext/gb2utf.jsp" target="_blank">
    <dl class="H_tool_dl">
      <dt><img src="images/tool/tool_06.jpg" width="64" height="64" alt="" /></dt>
      <dd><em>航班查询</em> <span>机票查询，特价机票</span></dd>
    </dl>
    </a></li>
    <li><a href="http://bus.mapbar.com/beijing/" target="_blank">
    <dl class="H_tool_dl">
      <dt><img src="images/tool/tool_07.jpg" width="64" height="64" alt="" /></dt>
      <dd><em>公交查询</em> <span>最全面的全国公交信息</span></dd>
    </dl>
    </a></li>
    <li><a href="http://map.baidu.com/" target="_blank">
    <dl class="H_tool_dl">
      <dt><img src="images/tool/tool_08.jpg" width="64" height="64" alt="" /></dt>
      <dd><em>在线地图查询</em> <span>您的出行指南，生活助手</span></dd>
    </dl>
    </a></li>
    <li><a href="http://hotel.kuxun.cn/search.php" target="_blank">
    <dl class="H_tool_dl">
      <dt><img src="images/tool/tool_09.jpg" width="64" height="64" alt="" /></dt>
      <dd><em>酒店查询</em> <span>找酒店好帮手</span></dd>
    </dl>
    </a></li>
    <li><a href="http://guishu.showji.com/search.htm" target="_blank">
    <dl class="H_tool_dl">
      <dt><img src="images/tool/tool_10.jpg" width="64" height="64" alt="" /></dt>
      <dd><em>手机号码归属地</em> <span>全国手机号码归属地查询</span></dd>
    </dl>
    </a></li>
    <li><a href="http://qq.ip138.com/idsearch/index.asp" target="_blank">
    <dl class="H_tool_dl">
      <dt><img src="images/tool/tool_11.jpg" width="64" height="64" alt="" /></dt>
      <dd><em>身份证查询</em> <span>身份证验证、信息查询</span></dd>
    </dl>
    </a></li>
    <li><a href="http://www.ip138.com/ips.asp" target="_blank">
    <dl class="H_tool_dl">
      <dt><img src="images/tool/tool_12.jpg" width="64" height="64" alt="" /></dt>
      <dd><em>IP地址归属地</em> <span>IP地址归属地</span></dd>
    </dl>
    </a></li>
    <li><a href="http://www.hao123.com/haoserver/jiaotongcx.htm" target="_blank">
    <dl class="H_tool_dl">
      <dt><img src="images/tool/tool_13.jpg" width="64" height="64" alt="" /></dt>
      <dd><em>交通违章查询</em> <span>全国范围，车辆违章查询</span></dd>
    </dl>
    </a></li>
    <li><a href="http://app.baidu.com/youbian2" target="_blank">
    <dl class="H_tool_dl">
      <dt><img src="images/tool/tool_14.jpg" width="64" height="64" alt="" /></dt>
      <dd><em>邮政编码查询</em> <span>全国市、县、村邮编</span></dd>
    </dl>
    </a></li>
    <li><a href="http://app.baidu.com/widget?appid=116526" target="_blank">
    <dl class="H_tool_dl">
      <dt><img src="images/tool/tool_15.jpg" width="64" height="64" alt="" /></dt>
      <dd><em>科学计算器</em> <span>运算准确的科学计算器</span></dd>
    </dl>
    </a></li>
</ul>
<br class="Spacer" />
</body>
</html>