$(function () {
    // 定义全局变量
    basePath = $('#basePath').val();

    // 第一次加载界面，需要首页数据
    $.get(basePath + '/job/list', function (data) {
        dataHandler(data);
    }, 'json');
})

/**
 * 分页数据处理
 * @param data
 */
function dataHandler(data) {
    // 总记录数
    var recordCount = data.recordCount;
    // 当前页码
    var pageIndex = data.pageIndex;
    // 当前分页数
    var pageSize = data.pageSize;
    // 列表数据
    var pageList = data.pageList;

    console.log(pageList);

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
 * @param list
 */
function listHandler(list) {
    // tbody在追加列表之前必须是空的
    $('tbody').html('');

    // 列表不存在
    if (!list)
        return;

    // 动态加载列表数据，通过js方式创建元素并附加到tbody
    // 数据行数取决于list的元素个数，遍历list
    for (var index = 0; index < list.length; index++) {
        // 定义每行记录对应的document对象
        // 定义行
        var tr = $('<tr></tr>')
        // 定义列
        var ck = $('<th scope="row"><input type="checkbox"></th>')
        var th = $('<th scope="row">' + (index + 1) + '</th>');
        var jobId = $('<td>' + list[index].id + '</td>');
        var name = $('<td>' + list[index].name + '</td>');
        var remark = $('<td>' + list[index].remark + '</td>');
        var operation = '<td><button type="button" class="btn-sm btn-success" onclick="lookItem(this);">查看</button>';
        operation = operation + '<button type="button" class="btn-sm btn-outline-success" onclick="updateItem(this)"> 更新</button>' +
            '<button type="button" class="btn-sm btn-outline-danger" onclick="deleteItem(this)">删除</button></td>';
        // 组行和列并附加到tbody
        $('tbody').append(tr.append(ck).append(th).append(jobId).append(name).append(remark).append(operation));
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

    // 处理左侧分页栏
    // 分页最多显示3页
    if (totalPage <= 3) {
        for (var i = 1; i <= totalPage; i++) {
            $('.page-item:last').before($('<li class="page-item"><a class="page-link" href="javascript:void(0);" onclick="currPage(this,' + pageSize + ');">' + i + '</a></li>'));
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

    // 处理右侧打印信息如：Displaying 1 to 10 14 items
    $('#displaying').text('Displaying ' + pageIndex + ' to ' + totalPage + ' page, total ' + recordCount + " items.");
}

/**
 * 计算总页数
 * @param recordCount
 * @param pageSize
 */
function getTotalPage(recordCount, pageSize) {
    return recordCount % pageSize == 0 ? recordCount / pageSize : parseInt(recordCount / pageSize + 1);
}

/**
 * 上一页
 * @param pageIndex
 * @param pageSize
 */
function prePage(pageIndex, pageSize) {
    // 获取查询关键字
    var name = $('#name').val();
    var remark = $('#remark').val();

    if (pageIndex - 1 >= 1) {
        $.get(basePath + '/job/list', {
            name: name,
            remark: remark,
            pageIndex: pageIndex - 1, pageSize: pageSize
        }, function (data) {
            dataHandler(data);
        }, 'json');
    }
}

/**
 * 下一页
 * @param pageIndex
 * @param pageSize
 * @param totalPage
 */
function nextPage(pageIndex, pageSize, totalPage) {
    // 获取查询关键字
    var name = $('#name').val();
    var remark = $('#remark').val();

    if (pageIndex + 1 <= totalPage) {
        $.get(basePath + '/job/list', {
            name: name,
            remark: remark,
            pageIndex: pageIndex + 1,
            pageSize: pageSize
        }, function (data) {
            dataHandler(data);
        }, 'json');
    }
}

/**
 * 当前页
 * @param that
 * @param pageSize
 */
function currPage(that, pageSize) {
    // 获取查询关键字
    var name = $('#name').val();
    var remark = $('#remark').val();

    $.get(basePath + '/job/list', {
        name: name,
        remark: remark,
        pageIndex: that.text,
        pageSize: pageSize
    }, function (data) {
        dataHandler(data);
    }, 'json');
}

/**
 * 查询
 */
function query() {
    // 获取查询关键字
    var name = $('#name').val();
    var remark = $('#remark').val();

    // 请求服务器
    $.getJSON(basePath + '/job/list', {
        name: name,
        remark: remark
    }, function (data) {
        dataHandler(data);
    })
}

/**
 * 添加
 */
function addItem() {
    $(parent.document).find('.page-wrapper').html("<iframe src='" + basePath + "/job/add' width='100%' height='100%' frameborder='0' name='_blank' id='_blank'></iframe>");
}

/**
 * look
 * @param that
 */
function lookItem(that) {
    // 获取行id
    var jobId = getIdOfRow(that);
    // 加载查看页面，请求服务器
    $(parent.document).find('.page-wrapper').html("<iframe src='" + basePath + "/job/look/" + jobId  + "'  width='100%' height='100%' frameborder='0' name='_blank' id='_blank'></iframe>");
}

/**
 * 更新
 * @param that
 */
function updateItem(that) {
    // 获取行id
    var jobId = getIdOfRow(that);
    // 加载更新页面，请求服务器
    $(parent.document).find('.page-wrapper').html("<iframe src='" + basePath + "/job/update?id=" + jobId + "' width='100%' height='100%' frameborder='0' name='_blank' id='_blank'></iframe>");
}


/**
 * 删除
 * @param that
 */
function deleteItem(that) {
    // 设置模态框
    initModal('删除确认', '确认删除', '取消', '提交');
    // 显示模态框
    $('#userModalCenter').modal('show');
    // 按钮事件
    $('#userModalCenter .modal-footer .btn-primary').unbind().click(function () {
        // 关闭之前的模态框
        $('#userModalCenter').modal('hide');
        // 获取行id
        var jobId = getIdOfRow(that);
        // 请求服务器删除行
        $.getJSON(basePath + '/job/del/' + jobId, function (data) {
            alert(data.message);
            // 刷新列表
            $.getJSON(basePath + '/job/list', function (data) {
                dataHandler(data);
            })
        })
    })
}

/**
 * 批量删除
 */
function batchDeleteItems() {
    if ($(':checkbox:checked').length == 0 ) {
        alert("请先选择要删除的行！");
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
        // 获取行id
        var jobIds = new Array();
        $(':checkbox:checked').each(function () {
            jobIds.push(getIdOfRow($(this)));
        });
        // 请求服务器删除所选行
        $.getJSON(basePath + '/job/batchDelete', {ids: jobIds}, function (data) {
           alert(data.message);
           // 刷新列表
            $.getJSON(basePath + '/job/list', function (data) {
                dataHandler(data);
            })
        })
    })
}

/**
 * 获取行的id
 * @param that
 */
function getIdOfRow(that) {
    return $(that).parent().parent().children().eq(2).text();
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
