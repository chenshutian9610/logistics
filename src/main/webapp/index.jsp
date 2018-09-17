<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html xmlns:c="http://www.w3.org/1999/XSL/Transform">
<head>
    <title>首页</title>
    <style>
        red {
            color: red;
        }

        .green {
            background: #00ff66
        }
    </style>
</head>
<body>
<%@ include file="components/toolbar.jsp" %>
<br><br>
<table width="100%">
    <tr valign="top">
        <!-- login -->
        <td width="25%" align="left">
            &emsp;&emsp;&emsp;&emsp;&nbsp;<red>${requestScope.message}</red>
            <form action="/member/login" method="post">
                <p>&emsp;&emsp;姓名：<input type="text" name="name" required></p>
                <p>&emsp;&emsp;密码：<input type="text" name="pwd"></p>
                <p>&emsp;&emsp;
                    <select name="identity">
                        <option>会员</option>
                        <option>管理员</option>
                    </select>&emsp;
                    <input type="submit" value="确认">&emsp;
                    <input type="reset" value="重置">
                </p>
                <p>&emsp;&emsp;&emsp;&emsp;&nbsp;
                    <a href="register.jsp">注册</a>&emsp;
                    <a href="findPWD.jsp">找回密码</a>
                </p>
            </form>
        </td>
        <!-- result -->
        <td rowspan="2" align="left">
            <c:choose>
                <c:when test="${show=='member'}">
                    <p>&emsp;&emsp;用户名：${member}</p>
                    <p>&emsp;&emsp;注册的公司：
                        <a href="/corporation/info?id=${corporation.id}">${corporation.name}</a>
                    </p>
                    <p>&emsp;&emsp;发布的货物信息：</p>
                    <c:forEach items="${goods}" var="result">
                        <p>&emsp;&emsp;&emsp;&emsp;<a href="/goods/info?id=${result.id}">${result.name}</a></p>
                    </c:forEach>
                    <p>&emsp;&emsp;发布的车辆信息：</p>
                    <c:forEach items="${cars}" var="result">
                        <p>&emsp;&emsp;&emsp;&emsp;<a href="/car/info?id=${result.id}">${result.title}</a></p>
                    </c:forEach>
                </c:when>
                <c:when test="${show=='corporation'}">
                    <p>&emsp;&emsp;符合条件的公司如下：</p>
                    <c:forEach items="${cors}" var="result">
                        <p>&emsp;&emsp;&emsp;&emsp;<a href="/corporation/info?id=${result.id}">${result.name}</a>
                        </p>
                    </c:forEach>
                </c:when>
                <c:when test="${show=='start'}">
                    <p>&emsp;&emsp;符合条件的货物信息如下：</p>
                    <c:forEach items="${goods}" var="result">
                        <p>&emsp;&emsp;&emsp;&emsp;<a href="/goods/info?id=${result.id}">${result.name}</a></p>
                    </c:forEach><br>
                    <p>&emsp;&emsp;符合条件的车辆信息如下：</p>
                    <c:forEach items="${cars}" var="result">
                        <p>&emsp;&emsp;&emsp;&emsp;<a href="/car/info?id=${result.id}">${result.title}</a></p>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p class="green">&emsp;&emsp;发布的货物信息：</p>
                    <c:forEach items="${goods}" var="result">
                        <p>&emsp;&emsp;&emsp;&emsp;<a href="/goods/info?id=${result.id}">${result.name}</a></p>
                    </c:forEach><br>
                    <p class="green">&emsp;&emsp;发布的车辆信息：</p>
                    <c:forEach items="${cars}" var="result">
                        <p>&emsp;&emsp;&emsp;&emsp;<a href="/car/info?id=${result.id}">${result.title}</a></p>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </td>
        <!-- inform -->
        <td width="25%" align="center">
            <h1>公告</h1>
            <c:forEach items="${informs}" var="result">
                <a href="/inform/info?id=${result.id}">${result.title}</a><br>
            </c:forEach>
        </td>
    </tr>
    <tr valign="top">
        <!-- search -->
        <td align="left">
            <br><br>&emsp;&emsp;&emsp;&emsp;&emsp;<red>${search_message}</red>
            <form action="/search" method="post">
                <p>&emsp;&emsp;搜索：<input type="text" name="condition" required></p>
                <p>&emsp;&emsp;
                    <select name="status">
                        <option>用户名</option>
                        <option>公司名</option>
                        <option>起始地</option>
                    </select>&emsp;
                    <input type="submit" value="提交">&emsp;
                    <input type="reset" name="重置">
                </p>
            </form>
        </td>
        <!-- corporation -->
        <td align="center">
            <h1>公司</h1>
            <c:forEach items="${corporations}" var="result">
                <a href="/corporation/info?id=${result.id}">${result.name}</a><br>
            </c:forEach>
        </td>
    </tr>
</table>
</body>
</html>