<%@ page contentType="text/html;charset=utf-8" %>
<%@ page isELIgnored="false" %>
<%@ page import="web.beans.Goods,java.util.List" %>
<html>
<head>
    <title>货物信息</title>
</head>
<body>
<%@ include file="toolbar" %>
<center>
    <h1>货物信息</h1>
    <table width="80%" border="1" cellspacing="0" style="border-collapse: collapse;">
        <tr>
            <td>货物类型</td>
            <td>货物名称</td>
            <td>起点</td>
            <td>目的地</td>
            <td>货物重量</td>
            <td>发布日期</td>
            <td>详细信息</td>
        </tr>
        <% List<Goods> goods = (List<Goods>) request.getAttribute("result");
            if (goods == null) return;
            for (Goods obj : goods) {
                int id = obj.getId();%>
        <tr>
            <td><%=obj.getType() %>
            </td>
            <td><%=obj.getName() %>
            </td>
            <td><%=obj.getStart() %>
            </td>
            <td><%=obj.getEnd() %>
            </td>
            <td><%=obj.getWeight() %>
            </td>
            <td><%=obj.getDate() %>
            </td>
            <td><a href="goodsAction_info?id=<%=id %>">详细</a></td>
        </tr>
        <% } %>
    </table>
    第<%=request.getAttribute("index") %>页，共<%=request.getAttribute("page") %>页&nbsp;
    <a href="goodsAction_index?index=1">首页</a>&nbsp; <a
        href="goodsAction_index?index=<">上一页</a>&nbsp; <a
        href="goodsAction_index?index=>">下一页</a>&nbsp; <a
        href="goodsAction_index?index=0">尾页</a><br>
    <br>
    <% if (!"管理员模式".equals(session.getAttribute("message"))) { %>
    <a href="goodsAction_publish">发布</a>
    <%} %><br>
    <br> <a href="loginAction_init"><h3 style="color: red">${requestScope.message }</h3></a>
    <h3>${requestScope.message1 }</h3>
</center>
</body>
</html>