var FRISTCATALOGUEID = null;
var TESTPLANID  = localStorage['testPlanId'];
// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 这个地方需要校验 是否是数字！！！！！！！！
if(TESTPLANID ==null || TESTPLANID == ""){
    alert("系统出错！")
}
$("#editCommitContextButton").hide();
$("#addCommitContextButton").hide();
$.ajax({
    type: "post",
    data: {"testPlanId":TESTPLANID},
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
        $("#editContextButton").hide();
        $("#addContextButton").hide();
    }
});

function onClickEdit() {
    console.log("点击编辑目录");
    $("#editContextInfo").removeAttr("hidden");
    $("#showConext").attr("hidden","hidden");
    var root = "<li><a href='javascript:void(0)' onclick='showChild(this)' value='" + FRISTCATALOGUEID + "' class='inactive'>根目录</a></li>"
    $('#catalogue').remove();
    if($("#editFlag").is(':checked')){
        $('#editCatalogueModel').removeAttr("hidden");
        $('#rootCatalogue').children().remove();
        $('#rootCatalogue').append(root);
    }else {
        window.location.reload();
    }
}
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
                ShowEditCatalogue(parentsId,parentsName, msg);
            }else {
                console.log("应该显示 【编辑内容】  页面");
                ShowEditContextPage(parentsId,parentsName);
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
function ShowEditCatalogue(parentsId, parentsName , childrenCatalogue) {

    console.log("显示 编辑目录页面  ==> ShowEditCatalogue");
    console.log("parentsName: "+parentsName);
    console.log("childrenCatalogue： ");
    console.log(childrenCatalogue);
    //  给 根目录赋值
    $("#rootCatagolueNameStr").text("根目录： " + parentsName );
    $("#rootCatagolueName").text( parentsName );
    $("#rootCatagolueId").text( parentsId );
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
            '<button type="button" class="btn btn-default btn-sm" onclick="deleteButton(' + catalogue.id + ',\'' + catalogue.catalogueName + '\')">删除</button>' +
            '</td>' +
            '</tr>' ;
        $("#childrenCatalogueList").append(child);
    });
}

// 显示 内容【页】 页面
function ShowEditContextPage(catalogueID,catalogueName) {
    console.log("显示 内容页 页面  ==> ShowEditContextPage");
    $("#rootCatagolueNameStr_Context").text("目录： " + catalogueName );
    $("#rootCatagolueName_Context").text( catalogueName );
    $("#rootCatagolueId_Context").text( catalogueID );

    $("#showContextInfo").attr("hidden","hidden");
    $("#editContextInfo").attr("hidden","hidden");
    $("#editContextButton").hide();
    $("#addContextButton").hide();
    $("#contextMsg").children().remove();

    $.ajax({
        type: "post",
        data: {"catalogueId":catalogueID},
        url: "http://localhost:8080/testPlanContext/selectContextByCatalogueId",
        async: false,
        dataType: 'json',

        success: function (msg) {
            if(msg == "2001"){
                $("#contextMsg").append("<h4> 没有内容呀！ 赶快创建一条吧。。。 </h4>");
                $("#addContextButton").show();
                return;
            }
            var context = msg[0];
            $("#testPK").html(context.id);
            $("#testPK_edit").html(context.id);

            $("#testID").html(context.testID);
            $("#testID_edit").html(context.testID);

            $("#testContext").html(context.testContext);
            $("#testContext_edit").html(context.testContext);

            $("#testAim").html(context.testAim);
            $("#testAim_edit").html(context.testAim);

            $("#testTopology").html(context.testTopology);
            $("#testTopology_edit").html(context.testTopology);

            $("#testMethod").html(context.testMethod);
            $("#testMethod_edit").html(context.testMethod);

            $("#testConfigure").html(context.testConfigure);
            $("#testConfigure_edit").html(context.testConfigure);

            $("#testResult").html(context.testResult);
            $("#testResult_edit").html(context.testResult);

            $("#northInterface").html(context.northInterface);
            $("#northInterface_edit").html(context.northInterface);

            $("#testConclusion").html(context.testConclusion);
            $("#testConclusion_edit").html(context.testConclusion);

            $("#remark").html(context.remark);
            $("#remark_edit").html(context.remark);

            $("#showContextInfo").removeAttr("hidden");
            $("#editContextInfo").attr("hidden","hidden");
            $("#editContextButton").show();
        }
    });

}

// 显示 编辑目录页面的 模态框
function editButton(catalogueID, catalogueName, sequence) {
    console.log("------ editButton 方法");
    // 1 .  显示模态框
    $('#editModal').modal('show');
    // 2 . 模态框 头的显示
    $('#editModalLabel').text( "编辑： " + catalogueName ) ;
    // 3 . 添加默认的 值
    $('#editCatalogueName').val( catalogueName ) ;
    $('#editSequence').val( sequence ) ;
    $('#editCatalogueID').val(catalogueID) ;
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
    // 关闭 模态框
    $('#editModal').modal('hide');
}

// 显示 删除目录 模态框
function deleteButton(catalogueID,catalogueName) {
    console.log("------ deleteButton 方法");
    $('#deleteModalLabel').text( "删除： " + catalogueName ) ;
    $('#deleteCatalogueID').val(catalogueID);
    $('#deleteModal').modal('show');
}
// 提交 删除信息
function deleteCommit() {
    console.log("------ deleteCommit 方法");
    var catalogueID = $('#deleteCatalogueID').val();
    $.ajax({
        type: "post",
        data: {"catalogueId":catalogueID},
        url: "http://localhost:8080/testPlanCatalogue/deleteTestPlanCatalogue",
        async: false,
        dataType: 'html',
        error : function() {
            alert("deleteCommit 方法 出错");
        },
        success: function (msg) {
            if(msg == "0103"){
                alert("删除成功");
            }else {
                alert("删除失败");
            }
        }
    });
    // 关闭 模态框
    $('#deleteModal').modal('hide');
}

// 显示 添加目录 的模态框
function addCatalogueButton() {
    console.log("------ addButton 方法");
    $('#addModal').modal('show');
    $('#addModalLabel').text( "添加到： " + $("#rootCatagolueName").text()) ;
}
// 提交 添加信息
function addCommit() {
    console.log("------ addCommit 方法");
    var parentsId = $('#rootCatagolueId').text();
    var catalogueName = $('#addCatalogueName').val();
    var sequence = $('#addSequence').val() ;

    $.ajax({
        type: "post",
        data: {"parentsId":parentsId,"catalogueName":catalogueName,"sequence":sequence},
        url: "http://localhost:8080/testPlanCatalogue/createTestPlanCatalogue",
        async: false,
        dataType: 'html',
        error : function() {
            alert("addCommit 方法 出错");
        },
        success: function (msg) {
            if(msg == "0101"){
                alert("创建成功");
            }else {
                alert("创建失败");
            }
        }
    });
    // 关闭 模态框
    $('#addModal').modal('hide');
}

// 编辑 内容页
function editContextButton() {
    console.log("------ editContextButton 方法");
    $("#editContextInfo").removeAttr("hidden");
    $("#showContextInfo").attr("hidden","hidden");
    $("#editContextButton").hide();
    $("#addContextButton").hide();
    $("#editCommitContextButton").show();
}

// 提交 内容页
function editCommitContextButton(){
    console.log("------ editCommitContextButton 方法");

    var testPK =  $("#testPK_edit").val();
    $("#testPK").html(testPK);

    var testID = $("#testID_edit").val();
    $("#testID").html(testID);

    var testContext = $("#testContext_edit").val();
    $("#testContext").html(testContext);

    var testAim = $("#testAim_edit").val();
    $("#testAim").html(testAim);

    var testTopology = $("#testTopology_edit").val();
    $("#testTopology").html(testTopology);

    var testMethod = $("#testMethod_edit").val();
    $("#testMethod").html(testMethod);

    var testConfigure = $("#testConfigure_edit").val();
    $("#testConfigure").html(testConfigure);

    var testResult = $("#testResult_edit").val();
    $("#testResult").html(testResult);

    var northInterface = $("#northInterface_edit").val();
    $("#northInterface").html(northInterface);

    var testConclusion = $("#testConclusion_edit").val();
    $("#testConclusion").html(testConclusion);

    var remark = $("#remark_edit").val();
    $("#remark").html(remark);

    $.ajax({
        type: "post",
        data: {
            "testPlanContextId":testPK,
            "testID":testID,
            "testContext":testContext,
            "testAim":testAim,
            "testTopology":testTopology,
            "testMethod":testMethod,
            "testConfigure":testConfigure,
            "testResult":testResult,
            "northInterface":northInterface,
            "testConclusion":testConclusion,
            "remark":remark,
        },
        url: "http://localhost:8080/testPlanContext/updateTestPlanContext",
        async: false,
        dataType: 'html',
        error : function() {
            alert("editCommitContextButton 方法 出错");
        },
        success: function (msg) {
            if(msg == "0105"){
                alert("更新成功");
                $("#showContextInfo").removeAttr("hidden");
                $("#editContextInfo").attr("hidden","hidden");
                $("#editContextButton").show();
                $("#addContextButton").hide();
                $("#editCommitContextButton").hide();
            }else {
                alert("更新失败");
            }
        }
    });

}

function addContextButton() {
    console.log("------ addContextButton 方法");

    $("#editContextInfo").removeAttr("hidden");
    $("#showContextInfo").attr("hidden","hidden");
    $("#editContextButton").hide();
    $("#addContextButton").hide();
    $("#editCommitContextButton").hide();
    $("#addCommitContextButton").show();

}

function addCommitContextButton() {
    console.log("------ addCommitContextButton 方法");

    var catalogueId = $("#rootCatagolueId_Context").text();

    var testID = $("#testID_edit").val();
    $("#testID").html(testID);

    var testContext = $("#testContext_edit").val();
    $("#testContext").html(testContext);

    var testAim = $("#testAim_edit").val();
    $("#testAim").html(testAim);

    var testTopology = $("#testTopology_edit").val();
    $("#testTopology").html(testTopology);

    var testMethod = $("#testMethod_edit").val();
    $("#testMethod").html(testMethod);

    var testConfigure = $("#testConfigure_edit").val();
    $("#testConfigure").html(testConfigure);

    var testResult = $("#testResult_edit").val();
    $("#testResult").html(testResult);

    var northInterface = $("#northInterface_edit").val();
    $("#northInterface").html(northInterface);

    var testConclusion = $("#testConclusion_edit").val();
    $("#testConclusion").html(testConclusion);

    var remark = $("#remark_edit").val();
    $("#remark").html(remark);

    $.ajax({
        type: "post",
        data: {
            "catalogueId":catalogueId,
            "testID":testID,
            "testContext":testContext,
            "testAim":testAim,
            "testTopology":testTopology,
            "testMethod":testMethod,
            "testConfigure":testConfigure,
            "testResult":testResult,
            "northInterface":northInterface,
            "testConclusion":testConclusion,
            "remark":remark,
        },
        url: "http://localhost:8080/testPlanContext/createTestPlanContext",
        async: false,
        dataType: 'html',
        error : function() {
            alert("addCommitContextButton 方法 出错");
        },
        success: function (msg) {
            if(msg == "0101"){
                alert("创建成功");
                $("#showContextInfo").removeAttr("hidden");
                $("#editContextInfo").attr("hidden","hidden");
                $("#editContextButton").hide();
                $("#addContextButton").hide();
                $("#editCommitContextButton").hide();
                $("#addCommitContextButton").hide();
            }else {
                alert("创建失败");
            }
        }
    });
}