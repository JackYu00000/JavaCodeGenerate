<#include "../function.ftl">
<#assign hasoption>${checkColHasOption(cols)?string}</#assign>
<#assign hasradio>${getColHasRadio(cols)?string}</#assign>
<#assign hascheckbox>${getColHasCheckbox(cols)?string}</#assign>
<#assign hasdate>${getColHasDate(cols)?string}</#assign>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/pages/public/include.jsp"%>
<!DOCTYPE html>
<head>
<title>添加${name}</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<%@ include file="/pages/public/script.jsp"%>
<%@ include file="/pages/public/css.jsp"%>
<script type='text/javascript' src="<c:url value='/resources/js/plugins/validationEngine/languages/jquery.validationEngine-Normal_zh.js' />"></script>
<script type='text/javascript' src="<c:url value='/resources/js/plugins/validationEngine/jquery.validationEngine.js' />"></script>
</head>
<body>
			<div class="content">
				<div class="page-header">
					<div class="icon">
						<span class="ico-pen-2"></span>
					</div>
					<h1>
						${name} 
					</h1>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<div class="block">
							<div class="head">
								<h2>添${name}信息</h2>
								<ul class="buttons">
									<li></li>
								</ul>
							</div>
							<form id="validate" action="<c:url value='/mg/${tab?lower_case}/save'/>" method="post"
								class="savefrm">
								<#assign textclasses="" />
								<#list cols as col>
        <#if !col.primaryKey  && col.inSelfTable>
									<div class="row-form ">
										<div class="span3"><@genColTitle col /><#if (col.mandatory=='1')><span class="required" style="float: right;color: red;">(* 必填)</span></#if></div>
										<div class="span9">
											<#if (col.comment!?index_of("RADIO")>-1)>
	                                  			<#assign tcomment="${col.comment!?replace('.*RADIO_','','r')}" />
	                                  			<#list tcomment?split(",") as s>
	                                  				<#if col.type?lower_case?contains("boolean")>
	                                  					<input tabindex="${col_index}" type="radio" name="<@genAttributeByCol colName_ABC='${col.code}'/>" class="<@genAttributeByCol colName_ABC='${col.code}'/> radio<#if (col.mandatory=='1')> validate[required]</#if>" value="<#if s?contains('有')||s?contains('是')>true<#else>false</#if>"/>${s?trim}
	                                  				<#else>
	                                  					<#if (s?contains(":"))>
		                                  					<input tabindex="${col_index}" type="radio" name="<@genAttributeByCol colName_ABC='${col.code}'/>" class="<@genAttributeByCol colName_ABC='${col.code}'/> radio<#if (col.mandatory=='1')> validate[required]</#if>" value="${s?split(":")?first}"/>${s?split(":")?last}
		                                  				<#else>
		                                  					<input tabindex="${col_index}" type="radio" name="<@genAttributeByCol colName_ABC='${col.code}'/>" class="<@genAttributeByCol colName_ABC='${col.code}'/> radio<#if (col.mandatory=='1')> validate[required]</#if>" value="${s?trim}"/>${s?trim}
		                                  				</#if> 
	                                  				</#if>
												</#list>
											<#elseif (col.comment!?index_of("CHECKBOX")>-1)>
												<#assign tcomment="${col.comment!?replace('.*CHECKBOX_','','r')}" />
	                                  			<#list tcomment?split(",") as s>
	                                  				<#if (s?contains(":"))>
	                                  					<input tabindex="${col_index}" type="checkbox" name="<@genAttributeByCol colName_ABC='${col.code}'/>" class="<@genAttributeByCol colName_ABC='${col.code}'/> checkbox<#if (col.mandatory=='1')> validate[required]</#if>" value="${s?split(":")?first}"/>${s?split(":")?last}
	                                  				<#else>
	                                  					<input tabindex="${col_index}" type="checkbox" name="<@genAttributeByCol colName_ABC='${col.code}'/>" class="<@genAttributeByCol colName_ABC='${col.code}'/> checkbox<#if (col.mandatory=='1')> validate[required]</#if>" value="${s?trim}"/>${s?trim}
	                                  				</#if>
												</#list>
											<#elseif (col.comment!?index_of("OPTION")>-1 || col.joinTables!?size>0)>
												<#if (col.joinTables!?size==0)>
												<select tabindex="${col_index}" name="<@genAttributeByCol colName_ABC='${col.code}'/>" class="select <@genAttributeByCol colName_ABC='${col.code}'/><#if (col.mandatory=='1')> validate[required]</#if>" data-placeholder="请选择<@genColTitle col />">
												<#assign tcomment="${col.comment!?replace('.*OPTION_','','r')}" />
	                                  			<#list tcomment?split(",") as s>
	                                  				<#if (s?contains(":"))>
	                                  					<option value="${s?split(":")?first}">${s?split(":")?last}</option>
	                                  				<#else>
	                                  					<option value="${s?trim}">${s?trim}</option>
	                                  				</#if>
												</#list>
												</select>
												<#else>
												<input tabindex="${col_index}" type="hidden" name="<@genAttributeByCol colName_ABC='${col.code}'/>" class="<@genAttributeByCol colName_ABC='${col.code}'/> inputselect <#if (col.mandatory=='1')> validate[required]</#if>"  data-placeholder="请选择<@genColTitle col />" 
													<#list col.joinTables! as coljt><#if (coljt_index>0)><%--</#if><#assign dataCascadeKeys><@getOptionColumn coljt.joinTable.cols /></#assign>
													data-ref-id="${coljt.joinTableKey}"<%-- 引用外部数据主键字段(本数据存储字段) --%> 
													data-ref-show="${coljt.joinTableShow?split(',')[0]}"<%-- 引用外部数据显示字段，<@getShowColumn coljt.joinTable.cols /> --%> 
													data-ref-search-cols="${coljt.joinTableShow?split(',')[0]}"<%-- 引用外部数据用以检索字段，<@getShowColumn coljt.joinTable.cols /> --%> 
													data-ref-url="<c:url value='/mg/${coljt.joinTableCode?lower_case}.json' />"<%-- 引用外部数据检索地址 --%>
													data-cascade-keys="<@getOptionColumnFirst coljt.joinTable.cols />"<%-- 可用关联查询字段，${dataCascadeKeys} --%> 
													<#if (dataCascadeKeys?length>0)>
													data-cascade-val-from-class="<@getOptionColumnFirst cols />"<%-- 可用关联查询字段，取值dom的class，<@getOptionColumn cols />，顺序与data-cascade-keys对应 --%>/>
													<#else>
													data-cascade-val-from-class=""<%-- 可用关联查询字段，取值dom的class，<@getOptionColumn cols />，顺序与data-cascade-keys对应 --%>/>
													</#if>
													<#if (coljt_index>0)> --%></#if></#list>
												</#if>
											<#elseif (col.type?upper_case=="DATE")>	
												<input tabindex="${col_index}" prompt="请选择<@genColTitle col />" type="text" name="<@genAttributeByCol colName_ABC='${col.code}'/>" class="easyui-datebox input-medium<#if (col.mandatory=='1')> validate[required]</#if>"  style="width:174px;height:32px;"/>
											<#elseif (col.type?upper_case=="DATETIME")>	
												<input tabindex="${col_index}" prompt="请选择<@genColTitle col />" type="text" name="<@genAttributeByCol colName_ABC='${col.code}'/>" class="easyui-datetimebox input-medium<#if (col.mandatory=='1')> validate[required]</#if>"  style="width:174px;height:32px;"/>
											<#elseif (col.type?upper_case=="TIME")>	
												<input tabindex="${col_index}" prompt="请选择<@genColTitle col />" type="text" name="<@genAttributeByCol colName_ABC='${col.code}'/>" class="easyui-timespinner input-medium<#if (col.mandatory=='1')> validate[required]</#if>"  style="width:174px;height:32px;"/>
											<#elseif (col.type?upper_case?contains("TEXT"))>
												<#assign hastext=true />
												<#assign textclasses>${textclasses},txt<@genAttributeByCol colName_ABC='${col.code}'/></#assign>
												<textarea tabindex="${col_index}" name="<@genAttributeByCol colName_ABC='${col.code}'/>" class="txt<@genAttributeByCol colName_ABC='${col.code}'/>"><#noparse>$</#noparse>{${tab?uncap_first}.<@genAttributeByCol colName_ABC='${col.code}'/>}</textarea>
											<#else><#assign colInteger>${checkColTypeIsToJavaNotString(col.type)?string}</#assign>
											<input tabindex="${col_index}" <#if (col.type?upper_case?contains('VARCHAR'))>maxlength="${col.type?replace('\\D','','r')}"</#if> type="text" name="<@genAttributeByCol colName_ABC='${col.code}'/>" class="input-medium validate[<#if (col.mandatory=='1')>required<#else>option</#if><#if (col.type?upper_case?contains('VARCHAR'))>,maxSize[${col.type?replace('\\D','','r')}]</#if><#if colInteger=='true'>,onlyNumber</#if>]" placeholder="<@genColTitle col />"/>
											</#if> 
										</div>
									</div>
        </#if>
        </#list>
								<div class="row-form">
									<div class="span2 offset5">
										<a href="javascript:void(0);" class="btn btn-primary" onclick='javascript:saveInfo(this);'>确定</a>
										<a href="<c:url value='/mg/${tab?lower_case}'/>" class="btn">返回</a>
									</div>
								</div>
							</form>
							<#if (textclasses?trim?length>0)>
							<form class="picfrm" method="post" action="http://up.qiniu.com/" enctype="multipart/form-data">
	                             <input type="file" name="file" id="trackfile" class="picfile"  style="display:none;"/>
	                             <input name="token" type="hidden" value="<#noparse>$</#noparse>{imgtoken}" />
							</form> 
							</#if>
						</div>
					</div>
				</div>
			</div>

<#if (hasradio=='true' || hascheckbox=='true' || hasdate=='true')>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/easyui/easyui.css" />">
<script type="text/javascript" src="<c:url value="/resources/js/jquery.easyui.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/easyui-lang-zh_CN.js" />"></script>
<script type='text/javascript' src="<c:url value='/resources/js/plugins/uniform/jquery.uniform.min.js' />"></script>
</#if>	
<#if (hasoption=='true')>
<script type='text/javascript' src="<c:url value='/resources/js/plugins/select/select2.min.js' />"></script>
<script type='text/javascript' src="<c:url value='/resources/js/plugins/select/select2_locale_zh-CN.js' />"></script>
<link href="<c:url value='/resources/css/select/select2.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/resources/css/select/select2-bootstrap.css' />" rel="stylesheet" type="text/css" />
</#if>	
<#if (textclasses?trim?length>0)>
<link href="<c:url value='/resources/css/jquery.cleditor.css' />" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value='/resources/js/plugins/cleditor/jquery.cleditor.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/js/plugins/cleditor/jquery.cleditor.uploadimg.js' />"></script>
<script src="<c:url value='/resources/js/jquery.form.js' />"></script>
</#if>
<script type="text/javascript">
	$(document).ready(function(){
		if ($("#validate").length > 0)
			$("#validate, #validate_custom").validationEngine('attach', {
				promptPosition : "inline",
				showOneMessage : true,
				ajaxFormValidationMethod : "post"
		});
		<#if (textclasses?trim?length>0)>
		$('.picfile').bind('change',function(){
        	$('div#loader').modal();
        	  var options = {
        	       success: function(data) {
        	         var obj = eval(data);
        	         console.log(obj.key);
        	         
        	         var editor = edt.editor;
        		      var html = "<img src=\"" + docimgurlpre + obj.key + "?imageView2/2/w/400\">";
        		      if (html)
        		        editor.execCommand(edt.command, html, null, edt.button);
        		      if($(':hidden.xgeoimages').val()!='')
	        	        	 $(':hidden.xgeoimages').val($(':hidden.xgeoimages').val() + ",");
	        	      $(':hidden.xgeoimages').val($(':hidden.xgeoimages').val() + obj.key);
        		      $('div#loader').modal('hide');
        	        },
        	        error:function(){
        	        	$('div#loader').modal('hide');
        	        }
        	     };
        	  $(".picfrm").ajaxForm(options); 
        	  $(".picfrm").submit();
        });
		</#if>

		<#list textclasses?split(",") as s>
		<#if (s?trim?length>0)>
		$(".${s}").cleditor({width:"100%", height:"600px"})[0].focus();
		</#if>
		</#list>
		
		
		<#if (hasradio=='true' || hascheckbox=='true')>
		$("input:checkbox, input:radio").not('input.ibtn').uniform();
		</#if>	
	});
	
	<#if (textclasses?trim?length>0)>
	function writeUpload(data){
		edt = data;
		$('.picfile').click();
	}
	</#if>
	function saveInfo(thz){ 
		$(thz).parents('form').submit(); 
	}
	function addCustomerRules(){
	/*
		$.validationEngineLanguage.allRules["uniqueuserinfo"] = {
					"url" : "<c:url value='/aj/uinfo' />",
					"extraData":"uid=$('.tuid').val()", 
					"alertTextOk" : "* 不重复可以使用",
					"alertTextLoad" : "* 正在进行检索是否重复查询，请稍候",
					"alertText" : "* 重复"
				};
	*/
	}
</script>
</body>
</html>
