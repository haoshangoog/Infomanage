function login() {

    var account = $("#accountLogin").val();
    var password = $("#passwordLogin").val();

    $.ajax({
        type: "post",
        data: {"accountName":account,"password":password},
        url: "http://localhost:8080/user/login",
        async: false,
        dataType: 'html',
        error : function() {
            alert("login Ajax 出错");
        },
        success: function (msg) {
            if(msg == "1103"){
                console.log("登陆成功");
                $("#loginModal").modal("hide");
            }else if(msg == "1104"){
                $("#error").text("用户名或密码错误");
            }else {
                alert("未知错误");
            }
        }
    });

}