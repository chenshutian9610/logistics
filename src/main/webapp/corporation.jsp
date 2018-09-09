<%@ page contentType="text/html;charset=utf-8" %>
<%@ page isELIgnored="false" %>
<%@ page import="web.beans.Corporation,java.util.List" %>
<html>
<head>
    <title>公司信息</title>
</head>
<body>
<%@ include file="toolbar" %>
<center>
    <h1>公司信息</h1>
    <table width="100%" border="1">
        <tr>
            <td>公司名称</td>
            <td>联系电话</td>
            <td>地址</td>
            <td>详细信息</td>
        </tr>
        <% List<Corporation> cors = (List<Corporation>) request.getAttribute("result");
            if (cors == null) return;
            for (Corporation obj : cors) {
                int id = obj.getId();%>
        <tr>
            <td><%=obj.getName() %>
            </td>
            <td><%=obj.getPhone() %>
            </td>
            <td><%=obj.getAddress() %>
            </td>
            <td><a href="corporationAction_info?id=<%=id %>">详细</a></td>
        </tr>
        <% } %>
    </table>
    第<%=request.getAttribute("index") %>页，共<%=request.getAttribute("page") %>页&nbsp;
    <a href="corporationAction_index?index=1">首页</a>&nbsp; <a
        href="corporationAction_index?index=<">上一页</a>&nbsp; <a
        href="corporationAction_index?index=>">下一页</a>&nbsp; <a
        href="corporationAction_index?index=0">尾页</a><br>
    <br>
    <% if (!"管理员模式".equals(session.getAttribute("message"))) { %>
    <a href="corporationAction_publish">发布</a>
    <%} %><br>
    <br> <a href="loginAction_init"><h3 style="color: red">${requestScope.message }</h3></a>
    <h3>${requestScope.message1 }</h3>
</center>
</body>
</html>