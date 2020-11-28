<?xml version="1.0" encoding="UTF-8"?>
<web-app version="3.0" 
	xmlns="http://java.sun.com/xml/ns/javaee" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee 
	http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd">

	<jsp-config>
         <jsp-property-group>
             <description>
                  	制定include js文件时进行utf-8编码
             </description>
             <display-name>JSPConfiguration</display-name>
             <url-pattern>*.js</url-pattern>
             <el-ignored>true</el-ignored>
             <page-encoding>UTF-8</page-encoding>
             <scripting-invalid>false</scripting-invalid>
             <include-prelude></include-prelude>
             <include-coda></include-coda>
         </jsp-property-group>
     </jsp-config>
     
	<context-param>
		<param-name>webAppRootKey</param-name>
		<param-value>webApp.flysail.${webname}</param-value>
	</context-param>
	<servlet-mapping>
		<servlet-name>default</servlet-name>
		<url-pattern>/resources/*</url-pattern>
		<url-pattern>/mg/dbapi</url-pattern>
		<url-pattern>/mg/dbapi/json</url-pattern>
		<url-pattern>/mg/dbapi/mobi</url-pattern>
		<url-pattern>*.css</url-pattern>
		<url-pattern>*.js</url-pattern>
	</servlet-mapping>

  	<listener>
  		<listener-class>org.springframework.web.context.request.RequestContextListener</listener-class>
	</listener>
  
	<filter>
		<filter-name>characterEncodingFilter</filter-name>
		<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
		<init-param>
			<param-name>encoding</param-name>
			<param-value>UTF-8</param-value>
		</init-param>
		<init-param>
			<param-name>forceEncoding</param-name>
			<param-value>true</param-value>
		</init-param>
	</filter>

	<filter-mapping>
		<filter-name>characterEncodingFilter</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>
	
	<filter>
    	<filter-name>sitemesh</filter-name>
    	<filter-class>org.sitemesh.config.ConfigurableSiteMeshFilter</filter-class>
    </filter>
    <filter-mapping>
    	<filter-name>sitemesh</filter-name>
    	<url-pattern>/mg/*</url-pattern>
	</filter-mapping>

	<servlet>
		<servlet-name>flysail${webname}</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>
				 <!-- /WEB-INF/applicationContext-mysql.xml -->
				 classpath:applicationContext-mysql.xml
			</param-value>
		</init-param>
		<load-on-startup>1</load-on-startup>
	</servlet>

	<servlet>
        <servlet-name>context</servlet-name>
        <servlet-class>org.springframework.web.context.ContextLoaderServlet</servlet-class>
        <load-on-startup>2</load-on-startup>
  	</servlet>
  	
	<servlet-mapping>
		<servlet-name>flysail${webname}</servlet-name>
		<url-pattern>/</url-pattern>
	</servlet-mapping>
	
	<error-page>
       <error-code>404</error-code>
       <location>/404</location>
    </error-page>

	<error-page>
       <error-code>400</error-code>
       <location>/400</location>
    </error-page>

    <error-page>
       <error-code>505</error-code>                             
       <location>/505</location>
    </error-page>

    <error-page>
       <error-code>401</error-code>                             
       <location>/401</location>
    </error-page>
</web-app>