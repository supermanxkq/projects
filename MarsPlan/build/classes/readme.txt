1、初始化系统模块以及相应权限：在Globals.java中ISINIT_MODULE_PRIVILEGE值设置成true;
2、如初始化需创建角色信息：在Globals.java中ISCREATE_ADMIN_ROLE_USER值设置成true，
	ROLE_CODE值对应会员系统角色编号，ROLE_NAME为待创建的角色名称;（慎用）
3、系统初始化执行路径：http://localhost:8080/omall/system/init!init?sign=TYT57GFR68IJGT8EOMFMU8WR87326GFS
http://localhost:8080/MarsPlan/system/init!init?sign=TYT57GFR68IJGT8EOMFMU8WR87326GFS
4、删除系统自带struts2包
	antlr-2.7.2.jar
	struts2-convention-plugin-2.1.8.jar
	struts2-json-plugin-2.1.8.jar
	
	
	
	
	
	火星计划V1.0
	***************************
	代码库前台
	代码库后台管理
	ps:后台管理没有进行验证
	
	2016-03-06
	
	2016-03-09
	因为一个>的注释没有去掉，导致导航条显示格式不正确
	因为在修改按钮处没有添加check方法，导致更新code信息的时候不可以更新代码的说明信息。
	
	火星计划V2.0
	添加lucene搜索功能--------ok
	切换前台显示样式-----------ok
	添加阅读次数功能------------ok
	去掉360安全扫描，去掉QQ交谈，加入QQ群插件-----ok
	
	
	