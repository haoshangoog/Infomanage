<%--
  Created by IntelliJ IDEA.
  User: tuberose
  Date: 16-10-7
  Time: 下午10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试方案</title>
    <style type="text/css">
        *{margin: 0;padding: 0}
        /*body{font-size: 12px;font-family: "宋体","微软雅黑";}*/
        ul,li{list-style: none;}
        a:link,a:visited{text-decoration: none;}
        .list{width: 210px;border-bottom:solid 1px #316a91;}
        .list ul li{background-color:#467ca2; border:solid 1px #316a91; border-bottom:0;}
        .list ul li a{padding-left: 10px;color: #fff; font-size:12px; display: block; font-weight:bold; height:36px;line-height: 36px;position: relative;
        }
        .list ul li .inactive{ background:url(/jsp/css/img/off.png) no-repeat 184px center;}
        .list ul li .inactives{background:url(/jsp/css/img/on.png) no-repeat 184px center;}
        .list ul li ul{display: none;}
        .list ul li ul li { border-left:0; border-right:0; background-color:#6196bb; border-color:#467ca2;}
        .list ul li ul li ul{display: none;}
        .list ul li ul li a{ padding-left:20px;}
        .list ul li ul li ul li { background-color:#d6e6f1; border-color:#6196bb; }
        .last{ background-color:#d6e6f1; border-color:#6196bb; }
        .list ul li ul li ul li a{ color:#316a91; padding-left:30px;}
    </style>
</head>
<body>
<div class="row">
    <div class="col-md-2">
        <div class="list">
            <ul class="yiji" id="catalogue">
                <li><a href="javascript:" class="inactive">团体会员</a>
                    <ul style="display: none">
                        <li><a href="javascript:" class="inactive active">美协机关</a>
                            <ul>
                                <li><a href="#">办公室</a></li>
                                <li><a href="#">人事处</a></li>
                                <li><a href="#">组联部</a></li>
                                <li><a href="#">外联部</a></li>
                                <li><a href="#">研究部</a></li>
                                <li><a href="#">维权办</a></li>
                            </ul>
                        </li>
                        <li class="last"><a href="#">《美术》杂志社</a></li>
                    </ul>
                </li>
                <li><a href="#" class="inactive">组织机构</a>
                    <ul style="display: none">
                        <li><a href="#" class="inactive active">美协机关</a>
                            <ul>
                                <li><a href="#">办公室</a></li>
                                <li><a href="#">人事处</a></li>
                                <li><a href="#">组联部</a></li>
                                <li><a href="#">外联部</a></li>
                                <li><a href="#">研究部</a></li>
                                <li><a href="#">维权办</a></li>
                            </ul>
                        </li>
                        <li><a href="#" class="inactive active">中国文联美术艺术中心</a>
                            <ul>
                                <li><a href="#">综合部</a></li>
                                <li><a href="#">大型活动部</a></li>
                                <li><a href="#">展览部</a></li>
                                <li><a href="#">艺委会工作部</a></li>
                                <li><a href="#">信息资源部</a></li>
                                <li><a href="#">双年展办公室</a></li>
                            </ul>
                        </li>
                        <li class="last"><a href="#">《美术》杂志社</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <div class="col-md-9">
     2
    </div>
</div>
<script type="text/javascript">
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
        }
    })

        $("#catalogue").append("<li><a href='javascript:' class='inactive'>团体会员qqq</a></li>")

        $('.inactive').click(function(){
            $(this).parent('li').append("<ul  style='display: none'><li><a href='#'>综合部</a></li><li><a href='#'>大型活动部</a></li><li><a href='#'>展览部</a></li></ul>")
            console.log('11111');
            console.log($(this));
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
        })



</script>
</body>
</html>
