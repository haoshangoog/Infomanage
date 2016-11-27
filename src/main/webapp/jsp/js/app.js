$(document).ready(function() {
    LoginUserCenterSwitch();
    authority();





});

function SHOWMODAL(title,body,functionName) {
    var html=
    '<div class="modal fade" id="SHOWMODAL" tabindex="-1" role="dialog" aria-labelledby="SHOWMODALLabel" aria-hidden="true">' +
    '<div class="modal-dialog">' +
    '<div class="modal-content">' +
    '<div class="modal-header">' +
    '<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>' +
    '<h4 class="SHOWMODAL-title" id="SHOWMODALLabel">'+title+'</h4>' +
    '</div>' +
    '<div id="SHOWMODAL-body" class="modal-body">' +body+
    '</div>' +
    '<div class="modal-footer">' +
    '<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>' +
    '<button type="button" class="btn btn-primary" onclick="'+functionName+'">确认</button>' +
    '</div>' +
    '</div>' +
    '</div>' +
    '</div>';

    var demo = $("#SHOWMODAL")
    if(demo.length != 0){
        demo.remove();
    }
    if(demo.length == 0){
        $("body").append(html);
        $("#SHOWMODAL").modal("show");
    }
}


// 权限的实现方法
function authority() {
    var objs = $(".authority");
    if (sessionStorage["userSession"] == null || sessionStorage["userSession"] == ''){
        objs.remove();
        return
    }
    var userSession = sessionStorage["userSession"];
    var identity = JSON.parse(sessionStorage["userSession"])['identity'];
    var userIs = "__";
    if(identity == 1){
        userIs = "user"
    }else if(identity == 2){
        userIs = "admin"
    }
    for (var i = 0 ;i<objs.length;i++) {
        var authorityAttribute = objs[i].getAttribute('authority');
        if( authorityAttribute != null){
            var authorityUserArrary = objs[i].getAttribute('authority').split("|");
            if(authorityUserArrary!="" || authorityUserArrary.length != 0 ){
                var showIt = false
                for(var k =0 ; k < authorityUserArrary.length ; k++){
                    if(authorityUserArrary[k] == userIs){
                        showIt = true
                        return
                    }
                }
                if(!showIt){
                    objs[i].remove();
                }
            };
        }

    }
}
