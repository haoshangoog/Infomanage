function createFAQ() {
    var question = $("#question_modal").val();
    var answer = $("#answer_modal").val();
    if(question==null || question ==""){
        alert("创建失败，不可以创建问题为空的FAQ");
        return;
    }else {
        $.ajax({
            type: "post",
            data: {"question":question,"answer":answer},
            url: "/FAQ/createFAQ",
            async: false,
            dataType: 'html',
            error : function() {
                alert("createFAQ AJAX 错误");
            },
            success: function (msg) {
                if(msg == "0101"){
                    alert("创建成功");
                    window.location.href="/jsp/view/testplan/testplanList.jsp";
                }else {
                    alert("创建失败")
                }
            }
        });
    }
}

function showFAQ(id,question,answer) {
    $("#showFAQModalLabel").text(question);
    $("#showFAQModalBody").text(answer);
    $("#showFAQModal").modal("show");
}

function showEditFAQ(id,question,answer) {
    $("#id_edit").text(id);
    $("#question_edit").text(question);
    $("#answer_edit").text(answer);
    $("#editFAQModal").modal("show");
}

function editFAQ() {
    var id = $("#id_edit").text();
    var question = $("#question_edit").val();
    var answer = $("#answer_edit").val();
    if(id == null || id == "" ||question == "" || answer ==""){
        alert("更新信息出错");
        return;
    }
    $.ajax({
        type: "post",
        data: {"faqId":id,"question":question,"answer":answer},
        url: "/FAQ/updateFAQ",
        async: false,
        dataType: 'html',
        error : function() {
            alert("editFAQ AJAX 错误");
        },
        success: function (msg) {
            if(msg == "0105"){
                alert("更新成功");
                window.location.href="/jsp/view/FAQ/FAQList.jsp";
            }else {
                alert("更新失败")
            }
        }
    });
}

function deleteFAQ(id) {
    $.ajax({
        type: "post",
        data: {"faqId":id},
        url: "/FAQ/deleteFAQ",
        async: false,
        dataType: 'html',
        error : function() {
            alert("deleteFAQ AJAX 错误");
        },
        success: function (msg) {
            if(msg == "0103"){
                alert("删除成功");
                window.location.href="/jsp/view/FAQ/FAQList.jsp";
            }else {
                alert("删除失败")
            }
        }
    });
}