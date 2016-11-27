<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<base href="<%=basePath%>">
	<link rel="stylesheet" type="text/css"  href="<%=basePath%>jsp/css/cssFramework/font-awesome.min.css">
	<link rel="stylesheet" type="text/css"  href="<%=basePath%>jsp/css/cssFramework/bootstrap.min.css">
	<link rel="stylesheet" type="text/css"  href="<%=basePath%>jsp/css/cssFramework/bootstrap-switch.min.css">
	<link rel="stylesheet" type="text/css"  href="<%=basePath%>jsp/css/cssFramework/bootstrap-datetimepicker.css">
	<link rel="stylesheet" type="text/css"  href="<%=basePath%>jsp/css/cssFramework/bootstrap-theme.min.css">
	<link rel="stylesheet" type="text/css"  href="<%=basePath%>jsp/css/cssFramework/offcanvas.css">
	<link rel="stylesheet" type="text/css"	href="<%=basePath%>jsp/css/cssFramework/textedit_default.css">
	<link rel="stylesheet" type="text/css"  href="<%=basePath%>jsp/css/cssFramework/wysiwyg-editor.css" />
	<link rel="stylesheet" type="text/css"  href="<%=basePath%>jsp/css/cssFramework/textedit.css" />
	<link rel="stylesheet" type="text/css"  href="<%=basePath%>jsp/css/cssFramework/prettify-1.0.css" />
	<link rel="stylesheet" type="text/css"  href="<%=basePath%>jsp/css/app.css">
	<link rel="stylesheet" type="text/css"  href="<%=basePath%>jsp/css/table.css">
	<script type="text/javascript" src="<%=basePath%>jsp/js/JsFramework/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>jsp/js/JsFramework/jquery.validate.js"></script>
    <script type="text/javascript" src="<%=basePath%>jsp/js/JsFramework/bootstrap-switch.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>jsp/js/JsFramework/additional-methods.js"></script>
	<script type="text/javascript" src="<%=basePath%>jsp/js/JsFramework/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>jsp/js/JsFramework/moment-with-locales.js"></script>
	<script type="text/javascript" src="<%=basePath%>jsp/js/JsFramework/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="<%=basePath%>jsp/js/JsFramework/bootstrap-filestyle.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>jsp/js/JsFramework/ie-emulation-modes-warning.js"></script>
	<script type="text/javascript" src="<%=basePath%>jsp/js/JsFramework/masonry.pkgd.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>jsp/js/JsFramework/modernizr-2.6.2.min.js"></script>
	<%--<script type="text/javascript" src="<%=basePath%>jsp/js/JsFramework/textedit.js"></script>--%>
	<%--<script type="text/javascript" src="<%=basePath%>jsp/js/JsFramework/wysiwyg-editor.js"></script>--%>
	<script type="text/javascript" src="<%=basePath%>jsp/js/JsFramework/Chart.js"></script>
	<%--<script type="text/javascript" src="<%=basePath%>jsp/js/JsFramework/wysiwyg.js"></script>--%>
	<script type="text/javascript" src="<%=basePath%>jsp/js/app.js"></script>
	<script type="text/javascript" src="<%=basePath%>jsp/js/paging.js"></script>
	<script type="text/javascript" src="<%=basePath%>jsp/js/menuActive.js"></script>
	<script type="text/javascript" src="<%=basePath%>jsp/js/login.js"></script>
	<script type="text/javascript" src="<%=basePath%>jsp/js/TestPlan.js"></script>
	<script type="text/javascript" src="<%=basePath%>jsp/js/FAQ.js"></script>
	<script type="text/javascript" src="<%=basePath%>jsp/js/User.js"></script>
	<title>
		<sitemesh:write property='title' />
	</title>
	<sitemesh:write property='head' />
	
</head>
<body >
	<jsp:include page="/jsp/view/layout/nav_top.jsp"></jsp:include>
	<div class="container-fluid">
			<sitemesh:write property='body'/>
    </div>
	<div style="position: fixed;background-color: #323232;width: 100%;height: 100%;
					text-align: center;z-index: 999;top: 88%;opacity: 0.8;">
		<div style="color: white;padding-top: 20px;">
			瑞斯康达科技发展股份有限公司
			<p>Copyright © 2016 Raisecom Technology Co., Ltd. </p></div>
	</div>
</body>
</html>
