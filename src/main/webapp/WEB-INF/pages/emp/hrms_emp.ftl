<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker3.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker3.standalone.css"
          rel="stylesheet">
    <script src="${basePath}/js/bootstrap.js"></script>
    <style>
        .container-fluid {
            background: #f7f7f7;
            padding: 20px 30px;
        }

        h4.page-title {
            padding-bottom: 9px;
            margin: 10px 0 45px;
        }

        .modal-dialog {
            width: 300px;
            margin: 0 auto;
            margin-top: -360px;
        }
    </style>
</head>
<body class="container-fluid">
<div class="row">
    <div class="col-12">
        <h4 class="border-bottom page-title">员工管理</h4>
    <#--填充basePath上下文路径，为了在JS也能访问到上下文路径，统一上下文路径，避免以为跳转路径叠加的问题。-->
        <input type="hidden" id="basePath" value="${basePath}">
          <#if commonMessage?exists>
            <p>${commonMessage.message}</p>
          </#if>
        <div class="row">
            <div class="form-group">
                <button class="btn btn-sm btn-primary" onclick="addItem()">新增</button>
                <button class="btn btn-sm btn-danger" onclick="batchDeleteItems()">批量删除</button>
            </div>
            <div class="col-8">
                <div class="form-group form-row">
                    <label for="name" class="col col-form-label">姓名：</label>
                    <div class="col-2">
                        <input type="text" class="form-control" id="name" name="name" autocomplete="off">
                    </div>
                    <label for="sex" class="col col-form-label">性别</label>
                    <div class="col-2">
                        <select class="form-control" id="sex" name="sex">
                            <option value="">select</option>
                            <option value="0">男</option>
                            <option value="1">女</option>
                        </select>
                    </div>
                    <label for="race" class="col col-form-label">民族：</label>
                    <div class="col-2">
                        <input type="text" class="form-control" id="race" name="race" autocomplete="off">
                    </div>
                    <div class="col">
                        <button class="btn btn-sm btn-primary" onclick="query()">查询</button>
                    </div>
                </div>
            </div>
        </div>
        <table class="table table-bordered">
            <thead class="table-dark">
            <tr>
                <th scope="col"></th>
                <th scope="col">#</th>
                <th scope="col">工号</th>
                <th scope="col">姓名</th>
                <th scope="col">性别</th>
                <th scope="col">年龄</th>
                <th scope="col">生日</th>
                <th scope="col">民族</th>
                <th scope="col">学历</th>
                <th scope="col">政治面貌</th>
                <th scope="col">备注</th>
                <th scope="col">action</th>
            </tr>
            </thead>
            <tbody id="listBody">
            </tbody>
        </table>
        <div class="col-12">
            <div class="row">
                <ul class="col-6 pagination">
                    <li class="page-item">
                        <a class="page-link" href="javascript:void(0);" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                            <span class="sr-only">Previous</span>
                        </a>
                    </li>
                    <li class="page-item">
                        <a class="page-link" href="javascript:void(0);" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                            <span class="sr-only">Next</span>
                        </a>
                    </li>
                </ul>
                <p class="col-6 text-right" id="displaying">Displaying 0 to 0 page 0 items.</p>
            </div>
        </div>
    </div>
    <!-- 模态框(增加bootstrap模态框插件，处理列表操作时的各种交互）-->
    <div class="modal fade" id="userModalCenter" tabindex="-1" role="dialog" aria-labelledby="userModalCenterTitle"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="userModalLongTitle">标题</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary">保存修改</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
        integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
        crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-datepicker/1.8.0/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="${basePath}/js/emp/hrms-emp.js"></script>
</html>