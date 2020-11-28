<#include "../../function.ftl">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/pages/public/include.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="wtg" %>
<%@ include file="${tab?lower_case}_list_header.jsp"%>
<!DOCTYPE html>
<head>
<title>${name}列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<%@ include file="/pages/public/script.jsp"%>
<%@ include file="/pages/public/css.jsp"%>
<script type="text/javascript">
var delurl = "<c:url value='/mg/${tab?lower_case}/del'/>";
</script>
<script type='text/javascript' src="<c:url value='/resources/js/jquery.form.js' />"></script>
</head>
<body>
			<div class="content">
				<div class="page-header">
					<h1>
						${name}&nbsp;
					</h1>
				</div>
				
				<div class="row-fluid list_search_row_fluid">
				<%@ include file="${tab?lower_case}_list_search.jsp"%>
				</div>
				
				<div class="row-fluid">
				<div class="span12" style="margin-left:0;">
						<div class="block">
							<div class="head purple">
								<h2></h2>
								<ul class="buttons">
								<%-- <li><a href="" class="table-header-list-btn btn btn-info" data-form-url="<c:url value='/mg/${tab?lower_case}/del'/>" data-form-confirm-text="是否确认删除该信息？删除后将无法恢复！" data-form-data-class="list-form-data" data-form-cascade-name="" data-form-cascade-value="">删除</a></li>--%>
								<%-- <li><a href="" class="table-header-list-btn btn btn-info" data-form-url="<c:url value='/mg/${tab?lower_case}/save'/>" data-form-confirm-text="是否确认？" data-form-data-class="list-form-data" data-form-cascade-name="isReader" data-form-cascade-value="2">删除</a></li>--%>
								</ul>
							</div>
							<div class="data-fluid list-form-data">
								<table cellpadding="0" cellspacing="0" width="100%" class="table table-hover">
									<thead><#assign colShowSpan=0 />
										<tr>
											<th><input type="checkbox" class="slt_all checkbox" /></th>
											<#list cols as col>
											<#if (col_index>0 && col.inSelfTable)><#assign colShowSpan=colShowSpan+1 />
<c:if test="<#noparse>$</#noparse>{showcolumn.<@genAttributeByCol colName_ABC='${col.code}'/>==true}">	<th><span class="<@genAttributeByCol colName_ABC='${col.code}'/> <#if !col.inSelfTable>muted</#if>"><@genColTitle col /></span></th></c:if>
											</#if>
    										</#list>
    										<th>操作</th>
										</tr>
									</thead>
									<tfoot>
										<tr>
											<td colspan="${colShowSpan+2}">
												<div class="bulk-actions align-left">
													<#assign colShow>${showWithDialog(cols)?string}</#assign><#if (colShow=='true')>
													<a class="btn dialog_upd" href="javascript:void(0)" data-url="<c:url value='/mg/${tab?lower_case}/new'/>/dialog">添加新${name}</a>
													<#else>
													<a class="btn" href="<c:url value='/mg/${tab?lower_case}/new'/>">添加新${name}</a>
													</#if> 
												</div>
												<%@ include file="/pages/public/page_with_search.jsp"%>
												 <!-- End .pagination -->
												<div class="clear"></div>
											</td>
										</tr>
									</tfoot>
									<tbody>
									<form id="listsave" action="<c:url value='/mg/${tab?lower_case}/save'/>" method="post" class="savefrm">
									<c:forEach items="<#noparse>$</#noparse>{page.data}" var="${tab?uncap_first}" varStatus="vs">
									<tr>
										<#list cols as col>
											<#if (col_index=0)><#assign colInteger>${checkColTypeIsToJavaNotString(col.type)?string}</#assign>
											<td><input type="checkbox" class="slt checkbox" data-eid="<#noparse>$</#noparse>{${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>}" value="<#noparse>$</#noparse>{${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>}" name="<@genAttributeByCol colName_ABC='${col.code}'/><#if colInteger=='true'>s</#if>" form="listsave"/></td>
											</#if>
										</#list>
											<#list cols as col>
											<#if (col_index>0 && col.inSelfTable)>
<c:if test="<#noparse>$</#noparse>{showcolumn.<@genAttributeByCol colName_ABC='${col.code}'/>==true}">	<td class="<@genAttributeByCol colName_ABC='${col.code}'/>">
											<#if col_index=1>
												<#if (colShow=='true')>
												<a href="javascript:void(0)" data-url="<c:url value='/mg/${tab?lower_case}/<#noparse>$</#noparse>{${tab?uncap_first}.<@getTableKey cols />}'/>/dialog" class="dialog_show"><@genEntityShowJspJstlTag tab col /></a>&nbsp;
												<#else>
												<a href="<c:url value='/mg/${tab?lower_case}/<#noparse>$</#noparse>{${tab?uncap_first}.<@getTableKey cols />}'/>" data-url="<c:url value='/mg/${tab?lower_case}/<#noparse>$</#noparse>{${tab?uncap_first}.<@getTableKey cols />}'/>/dialog" class="blank_show"><@genEntityShowJspJstlTag tab col /></a>&nbsp;
												</#if>
											<#elseif (col_index>1)>	
												<@genEntityShowJspJstlTag tab col />
											</#if>
											</td></c:if>
											</#if>
    </#list>
											<td>
												<#assign colShow>${showWithDialog(cols)?string}</#assign><#if (colShow=='true')>
												<a class="dialog_upd" href="javascript:void(0)" data-url="<c:url value='/mg/${tab?lower_case}/<#noparse>$</#noparse>{${tab?uncap_first}.<@getTableKey cols />}'/>/modify/dialog">修改</a>
												<#else>
												<a href="<c:url value='/mg/${tab?lower_case}/<#noparse>$</#noparse>{${tab?uncap_first}.<@getTableKey cols />}'/>/modify">修改</a>
												</#if> 
												<a href="#" class="listdelbtn" data-name="<@getTableKey cols />" data-ref="<#noparse>$</#noparse>{${tab?uncap_first}.<@getTableKey cols />}">删除</a>
											</td>
										</tr>
									</c:forEach>
									</form>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
<%@ include file="${tab?lower_case}_list_page_js.jsp"%>
<script type='text/javascript' src="<c:url value='/resources/js/manager_list.js' />"></script>
</body>
</html>
