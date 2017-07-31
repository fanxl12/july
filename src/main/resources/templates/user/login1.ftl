<!DOCTYPE html>
<html lang="zh-CN" xmlns:th="http://www.thymeleaf.org">
<head>

    <title>登录</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link href="/july/static/bootstrap/3.3.0/css/bootstrap.css" rel="stylesheet"/>

</head>
<body>
<div class="container">

    123图片
    <img src="/july/static/image/test.png">

    <button type="button" class="btn btn-danger">Action</button>

    <h4>${name}</h4>
    <ul>
        <li>${dateVar}</li>
        <li>${dateVar?string('yyyy-mm-dd HH:mm:ss')}</li>
        <li>${number}</li>
        <li>${isGirl?string('yes', 'no')}</li>
        <li>${fanxl!'值不存在'}</li>
        <#assign a=100/>
        <li>${a + 40}</li>
    </ul>

    <ol>
        <#list myList as item>
            <li>${item} : ${item_index}</li>
        </#list>
        <#if map??>
            <#list map?keys as key>
            ${key}:${map[key]}
            </#list>
        <#else>
            wu zhi
        </#if>


    </ol>


</div>

</body>
</html>