<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<c:set var="title" value="公司信息"/>
<c:set var="type" value="corporation"/>
<%@ include file="components/entry-front.jsp" %>
    <tr align="center">
        <td>公司名称</td>
        <td>联系电话</td>
        <td>地址</td>
        <td>注册时间</td>
        <td>详细信息</td>
    </tr>
    <c:forEach items="${requestScope.result}" var="result">
        <tr align="center">
            <td>${result.name}</td>
            <td>${result.phone}</td>
            <td>${result.address}</td>
            <td>${result.date}</td>
            <td><a href="${type}Action_info?id=${result.id}">详细</a></td>
        </tr>
    </c:forEach>
<%@ include file="components/entry-after.jsp" %>