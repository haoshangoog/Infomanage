$(document).ready(function() {
    LoginUserCenterSwitch();






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