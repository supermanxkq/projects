文件上传
一、文件上传配置
<!-- 文件上传配置10M -->
	<bean id="multipartResolver"
        class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
		
		<property name="defaultEncoding" value="UTF-8"/>  
	    <property name="maxUploadSize" value="10000000"/>

	</bean>
二、jar包
com.springsource.org.apache.commons.fileupload-1.2.0.jar
com.springsource.org.apache.commons.io-1.4.0.jar
三、单文件上传请求
upload.do
四、多文件上传请求
upload2.do
五、访问
http://localhost:8080/SpringMvc04/index.html