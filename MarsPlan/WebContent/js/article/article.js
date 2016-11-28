/** 监听回车事件 */
$(document).ready(function() {
	$("#keyWord").focus();
	$("#keyWord").keydown(function(e) {
		var curKey = e.which;
		if (curKey == 13) {
			luceneSearch();
		}
	});
});

// 异步加载数据
function ajaxData(dataUrl, params) {
	var divId = "articlelist";
	var articlelist = $("#" + divId);
	$.ajax({
		async : false,
		url : dataUrl,
		data : params,
		dataType : "json",
		cache : false,
		type : "POST",
		error : function(textStatus, errorThrown) {
		},
		beforeSend : function() {
		},
		success : function(data, textStatus) {
			$("#pageNav").html(data.pageHtml2);
			articlelist.html("");
			articlelist.html(data.dataList);
		}
	});
}

function luceneSearch() {
	var keyWord = $("#keyWord").val();
	if (keyWord != "") {
		var dataUrl = "lucene/lucene!luceneSearch";
		var divId = "articlelist";
		var articlelist = $("#" + divId);
		var params = {
			"keyWord" : keyWord
		};
		$
				.ajax({
					async : false,
					url : dataUrl,
					data : params,
					dataType : "json",
					cache : false,
					type : "POST",
					error : function(textStatus, errorThrown) {
					},
					beforeSend : function() {
					},
					success : function(data, textStatus) {
						var insertHTML = "";
						if (data.success) {
							$("#pageNav").html("");
							articlelist.html("");
							var obj = eval(data.searches);
							$(obj)
									.each(
											function(index) {
												var val = obj[index];
												insertHTML += '<div class="media">';
												insertHTML += '<div class="media-body">';
												insertHTML += '<h4 class="media-heading"><a href="'
														+ val.url
														+ '">'
														+ val.title
														+ '</a></h4>';
												insertHTML += val.content;
												insertHTML += '</div><p class=\"text-right text-muted\"><ul class=\"text-right list-inline list-unstyled\"><li><small>'
														+ val.createDate
														+ '</small></li><li><i class=\"glyphicon glyphicon-search\"></i><a href=\"article/article!findArticleById?articleDTO.id='+val.id+'\"><font color=\"red\">阅读</font></a>('+val.readTimes+')</li></uL></p>';
												insertHTML += '</div><hr/>';
											});
							articlelist.html(insertHTML);
						}
					}
				});
	}
}
