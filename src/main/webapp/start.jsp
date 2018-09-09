<%@ page contentType="text/html;charset=utf-8" %>
<%@ page isELIgnored="false" %>
<%@ page import="web.beans.*,java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <style>
        p {
            background: #00ff66
        }

        r {
            color: red
        }
    </style>
</head>
<body>
<%@ include file="toolbar" %>
<% List<Corporation> cors = (List<Corporation>) request.getAttribute("cors");
    List<Inform> informs = (List<Inform>) request.getAttribute("informs");
    String occupy = (String) request.getAttribute("occupy");
    Member member = null;
    Corporation cor = null;
    List<Goods> goods = null;
    List<Car> cars = null;
    List<Corporation> cor_s = null;
    if (occupy != null) {
        member = (Member) request.getAttribute("member");
        goods = (List<Goods>) request.getAttribute("g_s");
        cars = (List<Car>) request.getAttribute("c_s");
        cor = (Corporation) request.getAttribute("cor");
        cor_s = (List<Corporation>) request.getAttribute("cor_s");
        if (goods == null)
            goods = new ArrayList<Goods>();
        if (cars == null)
            cars = new ArrayList<Car>();
    } else {
        goods = (List<Goods>) request.getAttribute("goods");
        cars = (List<Car>) request.getAttribute("cars");
    }
%>
<table width="100%" height="100%">
    <tr>
        <td width="22%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <r>${requestScope.message }</r>
            <br>
            <br>
            <form action="loginAction_login" method=post>
                用户名&nbsp;&nbsp;<input type="text" size="12" name="name"/><br>
                密码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="password" size="12"
                                                        name="pwd"/> <br>
                <br> <select name="kind">
                <option>会员</option>
                <option>管理员</option>
            </select>&nbsp;&nbsp; <input type="submit" value="登录"/>&nbsp;&nbsp; <input
                    type="reset" value="重置"/><br>
            </form>&nbsp; <a href="register.jsp">注册</a>&nbsp;&nbsp; <a
                    href="find-pwd.jsp">找回密码</a>
        </td>
        <td rowspan="2" valign="top"><br> <% if (occupy == null) { %>
            <p>货物信息</p>
            <br> <% for (Goods g : goods) { %> <a
                    href="goodsAction_info?id=<%=g.getId()%>"><%=g.getName() %>
            </a><br>
            <%} %>
            <p>车辆信息</p> <br> <% for (Car c : cars) { %> <a
                    href="carAction_info?id=<%=c.getId()%>"><%=c.getTitle() %>
            </a><br>
            <%} %>
            <p>企业信息</p> <br> <% for (Corporation corporation : cors) { %> <a
                    href="corporationAction_info?id=<%=corporation.getId()%>"><%=corporation.getName() %>
            </a><br>
            <%} %> <%} else if ("member".equals(occupy)) {%> 用户名为：&nbsp;<%=member.getName() %><br>
            <br> 所属公司：&nbsp;<% if (cor == null) %>空<%else{ %> <a
                    href="corporationAction_info?id=<%=cor.getId()%>"><%=cor.getName() %>
            </a>
            <%} %><br> 该用户发布过的货物信息如下：<br>
            <ul>
                <% for (Goods g : goods) { %>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <li><a href="goodsAction_info?id=<%=g.getId()%>"><%=g.getName() %>
                </a><br>
                        <%} %>
            </ul>
            该用户发布过的车辆信息如下：<br>
            <ul>
                <% for (Car c : cars) { %>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <li><a href="carAction_info?id=<%=c.getId()%>"><%=c.getTitle() %>
                </a><br>
                        <%} %>
            </ul>
            <%} else if ("start".equals(occupy)) {%> 符合条件的货物信息如下：<br>
            <ul>
                <% for (Goods g : goods) { %>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <li><a href="goodsAction_info?id=<%=g.getId()%>"><%=g.getName() %>
                </a><br>
                        <%} %>
            </ul>
            符合条件的车辆信息如下：<br>
            <ul>
                <% for (Car c : cars) { %>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <li><a href="carAction_info?id=<%=c.getId()%>"><%=c.getTitle() %>
                </a><br>
                        <%} %>
            </ul>
            <%} else if ("cor".equals(occupy)) {%> 符合条件的公司如下：<br>
            <ul>
                <% for (Corporation corporation : cor_s) { %>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <li><a
                        href="corporationAction_info?id=<%=corporation.getId()%>"><%=corporation.getName() %>
                </a><br>
                        <%} %>
            </ul>
            <%} %></td>
        <td align="center" width="20%" valign="top"><br>
            <h1>公告</h1>
            <br> <% for (Inform i : informs) { %> <a
                    href="informAction_info?id=<%=i.getId()%>"><%=i.getTitle() %>
            </a><br>
            <%} %></td>
    </tr>
    <tr>
        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <r>${requestScope.search_message }</r>
            <br>
            <br>
            <form action="loginAction_search" method=post margin-left="30">
                搜索框&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" size="12"
                                                   name="condition"/><br>
                <br> 搜索类型&nbsp;&nbsp;&nbsp;&nbsp; <select name="kind">
                <option>用户名</option>
                <option>起始地</option>
                <option>公司名</option>
            </select><br>
                <br> <input type="submit" value="搜索"/><br>
            </form>
        </td>
        <td align="center">
            <h1>企业</h1>
            <br> <% for (Corporation corporation : cors) { %> <a
                href="<%--企业链接--%>"><%=corporation.getName() %>----<%=corporation.getLink() %>
        </a><br>
            <%} %>
        </td>
    </tr>
</table>
</body>
</html>