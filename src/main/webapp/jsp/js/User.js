function UserModalClear() {
    $("#deleteUserBtn").hide();
    $("#editUserBtn").hide();

    $("#id_modal_div").hide();
    $("#id_modal").val("");
    $("#id_modal").attr("disabled", "disabled");

    $("#accountName_modal_div").hide();
    $("#accountName_modal").val("");
    $("#accountName_modal").attr("disabled", "disabled");

    $("#identity_modal_div").hide();
    $("#identity_modal").val("");
    $("#identity_modal").attr("disabled", "disabled");

    $("#password_modal_div").hide();
    $("#password_modal").val("");
    $("#password_modal").attr("disabled", "disabled");

    $("#realName_modal_div").hide();
    $("#realName_modal").val("");
    $("#realName_modal").attr("disabled", "disabled");

    $("#deleteFlag_modal_div").hide();
    $("#deleteFlag_modal").val("");
    $("#deleteFlag_modal").attr("disabled", "disabled");

    $("#createUserBtn").hide();
    $("#updateUserBtn").hide();

}

function showAddUser() {
    UserModalClear();
    $("#accountName_modal_div").show();
    $("#accountName_modal").removeAttr("disabled");

    $("#identity_modal_div").show();
    $("#identity_modal").removeAttr("disabled");

    $("#password_modal_div").show();
    $("#password_modal").removeAttr("disabled");

    $("#realName_modal_div").show();
    $("#realName_modal").removeAttr("disabled");

    $("#createUserBtn").show();

    $("#UserModal").modal("show");
}

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
                    $("#UserModal").modal("hide");
                    window.location.href="/jsp/view/admin/userManagement.jsp";
                }else {
                    alert("创建失败")
                }
            }
        });
    }
}


function showUser(id) {
    UserModalClear()
    $("#editUserBtn").show();
    $("#deleteUserBtn").show();

    $("#accountName_modal_div").show();
    $("#identity_modal_div").show();
    $("#password_modal_div").show();
    $("#realName_modal_div").show();
    $("#deleteFlag_modal_div").show();

    $.ajax({
        type: "post",
        data: {"userId":id},
        url: "/user/selectUserById",
        async: false,
        dataType: 'json',
        error : function() {
            alert("createUser AJAX 错误");
        },
        success: function (msg) {
            $("#id_modal").val(msg.id);
            $("#accountName_modal").val(msg.accountName);
            $("#identity_modal").val(msg.identity);
            $("#password_modal").val(msg.password);
            $("#realName_modal").val(msg.realName);
            $("#deleteFlag_modal").val(msg.deleteFlag);
        }
    });

    $("#UserModal").modal("show");

}

function showEditUser() {

    $("#editUserBtn").hide();
    $("#deleteUserBtn").hide();

    $("#accountName_modal").removeAttr("disabled");
    $("#identity_modal").removeAttr("disabled");
    $("#password_modal").removeAttr("disabled");
    $("#realName_modal").removeAttr("disabled");
    $("#deleteFlag_modal").removeAttr("disabled");

    $("#updateUserBtn").show();

}

function updateUser() {
    var id = $("#id_modal").val();
    var accountName =  $("#accountName_modal").val();
    var identity = $("#identity_modal").val();
    var password = $("#password_modal").val();
    var realName = $("#realName_modal").val();
    var deleteFlag = $("#deleteFlag_modal").val();

    $.ajax({
        type: "post",
        data: {"id":id,"accountName":accountName,"identity":identity,"password":password,"realName":realName,"deleteFlag":deleteFlag},
        url: "/user/updateUser",
        async: false,
        dataType: 'html',
        error : function() {
            alert("createUser AJAX 错误");
        },
        success: function (msg) {
            if(msg == "0105"){
                alert("更新成功");
                $("#UserModal").modal("hide");
                window.location.href="/jsp/view/admin/userManagement.jsp";
            }else{
                alert("更新失败");
                $("#UserModal").modal("hide");
            }
        }
    });
}

function deleteUser() {
    var id = $("#id_modal").val();
    $.ajax({
        type: "post",
        data: {"id":id},
        url: "/user/deleteUser",
        async: false,
        dataType: 'html',
        error : function() {
            alert("deleteUser AJAX 错误");
        },
        success: function (msg) {
            if(msg == "0103"){
                alert("禁用成功");
                window.location.href="/jsp/view/admin/userManagement.jsp";
            }else {
                alert("禁用失败");
                $("#UserModal").modal("hide");
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