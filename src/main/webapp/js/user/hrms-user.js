$(function () {
    // 定义全局变量
    basePath = $('#basePath').val();

    // 初始化日期插件
    $('#createDate').datepicker({
        language: "zh-CN", // 语言选择中文
        format: "yyyy-mm-dd", // 格式化日期
        autoclose: 1 // 选择完日期后，弹出框自动关闭
        /*timepicker: true, // 关闭时间选项
        yearEnd: 2050, // 设置最大年份
        todayButton: false, // 关闭选择今天按钮
        startView: 3, // 打开弹出框时，显示到什么格式，3代表月
        minView: 3 // 能选择到的最小日期格式*/
    });
    //1.第一次加载页面，需要首页数据
    $.get(basePath + '/user/list', function (data) {
        dataHandler(data);
    }, 'json');
});

/**
 * 分页数据处理
 * @param data
 */
function dataHandler(data) {
    //总记录数
    var recordCount = data.recordCount;
    // 当前页码
    var pageIndex = data.pageIndex;
    // 当前分页数
    var pageSize = data.pageSize;
    // 列表数据
    var pageList = data.pageList;

    // 列表数据处理
    listHandler(pageList);

    // 分页信息处理
    if (pageList)
        pageHandler(recordCount, pageIndex, pageSize);
    else
        pageHandler(0, 1, 4);
}

/**
 * 列表数据处理
 */
function listHandler(list) {
    //  tbody在追加列表之前必须是空的
    $('tbody').html('');

    // 列表不存在
    if (!list)
        return;

    // 每行数据对应6列，分别是序号，用户ID，帐号，用户名，创建时间和操作
    // 动态加载列表数据，通过js方式创建元素并附加到tbody
    // 数据行数取决于list的元素个数，遍历list
    for (var index = 0; index < list.length; index++) {
        // 定义每行记录对应的user对象
        // 定义行
        var tr = $('<tr></tr>');
        // 定义列
        var ck = $('<th scope="row"><input type="checkbox"></th>')
        var th = $('<th scope="row">' + (index + 1) + '</th>');
        var userId = $('<td>' + list[index].id + '</td>');
        var loginName = $('<td>' + list[index].loginName + '</td>');
        var username = $('<td>' + list[index].username + '</td>');
        var createDate = $('<td>' + list[index].createDate + '</td>');
        var operation = '<td><button type="button" class="btn-sm btn-outline-primary" onclick="lookItem(this)">查看</button>' ;
        if (list[index].status == 0) {
            operation = operation + '<button type="button" class="btn-sm btn-outline-secondary" onclick="frozeItem(this,1);">解冻</button>';
        } else {
            operation = operation + '<button type="button" class="btn-sm btn-outline-secondary" onclick="frozeItem(this,0);">冻结</button>';
        }
           operation = operation + '<button type="button" class="btn-sm btn-outline-success" onclick="updateItem(this)"> 更新</button>' +
            '<button type="button" class="btn-sm btn-outline-danger" onclick="deleteItem(this)">删除</button></td>';
        // 组行和列并附加到tbody
        $('tbody').append(tr.append(ck).append(th).append(userId).append(loginName).append(username).append(createDate).append(operation));
    }
}

/**
 * 分页信息处理
 * @param recordCount
 * @param pageIndex
 * @param pageSize
 */
function pageHandler(recordCount, pageIndex, pageSize) {
    // 清除旧的分页栏
    $('.page-link').each(function () {
        if (!$(this).attr('aria-label')) {
            $(this).parent().remove();
        }
    });

    // 获得总页数，备用
    var totalPage = getTotalPage(recordCount, pageSize);

    //  处理左侧分页栏
    //  分页最多显示3页
    if (totalPage <= 3) {
        for (var i = 1; i <= totalPage; i++) {
            $('.page-item:last').before($('<li class="page-item"><a class="page-link" href="javascript:void(0);" onclick="currPage(this' + pageSize + ');">' + i + '</a></li>'));
        }
    } else {
        // 页数超过3，显示当前页以及它的前一页和后一页
        if (pageIndex == 1) {// 如果当前页是第一页
            for (var i = 1; i <= 3; i++) {
                $('.page-item:last').before($('<li class="page-item"><a class="page-link" href="javascript:void(0);" onclick="currPage(this,' + pageSize + ');">' + i + '</a></li>'));
            }
        } else if (pageIndex == totalPage) {// 如果当前页是第一页
            for (var i = totalPage - 2; i <= totalPage; i++) {
                $('.page-item:last').before($('<li class="page-item"><a class="page-link" href="javascript:void(0);" onclick="currPage(this,' + pageSize + ');">' + i + '</a></li>'));
            }
        } else {
            $('.page-item:last').before($('<li class="page-item"><a class="page-link" href="javascript:void(0);" onclick="currPage(this,' + pageSize + ');">' + (pageIndex - 1) + '</a></li>'));
            $('.page-item:last').before($('<li class="page-item"><a class="page-link" href="javascript:void(0);" onclick="currPage(this,' + pageSize + ');">' + pageIndex + '</a></li>'));
            $('.page-item:last').before($('<li class="page-item"><a class="page-link" href="javascript:void(0);" onclick="currPage(this,' + pageSize + ');">' + (pageIndex + 1) + '</a></li>'));
        }
    }

    // 当前页设置背景色
    $('.page-link').each(function () {
        if ($(this).text() == pageIndex) {
            $(this).css('background', '#0056b3');
            $(this).css('color', '#e9ecef');
        } else {
            $(this).css('background', '#e9ecef');
            $(this).css('color', '#0056b3');
        }
    });

    // 为分页栏绑定事件
    // 上一页
    $('.page-link:first').unbind().click(function () {
        prePage(pageIndex, pageSize);
    });
    // 下一页
    $('.page-link:last').unbind().click(function () {
        nextPage(pageIndex, pageSize, totalPage);
    });

    // 处理右侧打印信息如：Displaying 1 to 10 14 items.
    $('#displaying').text('Displaying ' + pageIndex + ' to ' + totalPage + ' page,  total ' + recordCount + " items.");
}

/**
 * 计算总页数
 * @param recordCount
 * @param pageSize
 * @returns {number}
 */
function getTotalPage(recordCount, pageSize) {
    return recordCount % pageSize == 0 ? recordCount / pageSize : parseInt(recordCount / pageSize + 1);
}

/**
 * 上一页
 */
function prePage(pageIndex, pageSize) {
    // 获取查询关键字
    var username = $('#username').val();
    var createDate = $('#createDate').val();
    var status = $('#status').val();

    if (pageIndex - 1 >= 1) {
        $.get(basePath + 'user/list', {
            username: username,
            createDate: createDate,
            status: status,
            pageIndex: pageIndex - 1,
            pageSize: pageSize
        }, function (data) {
            dataHandler(data);
        }, 'json');
    }
}

/**
 * 下一页
 */
function nextPage(pageIndex, pageSize, totalPage) {
    // 获取查询关键字
    var username = $('#username').val();
    var createDate = $('#createDate').val();
    var status = $('#status').val();

    if (pageIndex + 1 <= totalPage) {
        $.get(basePath + 'user/list', {
            username: username,
            createDate: createDate,
            status: status,
            pageIndex: pageIndex + 1,
            pageSize: pageSize
        }, function (data) {
            dataHandler(data);
        }, 'json');
    }
}

/**
 * 当前页
 */
function currPage(that, pageSize) {
    // 获取查询关键字
    var username = $('#username').val();
    var createDate = $('#createDate').val();
    var status = $('#status').val();

    $.get(basePath + 'user/list', {
        username: username,
        createDate: createDate,
        status: status,
        pageIndex: that.text,
        pageSize: pageSize
    }, function (data) {
        dataHandler(data);
    }, 'json');
}

/**
 * 添加功能
 */
function addItem() {
    $(parent.document).find('.page-wrapper').html("<iframe src='" + basePath + "/user/add' width='100%' height='100%' frameborder='0' name='_blank' id='_blank'></iframe>");
}

/**
 * 查询功能
 */
function query() {
    // 获取查询关键字
    var username = $('#username').val();
    var createDate = $('#createDate').val();
    var status = $('#status').val();

    // 2.请求服务器
    $.getJSON(basePath + '/user/list', {username: username, createDate: createDate, status: status}, function (data) {
        dataHandler(data);
    });
}

/**
 * 查看记录
 * @param that
 */
function lookItem(that) {
    // 获得查看行id
    var userId = getIdOfRow(that);
    // 加载查看页面
    // 查看页面是请求服务器得到的
   /* $(parent.document).find('.page-wrapper')是从子页面hrms_user.ftl中
     获得父页面hrms_main.ftl中的.page-wrapper选择器对应的元素，仍然借助iframe框架来加载页面，
     保证局部与整体良好的分离特点。*/
    $(parent.document).find('.page-wrapper').html("<iframe src='" + basePath + "/user/look/" + userId + "' width='100%' height='100%' frameborder='0' name='_blank' id='_blank'></iframe>");
}

/**
 * 更新功能
 * @param that
 */
function updateItem(that) {
    // 获得查看行id
    var userId = getIdOfRow(that);
    // 加载更新页面
    // 更新页面是请求服务器得到的
    $(parent.document).find('.page-wrapper').html("<iframe src='" + basePath + "/user/update?id=" + userId + "' width='100%' height='100%' frameborder='0' name='_blank' id='_blank'></iframe>");
}

/**
 * 删除记录
 * @param that
 */
function deleteItem(that) {
    // 设置模态框
    initModal('删除确认','确认删除吗', '取消', '提交');

    // 显示模态框
    $('#userModalCenter').modal('show');

    // 按钮事件
    $('#userModalCenter .modal-footer .btn-primary').unbind().click(function () {
        // 关闭之前的模态框
        $('#userModalCenter').modal('hide');
        // 获得删除行的userId
        var userId = getIdOfRow(that);
        // 请求服务器删除该记录
        $.getJSON(basePath + '/user/del/' + userId, function (data) {
            alert(data.message);
            // 刷新列表
            $.getJSON(basePath + '/user/list', {user: null, pageIndex: 1, pageSize: 4}, function (data) {
                dataHandler(data);
            });
        });
    });
}

/**
 * 批量删除
 */
function batchDeleteItems() {

    if ($(':checkbox:checked').length == 0) {
        alert("请选择要删除的行");
        return;
    }

    // 设置模态框
    initModal('确认删除', '确认删除这' + $(':checkbox:checked').length + '条记录吗？', '取消', '提交');
    // 显示模态框
    $('#userModalCenter').modal('show');
    // 按钮事件
    $('#userModalCenter .modal-footer .btn-primary').unbind().click(function () {
        // 关闭之前的模态框
        $('#userModalCenter').modal('hide');
        // 获取行的id
        var userIds = new Array();
        $(':checkbox:checked').each(function () {
            userIds.push(getIdOfRow($(this)));
        });
        // 请求服务器删除该行记录
        $.getJSON(basePath + '/user/batchDelete', {ids: userIds} , function (data) {
            alert(data.message);
            // 刷新列表
            $.getJSON(basePath + '/user/list', function (data) {
                dataHandler(data);
            });
        });
    });
}

/**
 * 导出excel
 */
function exportItems() {
    if ($(':checkbox:checked').length == 0) {
        alert("请选择要导出的行");
        return;
    }

    // 设置模态框
    initModal('确认导出', '确认导出这' + $(':checkbox:checked').length + '条记录', '取消', '提交');
    // 显示模态框
    $('#userModalCenter').modal('show');
    // 按钮事件
    $('#userModalCenter .modal-footer .btn-primary').unbind().click(function () {
        // 关闭之前的模态框
        $('#userModalCenter').modal('hide');
        // 获取行id
        var userIds = new Array();
        $(':checkbox:checked').each(function () {
            userIds.push(getIdOfRow($(this)));
        })
        // 请求服务器
        location.href = basePath + '/user/export?ids[]=' + userIds;
    })
}

/**
 * 冻结解冻用户
 * @param that
 * @param status
 */
function frozeItem(that, status) {
    // 设置模态框
    if (status == 0)
        initModal('冻结确认','确认冻结吗','取消','提交');// title, body, btn1, btn2
    else
        initModal('解冻确认','确认解冻吗','取消','提交');

    // 显示模态框
    $('#userModalCenter').modal('show');

    // 按钮事件
    $('#userModalCenter .modal-footer .btn-primary').unbind().click(function () {
        // 关闭之前的模态框
        $('#userModalCenter').modal('hide');
        // 获得所选行的userId
        var  userId = getIdOfRow(that);
        // 请求服务器
        // controller
        $.post(basePath + '/user/froze', {id: userId, status: status}, function (data) {
            alert(data.message);
           // 刷新列表
            $.getJSON(basePath + '/user/list', {user: null, pageIndex: 1, pageSize: 4}, function (data) {
                dataHandler(data);
            });
        });
    });
}

/**
 * 获取行的id
 * @param that
 * @returns {Promise<string> | * | USVString | jQuery}
 */
function getIdOfRow(that) {
    return $(that).parent().siblings().eq(1).text();
}

/**
 * 初始化模态框
 * @param title
 * @param body
 * @param btn1
 * @param btn2
 */
function initModal(title, body, btn1, btn2) {
    // 标题
    $('#userModalCenter .modal-title').text(title);
    // 内容
    $('#userModalCenter .modal-body').text(body);
    // 按钮
    if (btn1)
        $('#userModalCenter .modal-footer .btn-secondary').text(btn1);
    if (btn2)
        $('#userModalCenter .modal-footer .btn-primary').text(btn2);
    if (!btn1 && !btn2)
        $('#userModalCenter .modal-footer').hide();
    else
        $('#userModalCenter .modal-footer').show();
}

