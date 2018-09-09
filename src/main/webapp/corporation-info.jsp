<%@ page contentType="text/html;charset=utf-8" %>
<%@ page isELIgnored="false" %>
<%@ page import="web.beans.Corporation" %>
<%@ page import="web.beans.Member" %>
<html>
<head>
    <title>信息</title>
</head>
<body>
<%@ include file="toolbar" %>
<center>
    <% String readonly = (String) request.getAttribute("readonly");
        Corporation cor = (Corporation) request.getAttribute("corporation");
        Member member = (Member) request.getAttribute("member");
        String real = "";
        if ("".equals(readonly))
            real = "?real=true";%>
    <h3 style="color: red">${requestScope.message }</h3>
    <form action="corporationAction_publish<%=real %>" method="post">
        <table width="60%" height="60%" border="1">
            <tr align="center">
                <td width="25% ">公司名称</td>
                <td width="400"><input type="text" name="cor.name" size="20"
                    <%=readonly %> value="<%=cor.getName()%>"></td>
                <td width="400">公司链接</td>
                <td width="400">
                    <% if ("".equals(readonly)) { %> <input type="text" name="cor.link"
                                                            size="20" value="<%=cor.getLink() %>"/> <%} else { %> <a
                        href="<%=cor.getLink() %>"><%=cor.getLink().length() == 0 ? "未设置" : cor.getLink() %>
                </a>
                    <%} %>
                </td>
            </tr>
            <tr align="center">
                <td>联系电话</td>
                <td><input type="text" name="cor.phone" size="20"
                    <%=readonly %> value="<%=cor.getPhone()%>"></td>
                <td>地址</td>
                <td><input type="text" name="cor.address" size="20"
                    <%=readonly %> value="<%=cor.getAddress()%>"></td>
            </tr>
            <% if ("readonly".equals(readonly)) { %>
            <tr align="center">
                <td>责任人</td>
                <td><%=member.getName()%>
                </td>
                <td>责任人电话</td>
                <td><%=member.getPhone() %>
                </td>
            </tr>
            <% } %>
            <tr height="200" align="center">
                <td>备注</td>
                <td colspan="3"><textarea rows="10" cols="60" name="cor.info"
                        <%=readonly %>><%=cor.getInfo()%></textarea></td>
            </tr>
        </table>
        <br>
        <% if ("管理员模式".equals(session.getAttribute("message"))) { %>
        <input type="hidden" name="cor.id" value="<%=cor.getId()%>"/> <input
            type="submit" value="修改"/> &nbsp;&nbsp;&nbsp;&nbsp; <a
            href="corporationAction_delete?id=<%=cor.getId()%>">删除</a>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <%} else if (real.length() != 0) { %>
        <input type="submit" value="发布"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <%} %>
        <a href="corporationAction_entry">返回</a>
    </form>
</center>
</body>
</html>