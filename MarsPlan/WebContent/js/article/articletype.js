$().ready(function() {
	var dataUrl = "articletype/articletype!jsonPageListF";
	var divId = "articletypelist";
	var articletypelist = $("#" + divId);
	$.ajax({
		async : false,
		url : dataUrl,
		dataType : "json",
		cache : false,
		type : "POST",
		error : function(textStatus, errorThrown) {
		},
		beforeSend : function() {
		},
		success : function(data, textStatus) {
			articletypelist.html("");
			articletypelist.html(data);
		}
	});
});
