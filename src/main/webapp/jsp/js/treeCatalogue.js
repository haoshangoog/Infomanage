var FRISTCATALOGUEID = null;

var EDITFlAG = $("#editFlag").is(':checked');

$.ajax({
    type: "post",
    data: {"testPlanId":1},
    url: "http://localhost:8080/testPlanCatalogue/selectFristCatalogue",
    async: false,
    dataType: 'json',
    error : function() {
        alert("分页AJAX 出错");
    },
    success: function (msg) {
        console.log(msg);
        FRISTCATALOGUEID    = msg[0].id ;
    }
});

$.ajax({
    type: "post",
    data: {"parentsCatalogueId":FRISTCATALOGUEID,"deleteflag":0},
    url: "http://localhost:8080/testPlanCatalogue/selectChildCatalogue",
    async: false,
    dataType: 'json',
    error : function() {
        alert("分页AJAX 出错");
    },
    success: function (msg) {
        console.log(msg);
        console.log(msg.length);
        if(msg.length != 0){
            for(var i = 0 ; i < msg.length ; i++){
                $("#catalogue").append("<li><a href='javascript:void(0)' onclick='showChild(this)' value='" + msg[i].id + "' class='inactive'>"+msg[i].catalogueName+"</a></li>")
            }
        }
    }
});
//////////////    以上是初始化 ///////////////////////////////////

function showChild(obj){
    parentsId = $(obj).attr("value");
    console.log(" obj --------")
    console.log(obj);
    var thisNode = $(obj);
    var head = "<ul  style='display: none'>";
    var end  = "</ul>";
    var middel = "";
    $.ajax({
        type: "post",
        data: {"parentsCatalogueId":parentsId,"deleteflag":0},
        url: "http://localhost:8080/testPlanCatalogue/selectChildCatalogue",
        async: false,
        dataType: 'json',
        error : function() {
            alert("分页AJAX 出错");
        },
        success: function (msg) {
            console.log(msg);
            console.log(msg.length);
            if(msg.length != 0){
                for(var i = 0 ; i < msg.length ; i++){
                    middel += "<li><a href='javascript:void(0)' onclick='showChild(this)' value='" + msg[i].id + "' class='inactive'>"+msg[i].catalogueName+"</a></li>";
                }
            }
            console.log($(thisNode))
            $(thisNode).parent('li').append(head+middel+end);
        }
    });



    if($(obj).siblings('ul').css('display')=='none'){
        $(obj).parent('li').siblings('li').removeClass('inactives');
        $(obj).addClass('inactives');
        $(obj).siblings('ul').slideDown(100).children('li');
        if($(obj).parents('li').siblings('li').children('ul').css('display')=='block'){
            $(obj).parents('li').siblings('li').children('ul').parent('li').children('a').removeClass('inactives');
            $(obj).parents('li').siblings('li').children('ul').slideUp(100);
        }
    }else{
        //控制自身变成+号
        $(obj).removeClass('inactives');
        //控制自身菜单下子菜单隐藏
        $(obj).siblings('ul').slideUp(100);
        //控制自身子菜单变成+号
        $(obj).siblings('ul').children('li').children('ul').parent('li').children('a').addClass('inactives');
        //控制自身菜单下子菜单隐藏
        $(obj).siblings('ul').children('li').children('ul').slideUp(100);
        // 移除 子菜单
        $(obj).siblings('ul').children('li').remove();
        //控制同级菜单只保持一个是展开的（-号显示）
        $(obj).siblings('ul').children('li').children('a').removeClass('inactives');
    }
};
