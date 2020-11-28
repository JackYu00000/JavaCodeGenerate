<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<#include "../../function.ftl">
<jsp:useBean id="showcolumn" class="java.util.HashMap" scope="page" />
<jsp:useBean id="searchcolumn" class="java.util.HashMap" scope="page" />
<#list cols as col><#if (col.inSelfTable && !col.primaryKey)>
<#if (col.inSelfTable)>
<c:set target="<#noparse>$</#noparse>{searchcolumn}" property="<@genAttributeByCol colName_ABC='${col.code}'/>" value="true"/><#noparse><%--</#noparse>${col.name}<#noparse>--%></#noparse>
<#else>
<c:set target="<#noparse>$</#noparse>{searchcolumn}" property="<@genAttributeByCol colName_ABC='${col.code}'/>" value="false"/><#noparse><%--</#noparse>${col.name}<#noparse>--%></#noparse>
</#if></#if></#list>
<#list cols as col><#if (col.inSelfTable)>
<#if (code?starts_with('T_DICT_') || code?starts_with('T_SUBSET_'))>
<c:set target="<#noparse>$</#noparse>{showcolumn}" property="<@genAttributeByCol colName_ABC='${col.code}'/>" value="true"/><#noparse><%--</#noparse>${col.name}<#noparse>--%></#noparse>
<#else>
	<#if (col.type?upper_case?contains('VARCHAR'))>
		<#if (col.type?replace('\\D','','r')?number>20)>	
<c:set target="<#noparse>$</#noparse>{showcolumn}" property="<@genAttributeByCol colName_ABC='${col.code}'/>" value="false"/><#noparse><%--</#noparse>${col.name}<#noparse>--%></#noparse>
		<#else>
<c:set target="<#noparse>$</#noparse>{showcolumn}" property="<@genAttributeByCol colName_ABC='${col.code}'/>" value="true"/><#noparse><%--</#noparse>${col.name}<#noparse>--%></#noparse>
		</#if>
	<#elseif (col.type?upper_case?contains("TEXT"))>
<c:set target="<#noparse>$</#noparse>{showcolumn}" property="<@genAttributeByCol colName_ABC='${col.code}'/>" value="false"/><#noparse><%--</#noparse>${col.name}<#noparse>--%></#noparse>
	<#else>
<c:set target="<#noparse>$</#noparse>{showcolumn}" property="<@genAttributeByCol colName_ABC='${col.code}'/>" value="true"/><#noparse><%--</#noparse>${col.name}<#noparse>--%></#noparse>
	</#if>
</#if>
</#if></#list>
