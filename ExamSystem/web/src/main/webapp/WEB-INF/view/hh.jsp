<%--
  Created by IntelliJ IDEA.
  User: chao
  Date: 2018/8/8
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript"
            src="${rootpath }/common/jquery-3.0.0.min.js"></script>
    <script type="text/javascript">
        function test() {
            var aa = $("#nameid").val();
            alert("输入的手机号是"+aa);
            $.post("yanzheng.htm", {
                "nameid" : aa
            }, function(result) {
                $("#spanid").html(result);

            })

        }
    </script>
</head>
<body>
<body>
<table align="center">
    <tr>
        <form action="${rootpath }/loginSubmit.htm" method="post">
            <input id="nameid" type="text" name="name"
                   value="${requestScope.name }">用户名 <br> <input
                type="password" name="password" value="">密码 <br> <input
                type="text" name="rand" value="">验证码 <br> <input
                type="submit" name="" value="提交">
        </form>
        <br> ${requestScope.info }
        <button onclick="test()">获取验证码</button>
        <br>
        <span id="spanid"></span>
    </tr>
</table>
</body>
</body>
</html>
