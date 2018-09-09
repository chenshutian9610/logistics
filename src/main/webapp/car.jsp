<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<c:set var="title" value="车辆信息"/>
<c:set var="type" value="car"/>
<%@ include file="components/entry-front.jsp" %>
    <tr align="center">
        <td>标题</td>
        <td>货车数量</td>
        <td>每辆车限重</td>
        <td>起点</td>
        <td>发布日期</td>
        <td>详细信息</td>
    </tr>
    <c:forEach items="${requestScope.result}" var="result">
        <tr align="center">
            <td>${result.name}</td>
            <td>${result.count}</td>
            <td>${result.capacity}</td>
            <td>${result.start}</td>
            <td>${result.date}</td>
            <td><a href="${type}Action_info?id=${result.id}">详细</a></td>
        </tr>
    </c:forEach>
<%@ include file="components/entry-after.jsp" %>