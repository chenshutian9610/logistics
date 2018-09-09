<%@ page contentType="text/html;charset=utf-8" %>
<%@ page isELIgnored="false" %>
<%@ page import="web.beans.Knowledge" %>
<%@ page import="web.beans.Member" %>
<html>
<head>
    <title>信息</title>
</head>
<body>
<%@ include file="toolbar" %>
<center>
    <% String readonly = (String) request.getAttribute("readonly");
        Knowledge knowledge = (Knowledge) request.getAttribute("knowledge");
        Member member = (Member) request.getAttribute("member");
        String by = (String) request.getAttribute("by");
        Object phone = request.getAttribute("phone");
        String real = "";
        if ("".equals(readonly))
            real = "?real=true";%>
    <h3 style="color: red">${requestScope.message }</h3>
    <form action="knowledgeAction_publish<%=real %>" method="post">
        <table height="60%" width="60%" border="1" cellspacing="0" style="border-collapse: collapse;">
            <tr align="center">
                <td>标题</td>
                <td <% if ("readonly".equals(readonly)) { %> colspan="3" <%} %>>
                    <input type="text" name="k.title" size="60" <%=readonly %>
                           value="<%=knowledge.getTitle()%>">
                </td>
            </tr>
            <% if ("readonly".equals(readonly)) { %>
            <tr align="center">
                <td width="25% ">发布人</td>
                <td width="25% "><%=member.getName()%>
                </td>
                <td width="25% ">发布时间</td>
                <td><%=knowledge.getDate() %>
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
            <tr align="center">
                <td width="25%">内容</td>
                <td colspan="3"><textarea rows="10" cols="60" name="k.info"
                        <%=readonly %>><%=knowledge.getInfo()%></textarea></td>
            </tr>
        </table>
        <br>
        <% if ("管理员模式".equals(session.getAttribute("message"))) { %>
        <input type="hidden" name="k.id" value="<%=knowledge.getId()%>"/> <input
            type="submit" value="修改"/> &nbsp;&nbsp;&nbsp;&nbsp; <a
            href="knowledgeAction_delete?id=<%=knowledge.getId()%>">删除</a>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <%} else if (real.length() != 0) { %>
        <input type="submit" value="发布"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <%} %>
        <a href="knowledgeAction_entry">返回</a>
    </form>
</center>
</body>
</html>