<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <!-- 数据类型和数据名称要对应上, 这里第二个参数是age,因为age是int类型的,就不传字母, 会转换失败-->
    <%--<form action="${pageContext.request.contextPath}/user/quick14" method="post">--%>
        <%--&lt;%&ndash;表明是第几个User对象的username age&ndash;%&gt;--%>
        <%--<input type="text" name="userList[0].username"><br/>--%>
        <%--<input type="text" name="userList[0].age"><br/>--%>
        <%--<input type="text" name="userList[1].username"><br/>--%>
        <%--<input type="text" name="userList[1].age"><br/>--%>
            <%--&lt;%&ndash;<input type="text" name="userMap['one'].username"><br/>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<input type="text" name="userMap['one'].age"><br/>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<input type="text" name="userMap['two'].username"><br/>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<input type="text" name="userMap['two'].age"><br/>&ndash;%&gt;--%>
        <%--<input type="submit" value="提交">--%>
    <%--</form>--%>

    <form action="${pageContext.request.contextPath}/user/quick14" method="post">

       <input type="text" name="userList[0].username" ><br/>
       <input type="text" name="userList[0].password" ><br/>
       <input type="text" name="userList[1].username" ><br/>
       <input type="text" name="userList[1].password" ><br/>
       <input type="text" name="userMap['one'].username" ><br/>
       <input type="text" name="userMap['one'].password" ><br/>
       <input type="text" name="userMap['two'].username" ><br/>
       <input type="text" name="userMapaccountMap['two'].password" ><br/>
        <input type="submit" value="保存">
    </form>
</body>
</html>
