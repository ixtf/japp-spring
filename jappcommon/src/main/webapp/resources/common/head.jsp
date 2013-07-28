<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link href="<c:url value="/resources/jquery/jquery-ui.css" />" rel="stylesheet" />
<link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet" />
<link href="<c:url value="/resources/bootstrap/css/bootstrap-responsive.min.css" />" rel="stylesheet" />
<link href="<c:url value="/resources/bootstrap/css/bootstrap-typeahead.css" />" rel="stylesheet" />
<link href="<c:url value="/resources/jquery/jquery.dataTables.css" />" rel="stylesheet" />
<link href="<c:url value="/resources/jquery/zTreeStyle.css" />" rel="stylesheet" />
<link href="<c:url value="/resources/ueditor/themes/default/ueditor.css" />" rel="stylesheet" />
<style type="text/css">
body {
	padding-top: 40px;
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}

.ztree li span.button.pIcon01_ico_open {
	margin-right: 2px;
	background: url(<c:url value="/resources/jquery/img/diy/1_open.png" />) no-repeat
		scroll 0 0 transparent;
	vertical-align: top;
	*vertical-align: middle
}

.ztree li span.button.pIcon01_ico_close {
	margin-right: 2px;
	background: url(<c:url value="/resources/jquery/img/diy/1_close.png" />) no-repeat
		scroll 0 0 transparent;
	vertical-align: top;
	*vertical-align: middle
}

.ztree li span.button.icon06_ico_docu {
	margin-right: 2px;
	background: url(<c:url value="/resources/jquery/img/diy/8.png" />) no-repeat scroll
		0 0 transparent;
	vertical-align: top;
	*vertical-align: middle
}
</style>

<script src="<c:url value="/resources/bootstrap/js/hogan.min.js" />"></script>
<script src="<c:url value="/resources/jquery/jquery.min.js" />"></script>
<script src="<c:url value="/resources/jquery/jquery-ui.min.js" />"></script>
<script src="<c:url value="/resources/jquery/jquery.ui.datepicker-zh-CN.js" />"></script>
<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/bootstrap/js/typeahead.min.js" />"></script>
<script src="<c:url value="/resources/jquery/jquery.validate.min.js" />"></script>
<script src="<c:url value="/resources/jquery/messages_zh.js" />"></script>
<script src="<c:url value="/resources/jquery/jquery.cookie.js" />"></script>
<script src="<c:url value="/resources/jquery/jquery.form.js" />"></script>
<script src="<c:url value="/resources/jquery/jquery.ztree.all.min.js" />"></script>
<script src="<c:url value="/resources/ueditor/editor.min.js" />"></script>
<script src="<c:url value="/resources/ueditor/editor_config.js" />"></script>
<script src="<c:url value="/resources/jquery/jquery.json-2.4.min.js" />"></script>
<script src="<c:url value="/resources/jquery/jquery.tablesorter.min.js" />"></script>
<script src="<c:url value="/resources/jquery/jquery.uitablefilter.js" />"></script>
<script src="<c:url value="/resources/jquery/jquery.dataTables.min.js" />"></script>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate value="${now}" pattern="yyyy" var="currentYear" scope="session" />

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->