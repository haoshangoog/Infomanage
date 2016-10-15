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
        * {
            margin: 0;
            padding: 0
        }

        /*body{font-size: 12px;font-family: "宋体","微软雅黑";}*/
        ul, li {
            list-style: none;
        }

        a:link, a:visited {
            text-decoration: none;
        }

        .list {
            width: 210px;
            border-bottom: solid 1px #316a91;
        }

        .list ul li {
            background-color: #467ca2;
            border: solid 1px #316a91;
            border-bottom: 0;
        }

        .list ul li a {
            padding-left: 10px;
            color: #fff;
            font-size: 12px;
            display: block;
            font-weight: bold;
            height: 36px;
            line-height: 36px;
            position: relative;
        }

        .list ul li .inactive {
            background: url(/jsp/css/img/off.png) no-repeat 184px center;
        }

        .list ul li .inactives {
            background: url(/jsp/css/img/on.png) no-repeat 184px center;
        }

        .list ul li ul {
            display: none;
        }

        .list ul li ul li {
            border-left: 0;
            border-right: 0;
            background-color: #6196bb;
            border-color: #467ca2;
        }

        .list ul li ul li ul {
            display: none;
        }

        .list ul li ul li a {
            padding-left: 20px;
        }

        .list ul li ul li ul li {
            background-color: #d6e6f1;
            border-color: #6196bb;
        }

        .last {
            background-color: #d6e6f1;
            border-color: #6196bb;
        }

        .list ul li ul li ul li a {
            color: #316a91;
            padding-left: 30px;
        }
    </style>
</head>
<body>
<div class="row">
    <div class="col-md-2" id="id1">
        <div class="switch">
            <label>编辑目录：</label>
            <input id="editFlag" type="checkbox" onclick="onClickEdit()"/>
        </div>
        <div class="list">
            <ul id="rootCatalogue"></ul>
            <ul class="yiji" id="catalogue"></ul>
        </div>
    </div>
    <div class="col-md-9">
        <div class="panel panel-default" style="height:auto!important; height:100%; min-height: 500px;">
            <div class="panel-body">
                <div id="editCatalogueModel" style="padding: 0% 5%" hidden="hidden">
                    <div class="row">
                        <label><h3 id="rootCatagolueNameStr">请选择目录</h3></label>
                        <label hidden="hidden"><h3 id="rootCatagolueName"></h3></label>
                        <label hidden="hidden"><h3 id="rootCatagolueId"></h3></label>
                        <hr style="margin-top:0px ; width: 100% ;border-top-color: black">
                    </div>
                    <div class="row">
                        <label><h4>子目录：</h4></label>
                        <button type="button" class="btn btn-default btn-sm" onclick="addCatalogueButton()" style="float: right">
                            添加子目录
                        </button>
                    </div>
                    <div class="row">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th width="60px">序号</th>
                                <th>子目录名称</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody id="childrenCatalogueList">
                            </tbody>
                        </table>
                    </div>
                </div>
                <div id="showConext" style="padding: 0% 5%">
                    <div class="row">
                        <label><h3 id="rootCatagolueNameStr_Context">请选择目录</h3></label>
                        <label hidden="hidden"><h3 id="rootCatagolueName_Context"></h3></label>
                        <label hidden="hidden"><h4 id="rootCatagolueId_Context"></h4></label>
                        <button id="editContextButton"type="button" class="btn btn-default btn-sm" onclick="editContextButton()" style="float: right" >
                            修改此内容
                        </button>
                        <button id="editCommitContextButton"type="button" class="btn btn-default btn-sm" onclick="editCommitContextButton()" style="float: right" >
                            提交修改内容
                        </button>
                        <button id="addContextButton" type="button" class="btn btn-default btn-sm" onclick="addContextButton()" style="float: right" >
                            创建内容
                        </button>
                        <button id="addCommitContextButton"type="button" class="btn btn-default btn-sm" onclick="addCommitContextButton()" style="float: right" >
                            提交创建改内容
                        </button>
                        <hr style="margin-top:0px ; width: 100% ;border-top-color: black">
                    </div>
                    <div id="contextMsg"></div>
                    <!-- START 编辑 页 信息的model -->
                    <div id="editContextInfo" hidden="hidden">
                        <div class="form-group" hidden="hidden">
                            <label>ID</label>
                            <textarea class="form-control" id="testPK_edit" disabled></textarea>
                        </div>
                        <div class="form-group">
                            <label>测试：</label>
                            <textarea class="form-control" id="testID_edit"></textarea>
                        </div>
                        <div class="form-group">
                            <label>测试内容：</label>
                            <textarea class="form-control" id="testContext_edit"></textarea>
                        </div>
                        <div class="form-group">
                            <label>测试目的：</label>
                            <textarea class="form-control" id="testAim_edit"></textarea>
                        </div>
                        <div class="form-group">
                            <label>测试拓扑：</label>
                            <textarea class="form-control" id="testTopology_edit"></textarea>
                        </div>
                        <div class="form-group">
                            <label>测试方法：</label>
                            <textarea class="form-control" id="testMethod_edit"></textarea>
                        </div>
                        <div class="form-group">
                            <label>测试配置：</label>
                            <textarea class="form-control" id="testConfigure_edit"></textarea>
                        </div>
                        <div class="form-group">
                            <label>测试结果：</label>
                            <textarea class="form-control" id="testResult_edit"></textarea>
                        </div>
                        <div class="form-group">
                            <label>北向接口：</label>
                            <textarea class="form-control" id="northInterface_edit"></textarea>
                        </div>
                        <div class="form-group">
                            <label>测试结论：</label>
                            <textarea class="form-control" id="testConclusion_edit"></textarea>
                        </div>
                        <div class="form-group">
                            <label>备注：</label>
                            <textarea class="form-control" id="remark_edit"></textarea>
                        </div>
                    </div>
                    <!-- END   编辑 页 信息的model -->
                    <!-- START 显示 页 信息的model -->
                    <table class="table table-hover" id="showContextInfo" hidden="hidden">
                        <thead>
                        <tr>
                            <th width="20%">名称</th>
                            <th>内容</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr hidden="hidden">
                            <td>ID</td>
                            <td id="testPK"></td>
                        </tr>
                        <tr>
                            <td>测试ID&nbsp&nbsp&nbsp：</td>
                            <td id="testID"></td>
                        </tr>
                        <tr>
                            <td>测试内容：</td>
                            <td id="testContext"></td>
                        </tr>
                        <tr>
                            <td>测试目的：</td>
                            <td id="testAim"></td>
                        </tr>
                        <tr>
                            <td>测试拓扑：</td>
                            <td id="testTopology"></td>
                        </tr>
                        <tr>
                            <td>测试方法：</td>
                            <td id="testMethod"></td>
                        </tr>
                        <tr>
                            <td>测试配置：</td>
                            <td id="testConfigure"></td>
                        </tr>
                        <tr>
                            <td>测试结果：</td>
                            <td id="testResult"></td>
                        </tr>
                        <tr>
                            <td>北向接口：</td>
                            <td id="northInterface"></td>
                        </tr>
                        <tr>
                            <td>测试结论：</td>
                            <td id="testConclusion"></td>
                        </tr>
                        <tr>
                            <td>备注：</td>
                            <td id="remark"></td>
                        </tr>
                        </tbody>
                    </table>
                    <!-- END 显示 页 信息的model -->
                </div>
            </div>
        </div>
    </div>
</div>

<!-- START 编辑目录的页面model -->
<div>


</div>
<!-- adit Modal -->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title" id="editModalLabel">Modal title</h4>
            </div>
            <div class="modal-body">
                <div class="form-group" hidden="hidden">
                    <label for="editCatalogueID">目录ID：</label>
                    <input type="text" class="form-control" id="editCatalogueID" disabled>
                </div>
                <div class="form-group">
                    <label for="editCatalogueName">目录名称：</label>
                    <input type="text" class="form-control" id="editCatalogueName" placeholder="请输入目录名称">
                </div>
                <div class="form-group">
                    <label for="editSequence">目录序列：</label>
                    <input type="number" class="form-control" id="editSequence" placeholder="请输入目录序列">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="editCommit()">更新此条记录</button>
            </div>
        </div>
    </div>
</div>
<!-- add Modal -->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="addModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title" id="addModalLabel">Modal title</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="addCatalogueName">目录名称：</label>
                    <input type="text" class="form-control" id="addCatalogueName" placeholder="请输入目录名称">
                </div>
                <div class="form-group">
                    <label for="addSequence">目录序列：</label>
                    <input type="number" class="form-control" id="addSequence" placeholder="请输入目录序列">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="addCommit()">创建此条记录</button>
            </div>
        </div>
    </div>
</div>
<!-- delete Modal -->
<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title" id="deleteModalLabel">Modal title</h4>
            </div>
            <div class="modal-body">
                <h4 id="deleteCatalogueWarnInfo">您确定要删除此条记录吗？</h4>
                <div class="form-group" hidden="hidden">
                    <label for="deleteCatalogueID">目录ID：</label>
                    <input type="text" class="form-control" id="deleteCatalogueID" disabled>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="deleteCommit()">删除此条记录</button>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript" src="jsp/js/treeCatalogue.js"></script>
</body>
</html>
