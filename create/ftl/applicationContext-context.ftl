<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:mvc="http://www.springframework.org/schema/mvc" 
	xmlns:util="http://www.springframework.org/schema/util"
	xmlns:aop="http://www.springframework.org/schema/aop" 
	xmlns:tx="http://www.springframework.org/schema/tx"

	xsi:schemaLocation="http://www.springframework.org/schema/mvc 
	http://www.springframework.org/schema/mvc/spring-mvc-4.0.xsd
		http://www.springframework.org/schema/beans 
		http://www.springframework.org/schema/beans/spring-beans-4.0.xsd
		http://www.springframework.org/schema/context 
		http://www.springframework.org/schema/context/spring-context-4.0.xsd
		    http://www.springframework.org/schema/util 
    http://www.springframework.org/schema/util/spring-util-4.0.xsd
     http://www.springframework.org/schema/tx 
     http://www.springframework.org/schema/tx/spring-tx-4.0.xsd
     http://www.springframework.org/schema/aop 
     http://www.springframework.org/schema/aop/spring-aop-4.0.xsd">

	<aop:aspectj-autoproxy/>
	<!-- Scans the classpath of this application for @Components to deploy as beans -->
	<context:component-scan base-package="com" />

	<!-- enable autowire -->
	<context:annotation-config />
	
	<!-- Configures the @Controller programming model -->
	<mvc:annotation-driven>
	 <mvc:message-converters>  
            <bean class="${packagename}.web.GsonHttpMessageConverter" />  
             <!-- 处理responseBody 里面日期类型 -->  
            <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">  
                <property name="objectMapper">
				     <bean class="org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean"/>
				   </property> 
            </bean>  
        </mvc:message-converters>  
	</mvc:annotation-driven>

	<!-- Forwards requests to the "/" resource to the "welcome" view -->
	<mvc:view-controller path="/" view-name="welcome" />

	<mvc:interceptors>
		<mvc:interceptor>
			<mvc:mapping path="/apijson/**" />
			<bean class="${packagename}.web.interceptor.InterfaceJsonHandlerInterceptorAdapter"></bean>
		</mvc:interceptor>
	</mvc:interceptors>
	
	<mvc:interceptors>
		<mvc:interceptor>
			<mvc:mapping path="/apirest/**" />
			<bean class="${packagename}.web.interceptor.InterfaceRestnHandlerInterceptorAdapter"></bean>
		</mvc:interceptor>
	</mvc:interceptors>
	
	<mvc:interceptors>
		<mvc:interceptor>
			<mvc:mapping path="/apimobi/**" />
			<bean class="${packagename}.web.interceptor.InterfaceMobiHandlerInterceptorAdapter"></bean>
		</mvc:interceptor>
	</mvc:interceptors>
	
	<mvc:interceptors>
		<mvc:interceptor>
			<mvc:mapping path="/mg/**" />
			<bean class="${packagename}.web.interceptor.MangerViewHandlerInterceptorAdapter"></bean>
		</mvc:interceptor>
	</mvc:interceptors>
	
	<mvc:interceptors>
		<mvc:interceptor>
			<mvc:mapping path="/view/**" />
<!-- 		<mvc:exclude-mapping path="/"/> -->
			<bean class="${packagename}.web.interceptor.WebViewHandlerInterceptorAdapter"></bean>
		</mvc:interceptor>
	</mvc:interceptors>
 
	<mvc:interceptors>
		<bean class="${packagename}.web.interceptor.GlobalAnnotationInterceptor"></bean>
	</mvc:interceptors>

	<!-- ContentNegotiatingViewResolver视图解析器,利用他就可以配置多种返回值 -->
	<bean class="org.springframework.web.servlet.view.ContentNegotiatingViewResolver">
		<!-- 这里是否忽略掉accept header，默认就是false -->
		<property name="ignoreAcceptHeader" value="true" />
		<!-- 如果所有的mediaType都没匹配上，就会使用defaultContentType -->
		<property name="defaultContentType" value="text/html" />
		<property name="mediaTypes">
			<map>
				<entry key="json" value="application/json" />
			</map>
		</property>
		<!-- 默认使用MappingJacksonJsonView生成jsonview-->
		<property name="defaultViews">
			<list>
				<bean class="org.springframework.web.servlet.view.json.MappingJackson2JsonView">
					<property name="extractValueFromSingleKeyModel" value="true" />
				</bean>
			</list>
		</property>
	</bean>

  	<!-- 上面没匹配到则会使用这个视图解析器 -->
	<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="prefix" value="/pages/" />
		<property name="suffix" value=".jsp" />
	</bean>
	
	<bean class="org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor" />
 
<!-- scan for mappers and let them be autowired -->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="mappers/**" />
    </bean>
    
     <!-- define the SqlSessionFactory -->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource" />
        <property name="typeAliasesPackage" value="${packagename}.entity.sql" />
        <!-- <property name="plugins">  
       <array>  
          <bean class="${packagename}.mybatis.interceptor.MapInterceptor"/>  
       </array>  
   </property> --> 
    </bean>
    
    <!-- 移动端接口注入 -->
    <bean id="mobiinter" class="${packagename}.web.interceptor.InterfaceMobiInterceptor"></bean>
    
    <util:properties id="settings" location="classpath:site.properties" />
    <!-- 支持上传文件 -->  
    <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver"/>
      
</beans>
