function login() {

    var account = $("#accountLogin").val();
    var password = $("#passwordLogin").val();

    $.ajax({
        type: "post",
        data: {"accountName":account,"password":password},
        url: "/user/login",
        async: false,
        dataType: 'html',
        error : function() {
            alert("login Ajax 出错");
        },
        success: function (msg) {
            if(msg == "1103"){
                console.log("登陆成功");
                $("#loginModal").modal("hide");
                LoginUserCenterSwitch();
            }else if(msg == "1104"){
                $("#error").text("用户名或密码错误");
            }else {
                alert("未知错误");
            }
        }
    });

}

function logout() {
    sessionStorage.clear();
    $("#loginUl").show();
    $("#userCenterUl").hide();
    $.ajax({
        type: "post",
        data: {},
        url: "/user/logout",
        async: false,
        dataType: 'html',
        error : function() {
            alert("logout Ajax 出错");
        },
        success: function (msg) {
            if(msg == "1105"){
                alert("退出登陆成功");
            }else {
                alert("未知错误");
            }
        }
    });
}

function LoginUserCenterSwitch() {
    $("#loginUl").show();
    $("#userCenterUl").hide();
    if(sessionStorage["userSession"] != null && window.sessionStorage["userSession"].id != null){
        $("#loginUl").hide();
        $("#userCenterUl").show();
    }else {
        $.ajax({
            type: "post",
            data: {},
            url: "/user/isLogin",
            async: false,
            dataType: 'html',
            error : function() {
                alert("LoginUserCenterSwitch Ajax 出错");
            },
            success: function (msg) {
                if(msg == "1107"){
                    console.log("未登录");
                }else if(JSON.parse(msg).id != null){
                    sessionStorage["userSession"] = msg;
                    $("#loginUl").hide();
                    $("#userCenterUl").show();
                }else {
                    alert("未知错误");
                }
            }
        });
    }
}