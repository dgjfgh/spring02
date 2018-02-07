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
</head>

<script type="text/javascript">
    var as="sss"

    function test() {
        debugger;
        alert("asd1")
//        location.reload(true)
//        location.replace("tt")//相对地址/tt
    }
</script>
<body>
<input type="button" onclick="test()" value="haha"/>

</body>
</html>