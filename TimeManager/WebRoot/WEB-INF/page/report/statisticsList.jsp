<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
	<head>
		<base href="<%=basePath%>" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>全部任务完成情况统计</title>
	<script src="js/jquery-1.8.2.min.js"></script>
		<script type="text/javascript"
			src="http://cdn.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
			<script src="js/common.js"></script>
		<script type="text/javascript">
		var finishedRecordPercent;
		var unFinishedRecordPercent;
		var delayedRecordPercent;
		/**计算百分比*/
		function calcPercents() {
				var params = {
				};
				var url = "statistics/statistics!calcPercents";
				$.ajax( {
					async : false,
					url : url,
					data : params,
					dataType : "json",
					cache : false,
					type : "post",
					error : function(textStatus, errorThrown) {
						alert("Ajax交互错误！");
					},
					success : function(data, textStatus) {
						finishedRecordPercent=data.percents[0];
						unFinishedRecordPercent=data.percents[1];
						delayedRecordPercent=data.percents[2];
						 Highcharts.setOptions({
							    lang:{
							       contextButtonTitle:"图表导出菜单",
							       decimalPoint:".",
							       printChart:"打印图表",
							       downloadJPEG:"下载JPEG图片",
							       downloadPDF:"下载PDF文件",
							       downloadPNG:"下载PNG文件",
							       downloadSVG:"下载SVG文件",
							    }
							  });
					    $('#container').highcharts({
					        /**去掉highcharts.com */
					    	credits: {
					        enabled:false
					     },
					        chart: {
					            type: 'pie',
					            options3d: {
									enabled: true,
					                alpha: 45,
					                beta: 0
					            }
					        },
					        title: {
					            text: '任务完成情况统计'
					        },
					        tooltip: {
					            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
					        },
					        plotOptions: {
					            pie: {
					                allowPointSelect: true,
					                cursor: 'pointer',
					                depth: 35,
					                dataLabels: {
					                    enabled: true,
					                    format: '{point.name}'
					                }
					            }
					        },
					        series: [{
					            type: 'pie',
					            name: '任务占有率',
					            colors: ['#95CEFF', '#FFBC75', '#5C5C61'],
					            data: [
					                ['已完成',   finishedRecordPercent],
					                ['已延期',   delayedRecordPercent],
					                {
					                    name: '未完成',
					                    y: unFinishedRecordPercent,
					                    sliced: true,
					                    selected: true
					                },
					            ]
					        }]
					    });
					}
				});
		}
		</script>
	</head>
	<body  onload="calcPercents();">

		<script src="highcharts/highcharts.js"></script>
		<script src="highcharts/highcharts-3d.js"></script>
		<script src="highcharts/modules/exporting.js"></script>
		<script src="highcharts/themes/grid.js"></script>
		<div id="container" style="height: 400px;margin-top: 30px;"></div>
	</body>
</html>
