<!DOCTYPE html>
<head>
<title>接口数据字段说明</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<!--[if lte IE 7]>
        <script type='text/javascript' src="/resources/js/plugins/other/lte-ie7.js"></script>
    <![endif]-->
<script type='text/javascript' src="/resources/js/plugins/jquery/jquery-1.9.1.min.js"></script>
<script type='text/javascript' src="/resources/js/plugins/jquery/jquery-migrate-1.1.1.min.js"></script>
<script type='text/javascript' src="/resources/js/plugins/jquery/jquery-ui-1.10.1.custom.min.js"></script>
<script type='text/javascript' src="/resources/js/plugins/bootstrap/bootstrap.min.js"></script>
<script type='text/javascript' src="/resources/js/plugins/cookies/jquery.cookies.2.2.0.min.js"></script>
<script type='text/javascript' src="/resources/js/actions.js"></script>
<!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />        
    <![endif]-->
<link href="/resources/css/stylesheets.css" rel="stylesheet" type="text/css" />
<!--[if lt IE 10]>
        <link href="/resources/css/ie.css" rel="stylesheet" type="text/css" />
    <![endif]-->

<link rel="shortcut icon" href="/resources/img/favicon.ico" />

<style>
span.edit_cancel_ico, span.edit_ico, span.edit_sure_ico{
	margin-left:2px;
	margin-right:2px;
	margin-top:1px;
}
span.edit_ico{
	color:blue;
}
span.edit_cancel_ico{
	color:red;
}
span.edit_sure_ico{
	color:green;
}
</style>
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

<link href="/resources/css/databasedocs.css"
	rel="stylesheet" type="text/css" />
<script type='text/javascript'
	src="/resources/js/bootstrap-scrollspy.js"></script>
<script type='text/javascript'
	src="/resources/js/bootstrap-affix.js"></script>
<script type="text/javascript">
	var locStaPre = "dbapi_user_save:";
	var locHidePre = "dbapi_id_user_hide:";
	
	if(window.localStorage){
	 	console.log('This browser supports localStorage');
	}else{
	 	console.log('This browser does NOT support localStorage');
	}
	
	var $window = $(window);

	// side bar
	setTimeout(function() {
		$('.bs-docs-sidenav').affix({
			offset : {
				top : function() {
					return $window.width() <= 980 ? 290 : 210
				},
				bottom : 270
			}
		})
	}, 100);
	
	$('[data-spy="scroll"]').each(function () {
      var $spy = $(this)
      $spy.scrollspy($spy.data())
    });
</script>
<script src="/resources/js/managerdoc/customshow.js"></script>
</head>
<body data-spy="scroll" data-target=".bs-docs-sidebar">
	<div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <a class="brand" href="/pages/docs/databaseapi_dict.html" style="color:#fff">字典数据字段说明</a>
          <a class="brand" href="/pages/docs/databaseapi_subset.html" style="color:#fff">子集记录数据字段说明</a>
          <a class="brand" href="/pages/docs/databaseapi_view.html" style="color:#fff">视图字段说明</a>
          <a class="brand" href="/pages/docs/databaseapi.html" style="color:#fff">接口数据字段说明</a>
      </div>
      </div>
    </div>
	<div class="container">
		<div class="row">
			<div class="span2 bs-docs-sidebar">
				<ul class="nav nav-list bs-docs-sidenav affix-top">
					<li><a href="#" class="remove_all"><i class="icon-download-alt tipl" title="下载Excel说明" data-url="/mg/dbapi/down?exp=dbapi&file=database_readme.xls"></i>全部隐藏</a></li>
					<!-- 导航条内容 -->
					${nav}
					<li><a href="/mg/dbapi/mobi" target="_blank"><i class="icon-chevron-right"></i><span style="color:red">移动接口使用测试</span></a></li>
					<li><a href="/mg/dbapi/json" target="_blank"><i class="icon-chevron-right"></i><span style="color:red">JSON接口使用测试</span></a></li>
				</ul>
			</div>
			<div class="span8">
				<!-- 正文内容 -->
				${content}

			</div>
			<div class="span2">
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
			<p>数据库使用说明api.&nbsp;&nbsp;<a href="/mg/dbapi/json">JSON接口使用测试</a>&nbsp;&nbsp;<a href="/mg/dbapi/mobi">移动端接口使用测试</a></p>
			<p>By flysail</p>
			<ul class="footer-links">
				<li><a href="mailto:2591181910@qq.com?subject=数据库使用问题反馈">问题反馈</a></li>
				<li class="muted">&middot;</li>
				<li>版本更新时间:${createDate}</li>
			</ul>
		</div>
	</footer>
</body>
</html>
