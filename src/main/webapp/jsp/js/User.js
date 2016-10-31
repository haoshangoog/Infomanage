function createUser() {
    var accountName = $("#accountName_modal").val();
    var identity = $("#identity_modal").val();
    var password = $("#password_modal").val();
    var realName = $("#realName_modal").val();

    if(accountName==null || accountName =="" ||
        identity==null || identity =="" ||
        password==null || password =="" ||
        realName==null || realName =="")
    {
        alert("创建时参数传递错误");
        return;
    }else {
        $.ajax({
            type: "post",
            data: {"accountName":accountName,"identity":identity,"password":password,"realName":realName},
            url: "/user/createUser",
            async: false,
            dataType: 'html',
            error : function() {
                alert("createUser AJAX 错误");
            },
            success: function (msg) {
                if(msg == "0101"){
                    alert("创建成功");
                    window.location.href="http://localhost:8080/jsp/view/admin/userManagement.jsp";
                }else {
                    alert("创建失败")
                }
            }
        });
    }
}

function showUser(id,question,answer) {
    $("#showUserModalLabel").text(question);
    $("#showUserModalBody").text(answer);
    $("#showUserModal").modal("show");
}

function showEditUser(id,question,answer) {
    $("#id_edit").text(id);
    $("#question_edit").text(question);
    $("#answer_edit").text(answer);
    $("#editUserModal").modal("show");
}

function editUser() {
    var id = $("#id_edit").text();
    var question = $("#question_edit").val();
    var answer = $("#answer_edit").val();
    if(id == null || id == "" ||question == "" || answer ==""){
        alert("更新信息出错");
        return;
    }
    $.ajax({
        type: "post",
        data: {"UserId":id,"question":question,"answer":answer},
        url: "/User/updateUser",
        async: false,
        dataType: 'html',
        error : function() {
            alert("editUser AJAX 错误");
        },
        success: function (msg) {
            if(msg == "0105"){
                alert("更新成功");
                window.location.href="http://localhost:8080/jsp/view/User/UserList.jsp";
            }else {
                alert("更新失败")
            }
        }
    });
}

function deleteUser(id) {
    $.ajax({
        type: "post",
        data: {"UserId":id},
        url: "/User/deleteUser",
        async: false,
        dataType: 'html',
        error : function() {
            alert("deleteUser AJAX 错误");
        },
        success: function (msg) {
            if(msg == "0103"){
                alert("删除成功");
                window.location.href="http://localhost:8080/jsp/view/User/UserList.jsp";
            }else {
                alert("删除失败")
            }
        }
    });
}

function valueToString() {

    $(".identity").each(function(){
        var identityVal = $(this).text();
        if(identityVal == "1"){
            $(this).text("普通用户");
        }else if (identityVal == "2"){
            $(this).text("管理员");
        }else{

        }
    });

    $(".deleteFlag").each(function(){
        var deleteFlagVal = $(this).text();
        if(deleteFlagVal == "0"){
            $(this).text("正在使用");
        }else if (deleteFlagVal == "1"){
            $(this).text("已禁用");
        }else{

        }
    });
}