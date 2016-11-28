var timer = window.setInterval(autoSave,30000);
//清理定时器
function clear(){
	window.clearInterval(timer);
}
function start(){
	timer = window.setInterval(autoSave,30000);
}
function autoSave(){
	var shortTitle = document.getElementById("shortTitle").value;
	if(shortTitle!=""){
		shortTitle = encodeURI(encodeURI(shortTitle));
		document.getElementById("shortTitleForSave").value = shortTitle;
	}
	var longTitle = document.getElementById("longTitle").value;
	if(longTitle!=""){
		longTitle = encodeURI(encodeURI(longTitle));
		document.getElementById("longTitleForSave").value = longTitle;
	}
	var deputyTitle = document.getElementById("deputyTitle").value;
	if(deputyTitle!=""){
		deputyTitle = encodeURI(encodeURI(deputyTitle));
		document.getElementById("deputyTitleForSave").value = deputyTitle;
	}
	var author = document.getElementById("author").value;
	if(author!=""){
		author = encodeURI(encodeURI(author));
		document.getElementById("authorForSave").value = author;
	}
	var comeFrom = document.getElementById("comeFrom").value;
	if(comeFrom!=""){
		comeFrom = encodeURI(encodeURI(comeFrom));
		document.getElementById("comeFromForSave").value = comeFrom;
	}
	var metaTitle = document.getElementById("metaTitle").value;
	if(metaTitle!=""){
		metaTitle = encodeURI(encodeURI(metaTitle));
		document.getElementById("metaTitleForSave").value = metaTitle;
	}
	var metaKeyword = document.getElementById("metaKeyword").value;
	if(metaKeyword!=""){
		metaKeyword = encodeURI(encodeURI(metaKeyword));
		document.getElementById("metaKeywordForSave").value = metaKeyword;
	}
	var metaDesc = document.getElementById("metaDesc").value;
	if(metaDesc!=""){
		metaDesc = encodeURI(encodeURI(metaDesc));
		document.getElementById("metaDescForSave").value = metaDesc;
	}
	var keywords = document.getElementById("keywords").value;
	if(keywords!=""){
		keywords = encodeURI(encodeURI(keywords));
		document.getElementById("keywordsForSave").value = keywords;
	}
	var tag = document.getElementById("tag").value;
	if(tag!=""){
		tag = encodeURI(encodeURI(tag));
		document.getElementById("tagForSave").value = tag;
	}
	var newsAbstract = document.getElementById("newsAbstract").value;
	if(newsAbstract!=""){
		newsAbstract = encodeURI(encodeURI(newsAbstract));
		document.getElementById("newsAbstractForSave").value = newsAbstract;
	}
	var content = CKEDITOR.instances.content.getData();
	if(content!=""){
		content = encodeURI(encodeURI(content));
		document.getElementById("contentForSave").value = content;
	}
	//判断关键项是否都已经填写了 如果没有填写则不执行缓存操作
	//获取formId
	var formId = document.getElementById("formId").value;
	//如果是保存之后返回的页面不需要再把新闻缓存
	var isUpdateBreak = document.getElementById("isUpdateBreak").value;
	if(isUpdateBreak=="no"){
		jQuery.ajax({
	        url: 'news_saveNews.action',
	        data: $('#'+formId).serialize(),
	        type: "POST",
	        contentType: "application/x-www-form-urlencoded",
	        success: function(data) {
	        	if(data=="缓存失败"||data=="程序异常"){
	        		clear();
	        	}
	        },
	        error: function(request) {
	        	clear();
	        }
	    });
	}else{
		clear();
	}
}
