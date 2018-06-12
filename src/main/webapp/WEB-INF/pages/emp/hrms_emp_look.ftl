<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="css/font-awesome.css">
    <style>
        .container-fluid {
            background: #f7f7f7;
            padding: 20px 30px;
        }

        h4.page-title {
            padding-bottom: 9px;
            margin: 10px 0 45px;
        }
    </style>
</head>
<body class="container-fluid">
<div class="row">
    <div class="col-12">
        <h4 class="border-bottom page-title">员工管理</h4>
        <h6 class="border-bottom page-title">查看员工</h6>
        <form>
            <div class="form-group form-row">
                <label for="id" class="col-1 col-form-label">工号</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly id="id" value="${employee.id}">
                </div>
                <label for="name" class="col-1 col-form-label">姓名</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly id="name" value="${employee.name}">
                </div>
            </div>
            <div class="form-group form-row">
                <label for="sex" class="col-1 col-form-label">性别</label>
                <div class="col-4">
                    <#--${true?string('5','7')}-->
                    <input type="text" class="form-control" readonly id="sex" value="${(employee.sex == 0) ?string('男','女')}">
                </div>
                <label for="age" class="col-1 col-form-label">年龄</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly id="age" value="${employee.age}">
                </div>
            </div>
            <div class="form-group form-row">
                <label for="birthday" class="col-1 col-form-label">生日</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly id="birthday"
                           value="${employee.birthday?string('yyyy-MM-dd HH:mm:ss')}">
                </div>
                <label for="race" class="col-1 col-form-label">民族</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly id="race" value="${employee.race}">
                </div>
            </div>
            <div class="form-group form-row">
                <label for="education" class="col-1 col-form-label">学历</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly id="education" value="${employee.education}">
                </div>
                <label for="party" class="col-1 col-form-label">政治面貌</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly id="party" value="${employee.party}">
                </div>
            </div>
            <div class="form-group form-row">
                <label for="cardId" class="col-1 col-form-label">身份证</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly id="cardId" value="${employee.cardId}">
                </div>
                <label for="address" class="col-1 col-form-label">地址</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly id="address" value="${employee.address}">
                </div>
            </div>
            <div class="form-group form-row">
                <label for="tel" class="col-1 col-form-label">座机</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly id="tel" value="${employee.tel}">
                </div>
                <label for="phone" class="col-1 col-form-label">手机号码</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly id="phone" value="${employee.phone}">
                </div>
            </div>
            <div class="form-group form-row">
                <label for="qqNum" class="col-1 col-form-label">QQ</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly id="qqNum" value="${employee.qqNum}">
                </div>
                <label for="email" class="col-1 col-form-label">邮箱</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly id="email" value="${employee.email}">
                </div>
            </div>
            <div class="form-group form-row">
                <label for="speciality" class="col-1 col-form-label">特长</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly id="speciality" value="${employee.speciality}">
                </div>
                <label for="hobby" class="col-1 col-form-label">爱好</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly id="hobby" value="${employee.hobby}">
                </div>
            </div>
            <div class="form-group form-row">
                <label for="createDate" class="col-1 col-form-label">创建日期</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly id="createDate"
                           value="${employee.createDate?string('yyyy-MM-dd HH:mm:ss')}">
                </div>
                <label for="postCode" class="col-1 col-form-label">邮编</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly id="postCode" value="${employee.postCode}">
                </div>
            </div>
            <div class="form-group form-row">
                <label for="name" class="col-1 col-form-label">部门名称</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly id="name" value="${employee.dept.name}">
                </div>
                <label for="name" class="col-1 col-form-label">职位名称</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly id="name" value="${employee.job.name}">
                </div>
            </div>
                <div class="form-group form-row">
                <label for="remark" class="col-1 col-form-label">remark</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly id="remark" value="${employee.remark}">
                </div>
                </div>
        </form>
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
<script>
    $(function () {
    });
</script>
</html>
