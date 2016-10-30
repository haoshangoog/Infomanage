<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>FAQ列表</title>
    <pageid name="FAQList"></pageid>
</head>
<body>

<div class="row">
    <div class="col-md-2">
        <button type="button" class="btn btn-primary btn-md" data-toggle="modal" data-target="#addFAQModal">
            添加FAQ
        </button>
    </div>
    <div class="col-md-10">
        <div id="FAQPaging"></div>
    </div>
</div>

<div id="FAQPaging_Model" hidden="hidden">
    <div class="col-md-8">
        <div class="panel panel-default">
            <div class="panel-body">
                <h4><a href="javascript:gotoContext(@@@id@)">@@@question@</a>
                    <button type="button" class="btn btn-sm" style="float: right"
                            onclick="SHOWMODAL('注意','您确定删除此条FAQ记录吗?','deleteFAQ(@@@id@)')">删除此FAQ</button>
                    <button type="button" class="btn btn-sm" style="float: right"
                            onclick="showEditFAQ('@@@id@','@@@question@','@@@answer@')">编辑</button>
                    <button type="button" class="btn btn-sm" style="float: right"
                            onclick="showFAQ('@@@id@','@@@question@','@@@answer@')">查看</button>
                </h4>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="addFAQModal" tabindex="-1" role="dialog" aria-labelledby="addFAQModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="addFAQModalLabel">添加FAQ</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="question_modal">问题：</label>
                    <textarea type="text" rows="5" class="form-control" id="question_modal" placeholder="请输入问题"></textarea>
                </div>
                <div class="form-group">
                    <label for="answer_modal">答案：</label>
                    <textarea type="text" rows="10" class="form-control" id="answer_modal" placeholder="请输入答案"></textarea>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="createFAQ()">创建</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal Show-->
<div class="modal fade" id="showFAQModal" tabindex="-1" role="dialog" aria-labelledby="showFAQModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="showFAQModalLabel">FAQ问题</h4>
            </div>
            <div class="modal-body">
                <p id="showFAQModalBody"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal edit-->
<div class="modal fade" id="editFAQModal" tabindex="-1" role="dialog" aria-labelledby="editFAQModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="editFAQModalLabel">编辑：</h4>
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
                <button type="button" class="btn btn-primary" onclick="editFAQ()">确认</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    pagingStart('#FAQPaging', '#FAQPaging_Model', '/FAQ/pageingFAQ?pageSize=5&deleteflag=0');
</script>
</body>

