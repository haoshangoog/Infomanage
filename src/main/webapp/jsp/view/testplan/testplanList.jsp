<%--
  Created by IntelliJ IDEA.
  User: tuberose
  Date: 16-10-7
  Time: 下午10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>测试方案列表</title>
    <pageid name="testplanList"></pageid>
</head>
<body>

<div class="row">
    <div id="testPlanPaging_Model" hidden="hidden">
        <div class="col-md-8 col-md-offset-2">
            <div class="panel panel-default">
                <div class="panel-body">
                    <h4><a href="javascript:gotoContext(@@@id@)">@@@planname@</a></h4>
                </div>
            </div>
        </div>
    </div>
    <div id="testPlanPaging"></div>
</div>

<script type="text/javascript">

    pagingStart('#testPlanPaging', '#testPlanPaging_Model', 'http://localhost:8080/testPlan/pageingTestPlan?pageSize=5&deleteflag=0');

    function gotoContext(id) {
        localStorage['testPlanId'] = id;
        window.location.href="http://localhost:8080/jsp/view/testplan/testplanFrom.jsp";
    }
</script>
</body>

