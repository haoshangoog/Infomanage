<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<head>
    <title>首页</title>
    <pageid name="index"></pageid>
</head>

<body>
<div class="col-md-12">
    <div class="jumbotron">
        <h1>信息管理系统</h1>
        <p>在这里你可以 干这个 干那个 还可以 干那个那个。。。</p>
        <p><a class="btn btn-primary btn-lg" href="#" role="button">了解更多</a></p>
    </div>
    <div class="col-md-8">
        <h3>最新测试方案</h3>
        <div id="testPlanPaging"></div>
        <div id="testPlanPaging_Model" hidden="hidden">
            <div class="panel panel-default">
                <div class="panel-body">
                    <h4><a href="javascript:gotoContext(@@@id@)">@@@planname@</a></h4>
                </div>
            </div>
        </div>

    </div>
    <div class="col-md-4">
        <h3>最热FAQ</h3>
        <div id="FAQPaging"></div>
        <div id="FAQPaging_Model" hidden="hidden">
            <div class="panel panel-default">
                <div class="panel-body">
                    <h4><a href="#">@@@question@</a></h4>
                </div>
            </div>
        </div>
    </div>

</div>
<script type="text/javascript">
    pagingStart('#testPlanPaging', '#testPlanPaging_Model', '/testPlan/pageingTestPlan?pageSize=5&deleteflag=0', false);
    pagingStart('#FAQPaging', '#FAQPaging_Model', '/FAQ/pageingFAQ?pageSize=5&deleteflag=0', false);
    function gotoContext(id) {
        localStorage['testPlanId'] = id;
        window.location.href = "/jsp/view/testplan/testplanFrom.jsp";
    }
</script>
</body>
