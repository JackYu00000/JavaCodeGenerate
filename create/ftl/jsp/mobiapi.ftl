<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/pages/public/include.jsp"%>
<% String url = request.getServerName(); %>
<!DOCTYPE html>
<head>
<title>移动端接口使用测试</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<%@ include file="/pages/public/script.jsp"%>
<%@ include file="/pages/public/css.jsp"%>
<script src="<c:url value='/resources/js/jquery.md5.js' />"></script>
<script src="<c:url value='/resources/js/jquery.form.js' />"></script>
<script type='text/javascript' src="<c:url value='/resources/js/plugins/select/select2.min.js' />"></script>
<link href="<c:url value='/resources/css/select/select2.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/resources/css/select/select2-bootstrap.css' />" rel="stylesheet" type="text/css" />

<style type="text/css">
ul{list-style:none;}
h1 small {
  font-size: 24.5px;
}
.savelist li:after{display:block; content:''; clear:both;}
.savelist li span{float:left;}
.savelist li a{float:right;}
.hidelist li a{float:right;}
</style>

<link href="<c:url value='/resources/css/databasedocs.css' />"
	rel="stylesheet" type="text/css" />
<script type='text/javascript'
	src="<c:url value='/resources/js/bootstrap-scrollspy.js' />"></script>
<script type='text/javascript'
	src="<c:url value='/resources/js/bootstrap-affix.js' />"></script>
<script type="text/javascript">
	var locStaPre = "mobi_user_save:";
	var locHidePre = "mobi_id_user_hide:";
	var navJson = {${navjson}};
	
	$(document).ready(function() {
		$('span.authory_key').text("当前登录authory_key:" + localStorage["author_key"]);
	    $('optgroup').each(function(){
			$(this).attr('label',navJson[$(this).attr('class')]);
		});
		
		$('ul.nav-list li a:gt(0)').each(function(){
			if(undefined!=navJson[$(this).attr('href').replace('#','')]){
				$(this).html($(this).html().replace($(this).text(),navJson[$(this).attr('href').replace('#','')]));
			}
		});
		
		$('select.jsonpath').bind('change',function(){
			$('a[href="#' + $(this).val().split("/")[2] + '"]').parents('li').addClass('active');
			$('a[href^="#"]').not($('a[href="#' + $(this).val().split("/")[2] + '"]')).parents('li').removeClass('active');
			if($(this).val().endsWith("}")){
				$('input.urlparam').show();
			}else{
				$('input.urlparam').hide();
			}
			var dataJson;
			if(undefined==localStorage[$(this).val()]){
				if(undefined==localStorage[locStaPre + $(this).val()])
					dataJson = JSON.parse('{\n \"data\": {\n \n }\n}');
				else
					dataJson = JSON.parse(localStorage[locStaPre + $(this).val()]);
			}else
				dataJson = JSON.parse(localStorage[$(this).val()]);
			$('.submitjson').val(JSON.stringify(dataJson,null,4));
		});
		
		$('a[href^="#"]').bind('click',function(){
			if($(this).attr("href")=="#sign"){
				$('select.jsonpath').val("/apimobi/sign").trigger("change");
			}else{
				$('select.jsonpath').val("/apimobi/" + $(this).attr("href").replace("#","") + "/list").trigger("change");
			}
		});
		
		$('.jsonbutton').bind('click',function(){
			$('.responsejson').val("");
			var valSel = $('select.jsonpath').val();
			if(valSel.endsWith("}")){
				valSel = valSel.replace(/{\w*}/,$("input.urlparam").val());
			}
			$.ajax({
			    contentType: 'application/json',
			    data:$('.submitjson').val(),
			    dataType: 'json',
			    success: function(data, status, xhr){
			    	$('.responsejson').val(JSON.stringify(data,null,4));
			    	if($('select.jsonpath').val()=='/apimobi/sign'){
			    		localStorage["author_key"] = xhr.getResponseHeader("author_key");
			    		$('span.authory_key').text("当前登录authory_key:" + localStorage["author_key"]);
			    	}
			    },
			    error: function(data){
			        $('.responsejson').val(JSON.stringify(JSON.parse(data.responseText),null,4));
			    },
			    processData: false,
			    type: 'POST',
			    url: valSel,
			    beforeSend: function(request) {
                  request.setRequestHeader("request-type", "ios");
                  request.setRequestHeader("autory_key", localStorage["author_key"]);
                }
			});
		});
		
		$('.signout').bind('click',function(){
     	    localStorage["logineduserinfo"] = localStorage["unlogineduserinfo"];
			var frm = document.createElement('form');
			frm.method="post";
			frm.action="<c:url value='/apimobi/signout' />";
			var options = {
     	       success: function(data) {
     	       		localStorage["logineduserinfo"] = "";
     	        },
     	        error:function(){
     	        	localStorage["logineduserinfo"] = "";
     	        }
		    };
		    $(frm).ajaxForm(options); 
		    $(frm).submit();
		});
	});
</script>
<script src="<c:url value='/resources/js/managerdoc/customshow.js' />"></script>
</head>
<body data-spy="scroll" data-target=".bs-docs-sidebar">
	<div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <a class="brand" href="#" style="color:#fff">移动端接口使用测试</a>
          <div class="nav-collapse collapse">
          	<ul class="nav">
              <li class="">
              </li>
            </ul>
          </div>
      </div>
      </div>
    </div>
	<div class="container">
		<div class="row">
			<div class="span2">
				<ul class="nav nav-list bs-docs-sidenav">
					<li><a href="#sign"><i class="icon-chevron-right"></i> 登陆</a></li>
					<li><a href="#" class="remove_all"><i class="icon-th-list tipl" title="下载PostMan说明" data-url="<c:url value='/mg/dbapi/down?exp=mobipostman&file=mobiapi_postman_' /><%=url%>.json.postman_collection"></i><i class="icon-download-alt tipr" title="下载Excel说明" data-url="<c:url value='/mg/dbapi/down?exp=mobiapi&file=mobiapi_readme_' /><%=url%>.xls"></i>全部隐藏</a></li>
					<!-- 导航条内容 -->
					<c:set var="jurlbeginlast" value="" />
					<c:forEach items="<#noparse>$</#noparse>{pathurls}" var="r" varStatus="vs">
	    			<c:set var="jurl" value="<#noparse>${fn:</#noparse>replace(r.patternsCondition,'[','')}"/>
	   				<c:set var="jurl" value="<#noparse>${fn:</#noparse>replace(jurl,']','')}"/>
	   				<c:set var="jurlbegin" value="<#noparse>${fn:</#noparse>split(jurl,'/')[1]}" />
	   	 			<c:if test="<#noparse>${fn:</#noparse>startsWith(jurl,'/apimobi/')}">
	   				<c:if test="<#noparse>$</#noparse>{jurlbeginlast!=jurlbegin}">
	   				<c:if test="<#noparse>$</#noparse>{jurlbeginlast!='' && jurlbeginlast!=null}">
	   				</c:if>
	   				<li><a href="#<#noparse>$</#noparse>{jurlbegin}"><i class="icon-remove"></i><i class="icon-chevron-right"></i> <#noparse>$</#noparse>{jurlbegin}</a></li>
	   				<c:set var="jurlbeginlast" value="<#noparse>$</#noparse>{jurlbegin}" />
	   				</c:if>
					</c:if>
					</c:forEach>

				</ul>
			</div>
			<div class="span8">
				<section>
					<h4>数据测试地址</h4><h5><span class="authory_key"></span></h5>
					<select class="select jsonpath" style="width:100%;">
					<optgroup label="登陆" class="sign">
	   	 			<option value="/apimobi/sign" class="sign">/apimobi/sign</option>
					<c:set var="jurlbeginlast" value="" />
					<c:forEach items="<#noparse>$</#noparse>{pathurls}" var="r" varStatus="vs">
	    			<c:set var="jurl" value="<#noparse>$</#noparse>{fn:replace(r.patternsCondition,'[','')}"/>
	   				<c:set var="jurl" value="<#noparse>$</#noparse>{fn:replace(jurl,']','')}"/>
	   				<c:set var="jurlarray" value="<#noparse>$</#noparse>{fn:split(jurl,'/')}" />
	   				<c:set var="jurlbegin" value="<#noparse>$</#noparse>{jurlarray[1]}" />
	   	 			<c:if test="<#noparse>$</#noparse>{fn:startsWith(jurl,'/apimobi/') && fn:length(jurlarray)==3 && jurlarray[2]!='sign'}">
	   				<c:if test="<#noparse>$</#noparse>{jurlbeginlast!=jurlbegin}">
	   				<c:if test="<#noparse>$</#noparse>{jurlbeginlast!='' && jurlbeginlast!=null}">
	   				</optgroup>
	   				</c:if>
	   				<optgroup label="<#noparse>$</#noparse>{jurlbegin}" class="<#noparse>$</#noparse>{jurlbegin}">
	   				<c:set var="jurlbeginlast" value="<#noparse>$</#noparse>{jurlbegin}" />
	   				</c:if>
	   	 			<option value="<#noparse>$</#noparse>{jurl}" class="jurlpath"><#noparse>$</#noparse>{jurl}</option>
					</c:if>
					</c:forEach>
					</select>
					<input type="text" class="urlparam" placeholder="填写url参数" style="display:none;" />
					<h4>提交数据<small>header 中加入参数：request-type，值：android、ios；参数：author_key，值：校验码；Content-Type，值：application/json</small></h4>
					<textarea class="submitjson" style="height:200px;"></textarea>
					<button class="jsonbutton" type="button" class="btn btn-primary" data-loading-text="处理中...">提交</button>
					<button class="savebutton" type="button" class="btn btn-primary" data-loading-text="处理中...">保存</button>
					
					<h4>返回数据</h4>
					<textarea class="responsejson" style="height:350px;"></textarea>
				</section>
			</div>
			<div class="span2">
				<section>
				<h5>保存记录</h5>
				<ul class="savelist">
				</ul>
				</section>
				<section>
				<h5>隐藏记录</h5>
				<ul class="hidelist">
				</ul>
				</section>
			</div>
		</div>
	</div>

	<!-- Footer ================================================== -->
	<footer class="footer">
		<div class="container">
			<p><a href="<c:url value='/mg/dbapi' />">数据库使用说明api</a>&nbsp;&nbsp;<a href="<c:url value='/mg/dbapi/json' />">JSON接口使用测试</a>&nbsp;&nbsp;移动端接口使用测试</p>
			<p>By flysail</p>
			<ul class="footer-links">
				<li><a href="mailto:2591181910@qq.com?subject=移动端接口问题反馈">问题反馈</a></li>
				<li class="muted">&middot;</li>
				<li>版本更新时间:${createDate}</li>
			</ul>
		</div>
	</footer>
</body>
<script type="text/javascript">
	localStorage.setItem("/apimobi/sign", JSON.stringify({data:{
  "id": null,
  "sec": null
}}));
	localStorage.setItem("unlogineduserinfo", JSON.stringify({
  "uid": null,
  "token": null
}));
	localStorage.setItem("logineduserinfo", JSON.stringify({
  "uid": null,
  "token": null
}));
</script>
<script type="text/javascript" src="/pages/docs/mobiapi_localStorage.js"></script>
</html>
