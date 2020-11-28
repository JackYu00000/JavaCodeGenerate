<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<#include "../../function.ftl">
<#assign hasoption>${checkColHasOption(cols)?string}</#assign>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/easyui/easyui.css" />">
<script type="text/javascript" src="<c:url value="/resources/js/jquery.easyui.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/easyui-lang-zh_CN.js" />"></script>
<script type='text/javascript' src="<c:url value='/resources/js/plugins/uniform/jquery.uniform.min.js' />"></script>
<#if (hasoption=='true')>
<script type='text/javascript' src="<c:url value='/resources/js/plugins/select/select2.min.js' />"></script>
<script type='text/javascript' src="<c:url value='/resources/js/plugins/select/select2_locale_zh-CN.js' />"></script>
<link href="<c:url value='/resources/css/select/select2.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/resources/css/select/select2-bootstrap.css' />" rel="stylesheet" type="text/css" />
</#if>	
<script type="text/javascript">
	$(document).ready(function(){
		<c:choose>
			<c:when test="<#noparse>$</#noparse>{${tab?uncap_first}.searchKey!=null && ${tab?uncap_first}.searchKey!=''}">
		$('div.searchField').slideUp();
		$('li[class="searchKey"]').addClass('active');
		<c:forEach items="<#noparse>${</#noparse>${tab?uncap_first}.searchCols}" var="col">
		if(<#noparse>$</#noparse>('input.searchCol<#noparse>[</#noparse>value="<#noparse>${</#noparse>col}"]<#noparse>')</#noparse>.attr("checked") != "checked")
			<#noparse>$</#noparse>('input.searchCol<#noparse>[</#noparse>value="<#noparse>${</#noparse>col}"]<#noparse>')</#noparse>.click();
     	<#noparse>$</#noparse>("td:contains('<#noparse>${</#noparse>${tab?uncap_first}.searchKey}')").filter(".<#noparse>${</#noparse>col}").each(function(){
				<#noparse>$</#noparse>(this).html(<#noparse>$</#noparse>(this).text().replace("<#noparse>${</#noparse>${tab?uncap_first}.searchKey}","<span class='cred yellow' style='font-size:14px'><#noparse>${</#noparse>${tab?uncap_first}.searchKey}</span>"));
		});
  		</c:forEach>
			</c:when>
			<c:otherwise>
		$('div.searchKey').slideUp();
		$('li[class="searchField"]').addClass('active');
		<#list cols as col>
		<#if (!col.primaryKey && col.inTableColumn)><c:if test="<#noparse>$</#noparse>{searchcolumn.<@genAttributeByCol colName_ABC='${col.code}'/>==true}">
		<#if col.type?upper_case?contains("INT")>
		<c:if test="<#noparse>$</#noparse>{${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>!=null}" >
		<#else>
		<c:if test="<#noparse>$</#noparse>{${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>!=null && ${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>!=''}" >
		</#if>
		<#if (col.comment!?index_of("RADIO")>-1) || (col.comment!?index_of("CHECKBOX")>-1)>
			<#if (col.comment?split(",")?size<1)>
			for(var i<@genAttributeByCol colName_ABC='${col.code}'/>=0;i<@genAttributeByCol colName_ABC='${col.code}'/><#noparse><('${</#noparse>${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>}').split(",").length;i<@genAttributeByCol colName_ABC='${col.code}'/>++){
				<#noparse>$</#noparse>("td").filter(function(){return $(this).text().trim()==<#noparse>$(</#noparse>'input.<@genAttributeByCol colName_ABC='${col.code}'/><#noparse>[value="' + ('${</#noparse>${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>}').split(",")[i<@genAttributeByCol colName_ABC='${col.code}'/>] + '"]').attr("data-show");}).filter(".<@genAttributeByCol colName_ABC='${col.code}'/>").each(function(){
				if(<#noparse>$</#noparse>('input.<@genAttributeByCol colName_ABC='${col.code}'/><#noparse>[</#noparse>value="' + ('<#noparse>${</#noparse>${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>}').split(",")[i<@genAttributeByCol colName_ABC='${col.code}'/>] + '"]<#noparse>')</#noparse>.attr("checked") != "checked")
					<#noparse>$</#noparse>('input.<@genAttributeByCol colName_ABC='${col.code}'/><#noparse>[</#noparse>value="' + ('<#noparse>${</#noparse>${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>}').split(",")[i<@genAttributeByCol colName_ABC='${col.code}'/>] + '"]<#noparse>')</#noparse>.click();
				<#noparse>$</#noparse>(this).html(<#noparse>$</#noparse>(this).text().replace(<#noparse>$('input.</#noparse><@genAttributeByCol colName_ABC='${col.code}'/>[value="' + ('<#noparse>${</#noparse>${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>}').split(",")[i<@genAttributeByCol colName_ABC='${col.code}'/>] + '"]').attr('data-show'),"<span class='cred yellow' style='font-size:14px'>" + <#noparse>$('</#noparse>input.<@genAttributeByCol colName_ABC='${col.code}'/>[value="' + ('<#noparse>${</#noparse>${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>}').split(",")[i<@genAttributeByCol colName_ABC='${col.code}'/>] + '"]').attr('data-show') + "</span>"));
			});}
			<#else>
			<#noparse>$</#noparse>('select.<@genAttributeByCol colName_ABC='${col.code}'/>').val([<#noparse>${</#noparse>${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>}]<#noparse>)</#noparse>.trigger('change');
			for(var i<@genAttributeByCol colName_ABC='${col.code}'/>=0;i<@genAttributeByCol colName_ABC='${col.code}'/><#noparse><('${</#noparse>${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>}').split(",").length;i<@genAttributeByCol colName_ABC='${col.code}'/>++){
				var tmpReplaceText = <#noparse>$(</#noparse>'option<#noparse>[value="' + ('${</#noparse>${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>}').split(",")[i<@genAttributeByCol colName_ABC='${col.code}'/>] + '"]').text();
				<#noparse>$</#noparse>("td").filter(function(){return $(this).text().trim()==tmpReplaceText;}).filter(".<@genAttributeByCol colName_ABC='${col.code}'/>").each(function(){
    			<#noparse>$</#noparse>(this).html(<#noparse>$</#noparse>(this).text().replace(tmpReplaceText,"<span class='cred yellow' style='font-size:14px'>" + tmpReplaceText + "</span>"));
			});}
			</#if>
		<#elseif (col.comment!?index_of("OPTION")>-1 || col.joinTables!?size>0)>
			<#if (col.joinTables!?size==0)>
			<#noparse>$(</#noparse>'select.<@genAttributeByCol colName_ABC='${col.code}'/>').val([<#noparse>${</#noparse>${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>}]<#noparse>)</#noparse>.trigger('change');
			for(var i<@genAttributeByCol colName_ABC='${col.code}'/>=0;i<@genAttributeByCol colName_ABC='${col.code}'/><#noparse><('${</#noparse>${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>}').split(",").length;i<@genAttributeByCol colName_ABC='${col.code}'/>++){
				var tmpReplaceText = <#noparse>$(</#noparse>'option<#noparse>[value="' + ('${</#noparse>${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>}').split(",")[i<@genAttributeByCol colName_ABC='${col.code}'/>] + '"]').text();
				<#noparse>$</#noparse>("td").filter(function(){return $(this).text().trim()==tmpReplaceText;}).filter(".<@genAttributeByCol colName_ABC='${col.code}'/>").each(function(){
    			<#noparse>$</#noparse>(this).html(<#noparse>$</#noparse>(this).text().replace(tmpReplaceText,"<span class='cred yellow' style='font-size:14px'>" + tmpReplaceText + "</span>"));
			});}
			</#if>
		<#elseif (col.type?upper_case=="DATE")>
			$("td:contains('<fmt:formatDate value='<#noparse>${</#noparse>${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/><#noparse>}</#noparse>' pattern='yyyy-MM-dd' />')").filter(".<@genAttributeByCol colName_ABC='${col.code}'/>").each(function(){
				<#noparse>$</#noparse>(this).html(<#noparse>$</#noparse>(this).text().replace("<fmt:formatDate value='<#noparse>${</#noparse>${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/><#noparse>}</#noparse>' pattern='yyyy-MM-dd' />","<span class='cred yellow' style='font-size:14px'><fmt:formatDate value='<#noparse>${</#noparse>${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/><#noparse>}</#noparse>' pattern='yyyy-MM-dd' /></span>"));
			});
		<#elseif (col.type?upper_case=="DATETIME")>
			$("td:contains('<fmt:formatDate value='<#noparse>${</#noparse>${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/><#noparse>}</#noparse>' pattern='yyyy-MM-dd' />')").filter(".<@genAttributeByCol colName_ABC='${col.code}'/>").each(function(){
				<#noparse>$</#noparse>(this).html(<#noparse>$</#noparse>(this).text().replace("<fmt:formatDate value='<#noparse>${</#noparse>${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/><#noparse>}</#noparse>' type='both' />","<span class='cred yellow' style='font-size:14px'><fmt:formatDate value='<#noparse>${</#noparse>${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/><#noparse>}</#noparse>' pattern='yyyy-MM-dd' /></span>"));
			});
		<#elseif (col.type?upper_case=="TIME")>
			$("td:contains('<fmt:formatDate value='<#noparse>${</#noparse>${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/><#noparse>}</#noparse>' pattern='yyyy-MM-dd' />')").filter(".<@genAttributeByCol colName_ABC='${col.code}'/>").each(function(){
				<#noparse>$</#noparse>(this).html(<#noparse>$</#noparse>(this).text().replace("<fmt:formatDate value='<#noparse>${</#noparse>${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/><#noparse>}</#noparse>' type='time' />","<span class='cred yellow' style='font-size:14px'><fmt:formatDate value='<#noparse>${</#noparse>${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/><#noparse>}</#noparse>' pattern='yyyy-MM-dd' /></span>"));
			});
		<#else>
			<#assign colInteger>${checkColTypeIsToJavaNotString(col.type)?string}</#assign>
			<#if colInteger=='true'>
				<#noparse>$</#noparse>(this).html(<#noparse>$</#noparse>(this).text().replace(('<#noparse>${</#noparse>${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>}').split(" ")[i<@genAttributeByCol colName_ABC='${col.code}'/>],"<span class='cred yellow' style='font-size:14px'>" + ('<#noparse>${</#noparse>${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>}').split(" ")[i<@genAttributeByCol colName_ABC='${col.code}'/>] + "</span>"));
			<#else>
			<c:choose>
				<c:when test="<#noparse>$</#noparse>{fn:contains(${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>,'+')}">
				<c:set var="${col.code}List" value="<#noparse>${</#noparse>fn:split(${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>, '+')}"/>
				var reg = /<c:forEach items="<#noparse>${</#noparse>${col.code}List}" var="${col.code}" varStatus="vs">(.*)<#noparse>${</#noparse>${col.code}}</c:forEach>(.*)/;
				var regstr = '<c:forEach items="<#noparse>${</#noparse>${col.code}List}" var="${col.code}" varStatus="vs"><#noparse>$${</#noparse>vs.index+1}<span class="cred yellow" style="font-size:14px"><#noparse>${</#noparse>${col.code}}</span></c:forEach>';
				$('td<c:forEach items="<#noparse>${</#noparse>${col.code}List}" var="${col.code}" varStatus="vs">:contains("<#noparse>${</#noparse>${col.code}}")</c:forEach>').each(function(){$(this).html($(this).text().trim().replace(reg,regstr));});
				</c:when>
				<c:otherwise>
			for(var i<@genAttributeByCol colName_ABC='${col.code}'/>=0;i<@genAttributeByCol colName_ABC='${col.code}'/><#noparse><('${</#noparse>${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>}').split(" ").length;i<@genAttributeByCol colName_ABC='${col.code}'/>++){
				$("td:contains('" + ('<#noparse>${</#noparse>${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>}').split(" ")[i<@genAttributeByCol colName_ABC='${col.code}'/>] + "')").filter(".<@genAttributeByCol colName_ABC='${col.code}'/>").each(function(){
					<#noparse>$</#noparse>(this).html(<#noparse>$</#noparse>(this).text().replace(('<#noparse>${</#noparse>${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>}').split(" ")[i<@genAttributeByCol colName_ABC='${col.code}'/>],"<span class='cred yellow' style='font-size:14px'>" + ('<#noparse>${</#noparse>${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>}').split(" ")[i<@genAttributeByCol colName_ABC='${col.code}'/>] + "</span>"));
				});
			}
				</c:otherwise>
			</c:choose>
			</#if>
		</#if>
		</c:if></c:if>
		</#if>
		</#list>
		<#list cols as col>
		<#assign colInteger>${checkColTypeIsToJavaNotString(col.type)?string}</#assign>
		<#if (colInteger=='true' && col.inTableColumn)><c:if test="<#noparse>$</#noparse>{searchcolumn.<@genAttributeByCol colName_ABC='${col.code}'/>==true}">
		<c:if test="<#noparse>$</#noparse>{${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>Mults!=null}" >
		$('select.<@genAttributeByCol colName_ABC='${col.code}'/>Mults').val([<c:forEach items="<#noparse>${</#noparse>${tab?uncap_first}.compareCols.<@genAttributeByCol colName_ABC='${col.code}'/>Mults}" var="tmpMults"><#noparse>${</#noparse>tmpMults},</c:forEach>]).trigger('change');
		</c:if></c:if>
		</#if>
		</#list>
			</c:otherwise>
		</c:choose>
		
	});
</script>