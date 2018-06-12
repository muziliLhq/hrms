<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>后台·人事管理系统</title>
    <link rel="shortcut icon" href="${basePath}/img/favicon.ico">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="${basePath}/css/font-awesome.css">
    <style>
        a {
            color: white;
        }

        a:hover {
            color: orangered;
        }

        .north {
            background: #09192A;
            color: white;
            height: 100px;
        }

        .menu {
            background: #09192A;
            padding: 0;
            height: 0px;
            padding-bottom: 100%;
        }

        .menu li {
            height: 50px;
            line-height: 50px;
            text-indent: 1em;
            color: white;
        }

        .menu li:hover {
            background: #0c5460;
            cursor: pointer;
        }

        .page-wrapper {
            padding: 0;
        }
    </style>
</head>
<body class="container-fluid">
<div class="row north">
    <div class="col-6">
        <p class="h2">Entor人事管理系统
            <small>v2018</small>
        </p>
    </div>
    <div class="col-6 align-self-end text-right">
        <ul class="list-inline">
            <li class="list-inline-item dropdown">
                <button class="btn btn-sm btn-dark dropdown-toggle" type="button" id="dropdownMenuButton"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <span class="icon-user">${user.loginName}</span>
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <a class="dropdown-item" href="javascript:void(0);" onclick="personal(${user.id});">个人中心</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="javascript:void(0);" onclick="logout();">安全退出</a>
                </div>
            </li>
            <li class="list-inline">
            <li id="time" style="color: white;"></li>
        </ul>
    </div>
</div>
<div class="row main">
    <div class="col-3 menu">
        <ul class="list-unstyled">
            <li><span class="icon-home"></span>欢迎使用</li>
            <li><span class="icon-user"></span>用户管理</li>
            <li><span class="icon-sitemap"></span>部门管理</li>
            <li><span class="icon-suitcase"></span>职位管理</li>
            <li><span class="icon-group"></span>员工管理</li>
            <li><span class="icon-volume-up"></span>公告管理</li>
            <li><span class="icon-download-alt"></span>下载中心</li>
        </ul>
        <p style="font-size: small;color: white;text-align: center;">技术支持：广西南宁易唐科技<a href="http://www.entor.com.cn/">http://www.entor.com.cn/</a>
        </p>
    </div>
    <div class="col-9 page-wrapper">
        <iframe src='notice/welcome' width='100%' height='100%' frameborder='0' name='_blank' id='_blank'></iframe>
    </div>
</div>
</body>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
        integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
        crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script>
    $(function () {
        // 菜单点击事件
        $('.menu li').click(function (event) {
            // 1.调整样式：让被点击的li改变背景色为浅色，其它变为深色
            $('.menu li').each(function (i) {
                $(this).css('background', '#09192A');
            });
            $(this).css('background', '#0c5460');

            // 2.点击了某个菜单，在中心区域加载指定的页面
            switch ($(this).text()) {
                case '欢迎使用':
                    $('.page-wrapper').html("<iframe src='notice/welcome' width='100%' height='100%' frameborder='0' name='_blank' id='_blank'></iframe>");
                    break;
                case '用户管理':
                    $('.page-wrapper').html("<iframe src='user' width='100%' height='100%' frameborder='0' name='_blank' id='_blank'></iframe>");
                    break;
                case '部门管理':
                    $('.page-wrapper').html("<iframe src='dept' width='100%' height='100%' frameborder='0' name='_blank' id='_blank'></iframe>");
                    break;
                case '职位管理':
                    $('.page-wrapper').html("<iframe src='job' width='100%' height='100%' frameborder='0' name='_blank' id='_blank'></iframe>");
                    break;
                case '员工管理':
                    $('.page-wrapper').html("<iframe src='emp' width='100%' height='100%' frameborder='0' name='_blank' id='_blank'></iframe>");
                    break;
                case '公告管理':
                    $('.page-wrapper').html("<iframe src='notice' width='100%' height='100%' frameborder='0' name='_blank' id='_blank'></iframe>");
                    break;
                case '下载中心':
                    $('.page-wrapper').html("<iframe src='document' width='100%' height='100%' frameborder='0' name='_blank' id='_blank'></iframe>");
                    break;
            }
        });
    });

    function logout() {
        if (confirm('确认退出吗？')) {
            location.href = "${basePath}/logout";
        }
    }

    function personal(id) {
        $('.page-wrapper').html("<iframe src='${basePath}/personal/" + id + "' width='100%' height='100%' frameborder='0' name='_blank' id='_blank'></iframe>");
    }

    function mytime(){
        var a = new Date();
        var b = a.toLocaleTimeString();
        var c = a.toLocaleDateString();
        document.getElementById("time").innerHTML = c+"&nbsp"+b;
    }
    setInterval(function() {mytime()},1000);
</script>
</html>