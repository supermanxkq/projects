springMVC增删改查的模拟练习，用户的登录验证，Ajax操作，Ajax数据打印工具类ResponseUtil.java
①接收参数
@RequestParam(value = "id", required = false) 
required=false,表示是否必要
②实体和视图的设置
ModelAndView mav = new ModelAndView();
③重定向请求地址
return "redirect:/student/list.do";
④乱码处理，配置spring封装好的filter
	<filter>
		<filter-name>characterEncodingFilter</filter-name>
		<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
		<init-param>
			<param-name>encoding</param-name>
			<param-value>utf-8</param-value>
		</init-param>
	</filter>
	<filter-mapping>
		<filter-name>characterEncodingFilter</filter-name>
		<url-pattern>*.do</url-pattern>
	</filter-mapping>
⑤转发,url地址不会改变，重定向url地址会改变
return "forward:/student/list.do";

⑥cookie的使用方法
Cookie cookie=new Cookie("user",userName+"-"+password);
		//设置cookie的有效时间，以秒为单位，这里为一周
		cookie.setMaxAge(1*60*60*24*7);
		User currentUser=new User(userName,password);
		response.addCookie(cookie);
⑦Ajax依赖
jar包
jackson-annotations-2.2.1.jar
jackson-core-2.2.1.jar
jackson-core-asl-1.8.8.jar
jackson-databind-2.2.1.jar
jackson-mapper-asl-1.8.8.jar
jackson-module-jaxb-annotations-2.2.1.jar
springMVC配置
命名空间
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:p="http://www.springframework.org/schema/p"
    xmlns:context="http://www.springframework.org/schema/context"
    xmlns:mvc="http://www.springframework.org/schema/mvc"
    xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/mvc
        http://www.springframework.org/schema/mvc/spring-mvc.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">
 <!-- 支持对象与json的转换。需要加xmlns:mvc的命名空间,加入json的jar包 -->
	<mvc:annotation-driven />
	
	
	
 //@ResponseBody将user自动转换为json对象       




2016年1月21日 17:25:52
