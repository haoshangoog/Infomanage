<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<head>
    <title>首页</title>
    <pageid name="index"></pageid>
    <link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>jsp/css/index.css">
</head>

<body>
<div class="col-md-12">
    <div class="col-md-3">
        <h3>测试方案</h3>
        <div id="testPlanPaging"></div>
        <div id="testPlanPaging_Model" hidden="hidden">
            <div class="panel panel-default">
                <div class="panel-body">
                    <h4><a href="javascript:gotoContext(@@@id@)">@@@planname@</a></h4>
                </div>
            </div>
        </div>

    </div>
    <div class="col-md-3 col-md-offset-6">
        <h3>FAQ</h3>
        <div id="FAQPaging"></div>
        <div id="FAQPaging_Model" hidden="hidden">
            <div class="panel panel-default">
                <div class="panel-body" style="padding-top: 0%;padding-bottom: 0%">
                    <h4><a href="#">@@@question@</a></h4>
                </div>
            </div>
        </div>
    </div>

</div>
<script type="text/javascript">
    pagingStart('#testPlanPaging', '#testPlanPaging_Model', '/testPlan/pageingTestPlan?pageSize=5&deleteFlag=0', false);
    pagingStart('#FAQPaging', '#FAQPaging_Model', '/FAQ/pageingFAQ?pageSize=5&deleteFlag=0', false);
    function gotoContext(id) {
        localStorage['testPlanId'] = id;
        window.location.href = "/jsp/view/testplan/testplanFrom.jsp";
    }
</script>
</body>
