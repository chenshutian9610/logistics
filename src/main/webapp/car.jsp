<%@ page contentType="text/html;charset=utf-8" %>
<%@ page isELIgnored="false" %>
<%@ page import="web.beans.Car,java.util.List" %>
<html>
<head>
    <title>车辆信息</title>
</head>
<body>
<%@ include file="toolbar" %>
<center>
    <h1>车辆信息</h1>
    <table width="80%" border="1" cellspacing="0" style="border-collapse: collapse;">
        <tr>
            <td>标题</td>
            <td>货车数量</td>
            <td>每辆车限重</td>
            <td>起点</td>
            <td>发布日期</td>
            <td>详细信息</td>
        </tr>
        <% List<Car> cars = (List<Car>) request.getAttribute("result");
            if (cars == null) return;
            for (Car obj : cars) {
                int id = obj.getId();%>
        <tr>
            <td><%=obj.getTitle() %>
            </td>
            <td><%=obj.getCount() %>
            </td>
            <td><%=obj.getCapacity() %>
            </td>
            <td><%=obj.getStart() %>
            </td>
            <td><%=obj.getDate() %>
            </td>
            <td><a href="carAction_info?id=<%=id %>">详细</a></td>
        </tr>
        <% } %>
    </table>
    第<%=request.getAttribute("index") %>页，共<%=request.getAttribute("page") %>页&nbsp;
    <a href="carAction_index?index=1">首页</a>&nbsp; <a
        href="carAction_index?index=<">上一页</a>&nbsp; <a
        href="carAction_index?index=>">下一页</a>&nbsp; <a
        href="carAction_index?index=0">尾页</a><br>
    <br>
    <% if (!"管理员模式".equals(session.getAttribute("message"))) { %>
    <a href="carAction_publish">发布</a>
    <%} %><br>
    <br> <a href="loginAction_init"><h3 style="color: red">${requestScope.message }</h3></a>
    <h3>${requestScope.message1 }</h3>
</center>
</body>
</html>