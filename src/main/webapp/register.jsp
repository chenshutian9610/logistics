<%@ page contentType="text/html;charset=utf-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>注册页面</title>
    <style>
        body {
            background-color: lightskyblue
        }
    </style>
    <meta charset="utf-8">
</head>
<body>
<br>
<br>
<center>
    <h1>请填写您的信息</h1>
    <p>提示，打上星号的选项必须填写</p>
    <form action="loginAction_register" method=post>
        <table>
            <tr>
                <td>姓名*</td>
                <td><input type="text" name="name"/></td>
            </tr>
            <tr>
                <td>性别</td>
                <td><input type="text" name="sex"/></td>
            </tr>
            <tr>
                <td>电话（请输入正确格式）</td>
                <td><input type="text" name="phone"/></td>
            </tr>
            <tr>
                <td>邮箱*</td>
                <td><input type="text" name="email"/></td>
            </tr>
            <tr>
                <td>找回密码-问题*</td>
                <td><input type="text" name="question"/></td>
            </tr>
            <tr>
                <td>找回密码-答案*</td>
                <td><input type="text" name="answer"/></td>
            </tr>
            <tr>
                <td>密码</td>
                <td><input type="password" name="pwd"/></td>
            </tr>
            <tr>
                <td>确认密码</td>
                <td><input type="password" name="repwd"/></td>
            </tr>
            <tr>
                <td align="center"><input type="submit" value="注册"/></td>
                <td align="center"><input type="reset" value="重置"/></td>
            </tr>
        </table>
    </form>
    <a href="loginAction_init">返回主页</a> <br>
    <br> ${requestScope.message}
    <% if ("true".equals(request.getParameter("error"))) { %>
    <c style="color:red"><%="该用户名已被注册过" %>
    </c>
    <%} %>
</center>

</body>
</html>