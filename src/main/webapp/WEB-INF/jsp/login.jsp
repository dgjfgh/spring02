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
    <!--form  表单-->
    <script>
        function validateForm() {
            var name=document.forms["loginForm"]["name"].value;
            var pwd=document.forms["loginForm"]["pwd"].value;
            if (name==="q"&&pwd==="w"){
                <%
//                response.sendRedirect("www.baidu.com");
//                request.getRequestDispatcher("/main").forward(request, response);
                 %>
            }else {
                alert("error")
            }
        }
    </script>
</head>

<body>

<form name="loginForm" action="/springmvc/login"
      <%--onsubmit="return validateForm()" --%>
      method="post">
    账号: <input type="text" name="name">
    密码: <input type="password" name="pwd">
    <input type="submit" value="登录">
</form>

</body>
</html>

