<%--
  Created by IntelliJ IDEA.
  User: yunfei
  Date: 2019/6/3
  Time: 9:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post">
    <input type="text" name="username" ><br>
    <input type="text" name="password" ><br>
    <input type="submit" value="登录" ><br>
</form>
</body>
</html>
