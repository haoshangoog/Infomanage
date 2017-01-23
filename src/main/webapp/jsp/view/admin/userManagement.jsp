<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
	<title>用户列表</title>
	<pageid name="userList"></pageid>
</head>
<body>

<div class="row">
	<div class="col-md-2">
		<button type="button" class="btn btn-primary btn-md" data-toggle="modal" onclick="showAddUser()">
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
			<tr onclick="showUser('@@@id@')">
				<td>@@@accountName@</td>
				<td>@@@realName@</td>
				<td class="identity">@@@identity@</td>
				<td class="deleteFlag">@@@deleteFlag@</td>
			</tr>
			</tbody>
		</table>

	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="UserModal" tabindex="-1" role="dialog" aria-labelledby="UserModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="UserModalLabel">添加用户
					<div style="float: right">
						<button type="button" id="editUserBtn" class="btn btn-default btn-sm" onclick="showEditUser()">修改</button>
						<button type="button" id="deleteUserBtn" class="btn btn-danger btn-sm" onclick="deleteUser()" >禁用用户</button>
					</div>
				</h4>
			</div>
			<div class="modal-body">
				<div class="form-group" id="id_modal_div">
					<label for="id_modal">ID：</label>
					<input type="text" class="form-control" id="id_modal" placeholder="输入Id" disabled="disabled">
				</div>
				<div class="form-group" id="accountName_modal_div">
					<label for="accountName_modal">用户名：</label>
					<input type="text" class="form-control" id="accountName_modal" placeholder="输入用户名">
				</div>
				<%--<div class="form-group" id="sex_modal_div">--%>
					<%--<label for="sex_modal">性别：</label>--%>
					<%--<select class="form-control" id="sex_modal">--%>
						<%--<option value ="1">男</option>--%>
						<%--<option value ="0">女</option>--%>
					<%--</select>--%>
				<%--</div>--%>
				<div class="form-group" id="identity_modal_div">
					<label for="identity_modal">身份：</label>
					<select class="form-control" id="identity_modal">
						<option value ="1" selected="selected">普通用户</option>
						<option value ="2">管理员</option>
					</select>
				</div>
				<div class="form-group" id="password_modal_div">
					<label for="password_modal">密码：</label>
					<input type="text" class="form-control" id="password_modal" placeholder="请输入密码">
				</div>
				<div class="form-group" id="realName_modal_div">
					<label for="identity_modal">真实名称：</label>
					<input type="text" class="form-control" id="realName_modal" placeholder="请输入真是名称">
				</div>
				<div class="form-group" id="deleteFlag_modal_div">
					<label for="deleteFlag_modal">状态：</label>
					<select class="form-control" id="deleteFlag_modal">
						<option value ="0">正在使用</option>
						<option value ="1">禁用</option>
					</select>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" id="createUserBtn" class="btn btn-primary" onclick="createUser()">创建</button>
				<button type="button" id="updateUserBtn" class="btn btn-primary" onclick="updateUser()">确认修改</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	pagingStart2('#UserPaging', '#UserPaging_Model','#per','#next','#pageNo', '/user/pageingUser?PageSize=5',"valueToString")
</script>
</body>

