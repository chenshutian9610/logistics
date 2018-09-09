<%@ page contentType="text/html;charset=utf-8" %>
<%@ page isELIgnored="false" %>
<%@ page import="web.beans.Car" %>
<%@ page import="web.beans.Member" %>
<html>
<head>
    <title>信息</title>
</head>
<body>
<%@ include file="toolbar" %>
<center>
    <% String readonly = (String) request.getAttribute("readonly");
        Car car = (Car) request.getAttribute("car");
        Member member = (Member) request.getAttribute("member");
        Object phone = request.getAttribute("phone");
        String by = (String) request.getAttribute("by");
        String real = "";
        if ("".equals(readonly))
            real = "?real=true";%>
    <h3 style="color: red">${requestScope.message }</h3>
    <form action="carAction_publish<%=real %>" method="post">
        <table width="60%" height="60%" border="1">
            <tr align="center">
                <td width="25% ">标题</td>
                <td width="400"><input type="text" name="c.title" size="20"
                    <%=readonly %> value="<%=car.getTitle()%>"></td>
                <td width="400">起始地点</td>
                <td width="400"><input type="text" name="c.start" size="20"
                    <%=readonly %> value="<%=car.getStart()%>"></td>
            </tr>
            <tr align="center">
                <td>车数量</td>
                <td><input type="text" name="c.count" size="20" <%=readonly %>
                           value="<%=car.getCount()%>"></td>
                <td>每辆车限重(吨)</td>
                <td><input type="text" name="c.capacity" size="20"
                    <%=readonly %> value="<%=car.getCapacity()%>"></td>
            </tr>
            <% if ("readonly".equals(readonly)) { %>
            <tr align="center">
                <td>发布人</td>
                <td><%=member.getName() %>
                </td>
                <td>发布时间</td>
                <td><%=car.getDate() %>
                </td>
            </tr>
            <tr align="center">
                <td>所属公司</td>
                <td><%=by %>
                </td>
                <td>联系电话</td>
                <td><%=phone %>
                </td>
            </tr>
            <% } %>
            <tr height="200" align="center">
                <td>备注</td>
                <td colspan="3"><textarea rows="10" cols="60" name="c.info"
                        <%=readonly %>><%=car.getInfo()%></textarea></td>
            </tr>
        </table>
        <% if ("管理员模式".equals(session.getAttribute("message"))) { %>
        <input type="hidden" name="c.id" value="<%=car.getId()%>"/> <input
            type="submit" value="修改"/> &nbsp;&nbsp;&nbsp;&nbsp; <a
            href="carAction_delete?id=<%=car.getId()%>">删除</a>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <%} else if (real.length() != 0) { %>
        <input type="submit" value="发布"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <%} %>
        <a href="carAction_entry">返回</a>
    </form>
</center>
</body>
</html>