<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>My JSP 'index.jsp' starting page</title>
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
</head>

<script src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js">
</script>
<script>
    $(document).ready(function () {
        $("button").click(function () {
            $("#test").show();
//            document.getElementById("test").innerHTML="as"
        });
        $("button").dblclick(function () {
            $("#test").hide();
        });

    });
    $("#test").click(function () {
        alert("hhh")
    })

    function display() {
        document.getElementById("demo").innerHTML = "asd"
    }

    document.onkeydown = function (event) {
        var e = event || window.event || arguments.callee.caller.arguments[0];
        if (e && e.keyCode == 27) { // 按 Esc
            //要做的事情
            alert("esc")
        }
        if (e && e.keyCode == 113) { // 按 F2
            //要做的事情
            alert("F2")
        }
        if (e && e.keyCode == 13) { // enter 键
            //要做的事情
            alert("enter")
        }
    };

</script>
<body>
${user[0].uname}
<%=request.getRealPath("url")%><br>
<%=request.getRealPath("./")%><br>
<%=basePath%>

<%--<h2>这是一个标题</h2>--%>
<p>这是一个段落</p>
<p id="test">这是另外一个段落</p>
<button>点我</button><br>
<button> hh</button>

<script></script>

<p id="demo"></p>
<p>开启调试工具，在代码执行到第三行前会停止执行。</p>
<script>
    var x = 15 * 5;
    document.getElementById("demo").innerHTML = x;

    var x = 0.1
    var y = 0.2
    document.write((x * 10 + y * 10) / 10)

    var cars = ["Saab", "Volvo", "BMW"];
    document.write(cars)
    cars = "asdq"
    document.write(cars.length + "<br>")
    document.write(undefined)

    /**
     * 类
     * @constructor
     */
    function User() {
        name
    }

    var user = new User()
    user.name="你好"
    document.write(user.name)

    /**
     * 键盘事件
     * @param event
     */
    document.onkeydown = function (event) {
        var e = event || window.event || arguments.callee.caller.arguments[0];
        if (e && e.keyCode == 27) { // 按 Esc
            //要做的事情
            alert("esc")
        }
        if (e && e.keyCode == 113) { // 按 F2
            //要做的事情
            alert("F2")
        }
        if (e && e.keyCode == 13) { // enter 键
            //要做的事情
            alert("enter")
        }
    };

    document.write("".constructor)
    document.write(String(new Date()) + "<br>")
    var y = "John";   // y 是一个字符串
    var x = +y;
    document.write(x)

    "use strict";//严格模式
    ddd = 12
    document.write(ddd)

</script>

<!--form  表单-->
<script>
    function validateForm(){
        var x=document.forms["myForm"]["fname"].value;
        var atpos=x.indexOf("@");
        var dotpos=x.lastIndexOf(".");
        if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length){
            alert("不是一个有效的 e-mail 地址");
            return false;
        }
    }
    function getValue(){
        var a,b,c;
        a = void ( b = 5, c = 7 );
        document.write('a = ' + a + ' b = ' + b +' c = ' + c );
    }
</script>

<form name="myForm" action=""
      onsubmit="return validateForm()" method="post">
    名字: <input type="text" name="fname">
    <input type="submit" value="提交">

    <form>
        <input type="button" value="点我" onclick="getValue();" />
    </form>


    <br>
    <a href="javascript:void(0);">点我没有反应的!</a>
    <a href="/springmvc/tt">点我定位到指定位置!</a>


    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <p id="pos">尾部定位点</p>


</body>
</html>

