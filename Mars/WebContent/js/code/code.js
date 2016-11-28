// 异步加载数据
function ajaxData(dataUrl, params) {
	 var divId = "codelist";
	 var codelist = $("#"+divId);
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
			$("#pageNav").html(data.pagehtml);
			codelist.html("");
			codelist.html(data.codeList);
		}
	});
}
