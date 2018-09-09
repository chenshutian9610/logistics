<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<c:set var="title" value="货物信息"/>
<c:set var="type" value="goods"/>
<%@ include file="components/entry-front.jsp"%>
    <tr align="center">
        <td>货物类型</td>
        <td>货物名称</td>
        <td>起点</td>
        <td>目的地</td>
        <td>货物重量</td>
        <td>发布日期</td>
        <td>详细信息</td>
    </tr>
    <c:forEach items="${requestScope.result}" var="result">
        <tr align="center">
            <td>${result.type}</td>
            <td>${result.name}</td>
            <td>${result.start}</td>
            <td>${result.end}</td>
            <td>${result.weight}</td>
            <td>${result.date}</td>
            <td><a href="${type}Action_info?id=${result.id}">详细</a></td>
        </tr>
    </c:forEach>
<%@ include file="components/entry-after.jsp"%>