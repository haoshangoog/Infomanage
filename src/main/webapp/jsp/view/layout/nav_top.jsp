<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <div class="navbar-header">
                <a class="navbar-brand" href="#" style="padding: 0; line-height: 100%">
                    <img alt="Brand" src="<%=request.getContextPath()%>jsp/image/logo1.png" style="height: 100%">
                </a>
            </div>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse">
            <ul id="topMenuList" class="nav navbar-nav">
                <li><a href="<%=request.getContextPath()%>jsp/view/testplan/testplanList.jsp">测试方案</a></li>
                <li><a href="<%=request.getContextPath()%>jsp/view/FAQ/FAQList.jsp">FAQ</a></li>
                <li><a href="">视频教学</a></li>
                <li><a href="">文档资料</a></li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown">管理员维护</a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="/jsp/view/admin/userManagement.jsp">用户管理</a></li>
                    </ul>
                </li>
            </ul>
            <ul id="loginUl" class="nav navbar-nav navbar-right">
                <li ><a data-toggle="modal" data-target="#loginModal"
                       data-whatever="@mdo">登录</a></li>
            </ul>
            <ul id="userCenterUl"class="nav navbar-nav nav-pills navbar-right" role="tablist">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown">
                        用户中心
                    </a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a onclick="logout()">退出</a></li>
                    </ul>
                </li>
            </ul>
            <form class="navbar-form navbar-right" role="search">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default">搜索</button>
            </form>

        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid --> </nav>
<div class="modal fade" id="loginModal" tabindex="-1" role="dialog"
     aria-labelledby="loginModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="exampleModalLabel">登录</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group  ">
                        <label id="error" style="color: red;text-align: center;"
                               class="control-label col-sm-10"></label>
                    </div>
                    <div class="form-group  ">
                        <label class="control-label col-sm-3">用户名：</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="accountLogin">
                        </div>
                    </div>
                    <div class="form-group  ">
                        <label class="control-label col-sm-3">密码：</label>
                        <div class="col-sm-7">
                            <input type="password" class="form-control" id="passwordLogin">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="login()">登录</button>
            </div>
        </div>
    </div>
</div>
</body>
