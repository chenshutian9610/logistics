<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <title>找回密码</title>
    <style>
        body {
            background-color: lightskyblue
        }
    </style>
    <meta charset="utf-8">
</head>
<body>
<center>
    <br>
    <br>
    <h1>请填写您的信息</h1>
    <form action="loginAction_findPWD" method="post">
        <table>
            <tr>
                <td>用户名</td>
                <td><input type="text" name="name"></td>
            </tr>
            <tr>
                <td>邮箱</td>
                <td><input type="text" name="email"></td>
            </tr>
            <tr>
                <td>问题</td>
                <td><input type="text" name="question"></td>
            </tr>
            <tr>
                <td>答案</td>
                <td><input type="text" name="answer"></td>
            </tr>
        </table>
        <input type="submit" value="提交">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="reset"
                                                                value="重置">
    </form>
    ${requestScope.message} <br>
    <br> <a href="loginAction_init">返回主页</a>
</center>
</body>
</html>