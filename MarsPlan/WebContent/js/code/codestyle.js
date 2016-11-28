$().ready(function() {
	var dataUrl = "codestyle/codestyle!jsonPageList";
	var divId = "codestylelist";
	var codestylelist = $("#" + divId);
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
			codestylelist.html("");
			codestylelist.html(data);
		}
	});
});
