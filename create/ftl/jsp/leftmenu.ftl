<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
.sidebar .navigation.bordered li{
	overflow:hidden;
}
.sidebar .navigation.bordered li ul > li a {
	width: 150px; 
}
.help{
	width:24px;
	font-size: 18px;
	color: #C9C9C9;	
	float:left; 
	line-height: 16px;
	border-bottom: 1px solid #1A1A1A; 
	text-decoration: none;  margin: 0px;
}  
.fa{
	height:40px;
	line-height:40px;
	background: #222222
}
.top{
font-size: 18px; 
color:white;
}              
</style>         
            <div class="top">
            	<div>用户：<#noparse>$</#noparse>{sessionScope.usershowname}<a href="<c:url value='/signout' />" class="button" style="float: right;">退出</a></div><br/>
                <a href="/pages/docs/databaseapi.html" class="button" target="blank">DATABASE API</a><br/>
                <a href="<c:url value='/mg/dbapi/json' />" class="button" target="blank">JSON格式接口  API</a><br/>
                <a href="<c:url value='/mg/dbapi/mobi' />" class="button" target="blank">移动端接口  API</a>
            </div>
            <!-- <li><a></a>
				<ul>
				</ul>
				</li> -->
            <ul class="navigation">  
            	${menu}          
            </ul>
            
            <div class="widget">
                <div class="datepicker"></div>
            </div>
            
