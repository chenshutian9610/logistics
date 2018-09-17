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
<br><br>
<center>
    <h1>请填写您的信息</h1>
    <form action="/member/registering" method=post>
        <p>姓名：&nbsp;&nbsp;<input type="text" name="name" required></p>
        <p>性别：&nbsp;&nbsp;<input type="text" name="sex"></p>
        <p>电话：&nbsp;&nbsp;<input type="text" name="phone" pattern="\d+" id="phone"></p>
        <p>邮箱：&nbsp;&nbsp;<input type="email" name="email" required></p>
        <p>问题：&nbsp;&nbsp;<input type="text" name="question" required></p>
        <p>答案：&nbsp;&nbsp;<input type="text" name="answer"></p>
        <p>密码：&nbsp;&nbsp;<input type="password" name="pwd"></p>
        <p>确认密码：<input type="password" name="repwd">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
        <p><input type="submit" value="注册"/>&nbsp;&nbsp;<input type="reset" value="重置"/></p>
    </form>
    <a href="/">返回主页</a>
    <br><br>${requestScope.message}
</center>
</body>
</html>