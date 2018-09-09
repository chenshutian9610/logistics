<%@ page contentType="text/html;charset=utf-8" %>
<tr align="center">
    <td>标题</td>
    <td>发布日期</td>
    <td>详细信息</td>
</tr>
<c:forEach items="${requestScope.result}" var="result">
    <tr align="center">
        <td>${result.name}</td>
        <td>${result.date}</td>
        <td><a href="${type}Action_info?id=${result.id}">详细</a></td>
    </tr>
</c:forEach>