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
    <script src="<%=path%>/js/jquery-3.2.1.js"></script>

    <script type="text/javascript">
            $(document).ready(function () {
                $("#hello").click(function () {
//                    $(this).text("sd")
                    $(this).html("aa")
//                    alert($(this).width())
//                    $.ajax({
//                        type: "POST",
//                        url: "/springmvc/test",
//                        data: "uname=haha",
//                        async: true,
//                        cache: true,
//                        success: function (data ) {
//                            alert(data );
//                        }
//                    })
                })
                $("#hello").hover(
                        function(){
//                            $("#hello").val("jjj")

                        },
                        function(){
//                            alert("拜拜! 现在你离开了 p1!");
                        }
                )
            })
    </script>
</head>
<body>
    <p id="hello"
          >Hello</p>

</body>
</html>


