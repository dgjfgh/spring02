<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>cs</title>
    <%--<script src="https://code.jquery.com/jquery.js"></script>--%>
    <script src="<%=path%>/js/jquery-1.7.1.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            $("#hello").click(function () {
//                $(this).text("sd")
//                $(this).html("aa")
//                alert($(this).width())
                $.ajax({
                    type: "POST",
                    url: "/springmvc01/testSqlxml",
                    data: "uname=haha",
                    async: true,
                    cache: true,
                    success: function (data) {
                        alert(data);
                    }
                })
            })
//            $("#hello").hover(
//                function () {
//                    $(this).html("aa")
//
//                },
//                function () {
//                    $(this).html("Hello")
//                }
//            )
        })
    </script>
</head>
<body>
<div id="hello"
>Hello</div>

</body>
</html>


