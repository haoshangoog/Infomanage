

function deleteTestPlan(id) {
    $.ajax({
        type: "post",
        data: {"planNameId":id},
        url: "/testPlan/deleteTestPlan",
        async: false,
        dataType: 'html',
        error : function() {
            alert("createTestPlan 错误");
        },
        success: function (msg) {
            if(msg == "0103"){
                alert("删除成功");
                window.location.href="/jsp/view/testplan/testplanList.jsp";
            }else {
                alert("删除失败")
            }
        }
    });
}