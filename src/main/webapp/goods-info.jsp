<%@ page contentType="text/html;charset=utf-8" %>
<%@ page isELIgnored="false" %>
<%@ page import="web.beans.Goods" %>
<%@ page import="web.beans.Member" %>
<html>
<head>
    <title>信息</title>
</head>
<body>
<%@ include file="toolbar" %>
<center>
    <% String readonly = (String) request.getAttribute("readonly");
        Goods goods = (Goods) request.getAttribute("goods");
        Member member = (Member) request.getAttribute("member");
        String by = (String) request.getAttribute("by");
        Object phone = request.getAttribute("phone");
        String real = "";
        if ("".equals(readonly))
            real = "?real=true";%>
    <h3 style="color: red">${requestScope.message }</h3>
    <form action="goodsAction_publish<%=real %>" method="post">
        <table height="60%" width="60%" border="1" cellspacing="0" style="border-collapse: collapse;">
            <tr align="center">
                <td width="25% ">货物名称</td>
                <td width="400"><input type="text" name="g.name" size="20"
                    <%=readonly %> value="<%=goods.getName()%>"></td>
                <td width="400">货物类型</td>
                <td width="400"><input type="text" name="g.type" size="20"
                    <%=readonly %> value="<%=goods.getType()%>"></td>
            </tr>
            <tr align="center">
                <td>起始地</td>
                <td><input type="text" name="g.start" size="20" <%=readonly %>
                           value="<%=goods.getStart()%>"></td>
                <td>目的地</td>
                <td><input type="text" name="g.end" size="20" <%=readonly %>
                           value="<%=goods.getEnd()%>"></td>
            </tr>
            <tr align="center">
                <td>重量</td>
                <td><input type="text" name="g.weight" size="20"
                        <%=readonly %> value="<%=goods.getWeight()%>"/></td>
                <td>单位</td>
                <td>吨</td>
            </tr>
            <% if ("readonly".equals(readonly)) { %>
            <tr align="center">
                <td>发布人</td>
                <td><%=member.getName()%>
                </td>
                <td>发布时间</td>
                <td><%=goods.getDate() %>
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
                <td colspan="3"><textarea rows="10" cols="60" name="g.info"
                        <%=readonly %>><%=goods.getInfo()%></textarea></td>
            </tr>
        </table>
        <br>
        <% if ("管理员模式".equals(session.getAttribute("message"))) { %>
        <input type="hidden" name="g.id" value="<%=goods.getId()%>"/> <input
            type="submit" value="修改"/> &nbsp;&nbsp;&nbsp;&nbsp; <a
            href="goodsAction_delete?id=<%=goods.getId()%>">删除</a>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <%} else if (real.length() != 0) { %>
        <input type="submit" value="发布"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <%} %>
        <a href="goodsAction_entry">返回</a>
    </form>
</center>
</body>
</html>