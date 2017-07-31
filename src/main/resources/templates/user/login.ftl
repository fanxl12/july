<!DOCTYPE html>
<html lang="zh-CN">
<head>

    <title>登录</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

    <link href="/july/static/bootstrap/3.3.0/css/bootstrap.css" rel="stylesheet"/>

</head>
<body>
<div class="container">

    <img src="/static/image/test.png">

    <#if msg??>
        <span>登录提示: ${msg}</span>
    </#if>

    <form action="/sport/login" method="post" class="form-horizontal">
        <div class="form-group">
            <label for="username">用户名</label>
            <input type="text" class="form-control required" id="username" name="username" placeholder="请输入用户名"/>
        </div>
        <div class="form-group">
            <label for="password">密码</label>
            <input type="password" class="form-control required" id="password" name="password" placeholder="请输入密码"/>
        </div>
        <button type="submit" class="btn btn-default">登录</button>
    </form>
</div>

</body>
</html>