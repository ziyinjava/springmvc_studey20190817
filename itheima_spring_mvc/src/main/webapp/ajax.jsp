<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <script>
        var userList = new Array();
        userList.push({"username":"zhangsan","age":18});
        userList.push({"username":"lisi","age":28});

        $.ajax({
            type:"POST",
            url:"${pageContext.request.contextPath}/user/quick15",
            data:JSON.stringify(userList),
            contentType:"application/json;charset=utf-8"
        });

        <%--$.ajaxSettings.contentType="application/json;charset=utf-8";--%>
        <%--$.post("${pageContext.request.contextPath}/user/quick15",{username:"zhangsan"},function (data) {--%>
            <%--console.log(data.username);--%>
        <%--});--%>
    </script>
</head>
<body>

</body>
</html>
