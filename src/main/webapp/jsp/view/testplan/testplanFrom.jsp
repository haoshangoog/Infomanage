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
    <div class="col-md-2" id="id1">
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
            </ul>
        </div>
    </div>
    <div class="col-md-9">
     2
    </div>
</div>
<script type="text/javascript" src="jsp/js/treeCatalogue.js"></script>
</body>
</html>
