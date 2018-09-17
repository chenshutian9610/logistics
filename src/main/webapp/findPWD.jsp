<%@ page contentType="text/html;charset=utf-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>找回密码</title>
    <style>
        body {
            background-color: lightskyblue
        }
    </style>
</head>
<body>
<center>
    <br><br>
    <h1>请填写您的信息</h1>
    <form action="/member/findPwd" method="post">
        <p>姓名：<input type="text" name="name" required></p>
        <p>邮箱：<input type="email" name="email" required></p>
        <p>问题：<input type="text" name="question" required></p>
        <p>答案：<input type="text" name="answer"></p>
        <input type="submit" value="提交">&nbsp;&nbsp;<input type="reset" value="重置">
    </form>
    ${requestScope.message}<br><br>
    <a href="/">返回主页</a>
</center>
</body>
</html>