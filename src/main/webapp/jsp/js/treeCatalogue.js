var FRISTCATALOGUEID = null;

// var EDITFlAG = $("#editFlag").is(':checked');

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
    parentsName = $(obj).text();

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

            // 判断显示的页面
            if($("#editFlag").is(':checked')){
                console.log("应该显示 【编辑目录】  页面");
                ShowEditCatalogue(parentsName, msg);
            }else {
                console.log("应该显示 【编辑内容】  页面");
            }

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


// 显示 编辑目录页面
function ShowEditCatalogue(parentsName , childrenCatalogue) {

    console.log("显示 编辑目录页面  ==> ShowEditCatalogue");
    console.log("parentsName: "+parentsName);
    console.log("childrenCatalogue： ");
    console.log(childrenCatalogue);
    //  给 根目录赋值
    $("#rootCatagolueName").text("根目录： " + parentsName );
    //  给 子目录赋值
    $("#childrenCatalogueList").children().remove();
    $.each(childrenCatalogue, function(i,catalogue){
        console.log(catalogue);
        var child =
            '<tr>' +
            '<td>' + catalogue.sequence + '</td>' +
            '<td>' + catalogue.catalogueName + '</td>' +
            '<td>' +
            '<button type="button" class="btn btn-default btn-sm"' +
                    'onclick="editButton(' + catalogue.id +',\'' + catalogue.catalogueName + "'," + catalogue.sequence + ')">编辑</button>' +
            '<button type="button" class="btn btn-default btn-sm" onclick="deleteButton(' + catalogue.id + ')">删除</button>' +
            '</td>' +
            '</tr>' ;
        $("#childrenCatalogueList").append(child);
    });



}

// 显示 内容页 页面
function ShowEditContextPage(parentsId) {

    console.log("显示 内容页 页面  ==> ShowEditContextPage");

}

// 显示 编辑目录页面的 模态框
function editButton(catalogueID, catalogueName, sequence) {
    console.log("------ editButton 方法");
    console.log(catalogue)
    if(catalogue){
        // 1 .  显示模态框
        $('#editModal').modal('show');
        // 2 . 模态框 头的显示
        $('#editModalLabel').text( "编辑： " + catalogueName ) ;
        // 3 . 添加默认的 值
        $('#editCatalogueName').val( catalogueName ) ;
        $('#editSequence').val( sequence ) ;
        $('#editCatalogueID').val(catalogueID)
    }
}
// 提交更改信息
function editCommit() {
    console.log("------ editCommit 方法");
    var catalogueID = $('#editCatalogueID').val();
    var catalogueName = $('#editCatalogueName').val();
    var sequence = $('#editSequence').val() ;

    $.ajax({
        type: "post",
        data: {"catalogueId":catalogueID,"catalogueName":catalogueName,"sequence":sequence},
        url: "http://localhost:8080/testPlanCatalogue/updateTestPlanCatalogue",
        async: false,
        dataType: 'html',
        error : function() {
            alert("editCommit 方法 出错");
        },
        success: function (msg) {
            if(msg == "0105"){
                alert("更新成功");
            }else {
                alert("更新失败");
            }
        }
    });

    $('#editModal').modal('hide');


}