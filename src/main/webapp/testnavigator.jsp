<%--
  Created by IntelliJ IDEA.
  User: HZ
  Date: 2018/2/2
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<script type="text/javascript">
    var nav=window.navigator;
    var result="浏览器信息<br>";
    for(var propertyName in nav){
        result=result+propertyName+"："+window.navigator[propertyName]+"<br>";
    }
//    document.writeln(result);
</script>

<script type="text/javascript">
    const NAME="name";
    function save(){
        var name=document.getElementById("name").value;
        var storages=document.getElementsByName("storage");
        if(storages[0].checked){
            localStorage.setItem(NAME,name);
        }else{
            sessionStorage.setItem(NAME,name);
        }
    }

    function get(){
        var storages=document.getElementsByName("storage");
        if(storages[0].checked){
            var result=localStorage.getItem(NAME);
            alert("name="+result);
        }else{
            var result=sessionStorage.getItem(NAME);
            alert("name="+result);
        }
    }


</script>

请输入您的名字：
<input type="text" id="name">
<br><br>
请选择保存方式：
<input type="radio" name="storage" value="local" checked="checked">localStorage
<input type="radio" name="storage" value="session">sessionStorage
<br><br>
<input type="button" value="存入数据" onclick="save()"/>
<input type="button" value="获取数据" onclick="get()"/>
</body>
</html>
