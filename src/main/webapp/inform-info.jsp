<%@ page contentType="text/html;charset=utf-8" %>
<%@ page isELIgnored="false" %>
<%@ page import="web.beans.Inform" %>
<html>
<head>
    <title>信息</title>
</head>
<body>
<%@ include file="toolbar" %>
<center>
    <% String readonly = (String) request.getAttribute("readonly");
        Inform inform = (Inform) request.getAttribute("inform");
        String real = (String) request.getAttribute("real");
        real = real == null ? "" : real;
        System.out.println(real);%>
    <h3 style="color: red">${requestScope.message }</h3>
    <form action="informAction_publish<%=real %>" method="post">
        <table height="60%" width="60%" border="1" cellspacing="0" style="border-collapse: collapse;">
            <tr align="center" height="50">
                <td width="25%">标题</td>
                <td><input type="text" name="i.title" size="60" <%=readonly %>
                           value="<%=inform.getTitle()%>"></td>
            </tr>
            <% if ("readonly".equals(readonly)) { %>
            <tr align="center" height="50">
                <td width="25%">发布时间</td>
                <td><%=inform.getDate() %>
                </td>
            </tr>
            <% } %>
            <tr height="200" align="center">
                <td width="25%">内容</td>
                <td><textarea rows="10" cols="60" name="i.info" <%=readonly %>><%=inform.getInfo()%></textarea>
                </td>
            </tr>
        </table>
        <br>
        <% if ("管理员模式".equals(session.getAttribute("message"))) { %>
        <% if (real.equals("?real=insertNow")) { %>
        <input type="submit" value="发布"/>&nbsp;&nbsp;&nbsp;&nbsp;
        <%} else { %>
        <input type="hidden" name="i.id" value="<%=inform.getId()%>"/>
        <% if (!"?real=updateNow".equals(real)) { %>
        <input type="hidden" name="real" value="update"/>
        <% } %>
        <input type="submit" value="修改"/>&nbsp;&nbsp;&nbsp;&nbsp; <a
            href="informAction_delete?id=<%=inform.getId()%>">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;
        <%
                }
            }
        %>
        <a href="informAction_entry">返回</a>
    </form>
</center>
</body>
</html>