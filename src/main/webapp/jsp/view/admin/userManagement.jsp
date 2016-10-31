<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
	<title>用户列表</title>
	<pageid name="userList"></pageid>
</head>
<body>

<div class="row">
	<div class="col-md-2">
		<button type="button" class="btn btn-primary btn-md" data-toggle="modal" data-target="#addUserModal">
			添加用户
		</button>
	</div>
	<div class="col-md-8">
		<table class="table table-hover">
			<thead>
			<tr>
				<th width="25%">用户名</th>
				<th width="25%">真是姓名</th>
				<th width="25%">用户类别</th>
				<th width="25%">状态</th>
			</tr>
			</thead>
			<tbody id="UserPaging">
			<tr>
				<td>Mark</td>
				<td>Otto</td>
				<td>@mdo</td>
				<td>111</td>
			</tr>
			</tbody>
		</table>
		<div class="row">
			<div class="col-md-2 col-md-offset-2" id="per"></div>
			<div class="col-md-2 col-md-offset-2" id="pageNo"></div>
			<div class="col-md-2 col-md-offset-1" id="next"></div>
		</div>

		<table hidden="hiddenr">
			<tbody id="UserPaging_Model">
			<tr onclick="alert('@@@id@')">
				<td>@@@accountName@</td>
				<td>@@@realName@</td>
				<td class="identity">@@@identity@</td>
				<td class="deleteFlag">@@@deleteFlag@</td>
			</tr>
			</tbody>
		</table>

	</div>
</div>


	<%--<div class="panel panel-default">--%>
	<%--<div class="panel-body">--%>
	<%--<h4><a href="javascript:gotoContext(@@@id@)">@@@question@</a>--%>
	<%--<button type="button" class="btn btn-sm" style="float: right"--%>
	<%--onclick="SHOWMODAL('注意','您确定删除此条用户记录吗?','deleteUser(@@@id@)')">删除此用户</button>--%>
	<%--<button type="button" class="btn btn-sm" style="float: right"--%>
	<%--onclick="showEditUser('@@@id@','@@@question@','@@@answer@')">编辑</button>--%>
	<%--<button type="button" class="btn btn-sm" style="float: right"--%>
	<%--onclick="showUser('@@@id@','@@@question@','@@@answer@')">查看</button>--%>
	<%--</h4>--%>
	<%--</div>--%>
	<%--</div>--%>
	<%--</div>--%>

<!-- Modal -->
<div class="modal fade" id="addUserModal" tabindex="-1" role="dialog" aria-labelledby="addUserModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				<h4 class="modal-title" id="addUserModalLabel">添加用户</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label for="accountName_modal">用户名：</label>
					<input type="text" class="form-control" id="accountName_modal" placeholder="输入用户名">
				</div>
				<div class="form-group" hidden="hidden">
					<label for="sex_modal">性别：</label>
					<select class="form-control" id="sex_modal">
						<option value ="1">男</option>
						<option value ="0">女</option>
					</select>
				</div>
				<div class="form-group">
					<label for="identity_modal">身份：</label>
					<select class="form-control" id="identity_modal">
						<option value ="1">普通用户</option>
						<option value ="2">管理员</option>
					</select>
				</div>
				<div class="form-group">
					<label for="password_modal">密码：</label>
					<input type="text" class="form-control" id="password_modal" placeholder="请输入密码">
				</div>
				<div class="form-group">
					<label for="identity_modal">真实名称：</label>
					<input type="text" class="form-control" id="realName_modal" placeholder="请输入真是名称">
				</div>
				<div class="form-group" hidden="hidden">
					<label for="deleteflag_modal">状态：</label>
					<select class="form-control" id="deleteflag_modal">
						<option value ="0">正在使用</option>
						<option value ="1">禁用</option>
					</select>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary" onclick="createUser()">创建</button>
			</div>
		</div>
	</div>
</div>

<!-- Modal Show-->
<div class="modal fade" id="showUserModal" tabindex="-1" role="dialog" aria-labelledby="showUserModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="showUserModalLabel">User问题</h4>
			</div>
			<div class="modal-body">
				<p id="showUserModalBody"></p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>

<!-- Modal edit-->
<div class="modal fade" id="editUserModal" tabindex="-1" role="dialog" aria-labelledby="editUserModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				<h4 class="modal-title" id="editUserModalLabel">编辑：</h4>
			</div>
			<div class="modal-body">
				<div id="id_edit" hidden="hidden"></div>
				<div class="form-group">
					<label for="question_edit">问题：</label>
					<textarea type="text" rows="5" class="form-control" id="question_edit" placeholder="请输入问题"></textarea>
				</div>
				<div class="form-group">
					<label for="answer_edit">答案：</label>
					<textarea type="text" rows="10" class="form-control" id="answer_edit" placeholder="请输入答案"></textarea>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary" onclick="editUser()">确认</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	pagingStart2('#UserPaging', '#UserPaging_Model','#per','#next','#pageNo', '/user/pageingUser?PageSize=5',"valueToString")
</script>
</body>

