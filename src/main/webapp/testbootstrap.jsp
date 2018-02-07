<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>Bootstrap 模板</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link
            href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
            rel="stylesheet">

    <!-- HTML5 Shiv 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
    <!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->

    <!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
    <script src="https://code.jquery.com/jquery.js"></script>
    <!-- 包括所有已编译的插件 -->
    <script src="<%=path%>/js/bootstrap.min.js"></script>
</head>

<script type="text/javascript">
    var as = "sss"

    function test() {
        debugger;
        alert("asd1")
//        location.reload(true)
//        location.replace("tt")//相对地址/tt
    }
</script>
<body>
<p class="bg-primary text-danger">haha</p><br>
<button class="btn btn-info btn-lg active">ss</button>
<br>
<%--<img src="img/adb.png" class="img-circle">--%>
<i class="glyphicon glyphicon-star"></i>
<span class="glyphicon glyphicon-star"></span>

<div class="input-group">
    <input type="text" class="form-control">
    <div class="input-group-btn">
        <button class="btn btn-default">提交</button>
    </div>
</div>
<ul class="nav nav-tabs">
    <li><a href="#">首页</a></li>
    <li><a href="#">咨询</a></li>
    <li><a href="#">产品</a></li>
    <li><a href="#">关于</a></li>
</ul>
<br>
<br>
<ul class="nav nav-pills">
    <li id="sy"><a href="#">首页</a></li>
    <li id="zx"><a href="#">咨询</a></li>
    <li id="cp"><a href="#">产品</a></li>
    <li id="gy"><a href="#">关于</a></li>
</ul>
<script >
    $(document).ready(function () {
        var sy = $("#sy");
        var zx = $("#zx");
        var cp = $("#cp");
        var gy = $("#gy");
        var cur=sy
        cur.addClass("active")
        sy.click(function () {
            cur.removeClass("active")
            sy.addClass("active")
            cur=sy
        })
        zx.click(function () {
            cur.removeClass("active")
            zx.addClass("active")
            cur=zx
        })
        cp.click(function () {
            cur.removeClass("active")
            cp.addClass("active")
            cur=cp
        })
        gy.click(function () {
            cur.removeClass("active")
            gy.addClass("active")
            cur=gy
        })
    })
</script>
</body>
</html>