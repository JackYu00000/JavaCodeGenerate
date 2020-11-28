<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<#include "../../function.ftl">
					<div class="span12">
					<div class="block">
					<ul id="mytab" class="nav nav-tabs">
					    <li class="searchField"><a>条件检索</a></li>
					    <li class="searchKey"><a>模糊检索</a></li>
					</ul>
					</div>
					</div>
				
					<div class="span12 searchField" style="margin-left:0;">
					<div class="block">
					<div class="data-fluid">
					<form action="<c:url value='/mg/${tab?lower_case}'/>" method="post">
									<table cellpadding="0" cellspacing="0" width="100%" class="table table-hover">
									<tbody>
									<tr>
<#list cols as col><#if (col.inTableColumn)><#if ((col.primaryKey && col.inSelfTable) || !col.primaryKey)><#assign colInteger>${checkColTypeIsToJavaNotString(col.type)?string}</#assign>
<c:if test="<#noparse>$</#noparse>{searchcolumn.<@genAttributeByCol colName_ABC='${col.code}'/>==true}">	<td class="<@genAttributeByCol colName_ABC='${col.code}'/>"><#if (col.comment!?index_of("RADIO")>-1)><#assign tcomment="${col.comment!?replace('.*RADIO_','','r')}" />
                                  		<select tabindex="${col_index}" multiple  style="width:100px" name="<@genAttributeByCol colName_ABC='${col.code}'/><#if (colInteger=='true' && col.inTableColumn)>Mults</#if>" class="select <@genAttributeByCol colName_ABC='${col.code}'/><#if (col.type?upper_case=="INT" && col.inTableColumn)>Mults</#if>" data-placeholder="请选择<@genColTitle col />">
											<option value=""></option>
									<#list tcomment?split(",") as s>
                                  <#if (s?contains(":"))>
                  					<option class="${s?split(":")?first}" value="${s?split(":")?first}">${s?split(":")?last}</option>
                  				<#else>
                  					<option class="${s?trim}" value="${s?trim}">${s?trim}</option>
                  				</#if>
</#list>
								</select>
<#elseif (col.comment!?index_of("CHECKBOX")>-1)>
<#assign tcomment="${col.comment!?replace('.*CHECKBOX_','','r')}" />
                                  <#if (tcomment?split(",")?size<1)>
                                  <#list tcomment?split(",") as s>
                                  <#if (s?contains(":"))>
                                  <input tabindex="${col_index}" data-show="${s?split(":")?last}" type="checkbox" name="<@genAttributeByCol colName_ABC='${col.code}'/><#if (colInteger=='true' && col.inTableColumn)>Mults</#if>" class="<@genAttributeByCol colName_ABC='${col.code}'/> checkbox" value="${s?split(":")?first}"/>${s?split(":")?last}
                                  <#else>
                                  <input tabindex="${col_index}" data-show="${s?trim}" type="checkbox" name="<@genAttributeByCol colName_ABC='${col.code}'/><#if colInteger=='true'><#if (col.type?upper_case=="INT" && col.inTableColumn)>Mults</#if></#if>" class="<@genAttributeByCol colName_ABC='${col.code}'/> checkbox" value="${s?trim}"/>${s?trim}
                                  </#if>
</#list>
                                   <#else>
                                   <select tabindex="${col_index}" multiple style="width:100px" name="<@genAttributeByCol colName_ABC='${col.code}'/><#if (colInteger=='true' && col.inTableColumn)>Mults</#if>" class="select <@genAttributeByCol colName_ABC='${col.code}'/><#if (col.type?upper_case=="INT" && col.inTableColumn)>Mults</#if>" data-placeholder="请选择<@genColTitle col />">
									<option value=""></option>
									<#list tcomment?split(",") as s>
                                  <#if (s?contains(":"))>
	                                  					<option class="${s?split(":")?first}" value="${s?split(":")?first}">${s?split(":")?last}</option>
	                                  				<#else>
	                                  					<option class="${s?trim}" value="${s?trim}">${s?trim}</option>
	                                  				</#if>
</#list>
														</select>
                                  </#if>
<#elseif ((col.comment!?index_of("OPTION")>-1 || col.joinTables!?size>0) && col.inSelfTable)>
												<#if (col.joinTables!?size==0)>
<select tabindex="${col_index}" name="<@genAttributeByCol colName_ABC='${col.code}'/>" class="select <@genAttributeByCol colName_ABC='${col.code}'/>" data-placeholder="请选择<@genColTitle col />">
												<#assign tcomment="${col.comment!?replace('.*OPTION_','','r')}" />
	                                  			<#list tcomment?split(",") as s>
	                                  				<#if (s?contains(":"))>
	                                  					<option class="${s?split(":")?first}" value="${s?split(":")?first}">${s?split(":")?last}</option>
	                                  				<#else>
	                                  					<option class="${s?trim}" value="${s?trim}">${s?trim}</option>
	                                  				</#if>
												</#list>
												</select>
												<#else>
												<input tabindex="${col_index}" type="hidden" name="<@genAttributeByCol colName_ABC='${col.code}'/>" value="<#noparse>$</#noparse>{${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>}" 
													class="<@genAttributeByCol colName_ABC='${col.code}'/> inputselect"  data-placeholder="请选择<@genColTitle col />"  
													<#list col.joinTables! as coljt><#if (coljt_index>0)><%--</#if><#assign dataCascadeKeys><@getOptionColumn coljt.joinTable.cols /></#assign>
													data-ref-id="${coljt.joinTableKey}"<%-- 引用外部数据主键字段(本数据存储字段) --%> 
													data-ref-show="${coljt.joinTableShow?split(',')[0]}"<%-- 引用外部数据显示字段,<@getShowColumn coljt.joinTable.cols /> --%> 
													data-ref-search-cols="${coljt.joinTableShow?split(',')[0]}"<%-- 引用外部数据用以检索字段,<@getShowColumn coljt.joinTable.cols /> --%> 
													data-ref-url="<c:url value='/mg/${coljt.joinTableCode?lower_case}.json' />"<%-- 引用外部数据检索地址 --%>
													data-ref-type="${coljt.joinTableCode?uncap_first}"<%-- 初始外部数据json中类型名 --%> 
													data-ref-filter-url="/mg/${coljt.joinTableCode?lower_case}/<#noparse>$</#noparse>{${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>}.json"<%-- 初始外部数据地址 --%>
													data-cascade-keys="<@getOptionColumnFirst coljt.joinTable.cols />"<%-- 可用关联查询字段，${dataCascadeKeys} --%> 
													<#if (dataCascadeKeys?length>0)>
													data-cascade-val-from-class="<@getOptionColumnFirst cols />"<%-- 可用关联查询字段，取值dom的class，<@getOptionColumn cols />，顺序与data-cascade-keys对应 --%>
													<#else>
													data-cascade-val-from-class=""<%-- 可用关联查询字段，取值dom的class，<@getOptionColumn cols />，顺序与data-cascade-keys对应 --%>
													</#if>
													<#if (coljt_index>0)> --%></#if></#list>
													/>
												</#if>
<#elseif (col.type?upper_case=="DATE")>
<input tabindex="${col_index}"  prompt="请选择<@genColTitle col />" data-show="<fmt:formatDate value='<#noparse>${</#noparse>${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/><#noparse>}</#noparse>' pattern='yyyy-MM-dd' />" type="text" name="<@genAttributeByCol colName_ABC='${col.code}'/>" value="<fmt:formatDate value='<#noparse>${</#noparse>${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/><#noparse>}</#noparse>' pattern='yyyy-MM-dd' />" class="easyui-datebox input-medium validate[optional]"  style="width:174px;height:32px;"/>
<#elseif (col.type?upper_case=="DATETIME")>
<input tabindex="${col_index}"  prompt="请选择<@genColTitle col />" data-show="<fmt:formatDate value='<#noparse>${</#noparse>${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/><#noparse>}</#noparse>' type='both' />" type="text" name="<@genAttributeByCol colName_ABC='${col.code}'/>" value="<fmt:formatDate value='<#noparse>${</#noparse>${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/><#noparse>}</#noparse>' type='both' />" class="easyui-datebox input-medium validate[optional]"  style="width:174px;height:32px;"/>
<#elseif (col.type?upper_case=="TIME")>
<input tabindex="${col_index}"  prompt="请选择<@genColTitle col />" data-show="<fmt:formatDate value='<#noparse>${</#noparse>${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/><#noparse>}</#noparse>' type='time' />" type="text" name="<@genAttributeByCol colName_ABC='${col.code}'/>" value="<fmt:formatDate value='<#noparse>${</#noparse>${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/><#noparse>}</#noparse>' type='time' />" class="easyui-timespinner input-medium validate[optional]"  style="width:174px;height:32px;"/>
<#elseif (col.type?upper_case?contains("TEXT"))>
<textarea tabindex="${col_index}" name="<@genAttributeByCol colName_ABC='${col.code}'/>" class="txt<@genAttributeByCol colName_ABC='${col.code}'/>"><#noparse>$</#noparse>{${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>}</textarea>
<#else>
<div class="input-medium input-append">
<input tabindex="${col_index}" type="text" name="<@genAttributeByCol colName_ABC='${col.code}'/>" class="validate[optional]" value="<#noparse>$</#noparse>{${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>}"  placeholder="<@genColTitle col />"/>
<button class="btn btn-warning cleaninput" type="button">X</button>
                                        </div>
</#if></td> </c:if>
    </#if></#if></#list>
											<td><button type="submit" class="btn btn-info">检索</button></td>
										</tr>
									</tbody>
								</table>
							</form>
					</div>
					</div>
					</div>
					<div class="span12 searchKey" style="margin-left:0;">
					<div class="block">
					<div class="data-fluid">
					<form action="<c:url value='/mg/${tab?lower_case}'/>" method="post">
									<table cellpadding="0" cellspacing="0" width="100%" class="table table-hover">
									<thead>
										<tr>
											<th>关键字</th>
											<th>搜索范围</th>
    										<th></th>
										</tr>
									</thead>
									<tbody>
									<tr>
										<td class="searchKey span4"><div class="input-append">
<input tabindex="0" type="text" name="searchKey" class="input-medium searchKey" value="<#noparse>$</#noparse>{${tab?uncap_first}.searchKey}"  placeholder="关键字"/>
<button class="btn btn-warning cleaninput" type="button">X</button>
                                        </div>
</td> 
										<td class="searchFields span8">
										<#list cols as col>
											<#if (!col.primaryKey && col.inTableColumn)>
<c:if test="<#noparse>$</#noparse>{searchcolumn.<@genAttributeByCol colName_ABC='${col.code}'/>==true}">	<input tabindex="${col_index}" type="checkbox" name="searchCols" class="<@genAttributeByCol colName_ABC='${col.code}'/> checkbox searchCol" value="<@genAttributeByCol colName_ABC='${col.code}'/>"/><@genColTitle col /></c:if>
											</#if>
    										</#list>
</td> 
											<td><button type="submit" class="btn btn-info">检索</button></td>
										</tr>
									</tbody>
								</table>
							</form>
					</div>
					</div>
					</div>
					

