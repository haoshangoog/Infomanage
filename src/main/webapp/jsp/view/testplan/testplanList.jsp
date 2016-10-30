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
    <div class="col-md-2">
        <button type="button" class="btn btn-primary btn-md" data-toggle="modal" data-target="#addTestPlanModal">
            添加测试文档
        </button>
    </div>
    <div class="col-md-10">
        <div id="testPlanPaging"></div>
    </div>
</div>

<div id="testPlanPaging_Model" hidden="hidden">
    <div class="col-md-8">
        <div class="panel panel-default">
            <div class="panel-body">
                <h4><a href="javascript:gotoContext(@@@id@)">@@@planname@</a>
                    <button type="button" class="btn btn-sm" style="float: right" onclick="SHOWMODAL('注意','您确定删除此条记录吗?','deleteTestPlan(@@@id@)')">删除此测试方案</button></h4>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="addTestPlanModal" tabindex="-1" role="dialog" aria-labelledby="addTestPlanModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="addTestPlanModalLabel">添加测试方案</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="planName">测试方案名称</label>
                    <input type="text" class="form-control" id="planName" placeholder="请输入测试方案名称">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="createTestPlan()">创建</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

    pagingStart('#testPlanPaging', '#testPlanPaging_Model', 'http://localhost:8080/testPlan/pageingTestPlan?pageSize=5&deleteflag=0');

    function gotoContext(id) {
        localStorage['testPlanId'] = id;
        window.location.href="http://localhost:8080/jsp/view/testplan/testplanFrom.jsp";
    }
    
    function createTestPlan() {
        var planName = $("#planName").val();
        if(planName==null || planName ==""){
            alert("创建失败");
            return;
        }else {
            $.ajax({
                type: "post",
                data: {"planName":planName},
                url: "http://localhost:8080/testPlan/createTestPlan",
                async: false,
                dataType: 'html',
                error : function() {
                    alert("createTestPlan 错误");
                },
                success: function (msg) {
                    if(msg == "0101"){
                        alert("创建成功");
                    }else {
                        alert("创建失败")
                    }
                }
            });
        }
    }

</script>
</body>

