var FRISTCATALOGUEID = null;
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
                $("#catalogue").append("<li><a href='javascript:void(0)' value='" + msg[i].id + "' class='inactive'>"+msg[i].catalogueName+"</a></li>")
            }
        }
    }
});
//////////////    以上是初始化 ///////////////////////////////////

$('.inactive').click(function(){
    parentsId = $(this).attr("value");
    var thisNode = this;
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
                    middel += "<li><a href='javascript:void(0)' value='" + msg[i].id + "' class='inactive'>"+msg[i].catalogueName+"</a></li>";
                }
            }
            console.log($(thisNode))
            $(thisNode).parent('li').append(head+middel+end);
        }
    });



    if($(this).siblings('ul').css('display')=='none'){
        $(this).parent('li').siblings('li').removeClass('inactives');
        $(this).addClass('inactives');
        $(this).siblings('ul').slideDown(100).children('li');
        if($(this).parents('li').siblings('li').children('ul').css('display')=='block'){
            $(this).parents('li').siblings('li').children('ul').parent('li').children('a').removeClass('inactives');
            $(this).parents('li').siblings('li').children('ul').slideUp(100);
        }
    }else{
        //控制自身变成+号
        $(this).removeClass('inactives');
        //控制自身菜单下子菜单隐藏
        $(this).siblings('ul').slideUp(100);
        //控制自身子菜单变成+号
        $(this).siblings('ul').children('li').children('ul').parent('li').children('a').addClass('inactives');
        //控制自身菜单下子菜单隐藏
        $(this).siblings('ul').children('li').children('ul').slideUp(100);
        //控制同级菜单只保持一个是展开的（-号显示）
        $(this).siblings('ul').children('li').children('a').removeClass('inactives');
    }
});
