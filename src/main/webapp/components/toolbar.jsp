<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="web.tool.DateUtils" %>
<table width="100%">
    <td colspan="3" bgcolor="#5d7d44" height="100" width="100%"/>
    <tr height="10%">
        <td colspan="3">&emsp;&emsp;
                <%=DateUtils.getDate()%>&emsp;&emsp;
            <a href="/">本站首页</a>&emsp;&emsp;
            <a href="/dynamicInfo/entry">物流动态</a>&emsp;&emsp;
            <a href="/knowledge/entry">物流知识</a>&emsp;&emsp;
            <a href="/goods/entry">货物信息</a>&emsp;&emsp;
            <a href="/car/entry">车辆信息</a>&emsp;&emsp;
            <a href="/corporation/entry">企业信息</a>&emsp;&emsp;
            <a href="/inform/entry">公告</a>&emsp;&emsp;
            <a href="/member/logout">退出</a>&emsp;&emsp;
            ${sessionScope.message}
        </td>
    </tr>
</table>