/**
 * 文档名称: Public Validate JavaScript 文档内容： 公用验证格式js文件 应用范围： 全局 编写日期： 2014-01-10 作
 * 者: 谢 公 司: 融芯思联(北京)科技有限公司
 */

// showErrorMsg 调用显示错误信息
function pubErrorShow(_label, _msg) {
	_label.html(_msg);
	_label.addClass("errorMsg");
	_label.show();
	return;
}

/**
 * 功能说明: 验证长度，显示错误提示信息 参数说明: _obj (页面标签) : 页面组件，如 input文本框； _msg (数组) :
 * 错误提示信息，如: var msg = ["xxx不能为空","xxx格式不正确"]； _labelId1 (label组件Id) :
 * 错误显示label； _labelId2 (label组件Id) : 输入格式提醒label； _min (最小值)
 * :该值长度下限(没有则传""或null) _max (最大值) :该值长度上限(默认为20)
 * 
 * 调用说明: 在组件的onBlur/onFocus触发的函数中调用，如下例所示: var type = ["isNull"]; var errorMsg =
 * ["机构名称不能为空!"]; orgNameFlag =
 * showErrorMsg(obj,type,errorMsg,$("#errorMsg"),$("#warnMsg"));
 */
function checkLenMsg(_obj, _msg, _labelId1, _min, _max) {
	var text = _obj.value;
	var min = _min == "" || null ? 0 : _min;
	var max = _max == "" || null ? 20 : _max;
	var label = $('#' + _labelId1);
	if (!validateRules.betweenLength(text, min, max)) {
		pubErrorShow(label, _msg);
		return false;
	}
	return true;

}
/**
 * 功能说明: 验证格式，显示错误提示信息 参数说明: _obj (页面标签) : 页面组件，如 input文本框； _type (数组) : 验证类型
 * ，如：var type = ["isNull","isEmail"]； _msg (数组) : 错误提示信息，如: var msg =
 * ["xxx不能为空","xxx格式不正确"]； _labelId1 (label组件Id) : 错误显示label； _labelId2
 * (label组件Id) : 输入格式提醒label； _errorFlag (状态标志) : 最后chech函数判断提交依据
 * 
 * 调用说明: 在组件的onBlur/onFocus触发的函数中调用，如下例所示: var type = ["isNull"]; var errorMsg =
 * ["机构名称不能为空!"]; orgNameFlag =
 * showErrorMsg(obj,type,errorMsg,$("#errorMsg"),$("#warnMsg"));
 */
function showErrorMsg(_obj, _type, _msg, _labelId1, _labelId2) {
	var text = _obj.value;
	var _label1 = $('#' + _labelId1);
	var _label2 = $('#' + _labelId2);

	_label2.html("");
	_label2.hide();
	_label1.html("");
	_label1.hide();
	for ( var i = 0; i < _type.length; i++) {
		var type = _type[i];
		var msg = _msg[i];
		switch (type) {
		case "isNull": // 空
			if (validateRules.isNull(text)) {
				pubErrorShow(_label1, msg);
				return false;
			}
			break;
		case "isSelectNull":
			if (text == -1 || text == '-1') {
				pubErrorShow(_label1, msg);
				return false;
			}
			break;
		case "isEmail":// 电子邮箱
			if (!validateRules.isEmail(text)) {
				pubErrorShow(_label1, msg);
				return false;
			}
			break;
		case "isTel":// 电话号码的函数(包括验证国内区号,国际区号,分机号)
			if (!validateRules.isTel(text)) {
				pubErrorShow(_label1, msg);
				return false;
			}
			break;
		case "isMobile":// 手机
			if (!validateRules.isMobile(text)) {
				pubErrorShow(_label1, msg);
				return false;
			}
			break;
		case "fullNumber":// 全是数字
			if (!validateRules.fullNumber(text)) {
				pubErrorShow(_label1, msg);
				return false;
			}
			break;
		case "isAmt": // 金额验证
			if (!validateRules.isAmount(text)) {
				pubErrorShow(_label1, msg);
				return false;
			}
			break;
		case "isPlusAmt":// 验证正数金额
			if (!validateRules.isPlusAmt(text)) {
				pubErrorShow(_label1, msg);
				return false;
			}
			break;
		case "isMinusAmt":// 验证负数金额
			if (!validateRules.isMinusAmt(text)) {
				pubErrorShow(_label1, msg);
				return false;
			}
			break;
		case "isNormalAmt":// 验证金额，格式：999999.99
			if (!validateRules.isNormalAmt(text)) {
				pubErrorShow(_label1, msg);
				return false;
			}
			break;
		case "isRate":// 验证费率,格式：00.0000/00.000/00.00/00.0
			var reg = /^([0]{1})(\.\d{0,4})?$/;
			// if(!validateRules.isRate(text)){
			if (!reg.test(text)) {
				pubErrorShow(_label1, msg);
				return false;
			}
			break;
		case "isDoubleRate":// 验证费率,格式：00.0000/00.000/00.00/00.0
			var reg = /^(-?\d{1,2})(\.{1}\d{1,4})?$/;
			// if(!validateRules.isRate(text)){
			if (!reg.test(text)) {
				pubErrorShow(_label1, msg);
				return false;
			}
			break;
		case "isRealName":// 真实姓名
			if (!validateRules.isRealName(text)) {
				pubErrorShow(_label1, msg);
				return false;
			}
			break;
		case "isCompName":// 公司（单位）名称
			if (!validateRules.isCompanyname(text)) {
				pubErrorShow(_label1, msg);
				return false;
			}
			break;
		case "isAddr":// 地址
			if (!validateRules.isCompanyaddr(text)) {
				pubErrorShow(_label1, msg);
				return false;
			}
			break;
		case "isUrl":// 网址
			if (!validateRules.isUrl(text)) {
				pubErrorShow(_label1, msg);
				return false;
			}
			break;
		case "isQQ":// QQ号码
			if (!validateRules.isQQ(text)) {
				pubErrorShow(_label1, msg);
				return false;
			}
			break;
		case "isZipCode":// 邮政编码
			if (!validateRules.isZipCode(text)) {
				pubErrorShow(_label1, msg);
				return false;
			}
			break;
		case "isPersonId":// 身份证号码
			if (!validateRules.isPersonId(text)) {
				pubErrorShow(_label1, msg);
				return false;
			}
			break;
		case "islowNum": 
			var reg = /^((\d{1,2}(\.\d{1,2})?))$/; 
			if (!reg.test(text)) {
				pubErrorShow(_label1, msg);
				return false;
			}
			break;
		
			
			
			
		}
		
			
			
	}

	return true;
}
/**
 * 功能说明: 提示用户要输入的格式 参数说明: _obj (页面标签) : 页面组件，如 input文本框 _msg (数组) : 错误提示信息，如:
 * var msg = ["xxx不能为空","xxx格式不正确"] _labelId1 (label组件ID) : 错误显示label _label2
 * (label组件ID) : 输入格式提醒label
 * 
 * 调用说明: 在组件的onBlur/onFocus触发的函数中调用，如下例所示: var type = ["isNull"]; var errorMsg =
 * ["机构名称不能为空!"]; showWarnMsg(obj,type,errorMsg,$("#errorMsg"),$("#warnMsg"));
 */
function showWarnMsg(_obj, _msg, _labelId1, _labelId2) {
	var text = _obj.value;
	var _label1 = $('#' + _labelId1);
	var _label2 = $('#' + _labelId2);
	_label1.html("");
	_label1.hide();
	if (validateRules.isNull(text)) {
		_label2.html(_msg);
		_label2.addClass("warnMsg");
		_label2.show();
	}
}

/*
 * 说 明： 常用格式验证正则表达式 范 围: 本文档 调用方式：validateRegExp.xx.test(validateStr)
 */
var validateRegExp = {
	decmal : "^([+-]?)\\d*\\.\\d+$", // 浮点数
	decmal1 : "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$", // 正浮点数
	decmal2 : "^-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)$", // 负浮点数
	decmal3 : "^-?([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0)$", // 浮点数
	decmal4 : "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0$", // 非负浮点数（正浮点数 + 0）
	decmal5 : "^(-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*))|0?.0+|0$", // 非正浮点数（负浮点数 +
	// 0）
	decmal6 : "/^[0-9]{1,6}(([\\.][0-9]{1,4})|([\\.]))?$/",// 金额，后两位小数格式：999999.99
	decmal7 : "^([0])(\.\d{0,4})?$",// 费率，后四位小数格式：0.0001
	intege : "^-?[1-9]\\d*$", // 整数
	intege1 : "^[1-9]\\d*$", // 正整数
	intege2 : "^-[1-9]\\d*$", // 负整数
	num : "^([+-]?)\\d*\\.?\\d+$", // 数字
	num1 : "^[1-9]\\d*|0$", // 正数（正整数 + 0）
	num2 : "^-[1-9]\\d*|0$", // 负数（负整数 + 0）
	ascii : "^[\\x00-\\xFF]+$", // 仅ACSII字符
	chinese : "^[\\u4e00-\\u9fa5]+$", // 仅中文
	color : "^[a-fA-F0-9]{6}$", // 颜色
	date : "^\\d{4}(\\-|\\/|\.)\\d{1,2}\\1\\d{1,2}$", // 日期
	email : "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$", // 邮件
	idcard : "^[1-9]([0-9]{14}|[0-9]{17})$", // 身份证
	ip4 : "^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$", // ip地址
	letter : "^[A-Za-z]+$", // 字母
	letter_l : "^[a-z]+$", // 小写字母
	letter_u : "^[A-Z]+$", // 大写字母
	mobile : "^0?(13|15|18|14)[0-9]{9}$", // 手机
	notempty : "^\\S+$", // 非空
	password : "^.*[A-Za-z0-9\\w_-]+.*$", // 密码
	fullNumber : "^[0-9]+$", // 数字
	picture : "(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$", // 图片
	qq : "^[1-9]{1}[0-9]{4,13}$", // QQ号码
	rar : "(.*)\\.(rar|zip|7zip|tgz)$", // 压缩文件
	tel : "^[0-9\-()（）]{7,18}$", // 电话号码的函数(包括验证国内区号,国际区号,分机号)
	tel1 : "/(^[0-9]{3,4}\-[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^[0-9]{3,4}[0-9]{7,8}$)|(^0{0,1}1[0-9]{10}$|(^\d{7,8,11}$))/",
	url : "^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$", // url
	username : "^[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+$", // 户名
	deptname : "^[A-Za-z0-9_()（）\\-\\u4e00-\\u9fa5]+$", // 单位名
	zipcode : "^\\d{6}$", // 邮编
	realname : "^[A-Za-z\\u4e00-\\u9fa5]+$", // 真实姓名
	companyname : "^[A-Za-z0-9_()（）\\-\\u4e00-\\u9fa5]+$",
	companyaddr : "^[A-Za-z0-9_()（）\\#\\-\\u4e00-\\u9fa5]+$",
	companysite : "^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&#=]*)?$",
	personId : "^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$",
	bankAccount:"^\d{15,19}$"   //银行卡号
// personId: "^\d{17}\w|\d{15}????$"
};
/*
 * 说 明： 简单密码验证数组 范 围: 本文档 调用方式：for(){}
 */
var weakPwdArray = [ "123456", "123456789", "111111", "5201314", "12345678",
		"123123", "password", "1314520", "123321", "7758521", "1234567",
		"5211314", "666666", "520520", "woaini", "520131", "11111111",
		"888888", "hotmail.com", "112233", "123654", "654321", "1234567890",
		"a123456", "88888888", "163.com", "000000", "yahoo.com.cn", "sohu.com",
		"yahoo.cn", "111222tianya", "163.COM", "tom.com", "139.com",
		"wangyut2", "pp.com", "yahoo.com", "147258369", "123123123", "147258",
		"987654321", "100200", "zxcvbnm", "123456a", "521521", "7758258",
		"111222", "110110", "1314521", "11111111", "12345678", "a321654",
		"111111", "123123", "5201314", "00000000", "q123456", "123123123",
		"aaaaaa", "a123456789", "qq123456", "11112222", "woaini1314",
		"a123123", "a111111", "123321", "a5201314", "z123456", "liuchang",
		"a000000", "1314520", "asd123", "88888888", "1234567890", "7758521",
		"1234567", "woaini520", "147258369", "123456789a", "woaini123",
		"q1q1q1q1", "a12345678", "qwe123", "123456q", "121212", "asdasd",
		"999999", "1111111", "123698745", "137900", "159357", "iloveyou",
		"222222", "31415926", "123456", "111111", "123456789", "123123",
		"9958123", "woaini521", "5201314", "18n28n24a5", "abc123", "password",
		"123qwe", "123456789", "12345678", "11111111", "dearbook", "00000000",
		"123123123", "1234567890", "88888888", "111111111", "147258369",
		"987654321", "aaaaaaaa", "1111111111", "66666666", "a123456789",
		"11223344", "1qaz2wsx", "xiazhili", "789456123", "password",
		"87654321", "qqqqqqqq", "000000000", "qwertyuiop", "qq123456",
		"iloveyou", "31415926", "12344321", "0000000000", "asdfghjkl",
		"1q2w3e4r", "123456abc", "0123456789", "123654789", "12121212",
		"qazwsxedc", "abcd1234", "12341234", "110110110", "asdasdasd",
		"123456", "22222222", "123321123", "abc123456", "a12345678",
		"123456123", "a1234567", "1234qwer", "qwertyui", "123456789a",
		"qq.com", "369369", "163.com", "ohwe1zvq", "xiekai1121", "19860210",
		"1984130", "81251310", "502058", "162534", "690929", "601445",
		"1814325", "as1230", "zz123456", "280213676", "198773", "4861111",
		"328658", "19890608", "198428", "880126", "6516415", "111213",
		"195561", "780525", "6586123", "caonima99", "168816", "123654987",
		"qq776491", "hahabaobao", "198541", "540707", "leqing123", "5403693",
		"123456", "123456789", "111111", "5201314", "123123", "12345678",
		"1314520", "123321", "7758521", "1234567", "5211314", "520520",
		"woaini", "520131", "666666", "RAND#a#8", "hotmail.com", "112233",
		"123654", "888888", "654321", "1234567890", "a123456" ];

/*
 * 说 明： 验证规则 范 围: 全局 调用方式：validateRules.isNull / validateRules.fullNumber
 */
var validateRules = {
	// 文本为空
	isNull : function(str) {
		return (str == "" || typeof str != "string");
	},
	// 文本不大于且不小于
	betweenLength : function(str, _min, _max) {
		return (str.length >= _min && str.length <= _max);
	},
	// 用户名
	isUid : function(str) {
		return new RegExp(validateRegExp.username).test(str);
	},
	// 全是数字
	fullNumber : function(str) {
		return new RegExp(validateRegExp.fullNumber).test(str);
	},
	// 密码格式（6-20位字符，字母数字或符号组合）
	isPwd : function(str) {
		return /^.*([\W_a-zA-z0-9-])+.*$/i.test(str);
	},
	isPwdRepeat : function(str1, str2) {
		return (str1 == str2);
	},
	// 邮箱格式验证
	isEmail : function(str) {
		return new RegExp(validateRegExp.email).test(str);
	},
	// 电话号码(包括验证国内区号,国际区号,分机号)
	isTel : function(str) {
		return new RegExp(validateRegExp.tel1).test(str);
	},
	// 手机验证
	isMobile : function(str) {
		return new RegExp(validateRegExp.mobile).test(str);
	},
	// 获取组件类型
	checkType : function(element) {
		return (element.attr("type") == "checkbox"
				|| element.attr("type") == "radio" || element.attr("rel") == "select");
	},
	// 真实姓名格式验证
	isRealName : function(str) {
		return new RegExp(validateRegExp.realname).test(str);
	},
	// 公司姓名验证
	isCompanyname : function(str) {
		return new RegExp(validateRegExp.companyname).test(str);
	},
	// 地址验证
	isCompanyaddr : function(str) {
		return new RegExp(validateRegExp.companyaddr).test(str);
	},
	// 网站验证
	isCompanysite : function(str) {
		return new RegExp(validateRegExp.companysite).test(str);
	},
	// 验证金额(正负金额+0)
	isAmount : function(str) {
		return new RegExp(validateRegExp.decmal3).test(str);
	},
	// 验证正数金额(正数金额+0)
	isPlusAmt : function(str) {
		return new RegExp(validateRegExp.decmal4).test(str);
	},
	// 验证负数金额(负数金额+0)
	isMinusAmt : function(str) {
		return new RegExp(validateRegExp.decmal5).test(str);
	},
	isNormalAmt : function(str) {
		return new RegExp(validateRegExp.decmal6).test(str);
	},
	isRate : function(str) {
		return new RegExp(validateRegExp.decmal7).test(str);
	},
	// 密码过于简单
	weakPwd : function(str) {
		for ( var i = 0; i < weakPwdArray.length; i++) {
			if (weakPwdArray[i] == str) {
				return true;
			}
		}
		return false;
	},
	// 网站地址
	isUrl : function(str) {
		return new RegExp(validateRegExp.url).test(str);
	},
	// qq
	isQQ : function(str) {
		return new RegExp(validateRegExp.qq).test(str);
	},
	// 邮编
	isZipCode : function(str) {
		return new RegExp(validateRegExp.zipcode).test(str);
	},
	// 身份证号码
	isPersonId : function(str) {
		return new RegExp(validateRegExp.personId).test(str);
	}
	
};

/**
 * 功能说明: 禁用页面组件 使用范围：删除的数据不允许修改功能调用 调用方式：机构管理模块删除不允许修改
 */
function setInputDisabled() {

	var submits = $(":submit");
	submits.attr("disabled", "disabled");
	submits.removeClass("formButton").addClass("formButtonDisabled");

	var selList = document.getElementsByTagName("select");
	for ( var i = 0; i < selList.length; i++) {
		selList[i].disabled = true;
	}

	var radList = document.getElementsByTagName("input");
	for ( var i = 0; i < (radList.length - 1); i++) {
		radList[i].disabled = true;
	}

	var texList = document.getElementsByTagName("textarea");
	for ( var i = 0; i < (texList.length - 1); i++) {
		texList[i].disabled = true;
	}

}
function getEle(id) {
	var ele = document.getElementById(id);
	return ele
}
