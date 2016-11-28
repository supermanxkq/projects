springMVC第一个程序Hello,world!
知识点总结：

①//配置控制器，指定springMVC配置文件，过滤请求配置
<servlet>
		<servlet-name>springmvc</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>classpath:spring-mvc.xml</param-value>
		</init-param>
	</servlet>
	<servlet-mapping>
		<servlet-name>springmvc</servlet-name>
		<url-pattern>*.do</url-pattern>
	</servlet-mapping>

②<!-- 使用注解的包，包括子集 -->
    <context:component-scan base-package="com.java1234"/>
    
③<!-- 视图解析器 -->
	<bean id="viewResolver"
		class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="prefix" value="/WEB-INF/jsp/" />
		<property name="suffix" value=".jsp"></property>
	</bean>
④	// 请求地址配置
	@RequestMapping("/helloWorld")
⑤//封装要传递的数据
model.addAttribute("message", "StringMvc他大爷");

2016年1月21日 17:25:41