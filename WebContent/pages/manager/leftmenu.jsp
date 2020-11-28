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
            	<div>用户：${sessionScope.usershowname}<a href="<c:url value='/signout' />" class="button" style="float: right;">退出</a></div><br/>
                <a href="/pages/docs/databaseapi.html" class="button" target="blank">DATABASE API</a><br/>
                <a href="<c:url value='/mg/dbapi/json' />" class="button" target="blank">JSON格式接口  API</a><br/>
                <a href="<c:url value='/mg/dbapi/mobi' />" class="button" target="blank">移动端接口  API</a>
            </div>
            <!-- <li><a></a>
				<ul>
				</ul>
				</li> -->
            <ul class="navigation">  
            	<li class='blblue'><a href="<c:url value='/mg/cyzyverifylogth' />" style="width:80%"><%-- 子菜单删除  style="width:80%" --%>CYZY_VERIFY_LOG_TH</a><div class="help fa"><!-- 子菜单删除 fa --><a href="<c:url value='/mg/cyzyverifylogth/new' />">+1</a></div>
</li>

<li class='blblue'><a href="<c:url value='/mg/cyzyusert' />" style="width:80%"><%-- 子菜单删除  style="width:80%" --%>CYZY_USER_T</a><div class="help fa"><!-- 子菜单删除 fa --><a href="<c:url value='/mg/cyzyusert/new' />">+1</a></div>
</li>

<li class='blblue'><a href="<c:url value='/mg/cyzyuserrolet' />" style="width:80%"><%-- 子菜单删除  style="width:80%" --%>CYZY_USER_ROLE_T</a><div class="help fa"><!-- 子菜单删除 fa --><a href="<c:url value='/mg/cyzyuserrolet/new' />">+1</a></div>
</li>

<li class='blblue'><a href="<c:url value='/mg/cyzythematict' />" style="width:80%"><%-- 子菜单删除  style="width:80%" --%>CYZY_THEMATIC_T</a><div class="help fa"><!-- 子菜单删除 fa --><a href="<c:url value='/mg/cyzythematict/new' />">+1</a></div>
</li>

<li class='blblue'><a href="<c:url value='/mg/cyzyshortmsglogt' />" style="width:80%"><%-- 子菜单删除  style="width:80%" --%>CYZY_SHORT_MSG_LOG_T</a><div class="help fa"><!-- 子菜单删除 fa --><a href="<c:url value='/mg/cyzyshortmsglogt/new' />">+1</a></div>
</li>

<li class='blblue'><a href="<c:url value='/mg/cyzyrolet' />" style="width:80%"><%-- 子菜单删除  style="width:80%" --%>CYZY_ROLE_T</a><div class="help fa"><!-- 子菜单删除 fa --><a href="<c:url value='/mg/cyzyrolet/new' />">+1</a></div>
</li>

<li class='blblue'><a href="<c:url value='/mg/cyzyrolemenut' />" style="width:80%"><%-- 子菜单删除  style="width:80%" --%>CYZY_ROLE_MENU_T</a><div class="help fa"><!-- 子菜单删除 fa --><a href="<c:url value='/mg/cyzyrolemenut/new' />">+1</a></div>
</li>

<li class='blblue'><a href="<c:url value='/mg/cyzyreviselogth' />" style="width:80%"><%-- 子菜单删除  style="width:80%" --%>CYZY_REVISE_LOG_TH</a><div class="help fa"><!-- 子菜单删除 fa --><a href="<c:url value='/mg/cyzyreviselogth/new' />">+1</a></div>
</li>

<li class='blblue'><a href="<c:url value='/mg/cyzyprojectt' />" style="width:80%"><%-- 子菜单删除  style="width:80%" --%>CYZY_PROJECT_T</a><div class="help fa"><!-- 子菜单删除 fa --><a href="<c:url value='/mg/cyzyprojectt/new' />">+1</a></div>
</li>

<li class='blblue'><a href="<c:url value='/mg/cyzyprojectreadt' />" style="width:80%"><%-- 子菜单删除  style="width:80%" --%>CYZY_PROJECT_READ_T</a><div class="help fa"><!-- 子菜单删除 fa --><a href="<c:url value='/mg/cyzyprojectreadt/new' />">+1</a></div>
</li>

<li class='blblue'><a href="<c:url value='/mg/cyzyprojectintent' />" style="width:80%"><%-- 子菜单删除  style="width:80%" --%>CYZY_PROJECT_INTEN_T</a><div class="help fa"><!-- 子菜单删除 fa --><a href="<c:url value='/mg/cyzyprojectintent/new' />">+1</a></div>
</li>

<li class='blblue'><a href="<c:url value='/mg/cyzyprojectcollectt' />" style="width:80%"><%-- 子菜单删除  style="width:80%" --%>CYZY_PROJECT_COLLECT_T</a><div class="help fa"><!-- 子菜单删除 fa --><a href="<c:url value='/mg/cyzyprojectcollectt/new' />">+1</a></div>
</li>

<li class='blblue'><a href="<c:url value='/mg/cyzypolicyt' />" style="width:80%"><%-- 子菜单删除  style="width:80%" --%>CYZY_POLICY_T</a><div class="help fa"><!-- 子菜单删除 fa --><a href="<c:url value='/mg/cyzypolicyt/new' />">+1</a></div>
</li>

<li class='blblue'><a href="<c:url value='/mg/cyzypaget' />" style="width:80%"><%-- 子菜单删除  style="width:80%" --%>CYZY_PAGE_T</a><div class="help fa"><!-- 子菜单删除 fa --><a href="<c:url value='/mg/cyzypaget/new' />">+1</a></div>
</li>

<li class='blblue'><a href="<c:url value='/mg/cyzynewst' />" style="width:80%"><%-- 子菜单删除  style="width:80%" --%>CYZY_NEWS_T</a><div class="help fa"><!-- 子菜单删除 fa --><a href="<c:url value='/mg/cyzynewst/new' />">+1</a></div>
</li>

<li class='blblue'><a href="<c:url value='/mg/cyzymenut' />" style="width:80%"><%-- 子菜单删除  style="width:80%" --%>CYZY_MENU_T</a><div class="help fa"><!-- 子菜单删除 fa --><a href="<c:url value='/mg/cyzymenut/new' />">+1</a></div>
</li>

<li class='blblue'><a href="<c:url value='/mg/cyzymanort' />" style="width:80%"><%-- 子菜单删除  style="width:80%" --%>CYZY_MANOR_T</a><div class="help fa"><!-- 子菜单删除 fa --><a href="<c:url value='/mg/cyzymanort/new' />">+1</a></div>
</li>

<li class='blblue'><a href="<c:url value='/mg/cyzymainestatet' />" style="width:80%"><%-- 子菜单删除  style="width:80%" --%>CYZY_MAIN_ESTATE_T</a><div class="help fa"><!-- 子菜单删除 fa --><a href="<c:url value='/mg/cyzymainestatet/new' />">+1</a></div>
</li>

<li class='blblue'><a href="<c:url value='/mg/cyzylogt' />" style="width:80%"><%-- 子菜单删除  style="width:80%" --%>CYZY_LOG_T</a><div class="help fa"><!-- 子菜单删除 fa --><a href="<c:url value='/mg/cyzylogt/new' />">+1</a></div>
</li>

<li class='blblue'><a href="<c:url value='/mg/cyzyfriendlinkt' />" style="width:80%"><%-- 子菜单删除  style="width:80%" --%>CYZY_FRIEND_LINK_T</a><div class="help fa"><!-- 子菜单删除 fa --><a href="<c:url value='/mg/cyzyfriendlinkt/new' />">+1</a></div>
</li>

<li class='blblue'><a href="<c:url value='/mg/cyzyexpot' />" style="width:80%"><%-- 子菜单删除  style="width:80%" --%>CYZY_EXPO_T</a><div class="help fa"><!-- 子菜单删除 fa --><a href="<c:url value='/mg/cyzyexpot/new' />">+1</a></div>
</li>

<li class='blblue'><a href="<c:url value='/mg/cyzydictt' />" style="width:80%"><%-- 子菜单删除  style="width:80%" --%>CYZY_DICT_T</a><div class="help fa"><!-- 子菜单删除 fa --><a href="<c:url value='/mg/cyzydictt/new' />">+1</a></div>
</li>

<li class='blblue'><a href="<c:url value='/mg/cyzydictitemt' />" style="width:80%"><%-- 子菜单删除  style="width:80%" --%>CYZY_DICT_ITEM_T</a><div class="help fa"><!-- 子菜单删除 fa --><a href="<c:url value='/mg/cyzydictitemt/new' />">+1</a></div>
</li>

<li class='blblue'><a href="<c:url value='/mg/cyzycommentlogth' />" style="width:80%"><%-- 子菜单删除  style="width:80%" --%>CYZY_COMMENT_LOG_TH</a><div class="help fa"><!-- 子菜单删除 fa --><a href="<c:url value='/mg/cyzycommentlogth/new' />">+1</a></div>
</li>

<li class='blblue'><a href="<c:url value='/mg/cyzyattachth' />" style="width:80%"><%-- 子菜单删除  style="width:80%" --%>CYZY_ATTACH_TH</a><div class="help fa"><!-- 子菜单删除 fa --><a href="<c:url value='/mg/cyzyattachth/new' />">+1</a></div>
</li>

<li class='blblue'><a href="<c:url value='/mg/cyzyapit' />" style="width:80%"><%-- 子菜单删除  style="width:80%" --%>接口权限</a><div class="help fa"><!-- 子菜单删除 fa --><a href="<c:url value='/mg/cyzyapit/new' />">+1</a></div>
</li>

<li class='blblue'><a href="<c:url value='/mg/cyzyakeyunitt' />" style="width:80%"><%-- 子菜单删除  style="width:80%" --%>CYZY_AKEY_UNIT_T</a><div class="help fa"><!-- 子菜单删除 fa --><a href="<c:url value='/mg/cyzyakeyunitt/new' />">+1</a></div>
</li>

<li class='blblue'><a href="<c:url value='/mg/cyzyadvertt' />" style="width:80%"><%-- 子菜单删除  style="width:80%" --%>CYZY_ADVERT_T</a><div class="help fa"><!-- 子菜单删除 fa --><a href="<c:url value='/mg/cyzyadvertt/new' />">+1</a></div>
</li>

<li class='blblue'><a href="<c:url value='/mg/cyzyactivet' />" style="width:80%"><%-- 子菜单删除  style="width:80%" --%>CYZY_ACTIVE_T</a><div class="help fa"><!-- 子菜单删除 fa --><a href="<c:url value='/mg/cyzyactivet/new' />">+1</a></div>
</li>

</li></ul>          
            </ul>
            
            <div class="widget">
                <div class="datepicker"></div>
            </div>
            
